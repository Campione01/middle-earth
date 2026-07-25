package net.jukoz.me.particles.custom;

import net.jukoz.me.compat.neoforge.dist.EnvType;
import net.jukoz.me.compat.neoforge.dist.Environment;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

/**
 * Represents a custom particle effect that simulates a ring of smoke.
 * This particle has gravity, velocity, and a limited lifespan.
 */
public class RingOfSmokeParticle extends TextureSheetParticle {
    private final SpriteSet spriteProvider;
    private final double strength;

    public RingOfSmokeParticle(ClientLevel clientWorld, double x, double y, double z, double vel_x, double vel_y, double vel_z, SpriteSet spriteProvider) {
        super(clientWorld, x, y, z, 0,0,0);
        this.spriteProvider = spriteProvider;
        this.strength =  (Math.abs(vel_x) + Math.abs(vel_y) + Math.abs(vel_z));

        this.xd = vel_x;
        this.yd = vel_y;
        this.zd = vel_z;

        setSprite(spriteProvider.get(0, this.lifetime));

        this.lifetime = (int)((600) * this.strength);
        this.gravity = 0;
        this.hasPhysics = true;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.removed) {
            setSpriteFromAge(spriteProvider);
            float ageRatio = (float)this.age / this.lifetime;
            this.setAlpha(1.0F - ageRatio);
            //this.checkCollisionWithPlayer();
        }
    }

    private void checkCollisionWithPlayer() {
        AABB particleBox = this.getBoundingBox();
        for (Player player : this.level.players()) {
            if (player.getBoundingBox().intersects(particleBox)) {
                onCollisionWithPlayer(player);
            }
        }
    }

    private void onCollisionWithPlayer(Player player) {
        // Handle collision logic here
        //LoggerUtil.logDebugMsg("Collision detected with player: " + player.getName().getString());
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public float getQuadSize(float tickDelta) {
        this.quadSize = (float) this.age/this.lifetime + 0.3F;
        return Math.min(this.quadSize, 0.8F);
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteProvider;

        /**
         * Constructs a new Factory.
         *
         * @param spriteProvider The sprite provider for the particle.
         */
        public Factory(SpriteSet spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        /**
         * Creates a new RingOfSmokeParticle.
         *
         * @param simpleParticleType The particle type.
         * @param clientWorld The client world where the particle exists.
         * @param x The initial x-coordinate of the particle.
         * @param y The initial y-coordinate of the particle.
         * @param z The initial z-coordinate of the particle.
         * @param vel_x The initial x-velocity of the particle.
         * @param vel_y The initial y-velocity of the particle.
         * @param vel_z The initial z-velocity of the particle.
         * @return A new RingOfSmokeParticle instance.
         */
        public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientWorld, double x, double y, double z, double vel_x, double vel_y, double vel_z) {
            return new RingOfSmokeParticle(clientWorld, x, y, z, vel_x, vel_y, vel_z, spriteProvider);
        }
    }
}