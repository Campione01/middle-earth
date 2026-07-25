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

public class HobgoblinPlateHelmetModel<T extends LivingEntity> extends HelmetAddonModel<T> {

    public HobgoblinPlateHelmetModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();

        modelPartData.addOrReplaceChild(PartNames.HAT, CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition head = modelPartData.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition addon = head.addOrReplaceChild("addons_0", CubeListBuilder.create()
                .texOffs(28, 0).addBox(-9.0F, -12.0F, -5.05F, 18.0F, 12.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.0F, 0.85F, -4.0F, 8.0F, 9.0F, 3.0F, new CubeDeformation(0.55F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition addons1 = addon.addOrReplaceChild("addons_1", CubeListBuilder.create().texOffs(54, 49).addBox(-0.5F, -3.8868F, -0.8666F, 4.0F, 14.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offset(-1.5F, -10.5976F, -4.4512F));
        addons1.addOrReplaceChild("left_pane", CubeListBuilder.create().texOffs(38, 53).mirror().addBox(-4.0F, -5.5F, 0.0F, 8.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.2366F, 0.5132F, 0.3928F, 0.0F, -0.3491F, 0.0F));
        addons1.addOrReplaceChild("right_pane", CubeListBuilder.create().texOffs(38, 53).addBox(-5.0F, -4.0F, 0.0F, 8.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3312F, -0.9868F, 0.0562F, 0.0F, 0.3491F, 0.0F));
        addons1.addOrReplaceChild("large_pane", CubeListBuilder.create().texOffs(24, 10).addBox(1.0F, -12.0F, -2.5F, 0.0F, 14.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 3.1132F, -0.3666F, 0.3054F, 0.0F, 0.0F));
        addons1.addOrReplaceChild("left_cube", CubeListBuilder.create().texOffs(32, 52).addBox(0.3F, -4.5F, -2.0F, 2.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6416F, 0.5132F, 1.3314F, 0.0F, -0.3491F, 0.0F));
        addons1.addOrReplaceChild("right_cube", CubeListBuilder.create().texOffs(32, 52).mirror().addBox(1.675F, -4.5F, -1.925F, 2.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.3584F, 0.5132F, 2.6314F, 0.0F, 0.3491F, 0.0F));

        addon.addOrReplaceChild("addons_2", CubeListBuilder.create().texOffs(20, 12).mirror().addBox(-11.0F, -21.0F, 0.0F, 22.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(28, 53).addBox(-0.5F, -18.0F, -0.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(24, 44).addBox(-9.5F, -8.0F, -0.5F, 19.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 0.0F));

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