package net.jukoz.me.resources;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.resources.datas.Disposition;
import net.jukoz.me.resources.persistent_datas.AffiliationData;
import net.jukoz.me.resources.persistent_datas.PlayerData;
import net.jukoz.me.utils.IdentifierUtil;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import java.util.HashMap;
import java.util.UUID;

public class StateSaverAndLoader extends SavedData {
    public HashMap<UUID, PlayerData> players = new HashMap<>();
    @Override
    public CompoundTag save(CompoundTag nbt, HolderLookup.Provider registryLookup) {
        CompoundTag playersNbt = new CompoundTag();
        players.forEach((uuid, playerData) -> {
            CompoundTag playerNbt = new CompoundTag();
            if(playerData.hasAffilition()){
                playerNbt.putString("disposition", playerData.getCurrentDisposition().toString().toLowerCase());
                playerNbt.putString("faction_id", playerData.getCurrentFactionId().toString().toLowerCase());
                playerNbt.putString("spawn_id", playerData.getCurrentSpawnId().toString().toLowerCase());
            }

            BlockPos overworldSpawn = playerData.getOverworldSpawnCoordinates();
            if(overworldSpawn != null){
                playerNbt.putIntArray("overworld_spawn", new int[]{overworldSpawn.getX(), overworldSpawn.getY(), overworldSpawn.getZ()});
            }
            if(playerData.getRace() != null){
                playerNbt.putString("race", playerData.getRace().toString());
            }

            playersNbt.put(uuid.toString(), playerNbt);
        });
        nbt.put("players", playersNbt);

        return nbt;
    }

    public static StateSaverAndLoader createFromNbt(CompoundTag tag, HolderLookup.Provider registryLookup) {
        StateSaverAndLoader state = new StateSaverAndLoader();
        CompoundTag playersNbt = tag.getCompound("players");
        playersNbt.getAllKeys().forEach(key -> {
            PlayerData playerData = new PlayerData();
            try{
                String dispositionValue = playersNbt.getCompound(key).getString("disposition");
                boolean hasDisposition = dispositionValue != null && !dispositionValue.isEmpty();
                String factionIdValue = playersNbt.getCompound(key).getString("faction_id");
                boolean hasFaction = factionIdValue != null && !factionIdValue.isEmpty();
                String spawnIdValue = playersNbt.getCompound(key).getString("spawn_id");
                boolean hasSpawn = spawnIdValue != null && !spawnIdValue.isEmpty();
                int[] overworldPos = playersNbt.getCompound(key).getIntArray("overworld_spawn");
                boolean hasOverworldPos = overworldPos != null && overworldPos.length == 3;

                if(hasDisposition && hasFaction && hasSpawn){
                    Disposition disposition = Disposition.valueOf(dispositionValue.toUpperCase());
                    ResourceLocation factionId = IdentifierUtil.getIdentifierFromString(factionIdValue);
                    ResourceLocation spawnId = IdentifierUtil.getIdentifierFromString(spawnIdValue);

                    AffiliationData affiliationData = new AffiliationData(disposition.name(), factionId, spawnId);
                    playerData.setAffiliationData(affiliationData);
                }
                String raceValue = playersNbt.getCompound(key).getString("race");
                if(raceValue != null && !raceValue.isEmpty()){
                    playerData.setRace(IdentifierUtil.getIdentifierFromString(raceValue));
                }

                if(hasOverworldPos){
                    BlockPos overworldSpawn = new BlockPos(
                            overworldPos[0],
                            overworldPos[1],
                            overworldPos[2]
                    );
                    playerData.setOverworldSpawn(overworldSpawn);
                }
            } catch(Exception e){
                LoggerUtil.logError("StateSaverAndLoader",e);
            }

            playerData.setDirtyMarker(state::setDirty);
            UUID uuid = UUID.fromString(key);
            state.players.put(uuid, playerData);
        });

        return state;
    }

    private static Factory<StateSaverAndLoader> type = new Factory<>(
            StateSaverAndLoader::new, // If there's no 'StateSaverAndLoader' yet create one
            StateSaverAndLoader::createFromNbt, // If there is a 'StateSaverAndLoader' NBT, parse it with 'createFromNbt'
            null // Supposed to be an 'DataFixTypes' enum, but we can just pass null
    );

    public static StateSaverAndLoader getServerState(MinecraftServer server) {
        // (Note: arbitrary choice to use 'World.OVERWORLD' instead of 'World.END' or 'World.NETHER'.  Any work)
        DimensionDataStorage persistentStateManager = server.getLevel(Level.OVERWORLD).getDataStorage();

        // The first time the following 'getOrCreate' function is called, it creates a brand new 'StateSaverAndLoader' and
        // stores it inside the 'PersistentStateManager'. The subsequent calls to 'getOrCreate' pass in the saved
        // 'StateSaverAndLoader' NBT on disk to our function 'StateSaverAndLoader::createFromNbt'.
        StateSaverAndLoader state = persistentStateManager.computeIfAbsent(type, MiddleEarth.MOD_ID);

        return state;
    }

    private static PlayerData createPlayerData(StateSaverAndLoader state) {
        PlayerData playerData = new PlayerData();
        playerData.setDirtyMarker(state::setDirty);
        state.setDirty();
        return playerData;
    }

    private static boolean canAccessPlayerState(Player player, String caller) {
        if(player == null){
            LoggerUtil.logError(caller + " cannot have null as parameter");
            return false;
        }
        if(player.level().isClientSide){
            LoggerUtil.logError(caller + " cannot be used client side");
            return false;
        }
        if(player.level().getServer() == null){
            LoggerUtil.logError(caller + " cannot be used without a server");
            return false;
        }
        return true;
    }

    public static PlayerData getPlayerStateReadOnly(Player player) {
        if(!canAccessPlayerState(player, "StateSaverAndLoader::getPlayerStateReadOnly")){
            return null;
        }

        StateSaverAndLoader serverState = getServerState(player.level().getServer());
        PlayerData playerState = serverState.players.get(player.getUUID());
        if(playerState != null){
            playerState.setDirtyMarker(serverState::setDirty);
        }
        return playerState;
    }

    public static PlayerData getPlayerState(Player player) {
        if(!canAccessPlayerState(player, "StateSaverAndLoader::getPlayerState")){
            return null;
        }

        // If it crashes, it means that you ain't using it correctly.
        StateSaverAndLoader serverState = getServerState(player.level().getServer());

        // Either get the player by the uuid, or we don't have data for him yet, make a new player state
        PlayerData playerState = serverState.players.computeIfAbsent(player.getUUID(), uuid -> createPlayerData(serverState));
        playerState.setDirtyMarker(serverState::setDirty);

        return playerState;
    }
}
