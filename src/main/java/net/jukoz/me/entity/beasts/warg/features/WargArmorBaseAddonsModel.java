package net.jukoz.me.entity.beasts.warg.features;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jukoz.me.entity.beasts.warg.WargAnimations;
import net.jukoz.me.entity.beasts.warg.WargEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class WargArmorBaseAddonsModel extends HierarchicalModel<WargEntity> {
    private final ModelPart warg;
    public WargArmorBaseAddonsModel(ModelPart root) {
        this.warg = root.getChild("root");
    }

    public static LayerDefinition getTexturedModelDataSpine() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 6.0F, -2.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(-2.6142F, 2.1138F, 1.5F));

        PartDefinition upper_body = body.addOrReplaceChild("upper_body", CubeListBuilder.create(), PartPose.offset(-0.3858F, -1.1138F, -1.5F));

        PartDefinition body_armor = upper_body.addOrReplaceChild("body_armor", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 2.0F));

        PartDefinition front_armor = body_armor.addOrReplaceChild("front_armor", CubeListBuilder.create(), PartPose.offset(7.8F, 1.5F, -1.0F));

        PartDefinition addons_front_armor = front_armor.addOrReplaceChild("addons_front_armor", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition spine = addons_front_armor.addOrReplaceChild("spine", CubeListBuilder.create().texOffs(8, 0).addBox(2.0F, -2.4F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(16, 0).addBox(-1.0F, -2.1F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 0.0F));

        PartDefinition spine_04_r1 = spine.addOrReplaceChild("spine_04_r1", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -0.7F, 0.0F, 0.0F, 0.0F, 0.0873F));

        PartDefinition spine_01_r1 = spine.addOrReplaceChild("spine_01_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -1.2F, 0.0F, 0.0F, 0.0F, 0.1309F));
        return LayerDefinition.create(modelData, 128, 128);
    }

    @Override
    public ModelPart root() {
        return warg;
    }

    @Override
    public void setupAnim(WargEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

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

    public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        warg.render(matrices, vertexConsumer, light, overlay, color);
    }
}
