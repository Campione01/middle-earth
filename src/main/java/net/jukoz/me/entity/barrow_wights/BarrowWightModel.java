package net.jukoz.me.entity.barrow_wights;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jukoz.me.compat.neoforge.dist.EnvType;
import net.jukoz.me.compat.neoforge.dist.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

@Environment(value = EnvType.CLIENT)
public class BarrowWightModel extends HierarchicalModel<BarrowWightEntity> {

    private final ModelPart wight;
    private final ModelPart head;
    private final ModelPart bottomJaw;
    private final ModelPart body;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private static final float ROTATION_SPEED = 0.6f;
    private static float RAD = (float)Math.PI/180;

    public BarrowWightModel(ModelPart root) {
        this.wight = root.getChild("wight");
        this.body = wight.getChild("body");
        this.head = wight.getChild("head");
        this.bottomJaw = head.getChild("bottomJaw");
        this.leftArm = wight.getChild("leftArm");
        this.rightArm = wight.getChild("rightArm");
        this.leftLeg = wight.getChild("leftLeg");
        this.rightLeg = wight.getChild("rightLeg");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();

        PartDefinition wight = modelPartData.addOrReplaceChild("wight", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition head = wight.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 52).addBox(-4.0F, -4.35F, -10.1324F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.1F)).texOffs(69, 7).addBox(-3.5F, -4.6F, -9.6324F, 7.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(47, 0).addBox(-4.0F, 1.8943F, -10.1206F, 8.0F, 1.0F, 6.0F, new CubeDeformation(0.1F)).texOffs(0, 28).addBox(-4.0F, -4.4443F, -10.5F, 8.0F, 10.0F, 10.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, -30.0557F, -1.0F));

        PartDefinition bottomJaw = head.addOrReplaceChild("bottomJaw", CubeListBuilder.create().texOffs(62, 44).addBox(-3.99F, -1.6614F, -5.9F, 7.98F, 2.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(72, 37).addBox(-3.99F, 0.3386F, -6.0F, 7.98F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.01F, 2.0557F, -4.3F, 0.2182F, 0.0F, 0.0F));

        PartDefinition body = wight.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 18).addBox(-10.0F, -6.9088F, -11.0419F, 20.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(36, 28).addBox(-5.0F, 4.6614F, -5.0419F, 10.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -26.6614F, 6.1294F));

        PartDefinition shoulderCloak_r1 = body.addOrReplaceChild("shoulderCloak_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-9.91F, -20.0F, -4.5F, 20.0F, 11.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.6614F, -3.1294F, 0.1745F, 0.0F, 0.0F));

        PartDefinition torsoCloakRight_r1 = body.addOrReplaceChild("torsoCloakRight_r1", CubeListBuilder.create().texOffs(56, 39).addBox(-1.0F, -7.3386F, -1.2919F, 0.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 6.0F, -1.0F, 0.4326F, -0.1125F, 0.2461F));

        PartDefinition legCloak_r1 = body.addOrReplaceChild("legCloak_r1", CubeListBuilder.create().texOffs(30, 44).addBox(-5.001F, -14.3386F, 2.7081F, 10.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 27.0F, -4.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition waistCloakFront_r1 = body.addOrReplaceChild("waistCloakFront_r1", CubeListBuilder.create().texOffs(0, 48).addBox(0.0F, 3.6614F, -3.0419F, 10.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 6.0F, -1.0F, -0.3054F, 0.0F, 0.0F));

        PartDefinition waistCloakBack_r1 = body.addOrReplaceChild("waistCloakBack_r1", CubeListBuilder.create().texOffs(64, 27).addBox(0.0F, 3.9114F, 2.9581F, 10.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 6.0F, -1.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition waistCloakRight_r1 = body.addOrReplaceChild("waistCloakRight_r1", CubeListBuilder.create().texOffs(26, 20).addBox(0.75F, 2.6614F, -4.0419F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 6.0F, -1.0F, -0.0066F, 0.0065F, 0.3008F));

        PartDefinition waistCloakLeft_r1 = body.addOrReplaceChild("waistCloakLeft_r1", CubeListBuilder.create().texOffs(54, 14).addBox(-0.75F, 2.6614F, -4.0419F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 6.0F, -1.0F, -0.0066F, -0.0065F, -0.3008F));

        PartDefinition torsoCloakLeft_r1 = body.addOrReplaceChild("torsoCloakLeft_r1", CubeListBuilder.create().texOffs(0, 23).addBox(1.0F, -7.3386F, -1.2919F, 0.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 6.0F, -1.0F, 0.4326F, 0.1125F, -0.2461F));

        PartDefinition backCloakBot_r1 = body.addOrReplaceChild("backCloakBot_r1", CubeListBuilder.create().texOffs(64, 31).addBox(-4.0F, -1.1345F, -0.4734F, 8.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.6109F, 0.0F, 0.0F));

        PartDefinition backCloakTop_r1 = body.addOrReplaceChild("backCloakTop_r1", CubeListBuilder.create().texOffs(69, 0).addBox(-4.0F, 0.2274F, -0.0767F, 8.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.3386F, -2.1294F, 0.7418F, 0.0F, 0.0F));

        PartDefinition torso_r1 = body.addOrReplaceChild("torso_r1", CubeListBuilder.create().texOffs(57, 55).addBox(-4.0F, -6.0F, -2.5F, 8.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0067F, -2.5449F, 0.3491F, 0.0F, 0.0F));

        PartDefinition leftArm = wight.addOrReplaceChild("leftArm", CubeListBuilder.create(), PartPose.offsetAndRotation(8.0F, -31.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r1 = leftArm.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(32, 60).addBox(-1.5F, -13.0F, -13.1401F, 3.0F, 26.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.494F, 12.0F, 11.1401F, 0.0F, 0.0436F, 0.0F));

        PartDefinition rightArm = wight.addOrReplaceChild("rightArm", CubeListBuilder.create(), PartPose.offsetAndRotation(-8.0F, -31.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r2 = rightArm.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(44, 60).addBox(-1.5F, -13.0F, -13.5F, 3.0F, 26.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, 11.5F, 0.0F, 0.0F, 0.0F));

        PartDefinition leftLeg = wight.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(0, 66).addBox(-2.2296F, -1.0567F, -1.4252F, 3.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -13.0F, 6.0F));

        PartDefinition rightLeg = wight.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(12, 66).addBox(-1.7296F, -2.0567F, -1.4252F, 3.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -12.0F, 6.0F));

        return LayerDefinition.create(modelData, 128, 128);
    }

    @Override
    public void setupAnim(BarrowWightEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if(!entity.level().isClientSide) return;
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.head.xRot = headPitch * RAD;
        this.head.yRot = netHeadYaw * RAD;


        this.bottomJaw.xRot = 0.25F * Math.max(0, Mth.cos(ageInTicks * 0.1f));

        float k = 0.8f * limbSwingAmount;
        this.rightLeg.xRot = Mth.cos(limbSwing * ROTATION_SPEED) * k;
        this.leftLeg.xRot = Mth.cos(limbSwing * ROTATION_SPEED + (float)Math.PI) * k;


        float kA = 0.3f * limbSwingAmount;
        this.rightArm.xRot = -Mth.cos(limbSwing * ROTATION_SPEED) * kA;
        this.leftArm.xRot = Mth.cos(limbSwing * ROTATION_SPEED) * kA;

        this.rightArm.yRot = 0;
        this.leftArm.yRot = 0;

        int screaming = entity.getScreamingActionTime();
        if(screaming >= 0) {
            float currentPercent = 1f - ((float) screaming / BarrowWightEntity.SCREAM_ACTION_TIME);

            float firstStepPercent = 1f;
            float secondStepPercent = 0.2f;

            float currentStepPercent = (currentPercent < firstStepPercent)? firstStepPercent : secondStepPercent ;
            float currentPercentInStep = 1 / currentStepPercent * currentPercent;

            if(currentStepPercent == firstStepPercent){
                // Head
                float headPitchGoal = -getRadSin(45);
                this.head.xRot = ((headPitchGoal - this.head.xRot) * currentStepPercent) * currentPercentInStep;

                // Jaw
                float jawPitchGoal = getRadSin(80);
                this.bottomJaw.xRot = ((jawPitchGoal - this.bottomJaw.xRot) * currentStepPercent) * currentPercentInStep;

                // Right Arm
                float rightArmPitchGoal = -getRadSin(90);
                this.rightArm.xRot = ((rightArmPitchGoal - 0) * currentStepPercent) * currentPercentInStep;

                float rightArmYawGoal = getRadSin(60);
                this.rightArm.yRot = ((rightArmYawGoal - 0) * currentStepPercent) * currentPercentInStep;

                // Left Arm
                float leftArmPitchGoal = -getRadSin(90);
                this.leftArm.xRot = ((leftArmPitchGoal - 0) * currentStepPercent) * currentPercentInStep;

                float leftArmYawGoal = -getRadSin(60);
                this.leftArm.yRot = ((leftArmYawGoal - 0) * currentStepPercent) * currentPercentInStep;
            }
        }
    }

    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        wight.render(matrices, vertices, light, overlay, color);
    }

    @Override
    public ModelPart root() {
        return this.wight;
    }

    private float getRadSin(float degree){
        return Mth.sin(degree * RAD);
    }
}
