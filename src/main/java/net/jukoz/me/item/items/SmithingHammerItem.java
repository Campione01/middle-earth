package net.jukoz.me.item.items;

import net.jukoz.me.item.utils.ModToolMaterials;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class SmithingHammerItem extends TieredItem {

    public SmithingHammerItem(Properties settings, ModToolMaterials material, float speed) {
        super(material, settings.stacksTo(1).attributes(createAttributeModifiers(speed)));
    }

    public static ItemAttributeModifiers createAttributeModifiers(float attackSpeed) {
        return ItemAttributeModifiers.builder().add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, attackSpeed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build();
    }

    public boolean canAttackBlock(BlockState state, Level world, BlockPos pos, Player miner) {
        return false;
    }
}
