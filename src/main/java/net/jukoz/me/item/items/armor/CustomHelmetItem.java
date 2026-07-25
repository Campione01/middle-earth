package net.jukoz.me.item.items.armor;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.item.ModDataComponentTypes;
import net.jukoz.me.item.dataComponents.CapeDataComponent;
import net.jukoz.me.item.dataComponents.CustomDyeableDataComponent;
import net.jukoz.me.item.dataComponents.HoodDataComponent;
import net.jukoz.me.item.utils.MEEquipmentTooltip;
import net.jukoz.me.item.utils.armor.ExtendedArmorMaterial;
import net.jukoz.me.item.utils.armor.ModDyeablePieces;
import net.jukoz.me.utils.ModFactions;
import net.jukoz.me.utils.ModSubFactions;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import java.util.ArrayList;
import java.util.List;

public class CustomHelmetItem extends ArmorItem implements MEEquipmentTooltip {

    public ModFactions faction;
    public ModSubFactions subFaction;

    private ExtendedArmorMaterial material;

    public CustomHelmetItem(ExtendedArmorMaterial material, Properties settings, ModFactions faction) {
        super(material.material(), ArmorItem.Type.HELMET, settings.stacksTo(1).durability(Type.HELMET.getDurability(material.durabilityModifier())));

        this.material = material;
        this.faction = faction;
        this.subFaction = null;
    }

    public CustomHelmetItem(ExtendedArmorMaterial material, Properties settings, ModSubFactions subFaction) {
        super(material.material(), ArmorItem.Type.HELMET, settings.stacksTo(1).durability(Type.HELMET.getDurability(material.durabilityModifier())));

        this.material = material;
        this.faction = subFaction.getParent();
        this.subFaction = subFaction;
    }

    @Override
    public List<Component> getAdditionalShiftLines(ItemStack stack) {
        List<Component> list = new ArrayList<>(List.of());
        list.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".tier_" + this.material.tier().toString().toLowerCase()));

        return list;
    }

    @Override
    public List<Component> getAdditionalAltLines(ItemStack stack) {
        List<Component> list = new ArrayList<>(List.of());
        HoodDataComponent hoodDataComponent = stack.get(ModDataComponentTypes.HOOD_DATA);
        CustomDyeableDataComponent dyeDataComponent = stack.get(ModDataComponentTypes.DYE_DATA);

        if(dyeDataComponent != null){
            list.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".color").append(": " + String.format(MEEquipmentTooltip.COLOR_PREFIX, (0xFFFFFF & CustomDyeableDataComponent.getColor(stack, CustomDyeableDataComponent.DEFAULT_COLOR)))).withStyle(ChatFormatting.GRAY));
        }
        if (hoodDataComponent != null) {
            if (ModDyeablePieces.dyeableHoods.containsKey(hoodDataComponent.hood())){
                list.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + "." + hoodDataComponent.hood().getName())
                        .append(" (")
                        .append(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".color")
                                .append(": " + String.format("#%06X", (0xFFFFFF & HoodDataComponent.getColor(stack, CustomDyeableDataComponent.DEFAULT_COLOR))))
                                .append(")")).withStyle(ChatFormatting.GRAY));
            } else {
                list.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + "." + hoodDataComponent.hood().getName()).withStyle(ChatFormatting.GRAY));
            }
        }

        return list;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        appendBaseTooltip(tooltip, stack, this.faction, this.subFaction);
        super.appendHoverText(stack, context, tooltip, type);
    }

    public static void toggleHoodState(ServerPlayer player, ItemStack stack){
        HoodDataComponent hoodDataComponent = stack.get(ModDataComponentTypes.HOOD_DATA);
        if (hoodDataComponent != null){
            if (hoodDataComponent.down() && hoodDataComponent.getHood().getConstantState() == null) {
                stack.set(ModDataComponentTypes.HOOD_DATA, new HoodDataComponent(false, hoodDataComponent.hood(), hoodDataComponent.hoodColor()));
                player.displayClientMessage(Component.translatable("alert." + MiddleEarth.MOD_ID + ".hood_up"), true);
            } else if (!hoodDataComponent.down() && hoodDataComponent.getHood().getConstantState() == null){
                stack.set(ModDataComponentTypes.HOOD_DATA, new HoodDataComponent(true, hoodDataComponent.hood(), hoodDataComponent.hoodColor()));
                player.displayClientMessage(Component.translatable("alert." + MiddleEarth.MOD_ID + ".hood_down"), true);
            }
        }
    }
}
