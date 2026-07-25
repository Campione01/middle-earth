package net.jukoz.me.compat.neoforge.api.datagen.v1.provider;

import com.google.gson.JsonElement;
import net.jukoz.me.compat.neoforge.api.datagen.v1.NeoForgeDataOutput;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.BlockStateGenerator;
import net.minecraft.data.models.model.DelegatedModel;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class NeoForgeModelProvider implements DataProvider {
    protected final NeoForgeDataOutput output;
    private final PackOutput.PathProvider blockStatePathProvider;
    private final PackOutput.PathProvider modelPathProvider;

    public NeoForgeModelProvider(NeoForgeDataOutput output) {
        this.output = output;
        this.blockStatePathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "blockstates");
        this.modelPathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models");
    }

    public abstract void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator);

    public abstract void generateItemModels(ItemModelGenerators itemModelGenerator);

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        Map<Block, BlockStateGenerator> blockStates = new HashMap<>();
        Map<ResourceLocation, Supplier<JsonElement>> models = new HashMap<>();
        Set<Item> skippedAutoModels = new HashSet<>();

        BlockModelGenerators blockModelGenerators = new BlockModelGenerators(
                generator -> registerBlockState(blockStates, generator),
                registerModel(models),
                skippedAutoModels::add);
        ItemModelGenerators itemModelGenerators = new ItemModelGenerators(registerModel(models));

        generateBlockStateModels(blockModelGenerators);
        generateItemModels(itemModelGenerators);
        addAutomaticBlockItemModels(blockStates.keySet(), models, skippedAutoModels);

        return CompletableFuture.allOf(
                saveCollection(cachedOutput, blockStates, block -> this.blockStatePathProvider.json(block.builtInRegistryHolder().key().location())),
                saveCollection(cachedOutput, models, this.modelPathProvider::json));
    }

    @Override
    public String getName() {
        return "NeoForge model provider";
    }

    private static void registerBlockState(Map<Block, BlockStateGenerator> blockStates, BlockStateGenerator generator) {
        Block block = generator.getBlock();
        BlockStateGenerator previous = blockStates.put(block, generator);
        if (previous != null) {
            throw new IllegalStateException("Duplicate blockstate definition for " + block);
        }
    }

    private static BiConsumer<ResourceLocation, Supplier<JsonElement>> registerModel(Map<ResourceLocation, Supplier<JsonElement>> models) {
        return (identifier, supplier) -> {
            Supplier<JsonElement> previous = models.put(identifier, supplier);
            if (previous != null) {
                throw new IllegalStateException("Duplicate model definition for " + identifier);
            }
        };
    }

    private static void addAutomaticBlockItemModels(Set<Block> blocks, Map<ResourceLocation, Supplier<JsonElement>> models, Set<Item> skippedAutoModels) {
        for (Block block : blocks) {
            Item item = Item.BY_BLOCK.get(block);
            if (item == null || skippedAutoModels.contains(item)) {
                continue;
            }

            ResourceLocation itemModel = ModelLocationUtils.getModelLocation(item);
            models.putIfAbsent(itemModel, new DelegatedModel(ModelLocationUtils.getModelLocation(block)));
        }
    }

    private static <T> CompletableFuture<?> saveCollection(CachedOutput cachedOutput,
                                                           Map<T, ? extends Supplier<JsonElement>> values,
                                                           Function<T, Path> pathFactory) {
        CompletableFuture<?>[] futures = values.entrySet().stream()
                .map(entry -> DataProvider.saveStable(cachedOutput, entry.getValue().get(), pathFactory.apply(entry.getKey())))
                .toArray(CompletableFuture[]::new);
        return CompletableFuture.allOf(futures);
    }
}
