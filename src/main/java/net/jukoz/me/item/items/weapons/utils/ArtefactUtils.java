package net.jukoz.me.item.items.weapons.utils;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;


public class ArtefactUtils {
    public static <T extends Entity> boolean isInBound(Level world, Entity entity, Class<T> entityClass, int range){
        return !world.getEntitiesOfClass(entityClass, entity.getBoundingBox().inflate(range), Entity::isAlive).isEmpty();
    }
}
