package net.jukoz.me.entity.spider;

import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.ArrayList;
import java.util.List;

public class MirkwoodSpiderModel extends EntityModel<MirkwoodSpiderEntity> {
    private static final float LEGS_MARGIN_ANGLE = 0.18f;
    private static final float LEGS_MARGIN_ANGLE_MULTIPLIER = 2f;
    private static final float UPPER_LEGS_ROLL = 0.6f;
    private static final float BOTTOM_LEGS_ROLL = -1.3f;
    private final ModelPart body;
    private final ModelPart leg_right;
    private final ModelPart leg1;
    private final ModelPart bottom_leg1;
    private final ModelPart leg2;
    private final ModelPart bottom_leg2;
    private final ModelPart leg3;
    private final ModelPart bottom_leg3;
    private final ModelPart leg4;
    private final ModelPart bottom_leg4;
    private final ModelPart leg_left;
    private final ModelPart leg5;
    private final ModelPart bottom_leg5;
    private final ModelPart leg6;
    private final ModelPart bottom_leg6;
    private final ModelPart leg7;
    private final ModelPart bottom_leg7;
    private final ModelPart leg8;
    private final ModelPart bottom_leg8;
    private final ModelPart thorax;
    private final ModelPart abdomen;
    private final ModelPart face;
    private final ModelPart fang1;
    private final ModelPart cube_r1;
    private final ModelPart fang2;
    private final ModelPart cube_r2;
    private final ModelPart eyes;

    private List<ModelPart> evenLegs = new ArrayList<>();
    private List<ModelPart> oddLegs = new ArrayList<>();

    private List<ModelPart> evenBottomLegs = new ArrayList<>();
    private List<ModelPart> oddBottomLegs = new ArrayList<>();
    private List<ModelPart> legs = new ArrayList<>();

    public MirkwoodSpiderModel(ModelPart root) {
        this.body = root.getChild("body");
        this.leg_right = this.body.getChild("leg_right");
        this.leg1 = leg_right.getChild("leg1");
        this.leg2 = leg_right.getChild("leg2");
        this.leg3 = leg_right.getChild("leg3");
        this.leg4 = leg_right.getChild("leg4");

        this.bottom_leg1 = leg1.getChild("bottom_leg1");
        this.bottom_leg2 = leg2.getChild("bottom_leg2");
        this.bottom_leg3 = leg3.getChild("bottom_leg3");
        this.bottom_leg4 = leg4.getChild("bottom_leg4");

        this.leg_left = this.body.getChild("leg_left");
        this.leg5 = leg_left.getChild("leg5");
        this.leg6 = leg_left.getChild("leg6");
        this.leg7 = leg_left.getChild("leg7");
        this.leg8 = leg_left.getChild("leg8");

        this.bottom_leg5 = leg5.getChild("bottom_leg5");
        this.bottom_leg6 = leg6.getChild("bottom_leg6");
        this.bottom_leg7 = leg7.getChild("bottom_leg7");
        this.bottom_leg8 = leg8.getChild("bottom_leg8");

        this.thorax = this.body.getChild("thorax");
        this.abdomen = thorax.getChild("abdomen");

        this.face = this.body.getChild("face");
        this.fang1 = face.getChild("fang1");
        this.fang2 = face.getChild("fang2");
        this.cube_r1 = fang1.getChild("cube_r1");
        this.cube_r2 = fang2.getChild("cube_r2");
        this.eyes = face.getChild("eyes");

        evenLegs.add(leg2);
        evenLegs.add(leg4);
        evenLegs.add(leg6);
        evenLegs.add(leg8);
        oddLegs.add(leg1);
        oddLegs.add(leg3);
        oddLegs.add(leg5);
        oddLegs.add(leg7);

        evenBottomLegs.add(bottom_leg2);
        evenBottomLegs.add(bottom_leg4);
        evenBottomLegs.add(bottom_leg6);
        evenBottomLegs.add(bottom_leg8);
        oddBottomLegs.add(bottom_leg1);
        oddBottomLegs.add(bottom_leg3);
        oddBottomLegs.add(bottom_leg5);
        oddBottomLegs.add(bottom_leg7);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition body = modelPartData.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, -5.5F));

        PartDefinition leg_right = body.addOrReplaceChild("leg_right", CubeListBuilder.create().texOffs(13, 45).addBox(-3.0F, -5.0F, -10.0F, 1.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 2.0F, 5.5F));

        PartDefinition leg1 = leg_right.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(33, 8).addBox(-11.8F, -3.0F, -1.0F, 13.0F, 2.0F, 2.0F, new CubeDeformation(-0.05F)), PartPose.offset(-3.2F, -1.5F, -0.2F));

        PartDefinition bottom_leg1 = leg1.addOrReplaceChild("bottom_leg1", CubeListBuilder.create().texOffs(22, 21).addBox(-17.5F, -1.0F, -1.0F, 18.0F, 2.0F, 2.0F, new CubeDeformation(-0.05F)), PartPose.offset(-11.4F, -2.0F, 0.0F));

        PartDefinition leg2 = leg_right.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(46, 16).addBox(-9.8F, -3.0F, -1.0F, 11.0F, 2.0F, 2.0F, new CubeDeformation(-0.05F)), PartPose.offset(-3.2F, -1.5F, -3.0F));

        PartDefinition bottom_leg2 = leg2.addOrReplaceChild("bottom_leg2", CubeListBuilder.create().texOffs(22, 25).addBox(-17.5F, -1.0F, -1.0F, 18.0F, 2.0F, 2.0F, new CubeDeformation(-0.05F)), PartPose.offset(-9.4F, -2.0F, 0.0F));

        PartDefinition leg3 = leg_right.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(46, 12).addBox(-9.8F, -3.0F, -1.0F, 11.0F, 2.0F, 2.0F, new CubeDeformation(-0.05F)), PartPose.offset(-3.2F, -1.5F, -5.8F));

        PartDefinition bottom_leg3 = leg3.addOrReplaceChild("bottom_leg3", CubeListBuilder.create().texOffs(22, 21).addBox(-17.5F, -1.0F, -1.0F, 18.0F, 2.0F, 2.0F, new CubeDeformation(-0.05F)), PartPose.offset(-9.4F, -2.0F, 0.0F));

        PartDefinition leg4 = leg_right.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(33, 4).addBox(-11.8F, -3.0F, -1.0F, 13.0F, 2.0F, 2.0F, new CubeDeformation(-0.05F)), PartPose.offset(-3.2F, -1.5F, -8.5F));

        PartDefinition bottom_leg4 = leg4.addOrReplaceChild("bottom_leg4", CubeListBuilder.create().texOffs(22, 25).addBox(-17.5F, -1.0F, -1.0F, 18.0F, 2.0F, 2.0F, new CubeDeformation(-0.05F)), PartPose.offset(-11.4F, -2.0F, 0.0F));

        PartDefinition leg_left = body.addOrReplaceChild("leg_left", CubeListBuilder.create().texOffs(0, 37).addBox(3.1F, -5.1F, -10.0F, 1.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.1F, 2.1F, -3.5F, 0.0F, 3.1416F, 0.0F));

        PartDefinition leg5 = leg_left.addOrReplaceChild("leg5", CubeListBuilder.create().texOffs(33, 4).addBox(-11.9F, -3.0F, -1.0F, 13.0F, 2.0F, 2.0F, new CubeDeformation(-0.05F)), PartPose.offset(3.0F, -1.6F, -0.2F));

        PartDefinition bottom_leg5 = leg5.addOrReplaceChild("bottom_leg5", CubeListBuilder.create().texOffs(22, 25).addBox(-17.4F, -1.0F, -1.0F, 18.0F, 2.0F, 2.0F, new CubeDeformation(-0.05F)), PartPose.offset(-11.6F, -2.0F, 0.0F));

        PartDefinition leg6 = leg_left.addOrReplaceChild("leg6", CubeListBuilder.create().texOffs(26, 45).addBox(-9.9F, -3.0F, -1.0F, 11.0F, 2.0F, 2.0F, new CubeDeformation(-0.05F)), PartPose.offset(3.0F, -1.6F, -3.0F));

        PartDefinition bottom_leg6 = leg6.addOrReplaceChild("bottom_leg6", CubeListBuilder.create().texOffs(22, 21).addBox(-17.4F, -1.0F, -1.0F, 18.0F, 2.0F, 2.0F, new CubeDeformation(-0.05F)), PartPose.offset(-9.6F, -2.0F, 0.0F));

        PartDefinition leg7 = leg_left.addOrReplaceChild("leg7", CubeListBuilder.create().texOffs(46, 16).addBox(-9.9F, -3.0F, -1.0F, 11.0F, 2.0F, 2.0F, new CubeDeformation(-0.05F)), PartPose.offset(3.0F, -1.6F, -5.8F));

        PartDefinition bottom_leg7 = leg7.addOrReplaceChild("bottom_leg7", CubeListBuilder.create().texOffs(22, 25).addBox(-17.4F, -1.0F, -1.0F, 18.0F, 2.0F, 2.0F, new CubeDeformation(-0.05F)), PartPose.offset(-9.6F, -2.0F, 0.0F));

        PartDefinition leg8 = leg_left.addOrReplaceChild("leg8", CubeListBuilder.create().texOffs(33, 0).addBox(-11.9F, -3.0F, -1.0F, 13.0F, 2.0F, 2.0F, new CubeDeformation(-0.05F)), PartPose.offset(3.0F, -1.6F, -8.5F));

        PartDefinition bottom_leg8 = leg8.addOrReplaceChild("bottom_leg8", CubeListBuilder.create().texOffs(22, 21).addBox(-17.4F, -1.0F, -1.0F, 18.0F, 2.0F, 2.0F, new CubeDeformation(-0.05F)), PartPose.offset(-11.6F, -2.0F, 0.0F));

        PartDefinition thorax = body.addOrReplaceChild("thorax", CubeListBuilder.create().texOffs(0, 21).addBox(-2.0F, -6.0F, -9.0F, 6.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 0.0F, 5.5F));

        PartDefinition abdomen = thorax.addOrReplaceChild("abdomen", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -6.0F, -0.5F, 10.0F, 8.0F, 13.0F, new CubeDeformation(0.5F)), PartPose.offset(1.0F, -2.0F, 1.5F));

        PartDefinition face = body.addOrReplaceChild("face", CubeListBuilder.create().texOffs(24, 29).addBox(-3.0F, -10.0F, -14.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.01F)), PartPose.offset(-1.0F, 2.0F, 3.5F));

        PartDefinition fang1 = face.addOrReplaceChild("fang1", CubeListBuilder.create().texOffs(0, 5).addBox(-0.75F, -0.5F, -6.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-1.5F, -3.75F, -10.0F, 0.0F, 0.0F, -0.2182F));

        PartDefinition cube_r1 = fang1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(8, 0).addBox(1.0F, -3.2961F, -5.5433F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-1.25F, 3.5F, 0.5F, 0.3927F, 0.0F, 0.0F));

        PartDefinition fang2 = face.addOrReplaceChild("fang2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.25F, -0.5F, -5.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(3.5F, -3.75F, -11.0F, 0.0F, 0.0F, 0.2182F));

        PartDefinition cube_r2 = fang2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(7, 4).addBox(1.0F, -3.2961F, -5.5433F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-1.75F, 3.5F, 1.5F, 0.3927F, 0.0F, 0.0F));

        PartDefinition eyes = face.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(6, 21).addBox(1.25F, -5.75F, -12.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(7, 11).addBox(-0.5F, -7.3F, -12.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.3F))
                .texOffs(4, 10).addBox(-2.25F, -7.25F, -10.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 10).addBox(-0.25F, -5.75F, -12.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(6, 25).addBox(-2.25F, -6.0F, -12.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(7, 9).addBox(3.25F, -7.25F, -10.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 29).addBox(3.25F, -6.0F, -12.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(8, 7).addBox(1.5F, -7.3F, -12.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        return LayerDefinition.create(modelData, 128, 128);
    }

    @Override
    public void setupAnim(MirkwoodSpiderEntity entity, float limbAngle, float limbDistance, float ageInTicks, float headYaw, float headPitch) {
        float cosVal = (Mth.cos(limbAngle * 1.4F) * 0.3F) * limbDistance;
        float cosTime = Mth.cos(ageInTicks * 0.15F) * 0.6F;

        float percentage = (float) entity.getClimbingTicks() / MirkwoodSpiderEntity.CLIMBING_TIME_TRANSITION;

        if(percentage > 0) {
            cosVal = Mth.cos(ageInTicks * 0.3F) * 0.6F;

        }

        for(ModelPart leg : oddLegs) {
            leg.zRot = UPPER_LEGS_ROLL + (cosVal / 3);
            leg.xRot = cosVal;
            leg.yRot = cosVal / 4;
        }
        for(ModelPart leg : evenLegs) {
            leg.zRot = UPPER_LEGS_ROLL - (cosVal / 3);
            leg.xRot = -cosVal;
            leg.yRot = -cosVal / 4;
        }

        for(ModelPart bottomLeg : oddBottomLegs) {
            bottomLeg.zRot = BOTTOM_LEGS_ROLL;
            bottomLeg.xRot = -cosVal;
            bottomLeg.yRot = 0;
        }
        for(ModelPart bottomLeg : evenBottomLegs) {
            bottomLeg.zRot = BOTTOM_LEGS_ROLL;
            bottomLeg.xRot = cosVal;
            bottomLeg.yRot = 0;
        }

        this.leg1.yRot += LEGS_MARGIN_ANGLE * LEGS_MARGIN_ANGLE_MULTIPLIER;
        this.leg2.yRot += LEGS_MARGIN_ANGLE;
        this.leg5.yRot += 0.15f + LEGS_MARGIN_ANGLE * LEGS_MARGIN_ANGLE_MULTIPLIER;
        this.leg6.yRot += LEGS_MARGIN_ANGLE;
        this.leg3.yRot -= LEGS_MARGIN_ANGLE;
        this.leg4.yRot -= 0.15f + LEGS_MARGIN_ANGLE * LEGS_MARGIN_ANGLE_MULTIPLIER;
        this.leg7.yRot -= LEGS_MARGIN_ANGLE;
        this.leg8.yRot -= LEGS_MARGIN_ANGLE * LEGS_MARGIN_ANGLE_MULTIPLIER;

        this.bottom_leg1.yRot += 0.2f;
        this.bottom_leg4.yRot -= 0.2f;
        this.bottom_leg5.yRot += 0.2f;
        this.bottom_leg8.yRot -= 0.2f;

        this.fang1.zRot = cosTime / 8;
        this.fang2.zRot = -cosTime / 8;
        this.fang1.xRot = -cosVal / 5;
        this.fang2.xRot = cosVal / 5;

        this.abdomen.xRot = -0.15f + (cosVal / 7);

        this.body.xRot = -1.5f * percentage;
        this.abdomen.xRot += (0.7f * percentage) + (0.04 * cosTime);
    }

    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        this.body.render(matrices, vertices, light, overlay, color);

    }
}
