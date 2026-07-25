package net.jukoz.me.item.items.armor;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.utils.ClientSideAccess;
import net.jukoz.me.utils.ModFactions;
import net.jukoz.me.utils.ModSubFactions;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import java.util.List;

public class CustomHorseArmorItem extends AnimalArmorItem {

    private final ModFactions faction;
    private final ModSubFactions subFaction;

    public CustomHorseArmorItem(Holder<ArmorMaterial> material, BodyType type, boolean hasOverlay, Properties settings, ModFactions faction) {
        super(material, type, hasOverlay, settings);
        this.faction = faction;
        this.subFaction = null;
    }

    public CustomHorseArmorItem(Holder<ArmorMaterial> material, BodyType type, boolean hasOverlay, Properties settings, ModSubFactions subFaction) {
        super(material, type, hasOverlay, settings);
        this.faction = subFaction.getParent();
        this.subFaction = subFaction;
    }


    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        ResolvableProfile profileComponent = stack.get(DataComponents.PROFILE);

        tooltip.add(Component.nullToEmpty(""));
        if (ClientSideAccess.hasShiftDown()) {
            tooltip.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".faction").append(Component.translatable("tooltip." + MiddleEarth.MOD_ID + "." + faction.getName())));
            if (this.subFaction != null) {
                tooltip.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".sub_faction").append(Component.translatable("tooltip." + MiddleEarth.MOD_ID + "." + subFaction.getName())));
            }
            if (profileComponent != null && profileComponent.name().isPresent()) {
                tooltip.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".artisan").append(profileComponent.name().get()).withStyle(ChatFormatting.GRAY));
            }
            tooltip.add(Component.nullToEmpty(""));
        } else {
            tooltip.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".shift"));
        }

        super.appendHoverText(stack, context, tooltip, type);
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers() {
        return super.getDefaultAttributeModifiers();
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
