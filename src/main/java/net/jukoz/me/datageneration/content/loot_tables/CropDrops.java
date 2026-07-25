package net.jukoz.me.datageneration.content.loot_tables;

import net.jukoz.me.block.ModNatureBlocks;
import net.jukoz.me.block.crop.*;
import net.jukoz.me.item.ModFoodItems;
import net.jukoz.me.item.ModResourceItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import java.util.ArrayList;
import java.util.List;

public class CropDrops {
    public static List<CropDrop> crops = new ArrayList<>() {
        {
            add(new CropDrop(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModNatureBlocks.TOMATO_CROP).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TomatoCropBlock.AGE, 3)),
                    ModNatureBlocks.TOMATO_CROP, ModFoodItems.TOMATO, ModResourceItems.TOMATO_SEEDS));
            add(new CropDrop(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModNatureBlocks.BELL_PEPPER_CROP).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BellpepperCropBlock.AGE, 4)),
                    ModNatureBlocks.BELL_PEPPER_CROP, ModFoodItems.BELL_PEPPER, ModResourceItems.BELL_PEPPER_SEEDS));
            add(new CropDrop(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModNatureBlocks.CUCUMBER_CROP).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CucumberCropBlock.AGE, 3)),
                    ModNatureBlocks.CUCUMBER_CROP, ModFoodItems.CUCUMBER, ModResourceItems.CUCUMBER_SEEDS));
            add(new CropDrop(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModNatureBlocks.FLAX_CROP).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(FlaxCropBlock.AGE, 3)),
                    ModNatureBlocks.FLAX_CROP, ModResourceItems.FLAX, ModResourceItems.FLAX_SEEDS));
            add(new CropDrop(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModNatureBlocks.GARLIC_CROP).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GarlicCropBlock.AGE, 3)),
                    ModNatureBlocks.GARLIC_CROP, ModFoodItems.GARLIC, ModFoodItems.GARLIC));
            add(new CropDrop(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModNatureBlocks.LEEK_CROP).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LeekCropBlock.AGE, 3)),
                    ModNatureBlocks.LEEK_CROP, ModFoodItems.LEEK, ModFoodItems.LEEK));
            add(new CropDrop(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModNatureBlocks.LETTUCE_CROP).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LettuceCropBlock.AGE, 3)),
                    ModNatureBlocks.LETTUCE_CROP, ModFoodItems.LETTUCE, ModResourceItems.LETTUCE_SEEDS));
            add(new CropDrop(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModNatureBlocks.ONION_CROP).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OnionCropBlock.AGE, 3)),
                    ModNatureBlocks.ONION_CROP, ModFoodItems.ONION, ModFoodItems.ONION));
            add(new CropDrop(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModNatureBlocks.PIPEWEED_CROP).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PipeweedCropBlock.AGE, 3)),
                    ModNatureBlocks.PIPEWEED_CROP, ModResourceItems.PIPEWEED, ModResourceItems.PIPEWEED_SEEDS));
        }
    };

    public static List<CropDrop> wild_crops = new ArrayList<>() {
        {
            add(new CropDrop(null, ModNatureBlocks.WILD_PIPEWEED, ModResourceItems.PIPEWEED, ModResourceItems.PIPEWEED_SEEDS));
            add(new CropDrop(null, ModNatureBlocks.WILD_FLAX, ModResourceItems.FLAX, ModResourceItems.FLAX_SEEDS));
            add(new CropDrop(null, ModNatureBlocks.WILD_TOMATO, ModFoodItems.TOMATO, ModResourceItems.TOMATO_SEEDS));
            add(new CropDrop(null, ModNatureBlocks.WILD_BELL_PEPPER, ModFoodItems.BELL_PEPPER, ModResourceItems.BELL_PEPPER_SEEDS));
            add(new CropDrop(null, ModNatureBlocks.WILD_CUCUMBER, ModFoodItems.CUCUMBER, ModResourceItems.CUCUMBER_SEEDS));
            add(new CropDrop(null, ModNatureBlocks.WILD_LEEK, ModFoodItems.LEEK, ModFoodItems.LEEK));
            add(new CropDrop(null, ModNatureBlocks.WILD_LETTUCE, ModFoodItems.LETTUCE, ModResourceItems.LETTUCE_SEEDS));
            add(new CropDrop(null, ModNatureBlocks.WILD_GARLIC, ModFoodItems.GARLIC, ModFoodItems.GARLIC));
            add(new CropDrop(null, ModNatureBlocks.WILD_ONION, ModFoodItems.ONION, ModFoodItems.ONION));
            add(new CropDrop(null, ModNatureBlocks.WILD_POTATO, Items.POTATO, Items.POTATO));
            add(new CropDrop(null, ModNatureBlocks.WILD_CARROT, Items.CARROT, Items.CARROT));
            add(new CropDrop(null, ModNatureBlocks.WILD_BEETROOT, Items.BEETROOT, Items.BEETROOT_SEEDS));
        }
    };

    public static class CropDrop {
        public LootItemBlockStatePropertyCondition.Builder builder;
        public Block crop_block;
        public Item fruit;
        public Item seeds;

        public CropDrop(LootItemBlockStatePropertyCondition.Builder builder, Block block, Item fruit, Item seeds) {
            this.builder = builder;
            this.crop_block = block;
            this.fruit = fruit;
            this.seeds = seeds;
        }
    }
}
