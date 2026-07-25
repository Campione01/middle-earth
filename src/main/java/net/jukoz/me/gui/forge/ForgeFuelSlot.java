package net.jukoz.me.gui.forge;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;

public class ForgeFuelSlot extends Slot {
    private final AbstractContainerMenu handler;

    public ForgeFuelSlot(Container inventory, AbstractContainerMenu handler, int index, int x, int y) {
        super(inventory, index, x, y);
        this.handler = handler;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return AbstractFurnaceBlockEntity.isFuel(stack);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return super.getMaxStackSize(stack);
    }
}
