package net.jukoz.me.block.special.fire_of_orthanc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.jukoz.me.block.ModDecorativeBlocks;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;

public class FireOfOrthancEntityRenderer extends EntityRenderer<FireOfOrthancEntity> {
    private final BlockRenderDispatcher blockRenderManager;

    public FireOfOrthancEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.shadowRadius = 0.5F;
        this.blockRenderManager = ctx.getBlockRenderDispatcher();
    }

    public void render(FireOfOrthancEntity fireOfOrthancEntity, float yaw, float tickDelta, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int light) {
        BlockState blockState = ModDecorativeBlocks.FIRE_OF_ORTHANC.defaultBlockState();
        if (blockState.getRenderShape() != RenderShape.MODEL) {
            return;
        }
        Level world = fireOfOrthancEntity.level();
        if (blockState == world.getBlockState(fireOfOrthancEntity.blockPosition()) || blockState.getRenderShape() == RenderShape.INVISIBLE) {
            return;
        }
        matrixStack.pushPose();
        matrixStack.mulPose(Axis.YP.rotationDegrees(-90.0F));

        float scale = ((float) fireOfOrthancEntity.tickCount / 36) + 1.0f;
        scale = Math.min(1.2f, scale);
        matrixStack.scale(scale, scale, scale);

        BlockPos blockPos = BlockPos.containing(fireOfOrthancEntity.getX(), fireOfOrthancEntity.getBoundingBox().maxY, fireOfOrthancEntity.getZ());
        matrixStack.translate(-0.5, 0.0, -0.5);

        this.blockRenderManager.getModelRenderer().tesselateBlock(world, this.blockRenderManager.getBlockModel(blockState), blockState,
                blockPos, matrixStack, vertexConsumerProvider.getBuffer(ItemBlockRenderTypes.getMovingBlockRenderType(blockState)), false,
                RandomSource.create(), 0, OverlayTexture.NO_OVERLAY);
        matrixStack.popPose();
        super.render(fireOfOrthancEntity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light);
    }

    @Override
    public ResourceLocation getTextureLocation(FireOfOrthancEntity entity) {
        return ResourceLocation.fromNamespaceAndPath("minecraft", "textures/block/stone.png"); //new Identifier(MiddleEarth.MOD_ID, "textures/block/fire_of_orthanc_main.png");
    }
}
