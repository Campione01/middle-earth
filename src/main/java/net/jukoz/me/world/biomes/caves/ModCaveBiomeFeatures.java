package net.jukoz.me.world.biomes.caves;

import net.jukoz.me.entity.ModEntities;
import net.jukoz.me.world.features.underground.CavesPlacedFeatures;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModCaveBiomeFeatures {

    public static void addAmethystGeode(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, CavesPlacedFeatures.AMETHYST_GEODE);
    }
    public static void addCitrineGeode(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, CavesPlacedFeatures.CITRINE_GEODE);
    }
    public static void addGlowstoneGeode(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, CavesPlacedFeatures.GLOWSTONE_GEODE);
    }
    public static void addRedAgateGeode(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, CavesPlacedFeatures.RED_AGATE_GEODE);
    }
    public static void addQuartzGeode(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, CavesPlacedFeatures.QUARTZ_GEODE);
    }

    public static void addAxolotls(MobSpawnSettings.Builder spawnSettings) {
        spawnSettings.addSpawn(MobCategory.AXOLOTLS, new MobSpawnSettings.SpawnerData(EntityType.AXOLOTL, 12, 2, 5));
    }
    public static void addBats(MobSpawnSettings.Builder spawnSettings) {
        spawnSettings.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.BAT, 3, 1, 2));
    }
    public static void addFrogs(MobSpawnSettings.Builder spawnSettings) {
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FROG, 4, 1, 4));
    }
    public static void addSnails(MobSpawnSettings.Builder spawnSettings) {
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.SNAIL, 5, 1, 3));
    }
    public static void addWildGoblins(MobSpawnSettings.Builder spawnSettings) {
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.WILD_GOBLIN, 5, 1, 2));
    }
    public static void addSpiders(MobSpawnSettings.Builder spawnSettings) {
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.CAVE_SPIDER, 2, 1, 2));
    }

    public static void addSnowTrolls(MobSpawnSettings.Builder spawnSettings) {
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.SNOW_TROLL, 4, 1, 2));
    }

}
