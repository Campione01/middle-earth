package net.jukoz.me.recipe;

import net.jukoz.me.item.ModDataComponentTypes;
import net.jukoz.me.item.items.armor.CustomAnimalArmorItem;
import net.jukoz.me.item.items.armor.CustomChestplateItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;


public class MountArmorAddonRemovalRecipe extends CustomRecipe {

    public MountArmorAddonRemovalRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> defaultedList = NonNullList.withSize(input.size(), ItemStack.EMPTY);

        for(int i = 0; i < defaultedList.size(); ++i) {
            ItemStack itemStack = input.getItem(i);
            if (itemStack.getItem().hasCraftingRemainingItem()) {
                defaultedList.set(i, new ItemStack(itemStack.getItem().getCraftingRemainingItem()));
            } else if (itemStack.getItem() instanceof ShearsItem) {
                defaultedList.set(i, itemStack.copyWithCount(1));
                break;
            }
        }
        return defaultedList;
    }

    @Override
    public boolean matches(CraftingInput input, Level world) {
        ItemStack itemStackArmor = ItemStack.EMPTY;
        ItemStack itemStackShears = ItemStack.EMPTY;

        for(int i = 0; i < input.size(); ++i) {
            ItemStack itemStack2 = input.getItem(i);
            if (!itemStack2.isEmpty()) {
                if (itemStack2.getItem() instanceof CustomAnimalArmorItem && itemStack2.get(ModDataComponentTypes.MOUNT_ARMOR_DATA) != null) {
                    if (!itemStackArmor.isEmpty()) {
                        return false;
                    }
                    itemStackArmor = itemStack2;
                } else {
                    if (!itemStack2.is(Items.SHEARS)) {
                        return false;
                    }
                    itemStackShears = itemStack2;
                }
            }
        }
        return !itemStackArmor.isEmpty() && !itemStackShears.isEmpty();
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider lookup) {
        ItemStack itemStack = ItemStack.EMPTY;

        for(int i = 0; i < input.size(); ++i) {
            ItemStack itemStack2 = input.getItem(i);
            if (!itemStack2.isEmpty()) {
                if (itemStack2.getItem() instanceof CustomAnimalArmorItem && itemStack2.get(ModDataComponentTypes.MOUNT_ARMOR_DATA) != null) {
                    if (!itemStack.isEmpty()) {
                        return ItemStack.EMPTY;
                    }

                    itemStack = itemStack2.copy();
                } else {
                    if (!itemStack2.is(Items.SHEARS)) {
                        return ItemStack.EMPTY;
                    }
                }
            }
        }

        if (!itemStack.isEmpty()) {
            itemStack.remove(ModDataComponentTypes.MOUNT_ARMOR_DATA);
            return itemStack;
        } else {
            return ItemStack.EMPTY;
        }
    }

    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializer.CUSTOM_MOUNT_ARMOR_ADDON_REMOVAL;
    }
}
