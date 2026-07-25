package net.jukoz.me.world.features.misc;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.ModBlocks;
import net.jukoz.me.block.ModNatureBlocks;
import net.jukoz.me.block.StoneBlockSets;
import net.jukoz.me.world.features.columns.ColumnsFeatureConfig;
import net.jukoz.me.world.gen.ModFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ModMiscConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAVA_MAGMA_POOL = registerKey("lava_magma_pool");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_PUMICE_COLUMNS = registerKey("small_pumice_columns");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_PUMICE_COLUMNS = registerKey("large_pumice_columns");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> featureRegisterable) {
        FeatureUtils.register(featureRegisterable, LAVA_MAGMA_POOL, Feature.LAKE,
                new LakeFeature.Configuration(BlockStateProvider.simple(Blocks.LAVA.defaultBlockState()), BlockStateProvider.simple(Blocks.MAGMA_BLOCK.defaultBlockState())));

        FeatureUtils.register(featureRegisterable, SMALL_PUMICE_COLUMNS, ModFeatures.COLUMNS,
                new ColumnsFeatureConfig(ConstantInt.of(1), UniformInt.of(1, 4), StoneBlockSets.PUMICE.base().defaultBlockState()));
        FeatureUtils.register(featureRegisterable, LARGE_PUMICE_COLUMNS, ModFeatures.COLUMNS,
                new ColumnsFeatureConfig(UniformInt.of(2, 3), UniformInt.of(4, 7), StoneBlockSets.PUMICE.base().defaultBlockState()));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, name));
    }
}
