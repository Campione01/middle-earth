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
import org.joml.Vector3f;

public class WargSaddleModel extends HierarchicalModel<WargEntity> {
    private final ModelPart warg;
    private final ModelPart saddle;
    private float dilation;
    private float position;
    public WargSaddleModel(ModelPart root) {
        this.warg = root.getChild("root");
        this.saddle = this.warg.getChild(PartNames.BODY).getChild("upper_body").getChild("saddle");
        this.dilation = 0;
        this.position = 0;
    }
    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 6.0F, -2.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition body = root.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create(), PartPose.offset(-2.6142F, 2.1138F, 1.5F));

        PartDefinition upperBody = body.addOrReplaceChild("upper_body", CubeListBuilder.create(), PartPose.offset(-0.3858F, -1.1138F, -1.5F));

        PartDefinition saddle = upperBody.addOrReplaceChild("saddle", CubeListBuilder.create(), PartPose.offset(1.0F, -4.0F, -4.0F));

        PartDefinition cube_r1 = saddle.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(23, 97).addBox(-2.5F, -5.5F, -9.0F, 11.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -2.0F, 2.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r2 = saddle.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(70, 32).addBox(1.5F, 10.5F, -9.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.1F))
                .texOffs(69, 19).addBox(-0.5F, 15.5F, -9.0F, 7.0F, 5.0F, 3.0F, new CubeDeformation(0.1F))
                .texOffs(20, 8).addBox(-2.5F, 16.5F, -9.0F, 11.0F, 16.0F, 15.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-1.0F, -19.0F, 2.0F, 0.0F, -1.5708F, 0.0F));
        return LayerDefinition.create(modelData, 128, 128);
    }



    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        warg.render(matrices, vertexConsumer, light, overlay, color);
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

        this.dilation = entity.isWearingBodyArmor() ? 0f : -0.1f;
        this.position = entity.isWearingBodyArmor() ? 0 : 0.5f;

        this.saddle.offsetScale(new Vector3f(dilation));
        this.saddle.offsetPos(new Vector3f(0, (position * 1.5f), position));

        this.animate(entity.idleAnimationState, WargAnimations.GROOM, animationProgress, 1f);
        this.animate(entity.attackAnimationState, WargAnimations.BITE, animationProgress, 1f);
        this.animate(entity.startSittingAnimationState, WargAnimations.SIT_DOWN, animationProgress, 3f);
        this.animate(entity.stopSittingAnimationState, WargAnimations.STAND_UP, animationProgress, 3f);
        this.animate(entity.sittingAnimationState, WargAnimations.SIT, animationProgress, 1f);
    }
}
