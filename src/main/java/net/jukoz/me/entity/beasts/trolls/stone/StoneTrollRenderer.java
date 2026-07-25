package net.jukoz.me.entity.beasts.trolls.stone;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.entity.model.ModEntityModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class StoneTrollRenderer extends MobRenderer<StoneTrollEntity, StoneTrollModel> {
    private static final String PATH = "textures/entities/trolls/stone/";

    public StoneTrollRenderer(EntityRendererProvider.Context context) {
        super(context, new StoneTrollModel(context.bakeLayer(ModEntityModelLayers.STONE_TROLL)), 1.1f);
    }

    @Override
    public ResourceLocation getTextureLocation(StoneTrollEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "stone_troll1.png");
    }

    public void render(StoneTrollEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {

        poseStack.scale(1, 1, 1);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
