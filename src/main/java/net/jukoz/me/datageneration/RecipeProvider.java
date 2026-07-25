package net.jukoz.me.datageneration;

import net.jukoz.me.compat.neoforge.api.datagen.v1.provider.NeoForgeRecipeProvider;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.*;
import net.jukoz.me.datageneration.content.models.*;
import net.jukoz.me.datageneration.custom.AlloyRecipeJsonBuilder;
import net.jukoz.me.datageneration.custom.AnvilShapingRecipeJsonBuilder;
import net.jukoz.me.item.*;
import net.jukoz.me.recipe.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.world.item.crafting.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {

    private final CompletableFuture<HolderLookup.Provider> registryLookup;
    private static final int INGOT_LIQUID_VALUE = 144;

    public RecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);

        this.registryLookup = registryLookupFuture;
    }

    @Override
    public void buildRecipes(RecipeOutput exporter) {

        //region STONE RECIPES
        for (StoneBlockSets.SimpleBlockSetMain record : StoneBlockSets.setsMain) {
            if (record.toString().contains("mossy_")) {
                createMossyRecipe(exporter, record.source(), record.base());
            } else if (record.toString().contains("cracked_")) {
                createSmeltingRecipe(exporter, record.source().asItem(), record.base().asItem());
            } else if (record.toString().contains("cobbled_")) {
                stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.base(), record.source(), 1);
            } else if (record.source() != null) {
                createBrickRecipe(exporter, record.source().asItem(), record.base(), 4);
                stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.base(), record.source(), 1);
                stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.slab(), record.source(), 2);
                stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.verticalSlab(), record.source(), 2);
                stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.stairs(), record.source());
                stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.wall(), record.source());
            }

            createButtonRecipe(exporter, record.base().asItem(), record.button());
            createPressurePlateRecipe(exporter, record.base().asItem(), record.pressurePlate());

            createSlabsRecipe(exporter, record.base(), record.slab());
            stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.slab(), record.base(), 2);

            createVerticalSlabsRecipe(exporter, record.slab(), record.verticalSlab());
            stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.verticalSlab(), record.base(), 2);
            stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.verticalSlab(), record.slab(), 1);
            createSlabsFromVerticalRecipe(exporter, record.verticalSlab(), record.slab());
            stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.slab(), record.verticalSlab(), 1);

            createStairsRecipe(exporter, record.base(), record.stairs());
            stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.stairs(), record.base(), 1);

            createWallsRecipe(exporter, record.base(), record.wall());
            stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.wall(), record.base(), 1);

            createFilledRecipe(exporter, record.base().asItem(), record.trapdoor(), 3);
            stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.trapdoor(), record.base());

            stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.rocks(), record.base(), 4);

            createStoneStoolRecipe(exporter, record.base().asItem(), record.stool());
            createStoneTableRecipe(exporter, record.base().asItem(), record.table());
            createStoneChairRecipe(exporter, record.base().asItem(), record.chair());
        }

        for (StoneBlockSets.SimpleBlockSet record : StoneBlockSets.sets) {
            if (record.toString().contains("mossy_")) {
                createMossyRecipe(exporter, record.source(), record.base());
            } else if (record.toString().contains("cracked_") || record.toString().contains("smooth_")) {
                createSmeltingRecipe(exporter, record.source().asItem(), record.base().asItem());
            } else if (record.toString().contains("cobbled_") || record.toString().contains("cobblestone")) {
                stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.base(), record.source(), 1);
                createSmeltingRecipeIdentifier(exporter, record.base().asItem(), record.source().asItem());
            } else if (record.toString().contains("old_") && !record.toString().contains("old_bricks")) {
                stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.base(), record.source(), 1);
            } else if (record.source() != null) {
                createBrickRecipe(exporter, record.source().asItem(), record.base(), 4);
                stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.base(), record.source(), 1);
                stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.slab(), record.source(), 2);
                stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.verticalSlab(), record.source(), 2);
                stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.stairs(), record.source());
                stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.wall(), record.source());
            }

            createSlabsRecipe(exporter, record.base(), record.slab());
            stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.slab(), record.base(), 2);
            createVerticalSlabsRecipe(exporter, record.slab(), record.verticalSlab());
            stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.verticalSlab(), record.base(), 2);
            stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.verticalSlab(), record.slab(), 1);
            createSlabsFromVerticalRecipe(exporter, record.verticalSlab(), record.slab());
            stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.slab(), record.verticalSlab(), 1);
            createStairsRecipe(exporter, record.base(), record.stairs());
            stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.stairs(), record.base(), 1);
            createWallsRecipe(exporter, record.base(), record.wall());
            stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.wall(), record.base(), 1);
        }

        for (StoneBlockSets.SimplePillarBlockSet record : StoneBlockSets.pillarSets) {
            if (record.toString().contains("mossy_")) {
                createMossyRecipe(exporter, record.source(), record.base());
            } else if (record.toString().contains("cracked_") || record.toString().contains("smooth_")) {
                createSmeltingRecipe(exporter, record.source().asItem(), record.base().asItem());
            } else if (record.toString().contains("cobbled_") || record.toString().contains("cobblestone")) {
                stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.base(), record.source(), 1);
                createSmeltingRecipeIdentifier(exporter, record.base().asItem(), record.source().asItem());
            } else if (record.toString().contains("old_") && !record.toString().contains("old_bricks")) {
                stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.base(), record.source(), 1);
            } else if (record.source() != null) {
                createBrickRecipe(exporter, record.source().asItem(), record.base(), 4);
                stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.base(), record.source(), 1);
                stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.slab(), record.source(), 2);
                stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.verticalSlab(), record.source(), 2);
                stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.stairs(), record.source());
                stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.wall(), record.source());
            }

            createSlabsRecipe(exporter, record.base(), record.slab());
            stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.slab(), record.base(), 2);
            createVerticalSlabsRecipe(exporter, record.slab(), record.verticalSlab());
            stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.verticalSlab(), record.base(), 2);
            stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.verticalSlab(), record.slab(), 1);
            createSlabsFromVerticalRecipe(exporter, record.verticalSlab(), record.slab());
            stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.slab(), record.verticalSlab(), 1);
            createStairsRecipe(exporter, record.base(), record.stairs());
            stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.stairs(), record.base(), 1);
            createWallsRecipe(exporter, record.base(), record.wall());
            stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, record.wall(), record.base(), 1);
        }
        //endregion

        //region WOOD RECIPES
        for (WoodBlockSets.SimpleBlockSet record : WoodBlockSets.sets) {
            createBrickRecipe(exporter, record.log().asItem(), record.wood(), 3);
            createBrickRecipe(exporter, record.strippedLog().asItem(), record.strippedWood(), 3);

            createWallsRecipe(exporter, record.wood(), record.woodWall());
            createWallsRecipe(exporter, record.strippedWood(), record.strippedWoodWall());

            createFenceRecipe(exporter, record.planks().asItem(), record.planksFence());
            createFenceRecipe(exporter, record.wood().asItem(), record.woodFence());
            createFenceRecipe(exporter, record.strippedWood().asItem(), record.strippedWoodFence());

            createSlabsRecipe(exporter, record.planks(), record.planksSlab());
            createSlabsRecipe(exporter, record.wood(), record.woodSlab());
            createSlabsRecipe(exporter, record.strippedWood(), record.strippedWoodSlab());

            createVerticalSlabsRecipe(exporter, record.planksSlab(), record.planksVerticalSlab());
            createSlabsFromVerticalRecipe(exporter, record.planksVerticalSlab(), record.planksSlab());
            createVerticalSlabsRecipe(exporter, record.woodSlab(), record.woodVerticalSlab());
            createSlabsFromVerticalRecipe(exporter, record.woodVerticalSlab(), record.woodSlab());
            createVerticalSlabsRecipe(exporter, record.strippedWoodSlab(), record.strippedWoodVerticalSlab());
            createSlabsFromVerticalRecipe(exporter, record.strippedWoodVerticalSlab(), record.strippedWoodSlab());

            createStairsRecipe(exporter, record.planks(), record.planksStairs());
            createStairsRecipe(exporter, record.wood(), record.woodStairs());
            createStairsRecipe(exporter, record.strippedWood(), record.strippedWoodStairs());

            createDoorRecipe(exporter, record.planks(), record.door());
            createTrapdoorRecipe(exporter, record.planks(), record.trapdoor());

            createWoodStoolRecipe(exporter, record.planks().asItem(), record.stool());
            createWoodBenchRecipe(exporter, record.planks().asItem(), record.bench());
            createWoodTableRecipe(exporter, record.planks().asItem(), record.table());
            createWoodChairRecipe(exporter, record.planks().asItem(), record.chair());

            createWoodLadderRecipe(exporter, record.planks().asItem(), record.ladder());

            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, record.planks(), 4)
                    .requires(record.log())
                    .unlockedBy(NeoForgeRecipeProvider.getHasName(record.log()),
                            NeoForgeRecipeProvider.has(record.planks()))
                    .save(exporter);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, record.planks(), 4)
                    .requires(record.wood())
                    .unlockedBy(NeoForgeRecipeProvider.getHasName(record.wood()),
                            NeoForgeRecipeProvider.has(record.planks()))
                    .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.BLOCK.getKey(record.planks()).getPath() + "_from_wood"));

            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, record.planks(), 4)
                    .requires(record.strippedLog())
                    .unlockedBy(NeoForgeRecipeProvider.getHasName(record.strippedLog()),
                            NeoForgeRecipeProvider.has(record.planks()))
                    .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.BLOCK.getKey(record.planks()).getPath() + "_from_stripped_log"));

            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, record.planks(), 4)
                    .requires(record.strippedWood())
                    .unlockedBy(NeoForgeRecipeProvider.getHasName(record.strippedWood()),
                            NeoForgeRecipeProvider.has(record.planks()))
                    .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.BLOCK.getKey(record.planks()).getPath() + "_from_stripped_wood"));

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, record.planksGate(), 1)
                    .pattern("sls")
                    .pattern("sls")
                    .define('l', record.planks())
                    .define('s', Items.STICK)
                    .unlockedBy(NeoForgeRecipeProvider.getHasName(record.planks()),
                            NeoForgeRecipeProvider.has(record.planks()))
                    .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.STICK),
                            NeoForgeRecipeProvider.has(Items.STICK))
                    .save(exporter);

            createButtonRecipe(exporter, record.planks().asItem(), record.button());
            createPressurePlateRecipe(exporter, record.planks().asItem(), record.pressurePlate());
        }
        //endregion

        //region MUSHROOM RECIPES
        for (MushroomBlockSets.MushroomBlockSet record : MushroomBlockSets.sets) {

            if (record.stem() != null) {
                createWallsRecipe(exporter, record.stem(), record.stemWall());

                ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, record.planks(), 4)
                        .requires(record.stem())
                        .unlockedBy(NeoForgeRecipeProvider.getHasName(record.stem()),
                                NeoForgeRecipeProvider.has(record.planks()))
                        .save(exporter);
            }

            createSlabsRecipe(exporter, record.planks(), record.planksSlab());
            createVerticalSlabsRecipe(exporter, record.planksSlab(), record.planksVerticalSlab());
            createSlabsFromVerticalRecipe(exporter, record.planksVerticalSlab(), record.planksSlab());
            createStairsRecipe(exporter, record.planks(), record.planksStairs());
            createDoorRecipe(exporter, record.planks(), record.door());
            createTrapdoorRecipe(exporter, record.planks(), record.trapdoor());
            createWoodStoolRecipe(exporter, record.planks().asItem(), record.stool());
            createWoodBenchRecipe(exporter, record.planks().asItem(), record.bench());
            createWoodTableRecipe(exporter, record.planks().asItem(), record.table());
            createWoodChairRecipe(exporter, record.planks().asItem(), record.chair());

            createWoodLadderRecipe(exporter, record.planks().asItem(), record.ladder());

            createFenceRecipe(exporter, record.planks().asItem(), record.planksFence());
            createFenceRecipe(exporter, record.stem().asItem(), record.stemFence());

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, record.planksGate(), 1)
                    .pattern("sls")
                    .pattern("sls")
                    .define('l', record.planks())
                    .define('s', Items.STICK)
                    .unlockedBy(NeoForgeRecipeProvider.getHasName(record.planks()),
                            NeoForgeRecipeProvider.has(record.planks()))
                    .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.STICK),
                            NeoForgeRecipeProvider.has(Items.STICK))
                    .save(exporter);

            createButtonRecipe(exporter, record.planks().asItem(), record.button());
            createPressurePlateRecipe(exporter, record.planks().asItem(), record.pressurePlate());
        }
        //endregion

        //region ROOF RECIPES
        for (OtherBlockSets.RoofBlockSet record : OtherBlockSets.sets) {

            if (record.origin() != null) {
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, record.block(), 7)
                        .pattern(" l ")
                        .pattern("lll")
                        .pattern("lll")
                        .define('l', record.origin())
                        .unlockedBy(NeoForgeRecipeProvider.getHasName(record.origin()),
                                NeoForgeRecipeProvider.has(record.origin()))
                        .save(exporter);
            }
            createSlabsRecipe(exporter, record.block(), record.slab());
            createVerticalSlabsRecipe(exporter, record.slab(), record.verticalSlab());
            createSlabsFromVerticalRecipe(exporter, record.verticalSlab(), record.slab());
            createStairsRecipe(exporter, record.block(), record.stairs());
            createWallsRecipe(exporter, record.block(), record.wall());
        }

        for (OtherBlockSets.MiscBlockSet record : OtherBlockSets.specialWoodSets) {

            if (record.origin() != null) {
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, record.block(), 7)
                        .pattern(" l ")
                        .pattern("lll")
                        .pattern("lll")
                        .define('l', record.origin())
                        .unlockedBy(NeoForgeRecipeProvider.getHasName(record.origin()),
                                NeoForgeRecipeProvider.has(record.origin()))
                        .save(exporter);
            }
            createSlabsRecipe(exporter, record.block(), record.slab());
            createVerticalSlabsRecipe(exporter, record.slab(), record.verticalSlab());
            createSlabsFromVerticalRecipe(exporter, record.verticalSlab(), record.slab());
            createStairsRecipe(exporter, record.block(), record.stairs());
            createWallsRecipe(exporter, record.block(), record.wall());
        }
        //endregion

        //region BLOCK LIST SPECIFIC RECIPES
        for (SimpleVerticalSlabModel.VerticalSlab verticalSlab : SimpleVerticalSlabModel.vanillaVerticalSlabs) {
            createVerticalSlabsRecipe(exporter, verticalSlab.slab(), verticalSlab.verticalSlab());
            createSlabsFromVerticalRecipe(exporter, verticalSlab.verticalSlab(), verticalSlab.slab());
        }

        for (SimpleVerticalSlabModel.VerticalSlab verticalSlab : SimpleVerticalSlabModel.vanillaWoodVerticalSlabs) {
            createVerticalSlabsRecipe(exporter, verticalSlab.slab(), verticalSlab.verticalSlab());
            createSlabsFromVerticalRecipe(exporter, verticalSlab.verticalSlab(), verticalSlab.slab());
        }

        for (SimpleVerticalSlabModel.VerticalSlab verticalSlab : SimpleVerticalSlabModel.vanillaStrippedVerticalSlabs) {
            createVerticalSlabsRecipe(exporter, verticalSlab.slab(), verticalSlab.verticalSlab());
            createSlabsFromVerticalRecipe(exporter, verticalSlab.verticalSlab(), verticalSlab.slab());
        }

        for (SimplePillarModel.StonePillar pillar : SimplePillarModel.stonePillars) {
            if (pillar.toString().contains("mossy_")) {
                createMossyRecipe(exporter, pillar.origin(), pillar.base());
            } else if (pillar.toString().contains("cracked_")) {
                createSmeltingRecipe(exporter, pillar.origin().asItem(), pillar.base().asItem());
            } else {
                createPillarRecipe(exporter, pillar.origin(), pillar.base(), 3);
                stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, pillar.base().asItem(), pillar.origin());
            }
        }

        for (SimpleBlockModel.ChiseledBlock block : SimpleBlockModel.chiseledBlocks) {
            createChiseledRecipe(exporter, block.origin(), block.base(), 1);
        }
        for (SimpleBlockModel.ChiseledPolishedBlock block : SimpleBlockModel.chiseledPolishedBlocksTopBottom) {
            createChiseledRecipe(exporter, block.origin(), block.base(), 1);
        }
        for (SimpleBlockModel.ChiseledBlock block : SimpleBlockModel.chiseledMainBlockTopBottom) {
            createChiseledRecipe(exporter, block.origin(), block.base(), 1);
        }
        for (SimpleBlockModel.ChiseledBlock block : SimpleBlockModel.chiseledBlocksTopBottom) {
            createChiseledRecipe(exporter, block.origin(), block.base(), 1);
        }
        for (SimpleBlockModel.ChiseledPolishedBlock block : SimpleBlockModel.chiseledPolishedBlocks) {
            createCutPolishedRecipe(exporter, block.origin(), block.base(), 1);
        }
        for (SimpleBlockModel.ChiseledPolishedBlock block : SimpleBlockModel.chiseledTilesBlocksTopBottom) {
            createCutPolishedRecipe(exporter, block.origin(), block.base(), 1);
        }
        for (SimpleBlockModel.ChiseledPolishedBlock block : SimpleBlockModel.chiseledSmoothBlocksTopBottom) {
            createCutPolishedRecipe(exporter, block.origin(), block.base(), 1);
        }

        for (SimpleSlabModel.Slab slab : SimpleSlabModel.vanillaSlabs) {
            createSlabsRecipe(exporter, slab.origin(), slab.slab());
        }

        for (SimpleSlabModel.Slab slab : SimpleSlabModel.vanillaWoodSlabs) {
            createSlabsRecipe(exporter, slab.origin(), slab.slab());
        }

        for (SimpleSlabModel.Slab slab : SimpleSlabModel.vanillaStrippedSlab) {
            createSlabsRecipe(exporter, slab.origin(), slab.slab());
        }

        for (SimpleStairModel.Stair stair : SimpleStairModel.vanillaStairs) {
            createStairsRecipe(exporter, stair.origin(), stair.stairs());
        }

        for (SimpleStairModel.Stair stair : SimpleStairModel.vanillaWoodStairs) {
            createStairsRecipe(exporter, stair.origin(), stair.stairs());
        }

        for (SimpleStairModel.Stair stair : SimpleStairModel.vanillaStrippedStairs) {
            createStairsRecipe(exporter, stair.origin(), stair.stairs());
        }

        for (SimpleWallModel.Wall wall : SimpleWallModel.vanillaWalls) {
            createWallsRecipe(exporter, wall.block(), wall.wall());
        }

        for (SimpleWallModel.Wall wall : SimpleWallModel.vanillaStrippedWalls) {
            createWallsRecipe(exporter, wall.block(), wall.wall());
        }

        for (SimpleWallModel.Wall wall : SimpleWallModel.vanillaWoodWalls) {
            createWallsRecipe(exporter, wall.block(), wall.wall());
        }

        for (SimpleFenceModel.Fence fence : SimpleFenceModel.vanillaStrippedFences) {
            createFenceRecipe(exporter, fence.block().asItem(), fence.fence());
        }

        for (SimpleFenceModel.Fence fence : SimpleFenceModel.vanillaWoodFences) {
            createFenceRecipe(exporter, fence.block().asItem(), fence.fence());
        }

        for (SimplePaneModel.Pane pane : SimplePaneModel.panes) {
            createPaneRecipe(exporter, pane.glass().asItem(), pane.pane(), 16);
        }

        for (SimpleTrapDoorModel.Trapdoor trapdoor : SimpleTrapDoorModel.vanillaStoneTrapdoors) {
            createFilledRecipe(exporter, trapdoor.block().asItem(), trapdoor.trapdoor(),3);
            stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, trapdoor.trapdoor(), trapdoor.block());
        }

        for (SimpleWoodStoolModel.VanillaStool stool : SimpleWoodStoolModel.vanillaStools) {
            createWoodStoolRecipe(exporter, stool.planks().asItem(), stool.base());
        }

        for (SimpleWoodBenchModel.VanillaBench bench : SimpleWoodBenchModel.vanillaBenchs) {
            createWoodBenchRecipe(exporter, bench.planks().asItem(), bench.base());
        }

        for (SimpleWoodTableModel.VanillaTable table : SimpleWoodTableModel.vanillaTables) {
            createWoodTableRecipe(exporter, table.planks().asItem(), table.base());
        }

        for (SimpleWoodChairModel.VanillaChair chair : SimpleWoodChairModel.vanillaChairs) {
            createWoodChairRecipe(exporter, chair.planks().asItem(), chair.base());
        }

        for (SimpleLadderModel.Ladder ladder : SimpleLadderModel.vanillaLadders) {
            createWoodLadderRecipe(exporter, ladder.block().asItem(), ladder.ladder());
        }

        for (SimpleStoneStoolModel.VanillaStool stool : SimpleStoneStoolModel.vanillaStools) {
            createStoneStoolRecipe(exporter, stool.origin().asItem(), stool.base());
        }

        for (SimpleStoneTableModel.VanillaTable table : SimpleStoneTableModel.vanillaTables) {
            createStoneTableRecipe(exporter, table.origin().asItem(), table.base());
        }

        for (SimpleStoneChairModel.VanillaChair chair : SimpleStoneChairModel.vanillaChairs) {
            createStoneChairRecipe(exporter, chair.origin().asItem(), chair.base());
        }

        for (SimpleRocksModel.Rocks rock : SimpleRocksModel.vanillaRocks) {
            stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, rock.rocks(), rock.block(), 4);
        }

        //endregion

        //region MANUAL BLOCK RECIPES
        createCenterSurroundRecipe(exporter, ModDecorativeBlocks.LEAD_GLASS.asItem(), Items.BLACK_DYE, ModDecorativeBlocks.BLACK_STAINED_LEAD_GLASS.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModDecorativeBlocks.LEAD_GLASS.asItem(), Items.BLUE_DYE, ModDecorativeBlocks.BLUE_STAINED_LEAD_GLASS.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModDecorativeBlocks.LEAD_GLASS.asItem(), Items.BROWN_DYE, ModDecorativeBlocks.BROWN_STAINED_LEAD_GLASS.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModDecorativeBlocks.LEAD_GLASS.asItem(), Items.CYAN_DYE, ModDecorativeBlocks.CYAN_STAINED_LEAD_GLASS.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModDecorativeBlocks.LEAD_GLASS.asItem(), Items.GRAY_DYE, ModDecorativeBlocks.GRAY_STAINED_LEAD_GLASS.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModDecorativeBlocks.LEAD_GLASS.asItem(), Items.GREEN_DYE, ModDecorativeBlocks.GREEN_STAINED_LEAD_GLASS.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModDecorativeBlocks.LEAD_GLASS.asItem(), Items.LIGHT_BLUE_DYE, ModDecorativeBlocks.LIGHT_BLUE_STAINED_LEAD_GLASS.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModDecorativeBlocks.LEAD_GLASS.asItem(), Items.LIGHT_GRAY_DYE, ModDecorativeBlocks.LIGHT_GRAY_STAINED_LEAD_GLASS.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModDecorativeBlocks.LEAD_GLASS.asItem(), Items.LIME_DYE, ModDecorativeBlocks.LIME_STAINED_LEAD_GLASS.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModDecorativeBlocks.LEAD_GLASS.asItem(), Items.MAGENTA_DYE, ModDecorativeBlocks.MAGENTA_STAINED_LEAD_GLASS.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModDecorativeBlocks.LEAD_GLASS.asItem(), Items.ORANGE_DYE, ModDecorativeBlocks.ORANGE_STAINED_LEAD_GLASS.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModDecorativeBlocks.LEAD_GLASS.asItem(), Items.PINK_DYE, ModDecorativeBlocks.PINK_STAINED_LEAD_GLASS.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModDecorativeBlocks.LEAD_GLASS.asItem(), Items.PURPLE_DYE, ModDecorativeBlocks.PURPLE_STAINED_LEAD_GLASS.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModDecorativeBlocks.LEAD_GLASS.asItem(), Items.RED_DYE, ModDecorativeBlocks.RED_STAINED_LEAD_GLASS.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModDecorativeBlocks.LEAD_GLASS.asItem(), Items.WHITE_DYE, ModDecorativeBlocks.WHITE_STAINED_LEAD_GLASS.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModDecorativeBlocks.LEAD_GLASS.asItem(), Items.YELLOW_DYE, ModDecorativeBlocks.YELLOW_STAINED_LEAD_GLASS.asItem(), 8);

        createLayerRecipe(exporter, Blocks.GRAVEL.asItem(), ModBlocks.GRAVEL_LAYER);
        createLayerRecipe(exporter, Blocks.SAND.asItem(), ModBlocks.SAND_LAYER);
        createLayerRecipe(exporter, ModBlocks.BLACK_SAND.asItem(), ModBlocks.BLACK_SAND_LAYER);
        createLayerRecipe(exporter, ModBlocks.WHITE_SAND.asItem(), ModBlocks.WHITE_SAND_LAYER);
        createLayerRecipe(exporter, ModBlocks.ASHEN_SAND.asItem(), ModBlocks.ASHEN_SAND_LAYER);
        createLayerRecipe(exporter, ModBlocks.ASHEN_GRAVEL.asItem(), ModBlocks.ASHEN_GRAVEL_LAYER);

        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_LEAD, ModBlocks.LEAD_BLOCK, 4);
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_LEAD_SLAB, ModBlocks.LEAD_BLOCK, 8);
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_LEAD_VERTICAL_SLAB, ModBlocks.LEAD_BLOCK, 8);
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_LEAD_STAIRS, ModBlocks.LEAD_BLOCK, 4);
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_LEAD_SLAB, ModBlocks.CUT_LEAD, 2);
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_LEAD_STAIRS, ModBlocks.CUT_LEAD);

        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SILVER, ModBlocks.SILVER_BLOCK, 4);
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SILVER_SLAB, ModBlocks.SILVER_BLOCK, 8);
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SILVER_VERTICAL_SLAB, ModBlocks.SILVER_BLOCK, 8);
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SILVER_STAIRS, ModBlocks.SILVER_BLOCK, 4);
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SILVER_SLAB, ModBlocks.CUT_SILVER, 2);
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SILVER_STAIRS, ModBlocks.CUT_SILVER);

        createStairsRecipe(exporter, ModBlocks.REED_BLOCK, ModBlocks.REED_STAIRS);
        createSlabsRecipe(exporter, ModBlocks.REED_BLOCK, ModBlocks.REED_SLAB);
        createVerticalSlabsRecipe(exporter, ModBlocks.REED_BLOCK, ModBlocks.REED_VERTICAL_SLAB);
        createWallsRecipe(exporter, ModBlocks.REED_BLOCK, ModBlocks.REED_WALL);

        createStairsRecipe(exporter, ModBlocks.STRAW_BLOCK, ModBlocks.STRAW_STAIRS);
        createSlabsRecipe(exporter, ModBlocks.STRAW_BLOCK, ModBlocks.STRAW_SLAB);
        createVerticalSlabsRecipe(exporter, ModBlocks.STRAW_BLOCK, ModBlocks.STRAW_VERTICAL_SLAB);
        createWallsRecipe(exporter, ModBlocks.STRAW_BLOCK, ModBlocks.STRAW_WALL);

        createStairsRecipe(exporter, ModBlocks.GRASSY_DIRT, ModBlocks.GRASSY_DIRT_STAIRS);
        createSlabsRecipe(exporter, ModBlocks.GRASSY_DIRT, ModBlocks.GRASSY_DIRT_SLAB);

        createStairsRecipe(exporter, ModBlocks.PEBBLED_GRASS, ModBlocks.PEBBLED_GRASS_STAIRS);
        createSlabsRecipe(exporter, ModBlocks.PEBBLED_GRASS, ModBlocks.PEBBLED_GRASS_SLAB);

        createStairsRecipe(exporter, ModBlocks.TURF, ModBlocks.TURF_STAIRS);
        createSlabsRecipe(exporter, ModBlocks.TURF, ModBlocks.TURF_SLAB);
        createVerticalSlabsRecipe(exporter, ModBlocks.TURF, ModBlocks.TURF_VERTICAL_SLAB);

        createStairsRecipe(exporter, ModBlocks.MIRE, ModBlocks.MIRE_STAIRS);
        createSlabsRecipe(exporter, ModBlocks.MIRE, ModBlocks.MIRE_SLAB);

        createStairsRecipe(exporter, ModBlocks.DRY_DIRT, ModBlocks.DRY_DIRT_STAIRS);
        createSlabsRecipe(exporter, ModBlocks.DRY_DIRT, ModBlocks.DRY_DIRT_SLAB);

        createStairsRecipe(exporter, ModBlocks.DIRTY_ROOTS, ModBlocks.DIRTY_ROOTS_STAIRS);
        createSlabsRecipe(exporter, ModBlocks.DIRTY_ROOTS, ModBlocks.DIRTY_ROOTS_SLAB);

        createStairsRecipe(exporter, ModBlocks.ASHEN_DIRT, ModBlocks.ASHEN_DIRT_STAIRS);
        createSlabsRecipe(exporter, ModBlocks.ASHEN_DIRT, ModBlocks.ASHEN_DIRT_SLAB);

        createStairsRecipe(exporter, ModBlocks.COBBLY_ASHEN_DIRT, ModBlocks.COBBLY_ASHEN_DIRT_STAIRS);
        createSlabsRecipe(exporter, ModBlocks.COBBLY_ASHEN_DIRT, ModBlocks.COBBLY_ASHEN_DIRT_SLAB);

        createStairsRecipe(exporter, ModBlocks.COBBLY_DIRT, ModBlocks.COBBLY_DIRT_STAIRS);
        createSlabsRecipe(exporter, ModBlocks.COBBLY_DIRT, ModBlocks.COBBLY_DIRT_SLAB);

        createStairsRecipe(exporter, ModBlocks.SNOWY_DIRT, ModBlocks.SNOWY_DIRT_STAIRS);
        createSlabsRecipe(exporter, ModBlocks.SNOWY_DIRT, ModBlocks.SNOWY_DIRT_SLAB);

        createPaneRecipe(exporter, Blocks.WHITE_WOOL.asItem(), ModBlocks.NET, 16);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COPPER_BARS, 16)
                .pattern("IBI")
                .pattern("IBI")
                .define('I', Items.COPPER_INGOT)
                .define('B', Items.CUT_COPPER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.CUT_COPPER),
                        NeoForgeRecipeProvider.has(Items.CUT_COPPER))
                .save(exporter);

        createBrickRecipe(exporter, ModResourceItems.CITRINE_SHARD, ModBlocks.CITRINE_BLOCK, 1);
        createFilledRecipe(exporter, Items.GLOWSTONE, ModBlocks.GLOWSTONE_BLOCK, 1);
        createBrickRecipe(exporter, ModResourceItems.QUARTZ_SHARD, ModBlocks.QUARTZ_BLOCK, 1);
        createBrickRecipe(exporter, ModResourceItems.RED_AGATE_SHARD, ModBlocks.RED_AGATE_BLOCK, 1);

        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.BRICKS, StoneBlockSets.OLD_BRICKS.base());

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.WHITE_DAUB_HOBBIT_WINDOW, 4)
                .pattern("WBW")
                .pattern("BGB")
                .pattern("WBW")
                .define('W', StoneBlockSets.WHITE_DAUB.base())
                .define('G', Items.GLASS)
                .define('B', Items.BRICK)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(StoneBlockSets.WHITE_DAUB.base()),
                        NeoForgeRecipeProvider.has(StoneBlockSets.WHITE_DAUB.base()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.YELLOW_DAUB_HOBBIT_WINDOW, 4)
                .pattern("WBW")
                .pattern("BGB")
                .pattern("WBW")
                .define('W', StoneBlockSets.YELLOW_DAUB.base())
                .define('G', Items.GLASS)
                .define('B', Items.BRICK)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(StoneBlockSets.YELLOW_DAUB.base()),
                        NeoForgeRecipeProvider.has(StoneBlockSets.YELLOW_DAUB.base()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.PLASTER_HOBBIT_WINDOW, 4)
                .pattern("WBW")
                .pattern("BGB")
                .pattern("WBW")
                .define('W', StoneBlockSets.PLASTER.base())
                .define('G', Items.GLASS)
                .define('B', Items.BRICK)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(StoneBlockSets.PLASTER.base()),
                        NeoForgeRecipeProvider.has(StoneBlockSets.PLASTER.base()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.MEDGON_CARVED_WINDOW, 2)
                .pattern("EEE")
                .pattern("EGE")
                .pattern("EEE")
                .define('E', StoneBlockSets.MEDGON.base())
                .define('G', Items.GLASS)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(StoneBlockSets.MEDGON.base()),
                        NeoForgeRecipeProvider.has(StoneBlockSets.MEDGON.base()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.GONLUIN_CARVED_WINDOW, 2)
                .pattern("EEE")
                .pattern("EGE")
                .pattern("EEE")
                .define('E', StoneBlockSets.GONLUIN.base())
                .define('G', Items.GLASS)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(StoneBlockSets.GONLUIN.base()),
                        NeoForgeRecipeProvider.has(StoneBlockSets.GONLUIN.base()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.TUFF_CARVED_WINDOW, 2)
                .pattern("EEE")
                .pattern("EGE")
                .pattern("EEE")
                .define('E', Blocks.TUFF)
                .define('G', Items.GLASS)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Blocks.TUFF),
                        NeoForgeRecipeProvider.has(Blocks.TUFF))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.BLACKSTONE_CARVED_WINDOW, 2)
                .pattern("EEE")
                .pattern("EGE")
                .pattern("EEE")
                .define('E', Blocks.BLACKSTONE)
                .define('G', Items.GLASS)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Blocks.BLACKSTONE),
                        NeoForgeRecipeProvider.has(Blocks.BLACKSTONE))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.IZHERABAN_CARVED_WINDOW, 2)
                .pattern("EEE")
                .pattern("EGE")
                .pattern("EEE")
                .define('E', StoneBlockSets.IZHERABAN.base())
                .define('G', Items.GLASS)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(StoneBlockSets.IZHERABAN.base()),
                        NeoForgeRecipeProvider.has(StoneBlockSets.IZHERABAN.base()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.LEAD_GLASS, 4)
                .pattern("LGL")
                .pattern("GLG")
                .pattern("LGL")
                .define('L', ModResourceItems.LEAD_NUGGET)
                .define('G', Items.GLASS)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.ROD),
                        NeoForgeRecipeProvider.has(ModResourceItems.ROD))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.ROPE, 3)
                .pattern("SS")
                .pattern("SS")
                .pattern("SS")
                .define('S', Items.STRING)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.STRING),
                        NeoForgeRecipeProvider.has(Items.STRING))
                .save(exporter);

        createBrickRecipe(exporter, ModResourceItems.ASH, ModBlocks.ASH_BLOCK, 1);
        createBrickRecipe(exporter, ModBlocks.ASH_BLOCK.asItem(), Blocks.TUFF, 1);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, StoneBlockSets.ASHEN_STONE.base(), 4)
                .pattern("AS")
                .pattern("SA")
                .define('A', ModBlocks.ASH_BLOCK)
                .define('S', Blocks.STONE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModBlocks.ASH_BLOCK),
                        NeoForgeRecipeProvider.has(ModBlocks.ASH_BLOCK))
                .save(exporter);

        createGildedBlockRecipe(exporter, ModBlocks.CHISELED_GREEN_TUFF, ModBlocks.GILDED_CHISELED_GREEN_TUFF);
        createGildedBlockRecipe(exporter, ModBlocks.CHISELED_GREEN_TUFF_BRICKS, ModBlocks.GILDED_CHISELED_GREEN_TUFF_BRICKS);
        createGildedBlockRecipe(exporter, ModBlocks.CHISELED_POLISHED_GREEN_TUFF, ModBlocks.GILDED_CHISELED_POLISHED_GREEN_TUFF);
        createGildedBlockRecipe(exporter, ModBlocks.CHISELED_GREEN_TUFF_TILES, ModBlocks.GILDED_CHISELED_GREEN_TUFF_TILES);
        createGildedBlockRecipe(exporter, ModBlocks.CHISELED_SMOOTH_GREEN_TUFF, ModBlocks.GILDED_CHISELED_SMOOTH_GREEN_TUFF);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, StoneBlockSets.GILDED_GREEN_TUFF.base(), 5)
                .pattern("TNT")
                .pattern("NTN")
                .pattern("TNT")
                .define('T', StoneBlockSets.GREEN_TUFF.base())
                .define('N', Items.GOLD_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(StoneBlockSets.GREEN_TUFF.base()),
                        NeoForgeRecipeProvider.has(StoneBlockSets.GREEN_TUFF.base()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WATTLE_TRAPDOOR, 2)
                .pattern("PLP")
                .pattern("PLP")
                .define('P', TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .define('L', ModResourceItems.LEAD_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.LEAD_NUGGET),
                        NeoForgeRecipeProvider.has(ModResourceItems.LEAD_NUGGET))
                .save(exporter);

        createDyeableItemRecipe(exporter, ModBlocks.WATTLE_TRAPDOOR, Items.RED_DYE, ModBlocks.RED_WATTLE_TRAPDOOR);
        createDyeableItemRecipe(exporter, ModBlocks.WATTLE_TRAPDOOR, Items.GREEN_DYE, ModBlocks.GREEN_WATTLE_TRAPDOOR);
        createDyeableItemRecipe(exporter, ModBlocks.WATTLE_TRAPDOOR, Items.BROWN_DYE, ModBlocks.DARK_WATTLE_TRAPDOOR);
        createDyeableItemRecipe(exporter, ModBlocks.WATTLE_TRAPDOOR, Items.BLACK_DYE, ModBlocks.BLACK_WATTLE_TRAPDOOR);

        createBrickworkBlockRecipe(exporter, StoneBlockSets.STONE_TILES.base(), StoneBlockSets.STUCCO.base(), StoneBlockSets.STONE_BRICKWORK.base());
        createBrickworkBlockRecipe(exporter, StoneBlockSets.CALCITE_TILES.base(), StoneBlockSets.STUCCO.base(), StoneBlockSets.CALCITE_BRICKWORK.base());
        createBrickworkBlockRecipe(exporter, Blocks.DEEPSLATE_TILES, StoneBlockSets.STUCCO.base(), StoneBlockSets.DEEPSLATE_BRICKWORK.base());
        createBrickworkBlockRecipe(exporter, StoneBlockSets.BASALT_TILES.base(), StoneBlockSets.STUCCO.base(), StoneBlockSets.BASALT_BRICKWORK.base());
        createBrickworkBlockRecipe(exporter, StoneBlockSets.ANDESITE_TILES.base(), StoneBlockSets.STUCCO.base(), StoneBlockSets.ANDESITE_BRICKWORK.base());
        createBrickworkBlockRecipe(exporter, StoneBlockSets.GRANITE_TILES.base(), StoneBlockSets.STUCCO.base(), StoneBlockSets.GRANITE_BRICKWORK.base());
        createBrickworkBlockRecipe(exporter, StoneBlockSets.DIORITE_TILES.base(), StoneBlockSets.STUCCO.base(), StoneBlockSets.DIORITE_BRICKWORK.base());


        createBrickworkBlockRecipe(exporter, StoneBlockSets.DOLOMITE_TILES.base(), StoneBlockSets.STUCCO.base(), StoneBlockSets.DOLOMITE_BRICKWORK.base());
        createBrickworkBlockRecipe(exporter, StoneBlockSets.HEMATITE_TILES.base(), StoneBlockSets.STUCCO.base(), StoneBlockSets.HEMATITE_BRICKWORK.base());
        createBrickworkBlockRecipe(exporter, StoneBlockSets.GNEISS_TILES.base(), StoneBlockSets.STUCCO.base(), StoneBlockSets.GNEISS_BRICKWORK.base());
        createBrickworkBlockRecipe(exporter, StoneBlockSets.IZHERABAN_TILES.base(), StoneBlockSets.STUCCO.base(), StoneBlockSets.IZHERABAN_BRICKWORK.base());
        createBrickworkBlockRecipe(exporter, StoneBlockSets.LIMESTONE_TILES.base(), StoneBlockSets.STUCCO.base(), StoneBlockSets.LIMESTONE_BRICKWORK.base());
        createBrickworkBlockRecipe(exporter, StoneBlockSets.GALONN_TILES.base(), StoneBlockSets.STUCCO.base(), StoneBlockSets.GALONN_BRICKWORK.base());
        createBrickworkBlockRecipe(exporter, StoneBlockSets.GABBRO_BRICKS.base(), StoneBlockSets.STUCCO.base(), StoneBlockSets.GABBRO_BRICKWORK.base());
        createBrickworkBlockRecipe(exporter, StoneBlockSets.TUFF_TILES.base(), StoneBlockSets.STUCCO.base(), StoneBlockSets.TUFF_BRICKWORK.base());
        createBrickworkBlockRecipe(exporter, StoneBlockSets.BLACKSTONE_TILES.base(), StoneBlockSets.STUCCO.base(), StoneBlockSets.BLACKSTONE_BRICKWORK.base());
        createBrickworkBlockRecipe(exporter, StoneBlockSets.TAN_CLAY_BRICKS.base(), StoneBlockSets.PLASTER.base(), StoneBlockSets.TAN_CLAY_BRICKWORK.base());
        createBrickworkBlockRecipe(exporter, StoneBlockSets.MIXED_STONES.base(), StoneBlockSets.STUCCO.base(), StoneBlockSets.MIXED_STONES_BRICKWORK.base());
        createBrickworkBlockRecipe(exporter, StoneBlockSets.MEDGON.base(), StoneBlockSets.STUCCO.base(), StoneBlockSets.MEDGON_BRICKWORK.base());

        createCenterSurroundRecipe(exporter, Items.BRICK, Items.BLUE_DYE, OtherBlockSets.BLUE_ROOF_TILES.block().asItem(), 8);
        createCenterSurroundRecipe(exporter, OtherBlockSets.BLUE_ROOF_TILES.block().asItem(), Items.WHITE_DYE, OtherBlockSets.LIGHT_BLUE_ROOF_TILES.block().asItem(), 8);
        createCenterSurroundRecipe(exporter, OtherBlockSets.BLUE_ROOF_TILES.block().asItem(), Items.LIGHT_GRAY_DYE, OtherBlockSets.BRIGHT_BLUE_ROOF_TILES.block().asItem(), 8);
        createCenterSurroundRecipe(exporter, OtherBlockSets.BLUE_ROOF_TILES.block().asItem(), Items.GRAY_DYE, OtherBlockSets.OFF_BLUE_ROOF_TILES.block().asItem(), 8);
        createCenterSurroundRecipe(exporter, OtherBlockSets.BLUE_ROOF_TILES.block().asItem(), Items.BLACK_DYE, OtherBlockSets.DARK_BLUE_ROOF_TILES.block().asItem(), 8);

        createCenterSurroundRecipe(exporter, Items.BRICK, Items.BROWN_DYE, OtherBlockSets.BROWN_ROOF_TILES.block().asItem(), 8);
        createCenterSurroundRecipe(exporter, OtherBlockSets.BROWN_ROOF_TILES.block().asItem(), Items.GRAY_DYE, OtherBlockSets.OFF_BROWN_ROOF_TILES.block().asItem(), 8);
        createCenterSurroundRecipe(exporter, OtherBlockSets.BROWN_ROOF_TILES.block().asItem(), Items.BLACK_DYE, OtherBlockSets.DARK_BROWN_ROOF_TILES.block().asItem(), 8);

        createCenterSurroundRecipe(exporter, Items.BRICK, Items.CYAN_DYE, OtherBlockSets.CYAN_ROOF_TILES.block().asItem(), 8);
        createCenterSurroundRecipe(exporter, OtherBlockSets.CYAN_ROOF_TILES.block().asItem(), Items.WHITE_DYE, OtherBlockSets.LIGHT_CYAN_ROOF_TILES.block().asItem(), 8);
        createCenterSurroundRecipe(exporter, OtherBlockSets.CYAN_ROOF_TILES.block().asItem(), Items.LIGHT_GRAY_DYE, OtherBlockSets.BRIGHT_CYAN_ROOF_TILES.block().asItem(), 8);
        createCenterSurroundRecipe(exporter, OtherBlockSets.CYAN_ROOF_TILES.block().asItem(), Items.GRAY_DYE, OtherBlockSets.OFF_CYAN_ROOF_TILES.block().asItem(), 8);
        createCenterSurroundRecipe(exporter, OtherBlockSets.CYAN_ROOF_TILES.block().asItem(), Items.BLACK_DYE, OtherBlockSets.DARK_CYAN_ROOF_TILES.block().asItem(), 8);

        createCenterSurroundRecipe(exporter, Items.BRICK, Items.GRAY_DYE, OtherBlockSets.GRAY_ROOF_TILES.block().asItem(), 8);
        createCenterSurroundRecipe(exporter, OtherBlockSets.GRAY_ROOF_TILES.block().asItem(), Items.WHITE_DYE, OtherBlockSets.LIGHT_GRAY_ROOF_TILES.block().asItem(), 8);
        createCenterSurroundRecipe(exporter, OtherBlockSets.GRAY_ROOF_TILES.block().asItem(), Items.GRAY_DYE, OtherBlockSets.OFF_GRAY_ROOF_TILES.block().asItem(), 8);
        createCenterSurroundRecipe(exporter, OtherBlockSets.GRAY_ROOF_TILES.block().asItem(), Items.BLACK_DYE, OtherBlockSets.DARK_GRAY_ROOF_TILES.block().asItem(), 8);

        createCenterSurroundRecipe(exporter, Items.BRICK, Items.GREEN_DYE, OtherBlockSets.GREEN_ROOF_TILES.block().asItem(), 8);
        createCenterSurroundRecipe(exporter, OtherBlockSets.GREEN_ROOF_TILES.block().asItem(), Items.WHITE_DYE, OtherBlockSets.LIGHT_GREEN_ROOF_TILES.block().asItem(), 8);
        createCenterSurroundRecipe(exporter, OtherBlockSets.GREEN_ROOF_TILES.block().asItem(), Items.LIGHT_GRAY_DYE, OtherBlockSets.BRIGHT_GREEN_ROOF_TILES.block().asItem(), 8);
        createCenterSurroundRecipe(exporter, OtherBlockSets.GREEN_ROOF_TILES.block().asItem(), Items.GRAY_DYE, OtherBlockSets.OFF_GREEN_ROOF_TILES.block().asItem(), 8);
        createCenterSurroundRecipe(exporter, OtherBlockSets.GREEN_ROOF_TILES.block().asItem(), Items.BLACK_DYE, OtherBlockSets.DARK_GREEN_ROOF_TILES.block().asItem(), 8);

        createCenterSurroundRecipe(exporter, Items.BRICK, Items.RED_DYE, OtherBlockSets.RED_ROOF_TILES.block().asItem(), 8);
        createCenterSurroundRecipe(exporter, OtherBlockSets.RED_ROOF_TILES.block().asItem(), Items.WHITE_DYE, OtherBlockSets.LIGHT_RED_ROOF_TILES.block().asItem(), 8);
        createCenterSurroundRecipe(exporter, OtherBlockSets.RED_ROOF_TILES.block().asItem(), Items.LIGHT_GRAY_DYE, OtherBlockSets.BRIGHT_RED_ROOF_TILES.block().asItem(), 8);
        createCenterSurroundRecipe(exporter, OtherBlockSets.RED_ROOF_TILES.block().asItem(), Items.GRAY_DYE, OtherBlockSets.OFF_RED_ROOF_TILES.block().asItem(), 8);
        createCenterSurroundRecipe(exporter, OtherBlockSets.RED_ROOF_TILES.block().asItem(), Items.BLACK_DYE, OtherBlockSets.DARK_RED_ROOF_TILES.block().asItem(), 8);

        createCenterSurroundRecipe(exporter, Items.BRICK, Items.YELLOW_DYE, OtherBlockSets.YELLOW_ROOF_TILES.block().asItem(), 8);
        createCenterSurroundRecipe(exporter, OtherBlockSets.YELLOW_ROOF_TILES.block().asItem(), Items.WHITE_DYE, OtherBlockSets.LIGHT_YELLOW_ROOF_TILES.block().asItem(), 8);
        createCenterSurroundRecipe(exporter, OtherBlockSets.YELLOW_ROOF_TILES.block().asItem(), Items.LIGHT_GRAY_DYE, OtherBlockSets.BRIGHT_YELLOW_ROOF_TILES.block().asItem(), 8);
        createCenterSurroundRecipe(exporter, OtherBlockSets.YELLOW_ROOF_TILES.block().asItem(), Items.GRAY_DYE, OtherBlockSets.OFF_YELLOW_ROOF_TILES.block().asItem(), 8);
        createCenterSurroundRecipe(exporter, OtherBlockSets.YELLOW_ROOF_TILES.block().asItem(), Items.BLACK_DYE, OtherBlockSets.DARK_YELLOW_ROOF_TILES.block().asItem(), 8);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, StoneBlockSets.TAN_CLAY_BRICKS.base(), 5)
                .pattern(" B ")
                .pattern("BPB")
                .pattern(" B ")
                .define('P', StoneBlockSets.PLASTER.base())
                .define('B', Items.BRICKS)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(StoneBlockSets.PLASTER.base()),
                        NeoForgeRecipeProvider.has(StoneBlockSets.PLASTER.base()))
                .save(exporter);
        //endregion

        //region SMITHING
        createDaggerRecipeTag(exporter, Items.STICK, TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")), ModWeaponItems.WOODEN_DAGGER);
        createDaggerRecipeTag(exporter, Items.STICK, TagKey.create(Registries.ITEM, ResourceLocation.parse("stone_tool_materials")), ModWeaponItems.STONE_DAGGER);
        createDaggerRecipe(exporter, Items.STICK, Items.DIAMOND, ModWeaponItems.DIAMOND_DAGGER);

        createSpearRecipeTag(exporter, Items.STICK, TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")), ModWeaponItems.WOODEN_SPEAR);
        createSpearRecipeTag(exporter, Items.STICK, TagKey.create(Registries.ITEM, ResourceLocation.parse("stone_tool_materials")), ModWeaponItems.STONE_SPEAR);
        createSpearRecipe(exporter, Items.STICK, Items.DIAMOND, ModWeaponItems.DIAMOND_SPEAR);

        createToolSetRecipes(exporter, Items.STICK, ModResourceItems.BRONZE_INGOT, ModToolItems.BRONZE_PICKAXE, ModToolItems.BRONZE_AXE, ModToolItems.BRONZE_SHOVEL, ModToolItems.BRONZE_HOE);

        createToolSetRecipes(exporter, Items.STICK, ModResourceItems.CRUDE_INGOT, ModToolItems.CRUDE_PICKAXE, ModToolItems.CRUDE_AXE, ModToolItems.CRUDE_SHOVEL, ModToolItems.CRUDE_HOE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModResourceItems.FABRIC, 2)
                .pattern("sss")
                .pattern("sss")
                .define('s', Items.STRING)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.STRING),
                        NeoForgeRecipeProvider.has(Items.STRING))
                .save(exporter);

        createBucketRecipe(exporter, Items.IRON_INGOT, Items.BUCKET);

        createMetalsRecipe(exporter, ModResourceItems.TIN_NUGGET, ModResourceItems.TIN_INGOT, ModBlocks.TIN_BLOCK);
        createMetalsRecipe(exporter, ModResourceItems.LEAD_NUGGET, ModResourceItems.LEAD_INGOT, ModBlocks.LEAD_BLOCK);
        createMetalsRecipe(exporter, ModResourceItems.SILVER_NUGGET, ModResourceItems.SILVER_INGOT, ModBlocks.SILVER_BLOCK);
        createMetalsRecipe(exporter, ModResourceItems.MITHRIL_NUGGET, ModResourceItems.MITHRIL_INGOT, ModBlocks.MITHRIL_BLOCK);

        createMetalsRecipe(exporter, ModResourceItems.BRONZE_NUGGET, ModResourceItems.BRONZE_INGOT, ModBlocks.BRONZE_BLOCK);
        createMetalsRecipe(exporter, ModResourceItems.CRUDE_NUGGET, ModResourceItems.CRUDE_INGOT, ModBlocks.CRUDE_BLOCK);
        createMetalsRecipe(exporter, ModResourceItems.BURZUM_STEEL_NUGGET, ModResourceItems.BURZUM_STEEL_INGOT, ModBlocks.BURZUM_STEEL_BLOCK);
        createMetalsRecipe(exporter, ModResourceItems.STEEL_NUGGET, ModResourceItems.STEEL_INGOT, ModBlocks.STEEL_BLOCK);
        createMetalsRecipe(exporter, ModResourceItems.EDHEL_STEEL_NUGGET, ModResourceItems.EDHEL_STEEL_INGOT, ModBlocks.EDHEL_STEEL_BLOCK);
        createMetalsRecipe(exporter, ModResourceItems.KHAZAD_STEEL_NUGGET, ModResourceItems.KHAZAD_STEEL_INGOT, ModBlocks.KHAZAD_STEEL_BLOCK);
        //endregion

        //region SEEDS
        createSeedsRecipe(exporter, ModFoodItems.TOMATO, ModResourceItems.TOMATO_SEEDS);
        createSeedsRecipe(exporter, ModFoodItems.BELL_PEPPER, ModResourceItems.BELL_PEPPER_SEEDS);
        createSeedsRecipe(exporter, ModFoodItems.CUCUMBER, ModResourceItems.CUCUMBER_SEEDS);
        createSeedsRecipe(exporter, ModFoodItems.LETTUCE, ModResourceItems.LETTUCE_SEEDS);
        createSeedsRecipe(exporter, ModResourceItems.PIPEWEED, ModResourceItems.PIPEWEED_SEEDS);
        createSeedsRecipe(exporter, ModResourceItems.FLAX, ModResourceItems.FLAX_SEEDS);
        //endregion

        //region FOOD
        createCookedFoodRecipes(exporter, ModFoodItems.RAW_VENISON, ModFoodItems.COOKED_VENISON);
        createCookedFoodRecipes(exporter, ModFoodItems.RAW_HORSE, ModFoodItems.COOKED_HORSE);
        createCookedFoodRecipes(exporter, ModFoodItems.RAW_SWAN, ModFoodItems.COOKED_SWAN);
        createCookedFoodRecipes(exporter, ModFoodItems.MEAT_SKEWER, ModFoodItems.COOKED_MEAT_SKEWER);
        createCookedFoodRecipes(exporter, ModFoodItems.VEGETABLE_SKEWER, ModFoodItems.COOKED_VEGETABLE_SKEWER);
        createCookedFoodRecipes(exporter, Items.EGG, ModFoodItems.BOILED_EGG);
        //endregion


        SpecialRecipeBuilder.special(CustomArmorDyeRecipe::new).save(exporter, "custom_armor_dye");
        SpecialRecipeBuilder.special(ArmorHoodRecipe::new).save(exporter, "custom_armor_hood");
        SpecialRecipeBuilder.special(ArmorHoodRemovalRecipe::new).save(exporter, "custom_armor_hood_removal");
        SpecialRecipeBuilder.special(ArmorCapeRecipe::new).save(exporter, "custom_armor_cape");
        SpecialRecipeBuilder.special(ArmorCapeRemovalRecipe::new).save(exporter, "custom_armor_cape_removal");
        SpecialRecipeBuilder.special(MountArmorAddonRemovalRecipe::new).save(exporter, "custom_mount_armor_addon_removal");
        SpecialRecipeBuilder.special(MountArmorSideSkullAddonRecipe::new).save(exporter, "custom_mount_armor_side_skull_addon");
        SpecialRecipeBuilder.special(MountArmorTopSkullAddonRecipe::new).save(exporter, "custom_mount_armor_top_skull_addon");

        //region Alloying
        AlloyRecipeJsonBuilder.createAlloyRecipe(RecipeCategory.MISC, "bronze", INGOT_LIQUID_VALUE * 4)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "copper")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "copper")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "copper")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "tin")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.COPPER_INGOT),
                        NeoForgeRecipeProvider.has(Items.COPPER_INGOT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bronze" + "_from_alloying"));

        AlloyRecipeJsonBuilder.createAlloyRecipe(RecipeCategory.MISC, "crude", INGOT_LIQUID_VALUE * 3)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "copper")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "copper")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "tin")))
                .input(ModResourceItems.ASH)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.COPPER_INGOT),
                        NeoForgeRecipeProvider.has(Items.COPPER_INGOT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "crude" + "_from_alloying"));

        AlloyRecipeJsonBuilder.createAlloyRecipe(RecipeCategory.MISC, "steel", INGOT_LIQUID_VALUE * 3)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "iron")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "iron")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "iron")))
                .input(Items.COAL)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.IRON_INGOT),
                        NeoForgeRecipeProvider.has(Items.IRON_INGOT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "steel" + "_from_alloying_tags"));

        AlloyRecipeJsonBuilder.createAlloyRecipe(RecipeCategory.MISC, "khazad_steel", INGOT_LIQUID_VALUE * 3)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "iron")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "iron")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "lead")))
                .input(Items.COAL)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.IRON_INGOT),
                        NeoForgeRecipeProvider.has(Items.IRON_INGOT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "khazad_steel" + "_from_alloying_tags"));

        AlloyRecipeJsonBuilder.createAlloyRecipe(RecipeCategory.MISC, "edhel_steel", INGOT_LIQUID_VALUE * 3)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "iron")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "iron")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "iron")))
                .input(ModResourceItems.SILVER_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.IRON_INGOT),
                        NeoForgeRecipeProvider.has(Items.IRON_INGOT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "edhel_steel" + "_from_alloying_tags"));

        AlloyRecipeJsonBuilder.createAlloyRecipe(RecipeCategory.MISC, "burzum_steel", INGOT_LIQUID_VALUE * 3)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "iron")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "iron")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "lead")))
                .input(ModResourceItems.ASH)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.IRON_INGOT),
                        NeoForgeRecipeProvider.has(Items.IRON_INGOT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "burzum_steel" + "_from_alloying_tags"));

        HotMetalsModel.nuggets.forEach(nugget -> {
            //createMeltRecipe(exporter, nugget, Registries.ITEM.getId(nugget).getPath().replace("_nugget", ""), INGOT_LIQUID_VALUE / 9);
        });
        HotMetalsModel.shapesTag.forEach(shape -> {
            createAnvilShapingRecipeTag(exporter, shape.tagKey(), shape.output(), shape.amount());
        });
        HotMetalsModel.shapesItem.forEach(shape -> {
            createAnvilShapingRecipeItem(exporter, shape.item(), shape.output(), shape.amount());
        });

        createMeltBulkRecipeTag(exporter, TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "copper")), "copper");
        createMeltBulkRecipeTag(exporter, TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "tin")), "tin");

        createMeltBulkRecipe(exporter, ModResourceItems.BRONZE_INGOT, "bronze");
        createMeltBulkRecipe(exporter, ModResourceItems.CRUDE_INGOT, "crude");

        createMeltBulkRecipeTag(exporter, TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "lead")), "lead");
        createMeltBulkRecipeTag(exporter, TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "silver")), "silver");
        createMeltBulkRecipeTag(exporter, TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "iron")), "iron");
        createMeltBulkRecipeTag(exporter, TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "gold")), "gold");

        createMeltBulkRecipe(exporter, ModResourceItems.STEEL_INGOT, "steel");
        createMeltBulkRecipe(exporter, ModResourceItems.KHAZAD_STEEL_INGOT, "khazad_steel");
        createMeltBulkRecipe(exporter, ModResourceItems.EDHEL_STEEL_INGOT, "edhel_steel");
        createMeltBulkRecipe(exporter, ModResourceItems.BURZUM_STEEL_INGOT, "burzum_steel");

        createMeltBulkRecipeTag(exporter, TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "mithril")), "mithril");

        createMeltBulkRecipe(exporter, Items.NETHERITE_INGOT, "netherite");

        createAnvilRecipe(exporter, ModBlocks.STEEL_BLOCK.asItem(), ModResourceItems.STEEL_INGOT, ModDecorativeItems.TREATED_ANVIL);
        createAnvilRecipe(exporter, ModBlocks.KHAZAD_STEEL_BLOCK.asItem(), ModResourceItems.KHAZAD_STEEL_INGOT, ModDecorativeItems.DWARVEN_TREATED_ANVIL);
        createAnvilRecipe(exporter, ModBlocks.EDHEL_STEEL_BLOCK.asItem(), ModResourceItems.EDHEL_STEEL_INGOT, ModDecorativeItems.ELVEN_TREATED_ANVIL);
        createAnvilRecipe(exporter, ModBlocks.BURZUM_STEEL_BLOCK.asItem(), ModResourceItems.BURZUM_STEEL_INGOT, ModDecorativeItems.ORCISH_TREATED_ANVIL);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeItems.BELLOWS, 1)
                .pattern(" PS")
                .pattern("PFF")
                .pattern("TPS")
                .define('P', TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .define('S', Items.STICK)
                .define('F', Items.LEATHER)
                .define('T', ModResourceItems.TIN_INGOT)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.TIN_INGOT),
                        NeoForgeRecipeProvider.has(ModResourceItems.TIN_INGOT))
                .save(exporter);

        createWattleRecipes(exporter, Items.BRICKS,
                ModBlocks.WATTLE_AND_BRICK, ModBlocks.WATTLE_AND_BRICK_CROSS, ModBlocks.WATTLE_AND_BRICK_RIGHT,
                ModBlocks.WATTLE_AND_BRICK_LEFT, ModBlocks.WATTLE_AND_BRICK_PILLAR, ModBlocks.WATTLE_AND_BRICK_DIAMOND);

        createWattleRecipes(exporter, StoneBlockSets.WHITE_DAUB.base().asItem(),
                ModBlocks.WATTLE_AND_WHITE_DAUB, ModBlocks.WATTLE_AND_WHITE_DAUB_CROSS, ModBlocks.WATTLE_AND_WHITE_DAUB_RIGHT,
                ModBlocks.WATTLE_AND_WHITE_DAUB_LEFT, ModBlocks.WATTLE_AND_WHITE_DAUB_PILLAR, ModBlocks.WATTLE_AND_WHITE_DAUB_DIAMOND);

        createWattleRecipes(exporter, StoneBlockSets.DARK_DAUB.base().asItem(),
                ModBlocks.DARK_WATTLE_AND_DARK_DAUB, ModBlocks.DARK_WATTLE_AND_DARK_DAUB_CROSS, ModBlocks.DARK_WATTLE_AND_DARK_DAUB_RIGHT,
                ModBlocks.DARK_WATTLE_AND_DARK_DAUB_LEFT, ModBlocks.DARK_WATTLE_AND_DARK_DAUB_PILLAR, ModBlocks.DARK_WATTLE_AND_DARK_DAUB_DIAMOND);

        createWattleRecipes(exporter, StoneBlockSets.YELLOW_DAUB.base().asItem(),
                ModBlocks.WATTLE_AND_YELLOW_DAUB, ModBlocks.WATTLE_AND_YELLOW_DAUB_CROSS, ModBlocks.WATTLE_AND_YELLOW_DAUB_RIGHT,
                ModBlocks.WATTLE_AND_YELLOW_DAUB_LEFT, ModBlocks.WATTLE_AND_YELLOW_DAUB_PILLAR, ModBlocks.WATTLE_AND_YELLOW_DAUB_DIAMOND);

        createCenterSurroundRecipe(exporter, ModBlocks.WATTLE_AND_WHITE_DAUB.asItem(), Items.BLACK_DYE, ModBlocks.BLACK_WATTLE_AND_WHITE_DAUB.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModBlocks.WATTLE_AND_WHITE_DAUB_CROSS.asItem(), Items.BLACK_DYE, ModBlocks.BLACK_WATTLE_AND_WHITE_DAUB_CROSS.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModBlocks.WATTLE_AND_WHITE_DAUB_RIGHT.asItem(), Items.BLACK_DYE, ModBlocks.BLACK_WATTLE_AND_WHITE_DAUB_RIGHT.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModBlocks.WATTLE_AND_WHITE_DAUB_LEFT.asItem(), Items.BLACK_DYE, ModBlocks.BLACK_WATTLE_AND_WHITE_DAUB_LEFT.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModBlocks.WATTLE_AND_WHITE_DAUB_PILLAR.asItem(), Items.BLACK_DYE, ModBlocks.BLACK_WATTLE_AND_WHITE_DAUB_PILLAR.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModBlocks.WATTLE_AND_WHITE_DAUB_DIAMOND.asItem(), Items.BLACK_DYE, ModBlocks.BLACK_WATTLE_AND_WHITE_DAUB_DIAMOND.asItem(), 8);

        createCenterSurroundRecipe(exporter, ModBlocks.WATTLE_AND_WHITE_DAUB.asItem(), Items.GREEN_DYE, ModBlocks.GREEN_WATTLE_AND_WHITE_DAUB.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModBlocks.WATTLE_AND_WHITE_DAUB_CROSS.asItem(), Items.GREEN_DYE, ModBlocks.GREEN_WATTLE_AND_WHITE_DAUB_CROSS.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModBlocks.WATTLE_AND_WHITE_DAUB_RIGHT.asItem(), Items.GREEN_DYE, ModBlocks.GREEN_WATTLE_AND_WHITE_DAUB_RIGHT.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModBlocks.WATTLE_AND_WHITE_DAUB_LEFT.asItem(), Items.GREEN_DYE, ModBlocks.GREEN_WATTLE_AND_WHITE_DAUB_LEFT.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModBlocks.WATTLE_AND_WHITE_DAUB_PILLAR.asItem(), Items.GREEN_DYE, ModBlocks.GREEN_WATTLE_AND_WHITE_DAUB_PILLAR.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModBlocks.WATTLE_AND_WHITE_DAUB_DIAMOND.asItem(), Items.GREEN_DYE, ModBlocks.GREEN_WATTLE_AND_WHITE_DAUB_DIAMOND.asItem(), 8);

        createCenterSurroundRecipe(exporter, ModBlocks.WATTLE_AND_WHITE_DAUB.asItem(), Items.RED_DYE, ModBlocks.RED_WATTLE_AND_WHITE_DAUB.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModBlocks.WATTLE_AND_WHITE_DAUB_CROSS.asItem(), Items.RED_DYE, ModBlocks.RED_WATTLE_AND_WHITE_DAUB_CROSS.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModBlocks.WATTLE_AND_WHITE_DAUB_RIGHT.asItem(), Items.RED_DYE, ModBlocks.RED_WATTLE_AND_WHITE_DAUB_RIGHT.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModBlocks.WATTLE_AND_WHITE_DAUB_LEFT.asItem(), Items.RED_DYE, ModBlocks.RED_WATTLE_AND_WHITE_DAUB_LEFT.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModBlocks.WATTLE_AND_WHITE_DAUB_PILLAR.asItem(), Items.RED_DYE, ModBlocks.RED_WATTLE_AND_WHITE_DAUB_PILLAR.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModBlocks.WATTLE_AND_WHITE_DAUB_DIAMOND.asItem(), Items.RED_DYE, ModBlocks.RED_WATTLE_AND_WHITE_DAUB_DIAMOND.asItem(), 8);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TREATED_STEEL_BARS, 16)
                .pattern("SSS")
                .pattern("SSS")
                .define('S', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "steel_ingots")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.STEEL_INGOT),
                        NeoForgeRecipeProvider.has(ModResourceItems.STEEL_INGOT))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TREATED_STEEL_TRAPDOOR, 2)
                .pattern("NSN")
                .pattern("NSN")
                .define('S', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "steel_ingots")))
                .define('N', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "steel_nuggets")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.STEEL_INGOT),
                        NeoForgeRecipeProvider.has(ModResourceItems.STEEL_INGOT))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TREATED_STEEL_DOOR, 3)
                .pattern("SS")
                .pattern("SS")
                .pattern("SS")
                .define('S', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "steel_ingots")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.STEEL_INGOT),
                        NeoForgeRecipeProvider.has(ModResourceItems.STEEL_INGOT))
                .save(exporter);

        createPaneRecipe(exporter, ModResourceItems.SILVER_INGOT, ModBlocks.SILVER_BARS, 16);
        createPaneRecipe(exporter, Items.GOLD_INGOT, ModBlocks.GILDED_BARS, 16);

        createCenterSurroundRecipe(exporter, StoneBlockSets.WHITE_DAUB.base().asItem(), Items.BLACK_DYE, StoneBlockSets.DARK_DAUB.base().asItem(), 8);
        createCenterSurroundRecipe(exporter, StoneBlockSets.WHITE_DAUB.base().asItem(), Items.YELLOW_DYE, StoneBlockSets.YELLOW_DAUB.base().asItem(), 8);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, OtherBlockSets.TREATED_WOOD.block(), 6)
                .pattern("PPP")
                .pattern("PHP")
                .pattern("PPP")
                .define('P', TagKey.create(Registries.ITEM, ResourceLocation.parse("logs")))
                .define('H', Items.HONEYCOMB)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.HONEYCOMB),
                        NeoForgeRecipeProvider.has(Items.HONEYCOMB))
                .save(exporter);

        createBrickRecipe(exporter, OtherBlockSets.TREATED_WOOD.block().asItem(), OtherBlockSets.TREATED_WOOD_BEAM.block(), 3);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, OtherBlockSets.TREATED_WOOD_PLANKS.block(), 4)
                .requires(OtherBlockSets.TREATED_WOOD.block())
                .unlockedBy(NeoForgeRecipeProvider.getHasName(OtherBlockSets.TREATED_WOOD.block()),
                        NeoForgeRecipeProvider.has(OtherBlockSets.TREATED_WOOD.block()))
                .save(exporter);

        createBrickRecipe(exporter, OtherBlockSets.TREATED_WOOD_PLANKS.block().asItem(), OtherBlockSets.TREATED_WOOD_PANELS.block(), 4);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, OtherBlockSets.TREATED_WOOD_CARVED_BEAM.block(), 1)
                .pattern("S")
                .pattern("S")
                .define('S', OtherBlockSets.TREATED_WOOD_BEAM.slab())
                .unlockedBy(NeoForgeRecipeProvider.getHasName(OtherBlockSets.TREATED_WOOD_BEAM.slab()),
                        NeoForgeRecipeProvider.has(OtherBlockSets.TREATED_WOOD_BEAM.slab()))
                .save(exporter);

        createBrickRecipe(exporter, OtherBlockSets.TREATED_WOOD_PANELS.block().asItem(), OtherBlockSets.TREATED_WOOD_TILING.block(), 4);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.CANDLE_HEAP, 1)
                .pattern("CCC")
                .pattern("CCC")
                .define('C', TagKey.create(Registries.ITEM, ResourceLocation.parse("candles")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.CANDLE),
                        NeoForgeRecipeProvider.has(Items.CANDLE))
                .save(exporter);

        createStatueRecipe(exporter, StoneBlockSets.POLISHED_CALCITE.base(), Blocks.CALCITE, ModBlocks.CALCITE_WALL, ModDecorativeBlocks.CALCITE_STATUE);
        createStatueRecipe(exporter, StoneBlockSets.POLISHED_GALONN.base(), StoneBlockSets.GALONN.base(), StoneBlockSets.GALONN.wall(), ModDecorativeBlocks.GALONN_STATUE);
        createStatueRecipe(exporter, StoneBlockSets.POLISHED_GONLUIN.base(), StoneBlockSets.GONLUIN.base(), StoneBlockSets.GONLUIN.wall(), ModDecorativeBlocks.GONLUIN_STATUE);
        createStatueRecipe(exporter, Blocks.POLISHED_TUFF, Blocks.TUFF, Blocks.TUFF_WALL, ModDecorativeBlocks.TUFF_STATUE);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.MEDGON_SPIKE, 1)
                .pattern("M  ")
                .pattern("MM ")
                .pattern("PMP")
                .define('M', StoneBlockSets.MEDGON.base())
                .define('P', StoneBlockSets.POLISHED_MEDGON.base())
                .unlockedBy(NeoForgeRecipeProvider.getHasName(StoneBlockSets.MEDGON.base()),
                        NeoForgeRecipeProvider.has(StoneBlockSets.MEDGON.base()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.WATTLE_AND_BRICK_WINDOW, 4)
                .pattern("BSB")
                .pattern("SGS")
                .pattern("BSB")
                .define('B', Items.BRICKS)
                .define('G', Items.GLASS)
                .define('S', Items.STICK)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.BRICKS),
                        NeoForgeRecipeProvider.has(Items.BRICKS))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.WATTLE_FRAMED_WINDOW, 2)
                .pattern("SSS")
                .pattern("SGS")
                .pattern("SSS")
                .define('G', Items.GLASS)
                .define('S', Items.STICK)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.STICK),
                        NeoForgeRecipeProvider.has(Items.STICK))
                .save(exporter);

        createCenterSurroundRecipe(exporter, ModDecorativeBlocks.WATTLE_FRAMED_WINDOW.asItem(), Items.GRAY_DYE, ModDecorativeBlocks.DARK_WATTLE_FRAMED_WINDOW.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModDecorativeBlocks.WATTLE_FRAMED_WINDOW.asItem(), Items.BLACK_DYE, ModDecorativeBlocks.BLACK_WATTLE_FRAMED_WINDOW.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModDecorativeBlocks.WATTLE_FRAMED_WINDOW.asItem(), Items.GREEN_DYE, ModDecorativeBlocks.GREEN_WATTLE_FRAMED_WINDOW.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModDecorativeBlocks.WATTLE_FRAMED_WINDOW.asItem(), Items.RED_DYE, ModDecorativeBlocks.RED_WATTLE_FRAMED_WINDOW.asItem(), 8);
        createCenterSurroundRecipe(exporter, ModDecorativeBlocks.WATTLE_FRAMED_WINDOW.asItem(), Items.WHITE_DYE, ModDecorativeBlocks.WHITE_WATTLE_FRAMED_WINDOW.asItem(), 8);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.MUD_BRICK_ROUND_WINDOW, 4)
                .pattern("MBM")
                .pattern("BGB")
                .pattern("MBM")
                .define('M', Items.MUD_BRICKS)
                .define('G', Items.GLASS)
                .define('B', Items.BRICK)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.BRICKS),
                        NeoForgeRecipeProvider.has(Items.BRICKS))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.WHITE_DAUB_ROUND_WINDOW, 4)
                .pattern("WSW")
                .pattern("SGS")
                .pattern("WSW")
                .define('W', StoneBlockSets.WHITE_DAUB.base())
                .define('G', Items.GLASS)
                .define('S', Items.STICK)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(StoneBlockSets.WHITE_DAUB.base()),
                        NeoForgeRecipeProvider.has(StoneBlockSets.WHITE_DAUB.base()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.YELLOW_DAUB_ROUND_WINDOW, 4)
                .pattern("WSW")
                .pattern("SGS")
                .pattern("WSW")
                .define('W', StoneBlockSets.YELLOW_DAUB.base())
                .define('G', Items.GLASS)
                .define('S', Items.STICK)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(StoneBlockSets.YELLOW_DAUB.base()),
                        NeoForgeRecipeProvider.has(StoneBlockSets.YELLOW_DAUB.base()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.PLASTER_ROUND_WINDOW, 4)
                .pattern("WSW")
                .pattern("SGS")
                .pattern("WSW")
                .define('W', StoneBlockSets.PLASTER.base())
                .define('G', Items.GLASS)
                .define('S', Items.STICK)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(StoneBlockSets.PLASTER.base()),
                        NeoForgeRecipeProvider.has(StoneBlockSets.PLASTER.base()))
                .save(exporter);

        createCushionRecipe(exporter, Blocks.BLUE_WOOL, ModDecorativeBlocks.BLUE_CUSHION);
        createDyeableItemRecipe(exporter, ModDecorativeBlocks.BLUE_CUSHION, Items.GRAY_DYE, ModDecorativeBlocks.DARK_BLUE_CUSHION);
        createCushionRecipe(exporter, Blocks.BROWN_WOOL, ModDecorativeBlocks.BROWN_CUSHION);
        createDyeableItemRecipe(exporter, ModDecorativeBlocks.BROWN_CUSHION, Items.GRAY_DYE, ModDecorativeBlocks.DARK_BROWN_CUSHION);
        createCushionRecipe(exporter, Blocks.GREEN_WOOL, ModDecorativeBlocks.GREEN_CUSHION);
        createDyeableItemRecipe(exporter, ModDecorativeBlocks.GREEN_CUSHION, Items.GRAY_DYE, ModDecorativeBlocks.DARK_GREEN_CUSHION);
        createCushionRecipe(exporter, Blocks.RED_WOOL, ModDecorativeBlocks.RED_CUSHION);
        createDyeableItemRecipe(exporter, ModDecorativeBlocks.RED_CUSHION, Items.GRAY_DYE, ModDecorativeBlocks.DARK_RED_CUSHION);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.ROPE_LADDER, 3)
                .pattern("R R")
                .pattern("RSR")
                .pattern("R R")
                .define('R', ModDecorativeBlocks.ROPE)
                .define('S', Items.STRING)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModDecorativeBlocks.ROPE),
                        NeoForgeRecipeProvider.has(ModDecorativeBlocks.ROPE))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.FANCY_BED, 1)
                .pattern("FFW")
                .pattern("FFW")
                .pattern("PPP")
                .define('W', TagKey.create(Registries.ITEM, ResourceLocation.parse("wool")))
                .define('F', ModResourceItems.FABRIC)
                .define('P', TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.STRAW_BED, 1)
                .pattern("SSS")
                .pattern("PPP")
                .define('S', ModResourceItems.STRAW)
                .define('P', TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.STRAW),
                        NeoForgeRecipeProvider.has(ModResourceItems.STRAW))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.FUR_BED, 1)
                .pattern("FFF")
                .pattern("PPP")
                .define('F', ModResourceItems.FUR)
                .define('P', TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FUR),
                        NeoForgeRecipeProvider.has(ModResourceItems.FUR))
                .save(exporter);

        createCenterSurroundRecipe(exporter, ModResourceItems.SILVER_NUGGET, Items.TORCH, ModDecorativeItems.SILVER_LANTERN, 1);
        createCenterSurroundRecipe(exporter, ModResourceItems.KHAZAD_STEEL_NUGGET, Items.TORCH, ModDecorativeItems.DWARVEN_LANTERN, 1);
        createCenterSurroundRecipe(exporter, ModResourceItems.EDHEL_STEEL_NUGGET, Items.TORCH, ModDecorativeItems.ELVEN_LANTERN, 1);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeItems.CRYSTAL_LAMP, 1)
                .pattern("NGN")
                .pattern("GLG")
                .pattern("NIN")
                .define('N', ModResourceItems.BRONZE_NUGGET)
                .define('I', ModResourceItems.BRONZE_INGOT)
                .define('L', TagKey.create(Registries.ITEM, ResourceLocation.parse("candles")))
                .define('G', ModResourceItems.QUARTZ_SHARD)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.QUARTZ_SHARD),
                        NeoForgeRecipeProvider.has(ModResourceItems.QUARTZ_SHARD))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeItems.SCONCE, 4)
                .pattern("NTN")
                .pattern(" I ")
                .define('N', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "steel_nuggets")))
                .define('I', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "steel_ingots")))
                .define('T', Items.TORCH)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.TORCH),
                        NeoForgeRecipeProvider.has(Items.TORCH))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeItems.GILDED_SCONCE, 4)
                .pattern("NTN")
                .pattern(" I ")
                .define('N', Items.GOLD_NUGGET)
                .define('I', Items.GOLD_INGOT)
                .define('T', Items.TORCH)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.TORCH),
                        NeoForgeRecipeProvider.has(Items.TORCH))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeItems.ORCISH_SCONCE, 2)
                .pattern("NTN")
                .pattern(" S ")
                .define('N', ModResourceItems.CRUDE_NUGGET)
                .define('S', Items.STICK)
                .define('T', Items.TORCH)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.TORCH),
                        NeoForgeRecipeProvider.has(Items.TORCH))
                .save(exporter);

        createWoodStoolRecipe(exporter, OtherBlockSets.TREATED_WOOD_PLANKS.block().asItem(), ModDecorativeBlocks.TREATED_WOOD_STOOL);
        createWoodBenchRecipe(exporter, OtherBlockSets.TREATED_WOOD_PLANKS.block().asItem(), ModDecorativeBlocks.TREATED_WOOD_BENCH);
        createWoodTableRecipe(exporter, OtherBlockSets.TREATED_WOOD_PLANKS.block().asItem(), ModDecorativeBlocks.TREATED_WOOD_TABLE);
        createWoodChairRecipe(exporter, OtherBlockSets.TREATED_WOOD_PLANKS.block().asItem(), ModDecorativeBlocks.TREATED_WOOD_CHAIR);
        createWoodLadderRecipe(exporter, OtherBlockSets.TREATED_WOOD_PLANKS.block().asItem(), ModDecorativeBlocks.TREATED_WOOD_LADDER);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.LARCH_HOBBIT_DOOR, 1)
                .pattern("LLL")
                .pattern("LSL")
                .pattern("LLL")
                .define('S', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "steel_ingots")))
                .define('L', WoodBlockSets.LARCH.planks())
                .unlockedBy(NeoForgeRecipeProvider.getHasName(WoodBlockSets.LARCH.planks()),
                        NeoForgeRecipeProvider.has(WoodBlockSets.LARCH.planks()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.SPRUCE_HOBBIT_DOOR, 1)
                .pattern("LSL")
                .pattern("SLL")
                .pattern("LSL")
                .define('S', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "steel_ingots")))
                .define('L', Items.SPRUCE_PLANKS)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.SPRUCE_PLANKS),
                        NeoForgeRecipeProvider.has(Items.SPRUCE_PLANKS))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.BLUE_HOBBIT_DOOR, 1)
                .pattern(" BG")
                .pattern("BDG")
                .pattern(" BG")
                .define('D', ModDecorativeBlocks.LARCH_HOBBIT_DOOR)
                .define('B', Items.BLUE_DYE)
                .define('G', Items.GOLD_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModDecorativeBlocks.LARCH_HOBBIT_DOOR),
                        NeoForgeRecipeProvider.has(ModDecorativeBlocks.LARCH_HOBBIT_DOOR))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.GREEN_HOBBIT_DOOR, 1)
                .pattern(" BG")
                .pattern("BDG")
                .pattern(" BG")
                .define('D', ModDecorativeBlocks.LARCH_HOBBIT_DOOR)
                .define('B', Items.GREEN_DYE)
                .define('G', Items.GOLD_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModDecorativeBlocks.LARCH_HOBBIT_DOOR),
                        NeoForgeRecipeProvider.has(ModDecorativeBlocks.LARCH_HOBBIT_DOOR))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.LIGHT_BLUE_HOBBIT_DOOR, 1)
                .pattern(" B ")
                .pattern("BDB")
                .pattern(" B ")
                .define('D', ModDecorativeBlocks.LARCH_HOBBIT_DOOR)
                .define('B', Items.LIGHT_BLUE_DYE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModDecorativeBlocks.LARCH_HOBBIT_DOOR),
                        NeoForgeRecipeProvider.has(ModDecorativeBlocks.LARCH_HOBBIT_DOOR))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.RED_HOBBIT_DOOR, 1)
                .pattern(" BG")
                .pattern("BDG")
                .pattern(" BG")
                .define('D', ModDecorativeBlocks.LARCH_HOBBIT_DOOR)
                .define('B', Items.RED_DYE)
                .define('G', Items.GOLD_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModDecorativeBlocks.LARCH_HOBBIT_DOOR),
                        NeoForgeRecipeProvider.has(ModDecorativeBlocks.LARCH_HOBBIT_DOOR))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.YELLOW_HOBBIT_DOOR, 1)
                .pattern(" BG")
                .pattern("BDG")
                .pattern(" BG")
                .define('D', ModDecorativeBlocks.LARCH_HOBBIT_DOOR)
                .define('B', Items.YELLOW_DYE)
                .define('G', Items.GOLD_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModDecorativeBlocks.LARCH_HOBBIT_DOOR),
                        NeoForgeRecipeProvider.has(ModDecorativeBlocks.LARCH_HOBBIT_DOOR))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.TALL_BLACK_PINE_DOOR, 1)
                .pattern("SP")
                .pattern("PP")
                .pattern("SP")
                .define('S', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "steel_nuggets")))
                .define('P', WoodBlockSets.BLACK_PINE.planks())
                .unlockedBy(NeoForgeRecipeProvider.getHasName(WoodBlockSets.BLACK_PINE.planks()),
                        NeoForgeRecipeProvider.has(WoodBlockSets.BLACK_PINE.planks()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.OAK_STABLE_DOOR, 1)
                .pattern("SPP")
                .pattern("PPP")
                .pattern("SPP")
                .define('S', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "steel_nuggets")))
                .define('P', Items.OAK_PLANKS)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.OAK_PLANKS),
                        NeoForgeRecipeProvider.has(Items.OAK_PLANKS))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.REINFORCED_BLACK_PINE_DOOR, 1)
                .pattern("SPP")
                .pattern("SPS")
                .pattern("SPP")
                .define('S', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "steel_ingots")))
                .define('P', WoodBlockSets.BLACK_PINE.planks())
                .unlockedBy(NeoForgeRecipeProvider.getHasName(WoodBlockSets.BLACK_PINE.planks()),
                        NeoForgeRecipeProvider.has(WoodBlockSets.BLACK_PINE.planks()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.REINFORCED_SPRUCE_DOOR, 1)
                .pattern("SPP")
                .pattern("SPS")
                .pattern("SPP")
                .define('S', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "steel_ingots")))
                .define('P', Items.SPRUCE_PLANKS)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.SPRUCE_PLANKS),
                        NeoForgeRecipeProvider.has(Items.SPRUCE_PLANKS))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.SIMPLE_LARCH_GATE, 1)
                .pattern("SPP")
                .pattern("PPP")
                .pattern("SPP")
                .define('S', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "steel_nuggets")))
                .define('P', WoodBlockSets.LARCH.planks())
                .unlockedBy(NeoForgeRecipeProvider.getHasName(WoodBlockSets.LARCH.planks()),
                        NeoForgeRecipeProvider.has(WoodBlockSets.LARCH.planks()))
                .save(exporter);

        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.RICKETY_SIMPLE_LARCH_DOOR, ModDecorativeBlocks.SIMPLE_LARCH_GATE);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.SPRUCE_STABLE_DOOR, 1)
                .pattern("SPP")
                .pattern("PPP")
                .pattern("SPP")
                .define('S', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "steel_nuggets")))
                .define('P', Items.SPRUCE_PLANKS)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.SPRUCE_PLANKS),
                        NeoForgeRecipeProvider.has(Items.SPRUCE_PLANKS))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.LARGE_STURDY_DOOR, 1)
                .pattern("SPP")
                .pattern("PPP")
                .pattern("SPP")
                .define('S', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "steel_ingots")))
                .define('P', TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.OAK_PLANKS),
                        NeoForgeRecipeProvider.has(Items.OAK_PLANKS))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.GREAT_GONDORIAN_GATE, 1)
                .pattern("LCL")
                .pattern("CCS")
                .pattern("LCL")
                .define('L', WoodBlockSets.BLACK_LEBETHRON.planks())
                .define('C', Items.OXIDIZED_COPPER)
                .define('S', ModResourceItems.STEEL_INGOT)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.OXIDIZED_COPPER),
                        NeoForgeRecipeProvider.has(Items.OXIDIZED_COPPER))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.GREAT_DWARVEN_GATE, 1)
                .pattern("BTB")
                .pattern("BTS")
                .pattern("BTB")
                .define('B', ModResourceItems.BRONZE_INGOT)
                .define('T', OtherBlockSets.TREATED_WOOD.block())
                .define('S', ModResourceItems.KHAZAD_STEEL_INGOT)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.BRONZE_INGOT),
                        NeoForgeRecipeProvider.has(ModResourceItems.BRONZE_INGOT))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.VARNISHED_DWARVEN_DOOR, 1)
                .pattern("TNT")
                .pattern("TTS")
                .pattern("TNT")
                .define('N', ModResourceItems.STEEL_NUGGET)
                .define('T', OtherBlockSets.TREATED_WOOD.block())
                .define('S', ModResourceItems.KHAZAD_STEEL_INGOT)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.BRONZE_INGOT),
                        NeoForgeRecipeProvider.has(ModResourceItems.BRONZE_INGOT))
                .save(exporter);

        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.RUINED_DWARVEN_DOOR, ModDecorativeBlocks.VARNISHED_DWARVEN_DOOR);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.HIDDEN_DWARVEN_DOOR, 1)
                .pattern("SSG")
                .pattern("GDL")
                .pattern("DSS")
                .define('L', Items.LEVER)
                .define('G', StoneBlockSets.SMOOTH_DOLOMITE.base())
                .define('D', StoneBlockSets.DOLOMITE.base())
                .define('S', Items.STONE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(StoneBlockSets.SMOOTH_DOLOMITE.base()),
                        NeoForgeRecipeProvider.has(StoneBlockSets.SMOOTH_DOLOMITE.base()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.GREAT_ELVEN_GATE, 1)
                .pattern("BTB")
                .pattern("BTS")
                .pattern("BTB")
                .define('B', Items.CYAN_DYE)
                .define('T', OtherBlockSets.TREATED_WOOD.block())
                .define('S', ModResourceItems.EDHEL_STEEL_INGOT)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(OtherBlockSets.TREATED_WOOD.block()),
                        NeoForgeRecipeProvider.has(OtherBlockSets.TREATED_WOOD.block()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.GREAT_ORCISH_GATE, 1)
                .pattern("SSS")
                .pattern("SNS")
                .pattern("NNN")
                .define('N', ModBlocks.BURZUM_STEEL_BLOCK)
                .define('S', ModResourceItems.BURZUM_STEEL_INGOT)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.BURZUM_STEEL_INGOT),
                        NeoForgeRecipeProvider.has(ModResourceItems.BURZUM_STEEL_INGOT))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TURF, 4)
                .pattern("MM")
                .pattern("MD")
                .define('M', Items.MOSS_BLOCK)
                .define('D', Items.DIRT)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.MOSS_BLOCK),
                        NeoForgeRecipeProvider.has(Items.MOSS_BLOCK))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GRASSY_DIRT, 4)
                .pattern("DM")
                .pattern("MD")
                .define('M', Items.MOSS_BLOCK)
                .define('D', Items.DIRT)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.MOSS_BLOCK),
                        NeoForgeRecipeProvider.has(Items.MOSS_BLOCK))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PEBBLED_GRASS, 4)
                .pattern("DM")
                .pattern("MD")
                .define('M', Items.MOSS_BLOCK)
                .define('D', TagKey.create(Registries.ITEM, ResourceLocation.parse("stone_crafting_materials")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.MOSS_BLOCK),
                        NeoForgeRecipeProvider.has(Items.MOSS_BLOCK))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SNOWY_DIRT, 4)
                .pattern("DS")
                .pattern("SD")
                .define('D', Items.DIRT)
                .define('S', Items.SNOW_BLOCK)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.DIRT),
                        NeoForgeRecipeProvider.has(Items.DIRT))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COBBLY_DIRT, 4)
                .pattern("DC")
                .pattern("CD")
                .define('D', Items.DIRT)
                .define('C', TagKey.create(Registries.ITEM, ResourceLocation.parse("stone_crafting_materials")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.DIRT),
                        NeoForgeRecipeProvider.has(Items.DIRT))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COBBLY_ASHEN_DIRT, 4)
                .pattern("DC")
                .pattern("CD")
                .define('D', ModBlocks.ASHEN_DIRT)
                .define('C', StoneBlockSets.ASHEN_COBBLESTONE.base())
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.DIRT),
                        NeoForgeRecipeProvider.has(Items.DIRT))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DIRTY_ROOTS, 2)
                .pattern(" R ")
                .pattern("RDR")
                .pattern(" R ")
                .define('D', Items.ROOTED_DIRT)
                .define('R', Items.HANGING_ROOTS)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.ROOTED_DIRT),
                        NeoForgeRecipeProvider.has(Items.ROOTED_DIRT))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.WATERING_CAN, 1)
                .pattern(" N ")
                .pattern("NII")
                .pattern(" II")
                .define('N', ModResourceItems.TIN_NUGGET)
                .define('I', ModResourceItems.TIN_INGOT)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.TIN_INGOT),
                        NeoForgeRecipeProvider.has(ModResourceItems.TIN_INGOT))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.WOODEN_BUCKET, 1)
                .pattern(" R ")
                .pattern("P P")
                .pattern(" P ")
                .define('R', ModDecorativeBlocks.ROPE)
                .define('P', TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModDecorativeBlocks.ROPE),
                        NeoForgeRecipeProvider.has(ModDecorativeBlocks.ROPE))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.TREATED_STEEL_ROD, 1)
                .pattern("S")
                .pattern("S")
                .pattern("S")
                .define('S', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "steel_ingots")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.STEEL_INGOT),
                        NeoForgeRecipeProvider.has(ModResourceItems.STEEL_INGOT))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Items.CHAIN, 4)
                .pattern("N")
                .pattern("I")
                .pattern("N")
                .define('N', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "steel_ingots")))
                .define('I', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "steel_nuggets")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.STEEL_INGOT),
                        NeoForgeRecipeProvider.has(ModResourceItems.STEEL_INGOT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(Items.CHAIN).getPath() + "_alt"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.BRONZE_CHAIN, 4)
                .pattern("N")
                .pattern("I")
                .pattern("N")
                .define('N', ModResourceItems.BRONZE_NUGGET)
                .define('I', ModResourceItems.BRONZE_INGOT)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.BRONZE_INGOT),
                        NeoForgeRecipeProvider.has(ModResourceItems.STEEL_INGOT))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.BRONZE_BROAD_CHAIN, 8)
                .pattern("NN")
                .pattern("II")
                .pattern("NN")
                .define('N', ModResourceItems.BRONZE_NUGGET)
                .define('I', ModResourceItems.BRONZE_INGOT)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.BRONZE_INGOT),
                        NeoForgeRecipeProvider.has(ModResourceItems.STEEL_INGOT))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.SPIKY_CHAIN, 4)
                .pattern(" N ")
                .pattern("NIN")
                .pattern(" N ")
                .define('I', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "steel_ingots")))
                .define('N', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "steel_nuggets")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.STEEL_INGOT),
                        NeoForgeRecipeProvider.has(ModResourceItems.STEEL_INGOT))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModResourceItems.DWARVEN_KEY, 1)
                .pattern("IN")
                .define('N', ModResourceItems.KHAZAD_STEEL_NUGGET)
                .define('I', ModResourceItems.KHAZAD_STEEL_INGOT)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.KHAZAD_STEEL_INGOT),
                        NeoForgeRecipeProvider.has(ModResourceItems.KHAZAD_STEEL_INGOT))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.CHIMNEY, 2)
                .pattern(" B ")
                .pattern(" B ")
                .pattern("PPP")
                .define('B', Items.BRICKS)
                .define('P', StoneBlockSets.POLISHED_DOLOMITE.base())
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.BRICKS),
                        NeoForgeRecipeProvider.has(Items.BRICKS))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.BIG_BRAZIER, 2)
                .pattern("B B")
                .pattern("BCB")
                .pattern("SSS")
                .define('B', ModBlocks.TREATED_STEEL_BARS)
                .define('C', Items.CAMPFIRE)
                .define('S', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "steel_ingots")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.CAMPFIRE),
                        NeoForgeRecipeProvider.has(Items.CAMPFIRE))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.GILDED_BIG_BRAZIER, 2)
                .pattern("B B")
                .pattern("BCB")
                .pattern("SSS")
                .define('B', ModBlocks.GILDED_BARS)
                .define('C', Items.CAMPFIRE)
                .define('S', Items.GOLD_INGOT)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.CAMPFIRE),
                        NeoForgeRecipeProvider.has(Items.CAMPFIRE))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.SMALL_BRAZIER, 2)
                .pattern("BCB")
                .pattern("SSS")
                .define('B', ModBlocks.TREATED_STEEL_BARS)
                .define('C', Items.CAMPFIRE)
                .define('S', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "steel_ingots")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.CAMPFIRE),
                        NeoForgeRecipeProvider.has(Items.CAMPFIRE))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.GILDED_SMALL_BRAZIER, 2)
                .pattern("BCB")
                .pattern("SSS")
                .define('B', ModBlocks.GILDED_BARS)
                .define('C', Items.CAMPFIRE)
                .define('S', Items.GOLD_INGOT)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.CAMPFIRE),
                        NeoForgeRecipeProvider.has(Items.CAMPFIRE))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.FIRE_BOWL, 2)
                .pattern("SCS")
                .pattern("SSS")
                .define('C', Items.CAMPFIRE)
                .define('S', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "steel_ingots")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.CAMPFIRE),
                        NeoForgeRecipeProvider.has(Items.CAMPFIRE))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.BONFIRE, 1)
                .pattern(" L ")
                .pattern("LCL")
                .define('C', Items.CAMPFIRE)
                .define('L', TagKey.create(Registries.ITEM, ResourceLocation.parse("logs")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.CAMPFIRE),
                        NeoForgeRecipeProvider.has(Items.CAMPFIRE))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.GROUND_BOOK, 1)
                .pattern("BSR")
                .define('B', Items.BOOK)
                .define('S', Items.STRING)
                .define('R', Items.RED_DYE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.BOOK),
                        NeoForgeRecipeProvider.has(Items.BOOK))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.DWARVEN_GROUND_BOOK, 1)
                .pattern("BG")
                .define('B', Items.BOOK)
                .define('G', Items.GOLD_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.BOOK),
                        NeoForgeRecipeProvider.has(Items.BOOK))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.SMALL_CRATE, 1)
                .pattern("SSS")
                .pattern("PPP")
                .define('S', TagKey.create(Registries.ITEM, ResourceLocation.parse("wooden_slabs")))
                .define('P', TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.OAK_PLANKS),
                        NeoForgeRecipeProvider.has(Items.OAK_PLANKS))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.THIN_BARREL, 1)
                .pattern("VSV")
                .pattern("V V")
                .pattern("VSV")
                .define('S', TagKey.create(Registries.ITEM, ResourceLocation.parse("wooden_slabs")))
                .define('V', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "wooden_vertical_slabs")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.OAK_SLAB),
                        NeoForgeRecipeProvider.has(Items.OAK_SLAB))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Items.BELL, 1)
                .pattern("VSV")
                .pattern("VGV")
                .define('S', Items.STICK)
                .define('V', ModBlocks.STONE_VERTICAL_SLAB)
                .define('G', Items.GOLD_INGOT)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.GOLD_INGOT),
                        NeoForgeRecipeProvider.has(Items.GOLD_INGOT))
                .save(exporter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModNatureBlocks.STICKY_SNOW, 8)
                .requires(Items.SNOWBALL, 8)
                .requires(Items.WATER_BUCKET, 1)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.SNOWBALL),
                        NeoForgeRecipeProvider.has(Items.SNOWBALL))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModNatureBlocks.STICKY_ICE, 4)
                .pattern("II")
                .pattern("II")
                .define('I', Items.ICE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.ICE),
                        NeoForgeRecipeProvider.has(Items.ICE))
                .save(exporter);

        createBannerPatternRecipe(exporter, ModResourceItems.PIPEWEED, ModResourceItems.PIPEWEED_BANNER_PATTERN);
        createBannerPatternRecipe(exporter, ModNatureBlocks.LEBETHRON_SAPLING.asItem(), ModResourceItems.GONDOR_BANNER_PATTERN);
        createBannerPatternRecipe(exporter, WoodBlockSets.MALLORN.sapling().asItem(), ModResourceItems.LOTHLORIEN_BANNER_PATTERN);
        createBannerPatternRecipe(exporter, Items.MAGMA_BLOCK, ModResourceItems.MORDOR_BANNER_PATTERN);
        createBannerPatternRecipe(exporter, Items.HAY_BLOCK, ModResourceItems.ROHAN_BANNER_PATTERN);
        createBannerPatternRecipe(exporter, Items.BONE, ModResourceItems.MISTY_MOUNTAINS_ORCS_BANNER_PATTERN);
        createBannerPatternRecipe(exporter, Items.WHITE_DYE, ModResourceItems.ISENGARD_BANNER_PATTERN);
        createBannerPatternRecipe(exporter, ModToolItems.DWARVEN_SMITHING_HAMMER, ModResourceItems.ANVIL_BANNER_PATTERN);
        createBannerPatternRecipe(exporter, ModResourceItems.BRONZE_INGOT, ModResourceItems.BELL_BANNER_PATTERN);
        createBannerPatternRecipe(exporter, Items.GOLD_NUGGET, ModResourceItems.DWARF_CROWN_BANNER_PATTERN);
        createBannerPatternRecipe(exporter, Items.BOW, ModResourceItems.BOW_BANNER_PATTERN);

        createBrickRecipe(exporter, ModBlocks.POINTED_DOLOMITE.asItem(), StoneBlockSets.DOLOMITE.base(), 1);
        createBrickRecipe(exporter, ModBlocks.POINTED_GALONN.asItem(), StoneBlockSets.GALONN.base(), 1);
        createBrickRecipe(exporter, ModBlocks.POINTED_IZHERABAN.asItem(), StoneBlockSets.IZHERABAN.base(), 1);
        createBrickRecipe(exporter, ModBlocks.POINTED_LIMESTONE.asItem(), StoneBlockSets.LIMESTONE.base(), 1);

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks"))), RecipeCategory.BUILDING_BLOCKS, WoodBlockSets.SCORCHED.planks(), 0.35f, 100)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.OAK_PLANKS),
                        NeoForgeRecipeProvider.has(Items.OAK_PLANKS)).save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.BLOCK.getKey(WoodBlockSets.SCORCHED.planks()).getPath() + "_from_smoking"));
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(TagKey.create(Registries.ITEM, ResourceLocation.parse("logs"))), RecipeCategory.BUILDING_BLOCKS, WoodBlockSets.SCORCHED.log(), 0.35f, 100)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.OAK_LOG),
                        NeoForgeRecipeProvider.has(Items.OAK_LOG)).save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.BLOCK.getKey(WoodBlockSets.SCORCHED.log()).getPath() + "_from_smoking"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModNatureBlocks.SHORT_ICICLES, 4)
                .pattern("III")
                .pattern(" I ")
                .define('I', Items.ICE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.ICE),
                        NeoForgeRecipeProvider.has(Items.ICE))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModNatureBlocks.DROOPING_ICICLES, 4)
                .pattern("III")
                .pattern("III")
                .pattern(" I ")
                .define('I', Items.ICE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.ICE),
                        NeoForgeRecipeProvider.has(Items.ICE))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Items.BUCKET, 1)
                .pattern("T T")
                .pattern("T T")
                .pattern(" T ")
                .define('T', ModResourceItems.TIN_INGOT)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.TIN_INGOT),
                        NeoForgeRecipeProvider.has(ModResourceItems.TIN_INGOT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(Items.BUCKET).getPath() + "_alt"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Items.CAULDRON, 1)
                .pattern("T T")
                .pattern("T T")
                .pattern("TBT")
                .define('T', ModResourceItems.TIN_INGOT)
                .define('B', ModBlocks.TIN_BLOCK)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.TIN_INGOT),
                        NeoForgeRecipeProvider.has(ModResourceItems.TIN_INGOT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(Items.CAULDRON).getPath() + "_alt"));

        createCenterSurroundRecipe(exporter, Blocks.TUFF.asItem(), Items.COPPER_INGOT, StoneBlockSets.GREEN_TUFF.base().asItem(), 8);

        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.BROWN_JUG, Items.CLAY);
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.LARGE_JUG, Items.CLAY);
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.GRAY_POT, Items.CLAY);

        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.BROWN_JAR, Items.CLAY);
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.CLAY_JAR, Items.CLAY);
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.GRAY_JAR, Items.CLAY);

        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.AMPHORA, Items.CLAY);
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.BROWN_AMPHORA, Items.CLAY);
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.GRAY_VASE, Items.CLAY);

        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.BROWN_FAT_POT, Items.CLAY);
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.FAT_POT, Items.CLAY);
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.GRAY_FAT_POT, Items.CLAY);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.POT_OF_GOLD, 1)
                .pattern(" G ")
                .pattern("GGG")
                .pattern(" P ")
                .define('P', ModDecorativeBlocks.FAT_POT)
                .define('G', ModResourceItems.GOLD_COIN)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.GOLD_COIN),
                        NeoForgeRecipeProvider.has(ModResourceItems.GOLD_COIN))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModNatureBlocks.AZALEA_FLOWER_GROWTH.asItem(), 8)
                .pattern("lll")
                .pattern("lll")
                .define('l', Items.FLOWERING_AZALEA_LEAVES)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.FLOWERING_AZALEA_LEAVES),
                        NeoForgeRecipeProvider.has(Items.FLOWERING_AZALEA_LEAVES))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModNatureBlocks.DRY_GROWTH.asItem(), 4)
                .pattern("sss")
                .pattern("sss")
                .define('s', Items.STICK)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.STICK),
                        NeoForgeRecipeProvider.has(Items.STICK))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModNatureBlocks.GREEN_GROWTH.asItem(), 8)
                .pattern("lll")
                .pattern("lll")
                .define('l', TagKey.create(Registries.ITEM, ResourceLocation.parse("leaves")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.OAK_LEAVES),
                        NeoForgeRecipeProvider.has(Items.OAK_LEAVES))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModNatureBlocks.IVY_GROWTH.asItem(), 6)
                .pattern("sls")
                .pattern("sls")
                .define('s', Items.STICK)
                .define('l', TagKey.create(Registries.ITEM, ResourceLocation.parse("leaves")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.OAK_LEAVES),
                        NeoForgeRecipeProvider.has(Items.OAK_LEAVES))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModNatureBlocks.LILAC_FLOWER_GROWTH.asItem(), 8)
                .pattern("lfl")
                .pattern("lfl")
                .define('f', Items.LILAC)
                .define('l', TagKey.create(Registries.ITEM, ResourceLocation.parse("leaves")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.OAK_LEAVES),
                        NeoForgeRecipeProvider.has(Items.OAK_LEAVES))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModNatureBlocks.PINK_FLOWER_GROWTH.asItem(), 8)
                .pattern("lfl")
                .pattern("lfl")
                .define('f', ModNatureBlocks.PINK_FLOWERS)
                .define('l', TagKey.create(Registries.ITEM, ResourceLocation.parse("leaves")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModNatureBlocks.PINK_FLOWERS),
                        NeoForgeRecipeProvider.has(ModNatureBlocks.PINK_FLOWERS))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModNatureBlocks.RED_FLOWER_GROWTH.asItem(), 8)
                .pattern("lfl")
                .pattern("lfl")
                .define('f', ModNatureBlocks.RED_FLOWERS)
                .define('l', TagKey.create(Registries.ITEM, ResourceLocation.parse("leaves")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModNatureBlocks.RED_FLOWERS),
                        NeoForgeRecipeProvider.has(ModNatureBlocks.RED_FLOWERS))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModNatureBlocks.WHITE_FLOWER_GROWTH.asItem(), 8)
                .pattern("lfl")
                .pattern("lfl")
                .define('f', ModNatureBlocks.WHITE_FLOWERS)
                .define('l', TagKey.create(Registries.ITEM, ResourceLocation.parse("leaves")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModNatureBlocks.WHITE_FLOWERS),
                        NeoForgeRecipeProvider.has(ModNatureBlocks.WHITE_FLOWERS))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModNatureBlocks.YELLOW_FLOWER_GROWTH.asItem(), 8)
                .pattern("lfl")
                .pattern("lfl")
                .define('f', ModNatureBlocks.YELLOW_FLOWERS)
                .define('l', TagKey.create(Registries.ITEM, ResourceLocation.parse("leaves")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModNatureBlocks.YELLOW_FLOWERS),
                        NeoForgeRecipeProvider.has(ModNatureBlocks.YELLOW_FLOWERS))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModNatureBlocks.FROZEN_GROWTH.asItem(), 8)
                .pattern("sis")
                .pattern("sis")
                .define('i', ModNatureBlocks.STICKY_SNOW)
                .define('s', ModNatureBlocks.DRY_GROWTH)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModNatureBlocks.DRY_GROWTH),
                        NeoForgeRecipeProvider.has(ModNatureBlocks.DRY_GROWTH))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.GOLDEN_CHALICE, 1)
                .pattern("I")
                .pattern("N")
                .pattern("N")
                .define('I', Items.GOLD_INGOT)
                .define('N', Items.GOLD_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.GOLD_INGOT),
                        NeoForgeRecipeProvider.has(Items.GOLD_INGOT))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.COPPER_TREASURE_HEAP_LAYER, 1)
                .pattern("NNN")
                .define('N', ModResourceItems.COPPER_COIN)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.COPPER_COIN),
                        NeoForgeRecipeProvider.has(ModResourceItems.COPPER_COIN))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.SILVER_TREASURE_HEAP_LAYER, 1)
                .pattern("NNN")
                .define('N', ModResourceItems.SILVER_COIN)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SILVER_COIN),
                        NeoForgeRecipeProvider.has(ModResourceItems.SILVER_COIN))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.GOLD_TREASURE_HEAP_LAYER, 1)
                .pattern("NNN")
                .define('N', ModResourceItems.GOLD_COIN)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.GOLD_COIN),
                        NeoForgeRecipeProvider.has(ModResourceItems.GOLD_COIN))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.COPPER_COIN_PILE, 1)
                .pattern("NN")
                .pattern("NN")
                .define('N', ModResourceItems.COPPER_COIN)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.COPPER_COIN),
                        NeoForgeRecipeProvider.has(ModResourceItems.COPPER_COIN))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.SILVER_COIN_PILE, 1)
                .pattern("NN")
                .pattern("NN")
                .define('N', ModResourceItems.SILVER_COIN)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SILVER_COIN),
                        NeoForgeRecipeProvider.has(ModResourceItems.SILVER_COIN))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModDecorativeBlocks.GOLD_COIN_PILE, 1)
                .pattern("NN")
                .pattern("NN")
                .define('N', ModResourceItems.GOLD_COIN)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.GOLD_COIN),
                        NeoForgeRecipeProvider.has(ModResourceItems.GOLD_COIN))
                .save(exporter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModResourceItems.COPPER_COIN, 3)
                .requires(ModDecorativeBlocks.COPPER_TREASURE_HEAP_LAYER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModDecorativeBlocks.COPPER_TREASURE_HEAP_LAYER),
                        NeoForgeRecipeProvider.has(ModDecorativeBlocks.COPPER_TREASURE_HEAP_LAYER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "copper_coin_from_treasure"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModResourceItems.SILVER_COIN, 3)
                .requires(ModDecorativeBlocks.SILVER_TREASURE_HEAP_LAYER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModDecorativeBlocks.SILVER_TREASURE_HEAP_LAYER),
                        NeoForgeRecipeProvider.has(ModDecorativeBlocks.SILVER_TREASURE_HEAP_LAYER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "silver_coin_from_treasure"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModResourceItems.GOLD_COIN, 3)
                .requires(ModDecorativeBlocks.GOLD_TREASURE_HEAP_LAYER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModDecorativeBlocks.GOLD_TREASURE_HEAP_LAYER),
                        NeoForgeRecipeProvider.has(ModDecorativeBlocks.GOLD_TREASURE_HEAP_LAYER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "gold_nugget_from_treasure"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModResourceItems.COPPER_COIN, 4)
                .requires(ModDecorativeBlocks.COPPER_COIN_PILE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModDecorativeBlocks.COPPER_COIN_PILE),
                        NeoForgeRecipeProvider.has(ModDecorativeBlocks.COPPER_COIN_PILE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "copper_coin_from_pile"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModResourceItems.SILVER_COIN, 4)
                .requires(ModDecorativeBlocks.SILVER_COIN_PILE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModDecorativeBlocks.SILVER_COIN_PILE),
                        NeoForgeRecipeProvider.has(ModDecorativeBlocks.SILVER_COIN_PILE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "silver_coin_from_pile"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModResourceItems.GOLD_COIN, 4)
                .requires(ModDecorativeBlocks.GOLD_COIN_PILE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModDecorativeBlocks.GOLD_COIN_PILE),
                        NeoForgeRecipeProvider.has(ModDecorativeBlocks.GOLD_COIN_PILE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "gold_nugget_from_pile"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModNatureBlocks.SHORT_BULRUSH, 2)
                .requires(ModNatureBlocks.TALL_BULRUSH)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModNatureBlocks.TALL_BULRUSH),
                        NeoForgeRecipeProvider.has(ModNatureBlocks.TALL_BULRUSH))
                .save(exporter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModNatureBlocks.SHORT_CATTAILS, 2)
                .requires(ModNatureBlocks.TALL_CATTAILS)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModNatureBlocks.TALL_CATTAILS),
                        NeoForgeRecipeProvider.has(ModNatureBlocks.TALL_CATTAILS))
                .save(exporter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModResourceItems.GOLD_COIN, 4)
                .requires(ModDecorativeBlocks.POT_OF_GOLD)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModDecorativeBlocks.POT_OF_GOLD),
                        NeoForgeRecipeProvider.has(ModDecorativeBlocks.POT_OF_GOLD))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "gold_from_pot_of_gold"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModWeaponItems.HELD_BANNER, 1)
                .pattern("WWW")
                .pattern("WWW")
                .pattern("WSW")
                .define('W', TagKey.create(Registries.ITEM, ResourceLocation.parse("wool")))
                .define('S', Items.STICK)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.GOLD_COIN),
                        NeoForgeRecipeProvider.has(ModResourceItems.GOLD_COIN))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, StoneBlockSets.SLATE.base(), 4)
                .pattern("DS")
                .pattern("SD")
                .define('D', Items.DEEPSLATE)
                .define('S', Items.STONE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.DEEPSLATE),
                        NeoForgeRecipeProvider.has(Items.DEEPSLATE))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, StoneBlockSets.BLUE_TUFF.base(), 4)
                .pattern("TG")
                .pattern("GT")
                .define('T', Items.TUFF)
                .define('G', StoneBlockSets.GONLUIN.base())
                .unlockedBy(NeoForgeRecipeProvider.getHasName(StoneBlockSets.GONLUIN.base()),
                        NeoForgeRecipeProvider.has(StoneBlockSets.GONLUIN.base()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, StoneBlockSets.HEMATITE.base(), 4)
                .pattern("SI")
                .pattern("IS")
                .define('S', Items.STONE)
                .define('I', StoneBlockSets.IRONSTONE.base())
                .unlockedBy(NeoForgeRecipeProvider.getHasName(StoneBlockSets.IRONSTONE.base()),
                        NeoForgeRecipeProvider.has(StoneBlockSets.IRONSTONE.base()))
                .save(exporter);

        createSmokingRecipe(exporter, Items.SHORT_GRASS, ModNatureBlocks.SCORCHED_GRASS.asItem());
        createSmokingRecipe(exporter, ModNatureBlocks.GRASS_TUFT.asItem(), ModNatureBlocks.SCORCHED_TUFT.asItem());
        createSmokingRecipe(exporter, ModNatureBlocks.GREEN_SHRUB.asItem(), ModNatureBlocks.SCORCHED_SHRUB.asItem());
        //endregion

        //region SMOKING-ONLY
        createSmokingRecipe(exporter, ModResourceItems.PIPEWEED, ModResourceItems.DRIED_PIPEWEED);
        //endregion

        SpecialRecipeBuilder.special(CustomItemDecorationRecipe::new).save(exporter, "custom_shield_decoration");
    }

    //region BLOCK RECIPE METHODS

    private void createBrickRecipe(RecipeOutput exporter, Item input, Block output, int count) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, count)
                .pattern("ll")
                .pattern("ll")
                .define('l', input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createPillarRecipe(RecipeOutput exporter, Block input, Block output, int count) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, count)
                .pattern("l")
                .pattern("l")
                .pattern("l")
                .define('l', input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createChiseledRecipe(RecipeOutput exporter, Block input, Block output, int count) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, count)
                .pattern("l")
                .pattern("l")
                .define('l', input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createCutPolishedRecipe(RecipeOutput exporter, Block input, Block output, int count) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, count)
                .pattern("l")
                .pattern("l")
                .define('l', input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createMossyRecipe(RecipeOutput exporter, Block input, Block output) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, output, 1)
                .requires(input)
                .requires(Items.VINE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.BLOCK.getKey(output).getPath() + "_vine"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, output, 1)
                .requires(input)
                .requires(Blocks.MOSS_BLOCK)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.BLOCK.getKey(output).getPath() + "_moss"));
    }

    private void createSmeltingRecipe(RecipeOutput exporter, Item input, Item output) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), RecipeCategory.BUILDING_BLOCKS, output, 0.1f, 200)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createSmeltingRecipeIdentifier(RecipeOutput exporter, Item input, Item output) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), RecipeCategory.BUILDING_BLOCKS, output, 0.1f, 200)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(output).getPath() + "_from_smelting"));
    }

    private void createMeltBulkRecipe(RecipeOutput exporter, Item input, String output) {
        createMeltRecipe(exporter, input, output, 1, INGOT_LIQUID_VALUE);
        createMeltRecipe(exporter, input, output, 2, INGOT_LIQUID_VALUE);
        createMeltRecipe(exporter, input, output, 3, INGOT_LIQUID_VALUE);
        createMeltRecipe(exporter, input, output, 4, INGOT_LIQUID_VALUE);
    }

    private void createMeltRecipe(RecipeOutput exporter, Item input, String output, int ingots, int amount) {
        switch (ingots){
            case 1 -> AlloyRecipeJsonBuilder.createAlloyRecipe(RecipeCategory.MISC, output, amount)
                    .input(input)
                    .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                            NeoForgeRecipeProvider.has(input))
                    .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, output + "_from_melting_1_" + BuiltInRegistries.ITEM.getKey(input).getPath()));
            case 2 -> AlloyRecipeJsonBuilder.createAlloyRecipe(RecipeCategory.MISC, output, amount * 2)
                    .input(input)
                    .input(input)
                    .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                            NeoForgeRecipeProvider.has(input))
                    .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, output + "_from_melting_2_" + BuiltInRegistries.ITEM.getKey(input).getPath()));
            case 3 -> AlloyRecipeJsonBuilder.createAlloyRecipe(RecipeCategory.MISC, output, amount* 3)
                    .input(input)
                    .input(input)
                    .input(input)
                    .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                            NeoForgeRecipeProvider.has(input))
                    .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, output + "_from_melting_3_" + BuiltInRegistries.ITEM.getKey(input).getPath()));
            case 4 -> AlloyRecipeJsonBuilder.createAlloyRecipe(RecipeCategory.MISC, output, amount * 4)
                    .input(input)
                    .input(input)
                    .input(input)
                    .input(input)
                    .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                            NeoForgeRecipeProvider.has(input))
                    .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, output + "_from_melting_4_" + BuiltInRegistries.ITEM.getKey(input).getPath()));
        }

    }

    private void createMeltBulkRecipeTag(RecipeOutput exporter, TagKey input, String output) {
        createMeltRecipeTag(exporter, input, output, 1, INGOT_LIQUID_VALUE);
        createMeltRecipeTag(exporter, input, output, 2, INGOT_LIQUID_VALUE);
        createMeltRecipeTag(exporter, input, output, 3, INGOT_LIQUID_VALUE);
        createMeltRecipeTag(exporter, input, output, 4, INGOT_LIQUID_VALUE);
    }

    private void createMeltRecipeTag(RecipeOutput exporter, TagKey input, String output, int ingots, int amount) {
        switch (ingots){
            case 1 -> AlloyRecipeJsonBuilder.createAlloyRecipe(RecipeCategory.MISC, output, amount)
                    .input(input)
                    .unlockedBy(NeoForgeRecipeProvider.getHasName(ModDecorativeItems.FORGE),
                            NeoForgeRecipeProvider.has(ModDecorativeItems.FORGE))
                    .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, output + "_from_melting_1_" + input.location().getPath()));
            case 2 -> AlloyRecipeJsonBuilder.createAlloyRecipe(RecipeCategory.MISC, output, amount * 2)
                    .input(input)
                    .input(input)
                    .unlockedBy(NeoForgeRecipeProvider.getHasName(ModDecorativeItems.FORGE),
                            NeoForgeRecipeProvider.has(ModDecorativeItems.FORGE))
                    .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, output + "_from_melting_2_" + input.location().getPath()));
            case 3 -> AlloyRecipeJsonBuilder.createAlloyRecipe(RecipeCategory.MISC, output, amount* 3)
                    .input(input)
                    .input(input)
                    .input(input)
                    .unlockedBy(NeoForgeRecipeProvider.getHasName(ModDecorativeItems.FORGE),
                            NeoForgeRecipeProvider.has(ModDecorativeItems.FORGE))
                    .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, output + "_from_melting_3_" + input.location().getPath()));
            case 4 -> AlloyRecipeJsonBuilder.createAlloyRecipe(RecipeCategory.MISC, output, amount * 4)
                    .input(input)
                    .input(input)
                    .input(input)
                    .input(input)
                    .unlockedBy(NeoForgeRecipeProvider.getHasName(ModDecorativeItems.FORGE),
                            NeoForgeRecipeProvider.has(ModDecorativeItems.FORGE))
                    .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, output + "_from_melting_4_" + input.location().getPath()));
        }
    }

    private void createAnvilShapingRecipeTag(RecipeOutput exporter, TagKey input, Item output, int amount) {
        AnvilShapingRecipeJsonBuilder.createAnvilShapingRecipe(RecipeCategory.MISC, output, amount)
                .input(input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.COPPER_INGOT),
                        NeoForgeRecipeProvider.has(Items.COPPER_INGOT))
                .save(exporter);
    }

    private void createAnvilShapingRecipeItem(RecipeOutput exporter, Item input, Item output, int amount) {
        AnvilShapingRecipeJsonBuilder.createAnvilShapingRecipe(RecipeCategory.MISC, output, amount)
                .input(input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.COPPER_INGOT),
                        NeoForgeRecipeProvider.has(Items.COPPER_INGOT))
                .save(exporter);
    }

    private void createAnvilRecipe(RecipeOutput exporter, Item inputBlock, Item inputIngot, Item output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 1)
                .pattern("IBI")
                .pattern(" I ")
                .pattern("LLL")
                .define('I', inputIngot)
                .define('B', inputBlock)
                .define('L', TagKey.create(Registries.ITEM, ResourceLocation.parse("logs")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(inputIngot),
                        NeoForgeRecipeProvider.has(inputIngot))
                .save(exporter);
    }

    private void createStairsRecipe(RecipeOutput exporter, Block input, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 4)
                .pattern("l  ")
                .pattern("ll ")
                .pattern("lll")
                .define('l', input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createSlabsRecipe(RecipeOutput exporter, Block input, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 6)
                .pattern("lll")
                .define('l', input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createSlabsFromVerticalRecipe(RecipeOutput exporter, Block input, Block output) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, output, 1)
                .requires(input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.BLOCK.getKey(input).getPath() + "_from_vertical"));
    }

    private void createVerticalSlabsRecipe(RecipeOutput exporter, Block input, Block output) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, output, 1)
                .requires(input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createDoorRecipe(RecipeOutput exporter, Block input, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 3)
                .pattern("ll")
                .pattern("ll")
                .pattern("ll")
                .define('l', input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createTrapdoorRecipe(RecipeOutput exporter, Block input, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 2)
                .pattern("lll")
                .pattern("lll")
                .define('l', input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createWallsRecipe(RecipeOutput exporter, Block input, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 6)
                .pattern("lll")
                .pattern("lll")
                .define('l', input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createCenterSurroundRecipe(RecipeOutput exporter, Item surroundInput, Item centerItem, Item output, int count) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, count)
                .pattern("BBB")
                .pattern("BDB")
                .pattern("BBB")
                .define('B', surroundInput)
                .define('D', centerItem)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(surroundInput),
                        NeoForgeRecipeProvider.has(surroundInput))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(output).getPath() + "_alt"));
    }

    private void createDyeableItemRecipe(RecipeOutput exporter, Block blockInput, Item dyeItem, Block output) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, output, 1)
                .requires(blockInput)
                .requires(dyeItem)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(blockInput),
                        NeoForgeRecipeProvider.has(blockInput))
                .save(exporter);
    }

    private void createPaneRecipe(RecipeOutput exporter, Item blockInput, Block output, int count) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, count)
                .pattern("BBB")
                .pattern("BBB")
                .define('B', blockInput)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(blockInput),
                        NeoForgeRecipeProvider.has(blockInput))
                .save(exporter);
    }

    private void createWoodStoolRecipe(RecipeOutput exporter, Item inputPlanks, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 3)
                .pattern("PP")
                .pattern("SS")
                .define('P', inputPlanks)
                .define('S', Items.STICK)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(inputPlanks),
                        NeoForgeRecipeProvider.has(inputPlanks))
                .save(exporter);
    }

    private void createWoodBenchRecipe(RecipeOutput exporter, Item inputPlanks, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 3)
                .pattern("PPP")
                .pattern("S S")
                .define('P', inputPlanks)
                .define('S', Items.STICK)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(inputPlanks),
                        NeoForgeRecipeProvider.has(inputPlanks))
                .save(exporter);
    }

    private void createWoodTableRecipe(RecipeOutput exporter, Item inputPlanks, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 3)
                .pattern("PPP")
                .pattern("S S")
                .pattern("S S")
                .define('P', inputPlanks)
                .define('S', Items.STICK)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(inputPlanks),
                        NeoForgeRecipeProvider.has(inputPlanks))
                .save(exporter);
    }

    private void createWoodChairRecipe(RecipeOutput exporter, Item inputPlanks, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 3)
                .pattern("P  ")
                .pattern("PPP")
                .pattern("S S")
                .define('P', inputPlanks)
                .define('S', Items.STICK)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(inputPlanks),
                        NeoForgeRecipeProvider.has(inputPlanks))
                .save(exporter);
    }

    private void createWoodLadderRecipe(RecipeOutput exporter, Item inputPlanks, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 3)
                .pattern("P P")
                .pattern("PSP")
                .pattern("P P")
                .define('P', inputPlanks)
                .define('S', Items.STICK)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(inputPlanks),
                        NeoForgeRecipeProvider.has(inputPlanks))
                .save(exporter);
    }

    private void createStoneStoolRecipe(RecipeOutput exporter, Item input, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 1)
                .pattern("SSS")
                .pattern("S S")
                .define('S', input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createStoneTableRecipe(RecipeOutput exporter, Item input, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 1)
                .pattern("SSS")
                .pattern(" S ")
                .pattern(" S ")
                .define('S', input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createStoneChairRecipe(RecipeOutput exporter, Item input, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 1)
                .pattern("S  ")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createLayerRecipe(RecipeOutput exporter, Item input, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 6)
                .pattern("BBB")
                .define('B', input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createButtonRecipe(RecipeOutput exporter, Item input, Block output) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, output, 1)
                .requires(input, 1)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createPressurePlateRecipe(RecipeOutput exporter, Item input, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 1)
                .pattern("BB")
                .define('B', input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createFenceRecipe(RecipeOutput exporter, Item input, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 3)
                .pattern("lsl")
                .pattern("lsl")
                .define('l', input)
                .define('s', Items.STICK)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.STICK),
                        NeoForgeRecipeProvider.has(Items.STICK))
                .save(exporter);
    }

    private void createGildedBlockRecipe(RecipeOutput exporter, Block input, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 1)
                .pattern(" N ")
                .pattern("NBN")
                .pattern(" N ")
                .define('B', input)
                .define('N', Items.GOLD_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createBrickworkBlockRecipe(RecipeOutput exporter, Block input, Block inputBinder, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 2)
                .pattern("SB")
                .define('S', inputBinder)
                .define('B', input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createWattleRecipes(RecipeOutput exporter, Item input, Block outputBase,
                                     Block outputCross, Block outputRight, Block outputLeft, Block outputPillar, Block outputDiamond) {
        createBaseWattleRecipe(exporter, input, outputBase);
        createCrossWattleRecipe(exporter, input, outputCross);
        createRightWattleRecipe(exporter, input, outputRight);
        createLeftWattleRecipe(exporter, input, outputLeft);
        createPillarWattleRecipe(exporter, input, outputPillar);
        createDiamondWattleRecipe(exporter, input, outputDiamond);
    }

    private void createBaseWattleRecipe(RecipeOutput exporter, Item input, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 1)
                .pattern(" S ")
                .pattern("SDS")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('D', input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createCrossWattleRecipe(RecipeOutput exporter, Item input, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 4)
                .pattern("SDS")
                .pattern("DSD")
                .pattern("SDS")
                .define('S', Items.STICK)
                .define('D', input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createRightWattleRecipe(RecipeOutput exporter, Item input, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 6)
                .pattern("DDS")
                .pattern("DSD")
                .pattern("SDD")
                .define('S', Items.STICK)
                .define('D', input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createLeftWattleRecipe(RecipeOutput exporter, Item input, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 6)
                .pattern("SDD")
                .pattern("DSD")
                .pattern("DDS")
                .define('S', Items.STICK)
                .define('D', input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createPillarWattleRecipe(RecipeOutput exporter, Item input, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 6)
                .pattern("DSD")
                .pattern("DSD")
                .pattern("DSD")
                .define('S', Items.STICK)
                .define('D', input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createDiamondWattleRecipe(RecipeOutput exporter, Item input, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 5)
                .pattern("DSD")
                .pattern("SDS")
                .pattern("DSD")
                .define('S', Items.STICK)
                .define('D', input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createStatueRecipe(RecipeOutput exporter, Block polishedInput, Block stoneInput, Block wallInput, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 1)
                .pattern("WSW")
                .pattern("WSW")
                .pattern("WPW")
                .define('W', wallInput)
                .define('S', stoneInput)
                .define('P', polishedInput)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(polishedInput),
                        NeoForgeRecipeProvider.has(polishedInput))
                .save(exporter);
    }

    private void createCushionRecipe(RecipeOutput exporter, Block woolBlock, Block output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 1)
                .pattern("WW")
                .pattern("PP")
                .define('W', woolBlock)
                .define('P', TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(woolBlock),
                        NeoForgeRecipeProvider.has(woolBlock))
                .save(exporter);
    }

    private void createBannerPatternRecipe(RecipeOutput exporter, Item input, Item output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output, 1)
                .pattern("PF")
                .pattern("BI")
                .define('I', input)
                .define('B', Items.BLACK_DYE)
                .define('F', Items.FEATHER)
                .define('P', Items.PAPER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.PAPER),
                        NeoForgeRecipeProvider.has(Items.PAPER))
                .save(exporter);
    }
    //endregion

    //region ITEM RECIPE METHODS
    private void createSeedsRecipe(RecipeOutput exporter, Item input, Item output) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, output, 1)
                .requires(input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

    private void createPickaxeRecipe(RecipeOutput exporter, Item inputRod, Item inputMaterial, Item output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output, 1)
                .pattern("MMM")
                .pattern(" R ")
                .pattern(" R ")
                .define('M', inputMaterial)
                .define('R', inputRod)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(inputMaterial),
                        NeoForgeRecipeProvider.has(inputMaterial))
                .save(exporter);
    }

    private void createAxeRecipe(RecipeOutput exporter, Item inputRod, Item inputMaterial, Item output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output, 1)
                .pattern("MM ")
                .pattern("MR ")
                .pattern(" R ")
                .define('M', inputMaterial)
                .define('R', inputRod)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(inputMaterial),
                        NeoForgeRecipeProvider.has(inputMaterial))
                .save(exporter);
    }

    private void createShovelRecipe(RecipeOutput exporter, Item inputRod, Item inputMaterial, Item output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output, 1)
                .pattern(" M ")
                .pattern(" R ")
                .pattern(" R ")
                .define('M', inputMaterial)
                .define('R', inputRod)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(inputMaterial),
                        NeoForgeRecipeProvider.has(inputMaterial))
                .save(exporter);
    }

    private void createHoeRecipe(RecipeOutput exporter, Item inputRod, Item inputMaterial, Item output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output, 1)
                .pattern("MM ")
                .pattern(" R ")
                .pattern(" R ")
                .define('M', inputMaterial)
                .define('R', inputRod)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(inputMaterial),
                        NeoForgeRecipeProvider.has(inputMaterial))
                .save(exporter);
    }

    private void createSwordRecipe(RecipeOutput exporter, Item inputRod, Item inputMaterial, Item output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                .pattern(" M ")
                .pattern(" M ")
                .pattern(" R ")
                .define('M', inputMaterial)
                .define('R', inputRod)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(inputMaterial),
                        NeoForgeRecipeProvider.has(inputMaterial))
                .save(exporter);
    }

    private void createDaggerRecipe(RecipeOutput exporter, Item inputRod, Item inputMaterial, Item output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                .pattern(" M ")
                .pattern(" R ")
                .define('M', inputMaterial)
                .define('R', inputRod)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(inputMaterial),
                        NeoForgeRecipeProvider.has(inputMaterial))
                .save(exporter);
    }

    private void createDaggerRecipeTag(RecipeOutput exporter, Item inputRod, TagKey inputMaterial, Item output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                .pattern(" M ")
                .pattern(" R ")
                .define('M', inputMaterial)
                .define('R', inputRod)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.OAK_PLANKS),
                        NeoForgeRecipeProvider.has(Items.OAK_PLANKS))
                .save(exporter);
    }

    private void createSpearRecipe(RecipeOutput exporter, Item inputRod, Item inputMaterial, Item output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                .pattern("  M")
                .pattern(" R ")
                .pattern("R  ")
                .define('M', inputMaterial)
                .define('R', inputRod)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(inputMaterial),
                        NeoForgeRecipeProvider.has(inputMaterial))
                .save(exporter);
    }

    private void createSpearRecipeTag(RecipeOutput exporter, Item inputRod, TagKey inputMaterial, Item output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                .pattern("  M")
                .pattern(" R ")
                .pattern("R  ")
                .define('M', inputMaterial)
                .define('R', inputRod)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.OAK_PLANKS),
                        NeoForgeRecipeProvider.has(Items.OAK_PLANKS))
                .save(exporter);
    }

    private void createBucketRecipe(RecipeOutput exporter, Item inputMaterial, Item output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 1)
                .pattern("M M")
                .pattern(" M ")
                .define('M', inputMaterial)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(inputMaterial),
                        NeoForgeRecipeProvider.has(inputMaterial))
                .save(exporter);
    }

    private void createToolSetRecipes(RecipeOutput exporter, Item inputRod, Item inputMaterial, Item outputPickaxe, Item outputAxe, Item outputShovel, Item outputHoe) {
        createPickaxeRecipe(exporter, inputRod, inputMaterial, outputPickaxe);
        createAxeRecipe(exporter, inputRod, inputMaterial, outputAxe);
        createShovelRecipe(exporter, inputRod, inputMaterial, outputShovel);
        createHoeRecipe(exporter, inputRod, inputMaterial, outputHoe);
    }

    private void createCookedFoodRecipes(RecipeOutput exporter, Item rawFood, Item cookedFood) {
        simpleCookingRecipe(exporter, "smelting", RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, 200, rawFood, cookedFood, 0.35f);
        simpleCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100, rawFood, cookedFood, 0.35f);
        simpleCookingRecipe(exporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 600, rawFood, cookedFood, 0.35f);
    }

    private void createSmokingRecipe(RecipeOutput exporter, Item rawFood, Item cookedFood) {
        simpleCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100, rawFood, cookedFood, 0.35f);
    }

    private void createMetalsRecipe(RecipeOutput exporter, Item nugget, Item ingot, Block block) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingot, 1)
                .requires(nugget, 9)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(nugget),
                        NeoForgeRecipeProvider.has(nugget))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ingot).getPath() + "_from_nuggets"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, nugget, 9)
                .requires(ingot)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ingot),
                        NeoForgeRecipeProvider.has(ingot))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(nugget).getPath() + "_from_ingot"));

        createFilledRecipe(exporter, ingot, block, 1);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingot, 9)
                .requires(block)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(block),
                        NeoForgeRecipeProvider.has(block))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ingot).getPath() + "_from_block"));
    }
    //endregion

    private void createFilledRecipe(RecipeOutput exporter, Item input, Block output, int count) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, count)
                .pattern("lll")
                .pattern("lll")
                .pattern("lll")
                .define('l', input)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(input),
                        NeoForgeRecipeProvider.has(input))
                .save(exporter);
    }

}
