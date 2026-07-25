package net.jukoz.me.entity.orcs.wild;

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

public class WildGoblinRenderer extends HumanoidMobRenderer<WildGoblinEntity, WildGoblinModel<WildGoblinEntity>> {
    private static final String PATH = "textures/entities/orcs/wild/";

    public WildGoblinRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new WildGoblinModel<>(ctx.bakeLayer(ModEntityModelLayers.ORC)), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, new WildGoblinModel(ctx.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new WildGoblinModel(ctx.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), ctx.getModelManager()));

    }

    @Override
    public ResourceLocation getTextureLocation(WildGoblinEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, LOCATION_BY_VARIANT.get(entity.getVariant()));
    }

    public static final Map<WildGoblinVariant, String> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(WildGoblinVariant.class), (resourceLocation) -> {
                resourceLocation.put(WildGoblinVariant.LIGHT_BROWN_RED,
                        PATH + "orc1.png");
                resourceLocation.put(WildGoblinVariant.PALE_BLUE_YELLOW,
                        PATH + "orc2.png");
                resourceLocation.put(WildGoblinVariant.PALE_GREY_ORANGE,
                        PATH + "orc3.png");
            });

    @Override
    public void render(WildGoblinEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {

        poseStack.scale(0.8f, 0.8f, 0.8f);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
