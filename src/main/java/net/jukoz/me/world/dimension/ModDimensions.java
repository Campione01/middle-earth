package net.jukoz.me.world.dimension;

import net.jukoz.me.utils.NeoForgeRegistrationBridge;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.config.ModServerConfigs;
import net.jukoz.me.resources.StateSaverAndLoader;
import net.jukoz.me.resources.datas.factions.FactionUtil;
import net.jukoz.me.resources.datas.races.Race;
import net.jukoz.me.resources.datas.races.RaceUtil;
import net.jukoz.me.resources.datas.races.data.AttributeData;
import net.jukoz.me.resources.persistent_datas.PlayerData;
import net.jukoz.me.utils.LoggerUtil;
import net.jukoz.me.world.biomes.surface.ModBiomeSource;
import net.jukoz.me.world.chunkgen.MiddleEarthChunkGenerator;
import net.jukoz.me.world.chunkgen.map.MiddleEarthHeightMap;
import net.jukoz.me.world.map.MiddleEarthMapConfigs;
import net.jukoz.me.world.map.MiddleEarthMapUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3i;

public class ModDimensions {
    public static ResourceLocation ME_DIMENSION_ID = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "middle_earth");
    public static ResourceLocation OW_DIMENSION_ID = ResourceLocation.parse("overworld");

    public static final ResourceKey<LevelStem> ME_DIMENSION_KEY =
            ResourceKey.create(Registries.LEVEL_STEM, ME_DIMENSION_ID);

    public static ResourceKey<Level> ME_WORLD_KEY =
            ResourceKey.create(Registries.DIMENSION, ME_DIMENSION_KEY.location());

    public static final ResourceKey<LevelStem> OW_DIMENSION_KEY =
            ResourceKey.create(Registries.LEVEL_STEM, ResourceLocation.parse("overworld"));

    public static ResourceKey<Level> OW_WORLD_KEY =
            ResourceKey.create(Registries.DIMENSION, OW_DIMENSION_KEY.location());

    public static void register() {
        NeoForgeRegistrationBridge.register(BuiltInRegistries.BIOME_SOURCE, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "middle_earth_biome_source"), ModBiomeSource.CODEC);
        NeoForgeRegistrationBridge.register(BuiltInRegistries.CHUNK_GENERATOR, ME_DIMENSION_ID, MiddleEarthChunkGenerator.CODEC);
        ME_WORLD_KEY = ResourceKey.create(Registries.DIMENSION, ME_DIMENSION_ID);

        LoggerUtil.logDebugMsg("Registering ModDimensions for " + MiddleEarth.MOD_ID);
    }

    public static Vector3i getDimensionHeight(int x, int z) {
        MiddleEarthHeightMap.getHeight(x, z);
        int height = (int) (1 + MiddleEarthChunkGenerator.DIRT_HEIGHT + MiddleEarthHeightMap.getHeight(x, z));
        return new Vector3i(x, height, z);
    }

    public static void teleportPlayerToMe(Player player, Vec3 coordinates, boolean setSpawnPoint, boolean welcomeNeeded){
        if(player.level().isClientSide() || !(player instanceof ServerPlayer serverPlayer)) {
            return;
        }

        ServerLevel serverWorld = serverPlayer.getServer().getLevel(ME_WORLD_KEY);
        if (serverWorld == null) {
            LoggerUtil.logError("Middle-earth dimension is not loaded; cannot teleport player.");
            return;
        }

        Vec3 target = getSafeMiddleEarthTeleportTarget(coordinates);
        BlockPos targetPos = BlockPos.containing(target);
        serverWorld.getChunk(targetPos);

        serverPlayer.stopSleeping();
        serverPlayer.teleportTo(serverWorld, target.x, target.y, target.z, serverPlayer.getYRot(), serverPlayer.getXRot());
        if(setSpawnPoint) {
            serverPlayer.setRespawnPosition(ME_WORLD_KEY, targetPos, serverPlayer.getYRot(), true, true);
        }
        if(welcomeNeeded) {
            FactionUtil.sendOnFactionJoinMessage(player);
        }
        PlayerData data = StateSaverAndLoader.getPlayerStateReadOnly(player);
        if(data != null){
            Race playerRace = data.getRace(serverWorld);
            if(playerRace != null) {
                RaceUtil.updateRace(player, playerRace, false);
            }
        }
    }

    public static Vec3 getSafeMiddleEarthTeleportTarget(Vec3 coordinates) {
        int x = (int) Math.floor(coordinates.x);
        int z = (int) Math.floor(coordinates.z);
        if(!MiddleEarthMapUtils.getInstance().isWorldCoordinateInBorder(x, z)) {
            Vector3i fallback = getSpawnCoordinate();
            x = fallback.x;
            z = fallback.z;
        }
        Vector3i height = getDimensionHeight(x, z);
        return new Vec3(x + 0.5D, height.y + 1.0D, z + 0.5D);
    }

    public static boolean isInMiddleEarth(Level world){
        return world.dimension().location().equals(ME_DIMENSION_ID);
    }

    public static boolean isInOverworld(Level world){
        return world.dimension().location().equals(OW_DIMENSION_ID);
    }

    public static boolean teleportPlayerToOverworld(Player player) {
        if(!player.level().isClientSide() && player instanceof ServerPlayer serverPlayer) {
            ResourceKey<Level> registryKey = OW_WORLD_KEY;
            PlayerData data = StateSaverAndLoader.getPlayerStateReadOnly(player);
            BlockPos coordinate = data == null ? null : data.getOverworldSpawnCoordinates();
            if(coordinate == null) {
                coordinate = player.getServer().overworld().getSharedSpawnPos();
            }

            ServerLevel serverWorld = serverPlayer.getServer().getLevel(registryKey);
            if (serverWorld != null) {
                serverWorld.getChunk(coordinate);

                serverPlayer.stopSleeping();
                serverPlayer.setRespawnPosition(Level.OVERWORLD, player.getServer().overworld().getSharedSpawnPos(), player.getServer().overworld().getSharedSpawnAngle(), true, true);
                serverPlayer.teleportTo(serverWorld, coordinate.getX() + 0.5D, coordinate.getY(), coordinate.getZ() + 0.5D, serverPlayer.getYRot(), serverPlayer.getXRot());

                if(!ModServerConfigs.ENABLE_KEEP_RACE_ON_DIMENSION_SWAP){
                    AttributeData.reset(player);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * For future usage only, not necessary for now
     * @return world coordinate for current map coordinate selected based on map iteration/pixel weight
     */
    public static Vector3i getSpawnCoordinate(){
        Vector3i spawnCoordinate = new Vector3i(939, 90, 915);;
        double worldIteration = Math.pow(2, MiddleEarthMapConfigs.MAP_ITERATION);
        int x = (int)((spawnCoordinate.x * worldIteration));
        int z = (int)((spawnCoordinate.z * worldIteration));

        return new Vector3i(x, spawnCoordinate.y, z);
    }
}
