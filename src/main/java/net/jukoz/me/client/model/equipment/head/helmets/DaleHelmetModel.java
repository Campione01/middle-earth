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

public class DaleHelmetModel<T extends LivingEntity> extends HelmetAddonModel<T> {

    public DaleHelmetModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();

        modelPartData.addOrReplaceChild(PartNames.HAT, CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = modelPartData.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition addon = head.addOrReplaceChild("addons", CubeListBuilder.create().texOffs(0, 37).addBox(-5.0F, -5.3F, -6.0F, 10.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 29).addBox(0.0F, -13.3F, -6.0F, 0.0F, 11.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone = addon.addOrReplaceChild("spike", CubeListBuilder.create(), PartPose.offset(0.0F, -11.0F, 0.0F));
        bone.addOrReplaceChild("spike_cube", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition Plumes = bone.addOrReplaceChild("Plumes", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

        PartDefinition Plume = Plumes.addOrReplaceChild("plume_0", CubeListBuilder.create().texOffs(0, 51).addBox(0.0F, -6.0F, -3.0F, 0.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -2.1369F, -0.5338F, 1.8835F));
        Plume.addOrReplaceChild("plume_cube_0", CubeListBuilder.create().texOffs(0, 45).addBox(0.0F, -6.0F, -3.0F, 0.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

        PartDefinition Plume2 = Plumes.addOrReplaceChild("plume_1", CubeListBuilder.create().texOffs(14, 51).addBox(0.0F, -6.0F, -3.0F, 0.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3841F, -0.761F, 0.4898F));
        Plume2.addOrReplaceChild("plume_cube_1", CubeListBuilder.create().texOffs(14, 45).addBox(0.0F, -6.0F, -3.0F, 0.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

        PartDefinition Plume3 = Plumes.addOrReplaceChild("plume_2", CubeListBuilder.create().texOffs(28, 51).addBox(0.0F, -6.0F, -3.0F, 0.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -2.8254F, -0.5049F, 2.4806F));
        Plume3.addOrReplaceChild("plume_cube_2", CubeListBuilder.create().texOffs(28, 45).addBox(0.0F, -6.0F, -3.0F, 0.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

        PartDefinition Plume4 = Plumes.addOrReplaceChild("plume_3", CubeListBuilder.create().texOffs(42, 51).addBox(0.0F, -6.0F, -3.0F, 0.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -2.7925F, 0.5672F, 3.1416F));
        Plume4.addOrReplaceChild("plume_cube_3", CubeListBuilder.create().texOffs(42, 45).addBox(0.0F, -6.0F, -3.0F, 0.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

        PartDefinition Plume5 = Plumes.addOrReplaceChild("plume_4", CubeListBuilder.create().texOffs(42, 39).addBox(0.0F, -6.0F, -3.0F, 0.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.7964F, -0.9478F, 1.7551F));
        Plume5.addOrReplaceChild("plume_cube_4", CubeListBuilder.create().texOffs(42, 33).addBox(0.0F, -6.0F, -3.0F, 0.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

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