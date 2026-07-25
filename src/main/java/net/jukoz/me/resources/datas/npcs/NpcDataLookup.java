package net.jukoz.me.resources.datas.npcs;

import net.jukoz.me.resources.MiddleEarthNpcs;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import java.util.ArrayList;
import java.util.List;

public class NpcDataLookup {
    public static List<NpcData> getAllNpcDatas(Level world, List<ResourceLocation> ids) {
        Registry<NpcData> registry = world.registryAccess().registryOrThrow(MiddleEarthNpcs.NPC_KEY);
        List<NpcData> list = new ArrayList<>();
        for(ResourceLocation id : ids){
            list.add(registry.get(id));
        }
        return list;
    }

    public static List<NpcData> getAllNpcDatasFromRace(Level world, List<ResourceLocation> ids, ResourceLocation race){
        List<NpcData> unsortedList = getAllNpcDatas(world, ids);
        List<NpcData> list = new ArrayList<>();
        for(NpcData npcData : unsortedList){
            if(npcData.getRaceId().equals(race))
                list.add(npcData);
        }
        return list;
    }
}
