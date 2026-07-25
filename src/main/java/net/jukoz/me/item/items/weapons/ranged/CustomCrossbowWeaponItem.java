package net.jukoz.me.item.items.weapons.ranged;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.item.utils.MEEquipmentTooltip;
import net.jukoz.me.item.utils.ModRangedWeaponTypes;
import net.jukoz.me.utils.ModFactions;
import net.jukoz.me.utils.ModSubFactions;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CustomCrossbowWeaponItem extends CrossbowItem implements MEEquipmentTooltip {
    private final ModFactions faction;
    private final ModSubFactions subFaction;
    public ModRangedWeaponTypes type;

    public CustomCrossbowWeaponItem(ModRangedWeaponTypes type) {
        super(new Item.Properties().durability(type.durability));
        this.faction = null;
        this.subFaction = null;
        this.type = type;
    }

    public CustomCrossbowWeaponItem(ModFactions faction, ModRangedWeaponTypes type) {
        super(new Item.Properties().durability(type.durability));
        this.faction = faction;
        this.subFaction = null;
        this.type = type;
    }

    public CustomCrossbowWeaponItem(ModSubFactions subFaction, ModRangedWeaponTypes type) {
        super(new Item.Properties().durability(type.durability));
        this.faction = subFaction.getParent();
        this.subFaction = subFaction;
        this.type = type;
    }

    public Predicate<ItemStack> getSupportedHeldProjectiles() {
        return ARROW_ONLY;
    }

    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_ONLY;
    }

    @Override
    public List<Component> getAdditionalShiftLines(ItemStack stack) {
        List<Component> list = new ArrayList<>(List.of());

        list.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".weapon_type").append(Component.translatable("tooltip." + MiddleEarth.MOD_ID + "." + this.type.name)));

        return list;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        appendBaseTooltip(tooltip, stack, this.faction, this.subFaction);
        super.appendHoverText(stack, context, tooltip, type);
    }

    @Override
    public Component getName(ItemStack stack) {
        if(BuiltInRegistries.ITEM.getKey(this).getPath().contains("_noble")
                || BuiltInRegistries.ITEM.getKey(this).getPath().contains("_elite")
                || BuiltInRegistries.ITEM.getKey(this).getPath().contains("uruk_hai")
                || BuiltInRegistries.ITEM.getKey(this).getPath().contains("heyday")
                || BuiltInRegistries.ITEM.getKey(this).getPath().contains("numenorean")){
            return Component.translatable(this.getDescriptionId(stack)).withStyle(ChatFormatting.GOLD);
        }
        return super.getName(stack);
    }
}
