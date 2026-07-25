package net.jukoz.me.block;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.special.*;
import net.jukoz.me.block.special.verticalSlabs.VerticalSlabBlock;
import net.jukoz.me.item.utils.ModItemGroups;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class MushroomBlockSets {
    public static final float MUSHROOM_STRENGTH = 2f;
    public static final float PLATE_BUTTON_STRENGTH = 0.1f;

    public static MushroomBlockSets.MushroomBlockSet DARK_MUSHROOM = registerMushroomSet("dark_mushroom", null);
    public static MushroomBlockSets.MushroomBlockSet GRAY_MUSHROOM = registerMushroomSet("gray_mushroom", null);
    public static MushroomBlockSets.MushroomBlockSet MUSHROOM = registerMushroomSet("mushroom", Blocks.MUSHROOM_STEM);


    public record MushroomBlockSet(Block stem, Block stemWall, Block stemFence,
                                 Block planks, Block planksSlab, Block planksVerticalSlab, Block planksStairs, Block planksFence, Block planksGate,
                                 Block pressurePlate, Block button, Block door, Block trapdoor, Block stool, Block bench, Block table, Block chair, Block ladder) {
    }

    public static MushroomBlockSets.MushroomBlockSet[] sets = new MushroomBlockSets.MushroomBlockSet[] {
            DARK_MUSHROOM,
            GRAY_MUSHROOM,
            MUSHROOM,
    };

    private static MushroomBlockSets.MushroomBlockSet registerMushroomSet(String name, Block stem) {

        if(stem == null){
            stem = ModBlocks.registerWoodBlock(name + "_stem", new HugeMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM).sound(SoundType.WOOD)),false);
        }

        Block stemWall = ModBlocks.registerWoodBlock(name + "_stem_wall", new WallBlock(BlockBehaviour.Properties.ofFullCopy(stem).strength(MushroomBlockSets.MUSHROOM_STRENGTH).sound(SoundType.WOOD)),false);

        Block stemFence = ModBlocks.registerWoodBlock(name + "_stem_fence", new FenceBlock(BlockBehaviour.Properties.ofFullCopy(stem).strength(MushroomBlockSets.MUSHROOM_STRENGTH).sound(SoundType.WOOD)),false);

        Block planks = ModBlocks.registerWoodBlock(name + "_planks", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(MushroomBlockSets.MUSHROOM_STRENGTH).sound(SoundType.WOOD)),false);

        Block slab = ModBlocks.registerWoodBlock(name + "_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(planks)
                .strength(MushroomBlockSets.MUSHROOM_STRENGTH, ModBlocks.SLAB_RESISTANCE).sound(SoundType.WOOD)),false);

        Block verticalSlab = ModBlocks.registerWoodBlock(name + "_vertical_slab", new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(planks).strength(MushroomBlockSets.MUSHROOM_STRENGTH).sound(SoundType.WOOD)),false);

        Block stairs = ModBlocks.registerWoodBlock(name + "_stairs", new StairBlock(planks.defaultBlockState(),
                BlockBehaviour.Properties.ofFullCopy(planks).strength(MushroomBlockSets.MUSHROOM_STRENGTH).sound(SoundType.WOOD)),false);

        Block fence = ModBlocks.registerWoodBlock(name + "_fence", new FenceBlock(BlockBehaviour.Properties.ofFullCopy(planks)
                .strength(MushroomBlockSets.MUSHROOM_STRENGTH).sound(SoundType.WOOD)),false);

        Block gate = ModBlocks.registerWoodBlock(name + "_fence_gate",  new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(planks)
                .strength(MushroomBlockSets.MUSHROOM_STRENGTH).sound(SoundType.WOOD)),false);

        Block button = ModBlocks.registerWoodBlock(name + "_button",  new ButtonBlock(BlockSetType.OAK, 5, BlockBehaviour.Properties.ofFullCopy(planks).strength(PLATE_BUTTON_STRENGTH)
                .sound(SoundType.WOOD).noCollission()),false);

        Block pressurePlate = ModBlocks.registerWoodBlock(name + "_pressure_plate",  new PressurePlateBlock(BlockSetType.OAK,
                BlockBehaviour.Properties.ofFullCopy(planks).strength(PLATE_BUTTON_STRENGTH).sound(SoundType.WOOD).noCollission()),false);

        Block door = ModBlocks.registerWoodBlock(name + "_door", new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(planks)
                .sound(SoundType.WOOD).noOcclusion()),false);

        Block trapdoor = ModBlocks.registerWoodBlock(name + "_trapdoor", new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(planks)
                .sound(SoundType.WOOD).noOcclusion()),false);

        Block stool = ModBlocks.registerBlock(name + "_stool", new WoodStoolBlock(BlockBehaviour.Properties.ofFullCopy(planks)
                .sound(SoundType.WOOD).noOcclusion()),false);

        Block bench = ModBlocks.registerBlock(name + "_bench", new WoodBenchBlock(BlockBehaviour.Properties.ofFullCopy(planks)
                .sound(SoundType.WOOD).noOcclusion()),false);

        Block table = ModBlocks.registerBlock(name + "_table", new WoodTableBlock(BlockBehaviour.Properties.ofFullCopy(planks)
                .sound(SoundType.WOOD).noOcclusion()),false);

        Block chair = ModBlocks.registerBlock(name + "_chair", new WoodChairBlock(BlockBehaviour.Properties.ofFullCopy(planks)
                .sound(SoundType.WOOD).noOcclusion()),false);

        Block ladder = ModBlocks.registerBlock(name + "_ladder", new ThickLadderBlock(BlockBehaviour.Properties.ofFullCopy(planks)
                .sound(SoundType.LADDER).noOcclusion()),false);

        ModItemGroups.DECORATIVES_BLOCKS_CONTENT.add(stool.asItem().getDefaultInstance());
        ModItemGroups.DECORATIVES_BLOCKS_CONTENT.add(bench.asItem().getDefaultInstance());
        ModItemGroups.DECORATIVES_BLOCKS_CONTENT.add(table.asItem().getDefaultInstance());
        ModItemGroups.DECORATIVES_BLOCKS_CONTENT.add(chair.asItem().getDefaultInstance());
        ModItemGroups.DECORATIVES_BLOCKS_CONTENT.add(ladder.asItem().getDefaultInstance());

        return new MushroomBlockSet(stem, stemWall, stemFence, planks, slab, verticalSlab, stairs, fence, gate, pressurePlate, button, door, trapdoor, stool, bench, table, chair, ladder);
    }

    public static void registerModBlockSets() {
        LoggerUtil.logDebugMsg("Registering MushroomBlockSets for " + MiddleEarth.MOD_ID);
    }
}
