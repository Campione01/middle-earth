package net.jukoz.me.item.items.weapons;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.item.utils.MEEquipmentTooltip;
import net.jukoz.me.item.utils.ModWeaponTypes;
import net.jukoz.me.utils.ModFactions;
import net.jukoz.me.utils.ModSubFactions;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import java.util.ArrayList;
import java.util.List;

public class ReachWeaponItem extends TieredItem implements MEEquipmentTooltip {

    public static final ResourceLocation ENTITY_INTERACTION_RANGE_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "entity_interaction_range");
    public float rangeDistance;

    public ModFactions faction;
    public ModSubFactions subFaction;
    public ModWeaponTypes type;

    public ReachWeaponItem(Tier toolMaterial, ModWeaponTypes type) {
        super(toolMaterial, new Item.Properties().attributes(createAttributeModifiers(toolMaterial, type.attack, type.attackSpeed, type.attackRange)));
        this.rangeDistance = type.attackRange;
        this.faction = ModFactions.NONE;
        this.subFaction = null;
        this.type = type;
    }

    public ReachWeaponItem(Tier toolMaterial, ModFactions faction, ModWeaponTypes type) {
        super(toolMaterial, new Item.Properties().attributes(createAttributeModifiers(toolMaterial, type.attack, type.attackSpeed, type.attackRange)));
        this.rangeDistance = type.attackRange;
        this.faction = faction;
        this.subFaction = null;
        this.type = type;
    }

    public ReachWeaponItem(Tier toolMaterial, ModSubFactions subFaction, ModWeaponTypes type) {
        super(toolMaterial, new Item.Properties().attributes(createAttributeModifiers(toolMaterial, type.attack, type.attackSpeed, type.attackRange)));
        this.rangeDistance = type.attackRange;
        this.faction = subFaction.getParent();
        this.subFaction = subFaction;
        this.type = type;
    }

    public boolean canAttackBlock(BlockState state, Level world, BlockPos pos, Player miner) {
        return !miner.isCreative();
    }

    public static ItemAttributeModifiers createAttributeModifiers(Tier material, float baseAttackDamage, float attackSpeed, float rangeDistance) {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID,
                        (float)baseAttackDamage + material.getAttackDamageBonus(), AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID,
                        attackSpeed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ENTITY_INTERACTION_RANGE_MODIFIER_ID,
                        rangeDistance, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
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

    public float getRangeDistance() {
        return rangeDistance;
    }

    public ModWeaponTypes getType() {
        return type;
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

    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity miner) {
        Tool toolComponent = (Tool)stack.get(DataComponents.TOOL);
        if (toolComponent == null) {
            return false;
        } else {
            if (!world.isClientSide && state.getDestroySpeed(world, pos) != 0.0F && toolComponent.damagePerBlock() > 0) {
                stack.hurtAndBreak(1, miner, EquipmentSlot.MAINHAND);
            }

            return true;
        }
    }
}
