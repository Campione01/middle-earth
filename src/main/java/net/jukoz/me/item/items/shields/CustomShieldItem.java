package net.jukoz.me.item.items.shields;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.item.utils.MEEquipmentTooltip;
import net.jukoz.me.item.utils.ModShieldTypes;
import net.jukoz.me.utils.ModFactions;
import net.jukoz.me.utils.ModSubFactions;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.TooltipFlag;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CustomShieldItem extends ShieldItem implements MEEquipmentTooltip {
    public final ModFactions faction;
    public final ModSubFactions subFaction;
    public final ModShieldTypes type;
    public final static HashSet<CustomShieldItem> instances = new HashSet<>();

    public CustomShieldItem(ModShieldTypes type, ModFactions faction) {
        super(new Item.Properties().stacksTo(1).durability(type.durability));
        this.type = type;
        this.faction = faction;
        this.subFaction = null;
        instances.add(this);
    }

    public CustomShieldItem(ModShieldTypes type, ModSubFactions subFaction) {
        super(new Item.Properties().stacksTo(1).durability(type.durability));
        this.type = type;
        this.faction = subFaction.getParent();
        this.subFaction = subFaction;
        instances.add(this);
    }

    @Override
    public List<Component> getAdditionalShiftLines(ItemStack stack) {
        List<Component> list = new ArrayList<>(List.of());

        list.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + "." + this.type.name));

        return list;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        appendBaseTooltip(tooltip, stack, this.faction, this.subFaction);
        super.appendHoverText(stack, context, tooltip, type);
    }
}
