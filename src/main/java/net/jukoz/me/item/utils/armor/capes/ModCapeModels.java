package net.jukoz.me.item.utils.armor.capes;

import net.jukoz.me.client.model.equipment.chest.ChestplateAddonModel;
import net.jukoz.me.client.model.equipment.chest.capes.armored.*;
import net.jukoz.me.client.model.equipment.chest.capes.unarmored.*;
import net.minecraft.world.entity.LivingEntity;

public enum ModCapeModels {
    SLIM_MODELS(new CapeSlimModel<>(CapeSlimModel.getTexturedModelData().bakeRoot()), new UnarmoredCapeSlimModel<>(UnarmoredCapeSlimModel.getTexturedModelData().bakeRoot())),
    MEDIUM_MODELS(new CapeMediumModel<>(CapeMediumModel.getTexturedModelData().bakeRoot()), new UnarmoredCapeMediumModel<>(UnarmoredCapeMediumModel.getTexturedModelData().bakeRoot())),
    WIDE_MODELS(new CapeWideModel<>(CapeWideModel.getTexturedModelData().bakeRoot()), new UnarmoredCapeWideModel<>(UnarmoredCapeWideModel.getTexturedModelData().bakeRoot())),
    FUR_MODELS(new FurCapeModel<>(FurCapeModel.getTexturedModelData().bakeRoot()), new UnarmoredFurCapeModel<>(UnarmoredFurCapeModel.getTexturedModelData().bakeRoot())),
    SURCOAT_MODELS(new CapeSurcoatModel<>(CapeSurcoatModel.getTexturedModelData().bakeRoot()), new UnarmoredCapeSurcoatModel<>(UnarmoredCapeSurcoatModel.getTexturedModelData().bakeRoot())),
    SURCOAT_FUR_MODELS(new FurSurcoatModel<>(FurSurcoatModel.getTexturedModelData().bakeRoot()), new UnarmoredFurSurcoatModel<>(UnarmoredFurSurcoatModel.getTexturedModelData().bakeRoot())),
    NAZGUL_ROBES_MODEL(new NazgulRobesModel<>(NazgulRobesModel.getTexturedModelData().bakeRoot()), new NazgulRobesModel<>(NazgulRobesModel.getTexturedModelData().bakeRoot())),
    ;

    private final ChestplateAddonModel<LivingEntity> armoredModel;
    private final ChestplateAddonModel<LivingEntity> unarmoredModel;

    ModCapeModels(ChestplateAddonModel<LivingEntity> armoredModel, ChestplateAddonModel<LivingEntity> unarmoredModel){
        this.armoredModel = armoredModel;
        this.unarmoredModel = unarmoredModel;
    }

    public ChestplateAddonModel<LivingEntity> getArmoredModel() {
        return armoredModel;
    }

    public ChestplateAddonModel<LivingEntity> getUnarmoredModel() {
        return unarmoredModel;
    }
}