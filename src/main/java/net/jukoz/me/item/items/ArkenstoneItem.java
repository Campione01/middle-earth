package net.jukoz.me.item.items;

import net.jukoz.me.item.utils.MEEquipmentTooltip;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import java.util.List;

public class ArkenstoneItem extends StandingAndWallBlockItem implements MEEquipmentTooltip {


    public ArkenstoneItem(Block standingBlock, Block wallBlock, Properties settings, Direction verticalAttachmentDirection) {
        super(standingBlock, wallBlock, settings, verticalAttachmentDirection);
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.translatable(this.getDescriptionId(stack)).withStyle(ChatFormatting.LIGHT_PURPLE).withStyle(ChatFormatting.ITALIC);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        appendBaseArtefactTooltip(tooltip, stack);
    }
}
