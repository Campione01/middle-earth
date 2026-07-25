package net.jukoz.me.item.items;

import net.jukoz.me.item.ModDataComponentTypes;
import net.jukoz.me.item.dataComponents.TemperatureDataComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import java.util.List;

public class SmithingMaterialItem extends Item {

    public SmithingMaterialItem(Properties settings) {
        super(settings);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        TemperatureDataComponent temperatureDataComponent = stack.get(ModDataComponentTypes.TEMPERATURE_DATA);
    }
}
