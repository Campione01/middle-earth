package net.jukoz.me.mixin;

import com.mojang.authlib.GameProfile;
import net.jukoz.me.resources.StateSaverAndLoader;
import net.jukoz.me.resources.persistent_datas.AffiliationData;
import net.jukoz.me.resources.persistent_datas.PlayerData;
import net.jukoz.me.utils.LoggerUtil;
import net.jukoz.me.world.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.logging.Logger;

@Mixin(ServerPlayer.class)
public class ServerPlayerEntityMixin extends Player {
    @Shadow public MinecraftServer server;
    @Shadow public ServerPlayerGameMode gameMode;

    public ServerPlayerEntityMixin(Level world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Inject(method = "findRespawnPositionAndUseSpawnBlock", at = @At(value = "RETURN", ordinal = 1), cancellable = true)
    public void getRespawnTargetWithBrokenBed(boolean alive, DimensionTransition.PostDimensionTransition postDimensionTransition, CallbackInfoReturnable<DimensionTransition> cir) {
        if(ModDimensions.isInMiddleEarth(this.level())){
            if (tryToOverrideSpawn(postDimensionTransition, cir)) return;
            resetSpawn(postDimensionTransition, cir);
            return;
        }
        cir.setReturnValue(DimensionTransition.missingRespawnBlock(this.server.overworld(), this, postDimensionTransition));
    }

    @Inject(method = "findRespawnPositionAndUseSpawnBlock", at = @At(value = "RETURN", ordinal = 2), cancellable = true)
    public void getRespawnTargetNatural(boolean alive, DimensionTransition.PostDimensionTransition postDimensionTransition, CallbackInfoReturnable<DimensionTransition> cir) {
        if(ModDimensions.isInMiddleEarth(this.level())){
            if (tryToOverrideSpawn(postDimensionTransition, cir)) return;
            resetSpawn(postDimensionTransition, cir);
            return;
        }
        cir.setReturnValue(DimensionTransition.missingRespawnBlock(this.server.overworld(), this, postDimensionTransition));
    }

    @Unique
    private void resetSpawn(DimensionTransition.PostDimensionTransition postDimensionTransition, CallbackInfoReturnable<DimensionTransition> cir){
        if(this.getServer() == null) return;

        PlayerList manager = this.getServer().getPlayerList();
        ServerPlayer foundPlayer = manager.getPlayer(this.getUUID());
        if(this.getServer() == null || foundPlayer == null) return;
        foundPlayer.setRespawnPosition(Level.OVERWORLD, foundPlayer.getServer().overworld().getSharedSpawnPos(), foundPlayer.getServer().overworld().getSharedSpawnAngle(), true, true);

        cir.setReturnValue(new DimensionTransition(this.server.overworld(), this, postDimensionTransition));
    }

    @Unique
    private boolean tryToOverrideSpawn(DimensionTransition.PostDimensionTransition postDimensionTransition, CallbackInfoReturnable<DimensionTransition> cir) {
        MinecraftServer server = this.getServer();

        if(server == null) return false;
        PlayerList manager = server.getPlayerList();
        ServerPlayer foundPlayer = manager.getPlayer(this.getUUID());

        if(foundPlayer == null) return false;
        if(ModDimensions.isInMiddleEarth(this.level())) {
            PlayerData data = StateSaverAndLoader.getPlayerStateReadOnly(foundPlayer);
            if(data != null && data.hasAffilition()){
                Vec3 spawnCoordinates = data.getSpawnMiddleEarthCoordinate(level());
                if(spawnCoordinates != null){
                    ServerLevel MEWorld = this.server.getLevel(ModDimensions.ME_WORLD_KEY);
                    if(MEWorld != null){
                        Vec3 coordinates = ModDimensions.getSafeMiddleEarthTeleportTarget(spawnCoordinates);
                        BlockPos targetPos = BlockPos.containing(coordinates);
                        MEWorld.getChunk(targetPos);
                        foundPlayer.setRespawnPosition(ModDimensions.ME_WORLD_KEY, targetPos,0,true, true);
                        cir.setReturnValue(new DimensionTransition(MEWorld, coordinates, net.minecraft.world.phys.Vec3.ZERO, 0, 0, false,postDimensionTransition));
                        return true;
                    }
                }
            }
        }
        foundPlayer.setRespawnPosition(Level.OVERWORLD, server.overworld().getSharedSpawnPos(), server.overworld().getSharedSpawnAngle(), true, true);
        cir.setReturnValue(new DimensionTransition(server.overworld(), server.overworld().getSharedSpawnPos().getCenter(), net.minecraft.world.phys.Vec3.ZERO, 0, 0, false,postDimensionTransition));
        return false;
    }

    @Override
    public boolean isSpectator() {
        return this.gameMode.getGameModeForPlayer() == GameType.SPECTATOR;
    }

    @Override
    public boolean isCreative() {
        return this.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
    }
}
