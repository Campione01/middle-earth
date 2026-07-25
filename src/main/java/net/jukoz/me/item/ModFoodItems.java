package net.jukoz.me.item;

import net.jukoz.me.utils.NeoForgeRegistrationBridge;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.ModNatureBlocks;
import net.jukoz.me.item.items.OrcishFoodItem;
import net.jukoz.me.item.utils.ModItemGroups;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;

public class ModFoodItems {

    public static final Item LEMBAS = registerItem("lembas",
            new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(20).saturationModifier(1).build())));

    public static final Item RAW_SWAN = registerItem("raw_swan",
            new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(3).saturationModifier(0.4F).effect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F).build())));
    public static final Item COOKED_SWAN = registerItem("cooked_swan",
            new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(8).saturationModifier(0.8F).build())));

    public static final Item RAW_VENISON = registerItem("raw_venison",
            new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(2).saturationModifier(0.4F).build())));
    public static final Item COOKED_VENISON = registerItem("cooked_venison",
            new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(6).saturationModifier(0.8F).build())));

    public static final Item RAW_HORSE = registerItem("raw_horse",
            new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(3).saturationModifier(0.4F).build())));
    public static final Item COOKED_HORSE = registerItem("cooked_horse",
            new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(8).saturationModifier(0.8F).build())));

    public static final Item MAGGOTY_BREAD = registerItem("maggoty_bread",
            new OrcishFoodItem(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(5).saturationModifier(1.2f).build())));
    public static final Item TOUGH_BERRIES = registerItem("tough_berries",
            new ItemNameBlockItem(ModNatureBlocks.TOUGH_BERRY_BUSH, new Item.Properties()));
    public static final Item STRAWBERRY = registerItem("strawberry",
            new ItemNameBlockItem(ModNatureBlocks.STRAWBERRY_BUSH,
                    new Item.Properties().food(
                            new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).build())));

    public static final Item TOMATO = registerItem("tomato",
            new Item(new Item.Properties().food(
                            new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));
    public static final Item BELL_PEPPER = registerItem("bell_pepper",
            new Item(new Item.Properties().food(
                            new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));
    public static final Item CUCUMBER = registerItem("cucumber",
            new Item(new Item.Properties().food(
                            new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));
    public static final Item GARLIC = registerItem("garlic",
            new ItemNameBlockItem(ModNatureBlocks.GARLIC_CROP,
                    new Item.Properties().food(
                            new FoodProperties.Builder().nutrition(2).saturationModifier(0.2f).build())));
    public static final Item LEEK = registerItem("leek",
            new ItemNameBlockItem(ModNatureBlocks.LEEK_CROP,
                    new Item.Properties().food(
                            new FoodProperties.Builder().nutrition(2).saturationModifier(0.2f).build())));
    public static final Item LETTUCE = registerItem("lettuce",
            new Item(new Item.Properties().food(
                            new FoodProperties.Builder().nutrition(3).saturationModifier(0.3f).build())));
    public static final Item ONION = registerItem("onion",
            new ItemNameBlockItem(ModNatureBlocks.ONION_CROP,
                    new Item.Properties().food(
                            new FoodProperties.Builder().nutrition(2).saturationModifier(0.2f).build())));

    public static final Item BERRY_PIE = registerItem("berry_pie",
            new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(8).saturationModifier(0.5f).build())));
    public static final Item BOILED_EGG = registerItem("boiled_egg",
            new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(3).saturationModifier(0.6f).build())));
    public static final Item FISH_STEW = registerItem("fish_stew",
            new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(8).saturationModifier(1.0f).usingConvertsTo(Items.BOWL).build()).stacksTo(1)));
    public static final Item MEAT_BOWL = registerItem("meat_bowl",
            new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(12).saturationModifier(1.0f).usingConvertsTo(Items.BOWL).build()).stacksTo(1)));
    public static final Item MEAT_EGG_MEAL = registerItem("meat_egg_meal",
            new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(9).saturationModifier(0.9f).usingConvertsTo(Items.BOWL).build()).stacksTo(1)));
    public static final Item MEAT_SKEWER = registerItem("meat_skewer",
            new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(5).saturationModifier(0.6f).usingConvertsTo(Items.STICK).build()).stacksTo(8)));
    public static final Item COOKED_MEAT_SKEWER = registerItem("cooked_meat_skewer",
            new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(10).saturationModifier(0.8f).usingConvertsTo(Items.STICK).build()).stacksTo(8)));
    public static final Item POULTRY_MEAL = registerItem("poultry_meal",
            new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(8).saturationModifier(0.8f).usingConvertsTo(Items.BOWL).build()).stacksTo(8)));
    public static final Item VEGETABLE_SKEWER = registerItem("vegetable_skewer",
            new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(3).saturationModifier(0.3f).usingConvertsTo(Items.STICK).build()).stacksTo(8)));
    public static final Item COOKED_VEGETABLE_SKEWER = registerItem("cooked_vegetable_skewer",
            new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(6).saturationModifier(0.6f).usingConvertsTo(Items.STICK).build()).stacksTo(8)));
    public static final Item VEGETABLE_SOUP = registerItem("vegetable_soup",
            new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(7).saturationModifier(0.6f).usingConvertsTo(Items.BOWL).build()).stacksTo(1)));

    public static final Item SACK_OF_HORSEFEED = registerItem("sack_of_horsefeed",
            new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build())));


    private static Item registerItem(String name, Item item) {
        ModItemGroups.FOOD_CONTENTS.add(item.getDefaultInstance());
        return NeoForgeRegistrationBridge.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, name), item);
    }

    public static void registerModItems() {
        LoggerUtil.logDebugMsg("Registering Mod Food Items for " + MiddleEarth.MOD_ID);
    }
}
