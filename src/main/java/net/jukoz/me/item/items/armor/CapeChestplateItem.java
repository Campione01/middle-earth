package net.jukoz.me.item.items.armor;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.item.ModDataComponentTypes;
import net.jukoz.me.item.dataComponents.CapeDataComponent;
import net.jukoz.me.item.dataComponents.CustomDyeableDataComponent;
import net.jukoz.me.item.utils.MEEquipmentTooltip;
import net.jukoz.me.item.utils.armor.ExtendedArmorMaterial;
import net.jukoz.me.item.utils.armor.ModArmorMaterials;
import net.jukoz.me.utils.ModFactions;
import net.jukoz.me.utils.ModSubFactions;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import java.util.ArrayList;
import java.util.List;

public class CapeChestplateItem extends ArmorItem implements MEEquipmentTooltip {
    public ModFactions faction;
    public ModSubFactions subFaction;

    public CapeChestplateItem(Properties settings, ExtendedArmorMaterial armorMaterial, ModFactions faction) {
        super(armorMaterial.material(), Type.CHESTPLATE, settings
                .stacksTo(1)
                .durability(Type.CHESTPLATE.getDurability(armorMaterial.durabilityModifier())));
        this.faction = faction;
        this.subFaction = null;
    }

    public CapeChestplateItem(Properties settings, ExtendedArmorMaterial armorMaterial, ModSubFactions subFaction) {
        super(armorMaterial.material(), Type.CHESTPLATE, settings
                .stacksTo(1)
                .durability(Type.CHESTPLATE.getDurability(armorMaterial.durabilityModifier())));
        this.faction = subFaction.getParent();
        this.subFaction = subFaction;
    }

    @Override
    public List<Component> getAdditionalAltLines(ItemStack stack) {
        List<Component> list = new ArrayList<>(List.of());
        CapeDataComponent capeDataComponent = stack.get(ModDataComponentTypes.CAPE_DATA);
        CustomDyeableDataComponent dyeDataComponent = stack.get(ModDataComponentTypes.DYE_DATA);

        if(dyeDataComponent != null){
            list.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".dyed").append(String.format(MEEquipmentTooltip.COLOR_PREFIX, (0xFFFFFF & CustomDyeableDataComponent.getColor(stack, CustomDyeableDataComponent.DEFAULT_COLOR)))).withStyle(ChatFormatting.GRAY));
        }
        if (capeDataComponent != null) {
            list.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + "." + capeDataComponent.cape().getName()).withStyle(ChatFormatting.GRAY));
        }

        return list;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        appendBaseTooltip(tooltip, stack, this.faction, this.subFaction);
        super.appendHoverText(stack, context, tooltip, type);
    }
}
