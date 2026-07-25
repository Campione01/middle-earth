package net.jukoz.me.item.items.armor.artefact;

import net.jukoz.me.item.items.armor.CustomHelmetItem;
import net.jukoz.me.item.utils.armor.ExtendedArmorMaterial;
import net.jukoz.me.utils.ModFactions;
import net.jukoz.me.utils.ModSubFactions;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import java.util.List;

public class CustomArtefactHelmetItem extends CustomHelmetItem {
    public CustomArtefactHelmetItem(ExtendedArmorMaterial material, Properties settings, ModFactions faction) {
        super(material, settings, faction);
    }

    public CustomArtefactHelmetItem(ExtendedArmorMaterial material, Properties settings, ModSubFactions subFaction) {
        super(material, settings, subFaction);
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.translatable(this.getDescriptionId(stack)).withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        appendBaseArtefactTooltip(tooltip, stack);
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack ingredient) {
        return false;
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        if(stack.getDamageValue() == stack.getMaxDamage() - 1) {
            return false;
        } else if( stack.getDamageValue() >= 1) {
            return true;
        } else {
            return false;
        }
    }
}
