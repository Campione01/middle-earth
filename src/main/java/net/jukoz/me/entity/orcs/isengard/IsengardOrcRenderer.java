package net.jukoz.me.entity.orcs.isengard;

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

public class IsengardOrcRenderer extends HumanoidMobRenderer<IsengardOrcEntity, IsengardOrcModel<IsengardOrcEntity>> {
    private static final String PATH = "textures/entities/orcs/isengard/";

    public IsengardOrcRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new IsengardOrcModel<>(ctx.bakeLayer(ModEntityModelLayers.ORC)), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, new IsengardOrcModel(ctx.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new IsengardOrcModel(ctx.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), ctx.getModelManager()));

    }

    @Override
    public ResourceLocation getTextureLocation(IsengardOrcEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, LOCATION_BY_VARIANT.get(entity.getVariant()));
    }

    public static final Map<IsengardOrcVariant, String> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(IsengardOrcVariant.class), (resourceLocation) -> {
                resourceLocation.put(IsengardOrcVariant.LIGHT_BROWN_RED,
                        PATH + "orc1.png");
                resourceLocation.put(IsengardOrcVariant.PALE_BLUE_YELLOW,
                        PATH + "orc2.png");
                resourceLocation.put(IsengardOrcVariant.PALE_GREY_ORANGE,
                        PATH + "orc3.png");
            });

    @Override
    public void render(IsengardOrcEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {

        poseStack.scale(0.75f, 0.75f, 0.75f);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
