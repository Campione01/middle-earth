package net.jukoz.me.compat.neoforge.api.datagen.v1.provider;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public abstract class NeoForgeRecipeProvider extends RecipeProvider {
    protected NeoForgeRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    public static Criterion<InventoryChangeTrigger.TriggerInstance> has(ItemLike item) {
        return RecipeProvider.has(item);
    }

    public static Criterion<InventoryChangeTrigger.TriggerInstance> has(MinMaxBounds.Ints count, ItemLike item) {
        return RecipeProvider.has(count, item);
    }

    public static String getHasName(ItemLike item) {
        return RecipeProvider.getHasName(item);
    }

    public static String getItemName(ItemLike item) {
        return RecipeProvider.getItemName(item);
    }

    public static String getItemName(Item item) {
        return RecipeProvider.getItemName(item);
    }
}
