package net.jukoz.me.mixin.client;

import net.jukoz.me.entity.barrow_wights.BarrowWightEntity;
import net.jukoz.me.statusEffects.HallucinationStatusEffect;
import net.jukoz.me.statusEffects.ModStatusEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public class MouseMixin {
    LivingEntity lookAt;
    int id;

    @Inject(method ="handleAccumulatedMovement", at = @At("HEAD"), cancellable = true)
    private void injected(CallbackInfo cir) {
        Player player = Minecraft.getInstance().player;
        if ( player != null && player.hasEffect(ModStatusEffects.HALLUCINATION)) {
            if (lookAt != null && !lookAt.isDeadOrDying() && id != -1) {
                lookAt = (LivingEntity) player.level().getEntity(id);

                if(lookAt == null){
                    ((HallucinationStatusEffect)player.getActiveEffectsMap().get(ModStatusEffects.HALLUCINATION).getEffect()).stop(player);
                    player.sendSystemMessage(Component.literal("is dead _ " + player.getActiveEffectsMap().get(ModStatusEffects.HALLUCINATION).getDuration()));
                    id = -1;
                    return;
                }

                double dX = lookAt.getX() - player.getX();
                double dY = lookAt.getY() - player.getY();
                double dZ = lookAt.getZ() - player.getZ();

                double g = Math.sqrt(dX * dX + dZ * dZ);

                float destPitch = Mth.wrapDegrees((float) (-(Mth.atan2(dY, g) * 57.2957763671875)));
                float destYaw = Mth.wrapDegrees((float) (Mth.atan2(dZ, dX) * 57.2957763671875) - 90.0f);

                // lerpFactor use :
                // 0 > x < 1 (Needs to be between 0 and 1)
                // Close to 0 means no fluidity, close to 1 means too much fluidity and lot of control for the player
                float lerpFactor = 0.85f;

                destPitch = Mth.lerp(lerpFactor, destPitch, player.getXRot());
                destYaw = Mth.lerp(lerpFactor, destYaw, player.getYRot());

                player.setXRot(destPitch);
                player.setYRot(destYaw);
                player.setYRot(destYaw);
                player.setYHeadRot(player.getYRot());
                player.xRotO = player.getXRot();
                player.yRotO = player.getYRot();
            } else if(id == -1){

                this.lookAt = player.level().getNearestEntity(BarrowWightEntity.class, TargetingConditions.forNonCombat(), null, player.getX(), player.getY(), player.getZ(), player.getBoundingBox().inflate(28));
                if(lookAt != null)
                    this.id = lookAt.getId();
                cir.cancel();
            }
        } else {
            lookAt = null;
            if(id != -1) id = -1;
        }
    }
}
