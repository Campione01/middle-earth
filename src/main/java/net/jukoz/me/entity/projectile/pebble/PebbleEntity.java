package net.jukoz.me.entity.projectile.pebble;

import net.jukoz.me.entity.ModEntities;
import net.jukoz.me.entity.hobbits.shire.ShireHobbitEntity;
import net.jukoz.me.entity.projectile.AbstractProjectileEntity;
import net.jukoz.me.item.ModResourceItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class PebbleEntity extends AbstractProjectileEntity {
    private float damage;

    public PebbleEntity(EntityType<? extends PebbleEntity> entityType, Level world) {
        super(entityType, world);
    }

    public PebbleEntity(Level world, LivingEntity owner, float dmg) {
        super(ModEntities.PEBBLE, owner, world);
        this.damage = dmg;
    }

    protected Item getDefaultItem() {
        return ModResourceItems.PEBBLE;
    }

    @Override
    public void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        if(this.getOwner() instanceof ShireHobbitEntity && entity instanceof ShireHobbitEntity) return;
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), this.damage);
    }
}
