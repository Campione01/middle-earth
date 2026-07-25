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

public class GondorianHelmetModel<T extends LivingEntity> extends HelmetAddonModel<T> {

    public final ModelPart addons;

    public GondorianHelmetModel(ModelPart root) {
        super(root);
        addons = root.getChild("head").getChild("addons");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();

        modelPartData.addOrReplaceChild(PartNames.HAT, CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = modelPartData.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition addons = head.addOrReplaceChild("addons", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -11.0F, -3.5F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 10).addBox(-1.5F, -12.5F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(29, 0).addBox(-3.0F, -15.6F, -4.35F, 6.0F, 12.0F, 0.0F, new CubeDeformation(0.5F))
                .texOffs(29, 12).addBox(-3.0F, -15.6F, 5.15F, 6.0F, 12.0F, 0.0F, new CubeDeformation(0.5F))
                .texOffs(0, 15).mirror().addBox(-4.0F, -8.0F, -4.314F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(50, 0).mirror().addBox(2.0F, -19.8F, 0.0F, 7.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(50, 16).addBox(-9.0F, -19.8F, 0.0F, 7.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 0.0F));

        addons.addOrReplaceChild("side_right_0", CubeListBuilder.create().texOffs(40, 49).addBox(-6.0F, -9.5F, 0.528F, 6.0F, 15.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -6.5F, -0.5F, 0.0F, 1.309F, 0.0F));
        addons.addOrReplaceChild("side_right_1", CubeListBuilder.create().texOffs(52, 32).addBox(-6.0F, -9.5F, 0.502F, 6.0F, 15.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -6.5F, -2.5F, 0.0F, 1.3963F, 0.0F));

        addons.addOrReplaceChild("side_left_0", CubeListBuilder.create().texOffs(52, 49).addBox(0.0F, -9.5F, 0.492F, 6.0F, 15.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -6.5F, -0.5F, 0.0F, -1.309F, 0.0F));
        addons.addOrReplaceChild("side_left_1", CubeListBuilder.create().texOffs(40, 32).addBox(0.0F, -9.5F, 0.534F, 6.0F, 15.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -6.5F, -2.5F, 0.0F, -1.3963F, 0.0F));


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