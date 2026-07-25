package net.jukoz.me.recipe;

import net.jukoz.me.utils.NeoForgeRegistrationBridge;


import net.jukoz.me.MiddleEarth;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;

public class ModRecipeSerializer<T extends Recipe<?>> {

    public static final SimpleCraftingRecipeSerializer<CustomArmorDyeRecipe> CUSTOM_ARMOR_DYE = register("custom_armor_dye", new SimpleCraftingRecipeSerializer<>(CustomArmorDyeRecipe::new));;
    public static final SimpleCraftingRecipeSerializer<ArmorCapeRecipe> CUSTOM_ARMOR_CAPE = register("custom_armor_cape", new SimpleCraftingRecipeSerializer<>(ArmorCapeRecipe::new));
    public static final SimpleCraftingRecipeSerializer<ArmorCapeRemovalRecipe> CUSTOM_ARMOR_CAPE_REMOVAL = register("custom_armor_cape_removal", new SimpleCraftingRecipeSerializer<>(ArmorCapeRemovalRecipe::new));
    public static final SimpleCraftingRecipeSerializer<ArmorHoodRecipe> CUSTOM_ARMOR_HOOD = register("custom_armor_hood", new SimpleCraftingRecipeSerializer<>(ArmorHoodRecipe::new));
    public static final SimpleCraftingRecipeSerializer<ArmorHoodRemovalRecipe> CUSTOM_ARMOR_HOOD_REMOVAL = register("custom_armor_hood_removal", new SimpleCraftingRecipeSerializer<>(ArmorHoodRemovalRecipe::new));
    public static final SimpleCraftingRecipeSerializer<MountArmorAddonRemovalRecipe> CUSTOM_MOUNT_ARMOR_ADDON_REMOVAL = register("custom_mount_armor_addon_removal", new SimpleCraftingRecipeSerializer<>(MountArmorAddonRemovalRecipe::new));
    public static final SimpleCraftingRecipeSerializer<MountArmorSideSkullAddonRecipe> CUSTOM_MOUNT_ARMOR_SIDE_SKULL_ADDON = register("custom_mount_armor_side_skull_addon", new SimpleCraftingRecipeSerializer<>(MountArmorSideSkullAddonRecipe::new));
    public static final SimpleCraftingRecipeSerializer<MountArmorTopSkullAddonRecipe> CUSTOM_MOUNT_ARMOR_TOP_SKULL_ADDON = register("custom_mount_armor_top_skull_addon", new SimpleCraftingRecipeSerializer<>(MountArmorTopSkullAddonRecipe::new));

    public static final SimpleCraftingRecipeSerializer<CustomItemDecorationRecipe> CUSTOM_ITEM_DECORATION = register("custom_item_decoration", new SimpleCraftingRecipeSerializer<>(CustomItemDecorationRecipe::new));

    static <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String id, S serializer) {
        return NeoForgeRegistrationBridge.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, id), serializer);
    }

    public static void registerRecipeSerializers(){
        LoggerUtil.logDebugMsg("Registering Mod Recipe Serializers for " + MiddleEarth.MOD_ID);
    }
}
