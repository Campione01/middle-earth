package net.jukoz.me.entity.beasts.broadhoof;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.entity.beasts.broadhoof.features.BroadhoofGoatArmorFeatureRenderer;
import net.jukoz.me.entity.beasts.broadhoof.features.BroadhoofGoatSaddleFeatureRenderer;
import net.jukoz.me.entity.beasts.warg.WargEntity;
import net.jukoz.me.entity.beasts.warg.WargModel;
import net.jukoz.me.entity.beasts.warg.WargVariant;
import net.jukoz.me.entity.beasts.warg.features.WargArmorFeatureRenderer;
import net.jukoz.me.entity.beasts.warg.features.WargEyesFeatureRenderer;
import net.jukoz.me.entity.beasts.warg.features.WargSaddleFeatureRenderer;
import net.jukoz.me.entity.model.ModEntityModelLayers;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import java.util.Map;

public class BroadhoofGoatRenderer extends MobRenderer<BroadhoofGoatEntity,BroadhoofGoatModel> {
    private static final String PATH = "textures/entities/broadhoof_goat/";
    private static final float SIZE = 1f;

    public BroadhoofGoatRenderer(EntityRendererProvider.Context context) {
        super(context, new BroadhoofGoatModel(context.bakeLayer(ModEntityModelLayers.BROADHOOF_GOAT)), 0.8f);
        this.addLayer(new BroadhoofGoatArmorFeatureRenderer(this, context.getModelSet()));
        this.addLayer(new BroadhoofGoatSaddleFeatureRenderer(this, context.getModelSet()));
    }

    @Override
    public void render(BroadhoofGoatEntity entity, float f, float g, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i) {
        if(entity.isBaby()) {
            matrixStack.scale(SIZE / 2, SIZE / 2, SIZE / 2);
        } else {
            matrixStack.scale(SIZE, SIZE, SIZE);
        }

        super.render(entity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public ResourceLocation getTextureLocation(BroadhoofGoatEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    public static final Map<BroadhoofGoatVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(BroadhoofGoatVariant.class), (map) -> {
                map.put(BroadhoofGoatVariant.GRAY,
                        ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "broadhoof_goat_gray.png"));
                map.put(BroadhoofGoatVariant.GRAY_BEARD,
                        ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "broadhoof_goat_gray_beard.png"));
                map.put(BroadhoofGoatVariant.GRAY_BEARD_YOUNG,
                        ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "broadhoof_goat_gray_beard_young.png"));
                map.put(BroadhoofGoatVariant.PATCHED,
                        ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "broadhoof_goat_patched.png"));
                map.put(BroadhoofGoatVariant.RED,
                        ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "broadhoof_goat_red.png"));
                map.put(BroadhoofGoatVariant.RED_WITH_PATCH,
                        ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "broadhoof_goat_red_with_patch.png"));
                map.put(BroadhoofGoatVariant.RED_WITH_SPOTS,
                        ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "broadhoof_goat_red_with_spots.png"));
                map.put(BroadhoofGoatVariant.WHITE,
                        ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "broadhoof_goat_white.png"));
                map.put(BroadhoofGoatVariant.BLACK,
                        ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "broadhoof_goat_black.png"));
                map.put(BroadhoofGoatVariant.BLACK_MASK,
                        ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "broadhoof_goat_black_mask.png"));
                map.put(BroadhoofGoatVariant.BROWN,
                        ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "broadhoof_goat_brown.png"));

            });
}
