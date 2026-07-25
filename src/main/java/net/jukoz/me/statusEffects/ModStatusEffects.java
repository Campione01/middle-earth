package net.jukoz.me.statusEffects;

import net.jukoz.me.utils.NeoForgeRegistrationBridge;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ModStatusEffects {
    public static final Holder<MobEffect> HALLUCINATION = register("hallucination", new HallucinationStatusEffect(MobEffectCategory.HARMFUL, 0x006666));

    private static Holder<MobEffect> register(String id, MobEffect statusEffect) {
        return NeoForgeRegistrationBridge.registerForHolder(BuiltInRegistries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, id), statusEffect);
    }

    public static void registerStatusEffects() {
        LoggerUtil.logDebugMsg("Registering Mod Status Effects for " + MiddleEarth.MOD_ID);
    }
}
