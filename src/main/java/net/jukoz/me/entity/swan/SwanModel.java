package net.jukoz.me.entity.swan;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jukoz.me.compat.neoforge.dist.EnvType;
import net.jukoz.me.compat.neoforge.dist.Environment;
import net.jukoz.me.entity.deer.DeerAnimations;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import org.joml.Vector3f;

@Environment(value= EnvType.CLIENT)
public class SwanModel extends HierarchicalModel<SwanEntity> {
    private final ModelPart swan;
    private final ModelPart headAndNeck;
    private final ModelPart head;
    private final ModelPart rightWing;
    private final ModelPart leftWing;

    public SwanModel(ModelPart root) {
        this.swan = root.getChild("root");
        this.headAndNeck = swan.getChild("body").getChild("headAndNeck");
        this.head = swan.getChild("body").getChild("headAndNeck").getChild("head");
        this.rightWing = swan.getChild("body").getChild("rightWing");
        this.leftWing = swan.getChild("body").getChild("leftWing");
    }
    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 23.0F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -3.5F, -5.0F, 7.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -6.5F, 0.0F));

        PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create(), PartPose.offset(4.0F, -2.75F, -4.5F));

        PartDefinition leftInnerWing = leftWing.addOrReplaceChild("leftInnerWing", CubeListBuilder.create().texOffs(30, 11).addBox(-0.5F, -0.75F, -0.5F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leftMiddleWing = leftInnerWing.addOrReplaceChild("leftMiddleWing", CubeListBuilder.create().texOffs(48, 3).addBox(-0.5F, -3.0F, 0.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.25F, 5.5F));

        PartDefinition leftOuterWing = leftMiddleWing.addOrReplaceChild("leftOuterWing", CubeListBuilder.create().texOffs(37, -6).addBox(0.0F, -2.5F, 0.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -0.5F, 6.0F));

        PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create(), PartPose.offset(-4.0F, -2.75F, -4.5F));

        PartDefinition rightInnerWing = rightWing.addOrReplaceChild("rightInnerWing", CubeListBuilder.create().texOffs(30, 11).mirror().addBox(-0.5F, -0.75F, -0.5F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition rightMiddleWing = rightInnerWing.addOrReplaceChild("rightMiddleWing", CubeListBuilder.create().texOffs(48, 3).mirror().addBox(-0.5F, -3.0F, 0.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 2.25F, 5.5F));

        PartDefinition rightOuterWing = rightMiddleWing.addOrReplaceChild("rightOuterWing", CubeListBuilder.create().texOffs(37, -6).mirror().addBox(0.0F, -2.5F, 0.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-0.5F, -0.5F, 6.0F));

        PartDefinition headAndNeck = body.addOrReplaceChild("headAndNeck", CubeListBuilder.create(), PartPose.offset(0.5F, -2.5F, -4.0F));

        PartDefinition neck = headAndNeck.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(12, 17).addBox(-1.5F, -1.75F, -4.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 20).addBox(-1.5F, -4.75F, -1.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -1.25F, 1.0F));

        PartDefinition head = headAndNeck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -6.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 55).addBox(-1.5F, -3.0F, -4.5F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -6.0F, 1.5F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(32, 23).addBox(-3.5F, -2.25F, -0.25F, 7.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(7, 26).addBox(-3.5F, -0.25F, -0.25F, 7.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.25F, 5.25F));

        PartDefinition leftLeg = root.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(0, 3).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(26, 23).addBox(-2.0F, 1.0F, -2.5F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -3.0F, 0.5F));

        PartDefinition rightLeg = root.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(0, 3).mirror().addBox(-1.0F, -2.0F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(26, 23).mirror().addBox(-2.0F, 1.0F, -2.5F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, -3.0F, 0.5F));
        return LayerDefinition.create(modelData, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        swan.render(matrices, vertices, light, overlay, color);
    }

    @Override
    public ModelPart root() {
        return swan;
    }

    @Override
    public void setupAnim(SwanEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.setHeadAngles(headYaw, headPitch);

        if(!entity.onGround() && !entity.isInWater()) {
            float angle = Mth.cos(Mth.cos(animationProgress * 0.5f));
            this.rightWing.zRot = 4 + (4 * angle);
            this.leftWing.zRot = -4 - (4 * angle);
        }

        if(entity.isBaby()) {
            this.head.offsetScale(new Vector3f(0.8f,0.8f,0.8f));
        }

        this.animateWalk(SwanAnimations.WALK, limbAngle, limbDistance, 4f, 4f);
        this.animate(entity.swimAnimationState, SwanAnimations.SWIM, animationProgress, 1f);
        this.animate(entity.idleAnimationState, SwanAnimations.WINGCLEAN, animationProgress, 1f);
        this.animate(entity.attackAnimationState, SwanAnimations.ATTACK, animationProgress, 1.7f);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30.0F, 30.0F);
        headPitch = Mth.clamp(headPitch, -25.0F, 40.0F);

        this.headAndNeck.yRot = headYaw * 0.017453292F;
        this.headAndNeck.xRot = headPitch * 0.017453292F;
    }
}