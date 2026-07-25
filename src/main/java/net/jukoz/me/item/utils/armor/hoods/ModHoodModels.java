package net.jukoz.me.item.utils.armor.hoods;

import net.jukoz.me.client.model.equipment.head.helmets.HelmetAddonModel;
import net.jukoz.me.client.model.equipment.head.hoods.armored.*;
import net.jukoz.me.client.model.equipment.head.hoods.unarmored.UnarmoredFurHoodDownModel;
import net.jukoz.me.client.model.equipment.head.hoods.unarmored.UnarmoredFurHoodModel;
import net.jukoz.me.client.model.equipment.head.hoods.unarmored.UnarmoredHoodModel;
import net.jukoz.me.client.model.equipment.head.hoods.unarmored.UnarmoredTallHoodModel;
import net.minecraft.world.entity.LivingEntity;

public enum ModHoodModels {
    REGULAR_MODELS(new HoodModel<>(HoodModel.getTexturedModelData().bakeRoot()), new HoodModel<>(HoodModel.getTexturedModelData().bakeRoot()),
            new UnarmoredHoodModel<>(UnarmoredHoodModel.getTexturedModelData().bakeRoot()), new UnarmoredHoodModel<>(UnarmoredHoodModel.getTexturedModelData().bakeRoot())),
    TALL_MODELS(new TallHoodModel<>(TallHoodModel.getTexturedModelData().bakeRoot()), new TallHoodModel<>(TallHoodModel.getTexturedModelData().bakeRoot()),
            new UnarmoredTallHoodModel<>(UnarmoredTallHoodModel.getTexturedModelData().bakeRoot()), new UnarmoredTallHoodModel<>(UnarmoredTallHoodModel.getTexturedModelData().bakeRoot())),
    FUR_MODELS(new FurHoodModel<>(FurHoodModel.getTexturedModelData().bakeRoot()), new FurHoodDownModel<>(FurHoodDownModel.getTexturedModelData().bakeRoot()),
            new UnarmoredFurHoodModel<>(UnarmoredFurHoodModel.getTexturedModelData().bakeRoot()), new UnarmoredFurHoodDownModel<>(UnarmoredFurHoodDownModel.getTexturedModelData().bakeRoot())),
    NAZGUL_MODELS(new NazgulHoodModel<>(NazgulHoodModel.getTexturedModelData().bakeRoot()), new NazgulHoodModel<>(NazgulHoodModel.getTexturedModelData().bakeRoot()),
            new NazgulHoodModel<>(NazgulHoodModel.getTexturedModelData().bakeRoot()), new NazgulHoodModel<>(NazgulHoodModel.getTexturedModelData().bakeRoot())),
    ;

    private final HelmetAddonModel<LivingEntity> armoredModel;
    private final HelmetAddonModel<LivingEntity> armoredDownModel;
    private final HelmetAddonModel<LivingEntity> unarmoredModel;
    private final HelmetAddonModel<LivingEntity> unarmoredDownModel;

    ModHoodModels(HelmetAddonModel<LivingEntity> armoredModel, HelmetAddonModel<LivingEntity> armoredDownModel,
                  HelmetAddonModel<LivingEntity> unarmoredModel, HelmetAddonModel<LivingEntity> unarmoredDownModel){
        this.armoredModel = armoredModel;
        this.armoredDownModel = armoredDownModel;
        this.unarmoredModel = unarmoredModel;
        this.unarmoredDownModel = unarmoredDownModel;
    }

    public HelmetAddonModel<LivingEntity> getArmoredModel() {
        return armoredModel;
    }

    public HelmetAddonModel<LivingEntity> getArmoredDownModel() {
        return armoredDownModel;
    }

    public HelmetAddonModel<LivingEntity> getUnarmoredModel() {
        return unarmoredModel;
    }

    public HelmetAddonModel<LivingEntity> getUnarmoredDownModel() {
        return unarmoredDownModel;
    }
}
