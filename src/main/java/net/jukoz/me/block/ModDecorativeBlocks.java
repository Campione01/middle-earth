package net.jukoz.me.block;

import net.jukoz.me.utils.NeoForgeRegistrationBridge;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.special.*;
import net.jukoz.me.block.special.artefact.arkenstone.ArkenstoneBlock;
import net.jukoz.me.block.special.artefact.arkenstone.ArkenstoneWallBlock;
import net.jukoz.me.block.special.artisantable.ArtisanTable;
import net.jukoz.me.block.special.beds.CustomBedBlock;
import net.jukoz.me.block.special.bellows.BellowsBlock;
import net.jukoz.me.block.special.doors.*;
import net.jukoz.me.block.special.fireBlocks.*;
import net.jukoz.me.block.special.fire_of_orthanc.FireOfOrthancBlock;
import net.jukoz.me.block.special.forge.ForgeBlock;
import net.jukoz.me.block.special.pots.AmphoraBlock;
import net.jukoz.me.block.special.pots.FatPotBlock;
import net.jukoz.me.block.special.pots.JarBlock;
import net.jukoz.me.block.special.pots.JugBlock;
import net.jukoz.me.block.special.reinforcedChest.ReinforcedChestBlock;
import net.jukoz.me.block.special.shapingAnvil.dwarvenTreatedAnvil.DwarvenShapingAnvilBlock;
import net.jukoz.me.block.special.shapingAnvil.elvenTreatedAnvil.ElvenTreatedAnvilblock;
import net.jukoz.me.block.special.shapingAnvil.orcishTreatedAnvil.OrcishTreatedAnvilblock;
import net.jukoz.me.block.special.shapingAnvil.treatedAnvil.TreatedAnvilblock;
import net.jukoz.me.block.special.statues.StatueBlock;
import net.jukoz.me.block.special.toggeable_lights.*;
import net.jukoz.me.block.special.torches.METorchBlock;
import net.jukoz.me.block.special.torches.MEWallTorchBlock;
import net.jukoz.me.block.special.torches.OrcSconceBlock;
import net.jukoz.me.block.special.wood_pile.WoodPileBlock;
import net.jukoz.me.item.utils.ModItemGroups;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.world.level.block.*;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import java.util.function.ToIntFunction;

public class ModDecorativeBlocks {

    public static final Block SILVER_LANTERN = registerBlock("silver_lantern",
            new SilverLanternBlock(BlockBehaviour.Properties.of().lightLevel(createLightLevelFromLitBlockState(15)).strength(1.0f)));
    public static final Block WALL_SILVER_LANTERN = registerBlock("silver_lantern_wall",
            new WallSilverLanternBlock(BlockBehaviour.Properties.of().lightLevel(createLightLevelFromLitBlockState(15)).strength(1.0f)));

    public static final Block ELVEN_LANTERN = registerBlock("elven_lantern",
            new ElvenLanternBlock(BlockBehaviour.Properties.of().lightLevel(createLightLevelFromLitBlockState(15)).strength(1.0f)));
    public static final Block WALL_ELVEN_LANTERN = registerBlock("elven_lantern_wall",
            new WallElvenLanternBlock(BlockBehaviour.Properties.of().lightLevel(createLightLevelFromLitBlockState(15)).strength(1.0f)));

    public static final Block DWARVEN_LANTERN = registerBlock("dwarven_lantern",
            new DwarvenLanternBlock(BlockBehaviour.Properties.of().lightLevel(createLightLevelFromLitBlockState(15)).strength(1.0f).noOcclusion()));
    public static final Block WALL_DWARVEN_LANTERN = registerBlock("dwarven_lantern_wall",
            new WallDwarvenLanternBlock(BlockBehaviour.Properties.of().lightLevel(createLightLevelFromLitBlockState(15)).strength(1.0f)));
    
    public static final Block CRYSTAL_LAMP = registerBlock("crystal_lamp",
            new CrystalLampBlock(BlockBehaviour.Properties.of().lightLevel(createLightLevelFromLitBlockState(15)).strength(1.0f).noOcclusion()));
    public static final Block WALL_CRYSTAL_LAMP = registerBlock("crystal_lamp_wall",
            new WallCrystalLampBlock(BlockBehaviour.Properties.of().lightLevel(createLightLevelFromLitBlockState(15)).strength(1.0f)));

    public static final Block FORGE = registerBlock("forge",
            new ForgeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).lightLevel(createLightLevelFromLitBlockState(15)).strength(1.65f).requiresCorrectToolForDrops()));
    public static final Block TREATED_ANVIL = registerBlock("treated_anvil",
            new TreatedAnvilblock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(1.65f).requiresCorrectToolForDrops().noOcclusion()));
    public static final Block DWARVEN_TREATED_ANVIL = registerBlock("dwarven_treated_anvil",
            new DwarvenShapingAnvilBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(1.65f).requiresCorrectToolForDrops().noOcclusion()));
    public static final Block ELVEN_TREATED_ANVIL = registerBlock("elven_treated_anvil",
            new ElvenTreatedAnvilblock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(1.65f).requiresCorrectToolForDrops().noOcclusion()));
    public static final Block ORCISH_TREATED_ANVIL = registerBlock("orcish_treated_anvil",
            new OrcishTreatedAnvilblock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(1.65f).requiresCorrectToolForDrops().noOcclusion()));

    public static final Block BELLOWS = registerBlock("bellows",
            new BellowsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block ARTISAN_TABLE = registerBlock("artisan_table",
            new ArtisanTable(BlockBehaviour.Properties.ofFullCopy(Blocks.SMITHING_TABLE).noOcclusion()));

    public static final Block SMALL_CRATE = registerBlock("small_crate",
            new CrateBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final Block THIN_BARREL = registerBlock("thin_barrel",
            new ThinBarrelBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final Block REINFORCED_CHEST = registerBlock("reinforced_chest",
            new ReinforcedChestBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(5.0f).sound(SoundType.WOOD).noOcclusion().requiresCorrectToolForDrops()));

    public static final Block WOOD_PILE = registerBlock("wood_pile",
            new WoodPileBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(1.0f).noOcclusion()));

    public static final Block CANDLE_HEAP = registerBlockWithItem("candle_heap",
            new CandleHeapBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CANDLE).noOcclusion().lightLevel(createLightLevelFromLitBlockState(10))));

    public static final Block CALCITE_STATUE = registerBlockWithItem("calcite_statue",
            new StatueBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).noOcclusion().requiresCorrectToolForDrops()));
    public static final Block GALONN_STATUE = registerBlockWithItem("galonn_statue",
            new StatueBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final Block GONLUIN_STATUE = registerBlockWithItem("gonluin_statue",
            new StatueBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final Block TUFF_STATUE = registerBlockWithItem("tuff_statue",
            new StatueBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF).noOcclusion().requiresCorrectToolForDrops()));
    public static final Block MEDGON_SPIKE = registerBlockWithItem("medgon_spike",
            new StatueBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final Block FIRE_OF_ORTHANC = registerBlock("fire_of_orthanc",
            new FireOfOrthancBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().mapColor(MapColor.COLOR_BLACK)
                    .sound(SoundType.METAL).strength(6f).ignitedByLava().isRedstoneConductor((state, world, pos) -> false).noOcclusion()));
    public static final Block TORCH_OF_ORTHANC = registerBlock("torch_of_orthanc",
            new TorchOfOrthancBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).lightLevel(createLightLevelFromLitBlockState(15)).noOcclusion().requiresCorrectToolForDrops(), ParticleTypes.FLAME));

    public static final Block WOOD_FRAMED_WINDOW = registerBlockWithItem("wood_framed_window",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block WOOD_FRAMED_WINDOW_PANE = registerBlockWithItem("wood_framed_window_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));

    public static final Block WATTLE_AND_BRICK_WINDOW = registerBlockWithItem("wattle_and_brick_window",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block WATTLE_AND_BRICK_WINDOW_PANE = registerBlockWithItem("wattle_and_brick_window_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));

    public static final Block WATTLE_FRAMED_WINDOW = registerBlockWithItem("wattle_framed_window",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block WATTLE_FRAMED_WINDOW_PANE = registerBlockWithItem("wattle_framed_window_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));

    public static final Block DARK_WATTLE_FRAMED_WINDOW = registerBlockWithItem("dark_wattle_framed_window",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block DARK_WATTLE_FRAMED_WINDOW_PANE = registerBlockWithItem("dark_wattle_framed_window_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));

    public static final Block BLACK_WATTLE_FRAMED_WINDOW = registerBlockWithItem("black_wattle_framed_window",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block BLACK_WATTLE_FRAMED_WINDOW_PANE = registerBlockWithItem("black_wattle_framed_window_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));

    public static final Block GREEN_WATTLE_FRAMED_WINDOW = registerBlockWithItem("green_wattle_framed_window",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block GREEN_WATTLE_FRAMED_WINDOW_PANE = registerBlockWithItem("green_wattle_framed_window_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));

    public static final Block RED_WATTLE_FRAMED_WINDOW = registerBlockWithItem("red_wattle_framed_window",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block RED_WATTLE_FRAMED_WINDOW_PANE = registerBlockWithItem("red_wattle_framed_window_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));

    public static final Block WHITE_WATTLE_FRAMED_WINDOW = registerBlockWithItem("white_wattle_framed_window",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block WHITE_WATTLE_FRAMED_WINDOW_PANE = registerBlockWithItem("white_wattle_framed_window_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));

    public static final Block TUFF_CARVED_WINDOW = registerBlockWithItem("tuff_carved_window",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block TUFF_CARVED_WINDOW_PANE = registerBlockWithItem("tuff_carved_window_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));

    public static final Block BLACKSTONE_CARVED_WINDOW = registerBlockWithItem("blackstone_carved_window",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block BLACKSTONE_CARVED_WINDOW_PANE = registerBlockWithItem("blackstone_carved_window_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));

    public static final Block GONLUIN_CARVED_WINDOW = registerBlockWithItem("gonluin_carved_window",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block GONLUIN_CARVED_WINDOW_PANE = registerBlockWithItem("gonluin_carved_window_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));

    public static final Block MEDGON_CARVED_WINDOW = registerBlockWithItem("medgon_carved_window",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block MEDGON_CARVED_WINDOW_PANE = registerBlockWithItem("medgon_carved_window_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));

    public static final Block IZHERABAN_CARVED_WINDOW = registerBlockWithItem("izheraban_carved_window",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block IZHERABAN_CARVED_WINDOW_PANE = registerBlockWithItem("izheraban_carved_window_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));

    public static final Block MUD_BRICK_ROUND_WINDOW = registerBlockWithItem("mud_brick_round_window",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block MUD_BRICK_ROUND_WINDOW_PANE = registerBlockWithItem("mud_brick_round_window_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));

    public static final Block WHITE_DAUB_ROUND_WINDOW = registerBlockWithItem("white_daub_round_window",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block WHITE_DAUB_ROUND_WINDOW_PANE = registerBlockWithItem("white_daub_round_window_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));
    public static final Block YELLOW_DAUB_ROUND_WINDOW = registerBlockWithItem("yellow_daub_round_window",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block YELLOW_DAUB_ROUND_WINDOW_PANE = registerBlockWithItem("yellow_daub_round_window_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));

    public static final Block WHITE_DAUB_HOBBIT_WINDOW = registerBlockWithItem("white_daub_hobbit_window",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block WHITE_DAUB_HOBBIT_WINDOW_PANE = registerBlockWithItem("white_daub_hobbit_window_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));
    public static final Block YELLOW_DAUB_HOBBIT_WINDOW = registerBlockWithItem("yellow_daub_hobbit_window",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block YELLOW_DAUB_HOBBIT_WINDOW_PANE = registerBlockWithItem("yellow_daub_hobbit_window_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));

    public static final Block PLASTER_HOBBIT_WINDOW = registerBlockWithItem("plaster_hobbit_window",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block PLASTER_HOBBIT_WINDOW_PANE = registerBlockWithItem("plaster_hobbit_window_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));

    public static final Block PLASTER_ROUND_WINDOW = registerBlockWithItem("plaster_round_window",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block PLASTER_ROUND_WINDOW_PANE = registerBlockWithItem("plaster_round_window_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));

    public static final Block LEAD_GLASS = registerBlockWithItem("lead_glass",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block BLACK_STAINED_LEAD_GLASS = registerBlockWithItem("black_stained_lead_glass",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block BLUE_STAINED_LEAD_GLASS = registerBlockWithItem("blue_stained_lead_glass",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block BROWN_STAINED_LEAD_GLASS = registerBlockWithItem("brown_stained_lead_glass",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block CYAN_STAINED_LEAD_GLASS = registerBlockWithItem("cyan_stained_lead_glass",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block GRAY_STAINED_LEAD_GLASS = registerBlockWithItem("gray_stained_lead_glass",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block GREEN_STAINED_LEAD_GLASS = registerBlockWithItem("green_stained_lead_glass",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block LIGHT_BLUE_STAINED_LEAD_GLASS = registerBlockWithItem("light_blue_stained_lead_glass",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block LIGHT_GRAY_STAINED_LEAD_GLASS = registerBlockWithItem("light_gray_stained_lead_glass",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block LIME_STAINED_LEAD_GLASS = registerBlockWithItem("lime_stained_lead_glass",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block MAGENTA_STAINED_LEAD_GLASS = registerBlockWithItem("magenta_stained_lead_glass",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block ORANGE_STAINED_LEAD_GLASS = registerBlockWithItem("orange_stained_lead_glass",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block PINK_STAINED_LEAD_GLASS = registerBlockWithItem("pink_stained_lead_glass",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block PURPLE_STAINED_LEAD_GLASS = registerBlockWithItem("purple_stained_lead_glass",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block RED_STAINED_LEAD_GLASS = registerBlockWithItem("red_stained_lead_glass",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block WHITE_STAINED_LEAD_GLASS = registerBlockWithItem("white_stained_lead_glass",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block YELLOW_STAINED_LEAD_GLASS = registerBlockWithItem("yellow_stained_lead_glass",
            new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final Block LEAD_GLASS_PANE = registerBlockWithItem("lead_glass_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));
    public static final Block BLACK_STAINED_LEAD_GLASS_PANE = registerBlockWithItem("black_stained_lead_glass_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));
    public static final Block BLUE_STAINED_LEAD_GLASS_PANE = registerBlockWithItem("blue_stained_lead_glass_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));
    public static final Block BROWN_STAINED_LEAD_GLASS_PANE = registerBlockWithItem("brown_stained_lead_glass_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));
    public static final Block CYAN_STAINED_LEAD_GLASS_PANE = registerBlockWithItem("cyan_stained_lead_glass_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));
    public static final Block GRAY_STAINED_LEAD_GLASS_PANE = registerBlockWithItem("gray_stained_lead_glass_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));
    public static final Block GREEN_STAINED_LEAD_GLASS_PANE = registerBlockWithItem("green_stained_lead_glass_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));
    public static final Block LIGHT_BLUE_STAINED_LEAD_GLASS_PANE = registerBlockWithItem("light_blue_stained_lead_glass_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));
    public static final Block LIGHT_GRAY_STAINED_LEAD_GLASS_PANE = registerBlockWithItem("light_gray_stained_lead_glass_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));
    public static final Block LIME_STAINED_LEAD_GLASS_PANE = registerBlockWithItem("lime_stained_lead_glass_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));
    public static final Block MAGENTA_STAINED_LEAD_GLASS_PANE = registerBlockWithItem("magenta_stained_lead_glass_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));
    public static final Block ORANGE_STAINED_LEAD_GLASS_PANE = registerBlockWithItem("orange_stained_lead_glass_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));
    public static final Block PINK_STAINED_LEAD_GLASS_PANE = registerBlockWithItem("pink_stained_lead_glass_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));
    public static final Block PURPLE_STAINED_LEAD_GLASS_PANE = registerBlockWithItem("purple_stained_lead_glass_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));
    public static final Block RED_STAINED_LEAD_GLASS_PANE = registerBlockWithItem("red_stained_lead_glass_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));
    public static final Block WHITE_STAINED_LEAD_GLASS_PANE = registerBlockWithItem("white_stained_lead_glass_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));
    public static final Block YELLOW_STAINED_LEAD_GLASS_PANE = registerBlockWithItem("yellow_stained_lead_glass_pane",
            new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));

    public static final Block BLUE_CUSHION = registerBlockWithItem("blue_cushion",
            new CushionBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB).noOcclusion()));
    public static final Block BROWN_CUSHION = registerBlockWithItem("brown_cushion",
            new CushionBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB).noOcclusion()));
    public static final Block DARK_BLUE_CUSHION = registerBlockWithItem("dark_blue_cushion",
            new CushionBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB).noOcclusion()));
    public static final Block DARK_BROWN_CUSHION = registerBlockWithItem("dark_brown_cushion",
            new CushionBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB).noOcclusion()));
    public static final Block DARK_GREEN_CUSHION = registerBlockWithItem("dark_green_cushion",
            new CushionBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB).noOcclusion()));
    public static final Block DARK_RED_CUSHION = registerBlockWithItem("dark_red_cushion",
            new CushionBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB).noOcclusion()));
    public static final Block GREEN_CUSHION = registerBlockWithItem("green_cushion",
            new CushionBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB).noOcclusion()));
    public static final Block RED_CUSHION = registerBlockWithItem("red_cushion",
            new CushionBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB).noOcclusion()));

    //region VANILLA FURNITURE
    public static final Block STONE_STOOL = registerBlockWithItem("stone_stool",
            new StoolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops().noOcclusion()));
    public static final Block STONE_TABLE = registerBlockWithItem("stone_table",
            new StoneTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops().noOcclusion()));
    public static final Block STONE_CHAIR = registerBlockWithItem("stone_chair",
            new StoneChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops().noOcclusion()));

    public static final Block CALCITE_STOOL = registerBlockWithItem("calcite_stool",
            new StoolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).requiresCorrectToolForDrops().noOcclusion()));
    public static final Block CALCITE_TABLE = registerBlockWithItem("calcite_table",
            new StoneTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).requiresCorrectToolForDrops().noOcclusion()));
    public static final Block CALCITE_CHAIR = registerBlockWithItem("calcite_chair",
            new StoneChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).requiresCorrectToolForDrops().noOcclusion()));

    public static final Block ANDESITE_STOOL = registerBlockWithItem("andesite_stool",
            new StoolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE).requiresCorrectToolForDrops().noOcclusion()));
    public static final Block ANDESITE_TABLE = registerBlockWithItem("andesite_table",
            new StoneTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE).requiresCorrectToolForDrops().noOcclusion()));
    public static final Block ANDESITE_CHAIR = registerBlockWithItem("andesite_chair",
            new StoneChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE).requiresCorrectToolForDrops().noOcclusion()));

    public static final Block GRANITE_STOOL = registerBlockWithItem("granite_stool",
            new StoolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).requiresCorrectToolForDrops().noOcclusion()));
    public static final Block GRANITE_TABLE = registerBlockWithItem("granite_table",
            new StoneTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).requiresCorrectToolForDrops().noOcclusion()));
    public static final Block GRANITE_CHAIR = registerBlockWithItem("granite_chair",
            new StoneChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).requiresCorrectToolForDrops().noOcclusion()));

    public static final Block DIORITE_STOOL = registerBlockWithItem("diorite_stool",
            new StoolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE).requiresCorrectToolForDrops().noOcclusion()));
    public static final Block DIORITE_TABLE = registerBlockWithItem("diorite_table",
            new StoneTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE).requiresCorrectToolForDrops().noOcclusion()));
    public static final Block DIORITE_CHAIR = registerBlockWithItem("diorite_chair",
            new StoneChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE).requiresCorrectToolForDrops().noOcclusion()));

    public static final Block DEEPSLATE_STOOL = registerBlockWithItem("deepslate_stool",
            new StoolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).requiresCorrectToolForDrops().noOcclusion()));
    public static final Block DEEPSLATE_TABLE = registerBlockWithItem("deepslate_table",
            new StoneTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).requiresCorrectToolForDrops().noOcclusion()));
    public static final Block DEEPSLATE_CHAIR = registerBlockWithItem("deepslate_chair",
            new StoneChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).requiresCorrectToolForDrops().noOcclusion()));

    public static final Block BLACKSTONE_STOOL = registerBlockWithItem("blackstone_stool",
            new StoolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).requiresCorrectToolForDrops().noOcclusion()));
    public static final Block BLACKSTONE_TABLE = registerBlockWithItem("blackstone_table",
            new StoneTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).requiresCorrectToolForDrops().noOcclusion()));
    public static final Block BLACKSTONE_CHAIR = registerBlockWithItem("blackstone_chair",
            new StoneChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).requiresCorrectToolForDrops().noOcclusion()));

    public static final Block BASALT_STOOL = registerBlockWithItem("basalt_stool",
            new StoolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).requiresCorrectToolForDrops().noOcclusion()));
    public static final Block BASALT_TABLE = registerBlockWithItem("basalt_table",
            new StoneTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).requiresCorrectToolForDrops().noOcclusion()));
    public static final Block BASALT_CHAIR = registerBlockWithItem("basalt_chair",
            new StoneChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).requiresCorrectToolForDrops().noOcclusion()));

    public static final Block TUFF_STOOL = registerBlockWithItem("tuff_stool",
            new StoolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF).requiresCorrectToolForDrops().noOcclusion()));
    public static final Block TUFF_TABLE = registerBlockWithItem("tuff_table",
            new StoneTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF).requiresCorrectToolForDrops().noOcclusion()));
    public static final Block TUFF_CHAIR = registerBlockWithItem("tuff_chair",
            new StoneChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF).requiresCorrectToolForDrops().noOcclusion()));

    public static final Block OAK_STOOL = registerBlockWithItem("oak_stool",
            new WoodStoolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block OAK_BENCH = registerBlockWithItem("oak_bench",
            new WoodBenchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block OAK_TABLE = registerBlockWithItem("oak_table",
            new WoodTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block OAK_CHAIR = registerBlockWithItem("oak_chair",
            new WoodChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion()));

    public static final Block SPRUCE_STOOL = registerBlockWithItem("spruce_stool",
            new WoodStoolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS).noOcclusion()));
    public static final Block SPRUCE_BENCH = registerBlockWithItem("spruce_bench",
            new WoodBenchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS).noOcclusion()));
    public static final Block SPRUCE_TABLE = registerBlockWithItem("spruce_table",
            new WoodTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS).noOcclusion()));
    public static final Block SPRUCE_CHAIR = registerBlockWithItem("spruce_chair",
            new WoodChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS).noOcclusion()));

    public static final Block BIRCH_STOOL = registerBlockWithItem("birch_stool",
            new WoodStoolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PLANKS).noOcclusion()));
    public static final Block BIRCH_BENCH = registerBlockWithItem("birch_bench",
            new WoodBenchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS).noOcclusion()));
    public static final Block BIRCH_TABLE = registerBlockWithItem("birch_table",
            new WoodTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PLANKS).noOcclusion()));
    public static final Block BIRCH_CHAIR = registerBlockWithItem("birch_chair",
            new WoodChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PLANKS).noOcclusion()));

    public static final Block JUNGLE_STOOL = registerBlockWithItem("jungle_stool",
            new WoodStoolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS).noOcclusion()));
    public static final Block JUNGLE_BENCH = registerBlockWithItem("jungle_bench",
            new WoodBenchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS).noOcclusion()));
    public static final Block JUNGLE_TABLE = registerBlockWithItem("jungle_table",
            new WoodTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS).noOcclusion()));
    public static final Block JUNGLE_CHAIR = registerBlockWithItem("jungle_chair",
            new WoodChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS).noOcclusion()));

    public static final Block ACACIA_STOOL = registerBlockWithItem("acacia_stool",
            new WoodStoolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS).noOcclusion()));
    public static final Block ACACIA_BENCH = registerBlockWithItem("acacia_bench",
            new WoodBenchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS).noOcclusion()));
    public static final Block ACACIA_TABLE = registerBlockWithItem("acacia_table",
            new WoodTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS).noOcclusion()));
    public static final Block ACACIA_CHAIR = registerBlockWithItem("acacia_chair",
            new WoodChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS).noOcclusion().noOcclusion()));

    public static final Block DARK_OAK_STOOL = registerBlockWithItem("dark_oak_stool",
            new WoodStoolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS).noOcclusion()));
    public static final Block DARK_OAK_BENCH = registerBlockWithItem("dark_oak_bench",
            new WoodBenchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS).noOcclusion()));
    public static final Block DARK_OAK_TABLE = registerBlockWithItem("dark_oak_table",
            new WoodTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS).noOcclusion()));
    public static final Block DARK_OAK_CHAIR = registerBlockWithItem("dark_oak_chair",
            new WoodChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS).noOcclusion()));
    
    public static final Block MANGROVE_STOOL = registerBlockWithItem("mangrove_stool",
            new WoodStoolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS).noOcclusion()));
    public static final Block MANGROVE_BENCH = registerBlockWithItem("mangrove_bench",
            new WoodBenchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS).noOcclusion()));
    public static final Block MANGROVE_TABLE = registerBlockWithItem("mangrove_table",
            new WoodTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS).noOcclusion()));
    public static final Block MANGROVE_CHAIR = registerBlockWithItem("mangrove_chair",
            new WoodChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS).noOcclusion()));

    public static final Block CHERRY_STOOL = registerBlockWithItem("cherry_stool",
            new WoodStoolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS).noOcclusion()));
    public static final Block CHERRY_BENCH = registerBlockWithItem("cherry_bench",
            new WoodBenchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS).noOcclusion()));
    public static final Block CHERRY_TABLE = registerBlockWithItem("cherry_table",
            new WoodTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS).noOcclusion()));
    public static final Block CHERRY_CHAIR = registerBlockWithItem("cherry_chair",
            new WoodChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS).noOcclusion()));

    public static final Block BAMBOO_STOOL = registerBlockWithItem("bamboo_stool",
            new WoodStoolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS).noOcclusion()));
    public static final Block BAMBOO_BENCH = registerBlockWithItem("bamboo_bench",
            new WoodBenchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS).noOcclusion()));
    public static final Block BAMBOO_TABLE = registerBlockWithItem("bamboo_table",
            new WoodTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS).noOcclusion()));
    public static final Block BAMBOO_CHAIR = registerBlockWithItem("bamboo_chair",
            new WoodChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS).noOcclusion()));

    public static final Block CRIMSON_STOOL = registerBlockWithItem("crimson_stool",
            new WoodStoolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS).noOcclusion()));
    public static final Block CRIMSON_BENCH = registerBlockWithItem("crimson_bench",
            new WoodBenchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS).noOcclusion()));
    public static final Block CRIMSON_TABLE = registerBlockWithItem("crimson_table",
            new WoodTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS).noOcclusion()));
    public static final Block CRIMSON_CHAIR = registerBlockWithItem("crimson_chair",
            new WoodChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS).noOcclusion()));

    public static final Block WARPED_STOOL = registerBlockWithItem("warped_stool",
            new WoodStoolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS).noOcclusion()));
    public static final Block WARPED_BENCH = registerBlockWithItem("warped_bench",
            new WoodBenchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS).noOcclusion()));
    public static final Block WARPED_TABLE = registerBlockWithItem("warped_table",
            new WoodTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS).noOcclusion()));
    public static final Block WARPED_CHAIR = registerBlockWithItem("warped_chair",
            new WoodChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS).noOcclusion()));

    public static final Block TREATED_WOOD_STOOL = registerBlockWithItem("treated_wood_stool",
            new WoodStoolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block TREATED_WOOD_BENCH = registerBlockWithItem("treated_wood_bench",
            new WoodBenchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block TREATED_WOOD_TABLE = registerBlockWithItem("treated_wood_table",
            new WoodTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block TREATED_WOOD_CHAIR = registerBlockWithItem("treated_wood_chair",
            new WoodChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion()));
    //endregion

    public static final Block WATERING_CAN = registerBlockWithItem("watering_can",
            new WateringCanBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion().requiresCorrectToolForDrops()));
    public static final Block WOODEN_BUCKET = registerBlockWithItem("wooden_bucket",
            new WoodenBucketBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion()));

    public static final Block POTTED_BEECH_SAPLING      = registerBlock("potted_beech_sapling", flowerPot(ModNatureBlocks.BEECH_SAPLING));
    public static final Block POTTED_CHESTNUT_SAPLING   = registerBlock("potted_chestnut_sapling", flowerPot(ModNatureBlocks.CHESTNUT_SAPLING));
    public static final Block POTTED_HOLLY_SAPLING      = registerBlock("potted_holly_sapling", flowerPot(ModNatureBlocks.HOLLY_SAPLING));
    public static final Block POTTED_FIR_SAPLING        = registerBlock("potted_fir_sapling", flowerPot(ModNatureBlocks.FIR_SAPLING));
    public static final Block POTTED_LARCH_SAPLING      = registerBlock("potted_larch_sapling", flowerPot(ModNatureBlocks.LARCH_SAPLING));
    public static final Block POTTED_LEBETHRON_SAPLING  = registerBlock("potted_lebethron_sapling", flowerPot(ModNatureBlocks.LEBETHRON_SAPLING));
    public static final Block POTTED_WHITE_LEBETHRON_SAPLING = registerBlock("potted_white_lebethron_sapling", flowerPot(ModNatureBlocks.WHITE_LEBETHRON_SAPLING));
    public static final Block POTTED_MALLORN_SAPLING    = registerBlock("potted_mallorn_sapling", flowerPot(ModNatureBlocks.MALLORN_SAPLING));
    public static final Block POTTED_MAPLE_SAPLING      = registerBlock("potted_maple_sapling", flowerPot(ModNatureBlocks.MAPLE_SAPLING));
    public static final Block POTTED_SILVER_MAPLE_SAPLING = registerBlock("potted_silver_maple_sapling", flowerPot(ModNatureBlocks.SILVER_MAPLE_SAPLING));
    public static final Block POTTED_MIRKWOOD_SAPLING   = registerBlock("potted_mirkwood_sapling", flowerPot(ModNatureBlocks.MIRKWOOD_SAPLING));
    public static final Block POTTED_PALM_SAPLING       = registerBlock("potted_palm_sapling", flowerPot(ModNatureBlocks.PALM_SAPLING));
    public static final Block POTTED_WHITE_PALM_SAPLING = registerBlock("potted_white_palm_sapling", flowerPot(ModNatureBlocks.WHITE_PALM_SAPLING));
    public static final Block POTTED_PINE_SAPLING       = registerBlock("potted_pine_sapling", flowerPot(ModNatureBlocks.PINE_SAPLING));
    public static final Block POTTED_BLACK_PINE_SAPLING = registerBlock("potted_black_pine_sapling", flowerPot(ModNatureBlocks.BLACK_PINE_SAPLING));
    public static final Block POTTED_WILLOW_SAPLING     = registerBlock("potted_willow_sapling", flowerPot(ModNatureBlocks.WILLOW_SAPLING));

    public static final Block POTTED_GREEN_SHRUB     = registerBlock("potted_green_shrub", flowerPot(ModNatureBlocks.GREEN_SHRUB));
    public static final Block POTTED_MALLOS         = registerBlock("potted_mallos", flowerPot(ModNatureBlocks.MALLOS));
    public static final Block POTTED_YELLOW_FLOWER     = registerBlock("potted_yellow_flower", flowerPot(ModNatureBlocks.YELLOW_FLOWER));
    public static final Block POTTED_YELLOW_TROLLIUS     = registerBlock("potted_yellow_trollius", flowerPot(ModNatureBlocks.YELLOW_TROLLIUS));
    public static final Block POTTED_TAN_SHRUB      = registerBlock("potted_tan_shrub", flowerPot(ModNatureBlocks.TAN_SHRUB));
    public static final Block POTTED_GREEN_JEWEL_CORNFLOWER     = registerBlock("potted_green_jewel_cornflower", flowerPot(ModNatureBlocks.GREEN_JEWEL_CORNFLOWER));
    public static final Block POTTED_SCORCHED_SHRUB     = registerBlock("potted_scorched_shrub", flowerPot(ModNatureBlocks.SCORCHED_SHRUB));
    public static final Block POTTED_FROZEN_SHRUB     = registerBlock("potted_frozen_shrub", flowerPot(ModNatureBlocks.FROZEN_SHRUB));
    
    public static final Block POTTED_CAVE_AMANITA = registerBlock("potted_cave_amanita", flowerPot(ModNatureBlocks.CAVE_AMANITA));
    public static final Block POTTED_DEEP_FIRECAP = registerBlock("potted_deep_firecap", flowerPot(ModNatureBlocks.DEEP_FIRECAP));
    public static final Block POTTED_GHOSTSHROOM = registerBlock("potted_ghostshroom", flowerPot(ModNatureBlocks.GHOSTSHROOM));
    public static final Block POTTED_MORSEL     = registerBlock("potted_morsel", flowerPot(ModNatureBlocks.MORSEL));
    public static final Block POTTED_SKYFIRECAP = registerBlock("potted_sky_firecap", flowerPot(ModNatureBlocks.SKY_FIRECAP));
    public static final Block POTTED_TRUMPET_SHROOM = registerBlock("potted_trumpet_shroom", flowerPot(ModNatureBlocks.TRUMPET_SHROOM));
    public static final Block POTTED_TUBESHROOM = registerBlock("potted_tubeshroom", flowerPot(ModNatureBlocks.TUBESHRROM));
    public static final Block POTTED_VIOLET_CAPS = registerBlock("potted_violet_caps", flowerPot(ModNatureBlocks.VIOLET_CAPS));
    public static final Block POTTED_WHITE_MUSHROOM = registerBlock("potted_white_mushroom", flowerPot(ModNatureBlocks.WHITE_MUSHROOM));
    public static final Block POTTED_YELLOW_AMANITA = registerBlock("potted_yellow_amanita", flowerPot(ModNatureBlocks.YELLOW_AMANITA));

    public static final Block BROWN_JUG = registerBlockWithItem("brown_jug",
            new JugBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).noOcclusion()));
    public static final Block GRAY_POT = registerBlockWithItem("gray_pot",
            new JugBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).noOcclusion()));
    public static final Block LARGE_JUG = registerBlockWithItem("large_jug",
            new JugBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).noOcclusion()));

    public static final Block AMPHORA = registerBlockWithItem("amphora",
            new AmphoraBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).noOcclusion()));
    public static final Block BROWN_AMPHORA = registerBlockWithItem("brown_amphora",
            new AmphoraBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).noOcclusion()));
    public static final Block GRAY_VASE = registerBlockWithItem("gray_vase",
            new AmphoraBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).noOcclusion()));

    public static final Block BROWN_JAR = registerBlockWithItem("brown_jar",
            new JarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).noOcclusion()));
    public static final Block CLAY_JAR = registerBlockWithItem("clay_jar",
            new JarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).noOcclusion()));
    public static final Block GRAY_JAR = registerBlockWithItem("gray_jar",
            new JarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).noOcclusion()));

    public static final Block BROWN_FAT_POT = registerBlockWithItem("brown_fat_pot",
            new FatPotBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).noOcclusion()));
    public static final Block FAT_POT = registerBlockWithItem("fat_pot",
            new FatPotBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).noOcclusion()));
    public static final Block GRAY_FAT_POT = registerBlockWithItem("gray_fat_pot",
            new FatPotBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).noOcclusion()));
    public static final Block POT_OF_GOLD = registerBlockWithItem("pot_of_gold",
            new FatPotBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).noOcclusion()));

    public static final Block GOLDEN_CHALICE = registerBlockWithItem("golden_chalice",
            new ChaliceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK).noOcclusion()));

    public static final Block COPPER_TREASURE_HEAP_LAYER = registerBlockWithItem("copper_treasure_heap_layer",
            new LayersAltBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK).noOcclusion()));
    public static final Block SILVER_TREASURE_HEAP_LAYER = registerBlockWithItem("silver_treasure_heap_layer",
            new LayersAltBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK).noOcclusion()));
    public static final Block GOLD_TREASURE_HEAP_LAYER = registerBlockWithItem("gold_treasure_heap_layer",
            new LayersAltBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK).noOcclusion()));

    public static final Block COPPER_COIN_PILE = registerBlockWithItem("copper_coin_pile",
            new CoinPileBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK).noCollission().noOcclusion()));
    public static final Block SILVER_COIN_PILE = registerBlockWithItem("silver_coin_pile",
            new CoinPileBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK).noCollission().noOcclusion()));
    public static final Block GOLD_COIN_PILE = registerBlockWithItem("gold_coin_pile",
            new CoinPileBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK).noCollission().noOcclusion()));

    public static final Block TREATED_STEEL_ROD = registerBlockWithItem("treated_steel_rod",
            new DecorativeRodBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion().requiresCorrectToolForDrops()));

    public static final Block ROPE = registerBlockWithItem("rope",
            new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).noCollission()));

    public static final Block BRONZE_CHAIN = registerBlockWithItem("bronze_chain",
            new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));
    public static final Block BRONZE_BROAD_CHAIN = registerBlockWithItem("bronze_broad_chain",
            new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));
    public static final Block SPIKY_CHAIN = registerBlockWithItem("spiky_chain",
            new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));

    public static final Block CHIMNEY = registerBlockWithItem("chimney",
            new ChimneyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).requiresCorrectToolForDrops()));

    public static final Block BIG_BRAZIER = registerBlockWithItem("big_brazier",
            new BrazierBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).lightLevel(createLightLevelFromLitBlockState(15)).noOcclusion().requiresCorrectToolForDrops()));
    public static final Block SMALL_BRAZIER = registerBlockWithItem("small_brazier",
            new SmallBrazierBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).lightLevel(createLightLevelFromLitBlockState(15)).noOcclusion().requiresCorrectToolForDrops()));

    public static final Block GILDED_BIG_BRAZIER = registerBlockWithItem("gilded_big_brazier",
            new GildedBrazierBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).lightLevel(createLightLevelFromLitBlockState(15)).noOcclusion().requiresCorrectToolForDrops()));
    public static final Block GILDED_SMALL_BRAZIER = registerBlockWithItem("gilded_small_brazier",
            new GildedSmallBrazierBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).lightLevel(createLightLevelFromLitBlockState(15)).noOcclusion().requiresCorrectToolForDrops()));

    public static final Block FIRE_BOWL = registerBlockWithItem("fire_bowl",
            new FireBowlBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).lightLevel(createLightLevelFromLitBlockState(15)).noOcclusion().requiresCorrectToolForDrops()));

    public static final Block BONFIRE = registerBlockWithItem("bonfire",
            new BonfireBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAMPFIRE).lightLevel(createLightLevelFromLitBlockState(15)).noOcclusion().requiresCorrectToolForDrops()));

    public static final Block SCONCE = registerBlock("sconce",
            new METorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).lightLevel(createLightLevelFromLitBlockState(15)).noOcclusion().requiresCorrectToolForDrops()));
    public static final Block WALL_SCONCE = registerBlock("wall_sconce",
            new MEWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).lightLevel(createLightLevelFromLitBlockState(15)).noOcclusion().requiresCorrectToolForDrops()));

    public static final Block GILDED_SCONCE = registerBlock("gilded_sconce",
            new METorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).lightLevel(createLightLevelFromLitBlockState(15)).noOcclusion().requiresCorrectToolForDrops()));
    public static final Block GILDED_WALL_SCONCE = registerBlock("gilded_wall_sconce",
            new MEWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).lightLevel(createLightLevelFromLitBlockState(15)).noOcclusion().requiresCorrectToolForDrops()));

    public static final Block ORCISH_SCONCE = registerBlock("orcish_sconce",
            new OrcSconceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).lightLevel(createLightLevelFromLitBlockState(15)).noOcclusion().requiresCorrectToolForDrops()));
    public static final Block ORCISH_WALL_SCONCE = registerBlock("orcish_wall_sconce",
            new MEWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).lightLevel(createLightLevelFromLitBlockState(15)).noOcclusion().requiresCorrectToolForDrops()));

    public static final Block GROUND_BOOK = registerBlockWithItem("ground_book",
            new GroundBookBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().noCollission()));
    public static final Block DWARVEN_GROUND_BOOK = registerBlockWithItem("dwarven_ground_book",
            new DwarvenGroundBookBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().noCollission()));

    public static final Block OAK_LADDER = registerBlockWithItem("oak_ladder",
            new ThickLadderBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB).sound(SoundType.LADDER).ignitedByLava()));
    public static final Block SPRUCE_LADDER = registerBlockWithItem("spruce_ladder",
            new ThickLadderBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SLAB).sound(SoundType.LADDER).ignitedByLava()));
    public static final Block BIRCH_LADDER = registerBlockWithItem("birch_ladder",
            new ThickLadderBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_SLAB).sound(SoundType.LADDER).ignitedByLava()));
    public static final Block JUNGLE_LADDER = registerBlockWithItem("jungle_ladder",
            new ThickLadderBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_SLAB).sound(SoundType.LADDER).ignitedByLava()));
    public static final Block ACACIA_LADDER = registerBlockWithItem("acacia_ladder",
            new ThickLadderBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SLAB).sound(SoundType.LADDER).ignitedByLava()));
    public static final Block DARK_OAK_LADDER = registerBlockWithItem("dark_oak_ladder",
            new ThickLadderBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_SLAB).sound(SoundType.LADDER).ignitedByLava()));
    public static final Block MANGROVE_LADDER = registerBlockWithItem("mangrove_ladder",
            new ThickLadderBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_SLAB).sound(SoundType.LADDER).ignitedByLava()));
    public static final Block CHERRY_LADDER = registerBlockWithItem("cherry_ladder",
            new ThickLadderBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SLAB).sound(SoundType.LADDER).ignitedByLava()));
    public static final Block BAMBOO_LADDER = registerBlockWithItem("bamboo_ladder",
            new ThickLadderBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_SLAB).sound(SoundType.WOOL).ignitedByLava()));
    public static final Block CRIMSON_LADDER = registerBlockWithItem("crimson_ladder",
            new ThickLadderBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SLAB).sound(SoundType.LADDER).ignitedByLava()));
    public static final Block WARPED_LADDER = registerBlockWithItem("warped_ladder",
            new ThickLadderBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_SLAB).sound(SoundType.LADDER).ignitedByLava()));

    public static final Block TREATED_WOOD_LADDER = registerBlockWithItem("treated_wood_ladder",
            new ThickLadderBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.LADDER).ignitedByLava()));

    public static final Block ROPE_LADDER = registerBlockWithItem("rope_ladder",
            new ThickLadderBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).sound(SoundType.WOOL).ignitedByLava()));

    public static final Block TALL_BLACK_PINE_DOOR = registerDoorBlock("tall_black_pine_door",
            new LargeDoor3x1(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));

    public static final Block OAK_STABLE_DOOR = registerDoorBlock("oak_stable_door",
            new LargeDoor4x2(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block REINFORCED_BLACK_PINE_DOOR = registerDoorBlock("reinforced_black_pine_door",
            new LargeDoor4x2(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block REINFORCED_SPRUCE_DOOR = registerDoorBlock("reinforced_spruce_door",
            new LargeDoor4x2(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block SIMPLE_LARCH_GATE = registerDoorBlock("simple_larch_gate",
            new LargeDoor4x2(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block RICKETY_SIMPLE_LARCH_DOOR = registerDoorBlock("rickety_simple_larch_door",
            new LargeDoor4x2(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block SPRUCE_STABLE_DOOR = registerDoorBlock("spruce_stable_door",
            new LargeDoor4x2(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));

    public static final Block LARGE_STURDY_DOOR = registerDoorBlock("large_sturdy_door",
            new LargeDoor5x3(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));

    public static final Block LARCH_HOBBIT_DOOR = registerDoorBlock("larch_hobbit_door",
            new LargeDoor2x2(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block SPRUCE_HOBBIT_DOOR = registerDoorBlock("spruce_hobbit_door",
            new LargeDoor2x2(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));

    public static final Block BLUE_HOBBIT_DOOR = registerDoorBlock("blue_hobbit_door",
            new LargeDoor2x2(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block GREEN_HOBBIT_DOOR = registerDoorBlock("green_hobbit_door",
            new LargeDoor2x2(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block LIGHT_BLUE_HOBBIT_DOOR = registerDoorBlock("light_blue_hobbit_door",
            new LargeDoor2x2(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block RED_HOBBIT_DOOR = registerDoorBlock("red_hobbit_door",
            new LargeDoor2x2(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block YELLOW_HOBBIT_DOOR = registerDoorBlock("yellow_hobbit_door",
            new LargeDoor2x2(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));

    public static final Block GREAT_GONDORIAN_GATE = registerDoorBlock("great_gondorian_gate",
            new LargeDoor10x5(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_DOOR)));

    public static final Block GREAT_DWARVEN_GATE = registerDoorBlock("great_dwarven_gate",
            new LargeDoor5x2(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_DOOR)));
    public static final Block HIDDEN_DWARVEN_DOOR = registerDoorBlock("hidden_dwarven_door",
            new LargeThickDoor3x2(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final Block VARNISHED_DWARVEN_DOOR = registerDoorBlock("varnished_dwarven_door",
            new LargeDoor4x2(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_DOOR)));
    public static final Block RUINED_DWARVEN_DOOR = registerDoorBlock("ruined_dwarven_door",
            new LargeDoor4x2(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_DOOR)));

    public static final Block GREAT_ELVEN_GATE = registerDoorBlock("great_elven_gate",
            new LargeDoor6x2(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));

    public static final Block GREAT_ORCISH_GATE = registerDoorBlock("great_orcish_gate",
            new LargeDoor10x4(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_DOOR)));

    public static final Block FANCY_BED = registerBlockWithItem("fancy_bed",
            new CustomBedBlock(DyeColor.BLACK, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_BED)));
    public static final Block FUR_BED = registerBlockWithItem("fur_bed",
            new CustomBedBlock(DyeColor.BLACK, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_BED)));
    public static final Block STRAW_BED = registerBlockWithItem("straw_bed",
            new CustomBedBlock(DyeColor.BLACK, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_BED)));

    public static final Block ARKENSTONE = registerBlock("arkenstone",
            new ArkenstoneBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).lightLevel((state -> 7)).noOcclusion().requiresCorrectToolForDrops()));
    public static final Block WALL_ARKENSTONE = registerBlock("wall_arkenstone",
            new ArkenstoneWallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).lightLevel((state -> 7)).noOcclusion().requiresCorrectToolForDrops()));

    public static Block registerBlock(String name, Block block) {
        ResourceLocation identifier = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, name);
        return NeoForgeRegistrationBridge.register(BuiltInRegistries.BLOCK, identifier, block);
    }

    public static Block registerBlockWithItem(String name, Block block) {
        ResourceLocation identifier = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, name);
        ModBlocks.registerBlockItem(name, block);
        ModItemGroups.DECORATIVES_BLOCKS_CONTENT.add(block.asItem().getDefaultInstance());
        return NeoForgeRegistrationBridge.register(BuiltInRegistries.BLOCK, identifier, block);
    }

    public static Block registerDoorBlock(String name, Block block) {
        return NeoForgeRegistrationBridge.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, name), block);
    }

    private static Block flowerPot(Block plant) {
        return new FlowerPotBlock(plant, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).noOcclusion());
    }

    private static ToIntFunction<BlockState> createLightLevelFromLitBlockState(int litLevel) {
        return (state) -> {
            return (Boolean) state.getValue(BlockStateProperties.LIT) ? litLevel : 0;
        };
    }

    public static void registerModBlocks() {
        LoggerUtil.logDebugMsg("Registering ModBlocks for " + MiddleEarth.MOD_ID);
    }
}
