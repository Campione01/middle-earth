package net.jukoz.me.entity.projectile;

import net.jukoz.me.entity.hobbits.shire.ShireHobbitEntity;
import net.jukoz.me.item.ModResourceItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public abstract class AbstractProjectileEntity extends ThrowableItemProjectile {
    private float damage;

    public AbstractProjectileEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level world) {
        super(entityType, world);
    }

    public AbstractProjectileEntity(EntityType<? extends ThrowableItemProjectile> entityType, double d, double e, double f, Level world) {
        super(entityType, d, e, f, world);
    }

    public AbstractProjectileEntity(EntityType<? extends ThrowableItemProjectile> entityType, LivingEntity livingEntity, Level world) {
        super(entityType, livingEntity, world);
    }

    public void handleEntityEvent(byte status) {
        if (status == 3) {
            for(int i = 0; i < 8; ++i) {
                this.level().addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.getItem()), this.getX(), this.getY(), this.getZ(), ((double)this.random.nextFloat() - 0.5) * 0.08, ((double)this.random.nextFloat() - 0.5) * 0.08, ((double)this.random.nextFloat() - 0.5) * 0.08);
            }
        }
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        if(this.getOwner() instanceof ShireHobbitEntity && entity instanceof ShireHobbitEntity) return;
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), this.damage);
    }

    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!level().isClientSide) {
            level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }
}
