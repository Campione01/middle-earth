package net.jukoz.me.block.special.forge;

import net.jukoz.me.compat.neoforge.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.ModBlockEntities;
import net.jukoz.me.block.ModDecorativeBlocks;
import net.jukoz.me.block.special.bellows.BellowsBlock;
import net.jukoz.me.block.special.shapingAnvil.TreatedAnvilBlockEntity;
import net.jukoz.me.datageneration.content.models.HotMetalsModel;
import net.jukoz.me.gui.forge.ForgeAlloyingScreenHandler;
import net.jukoz.me.gui.forge.ForgeHeatingScreenHandler;
import net.jukoz.me.item.ModDataComponentTypes;
import net.jukoz.me.item.ModResourceItems;
import net.jukoz.me.item.dataComponents.TemperatureDataComponent;
import net.jukoz.me.recipe.AlloyingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.armortrim.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimPattern;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ForgeBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, WorldlyContainer {
    private static final String ID = "forge";
    public static final int MAX_PROGRESS = 1200;
    public static final int MAX_STORAGE = 2304;
    public static final int MAX_BOOST_TIME = 10;
    public static final int FUEL_SLOT = 0;
    public static final int OUTPUT_SLOT = 5;
    private final NonNullList<ItemStack> inventory =
            NonNullList.withSize(6, ItemStack.EMPTY);
    protected final ContainerData propertyDelegate;
    protected final Map<Item, Integer> fuelTimeMap = AbstractFurnaceBlockEntity.getFuel();
    private int progress = 0;
    private int boostTime = 0;
    private int fuelTime = 0;
    private int maxFuelTime = 0;
    private int mode = 0;
    private int storage = 0;

    private MetalTypes currentMetal = MetalTypes.EMPTY;

    //TODO melting smithing parts
    //TODO convert metals to registry, enum constant datagen if no registry
    //TODO custom metal trim data component with palette

    public ForgeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FORGE, pos, state);
        this.propertyDelegate = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> ForgeBlockEntity.this.progress;
                    case 1 -> ForgeBlockEntity.this.fuelTime;
                    case 2 -> ForgeBlockEntity.this.maxFuelTime;
                    case 3 -> ForgeBlockEntity.this.mode;
                    case 4 -> ForgeBlockEntity.this.storage;
                    case 5 -> ForgeBlockEntity.this.currentMetal.getId();
                    default -> throw new IllegalStateException("Unexpected value: " + index);
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> ForgeBlockEntity.this.progress = value;
                    case 1 -> ForgeBlockEntity.this.fuelTime = value;
                    case 2 -> ForgeBlockEntity.this.maxFuelTime = value;
                    case 3 -> ForgeBlockEntity.this.mode = value;
                    case 4 -> ForgeBlockEntity.this.storage = value;
                }
            }

            @Override
            public int getCount() {
                return 6;
            }
        };
    }

    public ItemStack getRenderStack(ForgeBlockEntity entity) {
        if (this.currentMetal != MetalTypes.EMPTY){
            return entity.currentMetal.getIngot().getDefaultInstance();
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("screen." + MiddleEarth.MOD_ID + "." + ID);
    }

    public int getStorage() {
        return storage;
    }

    public MetalTypes getCurrentMetal() {
        return currentMetal;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int syncId, Inventory playerInventory, Player player) {
        if(hasBellows(player.level(), this.worldPosition, player.level().getBlockState(this.worldPosition)) == 1) {
            return new ForgeAlloyingScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
        } else {
            return new ForgeHeatingScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
        }
    }

    public int hasBellows(Level world, BlockPos pos, BlockState state){
        Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        BlockPos pos1 = pos.relative(direction.getClockWise());
        BlockPos pos2 = pos.relative(direction.getClockWise().getOpposite());

        Direction directionForge = state.getValue(ForgeBlock.FACING);

        if(world.getBlockState(pos1).is(ModDecorativeBlocks.BELLOWS) && world.getBlockState(pos2).is(ModDecorativeBlocks.BELLOWS)){
            Direction direction1 = world.getBlockState(pos1).getValue(BellowsBlock.FACING);
            Direction direction2 = world.getBlockState(pos2).getValue(BellowsBlock.FACING);
            switch (directionForge){
                case NORTH -> {
                    if (direction1 == Direction.WEST && direction2 == Direction.EAST){
                        return 1;
                    }
                }
                case SOUTH ->{
                    if (direction1 == Direction.EAST && direction2 == Direction.WEST){
                        return 1;
                    }
                }
                case EAST ->{
                    if (direction1 == Direction.NORTH && direction2 == Direction.SOUTH){
                        return 1;
                    }
                }
                case WEST ->{
                    if (direction1 == Direction.SOUTH && direction2 == Direction.NORTH){
                        return 1;
                    }
                }
            }
        }

        return 0;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup) {
        super.saveAdditional(nbt, registryLookup);
        ContainerHelper.saveAllItems(nbt, this.inventory, true, registryLookup);
        nbt.putInt(ID + ".progress", this.progress);
        nbt.putInt(ID + ".boost-time", this.boostTime);
        nbt.putInt(ID + ".fuel-time", this.fuelTime);
        nbt.putInt(ID + ".max-fuel-time", this.maxFuelTime);
        nbt.putInt(ID + ".mode", this.mode);
        nbt.putInt(ID + ".storage", this.storage);
        nbt.putString(ID + ".current-metal", this.currentMetal.getName());
    }

    @Override
    public void loadAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup) {
        super.loadAdditional(nbt, registryLookup);
        this.inventory.clear();
        ContainerHelper.loadAllItems(nbt, this.inventory, registryLookup);
        this.progress = nbt.getInt(ID + ".progress");
        this.boostTime = nbt.getInt(ID + ".boost-time");
        this.fuelTime = nbt.getInt(ID + ".fuel-time");
        this.maxFuelTime = nbt.getInt(ID + ".max-fuel-time");
        this.mode = nbt.getInt(ID + ".mode");
        this.storage = nbt.getInt(ID + ".storage");
        this.currentMetal = MetalTypes.valueOf(nbt.getString(ID + ".current-metal").toUpperCase());
    }

    public void update() {
        setChanged();
        level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) {
        return saveWithoutMetadata(registryLookup);
    }

    public void setInventory(NonNullList<ItemStack> inventory) {
        for (int i = 0; i < inventory.size(); i++) {
            this.inventory.set(i, inventory.get(i));
        }
    }

    protected boolean isFuel(Item item) {
        for (Item item1: fuelTimeMap.keySet()) {
            if(item1.equals(item)) return true;
        }
        return false;
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        int[] slots = new int[inventory.size()];
        for (int i = 0; i < slots.length; i++) {
            slots[i] = i;
        }
        return slots;
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction dir) {
        if (this.level.getBlockState(this.worldPosition).getValue(ForgeBlock.PART) == ForgePart.TOP) return false;

        if (mode == 0 && dir != null) return false;

        if (slot == FUEL_SLOT) {
            return isFuel(stack.getItem());
        }
        return true;
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction dir) {
        if (this.level.getBlockState(this.worldPosition).getValue(ForgeBlock.PART) == ForgePart.TOP) return false;

        if (dir == Direction.DOWN && slot < 5) return false;

        return true;
    }

    @Override
    public int getContainerSize() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < getContainerSize(); i++) {
            ItemStack itemStack = getItem(i);
            if (!itemStack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int slot) {
        return inventory.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        return ContainerHelper.removeItem(this.inventory, slot, amount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(inventory, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        this.inventory.set(slot, stack);
        if (stack.getCount() > getMaxStackSize()) {
            stack.setCount(getMaxStackSize());
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent() {
        this.inventory.clear();
    }

    public void bellowsBoost() {
        this.boostTime = MAX_BOOST_TIME;
        update();
    }

    public static void outputItemStack(int amount, Vec3 coords, ServerPlayer player){
        BlockPos pos = new BlockPos((int) coords.x(), (int) coords.y(), (int) coords.z());

        Optional<ForgeBlockEntity> forgeBlockEntity = player.level().getBlockEntity(pos, ModBlockEntities.FORGE);

        ItemStack itemstack = ItemStack.EMPTY;
        if(forgeBlockEntity.isPresent()){
            ForgeBlockEntity entity = forgeBlockEntity.get();

            HolderLookup.RegistryLookup<TrimMaterial>  armorTrimMaterialRegistry = entity.getLevel().registryAccess().lookupOrThrow(Registries.TRIM_MATERIAL);
            HolderLookup.RegistryLookup<TrimPattern>  armorTrimPatternRegistry = entity.getLevel().registryAccess().lookupOrThrow(Registries.TRIM_PATTERN);


            switch (amount){
                case 16 -> {
                    if (entity.currentMetal.getNugget() != null){
                        itemstack = new ItemStack(entity.currentMetal.getNugget());
                        itemstack.set(ModDataComponentTypes.TEMPERATURE_DATA, new TemperatureDataComponent(100));
                    }
                }
                case 144 -> {
                    itemstack = new ItemStack(entity.currentMetal.getIngot());
                    itemstack.set(ModDataComponentTypes.TEMPERATURE_DATA, new TemperatureDataComponent(100));
                }
                case 288 -> {
                    itemstack = new ItemStack(ModResourceItems.ROD);
                    if (entity.currentMetal.isVanilla()){
                        itemstack.set(DataComponents.TRIM, new ArmorTrim(
                                armorTrimMaterialRegistry.getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.parse(entity.currentMetal.getName()))),
                                armorTrimPatternRegistry.getOrThrow(ResourceKey.create(Registries.TRIM_PATTERN, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "smithing_part")))));

                    } else {
                        itemstack.set(DataComponents.TRIM, new ArmorTrim(
                                armorTrimMaterialRegistry.getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, entity.currentMetal.getName()))),
                                armorTrimPatternRegistry.getOrThrow(ResourceKey.create(Registries.TRIM_PATTERN, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "smithing_part")))));

                    }itemstack.set(ModDataComponentTypes.TEMPERATURE_DATA, new TemperatureDataComponent(100));
                }
                case 432 -> {
                    itemstack = new ItemStack(ModResourceItems.LARGE_ROD);
                    if (entity.currentMetal.isVanilla()){
                        itemstack.set(DataComponents.TRIM, new ArmorTrim(
                                armorTrimMaterialRegistry.getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.parse(entity.currentMetal.getName()))),
                                armorTrimPatternRegistry.getOrThrow(ResourceKey.create(Registries.TRIM_PATTERN, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "smithing_part")))));

                    } else {
                        itemstack.set(DataComponents.TRIM, new ArmorTrim(
                                armorTrimMaterialRegistry.getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, entity.currentMetal.getName()))),
                                armorTrimPatternRegistry.getOrThrow(ResourceKey.create(Registries.TRIM_PATTERN, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "smithing_part")))));

                    }
                    itemstack.set(ModDataComponentTypes.TEMPERATURE_DATA, new TemperatureDataComponent(100));
                }
            }

            if (entity.getItem(OUTPUT_SLOT).is(itemstack.getItem()) && !itemstack.isEmpty()){
                if (Objects.equals(entity.getItem(OUTPUT_SLOT).get(DataComponents.TRIM), itemstack.get(DataComponents.TRIM))) {
                    if (amount <= entity.storage && amount > 0) {
                        itemstack.setCount(entity.getItem(OUTPUT_SLOT).getCount() + 1);
                        entity.storage = entity.storage - amount;
                        if (entity.storage == 0) {
                            entity.currentMetal = MetalTypes.EMPTY;
                        }
                        entity.setItem(OUTPUT_SLOT, itemstack);
                        playExtractSound(entity.getLevel(), pos);
                        entity.update();
                    } else {
                        playFailedExtractSound(entity.getLevel(), pos);
                    }
                }else {
                    playFailedExtractSound(entity.getLevel(), pos);
                }
            } else if(entity.getItem(OUTPUT_SLOT).isEmpty() && !itemstack.isEmpty()){
                if (amount <= entity.storage && amount > 0) {
                    itemstack.setCount(entity.getItem(OUTPUT_SLOT).getCount() + 1);
                    entity.storage = entity.storage - amount;
                    if (entity.storage == 0) {
                        entity.currentMetal = MetalTypes.EMPTY;
                    }
                    entity.setItem(OUTPUT_SLOT, itemstack);
                    playExtractSound(entity.getLevel(), pos);
                    entity.update();
                } else {
                    playFailedExtractSound(entity.getLevel(), pos);
                }
            } else {
                playFailedExtractSound(entity.getLevel(), pos);
            }
        }
    }

    private static void playExtractSound(Level world, BlockPos pos){
        world.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0f, 1.0f);
    }

    private static void playFailedExtractSound(Level world, BlockPos pos){
        world.playSound(null, pos, SoundEvents.DECORATED_POT_INSERT_FAIL, SoundSource.BLOCKS, 1.0f, 1.0f);
    }

    public static void tick(Level world, BlockPos blockPos, BlockState blockState, ForgeBlockEntity entity) {
        if (blockState.getValue(ForgeBlock.PART) == ForgePart.TOP) return;

        entity.fuelTime = Math.max(0, entity.fuelTime - 1);
        entity.boostTime = Math.max(0, entity.boostTime - 1);

        boolean progress = false;

        entity.mode = entity.hasBellows(world, blockPos, blockState);
        entity.update();

        if(entity.mode == 1) { // Alloying mode
            if(hasAlloyingRecipe(entity)) {
                if(entity.hasFuel(entity)) {
                    int progressValue = 1;
                    if(entity.boostTime > 0) {
                        progressValue = 8;
                    }
                    entity.progress += progressValue;
                    progress = true;
                    entity.update();
                    if(entity.progress >= MAX_PROGRESS) {
                        craftItem(entity);
                        entity.progress = 0;
                        entity.update();
                    }
                }
            }
        } else { // Heating mode
            dropExtraItems(entity);
            if(hasHeatingRecipe(entity)) {
                if(entity.hasFuel(entity)) {
                    int progressValue = 2;
                    if(entity.boostTime > 0) {
                        progressValue = 16;
                    }
                    entity.progress += progressValue;
                    progress = true;
                    entity.update();
                    if(entity.progress >= MAX_PROGRESS) {
                        for (int i = 1; i <= 4; i++) {
                            entity.getItem(i).set(ModDataComponentTypes.TEMPERATURE_DATA, new TemperatureDataComponent(100));
                        }
                        entity.progress = 0;
                        entity.update();
                    }
                }
            }
        }

        if (!progress){
            entity.progress = Math.max(entity.progress - 2, 0);
            entity.update();
        }
        boolean isCooking = entity.fuelTime > 0;

        blockState = blockState.setValue(AbstractFurnaceBlock.LIT, isCooking);
        BlockState blockStateUp = blockState.setValue(AbstractFurnaceBlock.LIT, isCooking).setValue(ForgeBlock.PART, ForgePart.TOP);
        world.setBlock(blockPos, blockState, Block.UPDATE_ALL);
        world.setBlock(blockPos.above(), blockStateUp, Block.UPDATE_ALL);
    }

    private static void craftItem(ForgeBlockEntity entity) {
        SimpleContainer inventory1 = new SimpleContainer(entity.getContainerSize());
        List<ItemStack> inputs = new ArrayList<>();
        for (int i = 0; i < entity.getContainerSize(); i++) {
            inventory1.setItem(i, entity.getItem(i));
            if(i != FUEL_SLOT && i != OUTPUT_SLOT) inputs.add(entity.getItem(i));
        }

        Optional<RecipeHolder<AlloyingRecipe>> match = entity.getLevel().getRecipeManager()
                .getRecipeFor(AlloyingRecipe.Type.INSTANCE, new MultipleStackRecipeInput(inputs), entity.getLevel());
        if(match.isEmpty()) throw new RuntimeException("Somehow... you crafted an item without recipe?!");

        if(hasAlloyingRecipe(entity)) {
            for (int i = 1; i <= 4; i++) {
                entity.removeItem(i, 1);
            }
            entity.storage = entity.storage + match.get().value().amount;
            entity.currentMetal = MetalTypes.valueOf(match.get().value().output.toUpperCase());
            entity.update();
        }
    }

    private static boolean hasAlloyingRecipe(ForgeBlockEntity entity) {
        SimpleContainer inventory1 = new SimpleContainer(entity.getContainerSize());
        List<ItemStack> inputs = new ArrayList<>();
        for (int i = 0; i < entity.getContainerSize(); i++) {
            inventory1.setItem(i, entity.getItem(i));
            if(i != FUEL_SLOT && i != OUTPUT_SLOT) inputs.add(entity.getItem(i));
        }
        if(inputs.isEmpty()) return false;

        MultipleStackRecipeInput input = new MultipleStackRecipeInput(inputs);
        Optional<RecipeHolder<AlloyingRecipe>> match = entity.getLevel().getRecipeManager()
                .getRecipeFor(AlloyingRecipe.Type.INSTANCE, input, entity.getLevel());

        if(match.isEmpty()) return false;

        return canInsertLiquid(entity.storage, entity.currentMetal, match);
    }

    private static void dropExtraItems(ForgeBlockEntity entity) {
    {
        if(entity.getLevel() == null) return;
        for (int i = 0; i < entity.getContainerSize(); i++)
            if (i != FUEL_SLOT) {
                ItemStack itemStack = entity.getItem(i);
                if (!itemStack.isEmpty() && itemStack.getCount() > 1) {
                    int difference = itemStack.getCount() - 1;
                    if(i == OUTPUT_SLOT){
                        difference = itemStack.getCount();
                    }

                    ItemStack extraStack = itemStack.copy();
                    extraStack.setCount(difference);

                    ItemEntity itemEntity = new ItemEntity(entity.getLevel(),
                            entity.getBlockPos().getX() + 0.5f,
                            entity.getBlockPos().getY() + 1.5f,
                            entity.getBlockPos().getZ() + 0.5f, extraStack);
                    itemEntity.setDefaultPickUpDelay();
                    float f = (float) (Math.random() * 0.15f);
                    float g = (float) (Math.random() * 0.15f);
                    itemEntity.setDeltaMovement(f, 0.25f, g);
                    entity.getLevel().addFreshEntity(itemEntity);

                    itemStack.setCount(1);
                    if(i == OUTPUT_SLOT){
                        itemStack.setCount(0);
                    }
                }
            }
        }
    }

    private static boolean hasHeatingRecipe(ForgeBlockEntity entity) {
        List<ItemStack> inputs = new ArrayList<>();
        boolean hasColdItem = false;
        for (int i = 0; i < entity.getContainerSize(); i++) {
            if(i != FUEL_SLOT && i != OUTPUT_SLOT) {
                Item item = entity.getItem(i).getItem();
                if (entity.getItem(i).isEmpty()){
                    hasColdItem = true;
                } else if(!HotMetalsModel.nuggets.contains(item) && !HotMetalsModel.ingots.contains(item) && !HotMetalsModel.items.contains(item)) {
                    return false; // One of the items cannot be heated
                } else {
                    TemperatureDataComponent temperatureComponent = entity.getItem(i).get(ModDataComponentTypes.TEMPERATURE_DATA);
                    if(temperatureComponent == null || temperatureComponent.temperature() < 100) {
                        hasColdItem = true;
                        inputs.add(entity.getItem(i));
                    }
                }
            }
        }
        if(inputs.isEmpty()) return false;
        else return hasColdItem;
    }

    private boolean hasFuel(ForgeBlockEntity entity) {

        SimpleContainer inventory1 = new SimpleContainer(entity.getContainerSize());
        for (int i = 0; i < entity.getContainerSize(); i++) {
            inventory1.setItem(i, entity.getItem(i));
        }

        Item fuelItem = inventory1.getItem(FUEL_SLOT).getItem();
        if(this.fuelTime > 0) return true;
        else {
            if(isFuel(fuelItem)) {
                getFuel(entity, fuelItem);
                return true;
            } else return false;
        }
    }

    private void getFuel(ForgeBlockEntity entity, Item fuelItem) {
        fuelTime = Math.round((float) fuelTimeMap.get(fuelItem) / 16);
        maxFuelTime = fuelTime;
        if(fuelItem == Items.LAVA_BUCKET) {
            entity.removeItemNoUpdate(FUEL_SLOT);
            entity.setItem(FUEL_SLOT, Items.BUCKET.getDefaultInstance());
        }
        else entity.getItem(FUEL_SLOT).shrink(1);
    }

    private static boolean canInsertLiquid(int storage, MetalTypes currentMetal, Optional<RecipeHolder<AlloyingRecipe>> match) {
        MetalTypes metal = MetalTypes.valueOf(match.get().value().output.toUpperCase());
        if((storage + match.get().value().amount) <= MAX_STORAGE){
            if(metal == currentMetal){
                return true;
            } else return currentMetal == MetalTypes.EMPTY;
        } else {
            return false;
        }
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayer player) {
        return worldPosition;
    }
}
