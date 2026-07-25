package net.jukoz.me.client.model.equipment.head.helmets;

import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;

public class MordorOrcHelmetModel<T extends LivingEntity> extends HelmetAddonModel<T> {

    public MordorOrcHelmetModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();

        modelPartData.addOrReplaceChild(PartNames.HAT, CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition head = modelPartData.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition snout = head.addOrReplaceChild("snoot", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -0.0F, -2.59F, 1.1345F, 0.0F, 0.0F));

        snout.addOrReplaceChild("snout_cube", CubeListBuilder.create().texOffs(0, 16).addBox(-3.5F, -3.7F, -7.5F, 7.0F, 7.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));
        head.addOrReplaceChild("pikes", CubeListBuilder.create().texOffs(34, -2).addBox(0.0F, -14.6F, -8.5F, 0.0F, 12.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("forward_extension", CubeListBuilder.create().texOffs(40, 0).addBox(-4.0F, -8.2F, -7.8F, 8.0F, 9.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("down_hat_extension", CubeListBuilder.create().texOffs(46, 35).addBox(-4.0F, -8.4F, -4.0F, 8.0F, 8.0F, 1.0F, new CubeDeformation(0.9F)), PartPose.offset(0.0F, 10.0F, 0.0F));
        head.addOrReplaceChild("side_extension_left", CubeListBuilder.create().texOffs(18, 0).addBox(0.0F, -3.2F, 1.0F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-4.0F, -5.0F, 0.2F, 0.0F, -0.4363F, 0.0F));
        head.addOrReplaceChild("side_extension_right", CubeListBuilder.create().texOffs(29, 0).addBox(-1.0F, -3.2F, 1.0F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(4.0F, -5.0F, 0.2F, 0.0F, 0.4363F, 0.0F));

        modelPartData.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        modelPartData.addOrReplaceChild(PartNames.RIGHT_ARM, CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        modelPartData.addOrReplaceChild(PartNames.LEFT_ARM, CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        modelPartData.addOrReplaceChild(PartNames.RIGHT_LEG, CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        modelPartData.addOrReplaceChild(PartNames.LEFT_LEG, CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(modelData, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }
}