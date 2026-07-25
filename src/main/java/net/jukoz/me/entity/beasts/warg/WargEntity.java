package net.jukoz.me.entity.beasts.warg;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.entity.ModEntities;
import net.jukoz.me.entity.beasts.AbstractBeastEntity;
import net.jukoz.me.entity.beasts.broadhoof.BroadhoofGoatEntity;
import net.jukoz.me.entity.beasts.broadhoof.BroadhoofGoatHorns;
import net.jukoz.me.entity.beasts.broadhoof.BroadhoofGoatVariant;
import net.jukoz.me.entity.deer.DeerEntity;
import net.jukoz.me.entity.dwarves.longbeards.LongbeardDwarfEntity;
import net.jukoz.me.entity.elves.galadhrim.GaladhrimElfEntity;
import net.jukoz.me.entity.goals.*;
import net.jukoz.me.entity.hobbits.shire.ShireHobbitEntity;
import net.jukoz.me.entity.humans.bandit.BanditHumanEntity;
import net.jukoz.me.entity.humans.gondor.GondorHumanEntity;
import net.jukoz.me.entity.humans.rohan.RohanHumanEntity;
import net.jukoz.me.entity.pheasant.PheasantEntity;
import net.jukoz.me.item.ModEquipmentItems;
import net.jukoz.me.resources.StateSaverAndLoader;
import net.jukoz.me.resources.datas.Disposition;
import net.jukoz.me.resources.datas.RaceType;
import net.jukoz.me.resources.datas.races.RaceUtil;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.DoubleSupplier;
import java.util.function.IntUnaryOperator;

public class WargEntity extends AbstractBeastEntity {

    private static final float MIN_MOVEMENT_SPEED_BONUS = (float) WargEntity.generateSpeed(() -> 0.0);
    private static final float MAX_MOVEMENT_SPEED_BONUS = (float)WargEntity.generateSpeed(() -> 1.0);
    private static final float MIN_ATTACK_DAMAGE_BONUS = (float)WargEntity.getChildAttackDamageBonus(() -> 0.0);
    private static final float MAX_ATTACK_DAMAGE_BONUS = (float)WargEntity.getChildAttackDamageBonus(() -> 1.0);
    private static final float MIN_HEALTH_BONUS = WargEntity.generateMaxHealth(max -> 0);
    private static final float MAX_HEALTH_BONUS = WargEntity.generateMaxHealth(max -> max - 1);
    private static final Ingredient TEMPTING_INGREDIENT = Ingredient.of(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "warg_food")));
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(WargEntity.class, EntityDataSerializers.INT);
    public int idleAnimationTimeout = this.random.nextInt(600) + 1700;
    private static final EntityDimensions BABY_BASE_DIMENSIONS = ModEntities.WARG.getDimensions().scale(0.5f);

    public WargEntity(EntityType<? extends WargEntity> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.MAX_HEALTH, 24.0d)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.2d)
                .add(Attributes.ATTACK_SPEED, 1.0d)
                .add(Attributes.FOLLOW_RANGE, 38.0d)
                .add(Attributes.ATTACK_DAMAGE, 4.0d)
                .add(Attributes.STEP_HEIGHT, 1.15d)
                .add(Attributes.SAFE_FALL_DISTANCE, 6.0d);
    }

    protected void randomizeReinforcementsChance(RandomSource random) {
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(this.generateMaxHealth(random::nextInt));
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.generateSpeed(random::nextDouble));
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(this.getChildAttackDamageBonus(random::nextDouble));
    }

    @Override
    public boolean canUseSlot(EquipmentSlot slot) {
        return true;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new BeastSitGoal(this));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 2, false));
        this.goalSelector.addGoal(4, new ChargeAttackGoal(this, this.getDisposition(), maxChargeCooldown()));
        this.goalSelector.addGoal(5, new BreedGoal(this, 1.5));
        this.goalSelector.addGoal(6, new TemptGoal(this, 1.0, TEMPTING_INGREDIENT, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(3, new BeastRevengeGoal(this, new Class[0]).setGroupRevenge());
        this.targetSelector.addGoal(4, new BeastTargetPlayerGoal(this, this.getDisposition()));
        this.targetSelector.addGoal(5, new BeastActiveTargetGoal<>(this, GaladhrimElfEntity.class, true));
        this.targetSelector.addGoal(6, new BeastActiveTargetGoal<>(this, LongbeardDwarfEntity.class, true));
        this.targetSelector.addGoal(7, new BeastActiveTargetGoal<>(this, GondorHumanEntity.class, true));
        this.targetSelector.addGoal(8, new BeastActiveTargetGoal<>(this, RohanHumanEntity.class, true));
        this.targetSelector.addGoal(9, new BeastActiveTargetGoal<>(this, BanditHumanEntity.class, true));
        this.targetSelector.addGoal(10, new BeastActiveTargetGoal<>(this, ShireHobbitEntity.class, true));
        this.targetSelector.addGoal(11, new BeastActiveTargetGoal<>(this, Sheep.class, true));
        this.targetSelector.addGoal(12, new BeastActiveTargetGoal<>(this, Goat.class, true));
        this.targetSelector.addGoal(13, new BeastActiveTargetGoal<>(this, DeerEntity.class, true));
        this.targetSelector.addGoal(14, new BeastActiveTargetGoal<>(this, PheasantEntity.class, true));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VARIANT, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("Variant", this.getTypeVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.entityData.set(VARIANT, nbt.getInt("Variant"));
    }

    protected static float generateMaxHealth(IntUnaryOperator randomIntGetter) {
        return 12.0f + (float)randomIntGetter.applyAsInt(6) + (float)randomIntGetter.applyAsInt(6);
    }

    protected static double getChildAttackDamageBonus(DoubleSupplier randomDoubleGetter) {
        return (double)4f + randomDoubleGetter.getAsDouble() + randomDoubleGetter.getAsDouble() + randomDoubleGetter.getAsDouble();
    }

    protected static double generateSpeed(DoubleSupplier randomDoubleGetter) {
        return ((double)0.5 + randomDoubleGetter.getAsDouble() * 0.25 + randomDoubleGetter.getAsDouble() * 0.25 + randomDoubleGetter.getAsDouble() * 0.25) * 0.3;
    }

    @Override
    public void tick() {
        super.tick();

        if(this.isCharging()) {
            if(!chargeAnimationState.isStarted()) {
                this.chargeAnimationState.start(this.tickCount);
            }

            if(this.chargeTimeout <= maxChargeCooldown() - 10 && !this.isJumping()) {
                this.setCharging(false);
                this.setHasCharged(false);
            }
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if(!this.level().isClientSide() && !player.isCreative()) {
            RaceType playerRace = RaceUtil.getRaceType(player);

            if(playerRace == RaceType.NONE || (this.getRaceType() != null && !this.getRaceType().contains(playerRace))) {
                return InteractionResult.FAIL;
            }
        }

        if(this.isTamed()) {
            if (this.isFood(itemStack)) {
                if(this.getHealth() < this.getMaxHealth()) {
                    itemStack.consume(1, player);
                    FoodProperties foodComponent = itemStack.get(DataComponents.FOOD);
                    float f = foodComponent != null ? (float)foodComponent.nutrition() : 1.0f;
                    this.heal(2.0f * f);
                    return InteractionResult.sidedSuccess(this.level().isClientSide());
                }
                else if (!this.level().isClientSide && this.getAge() == 0 && this.canFallInLove()) {
                    this.usePlayerItem(player, hand, itemStack);
                    this.setInLove(player);
                    return InteractionResult.SUCCESS;
                }
            }
        }

        return super.mobInteract(player, hand);
    }

    @Override
    @Nullable
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
        WargEntity wargEntity = (WargEntity) entity;
        WargEntity wargEntity2 = ModEntities.WARG.create(world);
        if (wargEntity2 != null) {
            int i = this.random.nextInt(9);
            WargVariant wargVariant = i < 4 ? this.getVariant() : (i < 8 ? wargEntity.getVariant() : Util.getRandom(WargVariant.values(), this.random));
            wargEntity2.setVariant(wargVariant);
            this.setOffspringAttributes(entity, wargEntity2);
        }
        return wargEntity2;
    }

    @Override
    protected void setOffspringAttributes(AgeableMob other, AbstractHorse child) {
        this.setOffspringAttribute(other, child, Attributes.MAX_HEALTH, MIN_HEALTH_BONUS, MAX_HEALTH_BONUS);
        this.setOffspringAttribute(other, child, Attributes.ATTACK_DAMAGE, MIN_ATTACK_DAMAGE_BONUS, MAX_ATTACK_DAMAGE_BONUS);
        this.setOffspringAttribute(other, child, Attributes.MOVEMENT_SPEED, MIN_MOVEMENT_SPEED_BONUS, MAX_MOVEMENT_SPEED_BONUS);
    }

    @Override
    protected Disposition getDisposition() {
        return Disposition.EVIL;
    }

    @Override
    protected List<RaceType> getRaceType() {
        return List.of(RaceType.ORC, RaceType.URUK);
    }

    @Override
    public EntityDimensions getDefaultDimensions(Pose pose) {
        return this.isBaby() ? BABY_BASE_DIMENSIONS : super.getDefaultDimensions(pose);
    }

    @Override
    public boolean canMate(Animal other) {
        return other instanceof WargEntity;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "warg_food")));
    }

    @Override
    protected float getRiddenSpeed(Player controllingPlayer) {
        if(!this.isSitting()) {
            return controllingPlayer.isSprinting() ? ((float)this.getAttributeValue(Attributes.MOVEMENT_SPEED)) : ((float)this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.5f);
        }

        return super.getRiddenSpeed(controllingPlayer);
    }

    @Override
    public boolean canSprint() {
        return true;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(Entity passenger, EntityDimensions dimensions, float scaleFactor) {
        float f = this.walkAnimation.speed();
        float g = this.walkAnimation.position() * (Mth.PI / 180) * 18;
        float h = passenger.isSprinting() ? 1 : 0;

        double y = (Mth.cos(g * 2 * 1.2f - (Mth.PI * (h - 1))) * (0.06 + (0.035 * h)));

        return super.getPassengerAttachmentPoint(passenger, dimensions, scaleFactor).add(0, y,0);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if(this.isAggressive() || this.getLastHurtByMob() != null) {
            if(!this.isTamed() || this.isTamed() && this.getLastHurtByMob() != null) {
                this.setSitting(false);
            }

            if(!this.hasControllingPassenger()) {
                this.setRunning(true);
            }

            this.idleAnimationTimeout = this.random.nextInt(600) + 1700;
        }

        if(!this.isAggressive() && this.getLastHurtByMob() == null) {
            this.setRunning(false);
        }

        if(this.isSitting()) {
            this.getNavigation().stop();
        }
    }

    @Override
    protected void setupAnimationStates() {
        if(this.isSitting()) {
            this.startSittingAnimationState.startIfStopped(this.tickCount);
        }
        if(!this.isSitting() && this.startSittingAnimationState.isStarted()) {
            this.startSittingAnimationState.stop();
            this.stopSittingAnimationState.start(this.tickCount);
        }
    }

    public boolean isCommandItem(ItemStack stack) {
        return stack.is(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bones")));
    }

    @Override
    public void chargeAttack() {
        if(!this.hasCharged()) {
            this.setHasCharged(true);
            if(!this.isTamed() && !this.level().isClientSide) {
                if(targetDir == Vec3.ZERO && this.getTarget() != null) {
                    targetDir = new Vec3( this.getTarget().blockPosition().getX() - this.blockPosition().getX(),
                            this.getTarget().blockPosition().getY() - this.blockPosition().getY(),
                            this.getTarget().blockPosition().getZ() - this.blockPosition().getZ());
                }
                this.setDeltaMovement(targetDir.multiply(1,0,1).normalize().add(0,0.6,0).scale(0.7f));
            }
            else if (this.level().isClientSide) {
                this.setHasCharged(true);
                this.setDeltaMovement(this.getLookAngle().multiply(1,0,1).normalize().add(0,0.35,0).scale(1.3f));
            }
        }
        if(!this.isTamed() && !this.level().isClientSide) {
            this.setYRot((float) Math.toDegrees(Math.atan2(-targetDir.x, targetDir.z)));
        }

        List<Entity> entities = this.level().getEntities(this, this.getBoundingBox().inflate(0.2f, 0.0, 0.2f));

        for(Entity entity : entities) {
            if(entity.getUUID() != this.getOwnerUUID() && entity != this && !this.getPassengers().contains(entity) && !((entity instanceof WargEntity) && !this.isTamed())) {
                entity.hurt(entity.damageSources().mobAttack(this), this.getAttackDamage());

                if(entity instanceof ServerPlayer) {
                    entity.stopRiding();
                }
                else if(!this.level().isClientSide) {
                    entity.removeVehicle();
                    entity.ejectPassengers();
                }

                this.setCharging(false);
                this.setHasCharged(false);
            }
        }
    }

    @Override
    public boolean shouldAttackWhenMounted() {
        return true;
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        int i;
        if (fallDistance > 1.0f) {
            this.playSound(SoundEvents.WOLF_STEP, 2.5f, 0.7f);
        }
        if ((i = this.calculateFallDamage(fallDistance, damageMultiplier)) <= 0) {
            return false;
        }
        this.hurt(damageSource, i);
        if (this.isVehicle()) {
            for (Entity entity : this.getIndirectPassengers()) {
                entity.hurt(damageSource, i);
            }
        }
        this.playBlockFallSound();
        return true;
    }

    @Override
    public boolean isBodyArmorItem(ItemStack stack) {
        return stack.is(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "warg_armor")));
    }

    public boolean hasCharged() {
        return hasCharged;
    }

    public void setHasCharged(boolean hasCharged) {
        this.hasCharged = hasCharged;
    }

    @Override
    public boolean isBondingItem(ItemStack itemStack) {
        return itemStack.is(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "warg_food")));
    }

    @Override
    public int chargeDuration() {
        return 50;
    }

    @Override
    public int maxChargeCooldown() {
        return 200;
    }

    @Override
    public boolean canCarryChest() {
        return false;
    }

    /* VARIANTS */
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType spawnReason,
                                 @Nullable SpawnGroupData entityData) {
        WargVariant variant = Util.getRandom(WargVariant.values(), this.random);
        setVariant(variant);
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData);
    }

    public WargVariant getVariant() {
        return WargVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.entityData.get(VARIANT);
    }

    private void setVariant(WargVariant variant) {
        this.entityData.set(VARIANT, variant.getId() & 255);
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WOLF_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.WOLF_HURT;
    }
    @Override
    protected void playHurtSound(DamageSource damageSource) {
        this.playSound(this.getHurtSound(damageSource), 1.0f, 0.7f);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.WOLF_AMBIENT;
    }

    @Override
    public void playAmbientSound() {
        this.playSound(this.getAmbientSound(), 1.0f, 0.7f);
    }

    @Nullable
    @Override
    public SoundEvent getAmbientStandSound() {
        return SoundEvents.WOLF_HOWL;
    }

    @Nullable
    @Override
    protected SoundEvent getAngrySound() {
        return SoundEvents.WOLF_GROWL;
    }

    @Override
    public void makeMad() {
        this.playSound(this.getAngrySound(), 1.0f, 0.7f);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.WOLF_STEP, 0.15f, 0.7f);
    }

    @Override
    protected void playGallopSound(SoundType group) {
        this.playSound(SoundEvents.WOLF_STEP, 1.0f, 0.7f);
    }

    @Override
    protected void playJumpSound() {
        this.playSound(SoundEvents.WOLF_STEP, 1.1f, 0.7f);
    }
}
