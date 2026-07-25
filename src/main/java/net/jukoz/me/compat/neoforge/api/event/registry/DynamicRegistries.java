package net.jukoz.me.compat.neoforge.api.event.registry;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class DynamicRegistries {
    private static final Map<ResourceKey<? extends Registry<?>>, Codec<?>> SYNCED_REGISTRIES = new LinkedHashMap<>();

    private DynamicRegistries() {
    }

    public static <T> void registerSynced(ResourceKey<Registry<T>> key, Codec<T> codec) {
        SYNCED_REGISTRIES.put(key, codec);
    }

    public static Map<ResourceKey<? extends Registry<?>>, Codec<?>> getSyncedRegistries() {
        return Collections.unmodifiableMap(SYNCED_REGISTRIES);
    }
}
