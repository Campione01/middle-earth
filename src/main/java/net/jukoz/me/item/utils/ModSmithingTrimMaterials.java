package net.jukoz.me.item.utils;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.StoneBlockSets;
import net.jukoz.me.item.ModResourceItems;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import java.util.Map;

public class ModSmithingTrimMaterials {
    public static final ResourceKey<TrimMaterial> BRONZE = of("bronze");
    public static final ResourceKey<TrimMaterial> BURZUM_STEEL = of("burzum_steel");
    public static final ResourceKey<TrimMaterial> CRUDE = of("crude");
    public static final ResourceKey<TrimMaterial> EDHEL_STEEL = of("edhel_steel");
    public static final ResourceKey<TrimMaterial> JADE = of("jade");
    public static final ResourceKey<TrimMaterial> KHAZAD_STEEL = of("khazad_steel");
    public static final ResourceKey<TrimMaterial> LEAD = of("lead");
    public static final ResourceKey<TrimMaterial> MITHRIL = of("mithril");
    public static final ResourceKey<TrimMaterial> SILVER = of("silver");
    public static final ResourceKey<TrimMaterial> STEEL = of("steel");
    public static final ResourceKey<TrimMaterial> TIN = of("tin");

    public static void bootstrap(BootstrapContext<TrimMaterial> registry) {
        register(registry, BRONZE, ModResourceItems.BRONZE_INGOT, Style.EMPTY.withColor(13151627), 0.005f);
        register(registry, BURZUM_STEEL, ModResourceItems.BURZUM_STEEL_INGOT, Style.EMPTY.withColor(5985355), 0.008f);
        register(registry, CRUDE, ModResourceItems.CRUDE_INGOT, Style.EMPTY.withColor(7560021), 0.007f);
        register(registry, EDHEL_STEEL, ModResourceItems.EDHEL_STEEL_INGOT, Style.EMPTY.withColor(15921385), 0.009f);
        register(registry, JADE, StoneBlockSets.JADEITE.base().asItem(), Style.EMPTY.withColor(5869927), 0.001f);
        register(registry, KHAZAD_STEEL, ModResourceItems.KHAZAD_STEEL_INGOT, Style.EMPTY.withColor(6778743), 0.011f);
        register(registry, LEAD, ModResourceItems.LEAD_INGOT, Style.EMPTY.withColor(6384761), 0.003f);
        register(registry, MITHRIL, ModResourceItems.MITHRIL_INGOT, Style.EMPTY.withColor(14278631), 0.012f);
        register(registry, SILVER, ModResourceItems.SILVER_INGOT, Style.EMPTY.withColor(15397618), 0.004f);
        register(registry, STEEL, ModResourceItems.STEEL_INGOT, Style.EMPTY.withColor(0xECECEC), 0.006f);
        register(registry, TIN, ModResourceItems.TIN_INGOT, Style.EMPTY.withColor(13026492), 0.002f);
    }

    private static void register(BootstrapContext<TrimMaterial> registry, ResourceKey<TrimMaterial> key, Item ingredient, Style style, float itemModelIndex) {
        register(registry, key, ingredient, style, itemModelIndex, Map.of());
    }

    private static void register(BootstrapContext<TrimMaterial> registry, ResourceKey<TrimMaterial> key, Item ingredient, Style style, float itemModelIndex, Map<Holder<ArmorMaterial>, String> overrideArmorMaterials) {
        TrimMaterial armorTrimMaterial = TrimMaterial.create(key.location().getPath(), ingredient, itemModelIndex, Component.translatable(Util.makeDescriptionId("trim_material", key.location())).withStyle(style), overrideArmorMaterials);
        registry.register(key, armorTrimMaterial);
    }

    private static ResourceKey<TrimMaterial> of(String id) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, id));
    }
}
