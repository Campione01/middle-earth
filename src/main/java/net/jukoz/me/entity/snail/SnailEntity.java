package net.jukoz.me.entity.snail;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.entity.ModEntities;
import net.minecraft.world.level.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class SnailEntity extends Animal {
    public final AnimationState crawlingAnimationState = new AnimationState();
    public static final int CLIMBING_TIME_TRANSITION = 12;
    private static final EntityDataAccessor<Byte> SNAIL_FLAGS;
    private int climbingTicks = 0;
    int moreCropsTicks;

    public SnailEntity(EntityType<? extends Animal> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SnailEatCropGoal(this));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 0.8));
    }

    public static AttributeSupplier.Builder createSnailAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2)
                .add(Attributes.MOVEMENT_SPEED, 0.05f)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1)
                .add(Attributes.ARMOR, 0.5f);
    }

    public SnailVariant getVariant() {
        return SnailVariant.byId(this.getId());
    }
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
        return ModEntities.SNAIL.create(world);
    }


    static class SnailEatCropGoal
            extends MoveToBlockGoal {
        private final SnailEntity snail;
        private boolean wantsCrops;
        private boolean hasTarget;

        public SnailEatCropGoal(SnailEntity snail) {
            super(snail, 1.0f, 16);
            this.snail = snail;
        }

        @Override
        public boolean canUse() {
            if (this.nextStartTick <= 0) {
                if (!this.snail.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                    return false;
                }
                this.hasTarget = false;
                this.wantsCrops = this.snail.wantsCrops();
            }
            return super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            return this.hasTarget && super.canContinueToUse();
        }

        @Override
        public void tick() {
            super.tick();
            this.snail.getLookControl().setLookAt((double)this.blockPos.getX() + 0.5, this.blockPos.getY() + 1, (double)this.blockPos.getZ() + 0.5, 10.0f, this.snail.getMaxHeadXRot());
            if (this.isReachedTarget()) {
                Level world = this.snail.level();
                BlockPos blockPos = this.blockPos.above();
                BlockState blockState = world.getBlockState(blockPos);
                Block block = blockState.getBlock();
                if (this.hasTarget && block.defaultBlockState().is(BlockTags.CROPS)) {
                    world.setBlock(blockPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_CLIENTS);
                    world.destroyBlock(blockPos, true, this.snail);
                    this.snail.ate();
                    this.snail.moreCropsTicks = 1200;
                }
                this.hasTarget = false;
                this.nextStartTick = 10;
            }
        }

        @Override
        protected boolean isValidTarget(LevelReader world, BlockPos pos) {
            BlockState blockState = world.getBlockState(pos);
            if (blockState.is(Blocks.FARMLAND) && this.wantsCrops && !this.hasTarget && (blockState = world.getBlockState(pos.above())).is(BlockTags.CROPS)) {
                this.hasTarget = true;
                return true;
            }

            return false;
        }
    }

    boolean wantsCrops() {
        return this.moreCropsTicks <= 0;
    }

    @Override
    protected void customServerAiStep() {
        if (this.moreCropsTicks > 0) {
            this.moreCropsTicks -= this.random.nextInt(3);
            if (this.moreCropsTicks < 0) {
                this.moreCropsTicks = 0;
            }
        }
        super.customServerAiStep();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SLIME_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.SLIME_HURT;
    }

    @Override
    public void jumpFromGround() {
        // Snail is not able to jump
    }

    protected PathNavigation createNavigation(Level world) {
        return new WallClimberNavigation(this, world);
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(SNAIL_FLAGS, (byte)0);
    }

    @Override
    public void ate() {
        super.ate();
        if (this.isBaby()) {
            this.ageUp(60);
        }
        if(random.nextDouble() <= 0.15D) {
            Level world = this.level();
            SnailEntity snailSpawn = ((EntityType<SnailEntity>) EntityType.byString(MiddleEarth.MOD_ID + ":snail").get()).create(world);
            snailSpawn.absMoveTo(this.getX(), this.getY(), this.getZ());
            world.addFreshEntity(snailSpawn);
        }
    }

    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            this.setClimbingWall(this.horizontalCollision);
        }
        if(onClimbable()) {
            this.setDeltaMovement(0, 0.01, 0);
        }

        this.crawlingAnimationState.startIfStopped(this.tickCount);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if(isClimbingWall()) {

            this.climbingTicks = Math.min(CLIMBING_TIME_TRANSITION, this.climbingTicks + 1);
        } else {
            this.climbingTicks = Math.max(0, this.climbingTicks - 1);
        }
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }

    public boolean onClimbable() {
        return this.isClimbingWall();
    }

    public boolean isClimbingWall() {
        return (this.entityData.get(SNAIL_FLAGS) & 1) != 0;
    }

    public boolean isCollidingWall() {
        return this.horizontalCollision;
    }

    public void setClimbingWall(boolean climbing) {
        byte b = (Byte)this.entityData.get(SNAIL_FLAGS);
        if (climbing) {
            b = (byte)(b | 1);
        } else {
            b &= -2;
        }

        this.entityData.set(SNAIL_FLAGS, b);
    }

    public int getClimbingTicks() {
        return this.climbingTicks;
    }

    static {
        SNAIL_FLAGS = SynchedEntityData.defineId(SnailEntity.class, EntityDataSerializers.BYTE);
    }
}
