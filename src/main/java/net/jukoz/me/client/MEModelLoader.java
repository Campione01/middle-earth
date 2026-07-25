package net.jukoz.me.client;

import net.jukoz.me.compat.neoforge.dist.EnvType;
import net.jukoz.me.compat.neoforge.dist.Environment;
import net.jukoz.me.MiddleEarth;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.BlockStateModelLoader;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.ProfilerFiller;
import java.util.List;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class MEModelLoader extends ModelBakery {
    public MEModelLoader(BlockColors blockColors, ProfilerFiller profiler, Map<ResourceLocation, BlockModel> jsonUnbakedModels, Map<ResourceLocation, List<BlockStateModelLoader.LoadedJson>> blockStates) {
        super(blockColors, profiler, jsonUnbakedModels, blockStates);
    }

    public static Material kiteShieldBase(boolean hasPattern) {
        return shieldMaterial(hasPattern ? "entity/kite_shield_base" : "entity/kite_shield_base_nopattern");
    }

    public static Material heaterShieldBase(boolean hasPattern) {
        return shieldMaterial(hasPattern ? "entity/heater_shield_base" : "entity/heater_shield_base_nopattern");
    }

    public static Material roundShieldBase(boolean hasPattern) {
        return shieldMaterial(hasPattern ? "entity/round_shield_base" : "entity/round_shield_base_nopattern");
    }

    private static Material shieldMaterial(String texture) {
        return new Material(Sheets.SHIELD_SHEET, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, texture));
    }
}
