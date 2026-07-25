package net.jukoz.me.entity.humans.bandit;

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

public class BanditHumanRenderer extends HumanoidMobRenderer<BanditHumanEntity, BanditHumanModel<BanditHumanEntity>> {
    private static final String PATH = "textures/entities/humans/bandits/";

    public BanditHumanRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new BanditHumanModel<>(ctx.bakeLayer(ModEntityModelLayers.HUMAN)), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, new BanditHumanModel(ctx.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new BanditHumanModel(ctx.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), ctx.getModelManager()));

    }

    @Override
    public ResourceLocation getTextureLocation(BanditHumanEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, LOCATION_BY_VARIANT.get(entity.getVariant()));
    }

    public static final Map<BanditHumanVariant, String> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(BanditHumanVariant.class), (resourceLocation) -> {
                resourceLocation.put(BanditHumanVariant.LIGHT_BROWN_RED,
                        PATH + "bandit1.png");
                resourceLocation.put(BanditHumanVariant.PALE_BLUE_YELLOW,
                        PATH + "bandit2.png");
                resourceLocation.put(BanditHumanVariant.PALE_GREY_ORANGE,
                        PATH + "bandit3.png");
                resourceLocation.put(BanditHumanVariant.BALD_GREEN_BLUE,
                        PATH + "bandit4.png");
            });

    @Override
    public void render(BanditHumanEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {

        poseStack.scale(0.95f, 0.95f, 0.95f);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
