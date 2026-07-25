package net.jukoz.me.datageneration;

import net.jukoz.me.compat.neoforge.api.datagen.v1.NeoForgeDataOutput;
import net.jukoz.me.compat.neoforge.api.datagen.v1.provider.NeoForgeDynamicRegistryProvider;
import net.jukoz.me.resources.MiddleEarthFactions;
import net.minecraft.core.HolderLookup;
import java.util.concurrent.CompletableFuture;

public class FactionProvider extends NeoForgeDynamicRegistryProvider {
    public FactionProvider(NeoForgeDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(MiddleEarthFactions.FACTION_KEY));
    }

    @Override
    public String getName() {
        return "Factions";
    }
}
