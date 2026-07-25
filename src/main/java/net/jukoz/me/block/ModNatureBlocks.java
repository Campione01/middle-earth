package net.jukoz.me.block;

import net.jukoz.me.utils.NeoForgeRegistrationBridge;

import net.jukoz.me.block.special.saplings.VariantSaplingBlock;
import net.jukoz.me.datageneration.content.models.TintableCrossModel;
import net.jukoz.me.datageneration.content.tags.Saplings;
import net.jukoz.me.utils.LoggerUtil;
import net.jukoz.me.world.features.tree.ModTreeConfiguredFeatures;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.crop.*;
import net.jukoz.me.block.special.*;
import net.jukoz.me.item.utils.ModItemGroups;
import net.jukoz.me.world.features.tree.MushroomTreeConfiguredFeatures;
import net.minecraft.world.level.block.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.GlowLichenBlock;
import net.minecraft.world.level.block.HangingRootsBlock;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.MangroveRootsBlock;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.MapColor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static net.jukoz.me.block.WoodBlockSets.LEAVES_STRENGTH;

public class ModNatureBlocks {

    public static final Block MORGUL_IVY = registerBlock("morgul_ivy",
            new GlowLichenBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).replaceable().noCollission().strength(0.2f).sound(SoundType.GLOW_LICHEN)
                    .mapColor(DyeColor.GREEN).lightLevel(GlowLichenBlock.emission(5)).ignitedByLava()), true);
    public static final Block HANGING_COBWEB = registerBlock("hanging_cobweb",
            new HangingCobwebBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBWEB)), true);

    public static final Block CORNER_COBWEB = registerBlock("corner_cobweb",
            new CornerCobwebBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBWEB)), true);

    public static final Block CORRUPTED_MOSS_CARPET = registerBlock("corrupted_moss_carpet",
            new CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.1f).sound(SoundType.MOSS_CARPET)), true);

    public static final Block CORRUPTED_MOSS_BLOCK = registerBlock("corrupted_moss_block",
            new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.1f).sound(SoundType.MOSS)), true);

    public static final Block CORRUPTED_MOSS = registerBlock("corrupted_moss",
            new GlowLichenBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).replaceable().noCollission().strength(0.2f).sound(SoundType.GLOW_LICHEN)
                    .mapColor(DyeColor.GREEN).ignitedByLava()), true);

    public static final Block MOSS = registerBlock("moss",
            new GlowLichenBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).replaceable().noCollission().strength(0.2f).sound(SoundType.GLOW_LICHEN)
                    .mapColor(DyeColor.GREEN).ignitedByLava()), true);

    public static final Block FOREST_MOSS = registerBlock("forest_moss",
            new GlowLichenBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).replaceable().noCollission().strength(0.2f).sound(SoundType.GLOW_LICHEN)
                    .mapColor(DyeColor.GREEN).ignitedByLava()), true);

    public static final Block FOREST_MOSS_CARPET = registerBlock("forest_moss_carpet",
            new CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.1f).sound(SoundType.MOSS_CARPET)), true);

    public static final Block FOREST_MOSS_BLOCK = registerBlock("forest_moss_block",
            new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.1f).sound(SoundType.MOSS)), true);

    public static final Block OLD_PODZOL = registerBlock("old_podzol",
            new Block(BlockBehaviour.Properties.of().mapColor(MapColor.PODZOL).strength(0.5F).sound(SoundType.GRAVEL)), true);

    public static final Block LORIEN_PODZOL = registerBlock("lorien_podzol",
            new Block(BlockBehaviour.Properties.of().mapColor(MapColor.PODZOL).strength(0.5F).sound(SoundType.GRAVEL)), true);

    public static final Block BROWN_GRASS = registerBlock("brown_grass",
            new DesertPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);
    public static final Block DRY_GRASS = registerBlock("dry_grass",
            new DesertPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);
    public static final Block DYING_GRASS = registerBlock("dying_grass",
            new DesertPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);
    public static final Block GRIM_GRASS = registerBlock("grim_grass",
            new DesertPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);
    public static final Block TEMPERATE_GRASS = registerBlock("temperate_grass",
            new DesertPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);
    public static final Block GRASS_TUFT = registerBlock("grass_tuft",
            new CustomPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);
    public static final Block HEATHER = registerBlock("heather",
            new CustomPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);
    public static final Block RED_HEATHER = registerBlock("red_heather",
            new CustomPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);
    public static final Block DEAD_HEATHER = registerBlock("dead_heather",
            new DesertPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);
    public static final Block DRY_HEATHER = registerBlock("dry_heather",
            new DesertPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);
    public static final Block HEATH = registerBlock("heath",
            new CustomPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);

    public static final Block WHEATGRASS = registerBlock("wheatgrass",
            new CustomPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);
    public static final Block WILD_GRASS = registerBlock("wild_grass",
            new CustomPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);
    public static final Block WILDERGRASS = registerBlock("wildergrass",
            new CustomPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);

    public static final Block SEDUM = registerBlock("sedum",
            new CustomPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);
    public static final Block YELLOW_SEDUM = registerBlock("yellow_sedum",
            new CustomPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);

    public static final Block GREEN_SHRUB = registerBlock("green_shrub",
            new CustomPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);
    public static final Block SMALL_DRY_SHRUB = registerBlock("small_dry_shrub",
            new CustomPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);

    public static final Block BEACH_GRASS = registerBlock("beach_grass",
            new DesertPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);
    public static final Block COASTAL_PANIC_GRASS = registerBlock("coastal_panic_grass",
            new DesertPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);

    public static final Block SHRIVELED_SHRUB = registerBlock("shriveled_shrub",
            new DesertPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEAD_BUSH).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.BROWN)), true);

    public static final Block DEAD_RUSHES = registerBlock("dead_rushes",
            new DesertPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.BROWN)), true);
    public static final Block FALSE_OATGRASS = registerBlock("false_oatgrass",
            new DesertPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.BROWN)), true);
    public static final Block BRACKEN = registerBlock("bracken",
            new CustomPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
                    .mapColor(DyeColor.GREEN)), true);

    public static final Block MALLOS = registerBlock("mallos",
            new FlowerBlock(MobEffects.GLOWING, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak()
                    .sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).mapColor(DyeColor.GREEN)), true);
    public static final Block ELANOR = registerBlock("elanor",
            new FlowerBlock(MobEffects.GLOWING, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak()
                    .sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).mapColor(DyeColor.GREEN)), true);
    public static final Block TAN_SHRUB = registerBlock("tan_shrub",
            new DesertPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);
    public static final Block YELLOW_FLOWER = registerBlock("yellow_flower",
            new FlowerBlock(MobEffects.FIRE_RESISTANCE, 0, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)), true);
    public static final Block GREEN_JEWEL_CORNFLOWER = registerBlock("green_jewel_cornflower",
            new FlowerBlock(MobEffects.SATURATION, 0, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)), true);

    public static final Block LIGHT_BLUE_FLOWERS = registerBlock("light_blue_flowers",
            new FlowerBlock(MobEffects.SATURATION, 0, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)), true);
    public static final Block MAGENTA_FLOWERS = registerBlock("magenta_flowers",
            new FlowerBlock(MobEffects.SATURATION, 0, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)), true);
    public static final Block ORANGE_FLOWERS = registerBlock("orange_flowers",
            new FlowerBlock(MobEffects.SATURATION, 0, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)), true);
    public static final Block PINK_FLOWERS = registerBlock("pink_flowers",
            new FlowerBlock(MobEffects.SATURATION, 0, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)), true);
    public static final Block PURPLE_FLOWERS = registerBlock("purple_flowers",
            new FlowerBlock(MobEffects.SATURATION, 0, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)), true);
    public static final Block RED_FLOWERS = registerBlock("red_flowers",
            new FlowerBlock(MobEffects.SATURATION, 0, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)), true);
    public static final Block WHITE_FLOWERS = registerBlock("white_flowers",
            new FlowerBlock(MobEffects.SATURATION, 0, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)), true);
    public static final Block YELLOW_FLOWERS = registerBlock("yellow_flowers",
            new FlowerBlock(MobEffects.SATURATION, 0, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)), true);

    public static final Block LAVENDER = registerBlock("lavender",
            new FlowerBlock(MobEffects.SATURATION, 0, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)), true);
    public static final Block YELLOW_TROLLIUS = registerBlock("yellow_trollius",
            new FlowerBlock(MobEffects.SATURATION, 0, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)), true);

    public static final Block HOROKAKA = registerBlock("horokaka",
            new DesertPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.NONE)
                    .mapColor(DyeColor.GREEN)), true);
    public static final Block GIANT_HOROKAKA = registerBlock("giant_horokaka",
            new DesertPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.NONE)
                    .mapColor(DyeColor.GREEN)), true);

    public static final Block AZALEA_FLOWER_GROWTH = registerBlock("azalea_flower_growth",
            new GlowLichenBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).replaceable().noCollission().strength(0.2f).sound(SoundType.GLOW_LICHEN)
                    .mapColor(DyeColor.GREEN).ignitedByLava()), true);
    public static final Block DRY_GROWTH = registerBlock("dry_growth",
            new GlowLichenBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).replaceable().noCollission().strength(0.2f).sound(SoundType.GLOW_LICHEN)
                    .mapColor(DyeColor.GREEN).ignitedByLava()), true);
    public static final Block GREEN_GROWTH = registerBlock("green_growth",
            new GlowLichenBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).replaceable().noCollission().strength(0.2f).sound(SoundType.GLOW_LICHEN)
                    .mapColor(DyeColor.GREEN).ignitedByLava()), true);
    public static final Block IVY_GROWTH = registerBlock("ivy_growth",
            new GlowLichenBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).replaceable().noCollission().strength(0.2f).sound(SoundType.GLOW_LICHEN)
                    .mapColor(DyeColor.GREEN).ignitedByLava()), true);
    public static final Block LILAC_FLOWER_GROWTH = registerBlock("lilac_flower_growth",
            new GlowLichenBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).replaceable().noCollission().strength(0.2f).sound(SoundType.GLOW_LICHEN)
                    .mapColor(DyeColor.GREEN).ignitedByLava()), true);
    public static final Block PINK_FLOWER_GROWTH = registerBlock("pink_flower_growth",
            new GlowLichenBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).replaceable().noCollission().strength(0.2f).sound(SoundType.GLOW_LICHEN)
                    .mapColor(DyeColor.GREEN).ignitedByLava()), true);
    public static final Block RED_FLOWER_GROWTH = registerBlock("red_flower_growth",
            new GlowLichenBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).replaceable().noCollission().strength(0.2f).sound(SoundType.GLOW_LICHEN)
                    .mapColor(DyeColor.GREEN).ignitedByLava()), true);
    public static final Block WHITE_FLOWER_GROWTH = registerBlock("white_flower_growth",
            new GlowLichenBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).replaceable().noCollission().strength(0.2f).sound(SoundType.GLOW_LICHEN)
                    .mapColor(DyeColor.GREEN).ignitedByLava()), true);
    public static final Block YELLOW_FLOWER_GROWTH = registerBlock("yellow_flower_growth",
            new GlowLichenBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).replaceable().noCollission().strength(0.2f).sound(SoundType.GLOW_LICHEN)
                    .mapColor(DyeColor.GREEN).ignitedByLava()), true);

    public static final Block SHORT_CATTAILS = registerBlock("short_cattails",
            new WaterloggablePlant(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);
    public static final Block SHORT_BULRUSH = registerBlock("short_bulrush",
            new WaterloggablePlant(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);

    public static final Block TALL_CATTAILS = registerBlock("tall_cattails",
            new CustomWaterloggableTallPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS).instabreak(), false), true);
    public static final Block TALL_BULRUSH = registerBlock("tall_bulrush",
            new CustomWaterloggableTallPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS).instabreak(), false), true);

    public static final Block SMALL_LILY_PADS = registerBlock("small_lily_pads",
            new WaterlilyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LILY_PAD).noCollission()), false);
    public static final Block SMALL_FLOWERING_LILY_PADS = registerBlock("small_flowering_lily_pads",
            new WaterlilyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LILY_PAD).noCollission()), false);
    public static final Block LILY_PADS = registerBlock("lily_pads",
            new WaterlilyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LILY_PAD).noCollission()), false);
    public static final Block DUCKWEED = registerBlock("duckweed",
            new WaterlilyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LILY_PAD).noCollission()), false);

    public static final Block FROZEN_GRASS = registerBlock("frozen_grass",
            new DesertPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);
    public static final Block FROZEN_TUFT = registerBlock("frozen_tuft",
            new CustomPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);
    public static final Block FROZEN_SHRUB = registerBlock("frozen_shrub",
            new CustomPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);

    public static final Block FROZEN_GROWTH = registerBlock("frozen_growth",
            new GlowLichenBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).replaceable().noCollission().strength(0.2f).sound(SoundType.GLOW_LICHEN)
                    .mapColor(DyeColor.GREEN).ignitedByLava()), true);

    public static final Block STICKY_SNOW = registerBlock("sticky_snow",
            new GlowLichenBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).replaceable().noCollission()
                    .strength(0.2f).sound(SoundType.SNOW)), true);
    public static final Block STICKY_ICE = registerBlock("sticky_ice",
            new GlowLichenBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).replaceable().noCollission()
                    .strength(0.2f).sound(SoundType.GLASS)), true);

    public static final Block SHORT_ICICLES = registerBlock("short_icicles",
            new ShortIciclesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).noCollission()
                    .strength(0.2f).sound(SoundType.GLASS)), true);
    public static final Block DROOPING_ICICLES = registerBlock("drooping_icicles",
            new DroopingIciclesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).noCollission()
                    .strength(0.2f).sound(SoundType.GLASS)), true);

    public static final Block FLOATING_ICE = registerBlock("floating_ice",
            new FloatingIceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ICE)), false);

    public static final Block SCORCHED_GRASS = registerBlock("scorched_grass",
            new DesertPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);
    public static final Block SCORCHED_TUFT = registerBlock("scorched_tuft",
            new CustomPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);
    public static final Block SCORCHED_SHRUB = registerBlock("scorched_shrub",
            new CustomPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .mapColor(DyeColor.GREEN)), true);

    public static final Block BROWN_BOLETE = registerBlock("brown_bolete",
            new MushroomBlock(MushroomTreeConfiguredFeatures.BROWN_BOLETTE_TREE_KEY, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)), true);
    public static final Block CAVE_AMANITA = registerBlock("cave_amanita",
            new MushroomBlock(MushroomTreeConfiguredFeatures.CAVE_AMANITA_TREE_KEY, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)), true);
    public static final Block DEEP_FIRECAP = registerBlock("deep_firecap",
            new MushroomBlock(MushroomTreeConfiguredFeatures.DEEP_FIRECAP_TREE_KEY, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)), true);
    public static final Block GHOSTSHROOM = registerBlock("ghostshroom",
            new MushroomBlock(null, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM).lightLevel(state -> 4)), true);
    public static final Block MORSEL = registerBlock("morsel",
            new MushroomBlock(null, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)), true);
    public static final Block SKY_FIRECAP = registerBlock("sky_firecap",
            new MushroomBlock(MushroomTreeConfiguredFeatures.SKY_FIRECAP_TREE_KEY, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)), true);
    public static final Block TRUMPET_SHROOM = registerBlock("trumpet_shroom",
            new MushroomBlock(null, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)), true);
    public static final Block TALL_TRUMPET_SHROOM = registerBlock("tall_trumpet_shroom",
            new TallMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)), true);
    public static final Block TUBESHRROM = registerBlock("tubeshroom",
            new MushroomBlock(null, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM).lightLevel(state -> 4)), true);
    public static final Block TALL_TUBESHROOM = registerBlock("tall_tubeshroom",
            new TallMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM).lightLevel(state -> 5)), true);
    public static final Block VIOLET_CAPS = registerBlock("violet_caps",
            new MushroomBlock(null, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)), true);
    public static final Block WHITE_MUSHROOM = registerBlock("white_mushroom",
            new MushroomBlock(null, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)), true);
    public static final Block YELLOW_AMANITA = registerBlock("yellow_amanita",
            new MushroomBlock(MushroomTreeConfiguredFeatures.YELLOW_AMANITA_TREE_KEY, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)), true);

    public static final Block BROWN_BOLETE_TILLER = registerBlock("brown_bolete_tiller",
            new FlowerbedMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)), true);
    public static final Block CAVE_AMANITA_TILLER = registerBlock("cave_amanita_tiller",
            new FlowerbedMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)), true);
    public static final Block DEEP_FIRECAP_TILLER = registerBlock("deep_firecap_tiller",
            new FlowerbedMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)), true);
    public static final Block GHOSTSHROOM_TILLER = registerBlock("ghostshroom_tiller",
            new FlowerbedMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM).lightLevel(state -> 4)), true);
    public static final Block MORSEL_TILLER = registerBlock("morsel_tiller",
            new FlowerbedMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)), true);
    public static final Block SKY_FIRECAP_TILLER = registerBlock("sky_firecap_tiller",
            new FlowerbedMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)), true);
    public static final Block VIOLET_CAPS_TILLER = registerBlock("violet_caps_tiller",
            new FlowerbedMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)), true);
    public static final Block WHITE_MUSHROOM_TILLER = registerBlock("white_mushroom_tiller",
            new FlowerbedMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)), true);
    public static final Block YELLOW_AMANITA_TILLER = registerBlock("yellow_amanita_tiller",
            new FlowerbedMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)), true);

    public static final Block BROWN_BOLETE_BLOCK = registerBlock("brown_bolete_block",
            new HugeMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM_BLOCK)), true);
    public static final Block CAVE_AMANITA_BLOCK = registerBlock("cave_amanita_block",
            new HugeMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM_BLOCK)), true);
    public static final Block DEEP_FIRECAP_BLOCK = registerBlock("deep_firecap_block",
            new HugeMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM_BLOCK)), true);
    public static final Block SKY_FIRECAP_BLOCK = registerBlock("sky_firecap_block",
            new HugeMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM_BLOCK)), true);
    public static final Block YELLOW_AMANITA_BLOCK = registerBlock("yellow_amanita_block",
            new HugeMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM_BLOCK)), true);

    public static final Block BEECH_SAPLING = registerSimpleSapling("beech_sapling", ModTreeConfiguredFeatures.BEECH_TREE_KEY);
    public static final Block CHESTNUT_SAPLING = registerSimpleSapling("chestnut_sapling", ModTreeConfiguredFeatures.CHESTNUT_TREE_KEY);
    public static final Block HOLLY_SAPLING = registerSimpleSapling("holly_sapling", ModTreeConfiguredFeatures.HOLLY_TREE_KEY);
    public static final Block FIR_SAPLING = registerSimpleSapling("fir_sapling", ModTreeConfiguredFeatures.FIR_TREE_KEY);
    public static final Block LARCH_SAPLING = registerSimpleSapling("larch_sapling", ModTreeConfiguredFeatures.LARCH_TREE_KEY);
    public static final Block LEBETHRON_SAPLING = registerSimpleSapling("lebethron_sapling", ModTreeConfiguredFeatures.BLACK_LEBETHRON_TREE_KEY);
    public static final Block WHITE_LEBETHRON_SAPLING = registerSimpleSapling("white_lebethron_sapling", ModTreeConfiguredFeatures.WHITE_LEBETHRON_TREE_KEY);
    public static final Block MALLORN_SAPLING = registerSimpleSapling("mallorn_sapling", ModTreeConfiguredFeatures.MEGA_MALLORN_TREE_KEY);
    public static final Block MAPLE_SAPLING = registerVariantSapling("maple_sapling",
            List.of(ModTreeConfiguredFeatures.MAPLE_TREE_KEY, ModTreeConfiguredFeatures.YELLOW_MAPLE_TREE_KEY, ModTreeConfiguredFeatures.ORANGE_MAPLE_TREE_KEY, ModTreeConfiguredFeatures.RED_MAPLE_TREE_KEY));
    public static final Block SILVER_MAPLE_SAPLING = registerVariantSapling("silver_maple_sapling",
            List.of(ModTreeConfiguredFeatures.SILVER_MAPLE_TREE_KEY, ModTreeConfiguredFeatures.SILVER_YELLOW_MAPLE_TREE_KEY, ModTreeConfiguredFeatures.SILVER_ORANGE_MAPLE_TREE_KEY, ModTreeConfiguredFeatures.SILVER_RED_MAPLE_TREE_KEY));
    public static final Block MIRKWOOD_SAPLING = registerSimpleSapling("mirkwood_sapling", ModTreeConfiguredFeatures.SMALL_MIRKWOOD_TREE_KEY);
    public static final Block PALM_SAPLING = registerSimpleSapling("palm_sapling", ModTreeConfiguredFeatures.PALM_TREE_KEY);
    public static final Block WHITE_PALM_SAPLING = registerSimpleSapling("white_palm_sapling", ModTreeConfiguredFeatures.WHITE_PALM_TREE_KEY);
    public static final Block PINE_SAPLING = registerSimpleSapling("pine_sapling", ModTreeConfiguredFeatures.PINE_TREE_KEY);
    public static final Block BLACK_PINE_SAPLING = registerSimpleSapling("black_pine_sapling", ModTreeConfiguredFeatures.BLACK_PINE_TREE_KEY);
    public static final Block WILLOW_SAPLING = registerSimpleSapling("willow_sapling", ModTreeConfiguredFeatures.WILLOW_TREE_KEY);

    public static final Block LEBETHRON_LEAVES = registerBlock("lebethron_leaves",
            new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(LEAVES_STRENGTH).sound(SoundType.GRASS).ignitedByLava()), true);

    public static final Block BERRY_HOLLY_LEAVES = registerBlock("berry_holly_leaves",
            new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(LEAVES_STRENGTH).sound(SoundType.GRASS).ignitedByLava()), true);

    public static final Block DRY_LARCH_LEAVES = registerBlock("dry_larch_leaves",
            new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(LEAVES_STRENGTH).sound(SoundType.GRASS).ignitedByLava()), true);

    public static final Block FLOWERING_MALLORN_LEAVES = registerBlock("flowering_mallorn_leaves",
            new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(LEAVES_STRENGTH).sound(SoundType.GRASS)), true);

    public static final Block MAPLE_LEAVES = registerBlock("maple_leaves",
            new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(LEAVES_STRENGTH).sound(SoundType.GRASS).ignitedByLava()), true);
    public static final Block ORANGE_MAPLE_LEAVES = registerBlock("orange_maple_leaves",
            new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(LEAVES_STRENGTH).sound(SoundType.GRASS).ignitedByLava()), true);
    public static final Block RED_MAPLE_LEAVES = registerBlock("red_maple_leaves",
            new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(LEAVES_STRENGTH).sound(SoundType.GRASS).ignitedByLava()), true);
    public static final Block YELLOW_MAPLE_LEAVES = registerBlock("yellow_maple_leaves",
            new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(LEAVES_STRENGTH).sound(SoundType.GRASS).ignitedByLava()), true);

    public static final Block DRY_PINE_LEAVES = registerBlock("dry_pine_leaves",
            new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(LEAVES_STRENGTH).sound(SoundType.GRASS).ignitedByLava()), true);
    public static final Block PINE_BRANCHES = registerBlock("pine_branches",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(LEAVES_STRENGTH).sound(SoundType.GRASS).ignitedByLava()), true);

    public static final Block FALLEN_LEAVES = registerBlock("fallen_leaves",
            new FallenLeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.1f).sound(SoundType.MOSS_CARPET).noOcclusion().replaceable().noCollission()), true);
    public static final Block FALLEN_MALLORN_LEAVES = registerBlock("fallen_mallorn_leaves",
            new FallenLeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.1f).sound(SoundType.MOSS_CARPET).noOcclusion().replaceable().noCollission()), true);
    public static final Block FALLEN_MIRKWOOD_LEAVES = registerBlock("fallen_mirkwood_leaves",
            new FallenLeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.1f).sound(SoundType.MOSS_CARPET).noOcclusion().replaceable().noCollission()), true);

    public static final Block TOUGH_BERRY_BUSH = registerBlock("tough_berry_bush",
            new ToughBerryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH).randomTicks().noCollission().instabreak()
                    .sound(SoundType.SWEET_BERRY_BUSH)), false);
    public static final Block STRAWBERRY_BUSH = registerBlock("strawberry_bush",
            new StrawBerryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH).randomTicks().noCollission().instabreak().sound(SoundType.SWEET_BERRY_BUSH)), false);

    public static final Block WILD_WHEAT = registerCrossBlock("wild_wheat",
            new WildCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).sound(SoundType.CROP)), true);
    public static final Block TALL_WILD_WHEAT = registerBlock("tall_wild_wheat",
            new CustomTallPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).sound(SoundType.CROP), true), true);
    public static final Block WILD_PIPEWEED = registerBlock("wild_pipeweed",
            new CustomTallPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).sound(SoundType.CROP), true), true);
    public static final Block WILD_FLAX = registerCrossBlock("wild_flax",
            new WildCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).sound(SoundType.CROP)), true);
    public static final Block WILD_TOMATO = registerBlock("wild_tomato",
            new CustomTallPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).sound(SoundType.CROP), true), true);
    public static final Block WILD_BELL_PEPPER = registerCrossBlock("wild_bell_pepper",
            new WildCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).sound(SoundType.CROP)), true);
    public static final Block WILD_CUCUMBER = registerCrossBlock("wild_cucumber",
            new WildCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).sound(SoundType.CROP)), true);
    public static final Block WILD_GARLIC = registerCrossBlock("wild_garlic",
            new WildCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).sound(SoundType.CROP)), true);
    public static final Block WILD_ONION = registerCrossBlock("wild_onion",
            new WildCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).sound(SoundType.CROP)), true);
    public static final Block WILD_LETTUCE = registerCrossBlock("wild_lettuce",
            new WildCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).sound(SoundType.CROP)), true);
    public static final Block WILD_LEEK = registerCrossBlock("wild_leek",
            new WildCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).sound(SoundType.CROP)), true);
    public static final Block WILD_POTATO = registerBlock("wild_potato",
            new WildCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).sound(SoundType.CROP)), true);
    public static final Block WILD_CARROT = registerBlock("wild_carrot",
            new WildCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).sound(SoundType.CROP)), true);
    public static final Block WILD_BEETROOT = registerBlock("wild_beetroot",
            new WildCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).sound(SoundType.CROP)), true);

    public static final Block TOMATO_CROP = registerBlock("tomato_crop",
            new TomatoCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POTATOES)), false);
    public static final Block BELL_PEPPER_CROP = registerBlock("bell_pepper_crop",
            new BellpepperCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POTATOES)), false);
    public static final Block CUCUMBER_CROP = registerBlock("cucumber_crop",
            new CucumberCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POTATOES)), false);
    public static final Block FLAX_CROP = registerBlock("flax_crop",
            new FlaxCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)), false);
    public static final Block GARLIC_CROP = registerBlock("garlic_crop",
            new GarlicCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POTATOES)), false);
    public static final Block LEEK_CROP = registerBlock("leek_crop",
            new LeekCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POTATOES)), false);
    public static final Block LETTUCE_CROP = registerBlock("lettuce_crop",
            new LettuceCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POTATOES)), false);
    public static final Block ONION_CROP = registerBlock("onion_crop",
            new OnionCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POTATOES)), false);
    public static final Block PIPEWEED_CROP = registerBlock("pipeweed_crop",
            new PipeweedCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)), false);

    public static final Block MIRKWOOD_ROOTS = registerBlock("mirkwood_roots",
            new MangroveRootsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_ROOTS)), true);
    public static final Block MIRKWOOD_HANGING_ROOTS = registerBlock("mirkwood_hanging_roots",
            new HangingRootsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.HANGING_ROOTS)), true);

    public static final Block MIRKWOOD_VINES = registerCrossBlock("mirkwood_vines",
            new MirkwoodVinesBottomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WEEPING_VINES_PLANT)), true);
    public static final Block MIRKWOOD_VINES_PLANT = registerCrossBlock("mirkwood_vines_plant",
            new MirkwoodVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WEEPING_VINES)), false);

    public static final Block MIRKWOOD_SPIDER_EGG = registerBlock("mirkwood_spider_egg",
            new MirkwoodSpiderEggBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TURTLE_EGG)), true);

    public static final Block GLOWWORM_WEBBING = registerCrossBlock("glowworm_webbing",
            new GlowWormBottomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WEEPING_VINES_PLANT).lightLevel(state -> 6)), true);
    public static final Block GLOWWORM_MAIN = registerCrossBlock("glowworm_main",
            new GlowWormBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WEEPING_VINES).lightLevel(state -> 6)), false);

    public static Block registerCrossBlock(String name, Block block, boolean present) {
        Block resultBlock = registerBlock(name, block, present);
        TintableCrossModel.notTintedBlocks.add(resultBlock);
        return resultBlock;
    }

    public static Block registerSimpleSapling(String name, ResourceKey<ConfiguredFeature<?, ?>> treeFeature) {
        SaplingBlock saplingBlock = new SaplingBlock(new TreeGrower(name, Optional.empty(), Optional.ofNullable(treeFeature), Optional.empty()),
                BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
        Block resultBlock = registerBlock(name, saplingBlock, true);
        TintableCrossModel.notTintedBlocks.add(resultBlock);
        Saplings.saplings.add(resultBlock);
        return resultBlock;
    }

    public static Block registerVariantSapling(String name, List<ResourceKey<ConfiguredFeature<?, ?>>> treeFeatures) {
        List<TreeGrower> saplingGenerators = new ArrayList<>();
        for(ResourceKey<ConfiguredFeature<?,?>> treeFeature : treeFeatures) {
            saplingGenerators.add(new TreeGrower(name, Optional.empty(), Optional.ofNullable(treeFeature),
                            Optional.empty()));
        }

        SaplingBlock saplingBlock = new VariantSaplingBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), saplingGenerators);

        Block resultBlock = registerBlock(name, saplingBlock, true);
        TintableCrossModel.notTintedBlocks.add(resultBlock);
        return resultBlock;
    }

    public static Block registerBlock(String name, Block block, boolean present) {
        if(present) ModNatureBlocks.registerBlockItem(name, block);
        return NeoForgeRegistrationBridge.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, name), block);
    }

    static void registerBlockItem(String name, Block block) {
        var item =  NeoForgeRegistrationBridge.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, name),
                new BlockItem(block, new Item.Properties()));
        Item.BY_BLOCK.put(block, item);

        ModItemGroups.NATURE_BLOCKS_CONTENTS.add(item.getDefaultInstance());
    }

    public static void registerModBlocks() {
        LoggerUtil.logDebugMsg("Registering ModBlocks for " + MiddleEarth.MOD_ID);
    }
}
