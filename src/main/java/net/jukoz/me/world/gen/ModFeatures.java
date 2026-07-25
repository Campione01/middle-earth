package net.jukoz.me.world.gen;

import net.jukoz.me.utils.NeoForgeRegistrationBridge;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.utils.LoggerUtil;
import net.jukoz.me.world.features.boulder.BigBoulderFeature;
import net.jukoz.me.world.features.boulder.BigBoulderFeatureConfig;
import net.jukoz.me.world.features.columns.*;
import net.jukoz.me.world.features.deltas.ModDeltaFeatures;
import net.jukoz.me.world.features.ores.ModOreFeature;
import net.jukoz.me.world.features.ores.ModOreFeatureConfig;
import net.jukoz.me.world.features.ores.SurfaceOreFeature;
import net.jukoz.me.world.features.pillar.PillarFeature;
import net.jukoz.me.world.features.pillar.PillarFeatureConfig;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DeltaFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

public class ModFeatures {
    public static Feature<DeltaFeatureConfiguration> DELTA_FEATURE = register("delta_feature", new ModDeltaFeatures(DeltaFeatureConfiguration.CODEC));

    public static Feature<OreConfiguration> SURFACE_ORE = register("surface_ore", new SurfaceOreFeature(OreConfiguration.CODEC));
    public static Feature<ClusterFeatureConfig> CLUSTER = register("cluster", new ClusterFeature(ClusterFeatureConfig.CODEC));
    public static Feature<SmallPointedStoneFeatureConfig> SMALL_POINTED_STONE = register("small_pointed_stone", new SmallPointedStoneFeature(SmallPointedStoneFeatureConfig.CODEC));
    public static Feature<PillarFeatureConfig> PILLAR = register("pillar", new PillarFeature(PillarFeatureConfig.CODEC));
    public static Feature<CaveColumnFeatureConfig> CAVE_COLUMN = register("cave_columns", new CaveColumnFeature(CaveColumnFeatureConfig.CODEC));
    public static Feature<ColumnsFeatureConfig> COLUMNS = register("columns", new ColumnsFeature(ColumnsFeatureConfig.CODEC));
    public static Feature<BigBoulderFeatureConfig> BIG_BOULDER = register("big_boulder", new BigBoulderFeature(BigBoulderFeatureConfig.CODEC));
    public static Feature<ModOreFeatureConfig> ORE = register("ore", new ModOreFeature(ModOreFeatureConfig.CODEC));
    public static final Feature<NoneFeatureConfiguration> MIRKWOOD_VINE = register("mirkwood_vine", new MirkwoodVinesFeature(NoneFeatureConfiguration.CODEC));

    public static void init() {
        LoggerUtil.logInfoMsg("Registering new features");
    }

    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(String name, F feature) {
        return (F) NeoForgeRegistrationBridge.register(BuiltInRegistries.FEATURE, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, name), feature);
    }
}
