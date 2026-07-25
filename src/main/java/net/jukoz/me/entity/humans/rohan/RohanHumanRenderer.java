package net.jukoz.me.entity.humans.rohan;

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

public class RohanHumanRenderer extends HumanoidMobRenderer<RohanHumanEntity, RohanHumanModel<RohanHumanEntity>> {
    private static final String PATH = "textures/entities/humans/rohan/";

    public RohanHumanRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new RohanHumanModel<>(ctx.bakeLayer(ModEntityModelLayers.HUMAN)), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, new RohanHumanModel(ctx.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new RohanHumanModel(ctx.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), ctx.getModelManager()));

    }

    @Override
    public ResourceLocation getTextureLocation(RohanHumanEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, LOCATION_BY_VARIANT.get(entity.getVariant()));
    }

    public static final Map<RohanHumanVariant, String> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(RohanHumanVariant.class), (resourceLocation) -> {
                resourceLocation.put(RohanHumanVariant.LIGHT_BROWN_RED,
                        PATH + "rohirrim1.png");
                resourceLocation.put(RohanHumanVariant.PALE_BLUE_YELLOW,
                        PATH + "rohirrim2.png");
                resourceLocation.put(RohanHumanVariant.PALE_GREY_ORANGE,
                        PATH + "rohirrim3.png");
            });

    @Override
    public void render(RohanHumanEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {

        poseStack.scale(0.95f, 0.95f, 0.95f);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
