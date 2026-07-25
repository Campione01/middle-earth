package net.jukoz.me.entity.model;

import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class OrcModel {
    private static final String LEFT_EAR = "left_ear";
    private static final String RIGHT_EAR = "right_ear";
    private static final String LEFT_SLEEVE = "left_sleeve";
    private static final String RIGHT_SLEEVE = "right_sleeve";
    private static final String LEFT_PANTS = "left_leg";
    private static final String RIGHT_PANTS = "right_leg";

    public static LayerDefinition getTexturedModelData(CubeDeformation dilation) {
        MeshDefinition modelData = HumanoidModel.createMesh(dilation, 0.0f);
        PartDefinition modelPartData = modelData.getRoot();

        PartDefinition rightEar = modelPartData.getChild(PartNames.HEAD).addOrReplaceChild(RIGHT_EAR, CubeListBuilder.create().texOffs(1, 1).addBox(0, -0, -1f, 1.0f, 3.0f, 2.0f, dilation),
                PartPose.offsetAndRotation(-5f, -5f, 0.25f,-0.7f, 0, -0.35f));
        PartDefinition leftEar = modelPartData.getChild(PartNames.HEAD).addOrReplaceChild(LEFT_EAR, CubeListBuilder.create().texOffs(1, 1).addBox(0, -0, -1f, 1.0f, 3.0f, 2.0f, dilation),
                PartPose.offsetAndRotation(4f, -5f, 0.25f,-0.7f, 0, 0.35f));

        PartDefinition leftArm = modelPartData.addOrReplaceChild(PartNames.LEFT_ARM, CubeListBuilder.create().texOffs(32, 48)
                        .addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, dilation),
                PartPose.offset(17.0f, 2.5f, 0.0f));
        PartDefinition rightArm = modelPartData.addOrReplaceChild(PartNames.RIGHT_ARM, CubeListBuilder.create().texOffs(40, 16)
                        .addBox(-2.5f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, dilation),
                PartPose.offset(-7.0f, 2.5f, 0.0f));

        rightArm.addOrReplaceChild(RIGHT_SLEEVE, CubeListBuilder.create().texOffs(40, 32).addBox(0f, 0f, 0f, 4.0f, 12.0f, 4.0f, dilation.extend(0.25f)),
                PartPose.offset(-2.5f, -2f, -2f));
        leftArm.addOrReplaceChild(LEFT_SLEEVE, CubeListBuilder.create().texOffs(48, 48).addBox(0f, 0f, 0f, 4.0f, 12.0f, 4.0f, dilation.extend(0.25f)),
                PartPose.offset(-1f, -2f, -2f));

        modelPartData.getChild(PartNames.BODY).addOrReplaceChild(PartNames.JACKET, CubeListBuilder.create().texOffs(16, 32).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, dilation.extend(0.25f)), PartPose.ZERO);
        modelPartData.getChild(PartNames.LEFT_LEG).addOrReplaceChild(LEFT_PANTS, CubeListBuilder.create().texOffs(0, 48).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, dilation.extend(0.25f)),
                PartPose.offset(0, 0.0f, 0.0f));
        modelPartData.getChild(PartNames.RIGHT_LEG).addOrReplaceChild(RIGHT_PANTS, CubeListBuilder.create().texOffs(0, 32).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, dilation.extend(0.25f)),
                PartPose.offset(0, 0.0f, 0.0f));

        modelPartData.addOrReplaceChild(PartNames.HAT, CubeListBuilder.create().texOffs(32, 0).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, dilation.extend(0.25f)),
                PartPose.offset(0.0f, 0.0f, 0.0f));                // 54

        return LayerDefinition.create(modelData, 64, 64);
    }
}
