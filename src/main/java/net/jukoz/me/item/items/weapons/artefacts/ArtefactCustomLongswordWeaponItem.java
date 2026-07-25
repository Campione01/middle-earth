package net.jukoz.me.item.items.weapons.artefacts;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.item.items.weapons.CustomLongswordWeaponItem;
import net.jukoz.me.utils.ModFactions;
import net.jukoz.me.utils.ModSubFactions;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import java.util.ArrayList;
import java.util.List;

public class ArtefactCustomLongswordWeaponItem extends CustomLongswordWeaponItem {
    public static final ResourceLocation ENTITY_INTERACTION_RANGE_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "entity_interaction_range");

    public ArtefactCustomLongswordWeaponItem(Tier toolMaterial) {
        super(toolMaterial);
    }

    public ArtefactCustomLongswordWeaponItem(Tier toolMaterial, ModFactions faction) {
        super(toolMaterial, faction);
    }

    public ArtefactCustomLongswordWeaponItem(Tier toolMaterial, ModSubFactions subFaction) {
        super(toolMaterial, subFaction);
    }

    public static boolean isUsable(ItemStack stack) {
        return stack.getDamageValue() < stack.getMaxDamage() - 1;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        appendBaseArtefactTooltip(tooltip, stack);
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack ingredient) {
        return false;
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.translatable(this.getDescriptionId(stack)).withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC);
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

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (stack.getDamageValue() < stack.getMaxDamage() - 1){
            stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
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
    public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity miner) {
        Tool toolComponent = (Tool)stack.get(DataComponents.TOOL);
        if (toolComponent == null) {
            return false;
        } else {
            if (!world.isClientSide && state.getDestroySpeed(world, pos) != 0.0F && toolComponent.damagePerBlock() > 0) {
                if (stack.getDamageValue() < stack.getMaxDamage() - 1){
                    stack.hurtAndBreak(1, miner, EquipmentSlot.MAINHAND);
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
            return true;
        }
    }
}
