package net.jukoz.me.item.utils;

import com.google.common.base.Suppliers;
import net.jukoz.me.block.StoneBlockSets;
import net.jukoz.me.item.ModResourceItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import java.util.function.Supplier;

public enum ModToolMaterials implements Tier
{
    BRONZE(BlockTags.INCORRECT_FOR_IRON_TOOL, 200, 5.0f, 1.0f, 5, () -> Ingredient.of(ModResourceItems.BRONZE_INGOT)),
    CRUDE(BlockTags.INCORRECT_FOR_IRON_TOOL, 200, 5.0f, 1.0f, 5, () -> Ingredient.of(ModResourceItems.CRUDE_INGOT)),
    STEEL(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 750, 7.0f, 2.0f, 10, () -> Ingredient.of(ModResourceItems.STEEL_INGOT)),
    NOBLE_STEEL(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1500, 7.0f, 2.0f, 10, () -> Ingredient.of(ModResourceItems.STEEL_INGOT)),
    BURZUM_STEEL(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 750, 7.0f, 2.0f, 10, () -> Ingredient.of(ModResourceItems.BURZUM_STEEL_INGOT)),
    NOBLE_BURZUM_STEEL(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1500, 7.0f, 2.0f, 10, () -> Ingredient.of(ModResourceItems.BURZUM_STEEL_INGOT)),
    EDHEL_STEEL(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 750, 7.0f, 2.0f, 10, () -> Ingredient.of(ModResourceItems.EDHEL_STEEL_INGOT)),
    NOBLE_EDHEL_STEEL(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1500, 7.0f, 2.0f, 10, () -> Ingredient.of(ModResourceItems.EDHEL_STEEL_INGOT)),
    KHAZAD_STEEL(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 750, 7.0f, 2.0f, 10, () -> Ingredient.of(ModResourceItems.KHAZAD_STEEL_INGOT)),
    NOBLE_KHAZAD_STEEL(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1500, 7.0f, 2.0f, 10, () -> Ingredient.of(ModResourceItems.KHAZAD_STEEL_INGOT)),
    MITHRIL(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 9.0f, 1.5f, 15, () -> Ingredient.of(ModResourceItems.MITHRIL_INGOT)),

    MORGUL_KNIFE(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2, 7.0f, 2.0f, 10, () -> Ingredient.of(ModResourceItems.STEEL_INGOT)),

    COPPER_HAMMER(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 16, 7.0f, 2.0f, 10, () -> Ingredient.of(Items.COPPER_INGOT)),
    
    STEEL_HAMMER(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 256, 7.0f, 2.0f, 10, () -> Ingredient.of(ModResourceItems.STEEL_INGOT)),
    NOBLE_STEEL_HAMMER(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 512, 7.0f, 2.0f, 10, () -> Ingredient.of(ModResourceItems.STEEL_INGOT)),

    KHAZAD_STEEL_HAMMER(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 256, 7.0f, 2.0f, 10, () -> Ingredient.of(ModResourceItems.KHAZAD_STEEL_INGOT)),
    KHAZAD_NOBLE_STEEL_HAMMER(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 512, 7.0f, 2.0f, 10, () -> Ingredient.of(ModResourceItems.KHAZAD_STEEL_INGOT)),

    EDHEL_STEEL_HAMMER(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 256, 7.0f, 2.0f, 10, () -> Ingredient.of(ModResourceItems.EDHEL_STEEL_INGOT)),
    EDHEL_NOBLE_STEEL_HAMMER(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 512, 7.0f, 2.0f, 10, () -> Ingredient.of(ModResourceItems.EDHEL_STEEL_INGOT)),

    BURZUM_STEEL_HAMMER(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 256, 7.0f, 2.0f, 10, () -> Ingredient.of(ModResourceItems.BURZUM_STEEL_INGOT)),
    BURZUM_NOBLE_STEEL_HAMMER(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 512, 7.0f, 2.0f, 10, () -> Ingredient.of(ModResourceItems.BURZUM_STEEL_INGOT)),

    MITHRIL_HAMMER(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 1024, 7.0f, 2.0f, 10, () -> Ingredient.of(ModResourceItems.MITHRIL_INGOT)),
    ;

    private final TagKey<Block> inverseTag;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    private ModToolMaterials(TagKey<Block> inverseTag, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.inverseTag = inverseTag;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return this.inverseTag;
    }

    @Override
    public int getUses() {
        return this.itemDurability;
    }

    @Override
    public float getSpeed() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}