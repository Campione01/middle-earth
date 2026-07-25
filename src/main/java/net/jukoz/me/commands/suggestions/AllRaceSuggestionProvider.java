package net.jukoz.me.commands.suggestions;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.jukoz.me.exceptions.FactionIdentifierException;
import net.jukoz.me.resources.datas.factions.Faction;
import net.jukoz.me.resources.datas.factions.FactionLookup;
import net.jukoz.me.resources.datas.factions.data.SpawnDataHandler;
import net.jukoz.me.resources.datas.races.Race;
import net.jukoz.me.resources.datas.races.RaceLookup;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.resources.ResourceLocation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AllRaceSuggestionProvider implements SuggestionProvider<CommandSourceStack> {
    @Override
    public CompletableFuture<Suggestions> getSuggestions(CommandContext<CommandSourceStack> context, SuggestionsBuilder builder) {
        try {
            List<ResourceLocation> candidates = null;
            candidates = getAllRaces(context);
            return SuggestionUtil.getCorrespondingIdentifiers(candidates, builder);
        } catch (FactionIdentifierException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<ResourceLocation> getAllRaces(CommandContext<CommandSourceStack> context) throws FactionIdentifierException {
        List<Race> races = RaceLookup.getAllRaces(context.getSource().getLevel());
        List<ResourceLocation> candidates = new ArrayList<>();

        for(Race race: races){
            candidates.add(race.getId());
        }

        return candidates;
    }
}