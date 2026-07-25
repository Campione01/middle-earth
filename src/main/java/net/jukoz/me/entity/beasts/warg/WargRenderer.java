package net.jukoz.me.entity.beasts.warg;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.entity.beasts.warg.features.*;
import net.jukoz.me.entity.model.ModEntityModelLayers;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import java.util.Map;

public class WargRenderer extends MobRenderer<WargEntity, WargModel> {
    private static final String PATH = "textures/entities/warg/";
    private static final float SIZE = 1f;

    public WargRenderer(EntityRendererProvider.Context context) {
        super(context, new WargModel(context.bakeLayer(ModEntityModelLayers.WARG)), 0.8f);
        this.addLayer(new WargEyesFeatureRenderer(this));
        this.addLayer(new WargArmorFeatureRenderer(this, context.getModelSet()));
        this.addLayer(new WargArmorSpineFeatureRenderer(this, context.getModelSet()));
        this.addLayer(new WargArmorSideSkullsFeatureRenderer(this, context.getModelSet()));
        this.addLayer(new WargSaddleFeatureRenderer(this, context.getModelSet()));
        this.addLayer(new WargArmorFrontSkullFeatureRenderer(this, context.getModelSet()));
        this.addLayer(new WargArmorBackSkullFeatureRenderer(this, context.getModelSet()));
    }

    @Override
    public void render(WargEntity entity, float f, float g, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i) {
        if(entity.isBaby()) {
            matrixStack.scale(SIZE / 2, SIZE / 2, SIZE / 2);
        } else {
            matrixStack.scale(SIZE, SIZE, SIZE);
        }

        super.render(entity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public static final Map<WargVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(WargVariant.class), (map) -> {
                map.put(WargVariant.BROWN,
                        ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "warg_brown.png"));
                map.put(WargVariant.BLACK,
                        ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "warg_black.png"));
                map.put(WargVariant.GRAY,
                        ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "warg_gray.png"));
                map.put(WargVariant.LIGHT_GRAY,
                        ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "warg_light_gray.png"));
                map.put(WargVariant.GRAY_FACE,
                        ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "warg_gray_face.png"));
                map.put(WargVariant.RED_BALD,
                        ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "warg_red_bald.png"));
                map.put(WargVariant.TAN,
                        ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "warg_tan.png"));
                map.put(WargVariant.TAN_GRAY,
                        ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "warg_tan_gray.png"));
            });

    @Override
    public ResourceLocation getTextureLocation(WargEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }
}
