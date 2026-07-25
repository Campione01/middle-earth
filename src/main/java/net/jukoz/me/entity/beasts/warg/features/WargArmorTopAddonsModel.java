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

public class WargArmorTopAddonsModel extends HierarchicalModel<WargEntity> {

    private final ModelPart warg;
    public WargArmorTopAddonsModel(ModelPart root) {
        this.warg = root.getChild("root");
    }

    public static LayerDefinition getTexturedModelDataFront() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 6.0F, -2.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(-2.6142F, 2.1138F, 1.5F));

        PartDefinition upper_body = body.addOrReplaceChild("upper_body", CubeListBuilder.create(), PartPose.offset(-0.3858F, -1.1138F, -1.5F));

        PartDefinition body_armor = upper_body.addOrReplaceChild("body_armor", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 2.0F));

        PartDefinition armor_addons = body_armor.addOrReplaceChild("armor_addons", CubeListBuilder.create(), PartPose.offset(-5.0F, -5.0F, -4.0F));

        PartDefinition front_addons = armor_addons.addOrReplaceChild("front_addons", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition skull2 = front_addons.addOrReplaceChild("skull2", CubeListBuilder.create().texOffs(6, 43).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.1F))
                .texOffs(79, 48).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.2F))
                .texOffs(6, 30).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(6, 60).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(13.0F, -4.0F, 3.0F, 0.0F, -1.5708F, 0.0F));
        return LayerDefinition.create(modelData, 128, 128);
    }

    public static LayerDefinition getTexturedModelDataBack() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 6.0F, -2.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(-2.6142F, 2.1138F, 1.5F));

        PartDefinition upper_body = body.addOrReplaceChild("upper_body", CubeListBuilder.create(), PartPose.offset(-0.3858F, -1.1138F, -1.5F));

        PartDefinition body_armor = upper_body.addOrReplaceChild("body_armor", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 2.0F));

        PartDefinition armor_addons = body_armor.addOrReplaceChild("armor_addons", CubeListBuilder.create(), PartPose.offset(-5.0F, -5.0F, -4.0F));

        PartDefinition back_addons = armor_addons.addOrReplaceChild("back_addons", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 12).addBox(-1.0F, -9.0F, -1.0F, 2.0F, 7.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(6, 60).addBox(-1.0F, -23.0F, 2.0F, 2.0F, 23.0F, 2.0F, new CubeDeformation(-0.1F))
                .texOffs(20, 63).addBox(-1.0F, -27.0F, 3.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = back_addons.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(4, 99).addBox(-16.0F, -8.5F, 0.0F, 17.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -21.5F, 3.0F, 0.0F, 0.0F, -1.4399F));

        PartDefinition cube_r2 = back_addons.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(19, 81).addBox(-1.0F, -8.5F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(-2.0F, -21.5F, 3.0F, 0.0F, 0.0F, -1.5708F));

        PartDefinition skull = back_addons.addOrReplaceChild("skull", CubeListBuilder.create().texOffs(6, 43).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.1F))
                .texOffs(6, 30).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, 3.0F, 0.0F, -1.5708F, 0.0F));
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
