package net.jukoz.me.block.special.fire_of_orthanc;

import net.jukoz.me.block.ModDecorativeBlocks;
import net.jukoz.me.entity.ModEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.TraceableEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class FireOfOrthancEntity extends Entity implements TraceableEntity {
    private static final EntityDataAccessor<Integer> FUSE;
    private static final EntityDataAccessor<BlockState> BLOCK_STATE;
    private static final int DEFAULT_FUSE = 16;
    public static final float EXPLOSION_FORCE = 12.31f;
    protected boolean chainReaction = false;
    @Nullable
    private LivingEntity causingEntity;

    public FireOfOrthancEntity(EntityType<?> type, Level world) {
        super(type, world);
    }

    public FireOfOrthancEntity(Level world, double x, double y, double z, @Nullable LivingEntity igniter, boolean instant) {
        this(ModEntities.FIRE_OF_ORTHANC, world);
        this.setPos(x, y, z);
        double d = world.random.nextDouble() * 6.2831854820251465;
        this.setDeltaMovement(-Math.sin(d) * 0.02, 0.20000000298023224, -Math.cos(d) * 0.02);

        if(instant) {
            this.setFuse(6);
            chainReaction = true;
        }
        else this.setFuse(DEFAULT_FUSE);

        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.causingEntity = igniter;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(FUSE, DEFAULT_FUSE);
        builder.define(BLOCK_STATE, ModDecorativeBlocks.FIRE_OF_ORTHANC.defaultBlockState());
    }

    public void explode() {
        this.level().explode(this, this.getX(), this.getY(0.0625), this.getZ(), EXPLOSION_FORCE, Level.ExplosionInteraction.TNT);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {
        this.setFuse(nbt.getShort("fuse"));
        if (nbt.contains("block_state", 10)) {
            this.setBlockState(NbtUtils.readBlockState(this.level().holderLookup(Registries.BLOCK), nbt.getCompound("block_state")));
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {
        nbt.putShort("fuse", (short)this.getFuse());
        nbt.put("block_state", NbtUtils.writeBlockState(this.getBlockState()));
    }

    protected double getDefaultGravity() {
        return 0.04f;
    }

    public void tick() {
        this.applyGravity();
        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.98));
        if (this.onGround()) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.7, -0.5, 0.7));
        }

        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            this.discard();
            if (!this.level().isClientSide) {
                this.explode();
            }
        } else {
            this.updateInWaterStateAndDoFluidPushing();
            if (this.level().isClientSide && !chainReaction) {
                for(int j = 0; j < 4; j++) {
                    this.level().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.8f, this.getZ(),
                            (Math.random() - 0.5f) * 0.3f, 0.4f, (Math.random() - 0.5f) * 0.5f);
                }
            }
        }
    }

    @Nullable
    @Override
    public Entity getOwner() {
        return causingEntity;
    }

    public void setFuse(int fuse) {
        this.entityData.set(FUSE, fuse);
    }

    public int getFuse() {
        return this.entityData.get(FUSE);
    }

    public void setBlockState(BlockState state) {
        this.entityData.set(BLOCK_STATE, state);
    }

    public BlockState getBlockState() {
        return this.entityData.get(BLOCK_STATE);
    }

    static {
        FUSE = SynchedEntityData.defineId(FireOfOrthancEntity.class, EntityDataSerializers.INT);
        BLOCK_STATE = SynchedEntityData.defineId(FireOfOrthancEntity.class, EntityDataSerializers.BLOCK_STATE);
    }
}
