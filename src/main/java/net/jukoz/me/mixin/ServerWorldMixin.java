package net.jukoz.me.mixin;

import net.jukoz.me.world.dimension.ModDimensions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(ServerLevel.class)
public class ServerWorldMixin {
    @Shadow @Final
    private MinecraftServer server;

    @Inject(method = "tickChunk", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/server/level/ServerLevel;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"), cancellable = true)
    private void tickChunk(LevelChunk chunk, int randomTickSpeed, CallbackInfo ci) {
        if(ModDimensions.isInMiddleEarth(chunk.getLevel())) {
            ci.cancel();
        }
    }

    // Reason of addition
    // MC-188578 - Sleeping in a bed in a custom dimension doesn't set time to day
    // Link : https://bugs.mojang.com/browse/MC-188578
    @Inject(method = "wakeUpAllPlayers", at = @At("HEAD"))
    private void wakeSleepingPlayers(CallbackInfo ci) {
        // The math is like vanilla (ServerWorld lines ~[310-319])
        if (server.getGameRules().getBoolean(GameRules.RULE_DAYLIGHT)) {
            long currentTimeOfDay = server.getLevel(Level.OVERWORLD).getDayTime() + 24000L;
            server.getLevel(Level.OVERWORLD).setDayTime(currentTimeOfDay - currentTimeOfDay % 24000L);
        }
    }
}
