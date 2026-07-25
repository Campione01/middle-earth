package net.jukoz.me.item;

import net.jukoz.me.utils.NeoForgeRegistrationBridge;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.ModNatureBlocks;
import net.jukoz.me.item.utils.ModItemGroups;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PlaceOnWaterBlockItem;

public class ModNatureBlockItems {

    public static final Item SMALL_LILY_PADS = registerItem("small_lily_pads", new PlaceOnWaterBlockItem(ModNatureBlocks.SMALL_LILY_PADS, new Item.Properties()));
    public static final Item SMALL_FLOWERING_LILY_PADS = registerItem("small_flowering_lily_pads", new PlaceOnWaterBlockItem(ModNatureBlocks.SMALL_FLOWERING_LILY_PADS, new Item.Properties()));
    public static final Item LILY_PADS = registerItem("lily_pads", new PlaceOnWaterBlockItem(ModNatureBlocks.LILY_PADS, new Item.Properties()));
    public static final Item DUCKWEED = registerItem("duckweed", new PlaceOnWaterBlockItem(ModNatureBlocks.DUCKWEED, new Item.Properties()));

    public static final Item FLOATING_ICE = registerItem("floating_ice", new PlaceOnWaterBlockItem(ModNatureBlocks.FLOATING_ICE, new Item.Properties()));

    private static Item registerItem(String name, Item item) {
        ModItemGroups.NATURE_BLOCKS_CONTENTS.add(item.getDefaultInstance());
        return NeoForgeRegistrationBridge.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, name), item);
    }

    public static void registerModItems() {
        LoggerUtil.logDebugMsg("Registering Mod Nature Items for " + MiddleEarth.MOD_ID);
    }
}
