package net.jukoz.me.compat.neoforge.api.datagen.v1.provider;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import net.jukoz.me.compat.neoforge.api.event.registry.DynamicRegistries;
import net.jukoz.me.compat.neoforge.api.datagen.v1.NeoForgeDataOutput;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public abstract class NeoForgeDynamicRegistryProvider implements DataProvider {
    private final NeoForgeDataOutput output;
    private final CompletableFuture<HolderLookup.Provider> registriesFuture;

    protected NeoForgeDynamicRegistryProvider(NeoForgeDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        this.output = output;
        this.registriesFuture = registriesFuture;
    }

    protected abstract void configure(HolderLookup.Provider registries, Entries entries);

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        return registriesFuture.thenCompose(registries -> {
            Entries entries = new Entries(output.getModId());
            configure(registries, entries);

            DynamicOps<JsonElement> ops = registries.createSerializationContext(JsonOps.INSTANCE);
            List<CompletableFuture<?>> futures = new ArrayList<>();
            for (Map.Entry<ResourceKey<? extends Registry<?>>, List<Entry<?>>> registryEntry : entries.entries.entrySet()) {
                Codec<?> codec = DynamicRegistries.getSyncedRegistries().get(registryEntry.getKey());
                if (codec == null) {
                    throw new IllegalStateException("Missing codec for dynamic registry " + registryEntry.getKey().location());
                }
                futures.addAll(saveRegistry(cachedOutput, ops, registryEntry.getKey(), codec, registryEntry.getValue()));
            }
            return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
        });
    }

    public static class Entries {
        private final String modId;
        private final Map<ResourceKey<? extends Registry<?>>, List<Entry<?>>> entries = new LinkedHashMap<>();

        private Entries(String modId) {
            this.modId = modId;
        }

        public <T> void addAll(HolderLookup.RegistryLookup<T> lookup) {
            lookup.listElements()
                    .filter(reference -> reference.key().location().getNamespace().equals(modId))
                    .forEach(reference -> add(lookup.key(), reference));
        }

        private <T> void add(ResourceKey<? extends Registry<? extends T>> registryKey, Holder.Reference<T> reference) {
            entries.computeIfAbsent((ResourceKey<? extends Registry<?>>) registryKey, ignored -> new ArrayList<>())
                    .add(new Entry<>(reference.key(), reference.value()));
        }
    }

    private <T> List<CompletableFuture<?>> saveRegistry(CachedOutput cachedOutput, DynamicOps<JsonElement> ops,
                                                        ResourceKey<? extends Registry<?>> registryKey, Codec<?> codec,
                                                        List<Entry<?>> values) {
        String directory = registryKey.location().getNamespace() + "/" + registryKey.location().getPath();
        PackOutput.PathProvider pathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, directory);
        List<CompletableFuture<?>> futures = new ArrayList<>();
        for (Entry<?> entry : values) {
            futures.add(saveEntry(cachedOutput, ops, (Codec<T>) codec, (Entry<T>) entry, pathProvider));
        }
        return futures;
    }

    private static <T> CompletableFuture<?> saveEntry(CachedOutput cachedOutput, DynamicOps<JsonElement> ops,
                                                      Codec<T> codec, Entry<T> entry, PackOutput.PathProvider pathProvider) {
        Path path = pathProvider.json(entry.key().location());
        return codec.encodeStart(ops, entry.value())
                .mapOrElse(
                        json -> DataProvider.saveStable(cachedOutput, json, path),
                        error -> CompletableFuture.failedFuture(new IllegalStateException("Couldn't serialize " + path + ": " + error.message()))
                );
    }

    private record Entry<T>(ResourceKey<T> key, T value) {
    }
}
