package net.jukoz.me.block.special.shapingAnvil;

import net.jukoz.me.compat.neoforge.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.ModBlockEntities;
import net.jukoz.me.block.special.forge.MetalTypes;
import net.jukoz.me.gui.shapinganvil.ShapingAnvilScreenHandler;
import net.jukoz.me.item.ModDataComponentTypes;
import net.jukoz.me.item.dataComponents.TemperatureDataComponent;
import net.jukoz.me.particles.ModParticleTypes;
import net.jukoz.me.recipe.AnvilShapingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimPattern;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TreatedAnvilBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, WorldlyContainer {
    private static final String ID = "shaping_anvil";

    public int outputIndex = 0;
    public int maxOutputIndex = 0;

    public final NonNullList<ItemStack> inventory =
            NonNullList.withSize(1, ItemStack.EMPTY);

    protected final ContainerData propertyDelegate;

    //TODO make work in creative somehow

    public TreatedAnvilBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TREATED_ANVIL, pos, state);

        this.propertyDelegate = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> TreatedAnvilBlockEntity.this.outputIndex;
                    case 1 -> TreatedAnvilBlockEntity.this.maxOutputIndex;
                    default -> throw new IllegalStateException("Unexpected value: " + index);
                };
            }
            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> TreatedAnvilBlockEntity.this.outputIndex = value;
                    case 1 -> TreatedAnvilBlockEntity.this.maxOutputIndex = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public TreatedAnvilBlockEntity(BlockEntityType type, BlockPos pos, BlockState state) {
        super(type, pos, state);

        this.propertyDelegate = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> TreatedAnvilBlockEntity.this.outputIndex;
                    case 1 -> TreatedAnvilBlockEntity.this.maxOutputIndex;
                    default -> throw new IllegalStateException("Unexpected value: " + index);
                };
            }
            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> TreatedAnvilBlockEntity.this.outputIndex = value;
                    case 1 -> TreatedAnvilBlockEntity.this.maxOutputIndex = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public static void updateIndex(boolean left, Vec3 coords, ServerPlayer player){
        BlockPos pos = new BlockPos((int) coords.x(), (int) coords.y(), (int) coords.z());

        BlockEntity shapingAnvilBlockEntity = player.level().getBlockEntity(pos);

        if(shapingAnvilBlockEntity instanceof TreatedAnvilBlockEntity entity){
            if (left){
                if(entity.outputIndex == 0){
                    entity.outputIndex = entity.maxOutputIndex;
                    entity.update();
                } else {
                    entity.outputIndex -= 1;
                    entity.update();
                }
            } else{
                if (entity.outputIndex == entity.maxOutputIndex){
                    entity.outputIndex = 0;
                    entity.update();
                } else if(entity.outputIndex > entity.maxOutputIndex){
                    entity.outputIndex = entity.maxOutputIndex;
                } else{
                    entity.outputIndex += 1;
                    entity.update();
                }
            }
        }
    }

    public ItemStack getRenderStack(TreatedAnvilBlockEntity entity) {
        return entity.getItem(0);
    }

    public void bonk(TreatedAnvilBlockEntity entity){
        ItemStack input = entity.getItem(0);

        List<RecipeHolder<AnvilShapingRecipe>> match = entity.getLevel().getRecipeManager()
            .getRecipesFor(AnvilShapingRecipe.Type.INSTANCE, new SingleRecipeInput(input), entity.getLevel());

        if (!match.isEmpty() && input.get(ModDataComponentTypes.TEMPERATURE_DATA) != null  && hasShapingRecipe(entity)){
            int temperature = input.get(ModDataComponentTypes.TEMPERATURE_DATA).temperature();

            entity.getLevel().playSound(null, worldPosition, SoundEvents.ANVIL_LAND, SoundSource.BLOCKS, 1.5f, 1.0f - (float) temperature / 1000);

            int minRandProgress = 6;
            int maxRandProgress = 14;

            if (input.getMaxDamage() == 0 && input.getDamageValue() == 0){
                input.set(DataComponents.MAX_DAMAGE, match.get(entity.outputIndex).value().getAmount());
                input.setDamageValue(match.get(entity.outputIndex).value().getAmount() - (int) (Math.random() * (maxRandProgress - minRandProgress) + minRandProgress));
            } else{
                input.setDamageValue(input.getDamageValue() - (int) (Math.random() * (maxRandProgress - minRandProgress) + minRandProgress));
            }

            Level serverWorld = this.getLevel();
            if (serverWorld instanceof ServerLevel) {
                ((ServerLevel)serverWorld).sendParticles(ModParticleTypes.ANVIL_SPARK_PARTICLE, worldPosition.getX()+ 0.5f, worldPosition.getY() + 1.0f, worldPosition.getZ() + 0.5f, Math.max(temperature / 10, 3), 0.0, 0.0, 0.0, 0.0);
            }

            int minRandTemperature = 10;
            int maxRandTemperature = 18;
            int value = (int) (Math.random() * (maxRandTemperature - minRandTemperature) + minRandTemperature);

            if ((input.get(ModDataComponentTypes.TEMPERATURE_DATA).temperature() - value) <= 0){
                input.remove(ModDataComponentTypes.TEMPERATURE_DATA);
            } else {
                input.set(ModDataComponentTypes.TEMPERATURE_DATA, new TemperatureDataComponent(input.get(ModDataComponentTypes.TEMPERATURE_DATA).temperature() - value));
            }
            HolderLookup.RegistryLookup<TrimMaterial>  armorTrimMaterialRegistry = entity.getLevel().registryAccess().lookupOrThrow(Registries.TRIM_MATERIAL);
            HolderLookup.RegistryLookup<TrimPattern>  armorTrimPatternRegistry = entity.getLevel().registryAccess().lookupOrThrow(Registries.TRIM_PATTERN);


            if (input.getDamageValue() == 0){
                ItemStack output = match.get(entity.outputIndex).value().assemble(new SingleRecipeInput(input), entity.level.registryAccess());

                if(input.get(DataComponents.TRIM) != null){
                    output.set(DataComponents.TRIM, input.get(DataComponents.TRIM));
                } else{
                    MetalTypes metal = MetalTypes.EMPTY;
                    if(input.is(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "ingot_shaping")))) {
                        metal = MetalTypes.getMetalByIngot(input.getItem());
                    }else if(input.is(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "nugget_shaping")))) {
                        metal = MetalTypes.getMetalByNugget(input.getItem());
                    }
                    if (metal.isVanilla()){
                        output.set(DataComponents.TRIM, new ArmorTrim(
                                armorTrimMaterialRegistry.getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.parse(metal.getName()))),
                                armorTrimPatternRegistry.getOrThrow(ResourceKey.create(Registries.TRIM_PATTERN, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID,"smithing_part")))));
                    } else {
                        output.set(DataComponents.TRIM, new ArmorTrim(
                                armorTrimMaterialRegistry.getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, metal.getName()))),
                                armorTrimPatternRegistry.getOrThrow(ResourceKey.create(Registries.TRIM_PATTERN, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "smithing_part")))));
                    }
                }
                if (input.get(ModDataComponentTypes.TEMPERATURE_DATA) != null){
                    output.set(ModDataComponentTypes.TEMPERATURE_DATA, new TemperatureDataComponent(input.get(ModDataComponentTypes.TEMPERATURE_DATA).temperature()));
                }
                entity.getLevel().playSound(null, worldPosition, SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1.0f, 1.0f);
                entity.setItem(0, output);
                entity.update();
            }
        } else {
            Level serverWorld = this.getLevel();
            if (serverWorld instanceof ServerLevel) {
                ((ServerLevel)serverWorld).sendParticles(ModParticleTypes.ANVIL_SPARK_PARTICLE, worldPosition.getX()+ 0.5f, worldPosition.getY() + 1.0f, worldPosition.getZ() + 0.5f, 2, 0.0, 0.0, 0.0, 0.0);
            }
            entity.getLevel().playSound(null, worldPosition, SoundEvents.ANVIL_LAND, SoundSource.BLOCKS, 1.5f, 1.0f);
        }
    }

    public static void tick(Level world, BlockPos blockPos, BlockState blockState, TreatedAnvilBlockEntity entity) {
        ItemStack input = entity.getItem(0);
        if (!input.isEmpty()){
            List<RecipeHolder<AnvilShapingRecipe>> match = entity.getLevel().getRecipeManager()
                    .getRecipesFor(AnvilShapingRecipe.Type.INSTANCE, new SingleRecipeInput(input), entity.getLevel());;
            if(!match.isEmpty()){
                entity.maxOutputIndex = match.size() - 1;
                if (entity.outputIndex > entity.maxOutputIndex){
                    entity.outputIndex = entity.maxOutputIndex;
                }
                entity.update();
            } else {
                entity.maxOutputIndex = 0;
                entity.update();
            }
        } else {
            entity.maxOutputIndex = 0;
            entity.update();
        }
    }

    private static boolean hasShapingRecipe(TreatedAnvilBlockEntity entity) {
        SimpleContainer inventory1 = new SimpleContainer(entity.getContainerSize());
        ItemStack input;

        inventory1.setItem(0, entity.getItem(0));
        input = entity.getItem(0);

        if(input.isEmpty()) return false;

        SingleRecipeInput inputStack = new SingleRecipeInput(input);
        List<RecipeHolder<AnvilShapingRecipe>> match = entity.getLevel().getRecipeManager()
                .getRecipesFor(AnvilShapingRecipe.Type.INSTANCE, inputStack, entity.getLevel());

        return match.getFirst().value().getOutput() != null;
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup) {
        super.saveAdditional(nbt, registryLookup);
        ContainerHelper.saveAllItems(nbt, this.inventory, true, registryLookup);
        nbt.putInt("current-index", this.outputIndex);
        nbt.putInt("current-max-index", this.maxOutputIndex);
    }

    @Override
    public void loadAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup) {
        super.loadAdditional(nbt, registryLookup);
        this.inventory.clear();
        ContainerHelper.loadAllItems(nbt, this.inventory, registryLookup);
        this.outputIndex = nbt.getInt("current-index");
        this.maxOutputIndex = nbt.getInt("current-max-index");
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("screen." + MiddleEarth.MOD_ID + "." + ID);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int syncId, Inventory playerInventory, Player player) {
        return new ShapingAnvilScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) {
        return saveWithoutMetadata(registryLookup);
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        int[] slots = new int[inventory.size()];
        for (int i = 0; i < slots.length; i++) {
            slots[i] = i;
        }
        return slots;
    }

    public void update() {
        setChanged();
        level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction dir) {
        return getItem(0).isEmpty();
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction dir) {
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
        ItemStack result = ContainerHelper.removeItem(inventory, slot, amount);
        if (!result.isEmpty()) {
            update();
        }
        return result;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(inventory, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        inventory.set(slot, stack);
        if (stack.getCount() > getMaxStackSize()) {
            stack.setCount(getMaxStackSize());
        }
        update();
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent() {
        inventory.clear();
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayer player) {
        return worldPosition;
    }
}
