package net.jukoz.me.entity.beasts;

import net.jukoz.me.resources.StateSaverAndLoader;
import net.jukoz.me.resources.datas.Disposition;
import net.jukoz.me.resources.datas.RaceType;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

// Beasts are mostly aggressive Entities which work much like wolves, while also allowing the player to mount them.
public class AbstractBeastEntity extends AbstractHorse {
    public static final EntityDataAccessor<Boolean> CHARGING = SynchedEntityData.defineId(AbstractBeastEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> SITTING = SynchedEntityData.defineId(AbstractBeastEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> CHEST = SynchedEntityData.defineId(AbstractBeastEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> RUNNING = SynchedEntityData.defineId(AbstractBeastEntity.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState chargeAnimationState = new AnimationState();
    public final AnimationState sittingAnimationState = new AnimationState();
    public final AnimationState startSittingAnimationState = new AnimationState();
    public final AnimationState stopSittingAnimationState = new AnimationState();

    protected int idleAnimationTimeout = 1000;
    protected int attackTicksLeft = 0;
    protected boolean hasCharged = false;
    protected boolean startedSitting = false;

    protected int chargeTimeout; // ticking cooldown of the charge attack

    public static final int ATTACK_COOLDOWN = 10;
    public static final float RESISTANCE = 0.15f;
    protected Vec3 targetDir = Vec3.ZERO;

    // Initializing ====================================================================================================
    protected AbstractBeastEntity(EntityType<? extends AbstractBeastEntity> entityType, Level world) {
        super(entityType, world);
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(CHARGING, false);
        builder.define(SITTING, false);
        builder.define(CHEST, false);
        builder.define(RUNNING, false);
    }

    protected void randomizeReinforcementsChance(RandomSource random) {

    }

    protected void setupAnimationStates() {

    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> data) {
        if (!this.firstTick && CHARGING.equals(data)) {
            this.chargeTimeout = this.chargeTimeout == 0 ? maxChargeCooldown() : this.chargeTimeout;
        }
        super.onSyncedDataUpdated(data);
    }

    private SlotAccess createInventoryStackReference(final int slot, final Predicate<ItemStack> predicate) {
        return new SlotAccess(){

            @Override
            public ItemStack get() {
                return AbstractBeastEntity.this.inventory.getItem(slot);
            }

            @Override
            public boolean set(ItemStack stack) {
                if (!predicate.test(stack)) {
                    return false;
                }
                AbstractBeastEntity.this.inventory.setItem(slot, stack);
                AbstractBeastEntity.this.syncSaddleToClients();
                return true;
            }
        };
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("Sitting", this.isSitting());
        nbt.putBoolean("ChestedBeast", this.hasChest());
        if (this.hasChest()) {
            ListTag nbtList = new ListTag();
            for(int i = 2; i < this.inventory.getContainerSize(); ++i) {
                ItemStack itemStack = this.inventory.getItem(i);
                if (!itemStack.isEmpty()) {
                    CompoundTag nbtCompound = new CompoundTag();
                    nbtCompound.putByte("Slot", (byte)i);
                    nbtList.add(itemStack.save(this.registryAccess(), nbtCompound));
                }
            }
            nbt.put("Items", nbtList);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setSitting(nbt.getBoolean("Sitting"));
        this.setHasChest(nbt.getBoolean("ChestedBeast"));
        this.createInventory();
        if (this.hasChest()) {
            ListTag nbtList = nbt.getList("Items", 10);

            for(int i = 0; i < nbtList.size(); ++i) {
                CompoundTag nbtCompound = nbtList.getCompound(i);
                int j = nbtCompound.getByte("Slot") & 255;
                if (j >= 2 && j < this.inventory.getContainerSize()) {
                    this.inventory.setItem(j, (ItemStack)ItemStack.parse(this.registryAccess(), nbtCompound).orElse(ItemStack.EMPTY));
                }
            }
        }
        this.syncSaddleToClients();
    }

    // Getters and Setters =============================================================================================
    protected boolean isMountable() {
        return true;
    }
    protected boolean isTamable() {
        return true;
    }

    protected Disposition getDisposition(){
        return null;
    }

    protected List<RaceType> getRaceType() {
        return null;
    }

    public boolean hasChest() {
        return this.entityData.get(CHEST);
    }

    public void setHasChest(boolean hasChest) {
        this.entityData.set(CHEST, hasChest);
    }

    public boolean canCarryChest() {
        return true;
    }
    public final boolean cannotFollowOwner() {
        return this.isSitting() || this.isPassenger() || this.mayBeLeashed() || this.getOwner() != null && this.getOwner().isSpectator();
    }

    public boolean shouldAttackWhenMounted() {
        return false;
    }

    public boolean canCharge() {
        return !this.isSitting() && !this.isVehicle();
    }

    public boolean isRunning() {
        return this.entityData.get(RUNNING);
    }

    public void setRunning(boolean running) {
        this.entityData.set(RUNNING, running);
    }

    @Override
    public int getJumpCooldown() {
        return this.chargeTimeout;
    }

    public double getMountedHeightOffset() {
        float f = Math.min(0.25F, this.walkAnimation.speed());
        float g = this.walkAnimation.position();
        return (double)this.getBbHeight() - 0.19 + (double)(0.12F * Mth.cos(g * 1.5F) * 2.0F * f);
    }

    public Player getOwner() {
        if(this.getOwnerUUID() != null) {
            return getPlayerByUuid(this.getOwnerUUID());
        }
        return null;
    }

    public boolean hasCharged() {
        return hasCharged;
    }

    public void setHasCharged(boolean hasCharged) {
        this.hasCharged = hasCharged;
    }

    @Override
    public boolean isPersistenceRequired() {
        return isTamed();
    }

    public boolean isSitting() {
        return this.entityData.get(SITTING);
    }

    public void setSitting(boolean sitting) {
        this.entityData.set(SITTING, sitting);
    }

    public boolean isCommandItem(ItemStack stack) {
        return false;
    }

    public void setCharging(boolean charging) {
        this.entityData.set(CHARGING, charging);
    }

    public boolean isCharging() {
        return this.entityData.get(CHARGING);
    }

    public int getChargeTimeout() {
        return this.chargeTimeout;
    }
    public void setChargeTimeout(int chargeTimeout) {
        this.chargeTimeout = chargeTimeout;
    }

    public int maxChargeCooldown() {
        return 400;
    }
    public int chargeDuration() {
        return 20;
    }

    protected float getAttackDamage() {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    // Equipment =======================================================================================================

    protected void dropEquipment() {
        super.dropEquipment();
        if (this.hasChest()) {
            if (!this.level().isClientSide) {
                this.spawnAtLocation(Blocks.CHEST);
            }

            this.setHasChest(false);
        }
    }

    @Override
    public SlotAccess getSlot(int mappedIndex) {
        int j;
        int i = mappedIndex - 400;
        if (i >= 0 && i < 2 && i < this.inventory.getContainerSize()) {
            if (i == 0) {
                return this.createInventoryStackReference(i, stack -> stack.isEmpty() || stack.is(Items.SADDLE));
            }
            if (i == 1) {
                return SlotAccess.NULL;
            }
        }
        if ((j = mappedIndex - 500 + 2) >= 2 && j < this.inventory.getContainerSize()) {
            return SlotAccess.forContainer(this.inventory, j);
        }
        return super.getSlot(mappedIndex);
    }

    public int getInventoryColumns() {
        return this.hasChest() ? 5 : 0;
    }

    private void addChest(Player player, ItemStack chest) {
        if(canCarryChest()) {
            this.setHasChest(true);
            this.playAddChestSound();
            if (!player.getAbilities().instabuild) {
                chest.shrink(1);
            }
            this.createInventory();
        }
    }

    protected void playAddChestSound() {
        this.playSound(SoundEvents.DONKEY_CHEST, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
    }

    @Override
    public SoundEvent getSaddleSoundEvent() {
        return super.getSaddleSoundEvent();
    }

    @Override
    protected float getRiddenSpeed(Player controllingPlayer) {
        return this.isSitting() ? 0 : super.getRiddenSpeed(controllingPlayer);
    }

    // Move Set and Behavior ===========================================================================================
    @Override
    protected void executeRidersJump(float strength, Vec3 movementInput) {
        if(this.isSitting()) {
            this.setSitting(false);
        }
        else if(this.chargeTimeout <= 0) {
            this.setCharging(true);
            this.chargeTimeout = maxChargeCooldown();
        }
    }


    @Override
    public void handleStartJump(int height) {
        if(!this.isSitting()) {
            this.playSound(SoundEvents.CAMEL_DASH, 1.0f, 1.0f);
            this.setCharging(true);
        }
        else {
            this.setSitting(false);
        }
    }

    public void tryBonding(Player player) {
        if(random.nextDouble() <= 0.1d || player.hasInfiniteMaterials()) {
            this.tameBeast(player);
            this.level().broadcastEntityEvent(this, EntityEvent.TAMING_SUCCEEDED);

            this.chargeTimeout = 0;
        }
        else {
            this.level().broadcastEntityEvent(this, EntityEvent.TAMING_FAILED);
        }
    }

    protected void tameBeast(Player player) {
        if (player instanceof ServerPlayer) {
            this.setOwnerUUID(player.getUUID());
            this.setTamed(true);
            CriteriaTriggers.TAME_ANIMAL.trigger((ServerPlayer)player, this);
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        boolean bl = !this.isBaby() && this.isTamed() && player.isSecondaryUseActive();

        ItemStack itemStack = player.getItemInHand(hand);

        if(isBondingItem(player.getItemInHand(hand)) && !this.isTamed() && this.isTamable()) {
            if(!this.level().isClientSide()) {
                this.tryBonding(player);
                this.usePlayerItem(player, hand, itemStack);
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide());
        }

        if(this.isTamed() && this.isTamable()) {
            if(isCommandItem(itemStack) && player == getOwner()) {
                this.setSitting(!isSitting());
                return InteractionResult.SUCCESS;
            }

            if (itemStack.is(Items.CHEST) && !this.hasChest()) {
                this.addChest(player, itemStack);
                return InteractionResult.SUCCESS;
            }

            if(!(isCommandItem(itemStack) || isFood(itemStack) || itemStack.is(Items.CHEST)) && this.isMountable()) {
                super.mobInteract(player, hand);
            }
        }

        return InteractionResult.FAIL;
    }

    @Override
    public InteractionResult fedFood(Player player, ItemStack stack) {
        return super.fedFood(player, stack);
    }

    public boolean isBondingItem(ItemStack itemStack) {
        return false;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if(!source.equals(damageSources().drown()) && !source.equals(damageSources().lava())
                && !source.equals(damageSources().cramming()) && !source.equals(damageSources().magic())) {
            amount *= (1 - RESISTANCE);
        }
        return super.hurt(source, amount);
    }

    public void chargeAttack() {
    }

    // Tick Management =================================================================================================
    @Override
    public void tick() {
        super.tick();

        if(this.getTarget() != null) {
            this.getLookControl().setLookAt(this.getTarget());
        }

        if(this.isCharging()) {
            chargeAttack();
            if(!chargeAnimationState.isStarted()) {
                this.chargeAnimationState.start(this.tickCount);
            }
        }
        if(this.chargeTimeout <= (maxChargeCooldown() - chargeDuration()) || !isCharging()) {
            this.setCharging(false);
            this.targetDir = Vec3.ZERO;
        }
        if(!this.isCharging()) {
            this.chargeAnimationState.stop();
        }
        if(chargeTimeout > 0) {
            --this.chargeTimeout;
        }

        if(this.hasControllingPassenger() && !this.shouldAttackWhenMounted()) {
            this.setLastHurtByMob(null);
            this.setLastHurtByPlayer(null);
            this.setTarget(null);
        }

        if (this.level().isClientSide) {
            setupAnimationStates();
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.attackTicksLeft > 0) {
            --this.attackTicksLeft;
        }
    }

    // =================================================================================================================

    protected void setOffspringAttribute(AgeableMob other, AbstractHorse child, Holder<Attribute> attribute, double min, double max) {
        double d = this.createOffspringAttribute(this.getAttributeBaseValue(attribute), other.getAttributeBaseValue(attribute), min, max, this.random);
        child.getAttribute(attribute).setBaseValue(d);
    }

    static double createOffspringAttribute(double parentBase, double otherParentBase, double min, double max, RandomSource random) {
        double g;
        if (max <= min) {
            throw new IllegalArgumentException("Incorrect range for an attribute");
        }
        parentBase = Mth.clamp(parentBase, min, max);
        otherParentBase = Mth.clamp(otherParentBase, min, max);
        double d = 0.15 * (max - min);
        double f = (parentBase + otherParentBase) / 2.0;
        double e = Math.abs(parentBase - otherParentBase) + d * 2.0;
        double h = f + e * (g = (random.nextDouble() + random.nextDouble() + random.nextDouble()) / 3.0 - 0.5);
        if (h > max) {
            double i = h - max;
            return max - i;
        }
        if (h < min) {
            double i = min - h;
            return min + i;
        }
        return h;
    }

    @Override
    public void handleEntityEvent(byte status) {
        if (status == EntityEvent.START_ATTACKING) {
            this.attackTicksLeft = ATTACK_COOLDOWN;
            this.attackAnimationState.start(this.tickCount);
        }
        if (status == EntityEvent.TAMING_SUCCEEDED) {
            this.spawnTamingParticles(true);
        } else if (status == EntityEvent.TAMING_FAILED) {
            this.spawnTamingParticles(false);
        } else {
            super.handleEntityEvent(status);
        }
    }

    public Player getPlayerByUuid(UUID uuid) {
        for (int i = 0; i < this.level().players().size(); ++i) {
            Player playerEntity = this.level().players().get(i);
            if (!uuid.equals(playerEntity.getUUID())) continue;
            return playerEntity;
        }
        return null;
    }

    @Override
    protected void updateWalkAnimation(float posDelta) {
        float f = this.getPose() == Pose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.walkAnimation.update(f, 0.2f);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.WARDEN_STEP, 0.15F, 2.0F);
    }

    @Override
    public boolean alwaysAccepts() {
        return super.alwaysAccepts();
    }
}
