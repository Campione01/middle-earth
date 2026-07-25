package net.jukoz.me.entity.humans.dale;

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

public class DaleHumanRenderer extends HumanoidMobRenderer<DaleHumanEntity, DaleHumanModel<DaleHumanEntity>> {
    private static final String PATH = "textures/entities/humans/dale/";

    public DaleHumanRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new DaleHumanModel<>(ctx.bakeLayer(ModEntityModelLayers.HUMAN)), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, new DaleHumanModel<>(ctx.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new DaleHumanModel<>(ctx.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), ctx.getModelManager()));

    }

    @Override
    public ResourceLocation getTextureLocation(DaleHumanEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, LOCATION_BY_VARIANT.get(entity.getVariant()));
    }

    public static final Map<DaleHumanVariant, String> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(DaleHumanVariant.class), (resourceLocation) -> {
                resourceLocation.put(DaleHumanVariant.LIGHT_BROWN_RED,
                        PATH + "dale1.png");
                resourceLocation.put(DaleHumanVariant.PALE_BLUE_YELLOW,
                        PATH + "dale2.png");
                resourceLocation.put(DaleHumanVariant.PALE_GREY_ORANGE,
                        PATH + "dale3.png");
            });

    @Override
    public void render(DaleHumanEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {

        poseStack.scale(0.95f, 0.95f, 0.95f);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
