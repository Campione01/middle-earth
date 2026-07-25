package net.jukoz.me.world.features.tree;

import com.google.common.collect.ImmutableList;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.ModBlocks;
import net.jukoz.me.block.ModNatureBlocks;
import net.jukoz.me.block.WoodBlockSets;
import net.jukoz.me.world.features.tree.foliages.OvalFoliagePlacer;
import net.jukoz.me.world.features.tree.foliages.PalmFoliagePlacer;
import net.jukoz.me.world.features.tree.trunks.ArcTrunkPlacer;
import net.jukoz.me.world.features.tree.trunks.CanopyTrunkPlacer;
import net.jukoz.me.world.features.tree.trunks.LargeTrunkPlacer;
import net.jukoz.me.world.features.tree.trunks.SpruceTrunkPlacer;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.PineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import java.util.*;

public class ModTreeConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> BEECH_TREE_KEY = registerKey("beech_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIRCH_TREE_KEY = registerKey("birch_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHESTNUT_TREE_KEY = registerKey("chestnut_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DARK_OAK_TREE_KEY = registerKey("dark_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_DARK_OAK_TREE_KEY = registerKey("mega_dark_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_BIRCH_TREE_KEY = registerKey("mega_birch_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FIR_TREE_KEY = registerKey("fir_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HOLLY_TREE_KEY = registerKey("holy_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARCH_TREE_KEY = registerKey("larch_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_LEBETHRON_TREE_KEY = registerKey("black_lebethron_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_LEBETHRON_TREE_KEY = registerKey("white_lebethron_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_MIRKWOOD_TREE_KEY = registerKey("small_mirkwood_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIRKWOOD_TREE_KEY = registerKey("mirkwood_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_MIRKWOOD_TREE_KEY = registerKey("mega_mirkwood_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MALLORN_TREE_KEY = registerKey("mallorn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_MALLORN_TREE_KEY = registerKey("small_mallorn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MALLORN_BUSH_KEY = registerKey("mallorn_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MALLORN_FLOWERING_BUSH_KEY = registerKey("mallorn_flowering_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_MALLORN_TREE_KEY = registerKey("mega_mallorn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_TREE_KEY = registerKey("maple_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> YELLOW_MAPLE_TREE_KEY = registerKey("yellow_maple_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_MAPLE_TREE_KEY = registerKey("orange_maple_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_MAPLE_TREE_KEY = registerKey("red_maple_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_MAPLE_TREE_KEY = registerKey("silver_maple_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_YELLOW_MAPLE_TREE_KEY = registerKey("silver_yellow_maple_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_ORANGE_MAPLE_TREE_KEY = registerKey("silver_orange_maple_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_RED_MAPLE_TREE_KEY = registerKey("silver_red_maple_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_BUSH_TREE_KEY = registerKey("silver_oak_bush_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_TREE_KEY = registerKey("oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BEES_OAK_TREE_KEY = registerKey("bees_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_TREE_VINES_KEY = registerKey("oak_vines_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_SMALL_TREE_VINES_KEY = registerKey("oak_small_vines_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_OAK_TREE_KEY = registerKey("mega_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PALM_TREE_KEY = registerKey("palm_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_PALM_TREE_KEY = registerKey("white_palm_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PINE_TREE_KEY = registerKey("pine_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_PINE_TREE_KEY = registerKey("dead_pine_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DRY_PINE_TREE_KEY = registerKey("dry_pine_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DRY_PINE_BUSH_TREE_KEY = registerKey("dry_pine_bush_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_PINE_TREE_KEY = registerKey("black_pine_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_BLACK_PINE_TREE_KEY = registerKey("dead_black_pine_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SCORCHED_TREE_KEY = registerKey("scorched_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_TREE_KEY = registerKey("spruce_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_BUSH_TREE_KEY = registerKey("spruce_bush_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILLOW_TREE_KEY = registerKey("willow_tree");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<Block> registryEntryLookup = context.lookup(Registries.BLOCK);
        final ArrayList<BlockState> emptyList = new ArrayList<>();
        final ArrayList<BlockState> mallornLeaves = new ArrayList<>(Arrays.asList(WoodBlockSets.MALLORN.leaves().defaultBlockState(), WoodBlockSets.MALLORN.leaves().defaultBlockState(), ModNatureBlocks.FLOWERING_MALLORN_LEAVES.defaultBlockState()));
        final ArrayList<BlockState> hollyLeaves = new ArrayList<>(Arrays.asList(WoodBlockSets.HOLLY.leaves().defaultBlockState(), WoodBlockSets.HOLLY.leaves().defaultBlockState(), ModNatureBlocks.BERRY_HOLLY_LEAVES.defaultBlockState()));

        register(context, BEECH_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.BEECH.log()),
            new CanopyTrunkPlacer(12, 2, 0.91f, 0.87f, 5.0f, 3, 0.42f, -0.1f, 1,1),
            BlockStateProvider.simple(WoodBlockSets.BEECH.leaves()),
            new OvalFoliagePlacer(3, ConstantInt.of(0), ConstantInt.of(2), emptyList,0.4f),
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, BIRCH_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.BIRCH_LOG),
            new CanopyTrunkPlacer(15, 2, 0.95f, 0.9f, 4.3f, 3, 0.37f,  0.025f,1,1),
            BlockStateProvider.simple(Blocks.BIRCH_LEAVES),
            new OvalFoliagePlacer(3, ConstantInt.of(0), ConstantInt.of(2), emptyList,0.4f),
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, MEGA_BIRCH_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.BIRCH_LOG),
            new CanopyTrunkPlacer(18, 3, 1.0f, 0.67f, 5.2f, 3, 0.44f, -0.05f, 2, 1),
            BlockStateProvider.simple(Blocks.BIRCH_LEAVES),
            new OvalFoliagePlacer(2, ConstantInt.of(0), ConstantInt.of(3), emptyList,0.4f),
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, CHESTNUT_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.CHESTNUT.log()),
            new CanopyTrunkPlacer(7, 2, 0.9f, 0.87f, 8.6f, 2, 0.4f, -0.15f, 0,1),
            BlockStateProvider.simple(WoodBlockSets.CHESTNUT.leaves()),
            new OvalFoliagePlacer(2, ConstantInt.of(0), ConstantInt.of(2), emptyList,0.14f),
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, DARK_OAK_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.DARK_OAK_LOG),
            new CanopyTrunkPlacer(11, 2, 0.97f, 0.87f, 5.0f, 3, 0.42f, -0.1f, 1,1),
            BlockStateProvider.simple(Blocks.DARK_OAK_LEAVES),
            new OvalFoliagePlacer(2, ConstantInt.of(0), ConstantInt.of(3), emptyList, 0.3f),
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, MEGA_DARK_OAK_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.DARK_OAK_LOG),
            new CanopyTrunkPlacer(21, 3, 1.8f, 0.55f, 6.1f, 3, 0.44f, -0.15f, 2, 0),
            BlockStateProvider.simple(Blocks.DARK_OAK_LEAVES),
            new OvalFoliagePlacer(2, ConstantInt.of(0), ConstantInt.of(3), emptyList,0.4f),
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, FIR_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.FIR.log()),
            new StraightTrunkPlacer(11, 2 , 1),
            BlockStateProvider.simple(WoodBlockSets.FIR.leaves()),
            new SpruceFoliagePlacer(ConstantInt.of(3), UniformInt.of(0, 2), UniformInt.of(2, 3)),
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, HOLLY_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.HOLLY.log()),
            new CanopyTrunkPlacer(9, 2, 0.9f, 0.87f, 8.6f, 1, 0.4f, -0.15f, 0,1),
            BlockStateProvider.simple(WoodBlockSets.HOLLY.leaves()),
            new OvalFoliagePlacer(3, ConstantInt.of(-1), ConstantInt.of(2), hollyLeaves, 0.17f),
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, LARCH_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.LARCH.log()),
            new StraightTrunkPlacer(11, 2 , 1),
            BlockStateProvider.simple(WoodBlockSets.LARCH.leaves()),
            new SpruceFoliagePlacer(ConstantInt.of(3), UniformInt.of(0, 2), UniformInt.of(2, 3)),
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, BLACK_LEBETHRON_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.BLACK_LEBETHRON.log()),
            new CanopyTrunkPlacer(10, 2, 0.9f, 0.87f, 8.6f, 3, 0.42f, -0.15f, 0,1),
            BlockStateProvider.simple(ModNatureBlocks.LEBETHRON_LEAVES),
            new OvalFoliagePlacer(2, ConstantInt.of(0), ConstantInt.of(2), emptyList, 0.4f),
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, WHITE_LEBETHRON_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.WHITE_LEBETHRON.log()),
            new CanopyTrunkPlacer(10, 2, 0.9f, 0.87f, 8.6f, 3, 0.42f, -0.15f, 0,1),
            BlockStateProvider.simple(ModNatureBlocks.LEBETHRON_LEAVES),
            new OvalFoliagePlacer(2, ConstantInt.of(0), ConstantInt.of(2), emptyList, 0.4f),
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, MALLORN_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.MALLORN.log()),
            new CanopyTrunkPlacer(16, 2, 0.9f, 0.87f, 5.2f, 3, 0.45f, -0.15f, 0,1),
            BlockStateProvider.simple(WoodBlockSets.MALLORN.leaves()),
            new OvalFoliagePlacer(2, ConstantInt.of(0), ConstantInt.of(3), emptyList,0.4f),
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, SMALL_MALLORN_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.MALLORN.log()),
            new CanopyTrunkPlacer(9, 2, 0.9f, 0.87f, 5.2f, 2, 0.45f, -0.15f, 0,1),
            BlockStateProvider.simple(WoodBlockSets.MALLORN.leaves()),
            new OvalFoliagePlacer(2, ConstantInt.of(0), ConstantInt.of(3), emptyList,  0.4f),
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, MEGA_MALLORN_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.MALLORN.log()),
            new CanopyTrunkPlacer(34, 3, 1.6f, 0.56f, 8.3f, 4, 0.48f, 0f, 2,1),
            BlockStateProvider.simple(WoodBlockSets.MALLORN.leaves()),
            new OvalFoliagePlacer(3, ConstantInt.of(-1), ConstantInt.of(4), emptyList,  0.7f),
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, MALLORN_BUSH_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.MALLORN.log()),
            new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.simple(WoodBlockSets.MALLORN.leaves()),
            new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
            new TwoLayersFeatureSize(0, 0, 0)).build());
        register(context, MALLORN_FLOWERING_BUSH_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.MALLORN.log()),
            new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.simple(ModNatureBlocks.FLOWERING_MALLORN_LEAVES),
            new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 1),
            new TwoLayersFeatureSize(0, 0, 0)).build());

        CanopyTrunkPlacer mapleTrunk = new CanopyTrunkPlacer(11, 2, 0.91f, 0.87f,
                5.1f, 2, 0.37f, -0.1f, 1,1);
        OvalFoliagePlacer mapleFoliage = new OvalFoliagePlacer(2, ConstantInt.of(0), ConstantInt.of(3), emptyList, 0.3f);

        register(context, MAPLE_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.MAPLE.log()),
            mapleTrunk,
            BlockStateProvider.simple(ModNatureBlocks.MAPLE_LEAVES),
            mapleFoliage,
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, YELLOW_MAPLE_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.MAPLE.log()),
            mapleTrunk,
            BlockStateProvider.simple(ModNatureBlocks.YELLOW_MAPLE_LEAVES),
            mapleFoliage,
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, ORANGE_MAPLE_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.MAPLE.log()),
            mapleTrunk,
            BlockStateProvider.simple(ModNatureBlocks.ORANGE_MAPLE_LEAVES),
            mapleFoliage,
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, RED_MAPLE_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.MAPLE.log()),
            mapleTrunk,
            BlockStateProvider.simple(ModNatureBlocks.RED_MAPLE_LEAVES),
            mapleFoliage,
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, SILVER_MAPLE_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.SILVER_MAPLE.log()),
            mapleTrunk,
            BlockStateProvider.simple(ModNatureBlocks.MAPLE_LEAVES),
            mapleFoliage,
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, SILVER_YELLOW_MAPLE_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.SILVER_MAPLE.log()),
            mapleTrunk,
            BlockStateProvider.simple(ModNatureBlocks.YELLOW_MAPLE_LEAVES),
            mapleFoliage,
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, SILVER_ORANGE_MAPLE_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.SILVER_MAPLE.log()),
            mapleTrunk,
            BlockStateProvider.simple(ModNatureBlocks.ORANGE_MAPLE_LEAVES),
            mapleFoliage,
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, SILVER_RED_MAPLE_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.SILVER_MAPLE.log()),
            mapleTrunk,
            BlockStateProvider.simple(ModNatureBlocks.RED_MAPLE_LEAVES),
            mapleFoliage,
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, SMALL_MIRKWOOD_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.MIRKWOOD.wood()),
            new CanopyTrunkPlacer(7, 2, 0.9f, 0.87f, 3.2f, 1, 0.28f, -0.15f, 0, 0),
            BlockStateProvider.simple(WoodBlockSets.MIRKWOOD.leaves()),
            new OvalFoliagePlacer(2, ConstantInt.of(-1), ConstantInt.of(2), emptyList, 0.3f),
            new TwoLayersFeatureSize(1, 0, 2))
            .decorators(ImmutableList.of(new LeaveVineDecorator(0.1F)))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, MIRKWOOD_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.MIRKWOOD.wood()),
            new LargeTrunkPlacer(14, 2, 1.1f, 0.55f, 3.2f, 2, 0.28f),
            BlockStateProvider.simple(WoodBlockSets.MIRKWOOD.leaves()),
            new OvalFoliagePlacer(2, ConstantInt.of(-1), ConstantInt.of(3), emptyList, 0.4f),
            new TwoLayersFeatureSize(1, 0, 2))
            .decorators(ImmutableList.of(new LeaveVineDecorator(0.25F)))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, MEGA_MIRKWOOD_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.MIRKWOOD.log()),
            new LargeTrunkPlacer(27, 3, 2.3f, 0.6f, 6.2f, 5, 0.25f),
            BlockStateProvider.simple(WoodBlockSets.MIRKWOOD.leaves()),
            new OvalFoliagePlacer(3, ConstantInt.of(-1), ConstantInt.of(4), emptyList, 0.5f),
            new TwoLayersFeatureSize(1, 0, 2))
            .decorators(ImmutableList.of(new LeaveVineDecorator(0.25F)))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, OAK_BUSH_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.OAK_LOG),
            new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.simple(Blocks.OAK_LEAVES),
            new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
            new TwoLayersFeatureSize(0, 0, 0)).build());


        register(context, OAK_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.OAK_LOG),
            new CanopyTrunkPlacer(12, 2, 0.91f, 0.87f, 5.0f, 3, 0.42f, -0.1f, 1,1),
            BlockStateProvider.simple(Blocks.OAK_LEAVES),
            new OvalFoliagePlacer(2, ConstantInt.of(0), ConstantInt.of(3), emptyList, 0.3f),
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, BEES_OAK_TREE_KEY, Feature.TREE, oak().decorators(List.of(new BeehiveDecorator(0.25F))).build());
        register(context, OAK_TREE_VINES_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.OAK_LOG),
            new CanopyTrunkPlacer(10, 2, 0.91f, 0.87f, 5.0f, 3, 0.42f, -0.1f, 1,1),
            BlockStateProvider.simple(Blocks.OAK_LEAVES),
            new OvalFoliagePlacer(2, ConstantInt.of(0), ConstantInt.of(3), emptyList, 0.3f),
            new TwoLayersFeatureSize(1, 0, 2))
            .decorators(ImmutableList.of(new LeaveVineDecorator(0.25F)))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, OAK_SMALL_TREE_VINES_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.OAK_LOG),
            new StraightTrunkPlacer(4, 2, 0),
            BlockStateProvider.simple(Blocks.OAK_LEAVES),
            new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
            new TwoLayersFeatureSize(1, 0, 2))
            .decorators(ImmutableList.of(new LeaveVineDecorator(0.25F)))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, MEGA_OAK_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.OAK_LOG),
            new CanopyTrunkPlacer(20, 3, 1.8f, 0.55f, 5.7f, 3, 0.38f, -0.15f, 2, 0),
            BlockStateProvider.simple(Blocks.OAK_LEAVES),
            new OvalFoliagePlacer(2, ConstantInt.of(0), ConstantInt.of(3), emptyList, 0.4f),
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, PALM_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.PALM.log()),
            new ArcTrunkPlacer(10, 2, 0.02f, 0.07f, 0),
            BlockStateProvider.simple(WoodBlockSets.PALM.leaves()),
            new PalmFoliagePlacer(4, ConstantInt.of(0), ConstantInt.of(1), -0.3f, 0.3f),
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, WHITE_PALM_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.WHITE_PALM.log()),
            new ArcTrunkPlacer(10, 2, 0.02f, 0.07f, 0),
            BlockStateProvider.simple(WoodBlockSets.PALM.leaves()),
            new PalmFoliagePlacer(4, ConstantInt.of(0), ConstantInt.of(1), -0.3f, 0.3f),
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, PINE_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.PINE.log()),
            new SpruceTrunkPlacer(14, 3),
            BlockStateProvider.simple(WoodBlockSets.PINE.leaves()),
            new PineFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1), UniformInt.of(3, 4)),
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, DEAD_PINE_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.PINE.log()),
            new SpruceTrunkPlacer(14, 3),
            BlockStateProvider.simple(Blocks.AIR),
            new PineFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1), ConstantInt.of(1)),
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, DRY_PINE_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.PINE.log()),
            new SpruceTrunkPlacer(14, 3),
            BlockStateProvider.simple(ModNatureBlocks.DRY_PINE_LEAVES),
            new PineFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1), UniformInt.of(2, 3)),
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, DRY_PINE_BUSH_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(ModNatureBlocks.PINE_BRANCHES),
            new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.simple(ModNatureBlocks.DRY_PINE_LEAVES),
            new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
            new TwoLayersFeatureSize(0, 0, 0)).build());

        register(context, BLACK_PINE_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.BLACK_PINE.log()),
            new SpruceTrunkPlacer(13, 3),
            BlockStateProvider.simple(WoodBlockSets.BLACK_PINE.leaves()),
            new PineFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1), UniformInt.of(3, 4)),
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, DEAD_BLACK_PINE_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.BLACK_PINE.log()),
            new SpruceTrunkPlacer(13, 3),
            BlockStateProvider.simple(Blocks.AIR),
            new PineFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1), ConstantInt.of(1)),
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, SCORCHED_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.SCORCHED.log()),
            new CanopyTrunkPlacer(10, 2, 0.92f, 0.87f, 5.5f, 3, 0.38f, -0.1f, 1,1),
            BlockStateProvider.simple(Blocks.AIR),
            new PineFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1), ConstantInt.of(1)),
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(ModBlocks.ASHEN_DIRT)).build());

        register(context, SPRUCE_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.SPRUCE_LOG),
            new StraightTrunkPlacer(14, 2, 0),
            BlockStateProvider.simple(Blocks.SPRUCE_LEAVES),
            new SpruceFoliagePlacer(ConstantInt.of(3), UniformInt.of(0, 1), ConstantInt.of(2)),
            new TwoLayersFeatureSize(1, 0, 2))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, SPRUCE_BUSH_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.SPRUCE_LOG),
            new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.simple(Blocks.SPRUCE_LEAVES),
            new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
            new TwoLayersFeatureSize(0, 0, 0)).build());

        register(context, WILLOW_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WoodBlockSets.WILLOW.log()),
            new LargeTrunkPlacer(13, 2, 1.2f, 0.67f, 6.0f, 3, 0.32f),
            BlockStateProvider.simple(WoodBlockSets.WILLOW.leaves()),
            new OvalFoliagePlacer(2, ConstantInt.of(-1), ConstantInt.of(3), emptyList, 0.4f),
            new TwoLayersFeatureSize(1, 0, 2))
            .decorators(ImmutableList.of(new LeaveVineDecorator(0.35F)))
            .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
    }

    private static TreeConfiguration.TreeConfigurationBuilder builder(Block log, Block leaves, int baseHeight, int firstRandomHeight, int secondRandomHeight, int radius) {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(log), new StraightTrunkPlacer(baseHeight, firstRandomHeight, secondRandomHeight), BlockStateProvider.simple(leaves), new BlobFoliagePlacer(ConstantInt.of(radius), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1));
    }

    private static TreeConfiguration.TreeConfigurationBuilder oak() {
        return builder(Blocks.OAK_LOG, Blocks.OAK_LEAVES, 4, 2, 0, 2).ignoreVines();
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
            BootstrapContext<ConfiguredFeature<?, ?>> context,
            ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
