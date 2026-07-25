package net.jukoz.me.datageneration;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.jukoz.me.compat.neoforge.api.datagen.v1.NeoForgeDataOutput;
import net.jukoz.me.compat.neoforge.api.datagen.v1.provider.NeoForgeModelProvider;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.ModBlocks;
import net.jukoz.me.block.ModDecorativeBlocks;
import net.jukoz.me.block.ModNatureBlocks;
import net.jukoz.me.block.MushroomBlockSets;
import net.jukoz.me.block.crop.*;
import net.jukoz.me.block.special.LargeDoorBlock;
import net.jukoz.me.block.special.RocksBlock;
import net.jukoz.me.block.special.doors.*;
import net.jukoz.me.block.special.verticalSlabs.VerticalSlabBlock;
import net.jukoz.me.block.special.verticalSlabs.VerticalSlabShape;
import net.jukoz.me.datageneration.content.CustomItemModels;
import net.jukoz.me.datageneration.content.MEModels;
import net.jukoz.me.datageneration.content.models.*;
import net.jukoz.me.item.ModResourceItems;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.Condition;
import net.minecraft.data.models.blockstates.MultiPartGenerator;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.PropertyDispatch.C4;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.blockstates.VariantProperty;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ModelProvider extends NeoForgeModelProvider {

    public ModelProvider(NeoForgeDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {

        for (Block block : SimpleBlockModel.blocks) {
            blockStateModelGenerator.createTrivialCube(block);
        }

        for (Block block : SimpleBlockModel.cobbleableStoneBlocks) {
            TexturedModel texturedModel = TexturedModel.CUBE.get(block);
            ResourceLocation identifier = ModelTemplates.CUBE_MIRRORED_ALL.create(block, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation identifier2 = ModelTemplates.CUBE_ALL.create(block, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators
                    .createRotatedVariant(block, identifier, identifier2));
        }

        for (SimpleBlockModel.ChiseledBlock block : SimpleBlockModel.chiseledBlocks) {
            blockStateModelGenerator.createTrivialCube(block.base());
        }

        for (SimpleBlockModel.ChiseledBlock block : SimpleBlockModel.chiseledMainBlockTopBottom) {
            blockStateModelGenerator.createRotatedPillarWithHorizontalVariant(block.base(), TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT);
        }

        for (SimpleBlockModel.ChiseledBlock block : SimpleBlockModel.chiseledBlocksTopBottom) {
            blockStateModelGenerator.createRotatedPillarWithHorizontalVariant(block.base(), TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT);
        }

        for (SimpleBlockModel.ChiseledPolishedBlock block : SimpleBlockModel.chiseledPolishedBlocksTopBottom) {
            blockStateModelGenerator.createRotatedPillarWithHorizontalVariant(block.base(), TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT);
        }

        for (SimpleBlockModel.ChiseledPolishedBlock block : SimpleBlockModel.chiseledTilesBlocksTopBottom) {
            blockStateModelGenerator.createRotatedPillarWithHorizontalVariant(block.base(), TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT);
        }

        for (SimpleBlockModel.ChiseledPolishedBlock block : SimpleBlockModel.chiseledSmoothBlocksTopBottom) {
            blockStateModelGenerator.createRotatedPillarWithHorizontalVariant(block.base(), TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT);
        }

        for (Block wood : SimpleBlockModel.woodBlocks) {
            TextureMapping textureMap = new TextureMapping().put(TextureSlot.ALL,
                    ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" + BuiltInRegistries.BLOCK.getKey(wood).getPath().replaceAll("_wood", "_log")));
            ResourceLocation identifier = ModelTemplates.CUBE_COLUMN.create(wood, textureMap, blockStateModelGenerator.modelOutput);
            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createAxisAlignedPillarBlock(wood, identifier));
        }

        for (SimpleBlockModel.ChiseledPolishedBlock block : SimpleBlockModel.chiseledPolishedBlocks) {
            blockStateModelGenerator.createTrivialCube(block.base());
        }


        for (SimplePillarModel.Pillar block : SimplePillarModel.blocks) {
            blockStateModelGenerator.createRotatedPillarWithHorizontalVariant(block.base(), TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT);
        }

        for (SimplePillarModel.StonePillar block : SimplePillarModel.stonePillars) {
            blockStateModelGenerator.createRotatedPillarWithHorizontalVariant(block.base(), TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT);
        }

        for (SimpleSlabModel.Slab block : SimpleSlabModel.slabs) {
            ResourceLocation id = ModelLocationUtils.getModelLocation(block.origin());
            Block slab = block.slab();

            TexturedModel texturedModel = TexturedModel.CUBE.get(block.origin());
            ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create(slab, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation top = ModelTemplates.SLAB_TOP.create(slab, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators
                    .createSlab(slab, bottom, top, id));
        }

        for (SimpleSlabModel.Slab block : SimpleSlabModel.woodSlabs) {
            ResourceLocation id = ModelLocationUtils.getModelLocation(block.origin());
            Block slab = block.slab();

            TexturedModel texturedModel = TexturedModel.createAllSame(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" + BuiltInRegistries.BLOCK.getKey(block.origin()).getPath().replaceAll("_wood", "_log")));
            ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create(slab, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation top = ModelTemplates.SLAB_TOP.create(slab, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators
                    .createSlab(slab, bottom, top, id));
        }

        for (SimpleSlabModel.Slab block : SimpleSlabModel.strippedSlabs) {
            ResourceLocation id = ModelLocationUtils.getModelLocation(block.origin());
            Block slab = block.slab();

            TexturedModel texturedModel = TexturedModel.createAllSame(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" + BuiltInRegistries.BLOCK.getKey(block.origin()).getPath().replaceAll("_wood", "_log")));
            ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create(slab, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation top = ModelTemplates.SLAB_TOP.create(slab, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators
                    .createSlab(slab, bottom, top, id));
        }

        for (SimpleSlabModel.Slab block : SimpleSlabModel.vanillaSlabs) {
            ResourceLocation id = ModelLocationUtils.getModelLocation(block.origin());
            Block slab = block.slab();

            TexturedModel texturedModel = TexturedModel.createAllSame(ResourceLocation.fromNamespaceAndPath("minecraft", "block/" + BuiltInRegistries.BLOCK.getKey(block.origin()).getPath().replaceAll("_wood", "_log")));
            ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create(slab, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation top = ModelTemplates.SLAB_TOP.create(slab, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators
                    .createSlab(slab, bottom, top, id));
        }

        for (SimpleSlabModel.Slab block : SimpleSlabModel.vanillaWoodSlabs) {
            ResourceLocation id = ModelLocationUtils.getModelLocation(block.origin());
            Block slab = block.slab();

            TexturedModel texturedModel = TexturedModel.createAllSame(ResourceLocation.fromNamespaceAndPath("minecraft", "block/" + BuiltInRegistries.BLOCK.getKey(block.origin()).getPath().replaceAll("_wood", "_log")));
            ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create(slab, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation top = ModelTemplates.SLAB_TOP.create(slab, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators
                    .createSlab(slab, bottom, top, id));
        }

        for (SimpleSlabModel.Slab block : SimpleSlabModel.vanillaStrippedSlab) {
            ResourceLocation id = ModelLocationUtils.getModelLocation(block.origin());
            Block slab = block.slab();

            TexturedModel texturedModel = TexturedModel.createAllSame(ResourceLocation.fromNamespaceAndPath("minecraft", "block/" + BuiltInRegistries.BLOCK.getKey(block.origin()).getPath().replaceAll("_wood", "_log")));
            ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create(slab, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation top = ModelTemplates.SLAB_TOP.create(slab, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators
                    .createSlab(slab, bottom, top, id));
        }

        for (SimpleStairModel.Stair block : SimpleStairModel.stairs) {
            TexturedModel texturedModel = TexturedModel.CUBE.get(block.origin());
            Block stairs = block.stairs();

            ResourceLocation inner = ModelTemplates.STAIRS_INNER.create(stairs, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation regular = ModelTemplates.STAIRS_STRAIGHT.create(stairs, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create(stairs, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators
                    .createStairs(stairs, inner, regular, outer));
        }

        for (SimpleStairModel.Stair block : SimpleStairModel.woodStairs) {
            TexturedModel texturedModel = TexturedModel.createAllSame(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" + BuiltInRegistries.BLOCK.getKey(block.origin()).getPath().replaceAll("_wood", "_log")));
            Block stairs = block.stairs();

            ResourceLocation inner = ModelTemplates.STAIRS_INNER.create(stairs, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation regular = ModelTemplates.STAIRS_STRAIGHT.create(stairs, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create(stairs, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators
                    .createStairs(stairs, inner, regular, outer));
        }

        for (SimpleStairModel.Stair block : SimpleStairModel.strippedStairs) {
            TexturedModel texturedModel = TexturedModel.createAllSame(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" + BuiltInRegistries.BLOCK.getKey(block.origin()).getPath().replaceAll("_wood", "_log")));
            Block stairs = block.stairs();

            ResourceLocation inner = ModelTemplates.STAIRS_INNER.create(stairs, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation regular = ModelTemplates.STAIRS_STRAIGHT.create(stairs, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create(stairs, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators
                    .createStairs(stairs, inner, regular, outer));
        }

        for (SimpleStairModel.Stair block : SimpleStairModel.vanillaWoodStairs) {
            TexturedModel texturedModel = TexturedModel.createAllSame(ResourceLocation.fromNamespaceAndPath("minecraft", "block/" + BuiltInRegistries.BLOCK.getKey(block.origin()).getPath().replaceAll("_wood", "_log")));
            Block stairs = block.stairs();

            ResourceLocation inner = ModelTemplates.STAIRS_INNER.create(stairs, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation regular = ModelTemplates.STAIRS_STRAIGHT.create(stairs, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create(stairs, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators
                    .createStairs(stairs, inner, regular, outer));
        }

        for (SimpleStairModel.Stair block : SimpleStairModel.vanillaStrippedStairs) {
            TexturedModel texturedModel = TexturedModel.createAllSame(ResourceLocation.fromNamespaceAndPath("minecraft", "block/" + BuiltInRegistries.BLOCK.getKey(block.origin()).getPath().replaceAll("_wood", "_log")));
            Block stairs = block.stairs();

            ResourceLocation inner = ModelTemplates.STAIRS_INNER.create(stairs, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation regular = ModelTemplates.STAIRS_STRAIGHT.create(stairs, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create(stairs, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators
                    .createStairs(stairs, inner, regular, outer));
        }

        for (SimpleStairModel.Stair block : SimpleStairModel.vanillaStairs) {
            TexturedModel texturedModel = TexturedModel.CUBE.get(block.origin());
            Block stairs = block.stairs();

            ResourceLocation inner = ModelTemplates.STAIRS_INNER.create(stairs, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation regular = ModelTemplates.STAIRS_STRAIGHT.create(stairs, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create(stairs, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators
                    .createStairs(stairs, inner, regular, outer));
        }

        for (SimpleWallModel.Wall block : SimpleWallModel.blocks) {
            TexturedModel texturedModel = TexturedModel.CUBE.get(block.block());
            Block wall = block.wall();

            ModelTemplates.WALL_INVENTORY.create(wall, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            ResourceLocation post = ModelTemplates.WALL_POST.create(wall, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation low = ModelTemplates.WALL_LOW_SIDE.create(wall, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation tall = ModelTemplates.WALL_TALL_SIDE.create(wall, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators
                    .createWall(wall, post, low, tall));
        }

        for (SimpleWallModel.Wall block : SimpleWallModel.vanillaWalls) {
            TexturedModel texturedModel;
            if(BuiltInRegistries.BLOCK.getKey(block.block()).getPath().contains("waxed_") && BuiltInRegistries.BLOCK.getKey(block.block()).getPath().contains("cut_copper")){
                texturedModel = TexturedModel.createAllSame(ResourceLocation.fromNamespaceAndPath("minecraft", "block/" + BuiltInRegistries.BLOCK.getKey(block.block()).getPath().replaceAll("waxed_", "")));
            } else {
                texturedModel = TexturedModel.createAllSame(ResourceLocation.fromNamespaceAndPath("minecraft", "block/" + BuiltInRegistries.BLOCK.getKey(block.block()).getPath()));

            }
            Block wall = block.wall();

            ModelTemplates.WALL_INVENTORY.create(wall, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            ResourceLocation post = ModelTemplates.WALL_POST.create(wall, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation low = ModelTemplates.WALL_LOW_SIDE.create(wall, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation tall = ModelTemplates.WALL_TALL_SIDE.create(wall, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators
                    .createWall(wall, post, low, tall));
        }

        for (SimpleWallModel.Wall block : SimpleWallModel.strippedWalls) {
            TexturedModel texturedModel = TexturedModel.createAllSame(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" + BuiltInRegistries.BLOCK.getKey(block.block()).getPath().replaceAll("_wood", "_log")));
            Block wall = block.wall();

            ResourceLocation inventory = ModelTemplates.WALL_INVENTORY.create(wall, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.delegateItemModel(wall, inventory);

            ResourceLocation post = ModelTemplates.WALL_POST.create(wall, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation low = ModelTemplates.WALL_LOW_SIDE.create(wall, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation tall = ModelTemplates.WALL_TALL_SIDE.create(wall, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators
                    .createWall(wall, post, low, tall));
        }

        for (SimpleWallModel.Wall block : SimpleWallModel.vanillaStrippedWalls) {
            TexturedModel texturedModel = TexturedModel.createAllSame(ResourceLocation.fromNamespaceAndPath("minecraft", "block/" + BuiltInRegistries.BLOCK.getKey(block.block()).getPath().replaceAll("_wood", "_log")));
            Block wall = block.wall();

            ResourceLocation inventory = ModelTemplates.WALL_INVENTORY.create(wall, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.delegateItemModel(wall, inventory);

            ResourceLocation post = ModelTemplates.WALL_POST.create(wall, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation low = ModelTemplates.WALL_LOW_SIDE.create(wall, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation tall = ModelTemplates.WALL_TALL_SIDE.create(wall, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators
                    .createWall(wall, post, low, tall));
        }

        for (SimpleWallModel.Wall block : SimpleWallModel.vanillaWoodWalls) {
            TexturedModel texturedModel = TexturedModel.createAllSame(ResourceLocation.fromNamespaceAndPath("minecraft", "block/" + BuiltInRegistries.BLOCK.getKey(block.block()).getPath().replaceAll("_wood", "_log")));
            Block wall = block.wall();

            ResourceLocation inventory = ModelTemplates.WALL_INVENTORY.create(wall, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.delegateItemModel(wall, inventory);

            ResourceLocation post = ModelTemplates.WALL_POST.create(wall, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation low = ModelTemplates.WALL_LOW_SIDE.create(wall, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation tall = ModelTemplates.WALL_TALL_SIDE.create(wall, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators
                    .createWall(wall, post, low, tall));
        }

        for (SimpleFenceModel.Fence block : SimpleFenceModel.blocks) {
            TexturedModel texturedModel = TexturedModel.CUBE.get(block.block());
            Block fence = block.fence();

            ResourceLocation post = ModelTemplates.FENCE_POST.create(fence, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation side = ModelTemplates.FENCE_SIDE.create(fence, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation inventory = ModelTemplates.FENCE_INVENTORY.create(fence, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators
                    .createFence(fence, post, side));

            blockStateModelGenerator.delegateItemModel(fence, inventory);
        }

        for (SimpleFenceModel.Fence block : SimpleFenceModel.strippedFences) {
            TexturedModel texturedModel = TexturedModel.createAllSame(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" + BuiltInRegistries.BLOCK.getKey(block.block()).getPath().replaceAll("_wood", "_log")));
            Block fence = block.fence();

            ResourceLocation post = ModelTemplates.FENCE_POST.create(fence, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation side = ModelTemplates.FENCE_SIDE.create(fence, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation inventory = ModelTemplates.FENCE_INVENTORY.create(fence, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators
                    .createFence(fence, post, side));

            blockStateModelGenerator.delegateItemModel(fence, inventory);
        }

        for (SimpleFenceModel.Fence block : SimpleFenceModel.vanillaStrippedFences) {
            TexturedModel texturedModel = TexturedModel.createAllSame(ResourceLocation.fromNamespaceAndPath("minecraft", "block/" + BuiltInRegistries.BLOCK.getKey(block.block()).getPath().replaceAll("_wood", "_log")));
            Block fence = block.fence();

            ResourceLocation post = ModelTemplates.FENCE_POST.create(fence, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation side = ModelTemplates.FENCE_SIDE.create(fence, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation inventory = ModelTemplates.FENCE_INVENTORY.create(fence, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators
                    .createFence(fence, post, side));

            blockStateModelGenerator.delegateItemModel(fence, inventory);
        }

        for (SimpleFenceModel.Fence block : SimpleFenceModel.vanillaWoodFences) {
            TexturedModel texturedModel = TexturedModel.createAllSame(ResourceLocation.fromNamespaceAndPath("minecraft", "block/" + BuiltInRegistries.BLOCK.getKey(block.block()).getPath().replaceAll("_wood", "_log")));
            Block fence = block.fence();

            ResourceLocation post = ModelTemplates.FENCE_POST.create(fence, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation side = ModelTemplates.FENCE_SIDE.create(fence, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation inventory = ModelTemplates.FENCE_INVENTORY.create(fence, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators
                    .createFence(fence, post, side));

            blockStateModelGenerator.delegateItemModel(fence, inventory);
        }

        for (SimpleFenceGateModel.FenceGate block : SimpleFenceGateModel.blocks) {
            TexturedModel texturedModel = TexturedModel.CUBE.get(block.block());
            Block fenceGate = block.fenceGate();

            ResourceLocation open = ModelTemplates.FENCE_GATE_OPEN.create(fenceGate, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation closed = ModelTemplates.FENCE_GATE_CLOSED.create(fenceGate, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation openWall = ModelTemplates.FENCE_GATE_WALL_OPEN.create(fenceGate, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation closedWall = ModelTemplates.FENCE_GATE_WALL_CLOSED.create(fenceGate, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators
                    .createFenceGate(fenceGate, open, closed, openWall, closedWall, true));
        }

        for (SimpleButtonModel.Button block : SimpleButtonModel.buttons) {
            TexturedModel texturedModel = TexturedModel.CUBE.get(block.block());
            Block button = block.button();

            ResourceLocation unpressed = ModelTemplates.BUTTON.create(button, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation pressed = ModelTemplates.BUTTON_PRESSED.create(button, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation inventory = ModelTemplates.BUTTON_INVENTORY.create(button, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators
                    .createButton(button, unpressed, pressed));

            blockStateModelGenerator.delegateItemModel(button, inventory);
        }

        for (SimplePressurePlateModel.PressurePlate block : SimplePressurePlateModel.pressurePlates) {
            TexturedModel texturedModel = TexturedModel.CUBE.get(block.block());
            Block pressurePlate = block.pressurePlate();

            ResourceLocation up = ModelTemplates.PRESSURE_PLATE_UP.create(pressurePlate, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation down = ModelTemplates.PRESSURE_PLATE_DOWN.create(pressurePlate, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);

            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators
                    .createPressurePlate(pressurePlate, up, down));
        }

        for (SimpleTrapDoorModel.Trapdoor trapdoor : SimpleTrapDoorModel.trapdoors) {
            blockStateModelGenerator.createOrientableTrapdoor(trapdoor.trapdoor());
        }

        for (SimpleTrapDoorModel.Trapdoor trapdoor : SimpleTrapDoorModel.stoneTrapdoors) {
            registerStoneTrapdoor(blockStateModelGenerator, trapdoor.trapdoor());
        }

        for (SimpleTrapDoorModel.Trapdoor trapdoor : SimpleTrapDoorModel.vanillaStoneTrapdoors) {
            registerVanillaTrapdoor(blockStateModelGenerator, trapdoor.trapdoor());
        }

        for (SimpleLadderModel.Ladder trapdoor : SimpleLadderModel.ladders) {
            registerOrientableTrapdoorLadder(blockStateModelGenerator, trapdoor.ladder());
        }
        for (SimpleLadderModel.Ladder trapdoor : SimpleLadderModel.vanillaLadders) {
            registerOrientableTrapdoorLadder(blockStateModelGenerator, trapdoor.ladder());
        }

        for(SimpleDoorModel.Door door : SimpleDoorModel.doors){
            blockStateModelGenerator.createDoor(door.door());
        }

        for (Block block : TintableCrossModel.notTintedBlocks) {
            if (block != null)
                blockStateModelGenerator.createCrossBlockWithDefaultItem(block, BlockModelGenerators.TintState.NOT_TINTED);

        }

        for (Block block : TintableCrossModel.tintedBlocks) {
            blockStateModelGenerator.createCrossBlockWithDefaultItem(block, BlockModelGenerators.TintState.TINTED);
        }

        for (Block block : TintableCrossModel.grassLikeBlocks) {
            blockStateModelGenerator.createCrossBlockWithDefaultItem(block, BlockModelGenerators.TintState.NOT_TINTED);
        }

        for (Block block : SimpleFlowerBedModel.flowerBeds) {
            blockStateModelGenerator.createFlowerBed(block);
        }

        for (SimpleFlowerPotModel.FlowerPot flowerPot : SimpleFlowerPotModel.pots) {
            registerFlowerPotPlant(blockStateModelGenerator, flowerPot.plant(), flowerPot.pottedPlant(), BlockModelGenerators.TintState.NOT_TINTED);
        }

        for (Block block : SimpleDoubleBlockModel.doubleBlocks) {
            blockStateModelGenerator.createDoublePlant(block, BlockModelGenerators.TintState.NOT_TINTED);
        }

        for (Block block : SimpleDoubleBlockModel.doubleBlocksItems) {
            registerDoubleBlock(blockStateModelGenerator, block, BlockModelGenerators.TintState.NOT_TINTED);
        }

        for (Block block : SimpleMushroomBlockModel.mushroomBlocks) {
            blockStateModelGenerator.createMushroomBlock(block);
        }

        for (SimpleVerticalSlabModel.VerticalSlab verticalSlab : SimpleVerticalSlabModel.verticalSlabs) {
            registerVerticalSlabModelBlockStates(blockStateModelGenerator, verticalSlab.verticalSlab(), verticalSlab.block(), BuiltInRegistries.BLOCK.getKey(verticalSlab.block()).getPath());
        }

        for (SimpleVerticalSlabModel.VerticalSlab verticalSlab : SimpleVerticalSlabModel.woodVerticalSlabs) {
            registerVerticalSlabModelBlockStates(blockStateModelGenerator, verticalSlab.verticalSlab(), verticalSlab.block(), BuiltInRegistries.BLOCK.getKey(verticalSlab.block()).getPath().replaceAll("_wood", "_log"));
        }

        for (SimpleVerticalSlabModel.VerticalSlab verticalSlab : SimpleVerticalSlabModel.strippedVerticalSlabs) {
            registerVerticalSlabModelBlockStates(blockStateModelGenerator, verticalSlab.verticalSlab(), verticalSlab.block(), BuiltInRegistries.BLOCK.getKey(verticalSlab.block()).getPath().replaceAll("_wood", "_log"));
        }

        for (SimpleLayersModel.Layers block : SimpleLayersModel.layers) {
            registerLayers(blockStateModelGenerator, block.layers(), block.origin(), false);
        }

        for (SimpleLayersModel.Layers block : SimpleLayersModel.vanillaLayers) {
            registerLayers(blockStateModelGenerator, block.layers(), block.origin(), true);
        }

        for (SimplePaneModel.Pane pane : SimplePaneModel.panes) {
            registerLeadGlassPane(blockStateModelGenerator, pane.glass(), pane.pane());
        }

        for(Block block : SimpleWoodStoolModel.stools){
            registerWoodStoolModelBlockStates(blockStateModelGenerator, block);
        }

        for(Block block : SimpleWoodBenchModel.benchs){
            registerWoodBenchModelBlockStates(blockStateModelGenerator, block);
        }

        for (Block block : SimpleStoneStoolModel.stools) {
            registerStoneStoolModelBlockStates(blockStateModelGenerator, block,
                    ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" +
                            BuiltInRegistries.BLOCK.getKey(block).getPath().replaceAll("_stool", "")));
        }

        for(SimpleStoneStoolModel.VanillaStool stool : SimpleStoneStoolModel.vanillaStools){
            String id = "block/" + BuiltInRegistries.BLOCK.getKey(stool.origin()).getPath();
            if (stool.origin() == Blocks.BASALT) id += "_side";
            registerStoneStoolModelBlockStates(blockStateModelGenerator, stool.base(),
                    ResourceLocation.fromNamespaceAndPath("minecraft", id));
        }

        for (Block block : SimpleStoneTableModel.tables) {
            registerStoneTableModelBlockStates(blockStateModelGenerator, block, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" + BuiltInRegistries.BLOCK.getKey(block).getPath().replaceAll("_table", "")));
        }
        for(SimpleStoneTableModel.VanillaTable table : SimpleStoneTableModel.vanillaTables) {
            String id = "block/" + BuiltInRegistries.BLOCK.getKey(table.origin()).getPath();
            if(table.origin() == Blocks.BASALT) id += "_side";
            registerStoneTableModelBlockStates(blockStateModelGenerator, table.base(), ResourceLocation.fromNamespaceAndPath("minecraft", id));
        }

        for (Block block : SimpleStoneChairModel.chairs) {
            registerStoneChairModelBlockStates(blockStateModelGenerator, block, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" + BuiltInRegistries.BLOCK.getKey(block).getPath().replaceAll("_chair", "")));
        }

        for(SimpleStoneChairModel.VanillaChair chair : SimpleStoneChairModel.vanillaChairs){
            String id = "block/" + BuiltInRegistries.BLOCK.getKey(chair.origin()).getPath();
            if(chair.origin() == Blocks.BASALT) id += "_side";
            registerStoneChairModelBlockStates(blockStateModelGenerator, chair.base(), ResourceLocation.fromNamespaceAndPath("minecraft", id));
        }

        for(Block block : SimpleWoodTableModel.tables){
            registerWoodTableModelBlockStates(blockStateModelGenerator, block);
        }

        for(Block block : SimpleWoodChairModel.chairs){
            registerWoodChairModelBlockStates(blockStateModelGenerator, block);
        }

        for(Block block : SimpleFanModel.grassLikeFans){
            registerFanModel(blockStateModelGenerator, block);
        }

        for(SimpleRocksModel.Rocks rocks : SimpleRocksModel.rocks){
            registerRocksBlock(blockStateModelGenerator, rocks.rocks(), rocks.block());
        }

        for(SimpleRocksModel.Rocks rocks : SimpleRocksModel.vanillaRocks){
            registerRocksBlock(blockStateModelGenerator, rocks.rocks(), rocks.block());
        }

        for(SimpleWoodStoolModel.VanillaStool stool : SimpleWoodStoolModel.vanillaStools) {
            registerWoodStoolModelBlockStates(blockStateModelGenerator, stool.base());
        }

        for(SimpleWoodBenchModel.VanillaBench bench : SimpleWoodBenchModel.vanillaBenchs) {
            registerWoodBenchModelBlockStates(blockStateModelGenerator, bench.base());
        }

        for(SimpleWoodTableModel.VanillaTable table : SimpleWoodTableModel.vanillaTables) {
            registerWoodTableModelBlockStates(blockStateModelGenerator, table.base());
        }

        for(SimpleWoodChairModel.VanillaChair chair : SimpleWoodChairModel.vanillaChairs) {
            registerWoodChairModelBlockStates(blockStateModelGenerator, chair.base());
        }


        for (SimpleVerticalSlabModel.VerticalSlab verticalSlab : SimpleVerticalSlabModel.vanillaVerticalSlabs) {
            String id = String.valueOf(BuiltInRegistries.BLOCK.getKey(verticalSlab.block()));
            id = id.substring(id.lastIndexOf(":") + 1);

            if(verticalSlab.block() == Blocks.SANDSTONE || verticalSlab.block() == Blocks.RED_SANDSTONE || verticalSlab.block() == Blocks.CUT_SANDSTONE || verticalSlab.block() == Blocks.CUT_RED_SANDSTONE) {
                String topId = id + "_top";
                String bottomId = id + "_bottom";
                if(verticalSlab.block() == Blocks.CUT_SANDSTONE || verticalSlab.block() == Blocks.CUT_RED_SANDSTONE) {
                    topId = topId.substring(topId.indexOf("_") + 1);
                    bottomId = bottomId.substring(bottomId.indexOf("_") + 1);
                }
                registerColumnVerticalSlabModelBlockStates(blockStateModelGenerator, verticalSlab.verticalSlab(), verticalSlab.block(), "minecraft", topId, bottomId, id);
            } else {
                    if(verticalSlab.block() == Blocks.SMOOTH_RED_SANDSTONE
                            || verticalSlab.block() == Blocks.SMOOTH_SANDSTONE) {
                        id += "_top";
                        id = id.substring(id.indexOf("_") + 1);
                    } else if(verticalSlab.block() == Blocks.QUARTZ_BLOCK) {
                        id += "_side";
                    } else if(verticalSlab.block() == Blocks.SMOOTH_QUARTZ) {
                        id = "quartz_block_bottom";
                    } else if(verticalSlab.block() == Blocks.WAXED_CUT_COPPER
                            || verticalSlab.block() == Blocks.WAXED_EXPOSED_CUT_COPPER
                            || verticalSlab.block() == Blocks.WAXED_WEATHERED_CUT_COPPER
                            || verticalSlab.block() == Blocks.WAXED_OXIDIZED_CUT_COPPER) {
                        id = id.substring(id.indexOf("_") + 1);
                    }
                    registerVanillaVerticalSlabModelBlockStates(blockStateModelGenerator, verticalSlab.verticalSlab(), verticalSlab.block(), id);
                }
        }

        for(SimpleVerticalSlabModel.VerticalSlab verticalSlab : SimpleVerticalSlabModel.vanillaWoodVerticalSlabs) {
            String id = BuiltInRegistries.BLOCK.getKey(verticalSlab.block()).getPath();
            String baseTextureId = id.substring(0, id.lastIndexOf("_")) + "_log";
            baseTextureId = baseTextureId.replaceAll("_wood", "_log");
            registerVanillaVerticalSlabModelBlockStates(blockStateModelGenerator, verticalSlab.verticalSlab(), verticalSlab.block(), baseTextureId);
        }
        for (SimpleVerticalSlabModel.VerticalSlab verticalSlab : SimpleVerticalSlabModel.vanillaStrippedVerticalSlabs) {
            String id = BuiltInRegistries.BLOCK.getKey(verticalSlab.block()).getPath();
            String baseTextureId = id.substring(0, id.lastIndexOf("_")) + "_log";
            baseTextureId = baseTextureId.replaceAll("_wood", "_log");
            registerVanillaVerticalSlabModelBlockStates(blockStateModelGenerator, verticalSlab.verticalSlab(), verticalSlab.block(), baseTextureId);
        }

        SimpleTopWaterModel.topWaterBlocks.forEach(block -> {
            registerTopWaterblock(blockStateModelGenerator, block);
        });

        // Crops
        blockStateModelGenerator.createCropBlock(ModNatureBlocks.BELL_PEPPER_CROP, BellpepperCropBlock.AGE, 0, 1, 2, 3, 4);
        blockStateModelGenerator.createCropBlock(ModNatureBlocks.CUCUMBER_CROP, CucumberCropBlock.AGE, 0, 1, 2, 3);
        blockStateModelGenerator.createCropBlock(ModNatureBlocks.FLAX_CROP, FlaxCropBlock.AGE, 0, 1, 2, 3);
        blockStateModelGenerator.createCropBlock(ModNatureBlocks.GARLIC_CROP, GarlicCropBlock.AGE, 0, 1, 2, 3);
        blockStateModelGenerator.createCropBlock(ModNatureBlocks.LEEK_CROP, LeekCropBlock.AGE, 0, 1, 2, 3);
        blockStateModelGenerator.createCropBlock(ModNatureBlocks.LETTUCE_CROP, LettuceCropBlock.AGE, 0, 1, 2, 3);
        blockStateModelGenerator.createCropBlock(ModNatureBlocks.ONION_CROP, OnionCropBlock.AGE, 0, 1, 2, 3);

        //CLUSTERS
        blockStateModelGenerator.createAmethystCluster(ModBlocks.GLOWSTONE_CLUSTER);
        blockStateModelGenerator.createAmethystCluster(ModBlocks.SMALL_GLOWSTONE_BUD);
        blockStateModelGenerator.createAmethystCluster(ModBlocks.MEDIUM_GLOWSTONE_BUD);
        blockStateModelGenerator.createAmethystCluster(ModBlocks.LARGE_GLOWSTONE_BUD);
        blockStateModelGenerator.createAmethystCluster(ModBlocks.RED_AGATE_CLUSTER);
        blockStateModelGenerator.createAmethystCluster(ModBlocks.SMALL_RED_AGATE_BUD);
        blockStateModelGenerator.createAmethystCluster(ModBlocks.MEDIUM_RED_AGATE_BUD);
        blockStateModelGenerator.createAmethystCluster(ModBlocks.LARGE_RED_AGATE_BUD);
        blockStateModelGenerator.createAmethystCluster(ModBlocks.CITRINE_CLUSTER);
        blockStateModelGenerator.createAmethystCluster(ModBlocks.SMALL_CITRINE_BUD);
        blockStateModelGenerator.createAmethystCluster(ModBlocks.MEDIUM_CITRINE_BUD);
        blockStateModelGenerator.createAmethystCluster(ModBlocks.LARGE_CITRINE_BUD);
        blockStateModelGenerator.createAmethystCluster(ModBlocks.QUARTZ_CLUSTER);
        blockStateModelGenerator.createAmethystCluster(ModBlocks.SMALL_QUARTZ_BUD);
        blockStateModelGenerator.createAmethystCluster(ModBlocks.MEDIUM_QUARTZ_BUD);
        blockStateModelGenerator.createAmethystCluster(ModBlocks.LARGE_QUARTZ_BUD);

        registerLargeDoor(blockStateModelGenerator, (LargeDoorBlock) ModDecorativeBlocks.BLUE_HOBBIT_DOOR, LargeDoor2x2.PART);
        registerLargeDoor(blockStateModelGenerator, (LargeDoorBlock) ModDecorativeBlocks.GREEN_HOBBIT_DOOR, LargeDoor2x2.PART);
        registerLargeDoor(blockStateModelGenerator, (LargeDoorBlock) ModDecorativeBlocks.LIGHT_BLUE_HOBBIT_DOOR, LargeDoor2x2.PART);
        registerLargeDoor(blockStateModelGenerator, (LargeDoorBlock) ModDecorativeBlocks.RED_HOBBIT_DOOR, LargeDoor2x2.PART);
        registerLargeDoor(blockStateModelGenerator, (LargeDoorBlock) ModDecorativeBlocks.YELLOW_HOBBIT_DOOR, LargeDoor2x2.PART);
        registerLargeDoor(blockStateModelGenerator, (LargeDoorBlock) ModDecorativeBlocks.LARCH_HOBBIT_DOOR, LargeDoor2x2.PART);
        registerLargeDoor(blockStateModelGenerator, (LargeDoorBlock) ModDecorativeBlocks.SPRUCE_HOBBIT_DOOR, LargeDoor2x2.PART);

        registerLargeDoor(blockStateModelGenerator, (LargeDoorBlock) ModDecorativeBlocks.TALL_BLACK_PINE_DOOR, LargeDoor3x1.PART);

        registerLargeDoor(blockStateModelGenerator, (LargeDoorBlock) ModDecorativeBlocks.OAK_STABLE_DOOR, LargeDoor4x2.PART);
        registerLargeDoor(blockStateModelGenerator, (LargeDoorBlock) ModDecorativeBlocks.REINFORCED_SPRUCE_DOOR, LargeDoor4x2.PART);
        registerLargeDoor(blockStateModelGenerator, (LargeDoorBlock) ModDecorativeBlocks.REINFORCED_BLACK_PINE_DOOR, LargeDoor4x2.PART);
        registerLargeDoor(blockStateModelGenerator, (LargeDoorBlock) ModDecorativeBlocks.RICKETY_SIMPLE_LARCH_DOOR, LargeDoor4x2.PART);
        registerLargeDoor(blockStateModelGenerator, (LargeDoorBlock) ModDecorativeBlocks.SIMPLE_LARCH_GATE, LargeDoor4x2.PART);
        registerLargeDoor(blockStateModelGenerator, (LargeDoorBlock) ModDecorativeBlocks.SPRUCE_STABLE_DOOR, LargeDoor4x2.PART);

        registerLargeDoor(blockStateModelGenerator, (LargeDoorBlock) ModDecorativeBlocks.LARGE_STURDY_DOOR, LargeDoor5x3.PART);

        registerLargeDoor(blockStateModelGenerator, (LargeDoorBlock) ModDecorativeBlocks.GREAT_GONDORIAN_GATE, LargeDoor10x5.PART);

        registerLargeDoor(blockStateModelGenerator, (LargeDoorBlock) ModDecorativeBlocks.GREAT_DWARVEN_GATE, LargeDoor5x2.PART);
        registerLargeDoor(blockStateModelGenerator, (LargeDoorBlock) ModDecorativeBlocks.VARNISHED_DWARVEN_DOOR, LargeDoor4x2.PART);
        registerLargeDoor(blockStateModelGenerator, (LargeDoorBlock) ModDecorativeBlocks.RUINED_DWARVEN_DOOR, LargeDoor4x2.PART);
        registerThickLargeDoor(blockStateModelGenerator, (LargeDoorBlock) ModDecorativeBlocks.HIDDEN_DWARVEN_DOOR, LargeThickDoor3x2.PART);

        registerLargeDoor(blockStateModelGenerator, (LargeDoorBlock) ModDecorativeBlocks.GREAT_ELVEN_GATE, LargeDoor6x2.PART);

        registerLargeDoor(blockStateModelGenerator, (LargeDoorBlock) ModDecorativeBlocks.GREAT_ORCISH_GATE, LargeDoor10x4.PART);

        blockStateModelGenerator.createRotatedPillarWithHorizontalVariant(ModBlocks.GILDED_CHISELED_GREEN_TUFF, TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT);
        blockStateModelGenerator.createRotatedPillarWithHorizontalVariant(ModBlocks.GILDED_CHISELED_GREEN_TUFF_BRICKS, TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT);
        blockStateModelGenerator.createRotatedPillarWithHorizontalVariant(ModBlocks.GILDED_CHISELED_POLISHED_GREEN_TUFF, TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT);
        blockStateModelGenerator.createRotatedPillarWithHorizontalVariant(ModBlocks.GILDED_CHISELED_GREEN_TUFF_TILES, TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT);
        blockStateModelGenerator.createRotatedPillarWithHorizontalVariant(ModBlocks.GILDED_CHISELED_SMOOTH_GREEN_TUFF, TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT);

        registerPaneModel(blockStateModelGenerator, ModBlocks.NET);

        registerPaneModel(blockStateModelGenerator, ModBlocks.GILDED_BARS);

        registerPaneModel(blockStateModelGenerator, ModBlocks.COPPER_BARS);
        registerPaneModel(blockStateModelGenerator, ModBlocks.EXPOSED_COPPER_BARS);
        registerPaneModel(blockStateModelGenerator, ModBlocks.WEATHERED_COPPER_BARS);
        registerPaneModel(blockStateModelGenerator, ModBlocks.OXIDIZED_COPPER_BARS);

        registerPaneModel(blockStateModelGenerator, ModBlocks.WAXED_COPPER_BARS);
        registerPaneModel(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_COPPER_BARS);
        registerPaneModel(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_COPPER_BARS);
        registerPaneModel(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_COPPER_BARS);

        registerPaneModel(blockStateModelGenerator, ModBlocks.TREATED_STEEL_BARS);

        registerPaneModel(blockStateModelGenerator, ModBlocks.SILVER_BARS);

        registerOrientableTrapdoorLadder(blockStateModelGenerator, ModDecorativeBlocks.ROPE_LADDER);

        blockStateModelGenerator.createMultiface(ModNatureBlocks.AZALEA_FLOWER_GROWTH);
        blockStateModelGenerator.createMultiface(ModNatureBlocks.DRY_GROWTH);
        blockStateModelGenerator.createMultiface(ModNatureBlocks.FROZEN_GROWTH);
        blockStateModelGenerator.createMultiface(ModNatureBlocks.GREEN_GROWTH);
        blockStateModelGenerator.createMultiface(ModNatureBlocks.IVY_GROWTH);
        blockStateModelGenerator.createMultiface(ModNatureBlocks.LILAC_FLOWER_GROWTH);
        blockStateModelGenerator.createMultiface(ModNatureBlocks.PINK_FLOWER_GROWTH);
        blockStateModelGenerator.createMultiface(ModNatureBlocks.RED_FLOWER_GROWTH);
        blockStateModelGenerator.createMultiface(ModNatureBlocks.WHITE_FLOWER_GROWTH);
        blockStateModelGenerator.createMultiface(ModNatureBlocks.YELLOW_FLOWER_GROWTH);

        blockStateModelGenerator.createMultiface(ModNatureBlocks.STICKY_ICE);
    }

    public final void registerFanModel(BlockModelGenerators blockStateCollector, Block coralFanBlock) {
        TexturedModel texturedModel = TexturedModel.CORAL_FAN.get(coralFanBlock);
        ResourceLocation identifier = texturedModel.create(coralFanBlock, blockStateCollector.modelOutput);
        blockStateCollector.blockStateOutput.accept(blockStateCollector.createSimpleBlock(coralFanBlock, identifier));
        blockStateCollector.createSimpleFlatItemModel(coralFanBlock);
    }

    public final void registerFlowerPotPlant(BlockModelGenerators blockStateModelGenerator, Block plantBlock, Block flowerPotBlock, BlockModelGenerators.TintState tintType) {
        TextureMapping textureMap = TextureMapping.plant(plantBlock);
        ResourceLocation identifier = tintType.getCrossPot().create(flowerPotBlock, textureMap, blockStateModelGenerator.modelOutput);
        blockStateModelGenerator.blockStateOutput.accept(blockStateModelGenerator.createSimpleBlock(flowerPotBlock, identifier));
    }

    public void registerVanillaVerticalSlabModelBlockStates(BlockModelGenerators blockStateModelGenerator, Block block, Block origin, String slabPath) {
        ResourceLocation fullBlockId = ModelLocationUtils.getModelLocation(origin);

        ResourceLocation variantId = MEModels.VERTICAL_SLAB.create(block,
                TextureMapping.singleSlot(TextureSlot.ALL, ResourceLocation.fromNamespaceAndPath("minecraft", "block/" + slabPath)),
                blockStateModelGenerator.modelOutput);

        ResourceLocation inner = MEModels.VERTICAL_SLAB_INNER.create(block, TextureMapping.singleSlot(TextureSlot.ALL, ResourceLocation.fromNamespaceAndPath("minecraft", "block/" + slabPath)), blockStateModelGenerator.modelOutput);
        ResourceLocation outer = MEModels.VERTICAL_SLAB_OUTER.create(block, TextureMapping.singleSlot(TextureSlot.ALL, ResourceLocation.fromNamespaceAndPath("minecraft", "block/" + slabPath)), blockStateModelGenerator.modelOutput);

        registerVerticalSlab(blockStateModelGenerator, block, fullBlockId, variantId, inner, outer);
    }

    public void registerVerticalSlabModelBlockStates(BlockModelGenerators blockStateModelGenerator, Block block, Block origin, String slabPath) {
        ResourceLocation fullBlockId = ModelLocationUtils.getModelLocation(origin);
        ResourceLocation variantId = MEModels.VERTICAL_SLAB.create(block,
                TextureMapping.singleSlot(TextureSlot.ALL, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" + slabPath)),
                blockStateModelGenerator.modelOutput);

        ResourceLocation inner = MEModels.VERTICAL_SLAB_INNER.create(block, TextureMapping.singleSlot(TextureSlot.ALL, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" + slabPath)), blockStateModelGenerator.modelOutput);
        ResourceLocation outer = MEModels.VERTICAL_SLAB_OUTER.create(block, TextureMapping.singleSlot(TextureSlot.ALL, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" + slabPath)), blockStateModelGenerator.modelOutput);

        registerVerticalSlab(blockStateModelGenerator, block, fullBlockId, variantId, inner, outer);
    }

    public void registerColumnVerticalSlabModelBlockStates(BlockModelGenerators blockStateModelGenerator, Block block, Block origin,
                                                           String modId, String topTexturePath, String bottomTexturePath, String sideTexturePath) {
        ResourceLocation fullBlockId = ModelLocationUtils.getModelLocation(origin);
        ResourceLocation sideTexture = ResourceLocation.fromNamespaceAndPath(modId, "block/" + sideTexturePath);

        ResourceLocation variantId = MEModels.VERTICAL_COLUMN_SLAB.create(block, (new TextureMapping())
                        .put(TextureSlot.TOP, ResourceLocation.fromNamespaceAndPath(modId, "block/" + topTexturePath))
                        .put(TextureSlot.BOTTOM, ResourceLocation.fromNamespaceAndPath(modId, "block/" + bottomTexturePath))
                        .put(TextureSlot.SIDE, sideTexture)
                        .put(TextureSlot.PARTICLE, sideTexture),
                blockStateModelGenerator.modelOutput);

        ResourceLocation inner = MEModels.VERTICAL_COLUMN_SLAB_INNER.create(block, (new TextureMapping())
                        .put(TextureSlot.TOP, ResourceLocation.fromNamespaceAndPath(modId, "block/" + topTexturePath))
                        .put(TextureSlot.BOTTOM, ResourceLocation.fromNamespaceAndPath(modId, "block/" + bottomTexturePath))
                        .put(TextureSlot.SIDE, sideTexture)
                        .put(TextureSlot.PARTICLE, sideTexture),
                blockStateModelGenerator.modelOutput);

        ResourceLocation outer = MEModels.VERTICAL_COLUMN_SLAB_OUTER.create(block, (new TextureMapping())
                        .put(TextureSlot.TOP, ResourceLocation.fromNamespaceAndPath(modId, "block/" + topTexturePath))
                        .put(TextureSlot.BOTTOM, ResourceLocation.fromNamespaceAndPath(modId, "block/" + bottomTexturePath))
                        .put(TextureSlot.SIDE, sideTexture)
                        .put(TextureSlot.PARTICLE, sideTexture),
                blockStateModelGenerator.modelOutput);
        registerVerticalSlab(blockStateModelGenerator, block, fullBlockId, variantId, inner, outer);
    }

    private void registerVerticalSlab(BlockModelGenerators blockStateModelGenerator, Block block, ResourceLocation fullBlock, ResourceLocation regular, ResourceLocation inner, ResourceLocation outer) {
        if(BuiltInRegistries.BLOCK.getKey(block).getPath().contains("waxed_") && BuiltInRegistries.BLOCK.getKey(block).getPath().contains("copper")){
            fullBlock = ResourceLocation.withDefaultNamespace(fullBlock.getPath().replaceAll("waxed_", ""));
        }
        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(PropertyDispatch
                .properties(BlockStateProperties.HORIZONTAL_FACING, VerticalSlabBlock.DOUBLE, VerticalSlabBlock.SHAPE)
                .select(Direction.EAST, false, VerticalSlabShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regular).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true))
                .select(Direction.WEST, false, VerticalSlabShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regular).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true))
                .select(Direction.SOUTH, false, VerticalSlabShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regular).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))
                .select(Direction.NORTH, false, VerticalSlabShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regular).with(VariantProperties.UV_LOCK, true))
                .select(Direction.EAST, false, VerticalSlabShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outer).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))
                .select(Direction.WEST, false, VerticalSlabShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outer).with(VariantProperties.UV_LOCK, true))
                .select(Direction.SOUTH, false, VerticalSlabShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outer).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true))
                .select(Direction.NORTH, false, VerticalSlabShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outer).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true))
                .select(Direction.EAST, false, VerticalSlabShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outer).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true))
                .select(Direction.WEST, false, VerticalSlabShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outer).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true))
                .select(Direction.SOUTH, false, VerticalSlabShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outer).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))
                .select(Direction.NORTH, false, VerticalSlabShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outer).with(VariantProperties.UV_LOCK, true))
                .select(Direction.EAST, false, VerticalSlabShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, inner).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))
                .select(Direction.WEST, false, VerticalSlabShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, inner).with(VariantProperties.UV_LOCK, true))
                .select(Direction.SOUTH,false,  VerticalSlabShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, inner).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true))
                .select(Direction.NORTH, false, VerticalSlabShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, inner).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true))
                .select(Direction.EAST, false, VerticalSlabShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, inner).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true))
                .select(Direction.WEST, false, VerticalSlabShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, inner).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true))
                .select(Direction.SOUTH, false, VerticalSlabShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, inner).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))
                .select(Direction.NORTH, false, VerticalSlabShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, inner).with(VariantProperties.UV_LOCK, true))

                .select(Direction.EAST, true, VerticalSlabShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, fullBlock).with(VariantProperties.UV_LOCK, true))
                .select(Direction.WEST, true, VerticalSlabShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, fullBlock).with(VariantProperties.UV_LOCK, true))
                .select(Direction.SOUTH, true, VerticalSlabShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, fullBlock).with(VariantProperties.UV_LOCK, true))
                .select(Direction.NORTH, true, VerticalSlabShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, fullBlock).with(VariantProperties.UV_LOCK, true))
                .select(Direction.EAST, true, VerticalSlabShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, fullBlock).with(VariantProperties.UV_LOCK, true))
                .select(Direction.WEST, true, VerticalSlabShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, fullBlock).with(VariantProperties.UV_LOCK, true))
                .select(Direction.SOUTH, true, VerticalSlabShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, fullBlock).with(VariantProperties.UV_LOCK, true))
                .select(Direction.NORTH, true, VerticalSlabShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, fullBlock).with(VariantProperties.UV_LOCK, true))
                .select(Direction.EAST, true, VerticalSlabShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, fullBlock).with(VariantProperties.UV_LOCK, true))
                .select(Direction.WEST, true, VerticalSlabShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, fullBlock).with(VariantProperties.UV_LOCK, true))
                .select(Direction.SOUTH, true, VerticalSlabShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, fullBlock).with(VariantProperties.UV_LOCK, true))
                .select(Direction.NORTH, true, VerticalSlabShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, fullBlock).with(VariantProperties.UV_LOCK, true))
                .select(Direction.EAST, true, VerticalSlabShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, fullBlock).with(VariantProperties.UV_LOCK, true))
                .select(Direction.WEST, true, VerticalSlabShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, fullBlock).with(VariantProperties.UV_LOCK, true))
                .select(Direction.SOUTH,true,  VerticalSlabShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, fullBlock).with(VariantProperties.UV_LOCK, true))
                .select(Direction.NORTH, true, VerticalSlabShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, fullBlock).with(VariantProperties.UV_LOCK, true))
                .select(Direction.EAST, true, VerticalSlabShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, fullBlock).with(VariantProperties.UV_LOCK, true))
                .select(Direction.WEST, true, VerticalSlabShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, fullBlock).with(VariantProperties.UV_LOCK, true))
                .select(Direction.SOUTH, true, VerticalSlabShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, fullBlock).with(VariantProperties.UV_LOCK, true))
                .select(Direction.NORTH, true, VerticalSlabShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, fullBlock).with(VariantProperties.UV_LOCK, true))));

    }

    public void registerWoodStoolModelBlockStates(BlockModelGenerators blockStateModelGenerator, Block block){
        ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" + BuiltInRegistries.BLOCK.getKey(block).getPath().replaceAll("stool", "chair"));
        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block,
                Variant.variant().with(VariantProperties.MODEL, MEModels.WOOD_STOOL.create(block,
                        (new TextureMapping()).put(TextureSlot.ALL, texture)
                                .put(TextureSlot.PARTICLE, texture),
                        blockStateModelGenerator.modelOutput)).with(VariantProperties.UV_LOCK, false))
                .with(BlockModelGenerators.createHorizontalFacingDispatch()));
    }

    public void registerWoodBenchModelBlockStates(BlockModelGenerators blockStateModelGenerator, Block block){
        ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" + BuiltInRegistries.BLOCK.getKey(block).getPath());
        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block,
                        Variant.variant().with(VariantProperties.MODEL, MEModels.WOOD_BENCH.create(block,
                                (new TextureMapping()).put(TextureSlot.ALL, texture)
                                        .put(TextureSlot.PARTICLE, texture),
                                blockStateModelGenerator.modelOutput)).with(VariantProperties.UV_LOCK, false))
                .with(BlockModelGenerators.createHorizontalFacingDispatch()));
    }

    public void registerWoodTableModelBlockStates(BlockModelGenerators blockStateModelGenerator, Block block){
        ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" + BuiltInRegistries.BLOCK.getKey(block).getPath());
        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block,
                        Variant.variant().with(VariantProperties.MODEL, MEModels.WOOD_TABLE.create(block,
                                (new TextureMapping()).put(TextureSlot.ALL, texture)
                                        .put(TextureSlot.PARTICLE, texture),
                                blockStateModelGenerator.modelOutput)).with(VariantProperties.UV_LOCK, false)));
    }

    public void registerWoodChairModelBlockStates(BlockModelGenerators blockStateModelGenerator, Block block){
        ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" + BuiltInRegistries.BLOCK.getKey(block).getPath());
        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block,
                        Variant.variant().with(VariantProperties.MODEL, MEModels.WOOD_CHAIR.create(block,
                                (new TextureMapping()).put(TextureSlot.ALL, texture)
                                        .put(TextureSlot.PARTICLE, texture),
                                blockStateModelGenerator.modelOutput)).with(VariantProperties.UV_LOCK, false))
                .with(BlockModelGenerators.createHorizontalFacingDispatch()));
    }

    public void registerStoneStoolModelBlockStates(BlockModelGenerators blockStateModelGenerator, Block block, ResourceLocation texture) {
        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block,
                        Variant.variant().with(VariantProperties.MODEL, MEModels.STONE_STOOL.create(block,
                                (new TextureMapping()).put(TextureSlot.ALL, texture)
                                        .put(TextureSlot.PARTICLE, texture),
                                blockStateModelGenerator.modelOutput)).with(VariantProperties.UV_LOCK, false))
                .with(BlockModelGenerators.createHorizontalFacingDispatch()));
    }

    public void registerStoneTableModelBlockStates(BlockModelGenerators blockStateModelGenerator, Block block, ResourceLocation texture) {
        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block,
                Variant.variant().with(VariantProperties.MODEL, MEModels.STONE_TABLE.create(block,
                        (new TextureMapping()).put(TextureSlot.ALL, texture)
                                .put(TextureSlot.PARTICLE, texture),
                        blockStateModelGenerator.modelOutput)).with(VariantProperties.UV_LOCK, false)));
    }

    public void registerStoneChairModelBlockStates(BlockModelGenerators blockStateModelGenerator, Block block, ResourceLocation texture) {
        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block,
                        Variant.variant().with(VariantProperties.MODEL, MEModels.STONE_CHAIR.create(block,
                                (new TextureMapping()).put(TextureSlot.ALL, texture)
                                        .put(TextureSlot.PARTICLE, texture),
                                blockStateModelGenerator.modelOutput)).with(VariantProperties.UV_LOCK, false))
                .with(BlockModelGenerators.createHorizontalFacingDispatch()));
    }

    public final void registerDoubleBlock(BlockModelGenerators blockStateModelGenerator, Block doubleBlock, BlockModelGenerators.TintState tintType) {
        blockStateModelGenerator.createSimpleFlatItemModel(doubleBlock.asItem());
        ResourceLocation identifier = blockStateModelGenerator.createSuffixedVariant(doubleBlock, "_top", tintType.getCross(), TextureMapping::cross);
        ResourceLocation identifier2 = blockStateModelGenerator.createSuffixedVariant(doubleBlock, "_bottom", tintType.getCross(), TextureMapping::cross);
        blockStateModelGenerator.createDoubleBlock(doubleBlock, identifier, identifier2);
    }

    public final void registerLargeDoor(BlockModelGenerators blockStateModelGenerator, LargeDoorBlock largeDoor, IntegerProperty part){
        var statesMap = PropertyDispatch.properties(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.OPEN, BlockStateProperties.DOOR_HINGE, part);
        int rot = 0;
        for (int i = 0; i < largeDoor.getDoorWidth() * largeDoor.getDoorHeight(); i++){
            for(int k = 2; k < 6; k++){
                rot = switch (k) {
                    case 2 -> 0;
                    case 3 -> 180;
                    case 4 -> 270;
                    case 5 -> 90;
                    default -> rot;
                };

                statesMap.select(Direction.from3DDataValue(k), false, DoorHingeSide.LEFT, i, Variant.variant()
                        .with(VariantProperties.MODEL, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID,"block/" + BuiltInRegistries.BLOCK.getKey(largeDoor).getPath() + "_left_" + i))
                        .with(VariantProperties.UV_LOCK, false)
                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.valueOf("R" + rot)));

                statesMap.select(Direction.from3DDataValue(k), true, DoorHingeSide.LEFT, i, Variant.variant()
                        .with(VariantProperties.MODEL,ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" + BuiltInRegistries.BLOCK.getKey(largeDoor).getPath() + "_left_open_" + i))
                        .with(VariantProperties.UV_LOCK, false)
                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.valueOf("R" + rot)));

                statesMap.select(Direction.from3DDataValue(k), false, DoorHingeSide.RIGHT, i, Variant.variant()
                        .with(VariantProperties.MODEL, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID,"block/" + BuiltInRegistries.BLOCK.getKey(largeDoor).getPath() + "_right_" + i))
                        .with(VariantProperties.UV_LOCK, false)
                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.valueOf("R" + rot)));

                statesMap.select(Direction.from3DDataValue(k), true, DoorHingeSide.RIGHT, i, Variant.variant()
                        .with(VariantProperties.MODEL, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID,"block/" + BuiltInRegistries.BLOCK.getKey(largeDoor).getPath() + "_right_open_" + i))
                        .with(VariantProperties.UV_LOCK, false)
                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.valueOf("R" + rot)));

                if (k == 2){
                    MEModels.LARGE_DOOR_LEFT.createWithSuffix(largeDoor, "_left_" + i,
                            (new TextureMapping()).put(TextureSlot.ALL, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID,"block/" + BuiltInRegistries.BLOCK.getKey(largeDoor).getPath() + "_" + i))
                                    .put(TextureSlot.PARTICLE, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID,"block/" + BuiltInRegistries.BLOCK.getKey(largeDoor).getPath() + "_" + i)),
                            blockStateModelGenerator.modelOutput);

                    MEModels.LARGE_DOOR_LEFT_OPEN.createWithSuffix(largeDoor,"_left_open_" + i,
                            (new TextureMapping()).put(TextureSlot.ALL, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" +BuiltInRegistries.BLOCK.getKey(largeDoor).getPath() + "_" + i))
                                    .put(TextureSlot.PARTICLE, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" +BuiltInRegistries.BLOCK.getKey(largeDoor).getPath() + "_" + i)),
                            blockStateModelGenerator.modelOutput);

                    MEModels.LARGE_DOOR_RIGHT.createWithSuffix(largeDoor,"_right_" + i,
                            (new TextureMapping()).put(TextureSlot.ALL, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" +BuiltInRegistries.BLOCK.getKey(largeDoor).getPath() + "_" + i))
                                    .put(TextureSlot.PARTICLE, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" +BuiltInRegistries.BLOCK.getKey(largeDoor).getPath() + "_" + i)),
                            blockStateModelGenerator.modelOutput);

                    MEModels.LARGE_DOOR_RIGHT_OPEN.createWithSuffix(largeDoor,"_right_open_" + i,
                            (new TextureMapping()).put(TextureSlot.ALL, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" +BuiltInRegistries.BLOCK.getKey(largeDoor).getPath() + "_" + i))
                                    .put(TextureSlot.PARTICLE, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" +BuiltInRegistries.BLOCK.getKey(largeDoor).getPath() + "_" + i)),
                            blockStateModelGenerator.modelOutput);
                }
            }
        }
        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(largeDoor)
                .with(statesMap));
    }

    public final void registerThickLargeDoor(BlockModelGenerators blockStateModelGenerator, LargeDoorBlock largeDoor, IntegerProperty part){
        var statesMap = PropertyDispatch.properties(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.OPEN, BlockStateProperties.DOOR_HINGE, part);
        int rot = 0;
        for (int i = 0; i < largeDoor.getDoorWidth() * largeDoor.getDoorHeight(); i++){
            for(int k = 2; k < 6; k++){
                rot = switch (k) {
                    case 2 -> 0;
                    case 3 -> 180;
                    case 4 -> 270;
                    case 5 -> 90;
                    default -> rot;
                };

                statesMap.select(Direction.from3DDataValue(k), false, DoorHingeSide.LEFT, i, Variant.variant()
                        .with(VariantProperties.MODEL, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID,"block/" + BuiltInRegistries.BLOCK.getKey(largeDoor).getPath() + "_left_" + i))
                        .with(VariantProperties.UV_LOCK, false)
                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.valueOf("R" + rot)));

                statesMap.select(Direction.from3DDataValue(k), true, DoorHingeSide.LEFT, i, Variant.variant()
                        .with(VariantProperties.MODEL,ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" + BuiltInRegistries.BLOCK.getKey(largeDoor).getPath() + "_left_open_" + i))
                        .with(VariantProperties.UV_LOCK, false)
                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.valueOf("R" + rot)));

                statesMap.select(Direction.from3DDataValue(k), false, DoorHingeSide.RIGHT, i, Variant.variant()
                        .with(VariantProperties.MODEL, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID,"block/" + BuiltInRegistries.BLOCK.getKey(largeDoor).getPath() + "_right_" + i))
                        .with(VariantProperties.UV_LOCK, false)
                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.valueOf("R" + rot)));

                statesMap.select(Direction.from3DDataValue(k), true, DoorHingeSide.RIGHT, i, Variant.variant()
                        .with(VariantProperties.MODEL, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID,"block/" + BuiltInRegistries.BLOCK.getKey(largeDoor).getPath() + "_right_open_" + i))
                        .with(VariantProperties.UV_LOCK, false)
                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.valueOf("R" + rot)));

                if (k == 2){
                    MEModels.LARGE_THICK_DOOR_LEFT.createWithSuffix(largeDoor, "_left_" + i,
                            (new TextureMapping()).put(TextureSlot.ALL, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID,"block/" + BuiltInRegistries.BLOCK.getKey(largeDoor).getPath() + "_" + i))
                                    .put(TextureSlot.PARTICLE, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID,"block/" + BuiltInRegistries.BLOCK.getKey(largeDoor).getPath() + "_" + i)),
                            blockStateModelGenerator.modelOutput);

                    MEModels.LARGE_THICK_DOOR_LEFT_OPEN.createWithSuffix(largeDoor,"_left_open_" + i,
                            (new TextureMapping()).put(TextureSlot.ALL, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" +BuiltInRegistries.BLOCK.getKey(largeDoor).getPath() + "_" + i))
                                    .put(TextureSlot.PARTICLE, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" +BuiltInRegistries.BLOCK.getKey(largeDoor).getPath() + "_" + i)),
                            blockStateModelGenerator.modelOutput);

                    MEModels.LARGE_THICK_DOOR_RIGHT.createWithSuffix(largeDoor,"_right_" + i,
                            (new TextureMapping()).put(TextureSlot.ALL, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" +BuiltInRegistries.BLOCK.getKey(largeDoor).getPath() + "_" + i))
                                    .put(TextureSlot.PARTICLE, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" +BuiltInRegistries.BLOCK.getKey(largeDoor).getPath() + "_" + i)),
                            blockStateModelGenerator.modelOutput);

                    MEModels.LARGE_THICK_DOOR_RIGHT_OPEN.createWithSuffix(largeDoor,"_right_open_" + i,
                            (new TextureMapping()).put(TextureSlot.ALL, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" +BuiltInRegistries.BLOCK.getKey(largeDoor).getPath() + "_" + i))
                                    .put(TextureSlot.PARTICLE, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" +BuiltInRegistries.BLOCK.getKey(largeDoor).getPath() + "_" + i)),
                            blockStateModelGenerator.modelOutput);
                }
            }
        }
        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(largeDoor)
                .with(statesMap));
    }


    public final void registerLeadGlassPane(BlockModelGenerators blockStateModelGenerator, Block glass, Block glassPane) {
        blockStateModelGenerator.createTrivialCube(glass);
        TextureMapping textureMap = TextureMapping.pane(glass, ModDecorativeBlocks.LEAD_GLASS_PANE);
        ResourceLocation identifier = ModelTemplates.STAINED_GLASS_PANE_POST.create(glassPane, textureMap, blockStateModelGenerator.modelOutput);
        ResourceLocation identifier2 = ModelTemplates.STAINED_GLASS_PANE_SIDE.create(glassPane, textureMap, blockStateModelGenerator.modelOutput);
        ResourceLocation identifier3 = ModelTemplates.STAINED_GLASS_PANE_SIDE_ALT.create(glassPane, textureMap, blockStateModelGenerator.modelOutput);
        ResourceLocation identifier4 = ModelTemplates.STAINED_GLASS_PANE_NOSIDE.create(glassPane, textureMap, blockStateModelGenerator.modelOutput);
        ResourceLocation identifier5 = ModelTemplates.STAINED_GLASS_PANE_NOSIDE_ALT.create(glassPane, textureMap, blockStateModelGenerator.modelOutput);
        Item item = glassPane.asItem();
        ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(item), TextureMapping.layer0(glass), blockStateModelGenerator.modelOutput);
        blockStateModelGenerator.blockStateOutput.accept(MultiPartGenerator.multiPart(glassPane).with(Variant.variant().with(VariantProperties.MODEL, identifier)).with(Condition.condition().term(BlockStateProperties.NORTH, true), Variant.variant().with(VariantProperties.MODEL, identifier2)).with(Condition.condition().term(BlockStateProperties.EAST, true), Variant.variant().with(VariantProperties.MODEL, identifier2).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).with(Condition.condition().term(BlockStateProperties.SOUTH, true), Variant.variant().with(VariantProperties.MODEL, identifier3)).with(Condition.condition().term(BlockStateProperties.WEST, true), Variant.variant().with(VariantProperties.MODEL, identifier3).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).with(Condition.condition().term(BlockStateProperties.NORTH, false), Variant.variant().with(VariantProperties.MODEL, identifier4)).with(Condition.condition().term(BlockStateProperties.EAST, false), Variant.variant().with(VariantProperties.MODEL, identifier5)).with(Condition.condition().term(BlockStateProperties.SOUTH, false), Variant.variant().with(VariantProperties.MODEL, identifier5).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).with(Condition.condition().term(BlockStateProperties.WEST, false), Variant.variant().with(VariantProperties.MODEL, identifier4).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)));
    }

    public final void registerPaneModel(BlockModelGenerators blockStateModelGenerator, Block pane) {
        TextureMapping textureMap = TextureMapping.pane(pane, pane);
        ResourceLocation identifier = ModelTemplates.STAINED_GLASS_PANE_POST.create(pane, textureMap, blockStateModelGenerator.modelOutput);
        ResourceLocation identifier2 = ModelTemplates.STAINED_GLASS_PANE_SIDE.create(pane, textureMap, blockStateModelGenerator.modelOutput);
        ResourceLocation identifier3 = ModelTemplates.STAINED_GLASS_PANE_SIDE_ALT.create(pane, textureMap, blockStateModelGenerator.modelOutput);
        ResourceLocation identifier4 = ModelTemplates.STAINED_GLASS_PANE_NOSIDE.create(pane, textureMap, blockStateModelGenerator.modelOutput);
        ResourceLocation identifier5 = ModelTemplates.STAINED_GLASS_PANE_NOSIDE_ALT.create(pane, textureMap, blockStateModelGenerator.modelOutput);
        Item item = pane.asItem();
        ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(item), TextureMapping.layer0(pane), blockStateModelGenerator.modelOutput);
        blockStateModelGenerator.blockStateOutput.accept(MultiPartGenerator.multiPart(pane).with(Variant.variant().with(VariantProperties.MODEL, identifier)).with(Condition.condition().term(BlockStateProperties.NORTH, true), Variant.variant().with(VariantProperties.MODEL, identifier2)).with(Condition.condition().term(BlockStateProperties.EAST, true), Variant.variant().with(VariantProperties.MODEL, identifier2).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).with(Condition.condition().term(BlockStateProperties.SOUTH, true), Variant.variant().with(VariantProperties.MODEL, identifier3)).with(Condition.condition().term(BlockStateProperties.WEST, true), Variant.variant().with(VariantProperties.MODEL, identifier3).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).with(Condition.condition().term(BlockStateProperties.NORTH, false), Variant.variant().with(VariantProperties.MODEL, identifier4)).with(Condition.condition().term(BlockStateProperties.EAST, false), Variant.variant().with(VariantProperties.MODEL, identifier5)).with(Condition.condition().term(BlockStateProperties.SOUTH, false), Variant.variant().with(VariantProperties.MODEL, identifier5).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).with(Condition.condition().term(BlockStateProperties.WEST, false), Variant.variant().with(VariantProperties.MODEL, identifier4).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)));
    }

    private void registerLayers(BlockModelGenerators blockStateModelGenerator, Block layers, Block origin, Boolean isVanilla) {
        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(layers).with(PropertyDispatch.property(BlockStateProperties.LAYERS).generate((height) -> {
            Variant var10000 = Variant.variant();
            VariantProperty var10001 = VariantProperties.MODEL;
            ResourceLocation var2;
            if (height < 8) {
                Block var10002 = layers;
                int var10003 = height;
                var2 = ModelLocationUtils.getModelLocation(var10002, "_height" + var10003 * 2);
            } else if (isVanilla) {
                var2 = ResourceLocation.fromNamespaceAndPath("minecraft", "block/" + BuiltInRegistries.BLOCK.getKey(origin).getPath());
            } else {
                var2 = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" + BuiltInRegistries.BLOCK.getKey(origin).getPath());
            }
            return var10000.with(var10001, var2);
        })));
        blockStateModelGenerator.delegateItemModel(layers, ModelLocationUtils.getModelLocation(layers, "_height2"));
    }

    private void registerTopWaterblock(BlockModelGenerators blockStateModelGenerator, Block block) {
        blockStateModelGenerator.createSimpleFlatItemModel(block);
        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createRotatedVariant(block, ModelLocationUtils.getModelLocation(block)));
    }

    public void registerStoneTrapdoor(BlockModelGenerators blockStateModelGenerator, Block trapdoorBlock) {
        TextureMapping textureMap = TextureMapping.defaultTexture(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" + BuiltInRegistries.BLOCK.getKey(trapdoorBlock).getPath().replaceAll("_trapdoor", "")));
        ResourceLocation identifier = ModelTemplates.TRAPDOOR_TOP.create(trapdoorBlock, textureMap, blockStateModelGenerator.modelOutput);
        ResourceLocation identifier2 = ModelTemplates.TRAPDOOR_BOTTOM.create(trapdoorBlock, textureMap, blockStateModelGenerator.modelOutput);
        ResourceLocation identifier3 = ModelTemplates.TRAPDOOR_OPEN.create(trapdoorBlock, textureMap, blockStateModelGenerator.modelOutput);
        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createTrapdoor(trapdoorBlock, identifier, identifier2, identifier3));
        blockStateModelGenerator.delegateItemModel(trapdoorBlock, identifier2);
    }

    public void registerRocksBlock(BlockModelGenerators blockStateModelGenerator, Block rocksBlock, Block origin) {
        ResourceLocation stage0 = MEModels.ROCKS_STAGE_0.create(rocksBlock,
                TextureMapping.singleSlot(TextureSlot.ALL, ResourceLocation.fromNamespaceAndPath(BuiltInRegistries.BLOCK.getKey(origin).getNamespace(), "block/" + BuiltInRegistries.BLOCK.getKey(origin).getPath())),
                blockStateModelGenerator.modelOutput);
        ResourceLocation stage1 = MEModels.ROCKS_STAGE_1.create(rocksBlock,
                TextureMapping.singleSlot(TextureSlot.ALL, ResourceLocation.fromNamespaceAndPath(BuiltInRegistries.BLOCK.getKey(origin).getNamespace(), "block/" + BuiltInRegistries.BLOCK.getKey(origin).getPath())),
                blockStateModelGenerator.modelOutput);
        ResourceLocation stage2 = MEModels.ROCKS_STAGE_2.create(rocksBlock,
                TextureMapping.singleSlot(TextureSlot.ALL, ResourceLocation.fromNamespaceAndPath(BuiltInRegistries.BLOCK.getKey(origin).getNamespace(), "block/" + BuiltInRegistries.BLOCK.getKey(origin).getPath())),
                blockStateModelGenerator.modelOutput);
        ResourceLocation stage3 = MEModels.ROCKS_STAGE_3.create(rocksBlock,
                TextureMapping.singleSlot(TextureSlot.ALL, ResourceLocation.fromNamespaceAndPath(BuiltInRegistries.BLOCK.getKey(origin).getNamespace(), "block/" + BuiltInRegistries.BLOCK.getKey(origin).getPath())),
                blockStateModelGenerator.modelOutput);
        
        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(rocksBlock).with(PropertyDispatch
                .properties(BlockStateProperties.HORIZONTAL_FACING, RocksBlock.STAGE)
                .select(Direction.EAST, 0, Variant.variant().with(VariantProperties.MODEL, stage0).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true))
                .select(Direction.WEST, 0, Variant.variant().with(VariantProperties.MODEL, stage0).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true))
                .select(Direction.SOUTH, 0, Variant.variant().with(VariantProperties.MODEL, stage0).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))
                .select(Direction.NORTH, 0, Variant.variant().with(VariantProperties.MODEL, stage0).with(VariantProperties.UV_LOCK, true))

                .select(Direction.EAST, 1, Variant.variant().with(VariantProperties.MODEL, stage1).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true))
                .select(Direction.WEST, 1, Variant.variant().with(VariantProperties.MODEL, stage1).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true))
                .select(Direction.SOUTH, 1, Variant.variant().with(VariantProperties.MODEL, stage1).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))
                .select(Direction.NORTH, 1, Variant.variant().with(VariantProperties.MODEL, stage1).with(VariantProperties.UV_LOCK, true))

                .select(Direction.EAST, 2, Variant.variant().with(VariantProperties.MODEL, stage2).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true))
                .select(Direction.WEST, 2, Variant.variant().with(VariantProperties.MODEL, stage2).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true))
                .select(Direction.SOUTH, 2, Variant.variant().with(VariantProperties.MODEL, stage2).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))
                .select(Direction.NORTH, 2, Variant.variant().with(VariantProperties.MODEL, stage2).with(VariantProperties.UV_LOCK, true))

                .select(Direction.EAST, 3, Variant.variant().with(VariantProperties.MODEL, stage3).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true))
                .select(Direction.WEST, 3, Variant.variant().with(VariantProperties.MODEL, stage3).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true))
                .select(Direction.SOUTH, 3, Variant.variant().with(VariantProperties.MODEL, stage3).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))
                .select(Direction.NORTH, 3, Variant.variant().with(VariantProperties.MODEL, stage3).with(VariantProperties.UV_LOCK, true))));
    }

    public void registerVanillaTrapdoor(BlockModelGenerators blockStateModelGenerator, Block trapdoorBlock) {
        TextureMapping textureMap;
        if (BuiltInRegistries.BLOCK.getKey(trapdoorBlock).getPath().contains("basalt")) {
            textureMap = TextureMapping.defaultTexture(ResourceLocation.parse("block/" + BuiltInRegistries.BLOCK.getKey(trapdoorBlock).getPath().replaceAll("_trapdoor", "_side")));
        } else {
            textureMap = TextureMapping.defaultTexture(ResourceLocation.parse("block/" + BuiltInRegistries.BLOCK.getKey(trapdoorBlock).getPath().replaceAll("_trapdoor", "")));
        }
        ResourceLocation identifier = ModelTemplates.TRAPDOOR_TOP.create(trapdoorBlock, textureMap, blockStateModelGenerator.modelOutput);
        ResourceLocation identifier2 = ModelTemplates.TRAPDOOR_BOTTOM.create(trapdoorBlock, textureMap, blockStateModelGenerator.modelOutput);
        ResourceLocation identifier3 = ModelTemplates.TRAPDOOR_OPEN.create(trapdoorBlock, textureMap, blockStateModelGenerator.modelOutput);
        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createTrapdoor(trapdoorBlock, identifier, identifier2, identifier3));
        blockStateModelGenerator.delegateItemModel(trapdoorBlock, identifier2);
    }


    public void registerOrientableTrapdoorLadder(BlockModelGenerators blockStateModelGenerator, Block ladderBlock) {
        ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID,"block/" + BuiltInRegistries.BLOCK.getKey(ladderBlock).getPath());
        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ladderBlock,
                Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(ladderBlock)))
                .with(PropertyDispatch.properties(BlockStateProperties.ATTACH_FACE, BlockStateProperties.HORIZONTAL_FACING)
                        .select(AttachFace.FLOOR, Direction.NORTH, Variant.variant())
                        .select(AttachFace.FLOOR, Direction.EAST, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                        .select(AttachFace.FLOOR, Direction.SOUTH, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                        .select(AttachFace.FLOOR, Direction.WEST, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                        .select(AttachFace.WALL, Direction.NORTH, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
                        .select(AttachFace.WALL, Direction.EAST, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                        .select(AttachFace.WALL, Direction.SOUTH, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                        .select(AttachFace.WALL, Direction.WEST, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                        .select(AttachFace.CEILING, Direction.SOUTH, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R180))
                        .select(AttachFace.CEILING, Direction.WEST, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                        .select(AttachFace.CEILING, Direction.NORTH, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                        .select(AttachFace.CEILING, Direction.EAST, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))));

        MEModels.THICK_LADDER.create(ladderBlock, new TextureMapping().put(TextureSlot.TEXTURE, texture).put(TextureSlot.PARTICLE,texture), blockStateModelGenerator.modelOutput);
    }

    public static final ResourceLocation TRIM_TYPE = ResourceLocation.parse("trim_type");
    private static final List<ItemTrimMaterial> TRIM_MATERIALS = List.of(
            new ItemTrimMaterial("jade", 0.001f, Map.of()),
            new ItemTrimMaterial("tin", 0.002f, Map.of()),
            new ItemTrimMaterial("lead", 0.003f, Map.of()),
            new ItemTrimMaterial("silver", 0.004f, Map.of()),
            new ItemTrimMaterial("bronze", 0.005f, Map.of()),
            new ItemTrimMaterial("steel", 0.006f, Map.of()),
            new ItemTrimMaterial("crude", 0.007f, Map.of()),
            new ItemTrimMaterial("burzum_steel", 0.008f, Map.of()),
            new ItemTrimMaterial("edhel_steel", 0.009f, Map.of()),
            new ItemTrimMaterial("khazad_steel", 0.011f, Map.of()),
            new ItemTrimMaterial("mithril", 0.012f, Map.of()),
            new ItemTrimMaterial("quartz", 0.1f, Map.of()),
            new ItemTrimMaterial("iron", 0.2f, Map.of(ArmorMaterials.IRON, "iron_darker")),
            new ItemTrimMaterial("netherite", 0.3f, Map.of(ArmorMaterials.NETHERITE, "netherite_darker")),
            new ItemTrimMaterial("redstone", 0.4f, Map.of()),
            new ItemTrimMaterial("copper", 0.5f, Map.of()),
            new ItemTrimMaterial("gold", 0.6f, Map.of(ArmorMaterials.GOLD, "gold_darker")),
            new ItemTrimMaterial("emerald", 0.7f, Map.of()),
            new ItemTrimMaterial("diamond", 0.8f, Map.of(ArmorMaterials.DIAMOND, "diamond_darker")),
            new ItemTrimMaterial("lapis", 0.9f, Map.of()),
            new ItemTrimMaterial("amethyst", 1.0f, Map.of())
    );

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        for (SimpleWallModel.Wall wall : SimpleWallModel.blocks) {
            ResourceLocation id = BuiltInRegistries.BLOCK.getKey(wall.wall());
            itemModelGenerator.generateFlatItem(wall.wall().asItem(), new ModelTemplate(Optional.of(id.withPath("block/" + id.getPath() + "_inventory")), Optional.empty()));
        }

        for (SimpleWallModel.Wall wall : SimpleWallModel.vanillaWalls) {
            ResourceLocation id = BuiltInRegistries.BLOCK.getKey(wall.wall());
            itemModelGenerator.generateFlatItem(wall.wall().asItem(), new ModelTemplate(Optional.of(id.withPath("block/" + id.getPath() + "_inventory")), Optional.empty()));
        }

        for (Item item : SimpleItemModel.items) {
            itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
        }

        for (Item item : SimpleHandheldItemModel.items) {
            itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_HANDHELD_ITEM);
        }

        for (Item item : SimpleDoorInventoryModel.items) {
            itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
        }

        for (Item item : SimpleBigItemModel.items) {
            itemModelGenerator.generateFlatItem(item, CustomItemModels.BIG_WEAPON);
            itemModelGenerator.generateFlatItem(item, "_inventory", ModelTemplates.FLAT_HANDHELD_ITEM);
        }

        for (Item item : SimpleBigItemModel.bigBows) {
            for (int i = 0; i < 3; i++) {
                itemModelGenerator.generateFlatItem(item, "_pulling_" + i, CustomItemModels.LONGBOW);
                itemModelGenerator.generateFlatItem(item, "_pulling_" + i + "_inventory", ModelTemplates.FLAT_HANDHELD_ITEM);

            }
        }

        for (Item item : SimpleBigItemModel.genericItems) {
            itemModelGenerator.generateFlatItem(item, "_inventory", ModelTemplates.FLAT_HANDHELD_ITEM);
        }

        for (Item item : HotMetalsModel.items) {
            itemModelGenerator.generateFlatItem(item, "_hot", ModelTemplates.FLAT_ITEM);
        }

        for (Item item : HotMetalsModel.ingots) {
            ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(item, "_hot"), TextureMapping.layer0(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "item/ingot_hot")), itemModelGenerator.output);
        }

        for (Item item : HotMetalsModel.nuggets) {
            ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(item, "_hot"), TextureMapping.layer0(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "item/nugget_hot")), itemModelGenerator.output);
        }

        for (Item item : SimpleSpearModel.items) {
            itemModelGenerator.generateFlatItem(item, "_inventory", ModelTemplates.FLAT_HANDHELD_ITEM);
        }

        for (Item item : SimpleBowItemModel.items) {
            for (int i = 0; i < 3; i++) {
                itemModelGenerator.generateFlatItem(item, "_pulling_" + i, CustomItemModels.BOW);
            }
        }

        for (Item item : SimpleCrossbowItemModel.items) {
            for (int i = 0; i < 3; i++) {
                itemModelGenerator.generateFlatItem(item, "_pulling_" + i, CustomItemModels.CROSSBOW);
            }
            itemModelGenerator.generateFlatItem(item, "_charged", CustomItemModels.CROSSBOW);
        }

        for (Item item : SimpleSpawnEggItemModel.items) {
            itemModelGenerator.generateFlatItem(item, CustomItemModels.TEMPLATE_SPAWN_EGG);
        }

        // Dyeables needs to be done manually (because of layers)

        SimpleDyeableItemModel.items.forEach(item -> {
            registerDyeableArmor((ArmorItem) item, itemModelGenerator);
        });

        // CLUSTERS
        itemModelGenerator.generateFlatItem(ModBlocks.QUARTZ_CLUSTER.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModBlocks.SMALL_QUARTZ_BUD.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModBlocks.MEDIUM_QUARTZ_BUD.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModBlocks.LARGE_QUARTZ_BUD.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModBlocks.RED_AGATE_CLUSTER.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModBlocks.SMALL_RED_AGATE_BUD.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModBlocks.MEDIUM_RED_AGATE_BUD.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModBlocks.LARGE_RED_AGATE_BUD.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModBlocks.CITRINE_CLUSTER.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModBlocks.SMALL_CITRINE_BUD.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModBlocks.MEDIUM_CITRINE_BUD.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModBlocks.LARGE_CITRINE_BUD.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModBlocks.GLOWSTONE_CLUSTER.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModBlocks.SMALL_GLOWSTONE_BUD.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModBlocks.MEDIUM_GLOWSTONE_BUD.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModBlocks.LARGE_GLOWSTONE_BUD.asItem(), ModelTemplates.FLAT_ITEM);

        registerPalettedItem(ModResourceItems.ROD, itemModelGenerator);
        registerPalettedItem(ModResourceItems.LARGE_ROD, itemModelGenerator);

        registerPalettedItem(ModResourceItems.PICKAXE_HEAD, itemModelGenerator);
        registerPalettedItem(ModResourceItems.AXE_HEAD, itemModelGenerator);
        registerPalettedItem(ModResourceItems.SHOVEL_HEAD, itemModelGenerator);
        registerPalettedItem(ModResourceItems.HOE_HEAD, itemModelGenerator);

        registerPalettedItem(ModResourceItems.BLADE, itemModelGenerator);
        registerPalettedItem(ModResourceItems.SHORT_BLADE, itemModelGenerator);
        registerPalettedItem(ModResourceItems.LONG_BLADE, itemModelGenerator);
        registerPalettedItem(ModResourceItems.SWORD_HILT, itemModelGenerator);

        registerPalettedItem(ModResourceItems.MAIL_RING, itemModelGenerator);
        registerPalettedItem(ModResourceItems.MAIL, itemModelGenerator);

        registerPalettedItem(ModResourceItems.SCALE, itemModelGenerator);
        registerPalettedItem(ModResourceItems.SCALE_MAIL, itemModelGenerator);

        registerPalettedItem(ModResourceItems.ARMOR_PLATE, itemModelGenerator);

        registerPalettedItem(ModResourceItems.HELMET_PLATE, itemModelGenerator);
        registerPalettedItem(ModResourceItems.SHIELD_BORDER, itemModelGenerator);
        registerPalettedItem(ModResourceItems.SHIELD_PLATE, itemModelGenerator);
    }

    public final void registerDyeableArmor(ArmorItem armor, ItemModelGenerators itemModelGenerator) {
        ResourceLocation identifier = ModelLocationUtils.getModelLocation(armor);
        ResourceLocation identifier2 = TextureMapping.getItemTexture(armor);
        ResourceLocation identifier3 = TextureMapping.getItemTexture(armor, "_overlay");
        ModelTemplates.TWO_LAYERED_ITEM.create(identifier, TextureMapping.layered(identifier2, identifier3), itemModelGenerator.output, (id, textures) -> {
                    return createArmorJson(id, textures, armor.getMaterial());
                }
        );
    }

    public final void registerPalettedItem(Item item, ItemModelGenerators itemModelGenerator) {
        ResourceLocation identifierItem = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "item/" + BuiltInRegistries.ITEM.getKey(item).getPath());

        ResourceLocation identifier2 = TextureMapping.getItemTexture(item);

        ModelTemplates.FLAT_ITEM.create(identifierItem, TextureMapping.layer0(identifierItem), itemModelGenerator.output, (id, textures) -> this.registerPalettedItemJson(item, id, textures, itemModelGenerator));
        for (ItemTrimMaterial trimMaterial : TRIM_MATERIALS) {

            String string;
            if (trimMaterial.name.contains("iron")) {
                string = trimMaterial.name + "_darker";
            } else {
                string = trimMaterial.name;
            }

            ResourceLocation identifier4 = itemModelGenerator.getItemModelForTrimMaterial(identifierItem, string);
            String string2 = BuiltInRegistries.ITEM.getKey(item).getPath() + "_trim_" + string;
            ResourceLocation identifier5 = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, string2).withPrefix("trims/items/");

            itemModelGenerator.generateLayeredItem(identifier4, identifier2, identifier5);
        }
    }

    public final JsonObject registerPalettedItemJson(Item item, ResourceLocation id, Map<TextureSlot, ResourceLocation> textures, ItemModelGenerators itemModelGenerator) {
        ResourceLocation identifierItem = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "item/" + BuiltInRegistries.ITEM.getKey(item).getPath());

        JsonObject jsonObject = ModelTemplates.TWO_LAYERED_ITEM.createBaseTemplate(identifierItem, textures);
        JsonArray jsonArray = new JsonArray();
        for (ItemTrimMaterial trimMaterial : TRIM_MATERIALS) {
            JsonObject jsonObject2 = new JsonObject();
            JsonObject jsonObject3 = new JsonObject();
            jsonObject3.addProperty(TRIM_TYPE.getPath(), Float.valueOf(trimMaterial.itemModelIndex()));
            jsonObject2.add("predicate", jsonObject3);
            String string;
            if (trimMaterial.name.contains("iron")) {
                string = trimMaterial.name + "_darker";
            } else {
                string = trimMaterial.name;
            }
            jsonObject2.addProperty("model", itemModelGenerator.getItemModelForTrimMaterial(id, string).toString());
            jsonArray.add(jsonObject2);
        }

        jsonObject.add("overrides", jsonArray);

        return jsonObject;
    }

    public final JsonObject createArmorJson(ResourceLocation id, Map<TextureSlot, ResourceLocation> textures, Holder<ArmorMaterial> armorMaterial) {
        return ModelTemplates.TWO_LAYERED_ITEM.createBaseTemplate(id, textures);
    }

    record ItemTrimMaterial(String name, float itemModelIndex,
                            Map<Holder<ArmorMaterial>, String> overrideArmorMaterials) {

        public String getAppliedName(Holder<ArmorMaterial> armorMaterial) {
            return this.overrideArmorMaterials.getOrDefault(armorMaterial, this.name);
        }
    }
}
