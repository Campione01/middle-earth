package net.jukoz.me.datageneration;

import net.jukoz.me.resources.MiddleEarthFactions;
import net.jukoz.me.resources.MiddleEarthNpcs;
import net.jukoz.me.resources.MiddleEarthRaces;
import net.jukoz.me.item.utils.ModSmithingTrimMaterials;
import net.jukoz.me.item.utils.ModSmithingTrimPatterns;
import net.jukoz.me.world.biomes.surface.ModBiomes;
import net.jukoz.me.world.biomes.caves.ModCaveBiomes;
import net.jukoz.me.world.features.boulder.BoulderConfiguredFeatures;
import net.jukoz.me.world.features.boulder.BoulderPlacedFeatures;
import net.jukoz.me.world.features.misc.ModMiscConfiguredFeatures;
import net.jukoz.me.world.features.misc.ModMiscPlacedFeatures;
import net.jukoz.me.world.features.ores.OreConfiguredFeatures;
import net.jukoz.me.world.features.ores.OrePlacedFeatures;
import net.jukoz.me.world.features.tree.ModTreeConfiguredFeatures;
import net.jukoz.me.world.features.tree.ModTreePlacedFeatures;
import net.jukoz.me.world.features.tree.MushroomTreeConfiguredFeatures;
import net.jukoz.me.world.features.underground.CavesConfiguredFeatures;
import net.jukoz.me.world.features.underground.CavesPlacedFeatures;
import net.jukoz.me.world.features.vegetation.ModVegetationConfiguredFeatures;
import net.jukoz.me.world.features.vegetation.ModVegetationPlacedFeatures;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class DataGeneration {
    public static boolean isDataGen = false;

    public static RegistrySetBuilder createRegistrySetBuilder() {
        return buildRegistryEntries(new RegistrySetBuilder());
    }

    public static RegistrySetBuilder buildRegistryEntries(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.BIOME, DataGeneration::bootstrapBiomes);
        registryBuilder.add(Registries.CONFIGURED_FEATURE, DataGeneration::bootstrapConfiguredFeatures);
        registryBuilder.add(Registries.PLACED_FEATURE, DataGeneration::bootstrapPlacedFeatures);
        registryBuilder.add(Registries.TRIM_MATERIAL, ModSmithingTrimMaterials::bootstrap);
        registryBuilder.add(Registries.TRIM_PATTERN, ModSmithingTrimPatterns::bootstrap);
        // Dynamic
        registryBuilder.add(MiddleEarthRaces.RACE_KEY, MiddleEarthRaces::bootstrap);
        registryBuilder.add(MiddleEarthNpcs.NPC_KEY, MiddleEarthNpcs::bootstrap);
        registryBuilder.add(MiddleEarthFactions.FACTION_KEY, MiddleEarthFactions::bootstrap);
        return registryBuilder;
    }

    private static void bootstrapBiomes(BootstrapContext<Biome> context) {
        ModBiomes.bootstrap(context);
        ModCaveBiomes.bootstrap(context);
    }

    private static void bootstrapConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        ModTreeConfiguredFeatures.bootstrap(context);
        ModVegetationConfiguredFeatures.bootstrap(context);
        BoulderConfiguredFeatures.bootstrap(context);
        OreConfiguredFeatures.bootstrap(context);
        CavesConfiguredFeatures.bootstrap(context);
        ModMiscConfiguredFeatures.bootstrap(context);
        MushroomTreeConfiguredFeatures.bootstrap(context);
    }

    private static void bootstrapPlacedFeatures(BootstrapContext<PlacedFeature> context) {
        ModTreePlacedFeatures.bootstrap(context);
        ModVegetationPlacedFeatures.bootstrap(context);
        BoulderPlacedFeatures.bootstrap(context);
        OrePlacedFeatures.bootstrap(context);
        CavesPlacedFeatures.bootstrap(context);
        ModMiscPlacedFeatures.bootstrap(context);
    }
}
