package net.jukoz.me.entity.beasts.warg.features;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jukoz.me.entity.beasts.warg.WargAnimations;
import net.jukoz.me.entity.beasts.warg.WargEntity;
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

public class WargArmorModel extends HierarchicalModel<WargEntity> {
        private final ModelPart warg;
        private final ModelPart head;
        public WargArmorModel(ModelPart root) {
                this.warg = root.getChild("root");
                this.head = warg.getChild(PartNames.BODY).getChild("upper_body").getChild(PartNames.HEAD);
                }
        public static LayerDefinition getTexturedModelData() {
                MeshDefinition modelData = new MeshDefinition();
                PartDefinition modelPartData = modelData.getRoot();
                PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 6.0F, -2.0F, 0.0F, 1.5708F, 0.0F));

                PartDefinition body = root.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create(), PartPose.offset(-2.6142F, 2.1138F, 1.5F));

                PartDefinition upper_body = body.addOrReplaceChild("upper_body", CubeListBuilder.create(), PartPose.offset(-0.3858F, -1.1138F, -1.5F));

                PartDefinition body_armor = upper_body.addOrReplaceChild("body_armor", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 2.0F));

                PartDefinition front_armor = body_armor.addOrReplaceChild("front_armor", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -6.4F, -5.0F, 14.0F, 11.0F, 10.0F, new CubeDeformation(0.0F))
                        .texOffs(49, 0).addBox(-4.0F, -6.5F, -5.0F, 11.0F, 8.0F, 10.0F, new CubeDeformation(0.4F)), PartPose.offset(7.8F, 1.5F, -1.0F));

                PartDefinition left_chains = front_armor.addOrReplaceChild("left_chains", CubeListBuilder.create(), PartPose.offset(0.5F, -3.5F, 5.3F));

                PartDefinition chains_r1 = left_chains.addOrReplaceChild("chains_r1", CubeListBuilder.create().texOffs(102, 24).addBox(-6.5F, -2.0F, 0.0F, 13.0F, 10.0F, 0.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0007F, -0.0076F, -0.0002F));

                PartDefinition right_chains = front_armor.addOrReplaceChild("right_chains", CubeListBuilder.create(), PartPose.offsetAndRotation(0.5F, -2.5F, -5.3F, 0.0F, 0.0F, -0.1745F));

                PartDefinition chains_r2 = right_chains.addOrReplaceChild("chains_r2", CubeListBuilder.create().texOffs(102, 24).addBox(-6.5F, -2.0F, 0.0F, 13.0F, 10.0F, 0.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0007F, 0.0076F, 0.1744F));

                PartDefinition back_armor = body_armor.addOrReplaceChild("back_armor", CubeListBuilder.create(), PartPose.offset(-7.2F, 1.5F, -1.0F));

                PartDefinition backlegplate_r1 = back_armor.addOrReplaceChild("backlegplate_r1", CubeListBuilder.create().texOffs(42, 62).addBox(-8.2F, -6.5F, -5.0F, 15.0F, 9.0F, 10.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -3.1416F, 0.0F, 3.1416F));

                PartDefinition backarmor_r1 = back_armor.addOrReplaceChild("backarmor_r1", CubeListBuilder.create().texOffs(37, 36).addBox(-7.8F, -6.5F, -5.0F, 14.0F, 11.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.1F, 0.0F, -3.1416F, 0.0F, 3.1416F));

                PartDefinition head = upper_body.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create(), PartPose.offsetAndRotation(14.3858F, -1.8862F, 1.5F, 0.0F, 0.0F, 0.2618F));

                PartDefinition head_armor = head.addOrReplaceChild("head_armor", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

                PartDefinition Lbone_snout_r1 = head_armor.addOrReplaceChild("Lbone_snout_r1", CubeListBuilder.create().texOffs(101, 0).addBox(-7.2076F, -0.8956F, -2.3986F, 11.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.4218F, 1.1634F, -4.1014F, -2.7477F, -0.1032F, -3.0907F));

                PartDefinition Lbone_snout_r2 = head_armor.addOrReplaceChild("Lbone_snout_r2", CubeListBuilder.create().texOffs(101, 0).addBox(-7.2076F, -0.8956F, 2.3986F, 11.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.4218F, 1.1634F, 3.1014F, 2.7477F, 0.1032F, -3.0907F));

                PartDefinition underhelmet_r1 = head_armor.addOrReplaceChild("underhelmet_r1", CubeListBuilder.create().texOffs(5, 57).addBox(-3.2F, -6.6623F, -3.8552F, 11.0F, 9.0F, 8.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(5.859F, 2.9485F, -0.4F, -3.1416F, 0.0F, 3.1416F));

                PartDefinition helmet_r1 = head_armor.addOrReplaceChild("helmet_r1", CubeListBuilder.create().texOffs(3, 23).addBox(-3.2F, -6.2623F, -4.8552F, 11.0F, 13.0F, 10.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(5.859F, 1.9485F, -0.4F, -3.1416F, 0.0F, 3.1416F));

                PartDefinition snout2 = head_armor.addOrReplaceChild("snout2", CubeListBuilder.create(), PartPose.offset(7.3218F, -2.3967F, 3.1F));

                PartDefinition snout_r1 = snout2.addOrReplaceChild("snout_r1", CubeListBuilder.create().texOffs(36, 23).addBox(-6.2076F, 0.1044F, 2.1F, 13.0F, 5.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -3.1416F, 0.0F, 3.1416F));

                PartDefinition snout_r2 = snout2.addOrReplaceChild("snout_r2", CubeListBuilder.create().texOffs(98, 35).addBox(-5.2076F, 3.2829F, 2.1F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.2F, 0.0F, -3.1416F, 0.0F, 3.1416F));
                return LayerDefinition.create(modelData, 128, 128);
        }

        @Override
        public ModelPart root() {
                return warg;
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

        public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
                warg.render(matrices, vertexConsumer, light, overlay, color);
        }
}