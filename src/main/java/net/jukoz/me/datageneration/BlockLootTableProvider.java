package net.jukoz.me.datageneration;

import net.jukoz.me.compat.neoforge.api.datagen.v1.NeoForgeDataOutput;
import net.jukoz.me.compat.neoforge.api.datagen.v1.provider.NeoForgeBlockLootTableProvider;
import net.jukoz.me.block.*;
import net.jukoz.me.block.special.LargeDoorBlock;
import net.jukoz.me.block.special.RocksBlock;
import net.jukoz.me.block.special.verticalSlabs.VerticalSlabBlock;
import net.jukoz.me.datageneration.content.loot_tables.BlockDrops;
import net.jukoz.me.datageneration.content.loot_tables.CropDrops;
import net.jukoz.me.datageneration.content.loot_tables.LeavesDrops;
import net.jukoz.me.datageneration.content.loot_tables.PotDrops;
import net.jukoz.me.datageneration.content.models.SimplePaneModel;
import net.jukoz.me.datageneration.content.models.SimpleRocksModel;
import net.jukoz.me.datageneration.content.models.TintableCrossModel;
import net.jukoz.me.item.ModResourceItems;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemEnchantmentsPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.ItemSubPredicates;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BlockLootTableProvider extends NeoForgeBlockLootTableProvider {
    protected static final LootItemCondition.Builder WITH_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
    private final LootItemCondition.Builder WITH_SILK_TOUCH_OR_SHEARS = WITH_SHEARS.or(this.hasSilkTouch());
    private final LootItemCondition.Builder WITHOUT_SILK_TOUCH_NOR_SHEARS = WITH_SILK_TOUCH_OR_SHEARS.invert();
    private final float[] LEAVES_STICK_DROP_CHANCE = new float[]{0.02f, 0.022222223f, 0.025f, 0.033333335f, 0.1f};

    private final CompletableFuture<HolderLookup.Provider> registryLookup;

    protected BlockLootTableProvider(NeoForgeDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);

        this.registryLookup = registryLookup;
    }

    @Override
    public void generate() {
        for (Block block : BlockDrops.blocks) {
            if (BuiltInRegistries.BLOCK.getKey(block).getPath().equals("nurgon")) {
                cobbleDrops(block, StoneBlockSets.COBBLED_NURGON.base());
            } else if (BuiltInRegistries.BLOCK.getKey(block).getPath().equals("medgon")) {
                cobbleDrops(block, StoneBlockSets.COBBLED_MEDGON.base());
            } else if (BuiltInRegistries.BLOCK.getKey(block).getPath().equals("gonluin")) {
                cobbleDrops(block, StoneBlockSets.COBBLED_GONLUIN.base());
            } else if (BuiltInRegistries.BLOCK.getKey(block).getPath().equals("limestone")) {
                cobbleDrops(block, StoneBlockSets.COBBLED_LIMESTONE.base());
            } else if (BuiltInRegistries.BLOCK.getKey(block).getPath().equals("dolomite")) {
                cobbleDrops(block, StoneBlockSets.COBBLED_DOLOMITE.base());
            } else if (BuiltInRegistries.BLOCK.getKey(block).getPath().equals("quartzite")) {
                cobbleDrops(block, StoneBlockSets.COBBLED_QUARTZITE.base());
            } else if (BuiltInRegistries.BLOCK.getKey(block).getPath().equals("jadeite")) {
                cobbleDrops(block, StoneBlockSets.COBBLED_JADEITE.base());
            } else if (BuiltInRegistries.BLOCK.getKey(block).getPath().equals("ashen_stone")) {
                cobbleDrops(block, StoneBlockSets.ASHEN_COBBLESTONE.base());
            } else if (BuiltInRegistries.BLOCK.getKey(block).getPath().equals("ironstone")) {
                cobbleDrops(block, StoneBlockSets.COBBLED_IRONSTONE.base());
            } else if (BuiltInRegistries.BLOCK.getKey(block).getPath().equals("hematite")) {
                cobbleDrops(block, StoneBlockSets.COBBLED_HEMATITE.base());
            } else if (BuiltInRegistries.BLOCK.getKey(block).getPath().equals("gneiss")) {
                cobbleDrops(block, StoneBlockSets.COBBLED_GNEISS.base());
            } else if (BuiltInRegistries.BLOCK.getKey(block).getPath().equals("izheraban")) {
                cobbleDrops(block, StoneBlockSets.COBBLED_IZHERABAN.base());
            } else if (BuiltInRegistries.BLOCK.getKey(block).getPath().equals("schist")) {
                cobbleDrops(block, StoneBlockSets.COBBLED_SCHIST.base());
            } else if (BuiltInRegistries.BLOCK.getKey(block).getPath().equals("galonn")) {
                cobbleDrops(block, StoneBlockSets.COBBLED_GALONN.base());
            } else if (BuiltInRegistries.BLOCK.getKey(block).getPath().equals("slate")) {
                cobbleDrops(block, StoneBlockSets.COBBLED_SLATE.base());
            } else if (BuiltInRegistries.BLOCK.getKey(block).getPath().equals("blue_tuff")) {
                cobbleDrops(block, StoneBlockSets.COBBLED_BLUE_TUFF.base());
            } else if (BuiltInRegistries.BLOCK.getKey(block).getPath().contains("_door")) {
                add(block, createDoorTable(block));
            } else if (BuiltInRegistries.BLOCK.getKey(block).getPath().contains("vertical_slab")) {
                add(block, verticalSlabDrops(block));
            } else {
                // TODO : @SlooshyBoi crashes during Datagen
                if (block == null) continue;
                dropSelf(block);
            }
        }
        HolderLookup.RegistryLookup<Enchantment> enchantmentRegistry;

        try {
            enchantmentRegistry = registryLookup.get().lookupOrThrow(Registries.ENCHANTMENT);
        } catch (Exception ignored) {
            throw new IllegalStateException("Data generation without registries failed!");
        }

        for (LeavesDrops.LeavesDrop drop : LeavesDrops.blocks) {
            if (drop.toString().contains("pine")) {
                add(drop.block(), this.createLeavesDrops(drop.block(), drop.drop(), NORMAL_LEAVES_SAPLING_CHANCES)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F)).when(WITHOUT_SILK_TOUCH_NOR_SHEARS)
                                .add(((LootPoolSingletonContainer.Builder) this.applyExplosionCondition(drop.drop(), LootItem.lootTableItem(ModResourceItems.PINECONE)))
                                        .when(BonusLevelTableCondition.bonusLevelFlatChance(enchantmentRegistry.getOrThrow(Enchantments.FORTUNE), new float[]{0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F})))));

            } else {
                add(drop.block(), this.createLeavesDrops(drop.block(), drop.drop(), NORMAL_LEAVES_SAPLING_CHANCES));
            }
        }
        for (CropDrops.CropDrop cd : CropDrops.crops) {
            add(cd.crop_block, createCropDrops(cd.crop_block, cd.fruit, cd.seeds, cd.builder));
        }
        for (CropDrops.CropDrop cd : CropDrops.wild_crops) {
            add(cd.crop_block,
                    LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(cd.seeds)
                                    .when(LootItemRandomChanceCondition.randomChance(0.125f)))
                            .add(LootItem.lootTableItem(cd.fruit))));
        }

        for (Block pot : PotDrops.pots) {
            dropPottedContents(pot);
        }

        add(ModNatureBlocks.DEAD_RUSHES, createGrassDrops(ModNatureBlocks.DEAD_RUSHES));
        add(ModNatureBlocks.FALSE_OATGRASS, createGrassDrops(ModNatureBlocks.FALSE_OATGRASS));
        add(ModNatureBlocks.BRACKEN, createGrassDrops(ModNatureBlocks.BRACKEN));

        for (Block block : TintableCrossModel.grassLikeBlocks) {
            add(block, createGrassDrops(block));
        }
        for (Block block : TintableCrossModel.tintedBlocks) {
            dropWhenSilkTouch(block);
        }

        for (OreRockSets.OreRockSet set : OreRockSets.sets) {
            if (set.coal_ore() != null) {
                add(set.coal_ore(), createOreDrop(set.coal_ore(), Items.COAL));
            }
            if (set.copper_ore() != null) {
                add(set.copper_ore(), createCopperOreDrops(set.copper_ore()));
            }
            if (set.tin_ore() != null) {
                add(set.tin_ore(), createOreDrop(set.tin_ore(), ModResourceItems.RAW_TIN));
            }
            if (set.lead_ore() != null) {
                add(set.lead_ore(), createOreDrop(set.lead_ore(), ModResourceItems.RAW_LEAD));
            }
            if (set.silver_ore() != null) {
                add(set.silver_ore(), createOreDrop(set.silver_ore(), ModResourceItems.RAW_SILVER));
            }
            if (set.gold_ore() != null) {
                add(set.gold_ore(), createOreDrop(set.gold_ore(), Items.RAW_GOLD));
            }
            if (set.iron_ore() != null) {
                add(set.iron_ore(), createOreDrop(set.iron_ore(), Items.RAW_IRON));
            }
            if (set.mithril_ore() != null) {
                add(set.mithril_ore(), createOreDrop(set.mithril_ore(), ModResourceItems.RAW_MITHRIL));
            }
        }

        for (SimplePaneModel.Pane pane : SimplePaneModel.panes){
            dropWhenSilkTouch(pane.pane());
            dropWhenSilkTouch(pane.glass());
        }

        cobbleDrops(ModBlocks.STONE_MYCELIUM, Blocks.COBBLESTONE);

        largeDoorDrop(ModDecorativeBlocks.LARCH_HOBBIT_DOOR);
        largeDoorDrop(ModDecorativeBlocks.SPRUCE_HOBBIT_DOOR);
        largeDoorDrop(ModDecorativeBlocks.BLUE_HOBBIT_DOOR);
        largeDoorDrop(ModDecorativeBlocks.GREEN_HOBBIT_DOOR);
        largeDoorDrop(ModDecorativeBlocks.LIGHT_BLUE_HOBBIT_DOOR);
        largeDoorDrop(ModDecorativeBlocks.RED_HOBBIT_DOOR);
        largeDoorDrop(ModDecorativeBlocks.YELLOW_HOBBIT_DOOR);
        largeDoorDrop(ModDecorativeBlocks.TALL_BLACK_PINE_DOOR);
        largeDoorDrop(ModDecorativeBlocks.OAK_STABLE_DOOR);
        largeDoorDrop(ModDecorativeBlocks.REINFORCED_SPRUCE_DOOR);
        largeDoorDrop(ModDecorativeBlocks.REINFORCED_BLACK_PINE_DOOR);
        largeDoorDrop(ModDecorativeBlocks.SIMPLE_LARCH_GATE);
        largeDoorDrop(ModDecorativeBlocks.RICKETY_SIMPLE_LARCH_DOOR);
        largeDoorDrop(ModDecorativeBlocks.SPRUCE_STABLE_DOOR);
        largeDoorDrop(ModDecorativeBlocks.LARGE_STURDY_DOOR);
        largeDoorDrop(ModDecorativeBlocks.GREAT_GONDORIAN_GATE);
        largeDoorDrop(ModDecorativeBlocks.GREAT_DWARVEN_GATE);
        largeDoorDrop(ModDecorativeBlocks.VARNISHED_DWARVEN_DOOR);
        largeDoorDrop(ModDecorativeBlocks.RUINED_DWARVEN_DOOR);
        largeDoorDrop(ModDecorativeBlocks.HIDDEN_DWARVEN_DOOR);
        largeDoorDrop(ModDecorativeBlocks.GREAT_ELVEN_GATE);
        largeDoorDrop(ModDecorativeBlocks.GREAT_ORCISH_GATE);

        cobbleDrops(ModBlocks.SNOWY_GRASS_BLOCK, ModBlocks.DRY_DIRT);

        cobbleDrops(ModNatureBlocks.OLD_PODZOL, Blocks.DIRT);
        cobbleDrops(ModNatureBlocks.LORIEN_PODZOL, Blocks.DIRT);

        for (SimpleRocksModel.Rocks rock : SimpleRocksModel.rocks) {
            rocksDrop(rock.rocks());
        }

        for (SimpleRocksModel.Rocks rock : SimpleRocksModel.vanillaRocks) {
            rocksDrop(rock.rocks());
        }
    }

    public void rocksDrop(Block rocksDrop) {
        add(rocksDrop, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(rocksDrop).setProperties(
                                StatePropertiesPredicate.Builder.properties().hasProperty(RocksBlock.STAGE, 0)))
                        .setRolls(ConstantValue.exactly(1.0f))
                        .add(LootItem.lootTableItem(rocksDrop)))
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(rocksDrop).setProperties(
                                StatePropertiesPredicate.Builder.properties().hasProperty(RocksBlock.STAGE, 1)))
                        .setRolls(ConstantValue.exactly(2.0f))
                        .add(LootItem.lootTableItem(rocksDrop)))
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(rocksDrop).setProperties(
                                StatePropertiesPredicate.Builder.properties().hasProperty(RocksBlock.STAGE, 2)))
                        .setRolls(ConstantValue.exactly(3.0f))
                        .add(LootItem.lootTableItem(rocksDrop)))
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(rocksDrop).setProperties(
                                StatePropertiesPredicate.Builder.properties().hasProperty(RocksBlock.STAGE, 3)))
                        .setRolls(ConstantValue.exactly(4.0f))
                        .add(LootItem.lootTableItem(rocksDrop))));
    }

    public LootTable.Builder verticalSlabDrops(Block drop) {
        return LootTable.lootTable().withPool(
                LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add((LootPoolEntryContainer.Builder)this.applyExplosionDecay(drop, LootItem.lootTableItem(drop).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(drop).setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties().hasProperty(VerticalSlabBlock.DOUBLE, true)))))));
    }

    public void cobbleDrops(Block stoneBlock, Block cobbledBlock) {
        HolderLookup.RegistryLookup<Enchantment> enchantmentRegistry;

        try {
            enchantmentRegistry = registryLookup.get().lookupOrThrow(Registries.ENCHANTMENT);
        } catch (Exception ignored) {
            throw new IllegalStateException("Data generation without registries failed!");
        }
        add(stoneBlock,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .when(MatchTool.toolMatches(ItemPredicate.Builder.item().withSubPredicate(ItemSubPredicates.ENCHANTMENTS, ItemEnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(enchantmentRegistry.getOrThrow(Enchantments.SILK_TOUCH), MinMaxBounds.Ints.atLeast(1)))))))
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(stoneBlock)))
                        .withPool(LootPool.lootPool()
                                .when(MatchTool.toolMatches(ItemPredicate.Builder.item().withSubPredicate(ItemSubPredicates.ENCHANTMENTS, ItemEnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(enchantmentRegistry.getOrThrow(Enchantments.SILK_TOUCH), MinMaxBounds.Ints.atLeast(1)))))).invert())
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(cobbledBlock))));
    }

    public void largeDoorDrop(Block doorblock) {
        add(doorblock, LootTable.lootTable().withPool((LootPool.Builder) this.applyExplosionCondition(doorblock, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(doorblock).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(doorblock).setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties().hasProperty(LargeDoorBlock.PART, 0)))))));
    }

    public LootTable.Builder createLeavesDrops(Block leaves, Block drop, float... chance) {
        HolderLookup.RegistryLookup<Enchantment> enchantmentRegistry;

        try {
            enchantmentRegistry = registryLookup.get().lookupOrThrow(Registries.ENCHANTMENT);
        } catch (Exception ignored) {
            throw new IllegalStateException("Data generation without registries failed!");
        }
        return createSelfDropDispatchTable(leaves, this.hasShearsOrSilkTouch(), ((LootPoolSingletonContainer.Builder) this.applyExplosionCondition(leaves, LootItem.lootTableItem(drop)))
                .when(BonusLevelTableCondition.bonusLevelFlatChance(enchantmentRegistry.getOrThrow(Enchantments.FORTUNE), chance))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f))
                .when(WITHOUT_SILK_TOUCH_NOR_SHEARS).add((LootPoolEntryContainer.Builder<?>) ((LootPoolSingletonContainer.Builder) this.applyExplosionDecay(leaves, LootItem.lootTableItem(Items.STICK)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)))))
                        .when(BonusLevelTableCondition.bonusLevelFlatChance(enchantmentRegistry.getOrThrow(Enchantments.FORTUNE), LEAVES_STICK_DROP_CHANCE))));
    }
}
