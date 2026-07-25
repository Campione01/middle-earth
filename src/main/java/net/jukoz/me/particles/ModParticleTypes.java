package net.jukoz.me.particles;

import net.jukoz.me.utils.NeoForgeRegistrationBridge;

import net.jukoz.me.compat.neoforge.api.particle.v1.NeoForgeParticleTypes;
import net.jukoz.me.MiddleEarth;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class ModParticleTypes {

    public static final SimpleParticleType MALLORN_LEAVES_PARTICLE = NeoForgeParticleTypes.simple();
    public static final SimpleParticleType MIRKWOOD_LEAVES_PARTICLE = NeoForgeParticleTypes.simple();
    public static final SimpleParticleType ANVIL_SPARK_PARTICLE = NeoForgeParticleTypes.simple();
    public static final SimpleParticleType RING_OF_SMOKE = NeoForgeParticleTypes.simple();

    public static void registerParticleTypes(){
        NeoForgeRegistrationBridge.register(BuiltInRegistries.PARTICLE_TYPE, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "mallorn_leaves_particles"), MALLORN_LEAVES_PARTICLE);
        NeoForgeRegistrationBridge.register(BuiltInRegistries.PARTICLE_TYPE, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "mirkwood_leaves_particles"), MIRKWOOD_LEAVES_PARTICLE);
        NeoForgeRegistrationBridge.register(BuiltInRegistries.PARTICLE_TYPE, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "anvil_spark_particles"), ANVIL_SPARK_PARTICLE);
        NeoForgeRegistrationBridge.register(BuiltInRegistries.PARTICLE_TYPE, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "ring_of_smoke_particles"), RING_OF_SMOKE);
    }

}
