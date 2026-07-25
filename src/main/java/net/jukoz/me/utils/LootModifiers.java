package net.jukoz.me.utils;

import net.jukoz.me.item.ModFoodItems;
import net.jukoz.me.item.ModResourceItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.LootTableLoadEvent;

public class LootModifiers {

    private static final ResourceLocation HORSE_LOOT_TABLE_IDENTIFIER = ResourceLocation.fromNamespaceAndPath("minecraft", "entities/horse");
    private static final ResourceLocation GOAT_LOOT_TABLE_IDENTIFIER = ResourceLocation.fromNamespaceAndPath("minecraft", "entities/goat");

    public static final ResourceLocation FISHING_LOOT_TABLE_IDENTIFIER = ResourceLocation.fromNamespaceAndPath("me", "gameplay/fishing");
    public static final ResourceKey<LootTable> FISHING_LOOT_TABLE =
            ResourceKey.create(Registries.LOOT_TABLE, FISHING_LOOT_TABLE_IDENTIFIER);
    private static boolean registered;

    public static void modifyLootTables(){
        if (!registered) {
            registered = true;
            NeoForge.EVENT_BUS.addListener(LootModifiers::modifyLootTable);
        }
    }

    private static void modifyLootTable(LootTableLoadEvent event) {
        if(HORSE_LOOT_TABLE_IDENTIFIER.equals(event.getName())) {
            event.getTable().addPool(createHorsePool().build());
        }

        if(GOAT_LOOT_TABLE_IDENTIFIER.equals(event.getName())) {
            event.getTable().addPool(createGoatPool().build());
        }
    }

    private static LootPool.Builder createHorsePool() {
        return LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .when(LootItemRandomChanceCondition.randomChance(1f))
                .add(LootItem.lootTableItem(ModFoodItems.RAW_HORSE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)));
    }

    private static LootPool.Builder createGoatPool() {
        return LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .when(LootItemRandomChanceCondition.randomChance(1f))
                .add(LootItem.lootTableItem(ModResourceItems.FUR))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)));
    }
}
