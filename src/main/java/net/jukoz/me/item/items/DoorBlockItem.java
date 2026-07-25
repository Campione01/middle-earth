package net.jukoz.me.item.items;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.special.LargeDoorBlock;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import java.util.List;

public class DoorBlockItem extends BlockItem {
    private final LargeDoorBlock block;

    public DoorBlockItem(LargeDoorBlock block, Properties settings) {
        super(block, settings);
        this.block = block;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        tooltip.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".door_size").append(block.getDoorWidth() + "x" + block.getDoorHeight()));
        super.appendHoverText(stack, context, tooltip, type);
    }
}
