package net.jukoz.me.entity.model;

import com.google.common.collect.ImmutableMap;
import net.jukoz.me.compat.neoforge.dist.EnvType;
import net.jukoz.me.compat.neoforge.dist.Environment;
import net.jukoz.me.entity.beasts.warg.WargModel;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.MiddleEarthClient;
import net.jukoz.me.block.special.bellows.BellowsBlockEntityRenderer;
import net.jukoz.me.block.special.reinforcedChest.ReinforcedChestEntityRenderer;
import net.jukoz.me.entity.snail.SnailModel;
import net.jukoz.me.entity.spider.MirkwoodSpiderModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.jukoz.me.entity.beasts.trolls.snow.SnowTrollModel;
import java.util.Map;

@Environment(value= EnvType.CLIENT)
public class ModEntityModels {
    public static Map<ModelLayerLocation, LayerDefinition> getModels() {
        ImmutableMap.Builder<ModelLayerLocation, LayerDefinition> builder = ImmutableMap.builder();

        builder.put(ModEntityModelLayers.HUMAN, HumanModel.getTexturedModelData(CubeDeformation.NONE));
        builder.put(ModEntityModelLayers.DWARF, DwarfModel.getTexturedModelData(CubeDeformation.NONE));
        builder.put(ModEntityModelLayers.ELF, ElfModel.getTexturedModelData(CubeDeformation.NONE));
        builder.put(ModEntityModelLayers.HOBBIT, HobbitModel.getTexturedModelData(CubeDeformation.NONE));
        builder.put(ModEntityModelLayers.ORC, OrcModel.getTexturedModelData(CubeDeformation.NONE));
        builder.put(ModEntityModelLayers.URUK, UrukModel.getTexturedModelData(CubeDeformation.NONE));
        builder.put(ModEntityModelLayers.SNOW_TROLL, SnowTrollModel.getTexturedModelData());
        builder.put(ModEntityModelLayers.SPIDER, MirkwoodSpiderModel.getTexturedModelData());

        builder.put(ModEntityModelLayers.REINFORCED_CHEST, ReinforcedChestEntityRenderer.createSingleBodyLayer());
        builder.put(ModEntityModelLayers.BELLOWS, BellowsBlockEntityRenderer.getTexturedModelData());

        // Animals
        builder.put(ModEntityModelLayers.SNAIL, SnailModel.getTexturedModelData());

        ImmutableMap<ModelLayerLocation, LayerDefinition> immutableMap = builder.build();
        return immutableMap;
    }
}
