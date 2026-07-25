package net.jukoz.me.network.packets.C2S;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.network.contexts.ServerPacketContext;
import net.jukoz.me.network.packets.ClientToServerPacket;
import net.jukoz.me.resources.StateSaverAndLoader;
import net.jukoz.me.resources.persistent_datas.PlayerData;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public class PacketSetSpawnData extends ClientToServerPacket<PacketSetSpawnData>
{
    public static final CustomPacketPayload.Type<PacketSetSpawnData> ID = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "packet_spawn_data"));

    public static final StreamCodec<RegistryFriendlyByteBuf, PacketSetSpawnData> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, p -> p.overworldX,
            ByteBufCodecs.INT, p -> p.overworldY,
            ByteBufCodecs.INT, p -> p.overworldZ,
            PacketSetSpawnData::new
    );

    private final int overworldX;
    private final int overworldY;
    private final int overworldZ;
    public PacketSetSpawnData(int overworldX, int overworldY, int overworldZ){
        this.overworldX = overworldX;
        this.overworldY = overworldY;
        this.overworldZ = overworldZ;
    }

    @Override
    public Type<PacketSetSpawnData> type() {
        return ID;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, PacketSetSpawnData> streamCodec() {
        return CODEC;
    }

    @Override
    public void process(ServerPacketContext context) {
        try{
            context.player().getServer().execute(() -> {

                MinecraftServer server = context.player().server;
                ServerPlayer player = server.getPlayerList().getPlayer(context.player().getUUID());

                PlayerData playerState = StateSaverAndLoader.getPlayerState(player);

                BlockPos overworldSpawnBlockpos = new BlockPos(overworldX, overworldY, overworldZ);
                playerState.setOverworldSpawn(overworldSpawnBlockpos);
            });
        } catch (Exception e){
            LoggerUtil.logError("SpawnDataPacket::Apply - Tried applying the spawn data packet",e);
        }
    }

    @Override
    public String toString() {
        return "Overworld="+overworldX+","+overworldY+","+overworldZ+";";
    }
}