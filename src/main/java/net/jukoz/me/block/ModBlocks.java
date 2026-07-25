package net.jukoz.me.block;

import net.jukoz.me.utils.NeoForgeRegistrationBridge;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.special.*;
import net.jukoz.me.block.special.gemstones.CustomBuddingGemBlock;
import net.jukoz.me.block.special.verticalSlabs.VerticalSlabBlock;
import net.jukoz.me.datageneration.content.loot_tables.BlockDrops;
import net.jukoz.me.item.utils.ModItemGroups;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.world.level.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.AmethystBlock;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ColoredFallingBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.HayBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.MudBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.PushReaction;
import java.util.List;

public class ModBlocks {
    public static final float DIRT_STRENGTH = 0.6f;
    public static final float SLAB_RESISTANCE = 6.0f; // From explosions

    //region STONE PILLARS AND CHISELED
    public static final Block STONE_PILLAR = registerStoneBlock("stone_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block MOSSY_STONE_PILLAR = registerStoneBlock("mossy_stone_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(STONE_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CRACKED_STONE_PILLAR = registerStoneBlock("cracked_stone_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(STONE_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    
    public static final Block DEEPSLATE_PILLAR = registerStoneBlock("deepslate_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.DEEPSLATE_BRICKS_HARDNESS, StoneBlockSets.DEEPSLATE_BRICKS_BLAST_RESISTANCE).sound(SoundType.DEEPSLATE)),true);
    public static final Block MOSSY_DEEPSLATE_PILLAR = registerStoneBlock("mossy_deepslate_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(DEEPSLATE_PILLAR).strength(StoneBlockSets.DEEPSLATE_BRICKS_HARDNESS, StoneBlockSets.DEEPSLATE_BRICKS_BLAST_RESISTANCE).sound(SoundType.DEEPSLATE)),true);
    public static final Block CRACKED_DEEPSLATE_PILLAR = registerStoneBlock("cracked_deepslate_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(DEEPSLATE_PILLAR).strength(StoneBlockSets.DEEPSLATE_BRICKS_HARDNESS, StoneBlockSets.DEEPSLATE_BRICKS_BLAST_RESISTANCE).sound(SoundType.DEEPSLATE)),true);

    public static final Block ASHEN_PILLAR = registerStoneBlock("ashen_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.DEEPSLATE)),true);

    public static final Block GONLUIN_PILLAR = registerStoneBlock("gonluin_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block MOSSY_GONLUIN_PILLAR = registerStoneBlock("mossy_gonluin_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CRACKED_GONLUIN_PILLAR = registerStoneBlock("cracked_gonluin_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block BLUE_TUFF_PILLAR = registerStoneBlock("blue_tuff_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block MOSSY_BLUE_TUFF_PILLAR = registerStoneBlock("mossy_blue_tuff_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CRACKED_BLUE_TUFF_PILLAR = registerStoneBlock("cracked_blue_tuff_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block GREEN_TUFF_PILLAR = registerStoneBlock("green_tuff_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CRACKED_GREEN_TUFF_PILLAR = registerStoneBlock("cracked_green_tuff_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block SCHIST_PILLAR = registerStoneBlock("schist_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block DOLOMITE_PILLAR = registerStoneBlock("dolomite_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block MOSSY_DOLOMITE_PILLAR = registerStoneBlock("mossy_dolomite_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CRACKED_DOLOMITE_PILLAR = registerStoneBlock("cracked_dolomite_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block IRONSTONE_PILLAR = registerStoneBlock("ironstone_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block HEMATITE_PILLAR = registerStoneBlock("hematite_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block GNEISS_PILLAR = registerStoneBlock("gneiss_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block ZIGILABAN_PILLAR = registerStoneBlock("zigilaban_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block IZHERABAN_PILLAR = registerStoneBlock("izheraban_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block MOSSY_IZHERABAN_PILLAR = registerStoneBlock("mossy_izheraban_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CRACKED_IZHERABAN_PILLAR = registerStoneBlock("cracked_izheraban_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    
    public static final Block CALCITE_PILLAR = registerStoneBlock("calcite_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block MOSSY_CALCITE_PILLAR = registerStoneBlock("mossy_calcite_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CRACKED_CALCITE_PILLAR = registerStoneBlock("cracked_calcite_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block LIMESTONE_PILLAR = registerStoneBlock("limestone_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block MOSSY_LIMESTONE_PILLAR = registerStoneBlock("mossy_limestone_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CRACKED_LIMESTONE_PILLAR = registerStoneBlock("cracked_limestone_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    
    public static final Block GALONN_PILLAR = registerStoneBlock("galonn_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block MOSSY_GALONN_PILLAR = registerStoneBlock("mossy_galonn_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CRACKED_GALONN_PILLAR = registerStoneBlock("cracked_galonn_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    
    public static final Block ANDESITE_PILLAR = registerStoneBlock("andesite_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block MOSSY_ANDESITE_PILLAR = registerStoneBlock("mossy_andesite_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CRACKED_ANDESITE_PILLAR = registerStoneBlock("cracked_andesite_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block GRANITE_PILLAR = registerStoneBlock("granite_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block MOSSY_GRANITE_PILLAR = registerStoneBlock("mossy_granite_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CRACKED_GRANITE_PILLAR = registerStoneBlock("cracked_granite_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block DIORITE_PILLAR = registerStoneBlock("diorite_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block MOSSY_DIORITE_PILLAR = registerStoneBlock("mossy_diorite_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CRACKED_DIORITE_PILLAR = registerStoneBlock("cracked_diorite_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block BLACKSTONE_PILLAR = registerStoneBlock("blackstone_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block MOSSY_BLACKSTONE_PILLAR = registerStoneBlock("mossy_blackstone_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CRACKED_BLACKSTONE_PILLAR = registerStoneBlock("cracked_blackstone_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    
    public static final Block BASALT_PILLAR = registerStoneBlock("basalt_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block MOSSY_BASALT_PILLAR = registerStoneBlock("mossy_basalt_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CRACKED_BASALT_PILLAR = registerStoneBlock("cracked_basalt_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block TUFF_PILLAR = registerStoneBlock("tuff_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block MOSSY_TUFF_PILLAR = registerStoneBlock("mossy_tuff_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CRACKED_TUFF_PILLAR = registerStoneBlock("cracked_tuff_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block JADEITE_PILLAR = registerStoneBlock("jadeite_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CRACKED_JADEITE_PILLAR = registerStoneBlock("cracked_jadeite_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block MEDGON_PILLAR = registerStoneBlock("medgon_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block MOSSY_MEDGON_PILLAR = registerStoneBlock("mossy_medgon_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CRACKED_MEDGON_PILLAR = registerStoneBlock("cracked_medgon_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block CHISELED_STONE = registerStoneBlock("chiseled_stone",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_POLISHED_STONE = registerStoneBlock("chiseled_polished_stone",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_STONE_TILES = registerStoneBlock("chiseled_stone_tiles",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_SMOOTH_STONE = registerStoneBlock("chiseled_smooth_stone",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block CHISELED_DEEPSLATE_BRICKS = registerStoneBlock("chiseled_deepslate_bricks",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_POLISHED_DEEPSLATE = registerStoneBlock("chiseled_polished_deepslate",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_DEEPSLATE_TILES = registerStoneBlock("chiseled_deepslate_tiles",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_SMOOTH_DEEPSLATE = registerStoneBlock("chiseled_smooth_deepslate",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block CHISELED_ASHEN_BRICKS = registerStoneBlock("chiseled_ashen_bricks",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.DEEPSLATE)),true);
    public static final Block CHISELED_POLISHED_ASHEN_STONE = registerStoneBlock("chiseled_polished_ashen_stone",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.DEEPSLATE)),true);

    public static final Block CHISELED_GONLUIN = registerStoneBlock("chiseled_gonluin",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_GONLUIN_BRICKS = registerStoneBlock("chiseled_gonluin_bricks",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_POLISHED_GONLUIN = registerStoneBlock("chiseled_polished_gonluin",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block CHISELED_BLUE_TUFF_BRICKS = registerStoneBlock("chiseled_blue_tuff_bricks",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_POLISHED_BLUE_TUFF = registerStoneBlock("chiseled_polished_blue_tuff",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block CHISELED_GREEN_TUFF = registerStoneBlock("chiseled_green_tuff",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_GREEN_TUFF_BRICKS = registerStoneBlock("chiseled_green_tuff_bricks",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_POLISHED_GREEN_TUFF = registerStoneBlock("chiseled_polished_green_tuff",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_GREEN_TUFF_TILES = registerStoneBlock("chiseled_green_tuff_tiles",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_SMOOTH_GREEN_TUFF = registerStoneBlock("chiseled_smooth_green_tuff",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block GILDED_CHISELED_GREEN_TUFF = registerStoneBlock("gilded_chiseled_green_tuff",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block GILDED_CHISELED_GREEN_TUFF_BRICKS = registerStoneBlock("gilded_chiseled_green_tuff_bricks",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block GILDED_CHISELED_POLISHED_GREEN_TUFF = registerStoneBlock("gilded_chiseled_polished_green_tuff",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block GILDED_CHISELED_GREEN_TUFF_TILES = registerStoneBlock("gilded_chiseled_green_tuff_tiles",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block GILDED_CHISELED_SMOOTH_GREEN_TUFF = registerStoneBlock("gilded_chiseled_smooth_green_tuff",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block CHISELED_DOLOMITE = registerStoneBlock("chiseled_dolomite",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_DOLOMITE_BRICKS = registerStoneBlock("chiseled_dolomite_bricks",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_POLISHED_DOLOMITE = registerStoneBlock("chiseled_polished_dolomite",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_DOLOMITE_TILES = registerStoneBlock("chiseled_dolomite_tiles",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_SMOOTH_DOLOMITE = registerStoneBlock("chiseled_smooth_dolomite",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block POINTED_DOLOMITE = registerStoneBlock("pointed_dolomite",
            new PointedDolomiteBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POINTED_DRIPSTONE).randomTicks().strength(1.5F, 3.0F).dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY).isRedstoneConductor((state, world, pos) -> false)), false);

    public static final Block CHISELED_IRONSTONE = registerStoneBlock("chiseled_ironstone",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_IRONSTONE_BRICKS = registerStoneBlock("chiseled_ironstone_bricks",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_POLISHED_IRONSTONE = registerStoneBlock("chiseled_polished_ironstone",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block CHISELED_HEMATITE = registerStoneBlock("chiseled_hematite",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    //TODO : Waiting for texture
    //public static final Block CHISELED_HEMATITE_BRICKS = registerStoneBlock("chiseled_hematite_bricks",
    //        new PillarBlock(AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sounds(BlockSoundGroup.STONE)),true);
    public static final Block CHISELED_POLISHED_HEMATITE = registerStoneBlock("chiseled_polished_hematite",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block CHISELED_GNEISS = registerStoneBlock("chiseled_gneiss",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_GNEISS_BRICKS = registerStoneBlock("chiseled_gneiss_bricks",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_POLISHED_GNEISS = registerStoneBlock("chiseled_polished_gneiss",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    
    public static final Block CHISELED_ZIGILABAN = registerStoneBlock("chiseled_zigilaban",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_ZIGILABAN_BRICKS = registerStoneBlock("chiseled_zigilaban_bricks",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_POLISHED_ZIGILABAN = registerStoneBlock("chiseled_polished_zigilaban",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block CHISELED_IZHERABAN = registerStoneBlock("chiseled_izheraban",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_IZHERABAN_BRICKS = registerStoneBlock("chiseled_izheraban_bricks",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_POLISHED_IZHERABAN = registerStoneBlock("chiseled_polished_izheraban",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_IZHERABAN_TILES = registerStoneBlock("chiseled_izheraban_tiles",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_SMOOTH_IZHERABAN = registerStoneBlock("chiseled_smooth_izheraban",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block POINTED_IZHERABAN = registerStoneBlock("pointed_izheraban",
            new PointedIzherabanBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POINTED_DRIPSTONE).randomTicks().strength(1.5F, 3.0F).dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY).isRedstoneConductor((state, world, pos) -> false)), false);

    public static final Block CHISELED_CALCITE = registerStoneBlock("chiseled_calcite",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_CALCITE_BRICKS = registerStoneBlock("chiseled_calcite_bricks",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_POLISHED_CALCITE = registerStoneBlock("chiseled_polished_calcite",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_CALCITE_TILES = registerStoneBlock("chiseled_calcite_tiles",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_SMOOTH_CALCITE = registerStoneBlock("chiseled_smooth_calcite",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block CHISELED_LIMESTONE = registerStoneBlock("chiseled_limestone",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_LIMESTONE_BRICKS = registerStoneBlock("chiseled_limestone_bricks",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_POLISHED_LIMESTONE = registerStoneBlock("chiseled_polished_limestone",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_LIMESTONE_TILES = registerStoneBlock("chiseled_limestone_tiles",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_SMOOTH_LIMESTONE = registerStoneBlock("chiseled_smooth_limestone",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block POINTED_LIMESTONE = registerStoneBlock("pointed_limestone",
            new PointedLimestoneBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POINTED_DRIPSTONE).randomTicks().strength(1.5F, 3.0F).dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY).isRedstoneConductor((state, world, pos) -> false)), false);

    public static final Block CHISELED_GALONN = registerStoneBlock("chiseled_galonn",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_GALONN_BRICKS = registerStoneBlock("chiseled_galonn_bricks",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_POLISHED_GALONN = registerStoneBlock("chiseled_polished_galonn",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_GALONN_TILES = registerStoneBlock("chiseled_galonn_tiles",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_SMOOTH_GALONN = registerStoneBlock("chiseled_smooth_galonn",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block POINTED_GALONN = registerStoneBlock("pointed_galonn",
            new PointedGalonnBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POINTED_DRIPSTONE).randomTicks().strength(1.5F, 3.0F).dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY).isRedstoneConductor((state, world, pos) -> false)), false);

    public static final Block CHISELED_ANDESITE = registerStoneBlock("chiseled_andesite",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_ANDESITE_BRICKS = registerStoneBlock("chiseled_andesite_bricks",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_POLISHED_ANDESITE = registerStoneBlock("chiseled_polished_andesite",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_ANDESITE_TILES = registerStoneBlock("chiseled_andesite_tiles",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_SMOOTH_ANDESITE = registerStoneBlock("chiseled_smooth_andesite",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block CHISELED_GRANITE = registerStoneBlock("chiseled_granite",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_GRANITE_BRICKS = registerStoneBlock("chiseled_granite_bricks",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_POLISHED_GRANITE = registerStoneBlock("chiseled_polished_granite",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_GRANITE_TILES = registerStoneBlock("chiseled_granite_tiles",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_SMOOTH_GRANITE = registerStoneBlock("chiseled_smooth_granite",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block CHISELED_DIORITE = registerStoneBlock("chiseled_diorite",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_DIORITE_BRICKS = registerStoneBlock("chiseled_diorite_bricks",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_POLISHED_DIORITE = registerStoneBlock("chiseled_polished_diorite",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_DIORITE_TILES = registerStoneBlock("chiseled_diorite_tiles",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_SMOOTH_DIORITE = registerStoneBlock("chiseled_smooth_diorite",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block CHISELED_BASALT = registerStoneBlock("chiseled_basalt",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_BASALT_BRICKS = registerStoneBlock("chiseled_basalt_bricks",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_POLISHED_BASALT = registerStoneBlock("chiseled_polished_basalt",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_BASALT_TILES = registerStoneBlock("chiseled_basalt_tiles",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_SMOOTH_BASALT = registerStoneBlock("chiseled_smooth_basalt",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block CHISELED_BLACKSTONE = registerStoneBlock("chiseled_blackstone",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_POLISHED_BLACKSTONE_BRICKS = registerStoneBlock("chiseled_polished_blackstone_bricks",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_BLACKSTONE_TILES = registerStoneBlock("chiseled_blackstone_tiles",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_SMOOTH_BLACKSTONE = registerStoneBlock("chiseled_smooth_blackstone",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block CHISELED_SMOOTH_TUFF = registerStoneBlock("chiseled_smooth_tuff",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_POLISHED_TUFF = registerStoneBlock("chiseled_polished_tuff",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_TUFF_TILES = registerStoneBlock("chiseled_tuff_tiles",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);

    public static final Block NURGON_PILLAR = registerStoneBlock("nurgon_pillar",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR).strength(StoneBlockSets.NURGON_BRICKS_HARDNESS, StoneBlockSets.NURGON_BLAST_RESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_NURGON_BRICKS = registerStoneBlock("chiseled_nurgon_bricks",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.NURGON_BRICKS_HARDNESS, StoneBlockSets.NURGON_BLAST_RESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_POLISHED_NURGON = registerStoneBlock("chiseled_polished_nurgon",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.NURGON_BRICKS_HARDNESS, StoneBlockSets.NURGON_BLAST_RESISTANCE).sound(SoundType.STONE)),true);

    public static final Block CHISELED_MEDGON = registerStoneBlock("chiseled_medgon",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_MEDGON_BRICKS = registerStoneBlock("chiseled_medgon_bricks",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_POLISHED_MEDGON = registerStoneBlock("chiseled_polished_medgon",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_MEDGON_TILES = registerStoneBlock("chiseled_medgon_tiles",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
    public static final Block CHISELED_SMOOTH_MEDGON = registerStoneBlock("chiseled_smooth_medgon",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).strength(StoneBlockSets.BRICKS_HARDNESS, StoneBlockSets.BRICKS_BLASTRESISTANCE).sound(SoundType.STONE)),true);
//endregion

    public static final Block BLACK_WATTLE_TRAPDOOR = registerStoneBlock("black_wattle_trapdoor",
            new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)), true);
    public static final Block DARK_WATTLE_TRAPDOOR = registerStoneBlock("dark_wattle_trapdoor",
            new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)), true);
    public static final Block GREEN_WATTLE_TRAPDOOR = registerStoneBlock("green_wattle_trapdoor",
            new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)), true);
    public static final Block RED_WATTLE_TRAPDOOR = registerStoneBlock("red_wattle_trapdoor",
            new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)), true);
    public static final Block WATTLE_TRAPDOOR = registerStoneBlock("wattle_trapdoor",
            new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)), true);

    public static final Block SNOWY_GRASS_BLOCK = registerMiscBlock("snowy_grass_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK)),false);

    public static final Block GRASSY_DIRT = registerMiscBlock("grassy_dirt",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT).strength(DIRT_STRENGTH).sound(SoundType.GRAVEL)),true);
    public static final Block GRASSY_DIRT_SLAB = registerMiscBlock("grassy_dirt_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(GRASSY_DIRT)),true);
    public static final Block GRASSY_DIRT_STAIRS = registerMiscBlock("grassy_dirt_stairs",
            new StairBlock(GRASSY_DIRT.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(GRASSY_DIRT)),true);

    public static final Block PEBBLED_GRASS = registerMiscBlock("pebbled_grass",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE).sound(SoundType.STONE)),true);
    public static final Block PEBBLED_GRASS_SLAB = registerMiscBlock("pebbled_grass_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(PEBBLED_GRASS)),true);
    public static final Block PEBBLED_GRASS_STAIRS = registerMiscBlock("pebbled_grass_stairs",
            new StairBlock(PEBBLED_GRASS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(PEBBLED_GRASS)),true);

    public static final Block MIRE = registerMiscBlock("mire",
            new MudBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT).strength(DIRT_STRENGTH).sound(SoundType.MUD)),true);
    public static final Block MIRE_SLAB = registerMiscBlock("mire_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MIRE)),true);
    public static final Block MIRE_STAIRS = registerMiscBlock("mire_stairs",
            new StairBlock(MIRE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MIRE)),true);

    public static final Block TURF = registerMiscBlock("turf",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT).strength(DIRT_STRENGTH).sound(SoundType.GRASS)),true);
    public static final Block TURF_SLAB = registerMiscBlock("turf_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(TURF)),true);
    public static final Block TURF_STAIRS = registerMiscBlock("turf_stairs",
            new StairBlock(TURF.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(TURF)),true);
    public static final Block TURF_VERTICAL_SLAB = registerMiscBlock("turf_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(TURF)),true);

    public static final Block SNOWY_DIRT = registerMiscBlock("snowy_dirt",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COARSE_DIRT).strength(DIRT_STRENGTH).sound(SoundType.GRAVEL)),true);
    public static final Block SNOWY_DIRT_SLAB = registerMiscBlock("snowy_dirt_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SNOWY_DIRT)),true);
    public static final Block SNOWY_DIRT_STAIRS = registerMiscBlock("snowy_dirt_stairs",
            new StairBlock(SNOWY_DIRT.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SNOWY_DIRT)),true);

    public static final Block COBBLY_DIRT = registerMiscBlock("cobbly_dirt",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COARSE_DIRT).strength(DIRT_STRENGTH).sound(SoundType.GRAVEL)),true);
    public static final Block COBBLY_DIRT_SLAB = registerMiscBlock("cobbly_dirt_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(COBBLY_DIRT)),true);
    public static final Block COBBLY_DIRT_STAIRS = registerMiscBlock("cobbly_dirt_stairs",
            new StairBlock(COBBLY_DIRT.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(COBBLY_DIRT)),true);

    public static final Block DIRTY_ROOTS = registerMiscBlock("dirty_roots",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT).strength(DIRT_STRENGTH).sound(SoundType.GRAVEL)),true);
    public static final Block DIRTY_ROOTS_SLAB = registerMiscBlock("dirty_roots_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(DIRTY_ROOTS)),true);
    public static final Block DIRTY_ROOTS_STAIRS = registerMiscBlock("dirty_roots_stairs",
            new StairBlock(DIRTY_ROOTS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(DIRTY_ROOTS)),true);
    
    public static final Block DRY_DIRT = registerMiscBlock("dry_dirt",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT).strength(DIRT_STRENGTH).sound(SoundType.GRAVEL)),true);
    public static final Block DRY_DIRT_SLAB = registerMiscBlock("dry_dirt_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(DRY_DIRT)),true);
    public static final Block DRY_DIRT_STAIRS = registerMiscBlock("dry_dirt_stairs",
            new StairBlock(DRY_DIRT.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(DRY_DIRT)),true);
    public static final Block ASHEN_DIRT = registerMiscBlock("ashen_dirt",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT).strength(DIRT_STRENGTH).sound(SoundType.GRAVEL)),true);
    public static final Block ASHEN_DIRT_SLAB = registerMiscBlock("ashen_dirt_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(ASHEN_DIRT)),true);
    public static final Block ASHEN_DIRT_STAIRS = registerMiscBlock("ashen_dirt_stairs",
            new StairBlock(ASHEN_DIRT.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(ASHEN_DIRT)),true);

    public static final Block COBBLY_ASHEN_DIRT = registerMiscBlock("cobbly_ashen_dirt",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COARSE_DIRT).strength(DIRT_STRENGTH).sound(SoundType.GRAVEL)),true);
    public static final Block COBBLY_ASHEN_DIRT_SLAB = registerMiscBlock("cobbly_ashen_dirt_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(COBBLY_ASHEN_DIRT)),true);
    public static final Block COBBLY_ASHEN_DIRT_STAIRS = registerMiscBlock("cobbly_ashen_dirt_stairs",
            new StairBlock(COBBLY_ASHEN_DIRT.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(ASHEN_DIRT)),true);

    public static final Block ASHEN_SAND = registerMiscBlock("ashen_sand",
            new ColoredFallingBlock(new ColorRGBA(14406560), BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).strength(DIRT_STRENGTH).sound(SoundType.GRAVEL)),true);
    public static final Block ASHEN_SAND_LAYER = registerMiscBlock("ashen_sand_layer",
            new LayersBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SAND), ASHEN_SAND), false);

    public static final Block ASHEN_GRAVEL = registerMiscBlock("ashen_gravel",
            new ColoredFallingBlock(new ColorRGBA(14406560), BlockBehaviour.Properties.ofFullCopy(Blocks.GRAVEL).strength(DIRT_STRENGTH).sound(SoundType.GRAVEL)),true);
    public static final Block ASHEN_GRAVEL_LAYER = registerMiscBlock("ashen_gravel_layer",
            new LayersBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRAVEL), ASHEN_GRAVEL), false);

    public static final Block REED_BLOCK = registerMiscBlock("reed_block",
            new HayBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK).strength(DIRT_STRENGTH).sound(SoundType.GRASS)),true);
    public static final Block REED_STAIRS = registerMiscBlock("reed_stairs",
            new StairBlock(REED_BLOCK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK).strength(DIRT_STRENGTH).sound(SoundType.GRASS)),true);
    public static final Block REED_SLAB = registerMiscBlock("reed_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK).strength(DIRT_STRENGTH).sound(SoundType.GRASS)),true);
    public static final Block REED_VERTICAL_SLAB = registerMiscBlock("reed_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.REED_SLAB)),true);
    public static final Block REED_WALL = registerMiscBlock("reed_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK).strength(DIRT_STRENGTH).sound(SoundType.GRASS)),true);
    public static final Block STRAW_BLOCK = registerMiscBlock("straw_block",
            new HayBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK).strength(DIRT_STRENGTH).sound(SoundType.GRASS)),true);
    public static final Block STRAW_STAIRS = registerMiscBlock("straw_stairs",
            new StairBlock(STRAW_BLOCK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK).strength(DIRT_STRENGTH).sound(SoundType.GRASS)),true);
    public static final Block STRAW_SLAB = registerMiscBlock("straw_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK).strength(DIRT_STRENGTH).sound(SoundType.GRASS)),true);
    public static final Block STRAW_VERTICAL_SLAB = registerMiscBlock("straw_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.STRAW_SLAB)),true);
    public static final Block STRAW_WALL = registerMiscBlock("straw_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK).strength(DIRT_STRENGTH).sound(SoundType.GRASS)),true);

    public static final Block WATTLE_AND_BRICK = registerMiscBlock("wattle_and_brick",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block WATTLE_AND_BRICK_CROSS = registerMiscBlock("wattle_and_brick_cross",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block WATTLE_AND_BRICK_RIGHT = registerMiscBlock("wattle_and_brick_right",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block WATTLE_AND_BRICK_LEFT = registerMiscBlock("wattle_and_brick_left",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block WATTLE_AND_BRICK_PILLAR = registerMiscBlock("wattle_and_brick_pillar",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block WATTLE_AND_BRICK_DIAMOND = registerMiscBlock("wattle_and_brick_diamond",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);

    public static final Block WATTLE_AND_WHITE_DAUB = registerMiscBlock("wattle_and_white_daub",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block WATTLE_AND_WHITE_DAUB_CROSS = registerMiscBlock("wattle_and_white_daub_cross",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block WATTLE_AND_WHITE_DAUB_RIGHT = registerMiscBlock("wattle_and_white_daub_right",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block WATTLE_AND_WHITE_DAUB_LEFT = registerMiscBlock("wattle_and_white_daub_left",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block WATTLE_AND_WHITE_DAUB_PILLAR = registerMiscBlock("wattle_and_white_daub_pillar",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block WATTLE_AND_WHITE_DAUB_DIAMOND = registerMiscBlock("wattle_and_white_daub_diamond",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);

    public static final Block BLACK_WATTLE_AND_WHITE_DAUB = registerMiscBlock("black_wattle_and_white_daub",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block BLACK_WATTLE_AND_WHITE_DAUB_CROSS = registerMiscBlock("black_wattle_and_white_daub_cross",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block BLACK_WATTLE_AND_WHITE_DAUB_RIGHT = registerMiscBlock("black_wattle_and_white_daub_right",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block BLACK_WATTLE_AND_WHITE_DAUB_LEFT = registerMiscBlock("black_wattle_and_white_daub_left",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block BLACK_WATTLE_AND_WHITE_DAUB_PILLAR = registerMiscBlock("black_wattle_and_white_daub_pillar",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block BLACK_WATTLE_AND_WHITE_DAUB_DIAMOND = registerMiscBlock("black_wattle_and_white_daub_diamond",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);

    public static final Block GREEN_WATTLE_AND_WHITE_DAUB = registerMiscBlock("green_wattle_and_white_daub",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block GREEN_WATTLE_AND_WHITE_DAUB_CROSS = registerMiscBlock("green_wattle_and_white_daub_cross",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block GREEN_WATTLE_AND_WHITE_DAUB_RIGHT = registerMiscBlock("green_wattle_and_white_daub_right",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block GREEN_WATTLE_AND_WHITE_DAUB_LEFT = registerMiscBlock("green_wattle_and_white_daub_left",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block GREEN_WATTLE_AND_WHITE_DAUB_PILLAR = registerMiscBlock("green_wattle_and_white_daub_pillar",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block GREEN_WATTLE_AND_WHITE_DAUB_DIAMOND = registerMiscBlock("green_wattle_and_white_daub_diamond",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);

    public static final Block RED_WATTLE_AND_WHITE_DAUB = registerMiscBlock("red_wattle_and_white_daub",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block RED_WATTLE_AND_WHITE_DAUB_CROSS = registerMiscBlock("red_wattle_and_white_daub_cross",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block RED_WATTLE_AND_WHITE_DAUB_RIGHT = registerMiscBlock("red_wattle_and_white_daub_right",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block RED_WATTLE_AND_WHITE_DAUB_LEFT = registerMiscBlock("red_wattle_and_white_daub_left",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block RED_WATTLE_AND_WHITE_DAUB_PILLAR = registerMiscBlock("red_wattle_and_white_daub_pillar",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block RED_WATTLE_AND_WHITE_DAUB_DIAMOND = registerMiscBlock("red_wattle_and_white_daub_diamond",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);

    public static final Block DARK_WATTLE_AND_DARK_DAUB = registerMiscBlock("dark_wattle_and_dark_daub",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block DARK_WATTLE_AND_DARK_DAUB_CROSS = registerMiscBlock("dark_wattle_and_dark_daub_cross",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block DARK_WATTLE_AND_DARK_DAUB_RIGHT = registerMiscBlock("dark_wattle_and_dark_daub_right",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block DARK_WATTLE_AND_DARK_DAUB_LEFT = registerMiscBlock("dark_wattle_and_dark_daub_left",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block DARK_WATTLE_AND_DARK_DAUB_PILLAR = registerMiscBlock("dark_wattle_and_dark_daub_pillar",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block DARK_WATTLE_AND_DARK_DAUB_DIAMOND = registerMiscBlock("dark_wattle_and_dark_daub_diamond",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);

    public static final Block WATTLE_AND_YELLOW_DAUB = registerMiscBlock("wattle_and_yellow_daub",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block WATTLE_AND_YELLOW_DAUB_CROSS = registerMiscBlock("wattle_and_yellow_daub_cross",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block WATTLE_AND_YELLOW_DAUB_RIGHT = registerMiscBlock("wattle_and_yellow_daub_right",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block WATTLE_AND_YELLOW_DAUB_LEFT = registerMiscBlock("wattle_and_yellow_daub_left",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block WATTLE_AND_YELLOW_DAUB_PILLAR = registerMiscBlock("wattle_and_yellow_daub_pillar",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);
    public static final Block WATTLE_AND_YELLOW_DAUB_DIAMOND = registerMiscBlock("wattle_and_yellow_daub_diamond",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)),true);

    //region METAL AND GEMS
    public static final Block RAW_MITHRIL_BLOCK = registerMiscBlock("raw_mithril_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).strength(4f).requiresCorrectToolForDrops()),true);
    public static final Block MITHRIL_BLOCK = registerMiscBlock("mithril_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(6f).requiresCorrectToolForDrops()),true);
    public static final Block RAW_TIN_BLOCK = registerMiscBlock("raw_tin_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(2f).requiresCorrectToolForDrops()),true);
    public static final Block TIN_BLOCK = registerMiscBlock("tin_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(3f).requiresCorrectToolForDrops()),true);
    public static final Block RAW_LEAD_BLOCK = registerMiscBlock("raw_lead_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).strength(3f).requiresCorrectToolForDrops()),true);
    public static final Block LEAD_BLOCK = registerMiscBlock("lead_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(2f).requiresCorrectToolForDrops()),true);
    public static final Block RAW_SILVER_BLOCK = registerMiscBlock("raw_silver_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).strength(3f).requiresCorrectToolForDrops()),true);
    public static final Block SILVER_BLOCK = registerMiscBlock("silver_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(4f).requiresCorrectToolForDrops()),true);

    public static final Block BRONZE_BLOCK = registerMiscBlock("bronze_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(5f).requiresCorrectToolForDrops()),true);
    public static final Block CRUDE_BLOCK = registerMiscBlock("crude_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(5.5f).requiresCorrectToolForDrops()),true);
    public static final Block STEEL_BLOCK = registerMiscBlock("steel_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(6f).requiresCorrectToolForDrops()),true);
    public static final Block KHAZAD_STEEL_BLOCK = registerMiscBlock("khazad_steel_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(7f).requiresCorrectToolForDrops()),true);
    public static final Block EDHEL_STEEL_BLOCK = registerMiscBlock("edhel_steel_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(6f).requiresCorrectToolForDrops()),true);
    public static final Block BURZUM_STEEL_BLOCK = registerMiscBlock("burzum_steel_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(6f).requiresCorrectToolForDrops()),true);

    public static final Block CUT_LEAD = registerMiscBlock("cut_lead",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_COPPER).sound(SoundType.COPPER)),true);
    public static final Block CUT_LEAD_STAIRS = registerMiscBlock("cut_lead_stairs",
            new StairBlock(REED_BLOCK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_COPPER).sound(SoundType.COPPER)),true);
    public static final Block CUT_LEAD_SLAB = registerMiscBlock("cut_lead_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_COPPER).sound(SoundType.COPPER)),true);
    public static final Block CUT_LEAD_VERTICAL_SLAB = registerMiscBlock("cut_lead_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.CUT_LEAD_SLAB)),true);

    public static final Block CUT_SILVER = registerMiscBlock("cut_silver",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_COPPER).sound(SoundType.COPPER)),true);
    public static final Block CUT_SILVER_STAIRS = registerMiscBlock("cut_silver_stairs",
            new StairBlock(REED_BLOCK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_COPPER).sound(SoundType.COPPER)),true);
    public static final Block CUT_SILVER_SLAB = registerMiscBlock("cut_silver_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_COPPER).sound(SoundType.COPPER)),true);
    public static final Block CUT_SILVER_VERTICAL_SLAB = registerMiscBlock("cut_silver_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.CUT_SILVER_SLAB)),true);

    public static final Block QUARTZ_BLOCK = registerMiscBlock("quartz_block",
            new AmethystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)), true);
    public static final Block QUARTZ_CLUSTER = registerMiscBlock("quartz_cluster",
            new AmethystClusterBlock(7,3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER)), false);
    public static final Block LARGE_QUARTZ_BUD = registerMiscBlock("large_quartz_bud",
            new AmethystClusterBlock(5,3, BlockBehaviour.Properties.ofFullCopy(ModBlocks.QUARTZ_CLUSTER).sound(SoundType.LARGE_AMETHYST_BUD)), false);
    public static final Block MEDIUM_QUARTZ_BUD = registerMiscBlock("medium_quartz_bud",
            new AmethystClusterBlock(4,3, BlockBehaviour.Properties.ofFullCopy(ModBlocks.QUARTZ_CLUSTER).sound(SoundType.MEDIUM_AMETHYST_BUD)), false);
    public static final Block SMALL_QUARTZ_BUD = registerMiscBlock("small_quartz_bud",
            new AmethystClusterBlock(3,4, BlockBehaviour.Properties.ofFullCopy(ModBlocks.QUARTZ_CLUSTER).sound(SoundType.SMALL_AMETHYST_BUD)), false);
    public static final Block BUDDING_QUARTZ = registerMiscBlock("budding_quartz",
            new CustomBuddingGemBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BUDDING_AMETHYST), List.of(SMALL_QUARTZ_BUD,MEDIUM_QUARTZ_BUD,LARGE_QUARTZ_BUD, QUARTZ_CLUSTER)), false);

    public static final Block RED_AGATE_BLOCK = registerMiscBlock("red_agate_block",
            new AmethystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)), true);
    public static final Block RED_AGATE_CLUSTER = registerMiscBlock("red_agate_cluster",
            new AmethystClusterBlock(7,3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER)), false);
    public static final Block LARGE_RED_AGATE_BUD = registerMiscBlock("large_red_agate_bud",
            new AmethystClusterBlock(5,3, BlockBehaviour.Properties.ofFullCopy(ModBlocks.RED_AGATE_CLUSTER).sound(SoundType.LARGE_AMETHYST_BUD)), false);
    public static final Block MEDIUM_RED_AGATE_BUD = registerMiscBlock("medium_red_agate_bud",
            new AmethystClusterBlock(4,3, BlockBehaviour.Properties.ofFullCopy(ModBlocks.RED_AGATE_CLUSTER).sound(SoundType.MEDIUM_AMETHYST_BUD)), false);
    public static final Block SMALL_RED_AGATE_BUD = registerMiscBlock("small_red_agate_bud",
            new AmethystClusterBlock(3,4, BlockBehaviour.Properties.ofFullCopy(ModBlocks.RED_AGATE_CLUSTER).sound(SoundType.SMALL_AMETHYST_BUD)), false);
    public static final Block BUDDING_RED_AGATE = registerMiscBlock("budding_red_agate",
            new CustomBuddingGemBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BUDDING_AMETHYST), List.of(SMALL_RED_AGATE_BUD,MEDIUM_RED_AGATE_BUD,LARGE_RED_AGATE_BUD, RED_AGATE_CLUSTER)), false);
    
    public static final Block CITRINE_BLOCK = registerMiscBlock("citrine_block",
            new AmethystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)), true);
    public static final Block CITRINE_CLUSTER = registerMiscBlock("citrine_cluster",
            new AmethystClusterBlock(7,3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER)), false);
    public static final Block LARGE_CITRINE_BUD = registerMiscBlock("large_citrine_bud",
            new AmethystClusterBlock(5,3, BlockBehaviour.Properties.ofFullCopy(ModBlocks.CITRINE_CLUSTER).sound(SoundType.LARGE_AMETHYST_BUD)), false);
    public static final Block MEDIUM_CITRINE_BUD = registerMiscBlock("medium_citrine_bud",
            new AmethystClusterBlock(4,3, BlockBehaviour.Properties.ofFullCopy(ModBlocks.CITRINE_CLUSTER).sound(SoundType.MEDIUM_AMETHYST_BUD)), false);
    public static final Block SMALL_CITRINE_BUD = registerMiscBlock("small_citrine_bud",
            new AmethystClusterBlock(3,4, BlockBehaviour.Properties.ofFullCopy(ModBlocks.CITRINE_CLUSTER).sound(SoundType.SMALL_AMETHYST_BUD)), false);
    public static final Block BUDDING_CITRINE = registerMiscBlock("budding_citrine",
            new CustomBuddingGemBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BUDDING_AMETHYST), List.of(SMALL_CITRINE_BUD,MEDIUM_CITRINE_BUD,LARGE_CITRINE_BUD, CITRINE_CLUSTER)), false);

    public static final Block GLOWSTONE_BLOCK = registerMiscBlock("glowstone_block",
            new AmethystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).lightLevel(state -> 12)), true);
    public static final Block GLOWSTONE_CLUSTER = registerMiscBlock("glowstone_cluster",
            new AmethystClusterBlock(7,3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).lightLevel(state -> 10)), false);
    public static final Block LARGE_GLOWSTONE_BUD = registerMiscBlock("large_glowstone_bud",
            new AmethystClusterBlock(5,3, BlockBehaviour.Properties.ofFullCopy(ModBlocks.GLOWSTONE_CLUSTER).sound(SoundType.LARGE_AMETHYST_BUD).lightLevel(state -> 9)), false);
    public static final Block MEDIUM_GLOWSTONE_BUD = registerMiscBlock("medium_glowstone_bud",
            new AmethystClusterBlock(4,3, BlockBehaviour.Properties.ofFullCopy(ModBlocks.GLOWSTONE_CLUSTER).sound(SoundType.MEDIUM_AMETHYST_BUD).lightLevel(state -> 7)), false);
    public static final Block SMALL_GLOWSTONE_BUD = registerMiscBlock("small_glowstone_bud",
            new AmethystClusterBlock(3,4, BlockBehaviour.Properties.ofFullCopy(ModBlocks.GLOWSTONE_CLUSTER).sound(SoundType.SMALL_AMETHYST_BUD).lightLevel(state -> 5)), false);
    public static final Block BUDDING_GLOWSTONE = registerMiscBlock("budding_glowstone",
            new CustomBuddingGemBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BUDDING_AMETHYST).lightLevel(state -> 12), List.of(SMALL_GLOWSTONE_BUD,MEDIUM_GLOWSTONE_BUD,LARGE_GLOWSTONE_BUD, GLOWSTONE_CLUSTER)), false);

    public static final Block STONE_TRAPDOOR = registerStoneBlock("stone_trapdoor",
            new TrapDoorBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(StoneBlockSets.STONE_HARDNESS, StoneBlockSets.STONE_BLAST_RESISTANCE).sound(SoundType.STONE).noOcclusion()), true);
    public static final Block GRANITE_TRAPDOOR = registerStoneBlock("granite_trapdoor",
            new TrapDoorBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(StoneBlockSets.STONE_HARDNESS, StoneBlockSets.STONE_BLAST_RESISTANCE).sound(SoundType.STONE).noOcclusion()), true);
    public static final Block DIORITE_TRAPDOOR = registerStoneBlock("diorite_trapdoor",
            new TrapDoorBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE).strength(StoneBlockSets.STONE_HARDNESS, StoneBlockSets.STONE_BLAST_RESISTANCE).sound(SoundType.STONE).noOcclusion()), true);
    public static final Block ANDESITE_TRAPDOOR = registerStoneBlock("andesite_trapdoor",
            new TrapDoorBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE).strength(StoneBlockSets.STONE_HARDNESS, StoneBlockSets.STONE_BLAST_RESISTANCE).sound(SoundType.STONE).noOcclusion()), true);
    public static final Block CALCITE_TRAPDOOR = registerStoneBlock("calcite_trapdoor",
            new TrapDoorBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(StoneBlockSets.STONE_HARDNESS, StoneBlockSets.STONE_BLAST_RESISTANCE).sound(SoundType.STONE).noOcclusion()), true);
    public static final Block DEEPSLATE_TRAPDOOR = registerStoneBlock("deepslate_trapdoor",
            new TrapDoorBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(StoneBlockSets.DEEPSLATE_HARDNESS, StoneBlockSets.DEEPSLATE_BLAST_RESISTANCE).sound(SoundType.STONE).noOcclusion()), true);
    public static final Block TUFF_TRAPDOOR = registerStoneBlock("tuff_trapdoor",
            new TrapDoorBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF).strength(StoneBlockSets.STONE_HARDNESS, StoneBlockSets.STONE_BLAST_RESISTANCE).sound(SoundType.STONE).noOcclusion()), true);
    public static final Block BASALT_TRAPDOOR = registerStoneBlock("basalt_trapdoor",
            new TrapDoorBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(StoneBlockSets.STONE_HARDNESS, StoneBlockSets.STONE_BLAST_RESISTANCE).sound(SoundType.STONE).noOcclusion()), true);
    public static final Block BLACKSTONE_TRAPDOOR = registerStoneBlock("blackstone_trapdoor",
            new TrapDoorBlock(BlockSetType.POLISHED_BLACKSTONE, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).strength(StoneBlockSets.STONE_HARDNESS, StoneBlockSets.STONE_BLAST_RESISTANCE).sound(SoundType.STONE).noOcclusion()), true);

    public static final Block STONE_ROCKS = registerStoneBlock("stone_rocks",
            new RocksBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(StoneBlockSets.STONE_HARDNESS, StoneBlockSets.STONE_BLAST_RESISTANCE).sound(SoundType.STONE).noOcclusion()), true);
    public static final Block GRANITE_ROCKS = registerStoneBlock("granite_rocks",
            new RocksBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(StoneBlockSets.STONE_HARDNESS, StoneBlockSets.STONE_BLAST_RESISTANCE).sound(SoundType.STONE).noOcclusion()), true);
    public static final Block DIORITE_ROCKS = registerStoneBlock("diorite_rocks",
            new RocksBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE).strength(StoneBlockSets.STONE_HARDNESS, StoneBlockSets.STONE_BLAST_RESISTANCE).sound(SoundType.STONE).noOcclusion()), true);
    public static final Block ANDESITE_ROCKS = registerStoneBlock("andesite_rocks",
            new RocksBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE).strength(StoneBlockSets.STONE_HARDNESS, StoneBlockSets.STONE_BLAST_RESISTANCE).sound(SoundType.STONE).noOcclusion()), true);
    public static final Block CALCITE_ROCKS = registerStoneBlock("calcite_rocks",
            new RocksBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(StoneBlockSets.STONE_HARDNESS, StoneBlockSets.STONE_BLAST_RESISTANCE).sound(SoundType.STONE).noOcclusion()), true);
    public static final Block DEEPSLATE_ROCKS = registerStoneBlock("deepslate_rocks",
            new RocksBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(StoneBlockSets.DEEPSLATE_HARDNESS, StoneBlockSets.DEEPSLATE_BLAST_RESISTANCE).sound(SoundType.STONE).noOcclusion()), true);
    public static final Block TUFF_ROCKS = registerStoneBlock("tuff_rocks",
            new RocksBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF).strength(StoneBlockSets.STONE_HARDNESS, StoneBlockSets.STONE_BLAST_RESISTANCE).sound(SoundType.STONE).noOcclusion()), true);
    public static final Block BASALT_ROCKS = registerStoneBlock("basalt_rocks",
            new RocksBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(StoneBlockSets.STONE_HARDNESS, StoneBlockSets.STONE_BLAST_RESISTANCE).sound(SoundType.STONE).noOcclusion()), true);
    public static final Block BLACKSTONE_ROCKS = registerStoneBlock("blackstone_rocks",
            new RocksBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).strength(StoneBlockSets.STONE_HARDNESS, StoneBlockSets.STONE_BLAST_RESISTANCE).sound(SoundType.STONE).noOcclusion()), true);

    public static final Block NET = registerMiscBlock("net",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).sound(SoundType.WOOL)), true);

    public static final Block COPPER_BARS = registerMiscBlock("copper_bars",
            new OxidizablePaneBlock(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_COPPER_SLAB)), true);
    public static final Block EXPOSED_COPPER_BARS = registerMiscBlock("exposed_copper_bars",
            new OxidizablePaneBlock(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.ofFullCopy(Blocks.EXPOSED_CUT_COPPER_SLAB)), true);
    public static final Block WEATHERED_COPPER_BARS = registerMiscBlock("weathered_copper_bars",
            new OxidizablePaneBlock(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.ofFullCopy(Blocks.WEATHERED_CUT_COPPER_SLAB)), true);
    public static final Block OXIDIZED_COPPER_BARS = registerMiscBlock("oxidized_copper_bars",
            new OxidizablePaneBlock(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.ofFullCopy(Blocks.OXIDIZED_CUT_COPPER_SLAB)), true);
    public static final Block WAXED_COPPER_BARS = registerMiscBlock("waxed_copper_bars",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_CUT_COPPER_SLAB)), true);
    public static final Block WAXED_EXPOSED_COPPER_BARS = registerMiscBlock("waxed_exposed_copper_bars",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB)), true);
    public static final Block WAXED_WEATHERED_COPPER_BARS = registerMiscBlock("waxed_weathered_copper_bars",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB)), true);
    public static final Block WAXED_OXIDIZED_COPPER_BARS = registerMiscBlock("waxed_oxidized_copper_bars",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB)), true);

    public static final Block TREATED_STEEL_BARS = registerMiscBlock("treated_steel_bars",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS)), true);

    public static final Block SILVER_BARS = registerMiscBlock("silver_bars",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS).sound(SoundType.METAL)), true);

    public static final Block GILDED_BARS = registerMiscBlock("gilded_bars",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS).sound(SoundType.COPPER)), true);

    public static final Block TREATED_STEEL_DOOR = registerMiscBlock("treated_steel_door",
            new DoorBlock(BlockSetType.COPPER, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_DOOR)), true);
    public static final Block TREATED_STEEL_TRAPDOOR = registerMiscBlock("treated_steel_trapdoor",
            new TrapDoorBlock(BlockSetType.COPPER, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_TRAPDOOR)), true);
    //endregion

    public static final Block RIVER_SAND = registerMiscBlock("river_sand",
            new ColoredFallingBlock(new ColorRGBA(-8356741), BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).sound(SoundType.SAND)), true);
    public static final Block BLACK_SAND = registerMiscBlock("black_sand",
            new ColoredFallingBlock(new ColorRGBA(-8356741), BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).sound(SoundType.SAND)), true);
    public static final Block WHITE_SAND = registerMiscBlock("white_sand",
            new ColoredFallingBlock(new ColorRGBA(14406560), BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).sound(SoundType.SAND)), true);

    public static final Block STONE_MYCELIUM = registerMiscBlock("stone_mycelium",
            new StoneMyceliumBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).randomTicks()), false);

    public static final Block ASH_BLOCK = registerMiscBlock("ash_block",
            new ColoredFallingBlock(new ColorRGBA(-8356741), BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).strength(DIRT_STRENGTH).sound(SoundType.SAND)), true);

    //region VANILLA SLABS
    public static final Block DIRT_SLAB = registerMiscBlock("dirt_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)), true);
    public static final Block COARSE_DIRT_SLAB = registerMiscBlock("coarse_dirt_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COARSE_DIRT)), true);
    public static final Block ROOTED_DIRT_SLAB = registerMiscBlock("rooted_dirt_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ROOTED_DIRT)), true);
    public static final Block MUD_SLAB = registerMiscBlock("mud_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MUD)), true);
    public static final Block MOSS_SLAB = registerMiscBlock("moss_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_BLOCK)), true);

    public static final Block PACKED_MUD_SLAB = registerStoneBlock("packed_mud_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)), true);
    public static final Block PACKED_MUD_VERTICAL_SLAB = registerStoneBlock("packed_mud_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)), true);
    public static final Block PACKED_MUD_STAIRS = registerStoneBlock("packed_mud_stairs",
            new StairBlock(Blocks.PACKED_MUD.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)), true);
    public static final Block PACKED_MUD_WALL = registerStoneBlock("packed_mud_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)), true);

    public static final Block CALCITE_SLAB = registerStoneBlock("calcite_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE)), true);
    public static final Block CALCITE_VERTICAL_SLAB = registerStoneBlock("calcite_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE)), true);
    public static final Block CALCITE_STAIRS = registerStoneBlock("calcite_stairs",
            new StairBlock(Blocks.CALCITE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE)), true);
    public static final Block CALCITE_WALL = registerStoneBlock("calcite_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE)), true);

    public static final Block SMOOTH_BASALT_SLAB = registerStoneBlock("smooth_basalt_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_BASALT)), true);
    public static final Block SMOOTH_BASALT_VERTICAL_SLAB = registerStoneBlock("smooth_basalt_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_BASALT)), true);
    public static final Block SMOOTH_BASALT_STAIRS = registerStoneBlock("smooth_basalt_stairs",
            new StairBlock(Blocks.SMOOTH_BASALT.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_BASALT)), true);
    public static final Block SMOOTH_BASALT_WALL = registerStoneBlock("smooth_basalt_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_BASALT)), true);

    public static final Block CUT_COPPER_WALL = registerMiscBlock("cut_copper_wall",
            new OxidizableWallBlock(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_COPPER_SLAB)), true);
    public static final Block EXPOSED_CUT_COPPER_WALL = registerMiscBlock("exposed_cut_copper_wall",
            new OxidizableWallBlock(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.ofFullCopy(Blocks.EXPOSED_CUT_COPPER_SLAB)), true);
    public static final Block WEATHERED_CUT_COPPER_WALL = registerMiscBlock("weathered_cut_copper_wall",
            new OxidizableWallBlock(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.ofFullCopy(Blocks.WEATHERED_CUT_COPPER_SLAB)), true);
    public static final Block OXIDIZED_CUT_COPPER_WALL = registerMiscBlock("oxidized_cut_copper_wall",
            new OxidizableWallBlock(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.ofFullCopy(Blocks.OXIDIZED_CUT_COPPER_SLAB)), true);
    public static final Block WAXED_CUT_COPPER_WALL = registerMiscBlock("waxed_cut_copper_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_CUT_COPPER_SLAB)), true);
    public static final Block WAXED_EXPOSED_CUT_COPPER_WALL = registerMiscBlock("waxed_exposed_cut_copper_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB)), true);
    public static final Block WAXED_WEATHERED_CUT_COPPER_WALL = registerMiscBlock("waxed_weathered_cut_copper_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB)), true);
    public static final Block WAXED_OXIDIZED_CUT_COPPER_WALL = registerMiscBlock("waxed_oxidized_cut_copper_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB)), true);

    public static final Block BLACK_WOOL_SLAB = registerMiscBlock("black_wool_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block BLUE_WOOL_SLAB = registerMiscBlock("blue_wool_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block BROWN_WOOL_SLAB = registerMiscBlock("brown_wool_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block CYAN_WOOL_SLAB = registerMiscBlock("cyan_wool_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block GRAY_WOOL_SLAB = registerMiscBlock("gray_wool_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block GREEN_WOOL_SLAB = registerMiscBlock("green_wool_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block LIGHT_BLUE_WOOL_SLAB = registerMiscBlock("light_blue_wool_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block LIGHT_GRAY_WOOL_SLAB = registerMiscBlock("light_gray_wool_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block LIME_WOOL_SLAB = registerMiscBlock("lime_wool_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block MAGENTA_WOOL_SLAB = registerMiscBlock("magenta_wool_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block ORANGE_WOOL_SLAB = registerMiscBlock("orange_wool_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block PINK_WOOL_SLAB = registerMiscBlock("pink_wool_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block PURPLE_WOOL_SLAB = registerMiscBlock("purple_wool_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block RED_WOOL_SLAB = registerMiscBlock("red_wool_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block WHITE_WOOL_SLAB = registerMiscBlock("white_wool_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block YELLOW_WOOL_SLAB = registerMiscBlock("yellow_wool_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    //endregion

    //region VANILLA VERTICAL SLABS
    public static final Block OAK_WOOD_SLAB = registerWoodBlock("oak_wood_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB).ignitedByLava()), true);
    public static final Block SPRUCE_WOOD_SLAB = registerWoodBlock("spruce_wood_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SLAB).ignitedByLava()), true);
    public static final Block BIRCH_WOOD_SLAB = registerWoodBlock("birch_wood_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_SLAB).ignitedByLava()), true);
    public static final Block JUNGLE_WOOD_SLAB = registerWoodBlock("jungle_wood_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_SLAB).ignitedByLava()), true);
    public static final Block ACACIA_WOOD_SLAB = registerWoodBlock("acacia_wood_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SLAB).ignitedByLava()), true);
    public static final Block DARK_OAK_WOOD_SLAB = registerWoodBlock("dark_oak_wood_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_SLAB).ignitedByLava()), true);
    public static final Block MANGROVE_WOOD_SLAB = registerWoodBlock("mangrove_wood_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_SLAB).ignitedByLava()), true);
    public static final Block CHERRY_WOOD_SLAB = registerWoodBlock("cherry_wood_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SLAB).ignitedByLava()), true);

    public static final Block OAK_WOOD_VERTICAL_SLAB = registerWoodBlock("oak_wood_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB).ignitedByLava()), true);
    public static final Block SPRUCE_WOOD_VERTICAL_SLAB = registerWoodBlock("spruce_wood_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SLAB).ignitedByLava()), true);
    public static final Block BIRCH_WOOD_VERTICAL_SLAB = registerWoodBlock("birch_wood_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_SLAB).ignitedByLava()), true);
    public static final Block JUNGLE_WOOD_VERTICAL_SLAB = registerWoodBlock("jungle_wood_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_SLAB).ignitedByLava()), true);
    public static final Block ACACIA_WOOD_VERTICAL_SLAB = registerWoodBlock("acacia_wood_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SLAB).ignitedByLava()), true);
    public static final Block DARK_OAK_WOOD_VERTICAL_SLAB = registerWoodBlock("dark_oak_wood_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_SLAB).ignitedByLava()), true);
    public static final Block MANGROVE_WOOD_VERTICAL_SLAB = registerWoodBlock("mangrove_wood_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_SLAB).ignitedByLava()), true);
    public static final Block CHERRY_WOOD_VERTICAL_SLAB = registerWoodBlock("cherry_wood_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SLAB).ignitedByLava()), true);

    public static final Block OAK_WOOD_STAIRS = registerWoodBlock("oak_wood_stairs",
            new StairBlock(Blocks.OAK_WOOD.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS).ignitedByLava()), true);
    public static final Block SPRUCE_WOOD_STAIRS = registerWoodBlock("spruce_wood_stairs",
            new StairBlock(Blocks.OAK_WOOD.defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_STAIRS).ignitedByLava()), true);
    public static final Block BIRCH_WOOD_STAIRS = registerWoodBlock("birch_wood_stairs",
            new StairBlock(Blocks.BIRCH_WOOD.defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_STAIRS).ignitedByLava()), true);
    public static final Block JUNGLE_WOOD_STAIRS = registerWoodBlock("jungle_wood_stairs",
            new StairBlock(Blocks.JUNGLE_WOOD.defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_STAIRS).ignitedByLava()), true);
    public static final Block ACACIA_WOOD_STAIRS = registerWoodBlock("acacia_wood_stairs",
            new StairBlock(Blocks.ACACIA_WOOD.defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_STAIRS).ignitedByLava()), true);
    public static final Block DARK_OAK_WOOD_STAIRS = registerWoodBlock("dark_oak_wood_stairs",
            new StairBlock(Blocks.DARK_OAK_WOOD.defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_STAIRS).ignitedByLava()), true);
    public static final Block MANGROVE_WOOD_STAIRS = registerWoodBlock("mangrove_wood_stairs",
            new StairBlock(Blocks.MANGROVE_WOOD.defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_STAIRS).ignitedByLava()), true);
    public static final Block CHERRY_WOOD_STAIRS = registerWoodBlock("cherry_wood_stairs",
            new StairBlock(Blocks.CHERRY_WOOD.defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_STAIRS).ignitedByLava()), true);

    public static final Block OAK_WOOD_WALL = registerWoodBlock("oak_wood_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).ignitedByLava()), true);
    public static final Block SPRUCE_WOOD_WALL = registerWoodBlock("spruce_wood_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WOOD).ignitedByLava()), true);
    public static final Block BIRCH_WOOD_WALL = registerWoodBlock("birch_wood_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_WOOD).ignitedByLava()), true);
    public static final Block JUNGLE_WOOD_WALL = registerWoodBlock("jungle_wood_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_WOOD).ignitedByLava()), true);
    public static final Block ACACIA_WOOD_WALL = registerWoodBlock("acacia_wood_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_WOOD).ignitedByLava()), true);
    public static final Block DARK_OAK_WOOD_WALL = registerWoodBlock("dark_oak_wood_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_WOOD).ignitedByLava()), true);
    public static final Block MANGROVE_WOOD_WALL = registerWoodBlock("mangrove_wood_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_WOOD).ignitedByLava()), true);
    public static final Block CHERRY_WOOD_WALL = registerWoodBlock("cherry_wood_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_WOOD).ignitedByLava()), true);

    public static final Block OAK_WOOD_FENCE = registerWoodBlock("oak_wood_fence",
            new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).ignitedByLava()), true);
    public static final Block SPRUCE_WOOD_FENCE = registerWoodBlock("spruce_wood_fence",
            new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WOOD).ignitedByLava()), true);
    public static final Block BIRCH_WOOD_FENCE = registerWoodBlock("birch_wood_fence",
            new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_WOOD).ignitedByLava()), true);
    public static final Block JUNGLE_WOOD_FENCE = registerWoodBlock("jungle_wood_fence",
            new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_WOOD).ignitedByLava()), true);
    public static final Block ACACIA_WOOD_FENCE = registerWoodBlock("acacia_wood_fence",
            new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_WOOD).ignitedByLava()), true);
    public static final Block DARK_OAK_WOOD_FENCE = registerWoodBlock("dark_oak_wood_fence",
            new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_WOOD).ignitedByLava()), true);
    public static final Block MANGROVE_WOOD_FENCE = registerWoodBlock("mangrove_wood_fence",
            new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_WOOD).ignitedByLava()), true);
    public static final Block CHERRY_WOOD_FENCE = registerWoodBlock("cherry_wood_fence",
            new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_WOOD).ignitedByLava()), true);

    public static final Block STRIPPED_OAK_WOOD_SLAB = registerWoodBlock("stripped_oak_wood_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB).ignitedByLava()), true);
    public static final Block STRIPPED_SPRUCE_WOOD_SLAB = registerWoodBlock("stripped_spruce_wood_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SLAB).ignitedByLava()), true);
    public static final Block STRIPPED_BIRCH_WOOD_SLAB = registerWoodBlock("stripped_birch_wood_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_SLAB).ignitedByLava()), true);
    public static final Block STRIPPED_JUNGLE_WOOD_SLAB = registerWoodBlock("stripped_jungle_wood_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_SLAB).ignitedByLava()), true);
    public static final Block STRIPPED_ACACIA_WOOD_SLAB = registerWoodBlock("stripped_acacia_wood_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SLAB).ignitedByLava()), true);
    public static final Block STRIPPED_DARK_OAK_WOOD_SLAB = registerWoodBlock("stripped_dark_oak_wood_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_SLAB).ignitedByLava()), true);
    public static final Block STRIPPED_MANGROVE_WOOD_SLAB = registerWoodBlock("stripped_mangrove_wood_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_SLAB).ignitedByLava()), true);
    public static final Block STRIPPED_CHERRY_WOOD_SLAB = registerWoodBlock("stripped_cherry_wood_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SLAB).ignitedByLava()), true);

    public static final Block STRIPPED_OAK_WOOD_VERTICAL_SLAB = registerWoodBlock("stripped_oak_wood_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB).ignitedByLava()), true);
    public static final Block STRIPPED_SPRUCE_WOOD_VERTICAL_SLAB = registerWoodBlock("stripped_spruce_wood_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SLAB)), true);
    public static final Block STRIPPED_BIRCH_WOOD_VERTICAL_SLAB = registerWoodBlock("stripped_birch_wood_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_SLAB).ignitedByLava()), true);
    public static final Block STRIPPED_JUNGLE_WOOD_VERTICAL_SLAB = registerWoodBlock("stripped_jungle_wood_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_SLAB).ignitedByLava()), true);
    public static final Block STRIPPED_ACACIA_WOOD_VERTICAL_SLAB = registerWoodBlock("stripped_acacia_wood_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SLAB).ignitedByLava()), true);
    public static final Block STRIPPED_DARK_OAK_WOOD_VERTICAL_SLAB = registerWoodBlock("stripped_dark_oak_wood_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_SLAB).ignitedByLava()), true);
    public static final Block STRIPPED_MANGROVE_WOOD_VERTICAL_SLAB = registerWoodBlock("stripped_mangrove_wood_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_SLAB).ignitedByLava()), true);
    public static final Block STRIPPED_CHERRY_WOOD_VERTICAL_SLAB = registerWoodBlock("stripped_cherry_wood_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SLAB).ignitedByLava()), true);

    public static final Block STRIPPED_OAK_WOOD_STAIRS = registerWoodBlock("stripped_oak_wood_stairs",
            new StairBlock(Blocks.STRIPPED_OAK_WOOD.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS).ignitedByLava()), true);
    public static final Block STRIPPED_SPRUCE_WOOD_STAIRS = registerWoodBlock("stripped_spruce_wood_stairs",
            new StairBlock(Blocks.STRIPPED_OAK_WOOD.defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_STAIRS).ignitedByLava()), true);
    public static final Block STRIPPED_BIRCH_WOOD_STAIRS = registerWoodBlock("stripped_birch_wood_stairs",
            new StairBlock(Blocks.STRIPPED_BIRCH_WOOD.defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_STAIRS).ignitedByLava()), true);
    public static final Block STRIPPED_JUNGLE_WOOD_STAIRS = registerWoodBlock("stripped_jungle_wood_stairs",
            new StairBlock(Blocks.STRIPPED_JUNGLE_WOOD.defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_STAIRS).ignitedByLava()), true);
    public static final Block STRIPPED_ACACIA_WOOD_STAIRS = registerWoodBlock("stripped_acacia_wood_stairs",
            new StairBlock(Blocks.STRIPPED_ACACIA_WOOD.defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_STAIRS).ignitedByLava()), true);
    public static final Block STRIPPED_DARK_OAK_WOOD_STAIRS = registerWoodBlock("stripped_dark_oak_wood_stairs",
            new StairBlock(Blocks.STRIPPED_DARK_OAK_WOOD.defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_STAIRS).ignitedByLava()), true);
    public static final Block STRIPPED_MANGROVE_WOOD_STAIRS = registerWoodBlock("stripped_mangrove_wood_stairs",
            new StairBlock(Blocks.STRIPPED_MANGROVE_WOOD.defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_STAIRS).ignitedByLava()), true);
    public static final Block STRIPPED_CHERRY_WOOD_STAIRS = registerWoodBlock("stripped_cherry_wood_stairs",
            new StairBlock(Blocks.STRIPPED_CHERRY_WOOD.defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_STAIRS).ignitedByLava()), true);

    public static final Block STRIPPED_OAK_WOOD_WALL = registerWoodBlock("stripped_oak_wood_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).ignitedByLava()), true);
    public static final Block STRIPPED_SPRUCE_WOOD_WALL = registerWoodBlock("stripped_spruce_wood_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WOOD).ignitedByLava()), true);
    public static final Block STRIPPED_BIRCH_WOOD_WALL = registerWoodBlock("stripped_birch_wood_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_WOOD).ignitedByLava()), true);
    public static final Block STRIPPED_JUNGLE_WOOD_WALL = registerWoodBlock("stripped_jungle_wood_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_WOOD).ignitedByLava()), true);
    public static final Block STRIPPED_ACACIA_WOOD_WALL = registerWoodBlock("stripped_acacia_wood_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_WOOD).ignitedByLava()), true);
    public static final Block STRIPPED_DARK_OAK_WOOD_WALL = registerWoodBlock("stripped_dark_oak_wood_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_WOOD).ignitedByLava()), true);
    public static final Block STRIPPED_MANGROVE_WOOD_WALL = registerWoodBlock("stripped_mangrove_wood_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_WOOD).ignitedByLava()), true);
    public static final Block STRIPPED_CHERRY_WOOD_WALL = registerWoodBlock("stripped_cherry_wood_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_WOOD).ignitedByLava()), true);

    public static final Block STRIPPED_OAK_WOOD_FENCE = registerWoodBlock("stripped_oak_wood_fence",
            new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).ignitedByLava()), true);
    public static final Block STRIPPED_SPRUCE_WOOD_FENCE = registerWoodBlock("stripped_spruce_wood_fence",
            new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WOOD).ignitedByLava()), true);
    public static final Block STRIPPED_BIRCH_WOOD_FENCE = registerWoodBlock("stripped_birch_wood_fence",
            new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_WOOD).ignitedByLava()), true);
    public static final Block STRIPPED_JUNGLE_WOOD_FENCE = registerWoodBlock("stripped_jungle_wood_fence",
            new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_WOOD).ignitedByLava()), true);
    public static final Block STRIPPED_ACACIA_WOOD_FENCE = registerWoodBlock("stripped_acacia_wood_fence",
            new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_WOOD).ignitedByLava()), true);
    public static final Block STRIPPED_DARK_OAK_WOOD_FENCE = registerWoodBlock("stripped_dark_oak_wood_fence",
            new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_WOOD).ignitedByLava()), true);
    public static final Block STRIPPED_MANGROVE_WOOD_FENCE = registerWoodBlock("stripped_mangrove_wood_fence",
            new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_WOOD).ignitedByLava()), true);
    public static final Block STRIPPED_CHERRY_WOOD_FENCE = registerWoodBlock("stripped_cherry_wood_fence",
            new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_WOOD).ignitedByLava()), true);
    
    public static final Block OAK_VERTICAL_SLAB = registerWoodBlock("oak_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB).ignitedByLava()), true);
    public static final Block SPRUCE_VERTICAL_SLAB = registerWoodBlock("spruce_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SLAB).ignitedByLava()), true);
    public static final Block BIRCH_VERTICAL_SLAB = registerWoodBlock("birch_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_SLAB).ignitedByLava()), true);
    public static final Block JUNGLE_VERTICAL_SLAB = registerWoodBlock("jungle_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_SLAB).ignitedByLava()), true);
    public static final Block ACACIA_VERTICAL_SLAB = registerWoodBlock("acacia_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SLAB).ignitedByLava()), true);
    public static final Block DARK_OAK_VERTICAL_SLAB = registerWoodBlock("dark_oak_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_SLAB).ignitedByLava()), true);
    public static final Block MANGROVE_VERTICAL_SLAB = registerWoodBlock("mangrove_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_SLAB).ignitedByLava()), true);
    public static final Block CHERRY_VERTICAL_SLAB = registerWoodBlock("cherry_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SLAB).ignitedByLava()), true);
    public static final Block BAMBOO_VERTICAL_SLAB = registerWoodBlock("bamboo_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_SLAB).ignitedByLava()), true);
    public static final Block CRIMSON_VERTICAL_SLAB = registerWoodBlock("crimson_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SLAB).ignitedByLava()), true);
    public static final Block WARPED_VERTICAL_SLAB = registerWoodBlock("warped_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_SLAB).ignitedByLava()), true);

    public static final Block STONE_VERTICAL_SLAB = registerStoneBlock("stone_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)), true);
    public static final Block COBBLESTONE_VERTICAL_SLAB = registerStoneBlock("cobblestone_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_SLAB)), true);
    public static final Block MOSSY_COBBLESTONE_VERTICAL_SLAB = registerStoneBlock("mossy_cobblestone_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE_SLAB)), true);
    public static final Block SMOOTH_STONE_VERTICAL_SLAB = registerStoneBlock("smooth_stone_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_STONE_SLAB)), true);
    public static final Block STONE_BRICK_VERTICAL_SLAB = registerStoneBlock("stone_brick_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB)), true);
    public static final Block MOSSY_STONE_BRICK_VERTICAL_SLAB = registerStoneBlock("mossy_stone_brick_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICK_SLAB)), true);
    public static final Block GRANITE_VERTICAL_SLAB = registerStoneBlock("granite_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE_SLAB)), true);
    public static final Block POLISHED_GRANITE_VERTICAL_SLAB = registerStoneBlock("polished_granite_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_GRANITE_SLAB)), true);
    public static final Block DIORITE_VERTICAL_SLAB = registerStoneBlock("diorite_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE_SLAB)), true);
    public static final Block POLISHED_DIORITE_VERTICAL_SLAB = registerStoneBlock("polished_diorite_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DIORITE_SLAB)), true);
    public static final Block ANDESITE_VERTICAL_SLAB = registerStoneBlock("andesite_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE_SLAB)), true);
    public static final Block POLISHED_ANDESITE_VERTICAL_SLAB = registerStoneBlock("polished_andesite_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_ANDESITE_SLAB)), true);
    public static final Block COBBLED_DEEPSLATE_VERTICAL_SLAB = registerStoneBlock("cobbled_deepslate_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE_SLAB)), true);
    public static final Block POLISHED_DEEPSLATE_VERTICAL_SLAB = registerStoneBlock("polished_deepslate_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DEEPSLATE_SLAB)), true);
    public static final Block DEEPSLATE_BRICK_VERTICAL_SLAB = registerStoneBlock("deepslate_brick_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICK_SLAB)), true);
    public static final Block DEEPSLATE_TILE_VERTICAL_SLAB = registerStoneBlock("deepslate_tile_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_TILE_SLAB)), true);
    public static final Block TUFF_VERTICAL_SLAB = registerStoneBlock("tuff_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF)), true);
    public static final Block POLISHED_TUFF_VERTICAL_SLAB = registerStoneBlock("polished_tuff_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF)), true);
    public static final Block TUFF_BRICK_VERTICAL_SLAB = registerStoneBlock("tuff_brick_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF)), true);
    public static final Block BRICK_VERTICAL_SLAB = registerStoneBlock("brick_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB)), true);
    public static final Block MUD_BRICK_VERTICAL_SLAB = registerStoneBlock("mud_brick_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MUD_BRICK_SLAB)), true);
    public static final Block SANDSTONE_VERTICAL_SLAB = registerStoneBlock("sandstone_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_SLAB)), true);
    public static final Block SMOOTH_SANDSTONE_VERTICAL_SLAB = registerStoneBlock("smooth_sandstone_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_SANDSTONE_SLAB)), true);
    public static final Block CUT_SANDSTONE_VERTICAL_SLAB = registerStoneBlock("cut_sandstone_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_SANDSTONE_SLAB)), true);
    public static final Block RED_SANDSTONE_VERTICAL_SLAB = registerStoneBlock("red_sandstone_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_SANDSTONE_SLAB)), true);
    public static final Block SMOOTH_RED_SANDSTONE_VERTICAL_SLAB = registerStoneBlock("smooth_red_sandstone_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_RED_SANDSTONE_SLAB)), true);
    public static final Block CUT_RED_SANDSTONE_VERTICAL_SLAB = registerStoneBlock("cut_red_sandstone_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_RED_SANDSTONE_SLAB)), true);
    public static final Block PRISMARINE_VERTICAL_SLAB = registerStoneBlock("prismarine_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_SLAB)), true);
    public static final Block PRISMARINE_BRICK_VERTICAL_SLAB = registerStoneBlock("prismarine_brick_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_BRICK_SLAB)), true);
    public static final Block DARK_PRISMARINE_VERTICAL_SLAB = registerStoneBlock("dark_prismarine_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_PRISMARINE_SLAB)), true);
    public static final Block NETHER_BRICK_VERTICAL_SLAB = registerStoneBlock("nether_brick_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_SLAB)), true);
    public static final Block RED_NETHER_BRICK_VERTICAL_SLAB = registerStoneBlock("red_nether_brick_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICK_SLAB)), true);
    public static final Block BLACKSTONE_VERTICAL_SLAB = registerStoneBlock("blackstone_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_SLAB)), true);
    public static final Block POLISHED_BLACKSTONE_VERTICAL_SLAB = registerStoneBlock("polished_blackstone_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_SLAB)), true);
    public static final Block POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB = registerStoneBlock("polished_blackstone_brick_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICK_SLAB)), true);
    public static final Block END_STONE_BRICK_VERTICAL_SLAB = registerStoneBlock("end_stone_brick_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICK_SLAB)), true);
    public static final Block PURPUR_VERTICAL_SLAB = registerStoneBlock("purpur_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PURPUR_SLAB)), true);
    public static final Block QUARTZ_VERTICAL_SLAB = registerStoneBlock("quartz_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_SLAB)), true);
    public static final Block SMOOTH_QUARTZ_VERTICAL_SLAB = registerStoneBlock("smooth_quartz_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_QUARTZ_SLAB)), true);
    public static final Block CUT_COPPER_VERTICAL_SLAB = registerMiscBlock("cut_copper_vertical_slab",
            new OxidizableVerticalSlabBlock(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_COPPER_SLAB)), true);
    public static final Block EXPOSED_CUT_COPPER_VERTICAL_SLAB = registerMiscBlock("exposed_cut_copper_vertical_slab",
            new OxidizableVerticalSlabBlock(WeatheringCopper.WeatherState.EXPOSED, BlockBehaviour.Properties.ofFullCopy(Blocks.EXPOSED_CUT_COPPER_SLAB)), true);
    public static final Block WEATHERED_CUT_COPPER_VERTICAL_SLAB = registerMiscBlock("weathered_cut_copper_vertical_slab",
            new OxidizableVerticalSlabBlock(WeatheringCopper.WeatherState.WEATHERED, BlockBehaviour.Properties.ofFullCopy(Blocks.WEATHERED_CUT_COPPER_SLAB)), true);
    public static final Block OXIDIZED_CUT_COPPER_VERTICAL_SLAB = registerMiscBlock("oxidized_cut_copper_vertical_slab",
            new OxidizableVerticalSlabBlock(WeatheringCopper.WeatherState.OXIDIZED, BlockBehaviour.Properties.ofFullCopy(Blocks.OXIDIZED_CUT_COPPER_SLAB)), true);
    public static final Block WAXED_CUT_COPPER_VERTICAL_SLAB = registerMiscBlock("waxed_cut_copper_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_CUT_COPPER_SLAB)), true);
    public static final Block WAXED_EXPOSED_CUT_COPPER_VERTICAL_SLAB = registerMiscBlock("waxed_exposed_cut_copper_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB)), true);
    public static final Block WAXED_WEATHERED_CUT_COPPER_VERTICAL_SLAB = registerMiscBlock("waxed_weathered_cut_copper_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB)), true);
    public static final Block WAXED_OXIDIZED_CUT_COPPER_VERTICAL_SLAB = registerMiscBlock("waxed_oxidized_cut_copper_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB)), true);

    public static final Block BLACK_WOOL_VERTICAL_SLAB = registerMiscBlock("black_wool_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block BLUE_WOOL_VERTICAL_SLAB = registerMiscBlock("blue_wool_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block BROWN_WOOL_VERTICAL_SLAB = registerMiscBlock("brown_wool_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block CYAN_WOOL_VERTICAL_SLAB = registerMiscBlock("cyan_wool_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block GRAY_WOOL_VERTICAL_SLAB = registerMiscBlock("gray_wool_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block GREEN_WOOL_VERTICAL_SLAB = registerMiscBlock("green_wool_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block LIGHT_BLUE_WOOL_VERTICAL_SLAB = registerMiscBlock("light_blue_wool_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block LIGHT_GRAY_WOOL_VERTICAL_SLAB = registerMiscBlock("light_gray_wool_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block LIME_WOOL_VERTICAL_SLAB = registerMiscBlock("lime_wool_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block MAGENTA_WOOL_VERTICAL_SLAB = registerMiscBlock("magenta_wool_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block ORANGE_WOOL_VERTICAL_SLAB = registerMiscBlock("orange_wool_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block PINK_WOOL_VERTICAL_SLAB = registerMiscBlock("pink_wool_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block PURPLE_WOOL_VERTICAL_SLAB = registerMiscBlock("purple_wool_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block RED_WOOL_VERTICAL_SLAB = registerMiscBlock("red_wool_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block WHITE_WOOL_VERTICAL_SLAB = registerMiscBlock("white_wool_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block YELLOW_WOOL_VERTICAL_SLAB = registerMiscBlock("yellow_wool_vertical_slab",
            new VerticalSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    //endregion

    //region VANILLA STAIRS
    public static final Block DIRT_STAIRS = registerMiscBlock("dirt_stairs",
            new StairBlock(Blocks.DIRT.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)), true);
    public static final Block COARSE_DIRT_STAIRS = registerMiscBlock("coarse_dirt_stairs",
            new StairBlock(Blocks.COARSE_DIRT.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.COARSE_DIRT)), true);
    public static final Block ROOTED_DIRT_STAIRS = registerMiscBlock("rooted_dirt_stairs",
            new StairBlock(Blocks.ROOTED_DIRT.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.ROOTED_DIRT)), true);
    public static final Block MUD_STAIRS = registerMiscBlock("mud_stairs",
            new StairBlock(Blocks.MUD.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT).sound(SoundType.MUD)), true);
    public static final Block MOSS_STAIRS = registerMiscBlock("moss_stairs",
            new StairBlock(Blocks.MOSS_BLOCK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_BLOCK)), true);

    public static final Block BLACK_WOOL_STAIRS = registerMiscBlock("black_wool_stairs",
            new StairBlock(Blocks.BLACK_WOOL.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block BLUE_WOOL_STAIRS = registerMiscBlock("blue_wool_stairs",
            new StairBlock(Blocks.BLACK_WOOL.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block BROWN_WOOL_STAIRS = registerMiscBlock("brown_wool_stairs",
            new StairBlock(Blocks.BLACK_WOOL.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block CYAN_WOOL_STAIRS = registerMiscBlock("cyan_wool_stairs",
            new StairBlock(Blocks.BLACK_WOOL.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block GRAY_WOOL_STAIRS = registerMiscBlock("gray_wool_stairs",
            new StairBlock(Blocks.BLACK_WOOL.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block GREEN_WOOL_STAIRS = registerMiscBlock("green_wool_stairs",
            new StairBlock(Blocks.BLACK_WOOL.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block LIGHT_BLUE_WOOL_STAIRS = registerMiscBlock("light_blue_wool_stairs",
            new StairBlock(Blocks.BLACK_WOOL.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block LIGHT_GRAY_WOOL_STAIRS = registerMiscBlock("light_gray_wool_stairs",
            new StairBlock(Blocks.BLACK_WOOL.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block LIME_WOOL_STAIRS = registerMiscBlock("lime_wool_stairs",
            new StairBlock(Blocks.BLACK_WOOL.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block MAGENTA_WOOL_STAIRS = registerMiscBlock("magenta_wool_stairs",
            new StairBlock(Blocks.BLACK_WOOL.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block ORANGE_WOOL_STAIRS = registerMiscBlock("orange_wool_stairs",
            new StairBlock(Blocks.BLACK_WOOL.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block PINK_WOOL_STAIRS = registerMiscBlock("pink_wool_stairs",
            new StairBlock(Blocks.BLACK_WOOL.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block PURPLE_WOOL_STAIRS = registerMiscBlock("purple_wool_stairs",
            new StairBlock(Blocks.BLACK_WOOL.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block RED_WOOL_STAIRS = registerMiscBlock("red_wool_stairs",
            new StairBlock(Blocks.BLACK_WOOL.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block WHITE_WOOL_STAIRS = registerMiscBlock("white_wool_stairs",
            new StairBlock(Blocks.BLACK_WOOL.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    public static final Block YELLOW_WOOL_STAIRS = registerMiscBlock("yellow_wool_stairs",
            new StairBlock(Blocks.BLACK_WOOL.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).ignitedByLava()), true);
    //endregion

    //region VANILLA LAYERS
    public static final Block GRAVEL_LAYER = registerMiscBlock("gravel_layer",
            new LayersBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRAVEL), Blocks.GRAVEL), false);
    public static final Block SAND_LAYER = registerMiscBlock("sand_layer",
            new LayersBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SAND), Blocks.SAND), false);
    public static final Block BLACK_SAND_LAYER = registerMiscBlock("black_sand_layer",
            new LayersBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SAND), BLACK_SAND), false);
    public static final Block WHITE_SAND_LAYER = registerMiscBlock("white_sand_layer",
            new LayersBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SAND), WHITE_SAND), false);
    //endregion

    public static Block registerBlock(String name, Block block, boolean drop) {
        if(drop){
            BlockDrops.blocks.add(block);
        }
        registerBlockItem(name, block);
        return NeoForgeRegistrationBridge.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, name), block);
    }

    public static Block registerStoneBlock(String name, Block block, boolean drop) {
        if(drop){
            BlockDrops.blocks.add(block);
        }
        registerBlockItem(name, block);
        ModItemGroups.STONE_BLOCKS_CONTENTS.add(block.asItem().getDefaultInstance());
        return NeoForgeRegistrationBridge.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, name), block);
    }

    public static Block registerWoodBlock(String name, Block block, boolean drop) {
        if(drop){
            BlockDrops.blocks.add(block);
        }
        registerBlockItem(name, block);
        ModItemGroups.WOOD_BLOCKS_CONTENTS.add(block.asItem().getDefaultInstance());
        return NeoForgeRegistrationBridge.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, name), block);
    }

    public static Block registerMiscBlock(String name, Block block, boolean drop) {
        if(drop){
            BlockDrops.blocks.add(block);
        }
        registerBlockItem(name, block);
        ModItemGroups.MISC_BLOCKS_CONTENTS.add(block.asItem().getDefaultInstance());
        return NeoForgeRegistrationBridge.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, name), block);
    }

    static void registerBlockItem(String name, Block block) {
        var item =  NeoForgeRegistrationBridge.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, name),
                new BlockItem(block, new Item.Properties()));
        Item.BY_BLOCK.put(block, item);
    }

    public static void registerModBlocks() {
        LoggerUtil.logDebugMsg("Registering ModBlocks for " + MiddleEarth.MOD_ID);
    }

    public static boolean never(BlockState state, BlockGetter world, BlockPos pos) {
        return false;
    }

    public static Boolean canSpawnOnLeaves(BlockState state, BlockGetter world, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }
}
