package net.jukoz.me.registries;

import net.jukoz.me.resources.MiddleEarthFactions;
import net.jukoz.me.resources.MiddleEarthNpcs;
import net.jukoz.me.resources.MiddleEarthRaces;
import net.jukoz.me.resources.datas.factions.Faction;
import net.jukoz.me.resources.datas.npcs.NpcData;
import net.jukoz.me.resources.datas.races.Race;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

public final class MiddleEarthDataPackRegistries {
    private MiddleEarthDataPackRegistries() {
    }

    public static void register(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(MiddleEarthRaces.RACE_KEY, Race.CODEC, Race.CODEC);
        event.dataPackRegistry(MiddleEarthNpcs.NPC_KEY, NpcData.CODEC, NpcData.CODEC);
        event.dataPackRegistry(MiddleEarthFactions.FACTION_KEY, Faction.CODEC, Faction.CODEC);
    }
}
