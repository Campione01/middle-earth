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

public class OrthancCommanderHelmetModel<T extends LivingEntity> extends HelmetAddonModel<T> {

    public final ModelPart addons;

    public OrthancCommanderHelmetModel(ModelPart root) {
        super(root);
        addons = root.getChild("head").getChild("addons");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();

        modelPartData.addOrReplaceChild(PartNames.HAT, CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = modelPartData.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        head.addOrReplaceChild("addons", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -12.0F, -3.5F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 10).addBox(-1.5F, -13.5F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(46, 63).addBox(-4.5F, -4.8F, -4.475F, 9.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(1, 16).addBox(-11.0F, -19.875F, -0.7F, 22.0F, 12.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(-9, 30).addBox(-7.5F, -3.1F, -1.0F, 15.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.1F, 0.0F));

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