package net.jukoz.me.entity.uruks.mordor;

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

public class MordorBlackUrukRenderer extends HumanoidMobRenderer<MordorBlackUrukEntity, MordorBlackUrukModel<MordorBlackUrukEntity>> {
    private static final String PATH = "textures/entities/uruks/mordor/";

    public MordorBlackUrukRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new MordorBlackUrukModel<>(ctx.bakeLayer(ModEntityModelLayers.URUK)), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, new MordorBlackUrukModel(ctx.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new MordorBlackUrukModel(ctx.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), ctx.getModelManager()));
    }

    @Override
    public ResourceLocation getTextureLocation(MordorBlackUrukEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, LOCATION_BY_VARIANT.get(entity.getVariant()));
    }

    public static final Map<MordorBlackUrukVariant, String> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MordorBlackUrukVariant.class), (resourceLocation) -> {
                resourceLocation.put(MordorBlackUrukVariant.LIGHT_BROWN_RED,
                        PATH + "uruk1.png");
                resourceLocation.put(MordorBlackUrukVariant.PALE_BLUE_YELLOW,
                        PATH + "uruk2.png");
                resourceLocation.put(MordorBlackUrukVariant.PALE_GREY_ORANGE,
                        PATH + "uruk3.png");
            });

    @Override
    public void render(MordorBlackUrukEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {

        poseStack.scale(0.92f, 0.92f, 0.92f);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
