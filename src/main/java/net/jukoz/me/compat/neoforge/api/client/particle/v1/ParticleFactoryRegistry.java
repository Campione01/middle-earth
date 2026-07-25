package net.jukoz.me.compat.neoforge.api.client.particle.v1;

import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

import java.util.LinkedHashMap;
import java.util.Map;

public final class ParticleFactoryRegistry {
    private static final ParticleFactoryRegistry INSTANCE = new ParticleFactoryRegistry();
    private static final Map<ParticleType<?>, ParticleEngine.SpriteParticleRegistration<?>> PROVIDERS = new LinkedHashMap<>();

    private ParticleFactoryRegistry() {
    }

    public static ParticleFactoryRegistry getInstance() {
        return INSTANCE;
    }

    public <T extends ParticleOptions> void register(ParticleType<T> type, ParticleEngine.SpriteParticleRegistration<T> factory) {
        PROVIDERS.put(type, factory);
    }

    public static void registerProviders(RegisterParticleProvidersEvent event) {
        PROVIDERS.forEach((type, factory) -> registerProvider(event, type, factory));
    }

    @SuppressWarnings("unchecked")
    private static <T extends ParticleOptions> void registerProvider(RegisterParticleProvidersEvent event,
                                                                     ParticleType<?> type,
                                                                     ParticleEngine.SpriteParticleRegistration<?> factory) {
        event.registerSpriteSet((ParticleType<T>) type, (ParticleEngine.SpriteParticleRegistration<T>) factory);
    }
}
