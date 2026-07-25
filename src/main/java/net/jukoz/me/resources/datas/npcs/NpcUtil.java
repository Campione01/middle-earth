package net.jukoz.me.resources.datas.npcs;

import net.jukoz.me.item.items.weapons.ReachWeaponItem;
import net.jukoz.me.resources.datas.npcs.data.NpcGearData;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class NpcUtil {
    public static void equipAll(LivingEntity entity, NpcGearData data) {
        entity.setItemSlot(EquipmentSlot.HEAD, data.get(EquipmentSlot.HEAD));
        entity.setItemSlot(EquipmentSlot.CHEST, data.get(EquipmentSlot.CHEST));
        entity.setItemSlot(EquipmentSlot.LEGS, data.get(EquipmentSlot.LEGS));
        entity.setItemSlot(EquipmentSlot.FEET, data.get(EquipmentSlot.FEET));

        ItemStack mainHandItem = data.get(EquipmentSlot.MAINHAND);
        ItemStack offhandItem = data.get(EquipmentSlot.OFFHAND);

        if(mainHandItem != null && mainHandItem.getItem() instanceof ReachWeaponItem reachWeaponItem && reachWeaponItem.type.twoHanded){
            entity.setItemSlot(EquipmentSlot.MAINHAND, mainHandItem);
            entity.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(Items.AIR));
        } else if(offhandItem != null && offhandItem.getItem() instanceof ReachWeaponItem reachWeaponItem && reachWeaponItem.type.twoHanded){
            entity.setItemSlot(EquipmentSlot.MAINHAND, offhandItem);
            entity.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(Items.AIR));
        } else {
            entity.setItemSlot(EquipmentSlot.MAINHAND, mainHandItem);
            entity.setItemSlot(EquipmentSlot.OFFHAND, offhandItem);
        }
    }
}
