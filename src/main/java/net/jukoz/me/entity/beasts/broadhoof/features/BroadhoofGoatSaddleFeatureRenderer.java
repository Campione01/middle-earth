package net.jukoz.me.entity.beasts.broadhoof.features;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.entity.beasts.broadhoof.BroadhoofGoatEntity;
import net.jukoz.me.entity.beasts.broadhoof.BroadhoofGoatModel;
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

public class BroadhoofGoatSaddleFeatureRenderer extends RenderLayer<BroadhoofGoatEntity, BroadhoofGoatModel> {
    private final BroadhoofGoatSaddleModel model;

    public BroadhoofGoatSaddleFeatureRenderer(RenderLayerParent<BroadhoofGoatEntity, BroadhoofGoatModel> context, EntityModelSet loader) {
        super(context);
        this.model = new BroadhoofGoatSaddleModel(loader.bakeLayer(ModEntityModelLayers.BROADHOOF_GOAT_SADDLE));
    }

    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, int light, BroadhoofGoatEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (!entity.isSaddled()) {
            return;
        }

        this.model.setupAnim(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);

        ((BroadhoofGoatModel)this.getParentModel()).copyPropertiesTo(this.model);

        ResourceLocation saddleTexture = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "textures/entities/broadhoof_goat/feature/broadhoof_goat_saddle.png");

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderType.entityCutoutNoCull(saddleTexture));
        this.model.renderToBuffer(matrices, vertexConsumer, light, OverlayTexture.NO_OVERLAY, -1);
    }
}
