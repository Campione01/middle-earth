package net.jukoz.me.entity.uruks.isengard;

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

public class IsengardUrukHaiRenderer extends HumanoidMobRenderer<IsengardUrukHaiEntity, IsengardUrukHaiModel<IsengardUrukHaiEntity>> {
    private static final String PATH = "textures/entities/uruks/isengard/";

    public IsengardUrukHaiRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new IsengardUrukHaiModel<>(ctx.bakeLayer(ModEntityModelLayers.URUK)), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, new IsengardUrukHaiModel(ctx.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new IsengardUrukHaiModel(ctx.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), ctx.getModelManager()));
    }

    @Override
    public ResourceLocation getTextureLocation(IsengardUrukHaiEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, LOCATION_BY_VARIANT.get(entity.getVariant()));
    }

    public static final Map<IsengardUrukHaiVariant, String> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(IsengardUrukHaiVariant.class), (resourceLocation) -> {
                resourceLocation.put(IsengardUrukHaiVariant.LIGHT_BROWN_RED,
                        PATH + "uruk1.png");
                resourceLocation.put(IsengardUrukHaiVariant.PALE_BLUE_YELLOW,
                        PATH + "uruk2.png");
                resourceLocation.put(IsengardUrukHaiVariant.PALE_GREY_ORANGE,
                        PATH + "uruk3.png");
            });

    @Override
    public void render(IsengardUrukHaiEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {

        poseStack.scale(0.92f, 0.92f, 0.92f);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
