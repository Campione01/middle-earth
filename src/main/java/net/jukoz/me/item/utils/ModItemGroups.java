package net.jukoz.me.item.utils;

import net.jukoz.me.utils.NeoForgeRegistrationBridge;

import net.jukoz.me.compat.neoforge.api.itemgroup.v1.NeoForgeItemGroup;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.ModBlocks;
import net.jukoz.me.block.StoneBlockSets;
import net.jukoz.me.block.ModNatureBlocks;
import net.jukoz.me.block.WoodBlockSets;
import net.jukoz.me.item.*;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import java.util.LinkedList;
import java.util.List;

public class ModItemGroups {

    public static final List<ItemStack> STONE_BLOCKS_CONTENTS = new LinkedList<>();
    public static final CreativeModeTab STONE_BLOCKS = NeoForgeItemGroup.builder()
            .title(Component.translatable("itemGroup." + MiddleEarth.MOD_ID + ".stone_blocks"))
            .icon(() -> new ItemStack(StoneBlockSets.CALCITE_BRICKS.base().asItem()))
            .displayItems((displayContext, entries) -> {
                for (ItemStack item : STONE_BLOCKS_CONTENTS) {
                    entries.accept(item);
                }
            })
            .build();

    public static final List<ItemStack> WOOD_BLOCKS_CONTENTS = new LinkedList<>();
    public static final CreativeModeTab WOOD_BLOCKS = NeoForgeItemGroup.builder()
            .title(Component.translatable("itemGroup." + MiddleEarth.MOD_ID + ".wood_blocks"))
            .icon(() -> new ItemStack(WoodBlockSets.WILLOW.log().asItem()))
            .displayItems((displayContext, entries) -> {
                for (ItemStack item : WOOD_BLOCKS_CONTENTS) {
                    entries.accept(item);
                }
            })
            .build();

    public static final List<ItemStack> MISC_BLOCKS_CONTENTS = new LinkedList<>();
    public static final CreativeModeTab MISC_BLOCKS = NeoForgeItemGroup.builder()
            .title(Component.translatable("itemGroup." + MiddleEarth.MOD_ID + ".misc_blocks"))
            .icon(() -> new ItemStack(ModBlocks.STRAW_BLOCK.asItem()))
            .displayItems((displayContext, entries) -> {
                for (ItemStack item : MISC_BLOCKS_CONTENTS) {
                    entries.accept(item);
                }
            })
            .build();

    public static final List<ItemStack> DECORATIVES_BLOCKS_CONTENT = new LinkedList<>();
    public static final CreativeModeTab DECORATIVES_BLOCKS = NeoForgeItemGroup.builder()
            .title(Component.translatable("itemGroup." + MiddleEarth.MOD_ID + ".decorative_blocks"))
            .icon(() -> new ItemStack(ModDecorativeItems.DWARVEN_LANTERN))
            .displayItems((displayContext, entries) -> {
                for (ItemStack item : DECORATIVES_BLOCKS_CONTENT) {
                    entries.accept(item);
                }
            })
            .build();

    public static final List<ItemStack> NATURE_BLOCKS_CONTENTS = new LinkedList<>();
    public static final CreativeModeTab NATURE_BLOCKS = NeoForgeItemGroup.builder()
            .title(Component.translatable("itemGroup." + MiddleEarth.MOD_ID + ".nature_blocks"))
            .icon(() -> new ItemStack(ModNatureBlocks.HEATHER.asItem()))
            .displayItems((displayContext, entries) -> {
                for (ItemStack item : NATURE_BLOCKS_CONTENTS) {
                    entries.accept(item);
                }
            })
            .build();

    public static final List<ItemStack> FOOD_CONTENTS = new LinkedList<>();
    public static final CreativeModeTab FOOD = NeoForgeItemGroup.builder()
            .title(Component.translatable("itemGroup." + MiddleEarth.MOD_ID + ".food_items"))
            .icon(() -> new ItemStack(ModFoodItems.LEMBAS))
            .displayItems((displayContext, entries) -> {
                for (ItemStack item : FOOD_CONTENTS) {
                    entries.accept(item);
                }
            })
            .build();

    public static final List<ItemStack> WEAPONS_CONTENTS = new LinkedList<>();
    public static final CreativeModeTab WEAPONS = NeoForgeItemGroup.builder()
            .title(Component.translatable("itemGroup." + MiddleEarth.MOD_ID + ".weapon_items"))
            .icon(() -> new ItemStack(ModWeaponItems.GONDORIAN_SWORD))
            .displayItems((displayContext, entries) -> {
                for (ItemStack item : WEAPONS_CONTENTS) {
                    entries.accept(item);
                }
            })
            .build();

    public static final List<ItemStack> EQUIPMENT_CONTENTS = new LinkedList<>();
    public static final CreativeModeTab EQUIPMENT = NeoForgeItemGroup.builder()
            .title(Component.translatable("itemGroup." + MiddleEarth.MOD_ID + ".equipment_items"))
            .icon(() -> new ItemStack(ModEquipmentItems.GONDORIAN_FOUNTAIN_GUARD_HELMET))
            .displayItems((displayContext, entries) -> {
                for (ItemStack item : EQUIPMENT_CONTENTS) {
                    entries.accept(item);
                }
            })
            .build();

    public static final List<ItemStack> TOOLS_CONTENTS = new LinkedList<>();
    public static final CreativeModeTab TOOLS = NeoForgeItemGroup.builder()
            .title(Component.translatable("itemGroup." + MiddleEarth.MOD_ID + ".tool_items"))
            .icon(() -> new ItemStack(ModToolItems.KHAZAD_STEEL_PICKAXE))
            .displayItems((displayContext, entries) -> {
                for (ItemStack item : TOOLS_CONTENTS) {
                    entries.accept(item);
                }
            })
            .build();

    public static final List<ItemStack> RESOURCES_CONTENTS = new LinkedList<>();
    public static final CreativeModeTab RESOURCES = NeoForgeItemGroup.builder()
            .title(Component.translatable("itemGroup." + MiddleEarth.MOD_ID + ".resource_items"))
            .icon(() -> new ItemStack(ModResourceItems.MITHRIL_INGOT))
            .displayItems((displayContext, entries) -> {
                for (ItemStack item : RESOURCES_CONTENTS) {
                    entries.accept(item);
                }
            })
            .build();

    public static final List<ItemStack> SPAWN_EGGS_CONTENTS = new LinkedList<>();

    public static final CreativeModeTab SPAWN_EGGS = NeoForgeItemGroup.builder()
            .title(Component.translatable("itemGroup." + MiddleEarth.MOD_ID + ".spawn_egg_items"))
            .icon(() -> new ItemStack(ModEggItems.HOBBIT_CIVILIAN_SPAWN_EGG))
            .displayItems((displayContext, entries) -> {
                for (ItemStack item : SPAWN_EGGS_CONTENTS) {
                    entries.accept(item);
                }
            })
            .build();

    public static void register() {
        NeoForgeRegistrationBridge.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "stone_blocks"), STONE_BLOCKS);
        NeoForgeRegistrationBridge.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "wood_blocks"), WOOD_BLOCKS);
        NeoForgeRegistrationBridge.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "misc_blocks"), MISC_BLOCKS);
        NeoForgeRegistrationBridge.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "decorative"), DECORATIVES_BLOCKS);
        NeoForgeRegistrationBridge.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "nature_blocks"), NATURE_BLOCKS);
        NeoForgeRegistrationBridge.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "food_items"), FOOD);
        NeoForgeRegistrationBridge.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "weapon_items"), WEAPONS);
        NeoForgeRegistrationBridge.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "equipment_items"), EQUIPMENT);
        NeoForgeRegistrationBridge.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "tool_items"), TOOLS);
        NeoForgeRegistrationBridge.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "resource_items"), RESOURCES);
        NeoForgeRegistrationBridge.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "spawn_egg_items"), SPAWN_EGGS);
    }

}
