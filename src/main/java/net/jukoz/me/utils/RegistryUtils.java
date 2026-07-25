package net.jukoz.me.utils;

import net.jukoz.me.MiddleEarth;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

public class RegistryUtils {
    public static <V, T extends V> T register(Registry<V> registry, String name, T entry) {
        return NeoForgeRegistrationBridge.register(registry, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, name), entry);
    }
}
