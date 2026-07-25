package net.jukoz.me.entity.beasts.warg.features;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.entity.beasts.warg.WargEntity;
import net.jukoz.me.entity.beasts.warg.WargModel;
import net.jukoz.me.entity.model.ModEntityModelLayers;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class WargSaddleFeatureRenderer extends RenderLayer<WargEntity, WargModel> {
    private final WargSaddleModel model;

    public WargSaddleFeatureRenderer(RenderLayerParent<WargEntity, WargModel> context, EntityModelSet loader) {
        super(context);
        this.model = new WargSaddleModel(loader.bakeLayer(ModEntityModelLayers.WARG_SADDLE));
    }

    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, int light, WargEntity wargEntity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (!wargEntity.isSaddled()) {
            return;
        }

        this.model.setupAnim(wargEntity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);

        ((WargModel)this.getParentModel()).copyPropertiesTo(this.model);

        ResourceLocation saddleTexture = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "textures/entities/warg/feature/warg_saddle.png");

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderType.entityCutoutNoCull(saddleTexture));
        this.model.renderToBuffer(matrices, vertexConsumer, light, OverlayTexture.NO_OVERLAY, -1);
    }
}