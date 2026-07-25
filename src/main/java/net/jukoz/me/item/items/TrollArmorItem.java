package net.jukoz.me.item.items;

import net.jukoz.me.MiddleEarth;
import net.minecraft.core.Holder;
import net.minecraft.world.item.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;


public class TrollArmorItem extends AnimalArmorItem {

    private static final String ENTITY_TEXTURE_PREFIX = "textures/entity/troll/";
    private final String entityTexture;


    public TrollArmorItem(Holder<ArmorMaterial> material, BodyType type, boolean hasOverlay, Properties settings) {
        super(material, type, hasOverlay, settings);
        this.entityTexture = "textures/entity/troll/armor/troll_armor_steel.png";
    }

    public ResourceLocation getTexture() {
        return ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID ,this.entityTexture);
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return super.supportsEnchantment(stack, enchantment);
    }

    @Override
    public InteractionResultHolder<ItemStack> swapWithEquipmentSlot(Item item, Level world, Player user, InteractionHand hand) {
        return super.swapWithEquipmentSlot(item, world, user, hand);
    }

    @Override
    public boolean isEnabled(FeatureFlagSet enabledFeatures) {
        return super.isEnabled(enabledFeatures);
    }
}
