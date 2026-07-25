package net.jukoz.me.recipe;

import net.jukoz.me.item.ModDataComponentTypes;
import net.jukoz.me.item.dataComponents.HoodDataComponent;
import net.jukoz.me.item.items.armor.CustomHelmetItem;
import net.jukoz.me.item.items.armor.HoodHelmetItem;
import net.jukoz.me.item.utils.armor.hoods.ModHoodStates;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;


public class ArmorHoodRecipe extends CustomRecipe {
    public ArmorHoodRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, Level world) {
        ItemStack itemStackHelmet = ItemStack.EMPTY;
        ItemStack itemStackHood = ItemStack.EMPTY;

        for(int i = 0; i < input.size(); ++i) {
            ItemStack itemStack2 = input.getItem(i);
            if (!itemStack2.isEmpty()) {
                if (itemStack2.getItem() instanceof CustomHelmetItem) {
                    if (!itemStackHelmet.isEmpty()) {
                        return false;
                    }
                    itemStackHelmet = itemStack2;
                } else {
                    if (!(itemStack2.getItem() instanceof HoodHelmetItem)) {
                        return false;
                    }
                    itemStackHood = itemStack2;
                }
            }
        }
        return !itemStackHelmet.isEmpty() && !itemStackHood.isEmpty();
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider lookup) {
        ItemStack itemStack = ItemStack.EMPTY;
        ItemStack hood = ItemStack.EMPTY;

        for(int i = 0; i < input.size(); ++i) {
            ItemStack itemStack2 = input.getItem(i);
            if (!itemStack2.isEmpty()) {
                if (itemStack2.getItem() instanceof CustomHelmetItem) {
                    if (!itemStack.isEmpty()) {
                        return ItemStack.EMPTY;
                    }

                    itemStack = itemStack2.copy();
                } else {
                    if (!(itemStack2.getItem() instanceof HoodHelmetItem)) {
                        return ItemStack.EMPTY;
                    }
                    hood = itemStack2;
                }
            }
        }

        if (!itemStack.isEmpty()) {
            int color;
            if (hood.get(ModDataComponentTypes.DYE_DATA) != null){
                color = hood.get(ModDataComponentTypes.DYE_DATA).customRgb();
            } else {
                color = 0;
            }
            if (hood.get(ModDataComponentTypes.HOOD_DATA).hood().getConstantState() == ModHoodStates.DOWN){
                return HoodDataComponent.setHoodWithcolor(itemStack, true, hood.get(ModDataComponentTypes.HOOD_DATA).hood(), color);
            } else {
                return HoodDataComponent.setHoodWithcolor(itemStack, false, hood.get(ModDataComponentTypes.HOOD_DATA).hood(), color);
            }
        } else {
            return ItemStack.EMPTY;
        }
    }

    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializer.CUSTOM_ARMOR_HOOD;
    }
}
