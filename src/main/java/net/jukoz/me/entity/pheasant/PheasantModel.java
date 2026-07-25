package net.jukoz.me.entity.pheasant;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class PheasantModel extends HierarchicalModel<PheasantEntity> {
    private final ModelPart pheasant;
    private final ModelPart rightleg;
    private final ModelPart leftleg;
    private final ModelPart rightwing;
    private final ModelPart leftwing;

    public PheasantModel(ModelPart root) {
        this.pheasant = root.getChild("pheasant");
        this.rightleg = this.pheasant.getChild("rightleg");
        this.leftleg = this.pheasant.getChild("leftleg");
        this.rightwing = this.pheasant.getChild("rightwing");
        this.leftwing = this.pheasant.getChild("leftwing");

    }
    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition pheasant = modelPartData.addOrReplaceChild("pheasant", CubeListBuilder.create(), PartPose.offset(2.4F, 24.0F, -3.5F));

        PartDefinition upperbody = pheasant.addOrReplaceChild("upperbody", CubeListBuilder.create(), PartPose.offset(-2.5F, -4.0F, 3.0F));

        PartDefinition body = upperbody.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(2.5F, 4.0F, -3.0F));

        PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(27, 0).addBox(-2.5946F, -2.513F, -3.2795F, 5.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4F, -6.4F, 3.04F, -0.2618F, 0.0F, 0.0F));

        PartDefinition body_r2 = body.addOrReplaceChild("body_r2", CubeListBuilder.create().texOffs(27, 13).addBox(-1.9591F, -2.5521F, 0.0991F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -4.96F, 5.28F, 0.1745F, 0.0F, 0.0F));

        PartDefinition headandneck = upperbody.addOrReplaceChild("headandneck", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -3.0F, -2.0F, 0.1309F, 0.0F, 0.0F));

        PartDefinition head = headandneck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(26, 0).addBox(-0.52F, -1.24F, -3.28F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.56F, -2.92F, -2.4F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(44, 0).addBox(-1.56F, 0.08F, -2.4F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, -5.0714F, -0.8695F, -0.1309F, 0.0F, 0.0F));

        PartDefinition head_r1 = head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(15, 0).addBox(-1.0F, -1.16F, -0.64F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.1305F, 0.0F, 0.0F));

        PartDefinition neck = headandneck.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 8).addBox(-0.94F, -5.4F, -0.88F, 2.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.9914F, -0.8695F));

        PartDefinition tail = upperbody.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 1.0F));

        PartDefinition tail_r1 = tail.addOrReplaceChild("tail_r1", CubeListBuilder.create().texOffs(1, 26).addBox(-1.4653F, -2.2942F, -0.2503F, 3.0F, 3.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 2.0025F, 0.3054F, 0.0F, 0.0F));

        PartDefinition tail_r2 = tail.addOrReplaceChild("tail_r2", CubeListBuilder.create().texOffs(2, 7).addBox(-1.4743F, -0.9196F, 0.683F, 3.0F, 0.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 2.0025F, 0.3053F, 0.0F, 0.0F));

        PartDefinition tail_r3 = tail.addOrReplaceChild("tail_r3", CubeListBuilder.create().texOffs(2, 7).addBox(-1.5F, -0.52F, 1.2F, 3.0F, 0.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 2.0025F, 0.1773F, 0.0F, 0.0F));

        PartDefinition tail_r4 = tail.addOrReplaceChild("tail_r4", CubeListBuilder.create().texOffs(2, 7).addBox(-1.5001F, -0.9949F, 1.2189F, 3.0F, 0.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 2.0025F, 0.2181F, 0.0F, 0.0F));

        PartDefinition leftwing = pheasant.addOrReplaceChild("leftwing", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 2.5F));

        PartDefinition leftwing_r1 = leftwing.addOrReplaceChild("leftwing_r1", CubeListBuilder.create().texOffs(5, 16).mirror().addBox(4.3472F, -1.372F, -0.9923F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.7425F, 1.1553F, 0.2354F, -0.4346F, 0.0F, 0.0F));

        PartDefinition rightwing = pheasant.addOrReplaceChild("rightwing", CubeListBuilder.create(), PartPose.offset(-5.0F, -8.0F, 2.5F));

        PartDefinition rightwing_r1 = rightwing.addOrReplaceChild("rightwing_r1", CubeListBuilder.create().texOffs(5, 16).addBox(-0.7728F, -1.372F, -0.9923F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2575F, 1.1553F, 0.2354F, -0.4346F, 0.0F, 0.0F));

        PartDefinition rightleg = pheasant.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(10, 8).addBox(-0.54F, -1.0F, -1.46F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, -4.0F, 3.5F));

        PartDefinition leftleg = pheasant.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(10, 8).addBox(-0.55F, -1.0F, -1.62F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.25F, -4.0F, 3.5F));
        return LayerDefinition.create(modelData, 64, 48);
    }

    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        pheasant.render(matrices, vertices, light, overlay, color);
    }

    @Override
    public ModelPart root() {
        return this.pheasant;
    }

    @Override
    public void setupAnim(PheasantEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.rightleg.xRot = Mth.cos(limbAngle * 0.6662f) * 1.4f * limbDistance;
        this.leftleg.xRot = Mth.cos(limbAngle * 0.6662f + (float)Math.PI) * 1.4f * limbDistance;

        this.rightwing.zRot = 0;
        this.leftwing.zRot = 0;

        if(!entity.onGround()) {
            float angle = Mth.cos(Mth.cos(animationProgress * 0.5f));
            this.rightwing.zRot = 4 + (4 * angle);
            this.leftwing.zRot = -4 - (4 * angle);
        }
    }
}