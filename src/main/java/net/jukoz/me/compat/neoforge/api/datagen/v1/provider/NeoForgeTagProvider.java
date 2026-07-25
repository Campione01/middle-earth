package net.jukoz.me.compat.neoforge.api.datagen.v1.provider;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.jukoz.me.compat.neoforge.api.datagen.v1.NeoForgeDataOutput;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public abstract class NeoForgeTagProvider<T> implements DataProvider {
    private final NeoForgeDataOutput output;
    private final CompletableFuture<HolderLookup.Provider> registriesFuture;
    private final ResourceKey<? extends Registry<T>> registryKey;
    private final Function<T, ResourceLocation> keyGetter;
    private final Map<TagKey<T>, TagAppender<T>> tags = new LinkedHashMap<>();

    protected NeoForgeTagProvider(NeoForgeDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture,
                                ResourceKey<? extends Registry<T>> registryKey, Function<T, ResourceLocation> keyGetter) {
        this.output = output;
        this.registriesFuture = registriesFuture;
        this.registryKey = registryKey;
        this.keyGetter = keyGetter;
    }

    protected abstract void addTags(HolderLookup.Provider provider);

    protected TagAppender<T> tag(TagKey<T> tag) {
        return tags.computeIfAbsent(tag, unused -> new TagAppender<>(keyGetter));
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        return registriesFuture.thenCompose(provider -> {
            addTags(provider);
            PackOutput.PathProvider pathProvider = this.output.createRegistryTagsPathProvider(registryKey);
            List<CompletableFuture<?>> futures = new ArrayList<>();
            tags.forEach((tag, appender) -> futures.add(DataProvider.saveStable(output, appender.toJson(), pathProvider.json(tag.location()))));
            return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
        });
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    public static class TagAppender<T> {
        private final Function<T, ResourceLocation> keyGetter;
        private final List<String> values = new ArrayList<>();

        private TagAppender(Function<T, ResourceLocation> keyGetter) {
            this.keyGetter = keyGetter;
        }

        @SafeVarargs
        public final TagAppender<T> add(T... values) {
            for (T value : values) {
                ResourceLocation key = keyGetter.apply(value);
                if (key != null) {
                    this.values.add(key.toString());
                }
            }
            return this;
        }

        public TagAppender<T> addTag(TagKey<T> tag) {
            values.add("#" + tag.location());
            return this;
        }

        public TagAppender<T> forceAddTag(TagKey<T> tag) {
            values.add("#" + tag.location());
            return this;
        }

        public TagAppender<T> addOptional(ResourceLocation id) {
            values.add(id.toString());
            return this;
        }

        public TagAppender<T> addOptionalTag(ResourceLocation id) {
            values.add("#" + id);
            return this;
        }

        private JsonObject toJson() {
            JsonObject json = new JsonObject();
            json.addProperty("replace", false);
            JsonArray values = new JsonArray();
            this.values.forEach(values::add);
            json.add("values", values);
            return json;
        }
    }

    public abstract static class BlockTagProvider extends NeoForgeTagProvider<Block> {
        protected BlockTagProvider(NeoForgeDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture, Registries.BLOCK, BuiltInRegistries.BLOCK::getKey);
        }
    }

    public abstract static class ItemTagProvider extends NeoForgeTagProvider<Item> {
        protected ItemTagProvider(NeoForgeDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture, Registries.ITEM, BuiltInRegistries.ITEM::getKey);
        }
    }
}
