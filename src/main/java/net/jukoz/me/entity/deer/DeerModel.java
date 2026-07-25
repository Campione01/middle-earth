package net.jukoz.me.entity.deer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jukoz.me.entity.beasts.trolls.TrollAnimations;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class DeerModel extends HierarchicalModel<DeerEntity> {
    private final ModelPart deer;
    public DeerModel(ModelPart root) {
        this.deer = root.getChild("deer");
    }
    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition deer = modelPartData.addOrReplaceChild("deer", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition upperBody = deer.addOrReplaceChild("upper_body", CubeListBuilder.create(), PartPose.offset(0.0F, -15.5F, -3.5F));

        upperBody.addOrReplaceChild("chest", CubeListBuilder.create().texOffs(35, 15).addBox(-5.5F, -5.5F, -8.5F, 11.0F, 11.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(0, 24).addBox(-5.0F, -4.5F, 0.5F, 10.0F, 10.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(72, 49).addBox(-2.0F, -2.5F, 6.5F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -2.0F));

        PartDefinition head = upperBody.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -0.9137F, -8.8708F, -0.5672F, 0.0F, 0.0F));
        PartDefinition neck = head.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(0.0F, 0.7641F, 0.6357F));
        neck.addOrReplaceChild("neck_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -3.3504F, -8.5F, 5.0F, 7.0F, 17.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -5.0F, -3.0F, -1.0036F, 0.0F, 0.0F));

        PartDefinition mainHead = neck.addOrReplaceChild("main_head", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -9.5365F, -6.0671F, 0.2182F, 0.0F, 0.0F));
        mainHead.addOrReplaceChild("ear_left", CubeListBuilder.create().texOffs(27, 0).addBox(-1.0F, -2.5F, -0.5F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -2.5889F, -0.3614F, 0.1678F, -0.2564F, 0.9819F));
        mainHead.addOrReplaceChild("ear_right", CubeListBuilder.create().texOffs(36, 45).addBox(-1.0F, -2.5F, -0.5F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -2.5889F, -0.3614F, 0.1678F, 0.2564F, -0.9819F));
        mainHead.addOrReplaceChild("skull", CubeListBuilder.create().texOffs(33, 3).addBox(-3.0F, -3.0F, -2.4569F, 6.0F, 5.0F, 7.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, -0.7072F, -1.3409F, 0.4363F, 0.0F, 0.0F));
        mainHead.addOrReplaceChild("muzzle", CubeListBuilder.create().texOffs(62, 5).addBox(-3.0F, -1.0F, -6.8F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(1.0F, -1.7163F, -1.5432F, 0.4363F, 0.0F, 0.0F));

        PartDefinition antlers = head.addOrReplaceChild("antlers", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0F, -20.0863F, -8.1292F, 0.0873F, 0.0F, 0.0F));
        antlers.addOrReplaceChild("antler_left", CubeListBuilder.create().texOffs(2, 59).mirror().addBox(-2.0F, -7.5F, -6.2F, 0.0F, 15.0F, 15.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3309F, -0.4862F, -0.3936F));
        antlers.addOrReplaceChild("antler_right", CubeListBuilder.create().texOffs(2, 59).addBox(2.0F, -7.5F, -6.2F, 0.0F, 15.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 0.0F, 0.0F, 0.3309F, 0.4862F, 0.3936F));

        deer.addOrReplaceChild("leg_back_left", CubeListBuilder.create().texOffs(0, 57).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.49F, -11.0F, 4.0F));
        deer.addOrReplaceChild("leg_back_right", CubeListBuilder.create().texOffs(38, 57).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.49F, -11.0F, 4.0F));
        deer.addOrReplaceChild("leg_front_left", CubeListBuilder.create().texOffs(12, 57).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, -11.0F, -11.0F));
        deer.addOrReplaceChild("leg_front_right", CubeListBuilder.create().texOffs(24, 57).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, -11.0F, -11.0F));
        return LayerDefinition.create(modelData, 128, 128);
    }

    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        this.deer.render(matrices, vertices, light, overlay, color);

    }

    @Override
    public ModelPart root() {
        return this.deer;
    }

    @Override
    public void setupAnim(DeerEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animateWalk(DeerAnimations.WALK, limbAngle, limbDistance, 1f, 1f);
        this.animate(entity.idleAnimationState, DeerAnimations.IDLE, animationProgress, 1f);
    }
}

