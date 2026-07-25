package net.jukoz.me.compat.neoforge.api.client.rendering.v1;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class EntityModelLayerRegistry {
    private static final Map<ModelLayerLocation, Supplier<LayerDefinition>> MODEL_LAYERS = new LinkedHashMap<>();

    private EntityModelLayerRegistry() {
    }

    public static void registerModelLayer(ModelLayerLocation layer, Supplier<LayerDefinition> provider) {
        MODEL_LAYERS.put(layer, provider);
    }

    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        MODEL_LAYERS.forEach(event::registerLayerDefinition);
    }
}
