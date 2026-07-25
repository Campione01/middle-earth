package net.jukoz.me.network.packets.C2S;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.network.contexts.ServerPacketContext;
import net.jukoz.me.network.packets.ClientToServerPacket;
import net.jukoz.me.resources.StateSaverAndLoader;
import net.jukoz.me.resources.persistent_datas.PlayerData;
import net.jukoz.me.utils.LoggerUtil;
import net.jukoz.me.world.dimension.ModDimensions;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;


public class PacketTeleportToCurrentSpawn extends ClientToServerPacket<PacketTeleportToCurrentSpawn> {
    public static final CustomPacketPayload.Type<PacketTeleportToCurrentSpawn> ID = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "packet_teleport_current_spawn"));
    public static final StreamCodec<RegistryFriendlyByteBuf, PacketTeleportToCurrentSpawn> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, p -> p.welcomeNeeded,
            PacketTeleportToCurrentSpawn::new
    );
    private Boolean welcomeNeeded;

    public PacketTeleportToCurrentSpawn(boolean welcomeNeeded){
        this.welcomeNeeded = welcomeNeeded;
    }
    @Override
    public Type<PacketTeleportToCurrentSpawn> type() {
        return ID;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, PacketTeleportToCurrentSpawn> streamCodec() {
        return CODEC;
    }



    @Override
    public void process(ServerPacketContext context) {
        try{
            context.player().getServer().execute(() -> {
                PlayerData data = StateSaverAndLoader.getPlayerStateReadOnly(context.player());
                if(data != null){
                    if(data.hasAffilition()){
                        Vec3 spawnCoordinates = data.getSpawnMiddleEarthCoordinate(context.player().level());
                        if(spawnCoordinates != null)
                            ModDimensions.teleportPlayerToMe(context.player(), new Vec3(spawnCoordinates.x, spawnCoordinates.y, spawnCoordinates.z), true, welcomeNeeded);
                    }
                }
            });
        } catch (Exception e){
            LoggerUtil.logError("TeleportToMeSpawnRequestPacket::Apply - Tried applying the teleport to me request packet",e);
        }
    }
}
