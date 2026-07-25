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

public class IsenUrukHelmetModel<T extends LivingEntity> extends HelmetAddonModel<T> {


    public IsenUrukHelmetModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();

        modelPartData.addOrReplaceChild(PartNames.HAT, CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition head = modelPartData.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create(), PartPose.offset(0.0F, -23.0F, 0.0F));

        PartDefinition addons = head.addOrReplaceChild("addons", CubeListBuilder.create().texOffs(-9, 30).addBox(-7.5F, -0.7F, -0.5F, 15.0F, 0.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(-9, 39).addBox(-6.5F, -1.95F, -6.5F, 13.0F, 0.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(0, -12).addBox(0.0F, -11.5F, -6.0F, 0.0F, 10.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(32, 0).addBox(-4.0F, 1.8F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -1.0F, 0.0F));

        addons.addOrReplaceChild("spikes_0", CubeListBuilder.create().texOffs(40, 50).mirror().addBox(-2.3F, 2.5F, -7.0F, 5.0F, 0.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.75F, -0.65F, -1.0F, 0.0F, 0.0F, -0.5672F));
        addons.addOrReplaceChild("spikes_1", CubeListBuilder.create().texOffs(40, 50).addBox(-2.7F, 2.5F, -7.0F, 5.0F, 0.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.75F, -0.65F, -1.0F, 0.0F, 0.0F, 0.5672F));

        addons.addOrReplaceChild("fan_0", CubeListBuilder.create().texOffs(0, 17).addBox(-13.0F, -6.5F, 0.0F, 26.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.875F, 2.3F, -0.4363F, 0.0F, 0.0F));
        addons.addOrReplaceChild("fan_1", CubeListBuilder.create().texOffs(36, 29).addBox(-7.0F, -6.5F, 0.0F, 14.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, 1.0F, -0.5672F, 0.0F, 0.0F));

        addons.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(0, 13).addBox(-4.0F, -0.3F, -1.5F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.8F, -4.5F, 0.5236F, 0.0F, 0.0F));

        addons.addOrReplaceChild("side_r1", CubeListBuilder.create().texOffs(15, 53).mirror().addBox(-2.6F, 2.0F, -7.0F, 5.0F, 0.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.75F, -1.65F, 1.0F, 0.0F, 0.0F, -0.5672F));
        addons.addOrReplaceChild("side_r2", CubeListBuilder.create().texOffs(15, 53).addBox(-2.4F, 2.0F, -7.0F, 5.0F, 0.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.75F, -1.65F, 1.0F, 0.0F, 0.0F, 0.5672F));

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