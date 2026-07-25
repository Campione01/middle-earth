package net.jukoz.me.utils;

import net.minecraft.nbt.CompoundTag;

public class PlayerMovementData {
    public static final String KEY = "player_afk_data";
    public static final int MAX_AFK_TIME = 100;

    public static int addAFKTime(IEntityDataSaver player, int amount) {
        CompoundTag nbt = player.getPersistentData();
        int movement = nbt.getInt(KEY);

        int newMovement = Math.min(MAX_AFK_TIME, movement + amount);
        newMovement = Math.max(0, newMovement);

        if (newMovement != movement) {
            nbt.putInt(KEY, newMovement);
        }
        return newMovement;
    }

    public static int readAFK(IEntityDataSaver player) {
        CompoundTag nbt = player.getPersistentData();
        int movement = nbt.getInt(KEY);

        return movement;
    }

    public static void resetAFK(IEntityDataSaver player){
        CompoundTag nbt = player.getPersistentData();
        if (nbt.getInt(KEY) != 0) {
            nbt.putInt(KEY, 0);
        }
    }
}
