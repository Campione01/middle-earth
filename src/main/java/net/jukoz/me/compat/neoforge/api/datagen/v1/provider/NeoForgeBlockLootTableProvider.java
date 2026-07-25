package net.jukoz.me.compat.neoforge.api.datagen.v1.provider;

import net.jukoz.me.compat.neoforge.api.datagen.v1.NeoForgeDataOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.List;

public abstract class NeoForgeBlockLootTableProvider extends BlockLootSubProvider implements DataProvider {
    private final NeoForgeDataOutput output;
    private final CompletableFuture<HolderLookup.Provider> registryLookup;
    private final PackOutput.PathProvider pathProvider;

    protected NeoForgeBlockLootTableProvider(NeoForgeDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(Set.<Item>of(), FeatureFlags.REGISTRY.allFlags(), registryLookup.join());
        this.output = output;
        this.registryLookup = registryLookup;
        this.pathProvider = output.createRegistryElementsPathProvider(Registries.LOOT_TABLE);
    }

    public void excludeFromStrictValidation(Block block) {
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        return registryLookup.thenCompose(provider -> {
            this.map.clear();
            generate();

            List<CompletableFuture<?>> futures = new ArrayList<>();
            for (Map.Entry<ResourceKey<LootTable>, LootTable.Builder> entry : this.map.entrySet()) {
                ResourceKey<LootTable> key = entry.getKey();
                LootTable table = entry.getValue()
                        .setParamSet(LootContextParamSets.BLOCK)
                        .setRandomSequence(key.location())
                        .build();
                futures.add(DataProvider.saveStable(cachedOutput, provider, LootTable.DIRECT_CODEC, table, pathProvider.json(key.location())));
            }
            return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
        });
    }

    @Override
    public String getName() {
        return "NeoForge block loot tables";
    }
}
