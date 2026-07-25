package net.jukoz.me.compat.neoforge.api.particle.v1;

import net.minecraft.core.particles.SimpleParticleType;

public final class NeoForgeParticleTypes {
    private NeoForgeParticleTypes() {
    }

    public static SimpleParticleType simple() {
        return new SimpleParticleType(false);
    }
}
