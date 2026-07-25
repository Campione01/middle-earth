package net.jukoz.me.recipe;

import net.jukoz.me.item.ModDataComponentTypes;
import net.jukoz.me.item.ModEquipmentItems;
import net.jukoz.me.item.dataComponents.MountArmorAddonComponent;
import net.jukoz.me.item.items.armor.CustomAnimalArmorItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class MountArmorTopSkullAddonRecipe extends CustomRecipe {
    public MountArmorTopSkullAddonRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, Level world) {
        ItemStack itemStackArmor = ItemStack.EMPTY;
        ItemStack itemStackStick = ItemStack.EMPTY;
        ItemStack itemStackSkull= ItemStack.EMPTY;

        for(int i = 0; i < input.size(); ++i) {
            ItemStack itemStack2 = input.getItem(i);
            if (!itemStack2.isEmpty()) {
                if (itemStack2.is(ModEquipmentItems.WARG_MORDOR_PLATE_ARMOR) || itemStack2.is(ModEquipmentItems.WARG_GUNDABAD_PLATE_ARMOR)) {
                    if (!itemStackArmor.isEmpty()) {
                        return false;
                    }
                    itemStackArmor = itemStack2;
                }
                else if (itemStack2.is(Items.STICK)) {
                    if (!itemStackStick.isEmpty()) {
                        return false;
                    }
                    itemStackStick = itemStack2;
                }
                else if (itemStack2.is(Items.SKELETON_SKULL)) {
                    if (!itemStackSkull.isEmpty()) {
                        return false;
                    }
                    itemStackSkull = itemStack2;
                }
            }
        }
        return !itemStackArmor.isEmpty() && !itemStackStick.isEmpty() && !itemStackSkull.isEmpty();
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider lookup) {
        ItemStack itemStack = ItemStack.EMPTY;

        for(int i = 0; i < input.size(); ++i) {
            ItemStack itemStack2 = input.getItem(i);
            if (!itemStack2.isEmpty()) {
                if (itemStack2.getItem() instanceof CustomAnimalArmorItem) {
                    if (!itemStack.isEmpty()) {
                        return ItemStack.EMPTY;
                    }
                    itemStack = itemStack2.copy();
                }
            }
        }

        boolean sideArmorAddons = false;
        if(itemStack.get(ModDataComponentTypes.MOUNT_ARMOR_DATA) != null) {
            sideArmorAddons = itemStack.get(ModDataComponentTypes.MOUNT_ARMOR_DATA).sideArmorAddon();
        }

        ItemStack output = itemStack.copyWithCount(1);

        output.set(ModDataComponentTypes.MOUNT_ARMOR_DATA, new MountArmorAddonComponent(true, sideArmorAddons));

        return output;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializer.CUSTOM_MOUNT_ARMOR_TOP_SKULL_ADDON;
    }
}
