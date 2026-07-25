package net.jukoz.me.recipe;

import net.jukoz.me.utils.NeoForgeRegistrationBridge;

import net.jukoz.me.MiddleEarth;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipes {
    public static RecipeType<ArtisanRecipe> ARTISAN_TABLE;
    public static RecipeType<AnvilShapingRecipe> ANVIL_SHAPING;
    public static RecipeType<AlloyingRecipe> FORGE;

    public static void registerRecipes() {
        NeoForgeRegistrationBridge.register(BuiltInRegistries.RECIPE_SERIALIZER,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, AlloyingRecipe.Serializer.ID),
                AlloyingRecipe.Serializer.INSTANCE);
        FORGE = NeoForgeRegistrationBridge.register(BuiltInRegistries.RECIPE_TYPE,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, AlloyingRecipe.Type.ID),
                AlloyingRecipe.Type.INSTANCE);

        NeoForgeRegistrationBridge.register(BuiltInRegistries.RECIPE_SERIALIZER,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, AnvilShapingRecipe.Serializer.ID),
                AnvilShapingRecipe.Serializer.INSTANCE);
        ANVIL_SHAPING = NeoForgeRegistrationBridge.register(BuiltInRegistries.RECIPE_TYPE,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, AnvilShapingRecipe.Type.ID),
                AnvilShapingRecipe.Type.INSTANCE);

        NeoForgeRegistrationBridge.register(BuiltInRegistries.RECIPE_SERIALIZER,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, ArtisanRecipe.Serializer.ID),
                ArtisanRecipe.Serializer.INSTANCE);
        ARTISAN_TABLE = NeoForgeRegistrationBridge.register(BuiltInRegistries.RECIPE_TYPE,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, ArtisanRecipe.Type.ID),
                ArtisanRecipe.Type.INSTANCE);
    }
}
