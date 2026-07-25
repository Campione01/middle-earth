package net.jukoz.me.resources.datas.npcs.data;

import net.jukoz.me.compat.neoforge.api.util.NbtType;
import net.jukoz.me.item.ModEquipmentItems;
import net.jukoz.me.item.ModWeaponItems;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import java.util.HashMap;
import java.util.List;

public class NpcGearData {
    public final HashMap<EquipmentSlot, NpcGearSlotData> gears;

    public NpcGearData(){
        gears = new HashMap<>();
    }
    public static NpcGearData create() {
        return new NpcGearData();
    }
    public NpcGearData(CompoundTag gearNbt) {
        this.gears = new HashMap<>();
        addSlot(gearNbt, EquipmentSlot.HEAD);
        addSlot(gearNbt, EquipmentSlot.CHEST);
        addSlot(gearNbt, EquipmentSlot.LEGS);
        addSlot(gearNbt, EquipmentSlot.FEET);
        addSlot(gearNbt, EquipmentSlot.MAINHAND);
        addSlot(gearNbt, EquipmentSlot.OFFHAND);
    }

    private void addSlot(CompoundTag gearNbt, EquipmentSlot equipmentSlot) {
        if(gearNbt.get(equipmentSlot.getSerializedName()) != null){
            CompoundTag slotNbt = gearNbt.getCompound(equipmentSlot.getSerializedName());
            this.gears.put(equipmentSlot, NpcGearSlotData.readNbt(slotNbt));
        }
    }

    public NpcGearData add(EquipmentSlot slot, NpcGearSlotData slotData){
        if(gears.containsKey(slot)) {
            LoggerUtil.logError("NpcGearData::Overwriting slotData - %s".formatted(slot.getName()));
        }
        gears.put(slot, slotData);
        return this;
    }

    public static NpcGearData Create() {
        return NpcGearData.create();
    }

    public ItemStack get(EquipmentSlot slot) {
        if(!gears.containsKey(slot))
            return new ItemStack(Items.AIR);
        return gears.get(slot).getItemStack();
    }

    public static CompoundTag createNbt(NpcGearData gearData){
        CompoundTag nbt = new CompoundTag();
        for(EquipmentSlot slot : gearData.gears.keySet()){
            nbt.put(slot.getName().toLowerCase(), NpcGearSlotData.createNbt(gearData.gears.get(slot)));
        }
        return nbt;
    }
    public static NpcGearData readNbt(CompoundTag nbt){
        return new NpcGearData(nbt);
    }
}
