package net.jukoz.me.resources.datas.factions;

import net.jukoz.me.exceptions.FactionIdentifierException;
import net.jukoz.me.resources.MiddleEarthFactions;
import net.jukoz.me.resources.datas.Disposition;
import net.jukoz.me.resources.datas.FactionType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import java.util.*;
import java.util.stream.Stream;

public class FactionLookup {
    public static List<Faction> getAllFactions(Level world) {
        return world.registryAccess().registryOrThrow(MiddleEarthFactions.FACTION_KEY).stream().toList();
    }
    public static Faction getFactionById(Level world, ResourceLocation id) throws FactionIdentifierException {
        Faction faction = world.registryAccess().registryOrThrow(MiddleEarthFactions.FACTION_KEY).get(id);
        if(faction == null)
            throw new FactionIdentifierException();
        return faction;
    }

    public static HashMap<ResourceLocation, Faction> getFactionsByDisposition(Level world, Disposition disposition){
        Stream<Faction> factions = getAllJoinableFaction(world).stream();
        HashMap<ResourceLocation, Faction> foundFactions = new HashMap<>();

        for(Faction faction : factions.filter(x -> x.getDisposition() == disposition).toList()){
            if(faction.getFactionType() == FactionType.FACTION)
                foundFactions.put(faction.getId(), faction);
        }
        return foundFactions;
    }

    public static List<Faction> getAllJoinableFaction(Level world) {
        List<Faction> factions = getAllFactions(world);
        List<Faction> factionList = new ArrayList<>();
        for(Faction faction : factions) {
            if(!faction.isJoinable())
                continue;
            factionList.add(faction);
        }
        return factionList;
    }
}
