package net.jukoz.me.mixin;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.utils.IEntityDataSaver;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class EntityDataSaverMixin implements IEntityDataSaver {
    private final String KEY = MiddleEarth.MOD_ID;
    private CompoundTag persistentData;

    @Override
    public CompoundTag getPersistentData() {
        if(this.persistentData == null) {
            this.persistentData = new CompoundTag();
        }
        return persistentData;
    }

    @Inject(method = "addAdditionalSaveData", at = @At("HEAD"))
    protected void writeCustomDataToNbt(CompoundTag nbt, CallbackInfo ci) {
        if(persistentData != null) {
            nbt.put(KEY, persistentData);
        }
    }

    @Inject(method = "readAdditionalSaveData", at = @At("HEAD"))
    protected void readCustomDataToNbt(CompoundTag nbt, CallbackInfo ci) {
        if(nbt.contains(KEY, 10)) {
            persistentData = nbt.getCompound(KEY);
        }
    }
}
