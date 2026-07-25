package net.jukoz.me.item.items.weapons.artefacts;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.utils.ModFactions;
import net.jukoz.me.utils.ModSubFactions;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class MorgulKnifeItem extends ArtefactCustomDaggerWeaponItem {
    public static final ResourceLocation ENTITY_INTERACTION_RANGE_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "entity_interaction_range");

    public MorgulKnifeItem(Tier toolMaterial) {
        super(toolMaterial);
    }

    public MorgulKnifeItem(Tier toolMaterial, ModFactions faction) {
        super(toolMaterial, faction);
    }

    public MorgulKnifeItem(Tier toolMaterial, ModSubFactions subFaction) {
        super(toolMaterial, subFaction);
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (stack.getDamageValue() < stack.getMaxDamage() - 1){
            stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
            target.addEffect(new MobEffectInstance(MobEffects.WITHER, 6000));
            target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 6000));
        }
        if (stack.getDamageValue() == stack.getMaxDamage() - 1){
            stack.set(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.builder()
                    .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID,
                            0.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                    .add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID,
                            -3.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                    .add(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ENTITY_INTERACTION_RANGE_MODIFIER_ID,
                            0.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                    .build());
        }
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.translatable(this.getDescriptionId(stack)).withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC);
    }
}
