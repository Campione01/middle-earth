package net.jukoz.me.world.features.boulder;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.ModBlocks;
import net.jukoz.me.block.ModNatureBlocks;
import net.jukoz.me.block.StoneBlockSets;
import net.jukoz.me.world.gen.ModFeatures;
import net.minecraft.world.level.block.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockPileConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import java.util.Arrays;
import java.util.List;

public class BoulderConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ANDESITE_BOULDER = registerKey("andesite_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CALCITE_BOULDER = registerKey("calcite_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DIORITE_BOULDER = registerKey("diorite_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRANITE_BOULDER = registerKey("granite_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LIMESTONE_BOULDER = registerKey("limestone_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SANDSTONE_BOULDER = registerKey("sandstone_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STONE_BOULDER = registerKey("stone_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIRKWOOD_ROOTS_BOULDER = registerKey("mirkwood_roots_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOSSY_BOULDER = registerKey("mossy_boulder");


    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BOULDER_ANDESITE = registerKey("small_boulder_andesite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEDIUM_BOULDER_ANDESITE = registerKey("medium_boulder_andesite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_BOULDER_ANDESITE = registerKey("big_boulder_andesite");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BOULDER_ASHEN_STONE = registerKey("small_boulder_ashen_stone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEDIUM_BOULDER_ASHEN_STONE = registerKey("medium_boulder_ashen_stone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_BOULDER_ASHEN_STONE = registerKey("big_boulder_ashen_stone");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BOULDER_BASALT = registerKey("small_boulder_basalt");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEDIUM_BOULDER_BASALT = registerKey("medium_boulder_basalt");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_BOULDER_BASALT = registerKey("big_boulder_basalt");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BOULDER_BLUE_TUFF = registerKey("small_boulder_blue_tuff");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEDIUM_BOULDER_BLUE_TUFF = registerKey("medium_boulder_blue_tuff");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_BOULDER_BLUE_TUFF = registerKey("big_boulder_blue_tuff");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BOULDER_CALCITE = registerKey("small_boulder_calcite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEDIUM_BOULDER_CALCITE = registerKey("medium_boulder_calcite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_BOULDER_CALCITE = registerKey("big_boulder_calcite");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BOULDER_DIORITE = registerKey("small_boulder_diorite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEDIUM_BOULDER_DIORITE = registerKey("medium_boulder_diorite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_BOULDER_DIORITE = registerKey("big_boulder_diorite");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BOULDER_DOLOMITE = registerKey("small_boulder_dolomite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEDIUM_BOULDER_DOLOMITE = registerKey("medium_boulder_dolomite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_BOULDER_DOLOMITE = registerKey("big_boulder_dolomite");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BOULDER_SMOOTH_DOLOMITE = registerKey("small_boulder_smooth_dolomite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEDIUM_BOULDER_SMOOTH_DOLOMITE = registerKey("medium_boulder_smooth_dolomite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_BOULDER_SMOOTH_DOLOMITE = registerKey("big_boulder_smooth_dolomite");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BOULDER_GALONN = registerKey("small_boulder_galonn");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEDIUM_BOULDER_GALONN = registerKey("medium_boulder_galonn");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_BOULDER_GALONN = registerKey("big_boulder_galonn");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BOULDER_GNEISS = registerKey("small_boulder_gneiss");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEDIUM_BOULDER_GNEISS = registerKey("medium_boulder_gneiss");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_BOULDER_GNEISS = registerKey("big_boulder_gneiss");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BOULDER_GRANITE = registerKey("small_boulder_granite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEDIUM_BOULDER_GRANITE = registerKey("medium_boulder_granite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_BOULDER_GRANITE = registerKey("big_boulder_granite");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BOULDER_HEMATITE = registerKey("small_boulder_hematite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEDIUM_BOULDER_HEMATITE = registerKey("medium_boulder_hematite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_BOULDER_HEMATITE = registerKey("big_boulder_hematite");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BOULDER_IRONSTONE = registerKey("small_boulder_ironstone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEDIUM_BOULDER_IRONSTONE = registerKey("medium_boulder_ironstone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_BOULDER_IRONSTONE = registerKey("big_boulder_ironstone");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BOULDER_LIMESTONE = registerKey("small_boulder_limestone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEDIUM_BOULDER_LIMESTONE = registerKey("medium_boulder_limestone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_BOULDER_LIMESTONE = registerKey("big_boulder_limestone");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BOULDER_MOSSY_STONE = registerKey("small_boulder_mossy_stone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEDIUM_BOULDER_MOSSY_STONE = registerKey("medium_boulder_mossy_stone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_BOULDER_MOSSY_STONE = registerKey("big_boulder_mossy_stone");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BOULDER_SANDSTONE = registerKey("small_boulder_sandstone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEDIUM_BOULDER_SANDSTONE = registerKey("medium_boulder_sandstone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_BOULDER_SANDSTONE = registerKey("big_boulder_mossy_sandstone");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BOULDER_STONE = registerKey("small_boulder_stone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEDIUM_BOULDER_STONE = registerKey("medium_boulder_stone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_BOULDER_STONE = registerKey("big_boulder_vstone");

    // region Configs
    private static final float smallWidth = 1.5f;
    private static final float smallLength = 2.1f;
    private static final float smallHeight = 1.6f;
    private static final float smallRandomSize = 0.6f;
    private static final float smallRandomness = 0.21f;

    private static final float mediumWidth = 2.6f;
    private static final float mediumLength = 4.0f;
    private static final float mediumHeight = 2.8f;
    private static final float mediumRandomSize = 0.95f;
    private static final float mediumRandomness = 0.26f;

    private static final float bigWidth = 4.1f;
    private static final float bigLength = 5.85f;
    private static final float bigHeight = 4.35f;
    private static final float bigRandomSize = 1.7f;
    private static final float bigRandomness = 0.34f;
    // endregion

    private static final List<BlockState> andesite = Arrays.asList(
            Blocks.ANDESITE.defaultBlockState(),
            Blocks.ANDESITE.defaultBlockState(),
            StoneBlockSets.COBBLED_ANDESITE.base().defaultBlockState(),
            StoneBlockSets.MOSSY_COBBLED_ANDESITE.base().defaultBlockState());

    private static final List<BlockState> ashenStone = Arrays.asList(
            StoneBlockSets.ASHEN_STONE.base().defaultBlockState(),
            StoneBlockSets.ASHEN_STONE.base().defaultBlockState(),
            StoneBlockSets.ASHEN_COBBLESTONE.base().defaultBlockState(),
            ModBlocks.ASHEN_GRAVEL.defaultBlockState(),
            ModBlocks.ASH_BLOCK.defaultBlockState());

    private static final List<BlockState> basalt = Arrays.asList(
            Blocks.BASALT.defaultBlockState(),
            Blocks.SMOOTH_BASALT.defaultBlockState());

    private static final List<BlockState> blueTuff = Arrays.asList(
            StoneBlockSets.BLUE_TUFF.base().defaultBlockState(),
            StoneBlockSets.BLUE_TUFF.base().defaultBlockState(),
            StoneBlockSets.COBBLED_BLUE_TUFF.base().defaultBlockState(),
            StoneBlockSets.MOSSY_COBBLED_BLUE_TUFF.base().defaultBlockState());

    private static final List<BlockState> calcite = Arrays.asList(
            Blocks.CALCITE.defaultBlockState(),
            Blocks.CALCITE.defaultBlockState(),
            StoneBlockSets.COBBLED_CALCITE.base().defaultBlockState(),
            StoneBlockSets.MOSSY_COBBLED_CALCITE.base().defaultBlockState());

    private static final List<BlockState> diorite = Arrays.asList(
            Blocks.DIORITE.defaultBlockState(),
            Blocks.DIORITE.defaultBlockState(),
            StoneBlockSets.COBBLED_DIORITE.base().defaultBlockState(),
            StoneBlockSets.MOSSY_COBBLED_DIORITE.base().defaultBlockState());

    private static final List<BlockState> dolomite = Arrays.asList(
            StoneBlockSets.DOLOMITE.base().defaultBlockState(),
            StoneBlockSets.DOLOMITE.base().defaultBlockState(),
            StoneBlockSets.COBBLED_DOLOMITE.base().defaultBlockState(),
            StoneBlockSets.MOSSY_COBBLED_DOLOMITE.base().defaultBlockState());

    private static final List<BlockState> smoothDolomite = Arrays.asList(
            StoneBlockSets.SMOOTH_DOLOMITE.base().defaultBlockState(),
            StoneBlockSets.SMOOTH_DOLOMITE.base().defaultBlockState(),
            StoneBlockSets.CRACKED_SMOOTH_DOLOMITE.base().defaultBlockState(),
            StoneBlockSets.MOSSY_SMOOTH_DOLOMITE.base().defaultBlockState());

    private static final List<BlockState> galonn = Arrays.asList(
            StoneBlockSets.GALONN.base().defaultBlockState(),
            StoneBlockSets.GALONN.base().defaultBlockState(),
            StoneBlockSets.CRACKED_SMOOTH_GALONN.base().defaultBlockState(),
            StoneBlockSets.MOSSY_SMOOTH_GALONN.base().defaultBlockState());

    private static final List<BlockState> gneiss = Arrays.asList(
            StoneBlockSets.GNEISS.base().defaultBlockState(),
            StoneBlockSets.GNEISS.base().defaultBlockState(),
            StoneBlockSets.SMOOTH_GNEISS.base().defaultBlockState());

    private static final List<BlockState> granite = Arrays.asList(
            Blocks.GRANITE.defaultBlockState(),
            Blocks.GRANITE.defaultBlockState(),
            StoneBlockSets.COBBLED_GRANITE.base().defaultBlockState(),
            StoneBlockSets.MOSSY_COBBLED_GRANITE.base().defaultBlockState());

    private static final List<BlockState> hematite = Arrays.asList(
            StoneBlockSets.HEMATITE.base().defaultBlockState(),
            StoneBlockSets.HEMATITE.base().defaultBlockState(),
            StoneBlockSets.COBBLED_HEMATITE.base().defaultBlockState());

    private static final List<BlockState> ironStone = Arrays.asList(
            StoneBlockSets.IRONSTONE.base().defaultBlockState(),
            StoneBlockSets.IRONSTONE.base().defaultBlockState(),
            StoneBlockSets.COBBLED_IRONSTONE.base().defaultBlockState());

    private static final List<BlockState> limeStone = Arrays.asList(
            StoneBlockSets.LIMESTONE.base().defaultBlockState(),
            StoneBlockSets.LIMESTONE.base().defaultBlockState(),
            StoneBlockSets.CRACKED_SMOOTH_LIMESTONE.base().defaultBlockState(),
            StoneBlockSets.MOSSY_SMOOTH_LIMESTONE.base().defaultBlockState());

    private static final List<BlockState> mossyStone = Arrays.asList(
            Blocks.STONE.defaultBlockState(),
            Blocks.COBBLESTONE.defaultBlockState(),
            Blocks.MOSS_BLOCK.defaultBlockState(),
            Blocks.MOSSY_COBBLESTONE.defaultBlockState());

    private static final List<BlockState> sandStone = Arrays.asList(
            Blocks.SANDSTONE.defaultBlockState(),
            Blocks.SMOOTH_SANDSTONE.defaultBlockState(),
            Blocks.SAND.defaultBlockState());

    private static final List<BlockState> stone = Arrays.asList(
            Blocks.STONE.defaultBlockState(),
            Blocks.COBBLESTONE.defaultBlockState(),
            Blocks.MOSSY_COBBLESTONE.defaultBlockState());



    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> featureRegisterable) {
        FeatureUtils.register(featureRegisterable, ANDESITE_BOULDER, Feature.FOREST_ROCK,
                new BlockStateConfiguration(Blocks.ANDESITE.defaultBlockState()));
        FeatureUtils.register(featureRegisterable, CALCITE_BOULDER, Feature.FOREST_ROCK,
                new BlockStateConfiguration(Blocks.CALCITE.defaultBlockState()));
        FeatureUtils.register(featureRegisterable, DIORITE_BOULDER, Feature.FOREST_ROCK,
                new BlockStateConfiguration(Blocks.DIORITE.defaultBlockState()));
        FeatureUtils.register(featureRegisterable, GRANITE_BOULDER, Feature.FOREST_ROCK,
                new BlockStateConfiguration(Blocks.GRANITE.defaultBlockState()));
        FeatureUtils.register(featureRegisterable, LIMESTONE_BOULDER, Feature.FOREST_ROCK,
                new BlockStateConfiguration(StoneBlockSets.LIMESTONE.base().defaultBlockState()));
        FeatureUtils.register(featureRegisterable, SANDSTONE_BOULDER, Feature.FOREST_ROCK,
                new BlockStateConfiguration(Blocks.SANDSTONE.defaultBlockState()));
        FeatureUtils.register(featureRegisterable, STONE_BOULDER, Feature.FOREST_ROCK,
                new BlockStateConfiguration(Blocks.STONE.defaultBlockState()));
        FeatureUtils.register(featureRegisterable, MIRKWOOD_ROOTS_BOULDER, Feature.FOREST_ROCK,
                new BlockStateConfiguration(ModNatureBlocks.MIRKWOOD_ROOTS.defaultBlockState()));

        FeatureUtils.register(featureRegisterable, MOSSY_BOULDER, Feature.BLOCK_PILE,
                new BlockPileConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                        .add(Blocks.STONE.defaultBlockState(), 3)
                        .add(Blocks.ANDESITE.defaultBlockState(), 2)
                        .add(Blocks.STONE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM).setValue(SlabBlock.WATERLOGGED, false), 1)
                        .add(Blocks.MOSSY_COBBLESTONE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM).setValue(SlabBlock.WATERLOGGED, false), 2)
                        .add(Blocks.MOSSY_COBBLESTONE.defaultBlockState(), 6))));

        FeatureUtils.register(featureRegisterable, SMALL_BOULDER_ANDESITE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(smallWidth, smallLength, smallHeight, smallRandomSize, smallRandomness, andesite));
        FeatureUtils.register(featureRegisterable, MEDIUM_BOULDER_ANDESITE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(mediumWidth, mediumLength, mediumHeight, mediumRandomSize, mediumRandomness, andesite));
        FeatureUtils.register(featureRegisterable, BIG_BOULDER_ANDESITE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(bigWidth, bigLength, bigHeight, bigRandomSize, bigRandomness, andesite));

        FeatureUtils.register(featureRegisterable, SMALL_BOULDER_ASHEN_STONE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(smallWidth, smallLength, smallHeight, smallRandomSize, smallRandomness, ashenStone));
        FeatureUtils.register(featureRegisterable, MEDIUM_BOULDER_ASHEN_STONE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(mediumWidth, mediumLength, mediumHeight, mediumRandomSize, mediumRandomness, ashenStone));
        FeatureUtils.register(featureRegisterable, BIG_BOULDER_ASHEN_STONE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(bigWidth, bigLength, bigHeight, bigRandomSize, bigRandomness, ashenStone));

        FeatureUtils.register(featureRegisterable, SMALL_BOULDER_BASALT, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(smallWidth, smallLength, smallHeight, smallRandomSize, smallRandomness, basalt));
        FeatureUtils.register(featureRegisterable, MEDIUM_BOULDER_BASALT, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(mediumWidth, mediumLength, mediumHeight, mediumRandomSize, mediumRandomness, basalt));
        FeatureUtils.register(featureRegisterable, BIG_BOULDER_BASALT, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(bigWidth, bigLength, bigHeight, bigRandomSize, bigRandomness, basalt));

        FeatureUtils.register(featureRegisterable, SMALL_BOULDER_BLUE_TUFF, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(smallWidth, smallLength, smallHeight, smallRandomSize, smallRandomness, blueTuff));
        FeatureUtils.register(featureRegisterable, MEDIUM_BOULDER_BLUE_TUFF, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(mediumWidth, mediumLength, mediumHeight, mediumRandomSize, mediumRandomness, blueTuff));
        FeatureUtils.register(featureRegisterable, BIG_BOULDER_BLUE_TUFF, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(bigWidth, bigLength, bigHeight, bigRandomSize, bigRandomness, blueTuff));

        FeatureUtils.register(featureRegisterable, SMALL_BOULDER_CALCITE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(smallWidth, smallLength, smallHeight, smallRandomSize, smallRandomness, calcite));
        FeatureUtils.register(featureRegisterable, MEDIUM_BOULDER_CALCITE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(mediumWidth, mediumLength, mediumHeight, mediumRandomSize, mediumRandomness, calcite));
        FeatureUtils.register(featureRegisterable, BIG_BOULDER_CALCITE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(bigWidth, bigLength, bigHeight, bigRandomSize, bigRandomness, calcite));

        FeatureUtils.register(featureRegisterable, SMALL_BOULDER_DIORITE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(smallWidth, smallLength, smallHeight, smallRandomSize, smallRandomness, diorite));
        FeatureUtils.register(featureRegisterable, MEDIUM_BOULDER_DIORITE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(mediumWidth, mediumLength, mediumHeight, mediumRandomSize, mediumRandomness, diorite));
        FeatureUtils.register(featureRegisterable, BIG_BOULDER_DIORITE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(bigWidth, bigLength, bigHeight, bigRandomSize, bigRandomness, diorite));

        FeatureUtils.register(featureRegisterable, SMALL_BOULDER_DOLOMITE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(smallWidth, smallLength, smallHeight, smallRandomSize, smallRandomness, dolomite));
        FeatureUtils.register(featureRegisterable, MEDIUM_BOULDER_DOLOMITE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(mediumWidth, mediumLength, mediumHeight, mediumRandomSize, mediumRandomness, dolomite));
        FeatureUtils.register(featureRegisterable, BIG_BOULDER_DOLOMITE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(bigWidth, bigLength, bigHeight, bigRandomSize, bigRandomness, dolomite));

        FeatureUtils.register(featureRegisterable, SMALL_BOULDER_SMOOTH_DOLOMITE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(smallWidth, smallLength, smallHeight, smallRandomSize, smallRandomness, smoothDolomite));
        FeatureUtils.register(featureRegisterable, MEDIUM_BOULDER_SMOOTH_DOLOMITE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(mediumWidth, mediumLength, mediumHeight, mediumRandomSize, mediumRandomness, smoothDolomite));
        FeatureUtils.register(featureRegisterable, BIG_BOULDER_SMOOTH_DOLOMITE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(bigWidth, bigLength, bigHeight, bigRandomSize, bigRandomness, smoothDolomite));

        FeatureUtils.register(featureRegisterable, SMALL_BOULDER_GALONN, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(smallWidth, smallLength, smallHeight, smallRandomSize, smallRandomness, galonn));
        FeatureUtils.register(featureRegisterable, MEDIUM_BOULDER_GALONN, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(mediumWidth, mediumLength, mediumHeight, mediumRandomSize, mediumRandomness, galonn));
        FeatureUtils.register(featureRegisterable, BIG_BOULDER_GALONN, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(bigWidth, bigLength, bigHeight, bigRandomSize, bigRandomness, galonn));

        FeatureUtils.register(featureRegisterable, SMALL_BOULDER_GNEISS, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(smallWidth, smallLength, smallHeight, smallRandomSize, smallRandomness, gneiss));
        FeatureUtils.register(featureRegisterable, MEDIUM_BOULDER_GNEISS, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(mediumWidth, mediumLength, mediumHeight, mediumRandomSize, mediumRandomness, gneiss));
        FeatureUtils.register(featureRegisterable, BIG_BOULDER_GNEISS, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(bigWidth, bigLength, bigHeight, bigRandomSize, bigRandomness, gneiss));

        FeatureUtils.register(featureRegisterable, SMALL_BOULDER_GRANITE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(smallWidth, smallLength, smallHeight, smallRandomSize, smallRandomness, granite));
        FeatureUtils.register(featureRegisterable, MEDIUM_BOULDER_GRANITE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(mediumWidth, mediumLength, mediumHeight, mediumRandomSize, mediumRandomness, granite));
        FeatureUtils.register(featureRegisterable, BIG_BOULDER_GRANITE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(bigWidth, bigLength, bigHeight, bigRandomSize, bigRandomness, granite));

        FeatureUtils.register(featureRegisterable, SMALL_BOULDER_HEMATITE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(smallWidth, smallLength, smallHeight, smallRandomSize, smallRandomness, hematite));
        FeatureUtils.register(featureRegisterable, MEDIUM_BOULDER_HEMATITE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(mediumWidth, mediumLength, mediumHeight, mediumRandomSize, mediumRandomness, hematite));
        FeatureUtils.register(featureRegisterable, BIG_BOULDER_HEMATITE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(bigWidth, bigLength, bigHeight, bigRandomSize, bigRandomness, hematite));

        FeatureUtils.register(featureRegisterable, SMALL_BOULDER_IRONSTONE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(smallWidth, smallLength, smallHeight, smallRandomSize, smallRandomness, ironStone));
        FeatureUtils.register(featureRegisterable, MEDIUM_BOULDER_IRONSTONE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(mediumWidth, mediumLength, mediumHeight, mediumRandomSize, mediumRandomness, ironStone));
        FeatureUtils.register(featureRegisterable, BIG_BOULDER_IRONSTONE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(bigWidth, bigLength, bigHeight, bigRandomSize, bigRandomness, ironStone));

        FeatureUtils.register(featureRegisterable, SMALL_BOULDER_LIMESTONE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(smallWidth, smallLength, smallHeight, smallRandomSize, smallRandomness, limeStone));
        FeatureUtils.register(featureRegisterable, MEDIUM_BOULDER_LIMESTONE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(mediumWidth, mediumLength, mediumHeight, mediumRandomSize, mediumRandomness, limeStone));
        FeatureUtils.register(featureRegisterable, BIG_BOULDER_LIMESTONE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(bigWidth, bigLength, bigHeight, bigRandomSize, bigRandomness, limeStone));

        FeatureUtils.register(featureRegisterable, SMALL_BOULDER_MOSSY_STONE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(smallWidth, smallLength, smallHeight, smallRandomSize, smallRandomness, mossyStone));
        FeatureUtils.register(featureRegisterable, MEDIUM_BOULDER_MOSSY_STONE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(mediumWidth, mediumLength, mediumHeight, mediumRandomSize, mediumRandomness, mossyStone));
        FeatureUtils.register(featureRegisterable, BIG_BOULDER_MOSSY_STONE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(bigWidth, bigLength, bigHeight, bigRandomSize, bigRandomness, mossyStone));

        FeatureUtils.register(featureRegisterable, SMALL_BOULDER_SANDSTONE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(smallWidth, smallLength, smallHeight, smallRandomSize, smallRandomness, sandStone));
        FeatureUtils.register(featureRegisterable, MEDIUM_BOULDER_SANDSTONE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(mediumWidth, mediumLength, mediumHeight, mediumRandomSize, mediumRandomness, sandStone));
        FeatureUtils.register(featureRegisterable, BIG_BOULDER_SANDSTONE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(bigWidth, bigLength, bigHeight, bigRandomSize, bigRandomness, sandStone));

        FeatureUtils.register(featureRegisterable, SMALL_BOULDER_STONE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(smallWidth, smallLength, smallHeight, smallRandomSize, smallRandomness, stone));
        FeatureUtils.register(featureRegisterable, MEDIUM_BOULDER_STONE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(mediumWidth, mediumLength, mediumHeight, mediumRandomSize, mediumRandomness, stone));
        FeatureUtils.register(featureRegisterable, BIG_BOULDER_STONE, ModFeatures.BIG_BOULDER,
                new BigBoulderFeatureConfig(bigWidth, bigLength, bigHeight, bigRandomSize, bigRandomness, stone));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, name));
    }
}