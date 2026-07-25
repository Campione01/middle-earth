package net.jukoz.me.compat.neoforge.api.client.rendering.v1;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public final class EntityRendererRegistry {
    private EntityRendererRegistry() {
    }

    public static <T extends Entity> void register(EntityType<? extends T> type, EntityRendererProvider<T> provider) {
        EntityRenderers.register(type, provider);
    }
}
