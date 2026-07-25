package net.jukoz.me.entity.snail;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.entity.model.ModEntityModelLayers;
import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import java.util.Map;

public class SnailRenderer extends MobRenderer<SnailEntity, SnailModel> {

    private static final String PATH = "textures/entities/snails/";

    public SnailRenderer(EntityRendererProvider.Context context) {
        this(context, 0.2F, ModEntityModelLayers.SNAIL);
    }

    protected SnailRenderer(EntityRendererProvider.Context ctx, float shadowRadius, ModelLayerLocation layer) {
        super(ctx, new SnailModel(ctx.bakeLayer(layer)), shadowRadius);
    }

    public static final Map<SnailVariant, String> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(SnailVariant.class), (map) -> {
                map.put(SnailVariant.GREEN,
                        PATH + "snail_green.png");
                map.put(SnailVariant.PALE_GREEN,
                        PATH + "snail_pale_green.png");
                map.put(SnailVariant.BROWN,
                        PATH + "snail_brown.png");
                map.put(SnailVariant.GRAY,
                        PATH + "snail_gray.png");

            });

    @Override
    public ResourceLocation getTextureLocation(SnailEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, LOCATION_BY_VARIANT.get(entity.getVariant()));
    }

    @Override
    public void render(SnailEntity mobEntity, float f, float g, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i) {
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
