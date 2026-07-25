package net.jukoz.me.resources.datas.factions.data;

import net.jukoz.me.compat.neoforge.api.util.NbtType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector2i;

import java.util.*;

public class SpawnDataHandler {
    Vector2i mapViewCenter;

    HashMap<ResourceLocation, SpawnData> spawns;

    public SpawnDataHandler(List<SpawnData> spawnDatas){
        spawns = new HashMap<>();
        for(SpawnData spawn : spawnDatas){
            spawns.put(spawn.getIdentifier(), spawn);
        }
    }

    public SpawnDataHandler(Optional<CompoundTag> spawnsNbt) {
        if(spawnsNbt.isEmpty()){
            return;
        }
        deserializeNbt(spawnsNbt.get());
    }

    private void deserializeNbt(CompoundTag nbtCompound) {
        ListTag compoundList = nbtCompound.getList("data", NbtType.COMPOUND);
        spawns = new HashMap<>();
        for(int i = 0; i < compoundList.size(); i++){
            SpawnData spawnData = SpawnData.deserialize(compoundList.getCompound(i));
            spawns.put(spawnData.getIdentifier(), spawnData);
        }
    }

    public Optional<CompoundTag> serializeNbt() {
        if((spawns == null || spawns.isEmpty()))
            return Optional.empty();

        CompoundTag nbt = new CompoundTag();
        ListTag spawnDataList = new ListTag();
        for(SpawnData spawnData : spawns.values()){
            spawnDataList.add(SpawnData.serialize(spawnData));
        }
        nbt.put("data", spawnDataList);
        return Optional.of(nbt);
    }

    public SpawnData findSpawn(ResourceLocation spawnId) {
        return spawns.get(spawnId);
    }

    public static String getTranslatableKey(ResourceLocation id){
        if(id == null)
            return null;
        return "spawn.".concat(id.toLanguageKey());
    }

    public List<SpawnData> getSpawnList(){
        if(spawns == null || spawns.isEmpty())
            return null;
        return spawns.values().stream().toList();
    }

    public List<ResourceLocation> getAllSpawnIdentifiers(){
        if(spawns == null || spawns.isEmpty())
            return null;
        return spawns.keySet().stream().toList();
    }

    public ResourceLocation getDefaultSpawn() {
        if(spawns == null || spawns.isEmpty())
            return null;
        return spawns.keySet().stream().toList().getFirst();
    }

    public BlockPos getSpawnBlockPos(ResourceLocation spawnId) {
        if(spawns == null || spawns.isEmpty())
            return null;
        SpawnData data = spawns.get(spawnId);
        if(data == null) return null;
        return data.getBlockPos();
    }
}
