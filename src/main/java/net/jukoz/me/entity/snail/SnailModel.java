package net.jukoz.me.entity.snail;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jukoz.me.entity.deer.DeerAnimations;
import net.jukoz.me.entity.spider.MirkwoodSpiderEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class SnailModel extends HierarchicalModel<SnailEntity> {
	private final ModelPart snail;
	public SnailModel(ModelPart root) {
		this.snail = root.getChild("snail");
	}
	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition snail = modelPartData.addOrReplaceChild("snail", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 10).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 5.0F, 5.0F, new CubeDeformation(0.4F)), PartPose.offset(0.0F, 20.0F, 0.0F));

		PartDefinition body = snail.addOrReplaceChild("body", CubeListBuilder.create().texOffs(12, 22).addBox(-1.0F, -2.0F, -4.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, -1).addBox(-1.0F, -4.0F, -4.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, -1).mirror().addBox(1.0F, -4.0F, -4.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 4.0F, 0.0F));
		return LayerDefinition.create(modelData, 32, 32);
	}



	@Override
	public void renderToBuffer(PoseStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		this.snail.render(matrices, vertices, light, overlay, color);
	}

	@Override
	public ModelPart root() {
		return snail;
	}

	@Override
	public void setupAnim(SnailEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		float percentage = (float) entity.getClimbingTicks() / SnailEntity.CLIMBING_TIME_TRANSITION;

		snail.xRot = -1.6f * percentage;

		this.animate(entity.crawlingAnimationState, SnailAnimations.CRAWL, animationProgress, (float)entity.getDeltaMovement().length() * 2);
	}
}