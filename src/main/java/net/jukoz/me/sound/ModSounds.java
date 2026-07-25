package net.jukoz.me.sound;

import net.jukoz.me.utils.NeoForgeRegistrationBridge;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {
    public static SoundEvent BELLOWS_PUSH = registerSoundEvent("bellows_push");
    public static SoundEvent NAZGUL_FADE = registerSoundEvent("nazgul_fade");
    public static SoundEvent NAZGUL_SCREAM = registerSoundEvent("nazgul_scream");
    public static SoundEvent PIPE_EXHALE = registerSoundEvent("pipe_exhale");
    public static SoundEvent PIPE_REFILL = registerSoundEvent("pipe_refill");
    public static SoundEvent PIPE_IGNITE = registerSoundEvent( "pipe_ignite");

    private static SoundEvent registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, name);
        return NeoForgeRegistrationBridge.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static void registerModSounds() {
        LoggerUtil.logDebugMsg("Registering Mod SoundEvents for " + MiddleEarth.MOD_ID);
    }
}
