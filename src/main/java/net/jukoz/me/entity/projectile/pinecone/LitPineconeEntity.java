package net.jukoz.me.entity.projectile.pinecone;

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

public class LitPineconeEntity extends AbstractProjectileEntity {
    private float damage;


    public LitPineconeEntity(EntityType<? extends LitPineconeEntity> entityType, Level world) {
        super(entityType, world);
    }

    public LitPineconeEntity(Level world, LivingEntity owner, float dmg) {
        super(ModEntities.LIT_PINECONE, owner, world);
        this.damage = dmg;
    }

    public LitPineconeEntity(Level world, double x, double y, double z) {
        super(ModEntities.LIT_PINECONE, x, y, z, world);
    }

    @Override
    public void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        if(this.getOwner() instanceof ShireHobbitEntity && entity instanceof ShireHobbitEntity) return;
        entity.igniteForSeconds(4);
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), this.damage);
    }

    protected Item getDefaultItem() {
        return ModResourceItems.LIT_PINECONE;
    }

}
