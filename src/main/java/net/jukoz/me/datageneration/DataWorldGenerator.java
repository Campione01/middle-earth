package net.jukoz.me.datageneration;

import net.jukoz.me.compat.neoforge.api.datagen.v1.NeoForgeDataOutput;
import net.jukoz.me.compat.neoforge.api.datagen.v1.provider.NeoForgeDynamicRegistryProvider;
import net.jukoz.me.MiddleEarth;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import java.util.concurrent.CompletableFuture;

public class DataWorldGenerator extends NeoForgeDynamicRegistryProvider {
    public DataWorldGenerator(NeoForgeDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.BIOME));
        entries.addAll(registries.lookupOrThrow(Registries.CONFIGURED_FEATURE));
        entries.addAll(registries.lookupOrThrow(Registries.PLACED_FEATURE));
    }

    @Override
    public String getName() {
        return MiddleEarth.MOD_ID;
    }
}
