package net.jukoz.me.datageneration;

import net.jukoz.me.compat.neoforge.api.datagen.v1.NeoForgeDataOutput;
import net.jukoz.me.compat.neoforge.api.datagen.v1.provider.NeoForgeDynamicRegistryProvider;
import net.jukoz.me.resources.MiddleEarthRaces;
import net.minecraft.core.HolderLookup;
import java.util.concurrent.CompletableFuture;

public class RaceProvider extends NeoForgeDynamicRegistryProvider {
    public RaceProvider(NeoForgeDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(MiddleEarthRaces.RACE_KEY));
    }

    @Override
    public String getName() {
        return "Races";
    }
}
