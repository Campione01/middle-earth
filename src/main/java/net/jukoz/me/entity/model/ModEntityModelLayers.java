package net.jukoz.me.entity.model;

import net.jukoz.me.compat.neoforge.dist.EnvType;
import net.jukoz.me.compat.neoforge.dist.Environment;
import net.jukoz.me.compat.neoforge.api.client.rendering.v1.EntityModelLayerRegistry;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.special.bellows.BellowsBlockEntityRenderer;
import net.jukoz.me.block.special.reinforcedChest.ReinforcedChestEntityRenderer;
import net.jukoz.me.entity.barrow_wights.BarrowWightModel;
import net.jukoz.me.entity.beasts.broadhoof.BroadhoofGoatModel;
import net.jukoz.me.entity.beasts.broadhoof.features.BroadhoofGoatArmorModel;
import net.jukoz.me.entity.beasts.broadhoof.features.BroadhoofGoatSaddleModel;
import net.jukoz.me.entity.beasts.trolls.petrified.PetrifiedTrollModel;
import net.jukoz.me.entity.beasts.trolls.stone.StoneTrollModel;
import net.jukoz.me.entity.beasts.warg.WargModel;
import net.jukoz.me.entity.beasts.warg.features.*;
import net.jukoz.me.entity.deer.DeerModel;
import net.jukoz.me.entity.pheasant.PheasantModel;
import net.jukoz.me.entity.snail.SnailModel;
import net.jukoz.me.entity.spider.MirkwoodSpiderModel;
import net.jukoz.me.entity.swan.SwanModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.jukoz.me.entity.beasts.trolls.snow.SnowTrollModel;

@Environment(value= EnvType.CLIENT)
public final class ModEntityModelLayers {
    private static final String MAIN = "main";
    public static final ModelLayerLocation DWARF = ModEntityModelLayers.registerEntityModelLayer("dwarf", DwarfModel.getTexturedModelData(CubeDeformation.NONE));
    public static final ModelLayerLocation HUMAN = ModEntityModelLayers.registerEntityModelLayer("human", HumanModel.getTexturedModelData(CubeDeformation.NONE));
    public static final ModelLayerLocation ELF = ModEntityModelLayers.registerEntityModelLayer("elf", ElfModel.getTexturedModelData(CubeDeformation.NONE));
    public static final ModelLayerLocation HOBBIT = ModEntityModelLayers.registerEntityModelLayer("hobbit", HobbitModel.getTexturedModelData(CubeDeformation.NONE));
    public static final ModelLayerLocation ORC = ModEntityModelLayers.registerEntityModelLayer("orc", OrcModel.getTexturedModelData(CubeDeformation.NONE));
    public static final ModelLayerLocation URUK = ModEntityModelLayers.registerEntityModelLayer("uruk", UrukModel.getTexturedModelData(CubeDeformation.NONE));
    public static final ModelLayerLocation BROADHOOF_GOAT = ModEntityModelLayers.registerEntityModelLayer("broadhoof_goat", BroadhoofGoatModel.getTexturedModelData());
    public static final ModelLayerLocation BROADHOOF_GOAT_ARMOR = ModEntityModelLayers.registerEntityModelLayer("broadhoof_goat_armor", BroadhoofGoatArmorModel.getTexturedModelData());
    public static final ModelLayerLocation BROADHOOF_GOAT_SADDLE = ModEntityModelLayers.registerEntityModelLayer("broadhoof_goat_saddle", BroadhoofGoatSaddleModel.getTexturedModelData());
    public static final ModelLayerLocation WARG = ModEntityModelLayers.registerEntityModelLayer("warg", WargModel.getTexturedModelData());
    public static final ModelLayerLocation WARG_ARMOR = ModEntityModelLayers.registerEntityModelLayer("warg_armor", WargArmorModel.getTexturedModelData());
    public static final ModelLayerLocation WARG_ARMOR_ADDONS_FRONT = ModEntityModelLayers.registerEntityModelLayer("warg_armor_addons_front", WargArmorTopAddonsModel.getTexturedModelDataFront());
    public static final ModelLayerLocation WARG_ARMOR_ADDONS_BACK = ModEntityModelLayers.registerEntityModelLayer("warg_armor_addons_back", WargArmorTopAddonsModel.getTexturedModelDataBack());
    public static final ModelLayerLocation WARG_ARMOR_ADDONS_SPINE = ModEntityModelLayers.registerEntityModelLayer("warg_armor_addons_spine", WargArmorBaseAddonsModel.getTexturedModelDataSpine());
    public static final ModelLayerLocation WARG_ARMOR_ADDONS_SIDE_SKULL = ModEntityModelLayers.registerEntityModelLayer("warg_armor_addons_side_skull", WargArmorSideAddonsModel.getTexturedModelDataSideSkulls());
    public static final ModelLayerLocation WARG_SADDLE = ModEntityModelLayers.registerEntityModelLayer("warg_saddle", WargSaddleModel.getTexturedModelData());
    public static final ModelLayerLocation SNOW_TROLL = ModEntityModelLayers.registerEntityModelLayer("snow_troll", SnowTrollModel.getTexturedModelData());
    public static final ModelLayerLocation STONE_TROLL = ModEntityModelLayers.registerEntityModelLayer("stone_troll", StoneTrollModel.getTexturedModelData());
    public static final ModelLayerLocation PETRIFIED_TROLL = ModEntityModelLayers.registerEntityModelLayer("petrified_troll", PetrifiedTrollModel.getTexturedModelData());
    public static final ModelLayerLocation BARROW_WIGHT = ModEntityModelLayers.registerEntityModelLayer("barrow_wight", BarrowWightModel.getTexturedModelData());
    public static final ModelLayerLocation SPIDER = ModEntityModelLayers.registerEntityModelLayer("spider", MirkwoodSpiderModel.getTexturedModelData());

    // Animals
    public static final ModelLayerLocation SWAN = ModEntityModelLayers.registerEntityModelLayer("swan", SwanModel.getTexturedModelData());
    public static final ModelLayerLocation PHEASANT = ModEntityModelLayers.registerEntityModelLayer("pheasant", PheasantModel.getTexturedModelData());
    public static final ModelLayerLocation SNAIL = ModEntityModelLayers.registerEntityModelLayer("snail", SnailModel.getTexturedModelData());
    public static final ModelLayerLocation DEER = ModEntityModelLayers.registerEntityModelLayer("deer", DeerModel.getTexturedModelData());

    public static final ModelLayerLocation BELLOWS = ModEntityModelLayers.registerEntityModelLayer("bellows", BellowsBlockEntityRenderer.getTexturedModelData());
    public static final ModelLayerLocation REINFORCED_CHEST = ModEntityModelLayers.registerEntityModelLayer("reinforced_chest", ReinforcedChestEntityRenderer.getTexturedModelData());


    /**
     * The modelData is used to know the UV map to use for the 3D model
     * **/
    private static ModelLayerLocation registerEntityModelLayer(String registryName, LayerDefinition modelData) {
        ModelLayerLocation entityModelLayer = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, registryName), MAIN);
        EntityModelLayerRegistry.registerModelLayer(entityModelLayer, () -> modelData);
        return entityModelLayer;
    }
}
