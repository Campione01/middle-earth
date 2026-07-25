package net.jukoz.me.entity.orcs;

import net.jukoz.me.entity.NpcEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class OrcNpcEntity extends NpcEntity {
    protected OrcNpcEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
        super(entityType, world);
    }
}
