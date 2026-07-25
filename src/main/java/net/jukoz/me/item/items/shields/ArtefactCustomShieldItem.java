package net.jukoz.me.item.items.shields;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.item.utils.MEEquipmentTooltip;
import net.jukoz.me.item.utils.ModShieldTypes;
import net.jukoz.me.utils.ModFactions;
import net.jukoz.me.utils.ModSubFactions;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import java.util.ArrayList;
import java.util.List;

public class ArtefactCustomShieldItem extends CustomShieldItem implements MEEquipmentTooltip {

    public ArtefactCustomShieldItem(ModShieldTypes type, ModFactions faction) {
        super(type, faction);
    }

    public ArtefactCustomShieldItem(ModShieldTypes type, ModSubFactions subFaction) {
        super(type, subFaction);
    }

    @Override
    public List<Component> getAdditionalShiftLines(ItemStack stack) {
        List<Component> list = new ArrayList<>(List.of());
        list.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + "." + this.type.name));
        return list;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        appendBaseArtefactTooltip(tooltip, stack);
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.translatable(this.getDescriptionId(stack)).withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC);
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
