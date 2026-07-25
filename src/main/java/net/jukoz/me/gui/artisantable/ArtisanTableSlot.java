package net.jukoz.me.gui.artisantable;

import net.jukoz.me.item.ModResourceItems;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ArtisanTableSlot extends Slot {
    private boolean enabled = true;
    private InputType inputType = InputType.ANY;

    public ArtisanTableSlot(Container inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean isActive() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setInputType(InputType inputType) {
        this.inputType = inputType;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        if(!isActive()) return false;
        if(inputType == InputType.ANY) return super.mayPlace(stack);

        else if(inputType == InputType.HANDLE && !(stack.is(Items.STICK) || stack.is(ModResourceItems.ROD))) {
            return false;
        }
        else if(inputType == InputType.HILT && !(stack.is(ModResourceItems.SWORD_HILT))) {
            return false;
        }
        else if(inputType == InputType.BLADE && !(stack.is(ModResourceItems.SHORT_BLADE) ||
                stack.is(ModResourceItems.BLADE) || stack.is(ModResourceItems.LONG_BLADE))) {
            return false;
        }
        else if(inputType == InputType.AXE && !(stack.is(ModResourceItems.AXE_HEAD))) {
            return false;
        }
        else if(inputType == InputType.PICKAXE && !(stack.is(ModResourceItems.PICKAXE_HEAD))) {
            return false;
        }
        else if(inputType == InputType.SHOVEL && !(stack.is(ModResourceItems.SHOVEL_HEAD))) {
            return false;
        }
        else if(inputType == InputType.HOE && !(stack.is(ModResourceItems.HOE_HEAD))) {
            return false;
        }

        return super.mayPlace(stack);
    }
}
