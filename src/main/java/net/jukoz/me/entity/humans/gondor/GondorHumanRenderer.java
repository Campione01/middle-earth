package net.jukoz.me.entity.humans.gondor;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.entity.model.ModEntityModelLayers;
import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import java.util.Map;

public class GondorHumanRenderer extends HumanoidMobRenderer<GondorHumanEntity, GondorHumanModel<GondorHumanEntity>> {
    private static final String PATH = "textures/entities/humans/gondor/";

    public GondorHumanRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new GondorHumanModel<>(ctx.bakeLayer(ModEntityModelLayers.HUMAN)), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, new GondorHumanModel(ctx.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new GondorHumanModel(ctx.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), ctx.getModelManager()));

    }

    @Override
    public ResourceLocation getTextureLocation(GondorHumanEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, LOCATION_BY_VARIANT.get(entity.getVariant()));
    }

    public static final Map<GondorHumanVariant, String> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(GondorHumanVariant.class), (resourceLocation) -> {
                resourceLocation.put(GondorHumanVariant.LIGHT_BROWN_RED,
                        PATH + "gondor1.png");
                resourceLocation.put(GondorHumanVariant.PALE_BLUE_YELLOW,
                        PATH + "gondor2.png");
                resourceLocation.put(GondorHumanVariant.PALE_GREY_ORANGE,
                        PATH + "gondor3.png");
            });

    @Override
    public void render(GondorHumanEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {

        poseStack.scale(0.95f, 0.95f, 0.95f);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
