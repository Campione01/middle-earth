package net.jukoz.me.block;

import net.jukoz.me.utils.NeoForgeRegistrationBridge;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.special.bellows.BellowsBlockEntity;
import net.jukoz.me.block.special.forge.ForgeBlockEntity;
import net.jukoz.me.block.special.beds.CustomBedBlockEntity;
import net.jukoz.me.block.special.fireBlocks.*;
import net.jukoz.me.block.special.reinforcedChest.ReinforcedChestBlockEntity;
import net.jukoz.me.block.special.shapingAnvil.TreatedAnvilBlockEntity;
import net.jukoz.me.block.special.wood_pile.WoodPileBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {
    public static BlockEntityType<ForgeBlockEntity> FORGE;
    public static BlockEntityType<TreatedAnvilBlockEntity> TREATED_ANVIL;
    public static BlockEntityType<ReinforcedChestBlockEntity> REINFORCED_CHEST;
    public static BlockEntityType<BellowsBlockEntity> BELLOWS;
    public static BlockEntityType<WoodPileBlockEntity> WOOD_PILE;
    public static BlockEntityType<BrazierBlockEntity> BIG_BRAZIER;
    public static BlockEntityType<SmallBrazierBlockEntity> SMALL_BRAZIER;
    public static BlockEntityType<GildedBrazierBlockEntity> GILDED_BIG_BRAZIER;
    public static BlockEntityType<GildedSmallBrazierBlockEntity> GILDED_SMALL_BRAZIER;
    public static BlockEntityType<FireBowlBlockEntity> FIRE_BOWL;
    public static BlockEntityType<BonfireBlockEntity> BONFIRE;
    public static BlockEntityType<ChimneyBlockEntity> CHIMNEY;
    public static BlockEntityType<CustomBedBlockEntity> BED;

    public static void registerBlockEntities() {

        FORGE = NeoForgeRegistrationBridge.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "forge"),
                BlockEntityType.Builder.of(ForgeBlockEntity::new,
                        ModDecorativeBlocks.FORGE).build(null));
        TREATED_ANVIL = NeoForgeRegistrationBridge.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "treated_anvil"),
                BlockEntityType.Builder.of(TreatedAnvilBlockEntity::new,
                        ModDecorativeBlocks.TREATED_ANVIL,
                        ModDecorativeBlocks.DWARVEN_TREATED_ANVIL,
                        ModDecorativeBlocks.ELVEN_TREATED_ANVIL,
                        ModDecorativeBlocks.ORCISH_TREATED_ANVIL).build(null));
        REINFORCED_CHEST = NeoForgeRegistrationBridge.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "reinforced_chest"),
                BlockEntityType.Builder.of(ReinforcedChestBlockEntity::new,
                        ModDecorativeBlocks.REINFORCED_CHEST).build(null));
        BELLOWS = NeoForgeRegistrationBridge.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bellows"),
                BlockEntityType.Builder.of(BellowsBlockEntity::new,
                        ModDecorativeBlocks.BELLOWS).build(null));
        WOOD_PILE = NeoForgeRegistrationBridge.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "wood_pile"),
                BlockEntityType.Builder.of(WoodPileBlockEntity::new,
                        ModDecorativeBlocks.WOOD_PILE).build(null));
        BIG_BRAZIER = NeoForgeRegistrationBridge.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "big_brazier"),
                BlockEntityType.Builder.of(BrazierBlockEntity::new,
                        ModDecorativeBlocks.BIG_BRAZIER).build(null));
        SMALL_BRAZIER = NeoForgeRegistrationBridge.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "small_brazier"),
                BlockEntityType.Builder.of(SmallBrazierBlockEntity::new,
                        ModDecorativeBlocks.SMALL_BRAZIER).build(null));
        GILDED_BIG_BRAZIER = NeoForgeRegistrationBridge.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "gilded_big_brazier"),
                BlockEntityType.Builder.of(GildedBrazierBlockEntity::new,
                        ModDecorativeBlocks.GILDED_BIG_BRAZIER).build(null));
        GILDED_SMALL_BRAZIER = NeoForgeRegistrationBridge.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "gilded_small_brazier"),
                BlockEntityType.Builder.of(GildedSmallBrazierBlockEntity::new,
                        ModDecorativeBlocks.GILDED_SMALL_BRAZIER).build(null));
        FIRE_BOWL = NeoForgeRegistrationBridge.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "fire_bowl"),
                BlockEntityType.Builder.of(FireBowlBlockEntity::new,
                        ModDecorativeBlocks.FIRE_BOWL).build(null));
        BONFIRE = NeoForgeRegistrationBridge.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bonfire"),
                BlockEntityType.Builder.of(BonfireBlockEntity::new,
                        ModDecorativeBlocks.BONFIRE).build(null));
        CHIMNEY = NeoForgeRegistrationBridge.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "chimney"),
                BlockEntityType.Builder.of(ChimneyBlockEntity::new,
                        ModDecorativeBlocks.CHIMNEY).build(null));
        BED = NeoForgeRegistrationBridge.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bed"),
                BlockEntityType.Builder.of(CustomBedBlockEntity::new,
                        ModDecorativeBlocks.FANCY_BED, ModDecorativeBlocks.FUR_BED, ModDecorativeBlocks.STRAW_BED).build(null));

    }
}
