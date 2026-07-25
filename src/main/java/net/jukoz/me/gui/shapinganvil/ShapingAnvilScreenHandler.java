package net.jukoz.me.gui.shapinganvil;

import net.jukoz.me.gui.ModScreenHandlers;
import net.jukoz.me.recipe.AnvilShapingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import java.util.List;

public class ShapingAnvilScreenHandler extends AbstractContainerMenu {
    private final Container inventory;
    private final ContainerData propertyDelegate;
    protected BlockPos pos;
    private final Level world;

    public ShapingAnvilScreenHandler(int syncId, Inventory playerInventory, BlockPos blockPos) {
        this(syncId, playerInventory, new SimpleContainer(1), new SimpleContainerData(2));
        this.pos = blockPos;
    }

    public ShapingAnvilScreenHandler(int syncId, Inventory playerInventory, Container inventory, ContainerData delegate) {
        super(ModScreenHandlers.TREATED_ANVIL_SCREEN_HANDLER, syncId);
        checkContainerSize(inventory, 1);
        this.inventory = inventory;
        inventory.startOpen(playerInventory.player);
        this.propertyDelegate = delegate;
        this.pos = BlockPos.ZERO;
        this.world = playerInventory.player.level();

        this.addSlot(new ShapingAnvilSlot(inventory, 0, 80, 55));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addDataSlots(delegate);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasItem()) {
            ItemStack originalStack = slot.getItem();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.getContainerSize()) {
                if (!this.moveItemStackTo(originalStack, this.inventory.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(originalStack, 0, this.inventory.getContainerSize(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return newStack;
    }

    public BlockPos getPos() {
        return pos;
    }

    public ItemStack getOutput() {
        ItemStack input = inventory.getItem(0);

        List<RecipeHolder<AnvilShapingRecipe>> match = this.world.getRecipeManager()
                .getRecipesFor(AnvilShapingRecipe.Type.INSTANCE, new SingleRecipeInput(input), this.world);

        if (match.isEmpty()) return ItemStack.EMPTY;

        if (this.propertyDelegate.get(0) <= this.propertyDelegate.get(1)){
            return match.get(this.propertyDelegate.get(0)).value().getOutput();
        } else {
            return ItemStack.EMPTY;
        }
    }

    public boolean stillValid(Player player) {
        return this.inventory.stillValid(player);
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
