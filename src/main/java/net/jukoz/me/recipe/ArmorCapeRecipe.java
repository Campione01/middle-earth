package net.jukoz.me.recipe;

import net.jukoz.me.item.ModDataComponentTypes;
import net.jukoz.me.item.dataComponents.CapeDataComponent;
import net.jukoz.me.item.items.armor.CapeChestplateItem;
import net.jukoz.me.item.items.armor.CustomChestplateItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;


public class ArmorCapeRecipe extends CustomRecipe {

    public ArmorCapeRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, Level world) {
        ItemStack itemStackChest = ItemStack.EMPTY;
        ItemStack itemStackCape = ItemStack.EMPTY;

        for(int i = 0; i < input.size(); ++i) {
            ItemStack itemStack2 = input.getItem(i);
            if (!itemStack2.isEmpty()) {
                if (itemStack2.getItem() instanceof CustomChestplateItem) {
                    if (!itemStackChest.isEmpty()) {
                        return false;
                    }
                    itemStackChest = itemStack2;
                } else {
                    if (!(itemStack2.getItem() instanceof CapeChestplateItem)) {
                        return false;
                    }
                    itemStackCape = itemStack2;
                }
            }
        }
        return !itemStackChest.isEmpty() && !itemStackCape.isEmpty();
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider lookup) {
        ItemStack itemStack = ItemStack.EMPTY;
        ItemStack cape = ItemStack.EMPTY;

        for(int i = 0; i < input.size(); ++i) {
            ItemStack itemStack2 = input.getItem(i);
            if (!itemStack2.isEmpty()) {
                if (itemStack2.getItem() instanceof CustomChestplateItem) {
                    if (!itemStack.isEmpty()) {
                        return ItemStack.EMPTY;
                    }
                    itemStack = itemStack2.copy();
                } else {
                    if (!(itemStack2.getItem() instanceof CapeChestplateItem)) {
                        return ItemStack.EMPTY;
                    }
                    cape = itemStack2;
                }
            }
        }

        if (!itemStack.isEmpty()) {
            int color;
            if (cape.get(ModDataComponentTypes.DYE_DATA) != null){
                color = cape.get(ModDataComponentTypes.DYE_DATA).customRgb();
            } else {
                color = 0;
            }
            return CapeDataComponent.setCapeWithColor(itemStack,
                    cape.get(ModDataComponentTypes.CAPE_DATA).cape(),
                    color);
        } else {
            return ItemStack.EMPTY;
        }
    }

    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializer.CUSTOM_ARMOR_CAPE;
    }
}
