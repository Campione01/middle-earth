package net.jukoz.me.item.items.weapons;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.item.utils.MEEquipmentTooltip;
import net.jukoz.me.item.utils.ModWeaponTypes;
import net.jukoz.me.utils.ModFactions;
import net.jukoz.me.utils.ModSubFactions;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import java.util.ArrayList;
import java.util.List;

public class CustomSwordWeaponItem extends SwordItem implements MEEquipmentTooltip {
    public final ModFactions faction;
    public final ModSubFactions subFaction;

    public final ModWeaponTypes type;

    public CustomSwordWeaponItem(Tier toolMaterial) {
        super(toolMaterial, new Item.Properties().attributes(createAttributeModifiersSword(toolMaterial, ModWeaponTypes.SWORD.attack, ModWeaponTypes.SWORD.attackSpeed)));
        this.faction = ModFactions.NONE;
        this.subFaction = null;
        this.type = ModWeaponTypes.SWORD;
    }

    public CustomSwordWeaponItem(Tier toolMaterial,  ModFactions faction) {
        super(toolMaterial, new Item.Properties().attributes(createAttributeModifiersSword(toolMaterial, ModWeaponTypes.SWORD.attack, ModWeaponTypes.SWORD.attackSpeed)));
        this.faction = faction;
        this.subFaction = null;
        this.type = ModWeaponTypes.SWORD;
    }

    public CustomSwordWeaponItem(Tier toolMaterial, ModSubFactions subFaction) {
        super(toolMaterial, new Item.Properties().attributes(createAttributeModifiersSword(toolMaterial, ModWeaponTypes.SWORD.attack, ModWeaponTypes.SWORD.attackSpeed)));
        this.faction = subFaction.getParent();
        this.subFaction = subFaction;
        this.type = ModWeaponTypes.SWORD;
    }

    public static ItemAttributeModifiers createAttributeModifiersSword(Tier material, float baseAttackDamage, float attackSpeed) {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID,
                        baseAttackDamage + material.getAttackDamageBonus(), AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID,
                        attackSpeed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .build();
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
