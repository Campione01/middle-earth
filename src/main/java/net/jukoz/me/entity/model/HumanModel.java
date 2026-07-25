package net.jukoz.me.entity.model;

import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class HumanModel {
    private static final String LEFT_SLEEVE = "left_sleeve";
    private static final String RIGHT_SLEEVE = "right_sleeve";
    private static final String LEFT_PANTS = "left_leg";
    private static final String RIGHT_PANTS = "right_leg";

    public static LayerDefinition getTexturedModelData(CubeDeformation dilation) {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition root = modelData.getRoot();

        root.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(8, 8)
                .addBox(-1.0f, 0f, -1.0f, 2.0f, 2.0f, 2.0f, dilation),
                PartPose.offset(0.0f, 0.0f, 0.0f));

        root.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create().texOffs(0, 0)
                .addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, dilation),
                PartPose.offset(0.0f, 0.0f, 0.0f));

        PartDefinition leftArm = root.addOrReplaceChild(PartNames.LEFT_ARM, CubeListBuilder.create().texOffs(32, 48)
                        .addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, dilation),
                PartPose.offset(17.0f, 2.5f, 0.0f));
        PartDefinition rightArm = root.addOrReplaceChild(PartNames.RIGHT_ARM, CubeListBuilder.create().texOffs(40, 16)
                        .addBox(-2.5f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, dilation),
                PartPose.offset(-7.0f, 2.5f, 0.0f));

        rightArm.addOrReplaceChild(RIGHT_SLEEVE, CubeListBuilder.create().texOffs(40, 32).addBox(0f, 0f, 0f, 4.0f, 12.0f, 4.0f, dilation.extend(0.25f)),
                PartPose.offset(-2.5f, -2f, -2f));
        leftArm.addOrReplaceChild(LEFT_SLEEVE, CubeListBuilder.create().texOffs(48, 48).addBox(0f, 0f, 0f, 4.0f, 12.0f, 4.0f, dilation.extend(0.25f)),
                PartPose.offset(-1f, -2f, -2f));

        root.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(16, 16)
                .addBox(-4.0f, 0.0f, -2f, 8.0f, 12.0f, 4.0f, dilation),
                PartPose.offset(0.0f, 0f, 0.0f));

        root.getChild(PartNames.BODY).addOrReplaceChild(PartNames.JACKET, CubeListBuilder.create().texOffs(16, 32)
                .addBox(-4.0f, 0.0f, -2f, 8.0f, 12.0f, 4.0f, dilation.extend(0.25f)), PartPose.ZERO);

        root.addOrReplaceChild(PartNames.RIGHT_LEG, CubeListBuilder.create().texOffs(0, 16)
                .addBox(-2.0f, 0f, -2.0f, 4.0f, 12.0f, 4.0f, dilation),
                PartPose.offset(-1.9f, 0.0f, 0.0f));
        root.addOrReplaceChild(PartNames.LEFT_LEG, CubeListBuilder.create().texOffs(0, 16).mirror()
                .addBox(-2.0f, 0f, -2.0f, 4.0f, 12.0f, 4.0f, dilation),
                PartPose.offset(1.9f, 0.0f, 0.0f));


        root.getChild(PartNames.LEFT_LEG).addOrReplaceChild(LEFT_PANTS, CubeListBuilder.create().texOffs(0, 48)
                        .addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, dilation.extend(0.25f)),
                PartPose.offset(0, 0.0f, 0.0f));
        root.getChild(PartNames.RIGHT_LEG).addOrReplaceChild(RIGHT_PANTS, CubeListBuilder.create().texOffs(0, 32)
                        .addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, dilation.extend(0.25f)),
                PartPose.offset(0, 0.0f, 0.0f));

        root.addOrReplaceChild(PartNames.HAT, CubeListBuilder.create().texOffs(32, 0).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, dilation.extend(0.25f)),
                PartPose.offset(0.0f, 0.0f, 0.0f));                // 54

        return LayerDefinition.create(modelData, 64, 64);
    }
}
