package net.jukoz.me.item.utils;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.item.ModResourceItems;
import net.jukoz.me.item.ModToolItems;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.TrimPattern;
import java.util.Map;
import java.util.Optional;

public class ModSmithingTrimPatterns {
    public static final ResourceKey<TrimPattern> SMITHING_PART = of("smithing_part");

    public static void bootstrap(BootstrapContext<TrimPattern> registry) {
        register(registry, ModToolItems.SMITHING_HAMMER, SMITHING_PART);
    }


    public static Optional<Holder.Reference<TrimPattern>> get(HolderLookup.Provider registriesLookup, ItemStack stack) {
        return registriesLookup.lookupOrThrow(Registries.TRIM_PATTERN).listElements().filter(pattern -> stack.is(((TrimPattern)pattern.value()).templateItem())).findFirst();
    }

    public static void register(BootstrapContext<TrimPattern> registry, Item template, ResourceKey<TrimPattern> key) {
        TrimPattern armorTrimPattern = new TrimPattern(key.location(), BuiltInRegistries.ITEM.wrapAsHolder(template), Component.translatable(Util.makeDescriptionId("trim_pattern", key.location())), false);
        registry.register(key, armorTrimPattern);
    }

    private static ResourceKey<TrimPattern> of(String id) {
        return ResourceKey.create(Registries.TRIM_PATTERN, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, id));
    }
}
