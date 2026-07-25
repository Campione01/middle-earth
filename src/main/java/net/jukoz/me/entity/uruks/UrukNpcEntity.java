package net.jukoz.me.entity.uruks;

import net.jukoz.me.entity.NpcEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class UrukNpcEntity extends NpcEntity {
    protected UrukNpcEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        int index = 4;
        index = initEvilTargetSelector(index);
    }
}
