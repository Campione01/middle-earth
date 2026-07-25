package net.jukoz.me.recipe;

import com.google.common.collect.Lists;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.item.dataComponents.CustomDyeableDataComponent;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import java.util.ArrayList;
import java.util.List;

public class CustomArmorDyeRecipe extends CustomRecipe {
    public CustomArmorDyeRecipe(CraftingBookCategory craftingRecipeCategory) {
        super(craftingRecipeCategory);
    }

    @Override
    public boolean matches(CraftingInput craftingRecipeInput, Level world) {
        ItemStack itemStack = ItemStack.EMPTY;
        ArrayList<ItemStack> list = Lists.newArrayList();

        for (int i = 0; i < craftingRecipeInput.size(); ++i) {
            ItemStack itemStack2 = craftingRecipeInput.getItem(i);
            if (itemStack2.isEmpty()) continue;
            if (itemStack2.is(ModTags.DYEABLE)) {
                if (!itemStack.isEmpty()) {
                    return false;
                }
                itemStack = itemStack2;
                continue;
            }
            if (itemStack2.getItem() instanceof DyeItem) {
                list.add(itemStack2);
                continue;
            }
            return false;
        }
        return !itemStack.isEmpty() && !list.isEmpty();
    }

    @Override
    public ItemStack assemble(CraftingInput craftingRecipeInput, HolderLookup.Provider lookup) {
        ArrayList<DyeItem> list = Lists.newArrayList();
        ItemStack itemStack = ItemStack.EMPTY;

        for (int i = 0; i < craftingRecipeInput.size(); ++i) {
            ItemStack itemStack2 = craftingRecipeInput.getItem(i);
            if (itemStack2.isEmpty()) continue;
            if (itemStack2.is(ModTags.DYEABLE)) {
                if (!itemStack.isEmpty()) {
                    return ItemStack.EMPTY;
                }
                itemStack = itemStack2.copy();
                continue;
            }
            Item item = itemStack2.getItem();
            if (item instanceof DyeItem dyeItem) {
                list.add(dyeItem);
                continue;
            }
            return ItemStack.EMPTY;
        }
        if (itemStack.isEmpty() || list.isEmpty()) {
            return ItemStack.EMPTY;
        }
        return CustomDyeableDataComponent.setColor(itemStack, list);
    }

    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializer.CUSTOM_ARMOR_DYE;
    }
}
