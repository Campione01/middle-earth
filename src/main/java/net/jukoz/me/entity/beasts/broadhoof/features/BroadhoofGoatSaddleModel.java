package net.jukoz.me.entity.beasts.broadhoof.features;

import net.jukoz.me.entity.beasts.broadhoof.BroadhoofGoatAnimations;
import net.jukoz.me.entity.beasts.broadhoof.BroadhoofGoatEntity;
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

public class BroadhoofGoatSaddleModel extends HierarchicalModel<BroadhoofGoatEntity> {

    private final ModelPart broadhoofGoat;
    private final ModelPart head;
    private final ModelPart reins;
    public BroadhoofGoatSaddleModel(ModelPart root) {
        this.broadhoofGoat = root.getChild("broadhoof_goat");
        this.head = broadhoofGoat.getChild(PartNames.BODY).getChild(PartNames.HEAD);
        this.reins = head.getChild("saddle_things").getChild("reins");
    }
    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition broadhoof_goat = modelPartData.addOrReplaceChild("broadhoof_goat", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = broadhoof_goat.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create(), PartPose.offset(0.0F, -11.0F, 2.0F));

        PartDefinition saddle = body.addOrReplaceChild("saddle", CubeListBuilder.create().texOffs(74, 19).addBox(-7.0F, -23.0F, 13.0F, 14.0F, 14.0F, 13.0F, new CubeDeformation(0.3F))
                .texOffs(92, 72).addBox(-7.0F, -25.0F, 25.0F, 14.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 12.0F, -21.0F));

        PartDefinition head = body.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -6.0F, -9.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition saddle_things = head.addOrReplaceChild("saddle_things", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition reins = saddle_things.addOrReplaceChild("reins", CubeListBuilder.create().texOffs(81, -3).mirror().addBox(-4.1F, -7.0F, -8.0F, 0.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(81, -3).addBox(4.1F, -7.0F, -8.0F, 0.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 1.0F, 0.48F, 0.0F, 0.0F));

        PartDefinition mouth_things = saddle_things.addOrReplaceChild("mouth_things", CubeListBuilder.create().texOffs(62, 50).mirror().addBox(-2.0F, -3.0F, 1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(62, 50).addBox(5.0F, -3.0F, 1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(66, 47).addBox(-1.0F, -7.0F, -1.0F, 6.0F, 7.0F, 11.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(-2.0F, 0.0F, -8.0F, 0.7854F, 0.0F, 0.0F));
        return LayerDefinition.create(modelData, 128, 128);
    }

    @Override
    public ModelPart root() {
        return broadhoofGoat;
    }

    @Override
    public void setupAnim(BroadhoofGoatEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.setHeadAngles(headYaw, headPitch);

        this.reins.visible = entity.hasControllingPassenger();

        if((entity.hasControllingPassenger() && entity.getControllingPassenger().isSprinting()) || (entity.isAggressive() && !entity.hasControllingPassenger())) {
            this.animateWalk(BroadhoofGoatAnimations.RUN, limbAngle, limbDistance, 1.2f, 1.2f);
        }
        else {
            this.animateWalk(BroadhoofGoatAnimations.WALK, limbAngle, limbDistance, 4f, 4f);
        }

        this.animate(entity.idleAnimationState, BroadhoofGoatAnimations.EAT, animationProgress, 1f);
        this.animate(entity.attackAnimationState, BroadhoofGoatAnimations.RAM_ATTACK, animationProgress, 1f);
        this.animate(entity.startSittingAnimationState, BroadhoofGoatAnimations.LAY_DOWN, animationProgress, 3f);
        this.animate(entity.stopSittingAnimationState, BroadhoofGoatAnimations.STAND_UP, animationProgress, 3f);
        this.animate(entity.sittingAnimationState, BroadhoofGoatAnimations.LYING, animationProgress, 1f);
        this.animate(entity.chargeAnimationState, BroadhoofGoatAnimations.CHARGE_ATTACK, animationProgress, 1f);
        this.animate(entity.jumpAnimationState, BroadhoofGoatAnimations.JUMP, animationProgress, 1f);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30.0F, 30.0F);
        headPitch = Mth.clamp(headPitch, -25.0F, 40.0F);

        this.head.yRot = headYaw * 0.017453292F;
        this.head.xRot = headPitch * 0.017453292F;
    }
}
