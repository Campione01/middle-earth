package net.jukoz.me.gui.artisantable;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import net.jukoz.me.block.ModDecorativeBlocks;
import net.jukoz.me.block.special.forge.MultipleStackRecipeInput;
import net.jukoz.me.gui.ModScreenHandlers;
import net.jukoz.me.recipe.ArtisanRecipe;
import net.jukoz.me.recipe.ModRecipes;
import net.jukoz.me.resources.datas.Disposition;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArtisanTableScreenHandler extends AbstractContainerMenu {
    private final ContainerLevelAccess context;
    private final DataSlot selectedRecipe;
    private final Level world;
    private List<RecipeHolder<ArtisanRecipe>> availableRecipes;
    private ItemStack inputStack;
    long lastTakeTime;
    private ArtisanTableSlot[][] inputSlots;
    final Slot outputSlot;
    Runnable contentsChangedListener;
    public final Container input;
    final ResultContainer output;
    private Player playerEntity;
    private ArtisanTableInputsShape inputsShape = null;

    private String disposition;
    private boolean isCreative;

    public ArtisanTableScreenHandler(int syncId, Inventory playerInventory, String disposition) {
        this(syncId, playerInventory, ContainerLevelAccess.NULL);

        String[] splited = disposition.split("/");
        this.disposition = splited[0];
        this.isCreative = Boolean.parseBoolean(splited[1]);
    }

    public ArtisanTableScreenHandler(int syncId, Inventory playerInventory, final ContainerLevelAccess context) {
        super(ModScreenHandlers.ARTISAN_SCREEN_HANDLER, syncId);
        this.selectedRecipe = DataSlot.standalone();
        this.availableRecipes = Lists.newArrayList();
        this.inputStack = ItemStack.EMPTY;
        this.contentsChangedListener = () -> {
        };

        this.disposition = "neutral";
        this.isCreative = false;

        this.input = new SimpleContainer(9) {
            public void setChanged() {
                super.setChanged();
                ArtisanTableScreenHandler.this.slotsChanged(this);
                ArtisanTableScreenHandler.this.contentsChangedListener.run();
            }
        };
        this.output = new ResultContainer();
        this.context = context;
        this.world = playerInventory.player.level();

        inputSlots = new ArtisanTableSlot[3][3];
        int index = 0;
        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 3; x++) {
                inputSlots[y][x] = (ArtisanTableSlot) this.addSlot(new ArtisanTableSlot(this.input, index++, 13 + 18*x, 16 + 18*y));
            }
        }

        this.outputSlot = this.addSlot(new Slot(this.output, 9, 165, 33) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack itemStack) {
                itemStack.onCraftedBy(player.level(), player, itemStack.getCount());
                ArtisanTableScreenHandler.this.output.awardUsedRecipes(player, this.getInputStacks());

                for(int y = 0; y < 3; y++) {
                    for(int x = 0; x < 3; x++) {
                        ArtisanTableSlot slot = inputSlots[y][x];
                        if(x == 0 && y == 0) itemStack = slot.remove(1);
                        else slot.remove(1);
                    }
                }

                if (!itemStack.isEmpty()) {
                    ArtisanTableScreenHandler.this.populateResult(player);
                }

                long l = world.getGameTime();
                if (ArtisanTableScreenHandler.this.lastTakeTime != l) {
                    world.playSound(null, (BlockPos)player.blockPosition(), SoundEvents.VILLAGER_WORK_TOOLSMITH, SoundSource.BLOCKS, 1.0f, 1.0f);
                    ArtisanTableScreenHandler.this.lastTakeTime = l;
                }

                super.onTake(player, itemStack);
            }

            private List<ItemStack> getInputStacks() {
                return Arrays.stream(inputSlots)
                        .flatMap(slots -> Arrays.stream(slots).map(Slot::getItem))
                        .collect(Collectors.toList());
            }
        });

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        this.addDataSlot(this.selectedRecipe);
    }

    public int getSelectedRecipe() {
        return this.selectedRecipe.get();
    }

    public List<RecipeHolder<ArtisanRecipe>> getAvailableRecipes() {
        return this.availableRecipes;
    }

    public int getAvailableRecipeCount() {
        return this.availableRecipes.size();
    }

    public boolean canCraft() {
        return !this.availableRecipes.isEmpty();
    }

    public boolean stillValid(Player player) {
        this.playerEntity = player;
        return stillValid(this.context, player, ModDecorativeBlocks.ARTISAN_TABLE);
    }

    public boolean clickMenuButton(Player player, int id) {
        this.playerEntity = player;
        if (this.isInBounds(id)) {
            this.selectedRecipe.set(id);
            this.populateResult(player);
        }
        return true;
    }

    private boolean isInBounds(int id) {
        return id >= 0 && id < this.availableRecipes.size();
    }

    public void slotsChanged(Container inventory) {
        ItemStack itemStack = this.inputSlots[0][0].getItem();
        this.inputStack = itemStack.copy();
        this.updateInput(inventory);
    }

    public void changeTab(String shapeId) {
        if(playerEntity != null) {
            this.clearContainer(this.playerEntity, this.input);
            this.outputSlot.setByPlayer(ItemStack.EMPTY);
        }

        ArtisanTableInputsShape inputsShape = ArtisanTableInputsShape.getShape(shapeId);
        if(inputsShape == null) return;
        this.inputsShape = inputsShape;
        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 3; x++) {
                ArtisanTableSlot slot = inputSlots[y][x];
                InputType inputType = this.inputsShape.getInputType(x,y);
                if(inputType == null) continue;
                else if(inputType == InputType.NONE) slot.setEnabled(false);
                else slot.setEnabled(true);
                slot.setInputType(inputType);
            }
        }
    }

    private void updateInput(Container inventory) {
        String currentCategory = this.inputsShape.getId();
        if(currentCategory == null) return;

        List<ItemStack> inputs = new ArrayList<>();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ArtisanTableSlot slot = inputSlots[i / 3][i % 3];
            if(slot.isActive()) {
                inputs.add(inventory.getItem(i));
            }
        }
        this.availableRecipes.clear();
        this.selectedRecipe.set(-1);
        this.outputSlot.set(ItemStack.EMPTY);
        if (!inputs.isEmpty()) {
            this.availableRecipes = this.world.getRecipeManager().getRecipesFor(ModRecipes.ARTISAN_TABLE, new MultipleStackRecipeInput(inputs), this.world);
        }

        ArrayList<RecipeHolder<ArtisanRecipe>> filteredRecipes = new ArrayList<>();
        for(RecipeHolder<ArtisanRecipe> recipeEntry : this.availableRecipes) {
            if (recipeEntry.value().category.equals(currentCategory)){
                if (Disposition.valueOf(recipeEntry.value().disposition.toUpperCase()) == Disposition.NEUTRAL){
                    filteredRecipes.add(recipeEntry);
                } else if(Disposition.valueOf(recipeEntry.value().disposition.toUpperCase()) == Disposition.valueOf(this.disposition.toUpperCase()) || this.isCreative) {
                    filteredRecipes.add(recipeEntry);
                }
            }
        }
        this.availableRecipes = filteredRecipes;
    }

    void populateResult(Player player) {
        this.playerEntity = player;
        if (!this.availableRecipes.isEmpty() && this.isInBounds(this.selectedRecipe.get())) {
            List<ItemStack> inputs = new ArrayList<>();
            for (int i = 0; i < this.input.getContainerSize(); i++) {
                inputs.add(this.input.getItem(i));
            }
            RecipeHolder<ArtisanRecipe> recipeEntry = this.availableRecipes.get(this.selectedRecipe.get());

            ItemStack itemStack = recipeEntry.value().assemble(new MultipleStackRecipeInput(inputs), this.world.registryAccess());
            itemStack.set(DataComponents.PROFILE, new ResolvableProfile(new GameProfile(player.getUUID(), player.getName().getString())));

            if (itemStack.get(DataComponents.MAX_DAMAGE) != null){
                int maxDamage = (int) (itemStack.getMaxDamage() + itemStack.getMaxDamage() * 0.25);
                itemStack.set(DataComponents.MAX_DAMAGE, maxDamage);
            }

            if (itemStack.isItemEnabled(this.world.enabledFeatures())) {
                this.output.setRecipeUsed(recipeEntry);
                this.outputSlot.set(itemStack);
            } else {
                this.outputSlot.set(ItemStack.EMPTY);
            }
        } else {
            this.outputSlot.set(ItemStack.EMPTY);
        }
        this.broadcastChanges();
    }

    public MenuType<?> getType() {
        return ModScreenHandlers.ARTISAN_SCREEN_HANDLER;
    }

    public void setContentsChangedListener(Runnable contentsChangedListener) {
        this.contentsChangedListener = contentsChangedListener;
    }

    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
        return slot.container != this.output && super.canTakeItemForPickAll(stack, slot);
    }

    public ItemStack quickMoveStack(Player player, int slot) {
        this.playerEntity = player;
        ItemStack stack = ItemStack.EMPTY;
        Slot invSlot = this.slots.get(slot);

        if(invSlot.hasItem()) {
            ItemStack originalStack = invSlot.getItem();
            Item item = originalStack.getItem();
            stack = originalStack.copy();
            if (slot == 6){
                item.onCraftedPostProcess(originalStack, player.level());
                if (!this.moveItemStackTo(originalStack, 7, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
                invSlot.onQuickCraft(originalStack, stack);
            } else if(slot < this.input.getContainerSize()) {
                if(!this.moveItemStackTo(originalStack, this.input.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(originalStack, 0, this.input.getContainerSize(), false)) {
                return ItemStack.EMPTY;
            }
            if (originalStack.isEmpty()) {
                invSlot.setByPlayer(ItemStack.EMPTY);
            } else {
                invSlot.setChanged();
            }

            invSlot.onTake(player, originalStack);
            this.broadcastChanges();
        }
        return stack;
    }

    public void removed(Player player) {
        super.removed(player);
        this.output.removeItemNoUpdate(6);
        this.clearContainer(player, this.input);
    }
    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 36 + j * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 36 + i * 18, 142));
        }
    }
}
