package net.jukoz.me.utils;

import net.minecraft.core.Holder;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class NeoForgeRegistrationBridge {
    private static final List<PendingRegistration<?>> PENDING = new ArrayList<>();
    private static final Set<ResourceKey<? extends Registry<?>>> FIRED_REGISTRIES = new HashSet<>();
    private static final Map<ResourceKey<? extends Registry<?>>, Integer> QUEUED_COUNTS = new LinkedHashMap<>();

    private NeoForgeRegistrationBridge() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(NeoForgeRegistrationBridge::onRegister);
        enableStaticIntrusiveRegistration(BuiltInRegistries.BLOCK);
        enableStaticIntrusiveRegistration(BuiltInRegistries.ITEM);
        enableStaticIntrusiveRegistration(BuiltInRegistries.ENTITY_TYPE);
        enableStaticIntrusiveRegistration(BuiltInRegistries.BLOCK_ENTITY_TYPE);
        enableStaticIntrusiveRegistration(BuiltInRegistries.FLUID);
        LoggerUtil.logDebugMsg("NeoForge bridge listener registered");
    }

    public static <V, T extends V> T register(Registry<V> registry, ResourceLocation id, T entry) {
        enqueue(registry.key(), id, entry);
        return entry;
    }

    public static <V, T extends V> T register(Registry<V> registry, String id, T entry) {
        return register(registry, ResourceLocation.parse(id), entry);
    }

    public static <V, T extends V> Holder<V> registerForHolder(Registry<V> registry, ResourceLocation id, T entry) {
        enqueue(registry.key(), id, entry);
        return DeferredHolder.create(registry.key(), id);
    }

    private static synchronized <T> void enqueue(ResourceKey<? extends Registry<T>> registryKey, ResourceLocation id, T entry) {
        ResourceKey<? extends Registry<?>> registryKeyView = castRegistryKey(registryKey);
        if (FIRED_REGISTRIES.contains(registryKey)) {
            throw new IllegalStateException("Registry event already fired for " + registryKey + " while queuing " + id);
        }
        PENDING.add(new PendingRegistration<>(registryKey, id, entry));
        QUEUED_COUNTS.merge(registryKeyView, 1, Integer::sum);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static synchronized void onRegister(RegisterEvent event) {
        FIRED_REGISTRIES.add(event.getRegistryKey());
        int registered = 0;
        Iterator<PendingRegistration<?>> iterator = PENDING.iterator();
        while (iterator.hasNext()) {
            PendingRegistration pending = iterator.next();
            if (pending.registryKey.equals(event.getRegistryKey())) {
                event.register(pending.registryKey, pending.id, () -> pending.entry);
                iterator.remove();
                registered++;
            }
        }
        if (registered > 0 || isObservedRegistry(event.getRegistryKey())) {
            int queued = QUEUED_COUNTS.getOrDefault(event.getRegistryKey(), 0);
            LoggerUtil.logInfoMsg("NeoForge bridge registry event " + event.getRegistryKey().location()
                    + ": queued=" + queued + ", registered=" + registered + ", pending=" + PENDING.size());
        }
    }

    private static boolean isObservedRegistry(ResourceKey<? extends Registry<?>> registryKey) {
        return registryKey.equals(BuiltInRegistries.BLOCK.key()) || registryKey.equals(BuiltInRegistries.ITEM.key());
    }

    private static void enableStaticIntrusiveRegistration(Registry<?> registry) {
        if (registry instanceof MappedRegistry<?> mappedRegistry) {
            mappedRegistry.unfreeze();
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> ResourceKey<? extends Registry<?>> castRegistryKey(ResourceKey<? extends Registry<T>> registryKey) {
        return (ResourceKey<? extends Registry<?>>) (ResourceKey<?>) registryKey;
    }

    private record PendingRegistration<T>(ResourceKey<? extends Registry<T>> registryKey, ResourceLocation id, T entry) {
    }
}
