package net.jukoz.me.entity.beasts.warg;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jukoz.me.entity.deer.DeerAnimations;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import org.joml.Vector3f;

public class WargModel extends HierarchicalModel<WargEntity> {
    private final ModelPart warg;
    private final ModelPart head;
    private final ModelPart mane;
    public WargModel(ModelPart root) {
        this.warg = root.getChild("root");
        this.head = warg.getChild(PartNames.BODY).getChild("upper_body").getChild(PartNames.HEAD);
        this.mane = warg.getChild(PartNames.BODY).getChild("upper_body").getChild("mane");
    }
    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 6.0F, -2.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition body = root.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create(), PartPose.offset(-2.6142F, 2.1138F, 1.5F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(48, 28).addBox(-11.0F, -4.0F, -1.5F, 13.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.3858F, -4.1138F, -0.5F, 0.0F, 0.0F, -1.5708F));

        PartDefinition upper_body = body.addOrReplaceChild("upper_body", CubeListBuilder.create(), PartPose.offset(-0.3858F, -1.1138F, -1.5F));

        PartDefinition body_fur = upper_body.addOrReplaceChild("body_fur", CubeListBuilder.create().texOffs(1, 19).addBox(-6.5F, -5.5F, -5.0F, 13.0F, 13.0F, 10.0F, new CubeDeformation(-0.3F)), PartPose.offset(7.5F, -0.25F, 1.0F));

        PartDefinition main_body = upper_body.addOrReplaceChild("main_body", CubeListBuilder.create().texOffs(0, 0).addBox(-13.5F, -5.0F, -4.0F, 27.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.2F, 0.0F, 1.0F));

        PartDefinition mane = upper_body.addOrReplaceChild("mane", CubeListBuilder.create().texOffs(6, 73).addBox(-9.5F, -3.0F, 0.0F, 17.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(5.5F, -8.0F, 1.0F));

        PartDefinition right_front_leg = upper_body.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(46, 50).addBox(-2.0F, 1.0F, -1.5F, 4.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(10.0F, 4.0F, -1.5F));

        PartDefinition left_front_leg = upper_body.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(32, 50).addBox(-2.0F, 1.0F, -1.0F, 4.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(10.0F, 4.0F, 3.0F));

        PartDefinition head = upper_body.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create().texOffs(39, 35).addBox(-1.3858F, -4.1138F, -5.0F, 10.0F, 6.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(60, 59).addBox(7.6142F, 2.8862F, -2.0F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.05F))
                .texOffs(37, 18).addBox(-2.0F, 1.25F, -4.0F, 10.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.3858F, -1.8862F, 1.5F, 0.0F, 0.0F, 0.2618F));

        PartDefinition snout = head.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(60, 51).addBox(-9.5F, -0.5F, -1.5F, 15.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(8.1142F, -1.6138F, -0.5F));

        PartDefinition ear_right = head.addOrReplaceChild("ear_right", CubeListBuilder.create(), PartPose.offset(4.1142F, -2.1138F, -3.978F));

        PartDefinition cube_r1 = ear_right.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 19).addBox(0.2802F, -4.8619F, -1.032F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.0F, -0.022F, 0.0F, 0.0F, -1.0036F));

        PartDefinition ear_left = head.addOrReplaceChild("ear_left", CubeListBuilder.create(), PartPose.offset(4.1142F, -2.1138F, 3.022F));

        PartDefinition cube_r2 = ear_left.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 1).addBox(0.2802F, -4.8619F, 0.01F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.0F, 0.0F, 0.0F, 0.0F, -1.0036F));

        PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(0, 44).addBox(-5.5F, -0.25F, -1.5F, 15.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 66).addBox(-5.5F, -1.25F, -1.5F, 15.0F, 1.0F, 3.0F, new CubeDeformation(-0.05F)), PartPose.offset(4.1142F, 3.1362F, -0.5F));

        PartDefinition tongue = jaw.addOrReplaceChild("tongue", CubeListBuilder.create().texOffs(4, 85).addBox(-1.5F, 0.1F, -1.5F, 9.0F, -0.1F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -0.25F, 0.0F));

        PartDefinition left_back_leg = body.addOrReplaceChild("left_back_leg", CubeListBuilder.create().texOffs(0, 50).addBox(-2.7F, 1.0F, -1.5F, 5.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.8858F, 2.8862F, 2.0F));

        PartDefinition right_back_leg = body.addOrReplaceChild("right_back_leg", CubeListBuilder.create().texOffs(16, 50).addBox(-2.7F, 1.0F, -1.5F, 5.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.8858F, 2.8862F, -3.0F));
        return LayerDefinition.create(modelData, 128, 128);
    }

    @Override
    public void setupAnim(WargEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        if(!entity.hasControllingPassenger()) {
            this.setHeadAngles(headYaw, headPitch);
        }

        if((entity.hasControllingPassenger() && entity.getControllingPassenger().isSprinting()) || entity.isRunning()) {
            this.animateWalk(WargAnimations.RUN, limbAngle, limbDistance, 1.2f, 1.2f);
        }
        else {
            this.animateWalk(WargAnimations.WALK, limbAngle, limbDistance, 1.5f, 1.5f);
        }

        this.mane.visible = !(entity.isSaddled() || entity.isWearingBodyArmor());

        this.animate(entity.idleAnimationState, WargAnimations.GROOM, animationProgress, 1f);
        this.animate(entity.attackAnimationState, WargAnimations.BITE, animationProgress, 1f);
        this.animate(entity.startSittingAnimationState, WargAnimations.SIT_DOWN, animationProgress, 3f);
        this.animate(entity.stopSittingAnimationState, WargAnimations.STAND_UP, animationProgress, 3f);
        this.animate(entity.sittingAnimationState, WargAnimations.SIT, animationProgress, 1f);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30.0F, 30.0F);
        headPitch = Mth.clamp(headPitch, -25.0F, 40.0F);

        this.head.yRot = headYaw * 0.017453292F;
        this.head.xRot = headPitch * 0.017453292F;
    }

    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        this.warg.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart root() {
        return this.warg;
    }
}