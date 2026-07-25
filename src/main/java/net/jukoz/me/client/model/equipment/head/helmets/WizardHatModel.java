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

public class WizardHatModel<T extends LivingEntity> extends HelmetAddonModel<T> {

    public WizardHatModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();

        modelPartData.addOrReplaceChild(PartNames.HAT, CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = modelPartData.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition wizard = head.addOrReplaceChild("wizard", CubeListBuilder.create().texOffs(0, 47).addBox(-8.0F, -6.134F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.4F, -1.0F, -0.1745F, 0.0F, 0.0F));

        PartDefinition bone = wizard.addOrReplaceChild("bone", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.6F))
                .texOffs(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 4.0F, 8.0F, new CubeDeformation(1.0F)),
                PartPose.offsetAndRotation(0.0F, -0.325F, 0.2F, 0.0436F, 0.0F, 0.0F));

        PartDefinition hat = bone.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.6F, -0.4F, -0.0175F, 0.0F, 0.0F));

        hat.addOrReplaceChild("wizard_0", CubeListBuilder.create()
                .texOffs(24, 1).mirror().addBox(2.0F, -10.4886F, -2.2615F, 0.0F, 10.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(46, 12).addBox(0.0F, -5.4886F, -2.2615F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.5F)),
                PartPose.offsetAndRotation(-2.0F, -13.0143F, 1.5539F, -0.5672F, 0.0F, 0.0F));
        hat.addOrReplaceChild("wizard_1", CubeListBuilder.create()
                .texOffs(0, 12).addBox(-3.0F, -5.5F, -3.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.5F)),
                PartPose.offsetAndRotation(0.0F, -9.5F, 0.0F, -0.0873F, 0.0F, 0.0F));

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