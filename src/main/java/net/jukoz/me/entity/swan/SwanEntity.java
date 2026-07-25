package net.jukoz.me.entity.swan;

import net.jukoz.me.entity.ModEntities;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class SwanEntity extends Animal {
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(SwanEntity.class, EntityDataSerializers.INT);
    public static final Ingredient BREEDING_INGREDIENT = Ingredient.of(ItemTags.CHICKEN_FOOD);
    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    private int idleAnimationTimeout = this.random.nextInt(100) + 950;


    public SwanEntity(EntityType<? extends Animal> entityType, Level world) {
        super(entityType, world);
        this.setCanPickUpLoot(true);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));

        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.5f, false));
        this.goalSelector.addGoal(3, new PanicGoal(this, 1.5f));
        this.goalSelector.addGoal(4, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(5, new TemptGoal(this, 1.1, BREEDING_INGREDIENT, false));

        this.goalSelector.addGoal(6, new FollowParentGoal(this, 1.05));

        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(9, new RandomStrollGoal(this, 1.0F));

        this.goalSelector.addGoal(10, new AvoidEntityGoal<>(this, Wolf.class, 8.0F, 0.9, 1.2));
    }


    public static AttributeSupplier.Builder createSwanAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_SPEED, 1.0);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VARIANT, 0);
    }

    /* Slooshes brainstuff for maybe future references
    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanPathThroughDoors(true);
        birdNavigation.setCanSwim(true);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }
    */

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

    private void setupAnimationStates() {
        if(this.isAggressive()) {
            this.idleAnimationTimeout = this.random.nextInt(100) + 950;
        }
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(100) + 950;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
        if (this.isInWater()) {
            this.swimAnimationState.startIfStopped(this.tickCount);
        }
        else {
            this.swimAnimationState.stop();
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.flapWings();
        this.updateFloating();
    }

    private void updateFloating() {
        if (this.isInWater()) {
            CollisionContext shapeContext = CollisionContext.of(this);
            if (!shapeContext.isAbove(LiquidBlock.STABLE_SHAPE, this.blockPosition(), true) || this.level().getFluidState(this.blockPosition().above()).is(FluidTags.WATER)) {
                this.setDeltaMovement(this.getDeltaMovement().scale(0.5).add(0.0, 0.05, 0.0));
            } else {
                this.setOnGround(true);
            }
        }
    }

    @Override
    public boolean canStandOnFluid(FluidState state) {
        return state.is(FluidTags.WATER);
    }

    private void flapWings() {
        Vec3 vec3d = this.getDeltaMovement();
        if (!this.onGround() && vec3d.y < 0.0) {
            this.setDeltaMovement(vec3d.multiply(1.0, 0.6, 1.0));
        }
    }

    protected void onFlap() {
        this.playSound(SoundEvents.PARROT_FLY, 0.15F, 1.0F);
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        this.level().broadcastEntityEvent(this, EntityEvent.START_ATTACKING);
        return super.doHurtTarget(target);
    }

    @Override
    public void handleEntityEvent(byte status) {
        if(status == EntityEvent.START_ATTACKING) {
            this.attackAnimationState.start(this.tickCount);
        }
        super.handleEntityEvent(status);

    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.CHICKEN_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.CHICKEN_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.CHICKEN_DEATH;
    }
    protected SoundEvent getSwimSound() {
        return SoundEvents.TURTLE_SWIM;
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 2.0F);
    }

    @Nullable
    public SwanEntity getBreedOffspring(ServerLevel serverWorld, AgeableMob passiveEntity) {
        SwanEntity child = ModEntities.SWAN.create(serverWorld);
        SwanVariant variant = Util.getRandom(SwanVariant.values(), this.random);

        child.setVariant(variant);

        return child;
    }

    public boolean isFood(ItemStack stack) {
        return BREEDING_INGREDIENT.test(stack);
    }

    public boolean isPushedByFluid() {
        return true;
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType spawnReason,
                                 @Nullable SpawnGroupData entityData) {
        SwanVariant variant = Util.getRandom(SwanVariant.values(), this.random);
        setVariant(variant);
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData);
    }

    public SwanVariant getVariant() {
        return SwanVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.entityData.get(VARIANT);
    }

    private void setVariant(SwanVariant variant) {
        this.entityData.set(VARIANT, variant.getId() & 255);
    }
}
