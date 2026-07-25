package net.jukoz.me.entity.model;

import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class DwarfModel {
    public static final String NOSE = "nose";
    public static final String BEARD = "beard";
    public static final String BEARD2 = "beard2";
    public static final String BEARD_TIP = "beard_tip";
    public static final String NORI_BEARD_CENTER = "nori_beard_center";
    public static final String NORI_BEARD_LEFT = "nori_beard_left";
    public static final String NORI_BEARD_RIGHT = "nori_beard_right";
    private static final String LEFT_SLEEVE = "left_sleeve";
    private static final String RIGHT_SLEEVE = "right_sleeve";
    public static final float BEARD_PITCH_ANGLE = -0.174f;

    public static LayerDefinition getTexturedModelData(CubeDeformation dilation) {
        MeshDefinition modelData = new MeshDefinition();
            PartDefinition modelPartData = modelData.getRoot();
            PartDefinition head = modelPartData.addOrReplaceChild(PartNames.HEAD, CubeListBuilder
                    .create().texOffs(0, 0)
                    .addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, dilation), PartPose.offset(0.0f, 0.0f, 0.0f));

            head.addOrReplaceChild(NOSE, CubeListBuilder.create().texOffs(0, 5).addBox(-1f, 0, 0.25f, 2.0f, 2.0f, 1.0f, dilation),
                    PartPose.offsetAndRotation(0f, -4.0f, -5.0f, 0, 0, 0));

            head.addOrReplaceChild(BEARD, CubeListBuilder.create().texOffs(56, 25).addBox(0, 0, -4f, 7.0f, 5.0f, 2.0f, dilation),
                    PartPose.offsetAndRotation(-3.5f, 0.67f, 0.0f, BEARD_PITCH_ANGLE, 0, 0));
            head.addOrReplaceChild(BEARD2, CubeListBuilder.create().texOffs(57, 26).addBox(0, 0, -6f, 5.0f, 3.0f, 1.0f, dilation),
                    PartPose.offsetAndRotation(-2.5f, 1f, 1.05f, BEARD_PITCH_ANGLE, 0, 0));
            head.addOrReplaceChild(BEARD_TIP, CubeListBuilder.create().texOffs(57, 26).addBox(0, 0, -5f, 5.0f, 2.0f, 1.0f, dilation),
                    PartPose.offsetAndRotation(-2.5f, 5.99f, 1f, BEARD_PITCH_ANGLE, 0, 0));

            head.addOrReplaceChild(NORI_BEARD_CENTER, CubeListBuilder.create().texOffs(64, 0).addBox(-1, 1.5f, -4.5f, 2.0f, 5.0f, 2.0f, dilation.extend(0.15f)),
                    PartPose.offsetAndRotation(0f, 0f, 0f, BEARD_PITCH_ANGLE - 0.2f, 0, 0));
            head.addOrReplaceChild(NORI_BEARD_LEFT, CubeListBuilder.create().texOffs(64, 0).addBox(1f, 0.7f, -4f, 2.0f, 5.0f, 2.0f, dilation),
                    PartPose.offsetAndRotation(0f, 0f, 0f, BEARD_PITCH_ANGLE, 0, -0.55f));
            head.addOrReplaceChild(NORI_BEARD_RIGHT, CubeListBuilder.create().texOffs(64, 0).addBox(-3f, 0.7f, -4f, 2.0f, 5.0f, 2.0f, dilation),
                    PartPose.offsetAndRotation(0f, 0f, 0f, BEARD_PITCH_ANGLE, 0, 0.55f));

            modelPartData.addOrReplaceChild(PartNames.HAT, CubeListBuilder.create().texOffs(32, 0).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, dilation.extend(0.25f)), PartPose.offset(0.0f, 0.0f, 0.0f));
            modelPartData.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(16, 16).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, dilation), PartPose.offset(0.0f, 0.0f, 0.0f));

            PartDefinition rightArm = modelPartData.addOrReplaceChild(PartNames.RIGHT_ARM, CubeListBuilder.create()
                    .texOffs(40, 16).addBox(-3.0f, -2.5f, -2.0f, 4.0f, 12.0f, 4.0f, dilation), PartPose.offset(-5.0f, 2.0f, 0.0f));
            rightArm.addOrReplaceChild(RIGHT_SLEEVE, CubeListBuilder.create().texOffs(40, 32).addBox(0f, 0f, 0f, 4.0f, 12.0f, 4.0f, dilation.extend(0.25f)), PartPose.offset(-3f, -2f, -2f));

            PartDefinition leftArm = modelPartData.addOrReplaceChild(PartNames.LEFT_ARM, CubeListBuilder.create()
                    .texOffs(40, 16).mirror().addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, dilation), PartPose.offset(5.0f, 2.0f, 0.0f));
            leftArm.addOrReplaceChild(LEFT_SLEEVE, CubeListBuilder.create().texOffs(48, 48).addBox(0f, 0f, 0f, 4.0f, 12.0f, 4.0f, dilation.extend(0.25f)), PartPose.offset(-1f, -2f, -2f));

            modelPartData.addOrReplaceChild(PartNames.RIGHT_LEG, CubeListBuilder.create().texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, dilation), PartPose.offset(-1.9f, 12.0f, 0.0f));
            modelPartData.addOrReplaceChild(PartNames.LEFT_LEG, CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, dilation), PartPose.offset(1.9f, 12.0f, 0.0f));

            return LayerDefinition.create(modelData, 82, 64);
    }
}
