package net.jukoz.me.entity.projectile.pinecone;

import net.jukoz.me.entity.ModEntities;
import net.jukoz.me.entity.projectile.AbstractProjectileEntity;
import net.jukoz.me.item.ModResourceItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class PineconeEntity extends AbstractProjectileEntity {
    private float damage;

    public PineconeEntity(EntityType<? extends PineconeEntity> entityType, Level world) {
        super(entityType, world);
    }

    public PineconeEntity(Level world, LivingEntity owner, float dmg) {
        super(ModEntities.PINECONE, owner, world);
        this.damage = dmg;
    }

    public PineconeEntity(Level world, double x, double y, double z) {
        super(ModEntities.PINECONE, x, y, z, world);
    }

    protected Item getDefaultItem() {
        return ModResourceItems.PINECONE;
    }

    @Override
    public void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
    }
}
