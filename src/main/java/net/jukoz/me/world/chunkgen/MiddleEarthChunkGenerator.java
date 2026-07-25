package net.jukoz.me.world.chunkgen;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.jukoz.me.block.ModBlocks;
import net.jukoz.me.block.ModNatureBlocks;
import net.jukoz.me.block.StoneBlockSets;
import net.jukoz.me.config.ModServerConfigs;
import net.jukoz.me.utils.noises.BlendedNoise;
import net.jukoz.me.utils.noises.SimplexNoise;
import net.jukoz.me.world.biomes.BlocksLayeringData;
import net.jukoz.me.world.biomes.surface.*;
import net.jukoz.me.world.map.MiddleEarthMapConfigs;
import net.jukoz.me.world.map.MiddleEarthMapRuntime;
import net.jukoz.me.world.map.MiddleEarthMapUtils;
import net.jukoz.me.world.biomes.MEBiomeKeys;
import net.jukoz.me.world.chunkgen.map.MiddleEarthHeightMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.*;
import net.minecraft.world.level.chunk.*;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.RandomSupport;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.phys.Vec2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MiddleEarthChunkGenerator extends ChunkGenerator {
    public static final int MEDGON_LEVEL = -32;
    public static final int NURGON_LEVEL = 0;
    public static final int DEEPSLATE_LEVEL = 32;
    public static final int STONE_HEIGHT = 36;
    public static final int WATER_HEIGHT = 64;
    public static final int LAVA_HEIGHT = -60;
    public static final int HEIGHT = 27 + STONE_HEIGHT;
    public static final int DIRT_HEIGHT = 3 + HEIGHT;
    public static final int CAVE_NOISE = 5;

    MiddleEarthMapUtils middleEarthMapUtils;
    MiddleEarthMapRuntime middleEarthMapRuntime;

    public static final int mapMultiplier = (int) Math.pow(2, MiddleEarthMapConfigs.MAP_ITERATION + MiddleEarthMapConfigs.PIXEL_WEIGHT - 2);
    public static final Vec2 mountDoom = new Vec2(2131.5f, 1715.2f).scale(mapMultiplier);
    private static final int CAVE_STRETCH_H = 60;
    private static final int SPAGHETTI_CAVE_STRETCH_H = 90;
    private static final int CAVE_STRETCH_V = 50;

    public static final MapCodec<MiddleEarthChunkGenerator> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            instance.group(BiomeSource.CODEC.fieldOf("biome_source").forGetter(generator -> generator.getBiomeSource()))
                    .apply(instance, instance.stable(MiddleEarthChunkGenerator::new)));

    public MiddleEarthChunkGenerator(HolderGetter<Biome> biomeRegistry) {
        this(new ModBiomeSource(biomeRegistry));
    }

    public static List<Holder<Biome>> createBiomeList(HolderGetter<Biome> biomeRegistry) {
        return new ArrayList<>(Arrays.asList(
                    biomeRegistry.getOrThrow(MEBiomeKeys.OCEAN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ANDUIN_VALES),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ANDUIN_VALES_FOREST),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ANORIEN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ANORIEN_RIVERSIDE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ANORIEN_FOOTHILLS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.BARROW_DOWNS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.BELERIAND_ISLAND),
                    biomeRegistry.getOrThrow(MEBiomeKeys.BELFALAS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.BELFALAS_FOREST),
                    biomeRegistry.getOrThrow(MEBiomeKeys.BELFALAS_HILLS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.BELFALAS_BEACH),
                    biomeRegistry.getOrThrow(MEBiomeKeys.BLACKROOT_VALE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.BLACKROOT_FOREST),
                    biomeRegistry.getOrThrow(MEBiomeKeys.BLUE_MOUNTAINS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.BLUE_MOUNTAINS_FOOTHILLS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.BLUE_MOUNTAINS_BASE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.BLUE_MOUNTAINS_HIGH_LANDS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.BLUE_MOUNTAINS_PEAKS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.BLUE_MOUNTAINS_WOODS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.BROWN_LANDS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.CARADHRAS_BASE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.CARADHRAS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.CARADHRAS_PEAKS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.CELEBDIL_BASE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.CELEBDIL),
                    biomeRegistry.getOrThrow(MEBiomeKeys.CELEBDIL_PEAKS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.CORSAIR_COASTS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DALE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DALE_FOREST),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DALE_MEADOW),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DALE_CITY),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DAGORLAD),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DALE_RIVERSIDE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DARK_MIRKWOOD),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DARK_MIRKWOOD_EDGE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DARK_ANDUIN_VALES),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DEAD_MARSHES),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DEAD_MARSHES_WATER),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DESOLATED_LANDS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DOL_GULDUR),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DOL_GULDUR_HILL),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DORWINION),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DORWINION_LAVENDER_FIELD),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DORWINION_HILLS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DUNLAND_FOOTHILLS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DUNLAND_HILLS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.EAST_BIGHT),
                    biomeRegistry.getOrThrow(MEBiomeKeys.EASTERN_NURN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.EASTERN_RHOVANION),
                    biomeRegistry.getOrThrow(MEBiomeKeys.EASTERN_RHOVANION_FOREST),
                    biomeRegistry.getOrThrow(MEBiomeKeys.EMYN_MUIL),
                    biomeRegistry.getOrThrow(MEBiomeKeys.EMYN_MUIL_CLIFFS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.EMYN_MUIL_PEAKS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.EMYN_MUIL_POND),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ENEDWAITH),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ENEDWAITH_FIELD),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ENEDWAITH_WHEAT_FIELD),
                    biomeRegistry.getOrThrow(MEBiomeKeys.EREGION),
                    biomeRegistry.getOrThrow(MEBiomeKeys.EREGION_FOREST),
                    biomeRegistry.getOrThrow(MEBiomeKeys.EREGION_GLADE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ETHIR_ANDUIN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ETHIR_ANDUIN_RIVER_DELTA),
                    biomeRegistry.getOrThrow(MEBiomeKeys.FANGORN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.FANGORN_FOOTHILLS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.FANUIDHOL_BASE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.FANUIDHOL),
                    biomeRegistry.getOrThrow(MEBiomeKeys.FANUIDHOL_PEAKS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MIRKWOOD_RIVER),
                    biomeRegistry.getOrThrow(MEBiomeKeys.GREAT_RIVER),
                    biomeRegistry.getOrThrow(MEBiomeKeys.GUNDABAD_PLAINS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.FORODWAITH),
                    biomeRegistry.getOrThrow(MEBiomeKeys.FROZEN_OCEAN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.FROZEN_POND),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LINDON_SHORES_CLIFFS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LINDON_SHORES),
                    biomeRegistry.getOrThrow(MEBiomeKeys.GONDOR),
                    biomeRegistry.getOrThrow(MEBiomeKeys.GONDOR_FOREST),
                    biomeRegistry.getOrThrow(MEBiomeKeys.GONDOR_HILL),
                    biomeRegistry.getOrThrow(MEBiomeKeys.GORGOROTH),
                    biomeRegistry.getOrThrow(MEBiomeKeys.GORGOROTH_ASHEN_WOODS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.GORGOROTH_DELTA),
                    biomeRegistry.getOrThrow(MEBiomeKeys.GREY_MOUNTAINS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.GREY_MOUNTAINS_BASE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.GREY_MOUNTAINS_PEAKS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.GREY_ASHEN_WOODS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.GREY_PLAINS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.HARAD),
                    biomeRegistry.getOrThrow(MEBiomeKeys.HARAD_DESERT),
                    biomeRegistry.getOrThrow(MEBiomeKeys.HARAD_WOODS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.HARONDOR),
                    biomeRegistry.getOrThrow(MEBiomeKeys.HILLS_OF_EVENDIM),
                    biomeRegistry.getOrThrow(MEBiomeKeys.IRON_HILLS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.IRON_FOOTHILLS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.IRON_HILLS_BASE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.IRON_HILLS_PEAKS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.IRON_HILLS_PLAINS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NORTHERN_RHOVANION_FOREST),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NORTHERN_RHOVANION_HILLS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ISENGARD),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ISENGARD_HILL),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ITHILIEN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ITHILIEN_GLADE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ITHILIEN_WASTES),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ITHILIEN_WASTES_GLADE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LAMEDON),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LAMEDON_HILLS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LEBENNIN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LEBENNIN_HILLS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LEBENNIN_SHORES),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LINDON),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LINDON_FOREST),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LINDON_HIDDEN_BLOSSOM),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LINDON_MEADOW),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LONELY_MOUNTAIN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LONELY_MOUNTAIN_FOOTHILLS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LONELY_MOUNTAIN_BASE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LONELY_MOUNTAIN_PEAKS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LONELY_MOUNTAIN_TAIGA),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LONG_LAKE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LONG_LAKE_SHORES),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LONG_MARSHES),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LORIEN_EDGE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LOSSARNACH),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LOSSARNACH_CHERRY_BLOSSOM),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LOSSARNACH_VALLEY),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LOSSARNACH_VALLEY_RED),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LOSSARNACH_VALLEY_ORANGE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LOSSARNACH_VALLEY_YELLOW),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LOSSARNACH_VALLEY_GREEN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LOTHLORIEN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LOTHLORIEN_GLADE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LOTHLORIEN_BLOSSOM),
                    biomeRegistry.getOrThrow(MEBiomeKeys.OASIS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.POND),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MANGROVE_POND),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MINHIRIATH),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MINHIRIATH_WHEAT_FIELD),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MIRKWOOD),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MIRKWOOD_EDGE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MIRKWOOD_FOOTHILLS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MIRKWOOD_MOUNTAINS_BASE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MIRKWOOD_MOUNTAINS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MIRKWOOD_MOUNTAINS_PEAKS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MIRKWOOD_MARSHES),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MIRKWOOD_SWAMP),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MISTY_MOUNTAINS_BASE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MISTY_MOUNTAINS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MISTY_MOUNTAINS_PEAKS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MORDOR),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MORDOR_ASHEN_FOREST),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MORDOR_HILL),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ERED_LITHUI),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ERED_LITHUI_BASE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ERED_LITHUI_PEAKS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MORDOR_WASTES),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MORGUL_VALE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MORGUL_FOREST),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MORGUL_RIVER),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MOUNT_GUNDABAD_BASE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MOUNT_GUNDABAD),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MOUNT_GUNDABAD_PEAKS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MOUNT_DOOM_PIT),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MOUNT_DOOM),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NAN_CURUNIR),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NEN_HITHOEL),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NEN_HITHOEL_RAPIDS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NEN_HITHOEL_FOREST),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NEN_HITHOEL_SHORES),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NINDALF),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NORTH_DOWNS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NORTHERN_DUNLAND),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NORTHERN_DUNLAND_GLADE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NORTHERN_MIRKWOOD_MARSHES),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NORTHERN_MIRKWOOD_SWAMP),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NORTHERN_WASTELANDS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NURN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NURN_EDGE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NURN_EDGE_WOODS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NURN_FOREST),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NURN_HILL),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NURN_RIVER),
                    biomeRegistry.getOrThrow(MEBiomeKeys.NURN_SEA),
                    biomeRegistry.getOrThrow(MEBiomeKeys.OCEAN_COAST),
                    biomeRegistry.getOrThrow(MEBiomeKeys.OLD_ANGMAR),
                    biomeRegistry.getOrThrow(MEBiomeKeys.OLD_ANGMAR_FOREST),
                    biomeRegistry.getOrThrow(MEBiomeKeys.OLD_ANGMAR_COLD_HILL),
                    biomeRegistry.getOrThrow(MEBiomeKeys.OLD_ANGMAR_FROZEN_HILL),
                    biomeRegistry.getOrThrow(MEBiomeKeys.OLD_ARTHEDAIN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.OLD_ARTHEDAIN_FOREST),
                    biomeRegistry.getOrThrow(MEBiomeKeys.OLD_ARTHEDAIN_MEADOW),
                    biomeRegistry.getOrThrow(MEBiomeKeys.OLD_ARTHEDAIN_FOOTHILL),
                    biomeRegistry.getOrThrow(MEBiomeKeys.OLD_CARDOLAN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.OLD_CARDOLAN_FOREST),
                    biomeRegistry.getOrThrow(MEBiomeKeys.OLD_CARDOLAN_HILL),
                    biomeRegistry.getOrThrow(MEBiomeKeys.OLD_RHUDAUR),
                    biomeRegistry.getOrThrow(MEBiomeKeys.OLD_RHUDAUR_FOREST),
                    biomeRegistry.getOrThrow(MEBiomeKeys.OLD_RHUDAUR_HILL),
                    biomeRegistry.getOrThrow(MEBiomeKeys.OSGILIATH),
                    biomeRegistry.getOrThrow(MEBiomeKeys.PELENNOR_FIELDS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.PELENNOR_WHEAT_FIELD),
                    biomeRegistry.getOrThrow(MEBiomeKeys.RIVER),
                    biomeRegistry.getOrThrow(MEBiomeKeys.RHUN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.RHUN_FIELD),
                    biomeRegistry.getOrThrow(MEBiomeKeys.RHUN_FOREST),
                    biomeRegistry.getOrThrow(MEBiomeKeys.RHUN_HIDDEN_BLOSSOM),
                    biomeRegistry.getOrThrow(MEBiomeKeys.HIGH_MOOR),
                    biomeRegistry.getOrThrow(MEBiomeKeys.HIGH_MOOR_VALE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.HIGH_MOOR_HILLS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ROHAN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ROHAN_FOREST),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ROHAN_FIELD),
                    biomeRegistry.getOrThrow(MEBiomeKeys.SEA_OF_RHUN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.SARN_GEBIR_WILDLANDS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.SARN_GEBIR_SHORES),
                    biomeRegistry.getOrThrow(MEBiomeKeys.SHIRE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.SHIRE_EDGE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.SHIRE_FOREST),
                    biomeRegistry.getOrThrow(MEBiomeKeys.SHIRE_HILLS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.SHIRE_WOODS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.SOUTHEAST_RHOVANION),
                    biomeRegistry.getOrThrow(MEBiomeKeys.SOUTHEAST_RHOVANION_FIELD),
                    biomeRegistry.getOrThrow(MEBiomeKeys.SOUTHERN_DUNLAND),
                    biomeRegistry.getOrThrow(MEBiomeKeys.SOUTHERN_FOROCHEL),
                    biomeRegistry.getOrThrow(MEBiomeKeys.EPHEL_DUATH),
                    biomeRegistry.getOrThrow(MEBiomeKeys.EPHEL_DUATH_BASE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.EPHEL_DUATH_PEAKS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.THE_ANGLE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.THE_OLD_FOREST),
                    biomeRegistry.getOrThrow(MEBiomeKeys.THE_WOLD),
                    biomeRegistry.getOrThrow(MEBiomeKeys.THE_WOLD_WHEAT_FIELD),
                    biomeRegistry.getOrThrow(MEBiomeKeys.THE_WHITE_DOWNS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.TOLFALAS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.TOROGWAITH),
                    biomeRegistry.getOrThrow(MEBiomeKeys.TROLLSHAWS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.UDUN),
                    biomeRegistry.getOrThrow(MEBiomeKeys.UMBAR),
                    biomeRegistry.getOrThrow(MEBiomeKeys.UMBAR_WOODS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.WASTE_POND),
                    biomeRegistry.getOrThrow(MEBiomeKeys.WEBBED_WOODS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.WEBBED_DARK_WOODS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.WITHERED_HEATH),
                    biomeRegistry.getOrThrow(MEBiomeKeys.WHITE_MOUNTAINS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.WHITE_MOUNTAINS_BASE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.WHITE_MOUNTAINS_PEAKS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.WOODLAND_REALM),
                    biomeRegistry.getOrThrow(MEBiomeKeys.WOODLAND_FOOTHILLS),
                    biomeRegistry.getOrThrow(MEBiomeKeys.WOODLAND_GLADE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.WOODLAND_HILLS),

                    biomeRegistry.getOrThrow(MEBiomeKeys.BASIC_CAVE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LUSH_CAVE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DRIPSTONE_CAVE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DOLOMITE_CAVE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.GALONN_CAVE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.GILDED_CAVE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.IZHER_ABAN_CAVE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.LIMESTONE_CAVE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MOUNTAIN_CAVE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MUD_CAVE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.FUNGUS_CAVE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MITHRIL_CAVE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.BASALT_CAVE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.MAGMA_CAVE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.DRY_CAVE),
                    biomeRegistry.getOrThrow(MEBiomeKeys.ICE_CAVE)
                ));
    }

    public MiddleEarthChunkGenerator(BiomeSource biomeSource) {
        super(biomeSource);
        this.middleEarthMapUtils = MiddleEarthMapUtils.getInstance();
        this.middleEarthMapRuntime = MiddleEarthMapRuntime.getInstance();

    }

    @Override
    protected MapCodec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public void applyCarvers(WorldGenRegion chunkRegion, long seed, RandomState noiseConfig,
                      BiomeManager biomeAccess, StructureManager structureAccessor,
                      ChunkAccess chunk2, GenerationStep.Carving carverStep) {

    }

    @Override
    public void buildSurface(WorldGenRegion region, StructureManager structures, RandomState noiseConfig, ChunkAccess chunk) {
    }

    private void generateTerrain(ChunkAccess chunk, long seed, boolean generateProceduralStructures) {
        int bottomY = chunk.getMinBuildHeight();
        for(int x = 0; x < 16; x++) {
            for(int z = 0; z < 16; z++) {
                int posX = (chunk.getPos().x * 16) + x;
                int posZ = (chunk.getPos().z * 16) + z;
                MapBasedCustomBiome customHeightBiomeHeightData = middleEarthMapRuntime.getBiome(posX, posZ);
                if(customHeightBiomeHeightData == null) {
                    customHeightBiomeHeightData = MapBasedBiomePool.defaultBiome;
                }

                float height = MiddleEarthHeightMap.getHeight(posX, posZ);

                float caveBlendNoise = (float) ((2 * CAVE_NOISE * BlendedNoise.noise((double) (posX + seed) / 24f,  (double) (posZ + seed) / 24f)) - CAVE_NOISE);
                float slopeAngle = getTerrainSlope(height, posX, posZ);
                int waterHeight = customHeightBiomeHeightData.getWaterHeight();

                ResourceKey<Biome> biomeRegistryKey = customHeightBiomeHeightData.getBiomeKey();
                if(SubBiomes.isSubBiome(biomeRegistryKey)) {
                    SubBiome subBiome = SubBiomes.getSubBiomeFromChild(biomeRegistryKey);
                    if(subBiome != null) {
                        double perlin = ModBiomeSource.getSubBiomeNoise(posX, posZ, subBiome.getFrequency());
                        double additionalHeight = Math.max(subBiome.getAdditionalHeight((float) perlin) - 1, 0);
                        additionalHeight *= MiddleEarthMapRuntime.getInstance().getEdge(posX, posZ);
                        height += (float) additionalHeight;
                    }
                } else if(biomeRegistryKey == MEBiomeKeys.MOUNT_DOOM || biomeRegistryKey == MEBiomeKeys.MOUNT_DOOM_PIT) {
                    float percentage = (float) Math.sqrt(mountDoom.distanceToSqr(new Vec2(posX, posZ))) / 42;
                    percentage = Math.min(1, Math.max(0.0f, percentage));
                    percentage = (float) Math.pow(percentage, 2.47f);
                    height = height * percentage;
                    height -= (1 - percentage) * getNoisyHeight(posX, posZ) * 8;
                } else if(biomeRegistryKey == MEBiomeKeys.DEAD_MARSHES || biomeRegistryKey == MEBiomeKeys.DEAD_MARSHES_WATER) {
                    float oldHeight = height;
                    height = getMarshesHeight(posX, posZ, height);
                    float percentage = Math.min(MiddleEarthHeightMap.getImageNoiseModifier(posX, posZ), 0.3f) / 0.3f;
                    height = MiddleEarthHeightMap.lerp(height, oldHeight, percentage);
                }

                chunk.setBlockState(chunk.getPos().getBlockAt(x, bottomY, z), Blocks.BEDROCK.defaultBlockState(), false);
                for(int y = bottomY + 1; y <= LAVA_HEIGHT; y++) {
                    chunk.setBlockState(chunk.getPos().getBlockAt(x, y, z), Blocks.LAVA.defaultBlockState(), false);
                }

                for(int y = bottomY + 1; y < MEDGON_LEVEL + caveBlendNoise; y++) {
                    trySetBlock(chunk, chunk.getPos().getBlockAt(x, y, z), StoneBlockSets.MEDGON.base().defaultBlockState());
                }
                if(hasExtraBedrock(posX, posZ, seed)) chunk.setBlockState(chunk.getPos().getBlockAt(x, chunk.getMinBuildHeight() + 1, z),
                        Blocks.BEDROCK.defaultBlockState(), false);

                for(int y = MEDGON_LEVEL + (int) caveBlendNoise; y < NURGON_LEVEL + caveBlendNoise; y++) {
                    trySetBlock(chunk, chunk.getPos().getBlockAt(x, y, z), StoneBlockSets.NURGON.base().defaultBlockState());
                }
                for(int y = NURGON_LEVEL + (int) caveBlendNoise; y < DEEPSLATE_LEVEL + caveBlendNoise; y++) {
                    trySetBlock(chunk, chunk.getPos().getBlockAt(x, y, z), Blocks.DEEPSLATE.defaultBlockState());
                }

                float dirtHeight = HEIGHT + height - 1;
                int currentHeight = DEEPSLATE_LEVEL + (int) caveBlendNoise;
                int totalLayersHeight = (int) (dirtHeight - currentHeight);
                for(BlocksLayeringData.LayerData layerData : customHeightBiomeHeightData.getBiome().getBlocksLayering().layers) {
                    int blocks = (int) (totalLayersHeight * layerData.percentage);
                    for(int y = 0; y <= blocks; y++) {
                        trySetBlock(chunk, chunk.getPos().getBlockAt(x, currentHeight++, z), layerData.block.defaultBlockState());
                    }
                }
                chunk.setBlockState(chunk.getPos().getBlockAt(x, (int) (HEIGHT + height - 2), z), customHeightBiomeHeightData.getBiome().getBlocksLayering().layers.getFirst().block.defaultBlockState(), false);
                BlockState surfaceBlock = customHeightBiomeHeightData.getBiome().getSlopeMap().slopeDatas.getFirst().block.defaultBlockState();
                BlockState underSurfaceBlock;


                if(DIRT_HEIGHT + height < waterHeight && surfaceBlock == Blocks.GRASS_BLOCK.defaultBlockState()) {
                    surfaceBlock = Blocks.DIRT.defaultBlockState();
                    underSurfaceBlock = surfaceBlock;
                } else {
                    surfaceBlock = customHeightBiomeHeightData.getBiome().getSlopeMap().getBlockAtAngle(slopeAngle).defaultBlockState();
                    if(surfaceBlock == Blocks.GRASS_BLOCK.defaultBlockState() || surfaceBlock == ModBlocks.SNOWY_GRASS_BLOCK.defaultBlockState()) {
                        underSurfaceBlock = Blocks.DIRT.defaultBlockState();
                    }
                    else underSurfaceBlock = surfaceBlock;
                }

                chunk.setBlockState(chunk.getPos().getBlockAt(x, (int) (HEIGHT + height - 1), z), underSurfaceBlock, false);
                for(int y = (int) (HEIGHT + height); y < DIRT_HEIGHT + height; y++) {
                    chunk.setBlockState(chunk.getPos().getBlockAt(x, y, z), underSurfaceBlock, false);
                }
                chunk.setBlockState(chunk.getPos().getBlockAt(x, (int) (DIRT_HEIGHT + height), z), surfaceBlock, false);

                if(biomeRegistryKey == MEBiomeKeys.MOUNT_DOOM || biomeRegistryKey == MEBiomeKeys.MOUNT_DOOM_PIT) {
                    for(int y = (int) (DIRT_HEIGHT + height + 1); y <= 100; y++) {
                        chunk.setBlockState(chunk.getPos().getBlockAt(x, y, z), Blocks.LAVA.defaultBlockState(), false);
                    }
                    if(DIRT_HEIGHT + height < 110) {
                        chunk.setBlockState(chunk.getPos().getBlockAt(x, (int) (DIRT_HEIGHT + height), z), Blocks.MAGMA_BLOCK.defaultBlockState(), false);
                    }
                } else {
                    for(int y = (int) (DIRT_HEIGHT + height + 1); y <= waterHeight; y++) {
                        chunk.setBlockState(chunk.getPos().getBlockAt(x, y, z), Blocks.WATER.defaultBlockState(), false);
                    }
                }

                if(generateProceduralStructures && ModServerConfigs.ENABLE_PROCEDURAL_STRUCTURES) {
                    ProceduralStructures.generateStructures(customHeightBiomeHeightData, chunk, posX, (int) (DIRT_HEIGHT + height), posZ);
                }
            }
        }
    }

    private static boolean hasExtraBedrock(int x, int z, long seed) {
        long value = seed;
        value ^= (long) x * 341873128712L;
        value ^= (long) z * 132897987541L;
        value ^= value >>> 33;
        value *= 0xff51afd7ed558ccdL;
        value ^= value >>> 33;
        return (value & 1L) == 0L;
    }

    private float getTerrainSlope(float height, int x, int z) {
        int offset = 3;
        float eastHeight = MiddleEarthHeightMap.getHeight(x + offset, z);
        float southHeight = MiddleEarthHeightMap.getHeight(x, z + offset);

        float eastSlope = Math.abs((eastHeight - height) / offset);
        float southSlope = Math.abs((southHeight - height) / offset);
        float highestSlope = (eastSlope + southSlope) / 2;

        return (float) Math.toDegrees(Math.atan(highestSlope));
    }

    private void trySetBlock(ChunkAccess chunk, BlockPos blockPos, BlockState blockState) {
        float noise = 0;
        if(blockPos.getY() < WATER_HEIGHT) {
            noise =(float) SimplexNoise.noise(
                    (float) blockPos.getX() / CAVE_STRETCH_H, Math.tan((float) blockPos.getY() / CAVE_STRETCH_V), (float) blockPos.getZ() / CAVE_STRETCH_H);
            noise += 0.5f * (float) SimplexNoise.noise(
                    (float) blockPos.getX() / (CAVE_STRETCH_H * 0.5f), (float) blockPos.getY() / (CAVE_STRETCH_V * 0.5f), (float) blockPos.getZ() / (CAVE_STRETCH_H * 0.5f));
            noise = noise / (1 + 0.5f);
        }
        float noise3 = (float) SimplexNoise.noise((float) blockPos.getX() / 90, (float) blockPos.getY() / 60, (float) blockPos.getZ() / 90);
        float miniNoise = (float) SimplexNoise.noise((float) blockPos.getX() / 40, (float) blockPos.getY() / 30, (float) blockPos.getZ() / 40);


        float spaghettiNoise = Math.abs ((float) SimplexNoise.noise(
                (float) blockPos.getX() / (SPAGHETTI_CAVE_STRETCH_H * 1.5f), (float) Math.tan((float) blockPos.getY() / CAVE_STRETCH_V), (float) blockPos.getZ() / (SPAGHETTI_CAVE_STRETCH_H * 1.5f), 57142));
        float spaghettiNoise2 = Math.abs ((float) SimplexNoise.noise(
                (float) (98153 + blockPos.getZ()) / SPAGHETTI_CAVE_STRETCH_H, (float) blockPos.getY() / CAVE_STRETCH_V, (float) blockPos.getX() / SPAGHETTI_CAVE_STRETCH_H, 0));
        float spaghettiNoise3 = Math.abs ((float) SimplexNoise.noise(
                (float) (1243624 + blockPos.getZ()) / (SPAGHETTI_CAVE_STRETCH_H * 0.5f), (float) blockPos.getY() / CAVE_STRETCH_V, (float) blockPos.getX() / (SPAGHETTI_CAVE_STRETCH_H * 0.5f), 0));
        float combinedSpaghettiNoise = Math.abs(spaghettiNoise) + Math.abs(spaghettiNoise2) + Math.abs(spaghettiNoise3);
        combinedSpaghettiNoise /= 3;

        if(noise < 0.4f && noise3 < 0.75f && miniNoise < 0.8f && combinedSpaghettiNoise > 0.09f) {
            chunk.setBlockState(blockPos, blockState, false);
        }
    }

    public static float getMarshesHeight(int x, int z, float height) {
        height = -2 + (2.0f * (float) BlendedNoise.noise((double) x / 19,  (double) z / 19));
        height += (float) BlendedNoise.noise((double) x / 11,  (double) z / 11);
        return height;
    }

    public static float getNoisyHeight(int x, int z) {
        float height = -2 + (4.0f * (float) BlendedNoise.noise((double) x / 8,  (double) z / 8));
        height += 2 * (float) BlendedNoise.noise((double) x / 4,  (double) z / 4);
        return height;
    }
    
    @Override
    public void spawnOriginalMobs(WorldGenRegion region) {
        ChunkPos chunkPos = region.getCenter();
        Holder<Biome> registryEntry = region.getBiome(chunkPos.getWorldPosition().atY(region.getMaxBuildHeight() - 1));
        WorldgenRandom chunkRandom = new WorldgenRandom(new LegacyRandomSource(RandomSupport.generateUniqueSeed()));
        chunkRandom.setDecorationSeed(region.getSeed(), chunkPos.getMinBlockX(), chunkPos.getMinBlockZ());
        NaturalSpawner.spawnMobsForChunkGeneration(region, registryEntry, chunkPos, chunkRandom);
    }

    @Override
    public int getGenDepth() {
        return 384;
    }

    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Blender blender, RandomState noiseConfig, StructureManager structureAccessor, ChunkAccess chunk) {
        generateTerrain(chunk, MiddleEarthHeightMap.getSeed(), true);
        return CompletableFuture.completedFuture(chunk);
    }

    @Override
    public int getSeaLevel() {
        return WATER_HEIGHT;
    }

    @Override
    public int getMinY() {
        return -64;
    }

    @Override
    public int getBaseHeight(int x, int z, Heightmap.Types heightmap, LevelHeightAccessor world, RandomState noiseConfig) {
        float worldHeight = 1 + DIRT_HEIGHT + MiddleEarthHeightMap.getHeight(x, z);
        return (int)worldHeight;
    }

    @Override
    public NoiseColumn getBaseColumn(int x, int z, LevelHeightAccessor world, RandomState noiseConfig) {
        int minY = getMinY();
        int maxY = minY + getGenDepth();
        int surfaceY = Math.max(minY, Math.min(maxY - 1, getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, world, noiseConfig)));
        BlockState[] states = new BlockState[getGenDepth()];
        BlockState air = Blocks.AIR.defaultBlockState();
        BlockState bedrock = Blocks.BEDROCK.defaultBlockState();
        BlockState lava = Blocks.LAVA.defaultBlockState();
        BlockState deepslate = Blocks.DEEPSLATE.defaultBlockState();
        BlockState stone = Blocks.STONE.defaultBlockState();
        BlockState dirt = Blocks.DIRT.defaultBlockState();
        BlockState grass = Blocks.GRASS_BLOCK.defaultBlockState();
        BlockState water = Blocks.WATER.defaultBlockState();

        for (int y = minY; y < maxY; y++) {
            BlockState state = air;
            if (y == minY) {
                state = bedrock;
            } else if (y <= LAVA_HEIGHT) {
                state = lava;
            } else if (y < DEEPSLATE_LEVEL) {
                state = deepslate;
            } else if (y < surfaceY - 4) {
                state = stone;
            } else if (y < surfaceY) {
                state = dirt;
            } else if (y == surfaceY) {
                state = grass;
            } else if (y <= WATER_HEIGHT) {
                state = water;
            }
            states[y - minY] = state;
        }
        return new NoiseColumn(minY, states);
    }

    @Override
    public void addDebugScreenInfo(List<String> text, RandomState noiseConfig, BlockPos pos) {
    }
}
