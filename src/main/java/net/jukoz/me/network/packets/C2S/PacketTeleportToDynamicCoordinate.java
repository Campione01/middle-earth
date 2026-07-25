package net.jukoz.me.network.packets.C2S;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.network.contexts.ServerPacketContext;
import net.jukoz.me.network.packets.ClientToServerPacket;
import net.jukoz.me.world.chunkgen.map.MiddleEarthHeightMap;
import net.jukoz.me.world.dimension.ModDimensions;
import net.jukoz.me.world.map.MiddleEarthMapUtils;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector2d;

public class PacketTeleportToDynamicCoordinate extends ClientToServerPacket<PacketTeleportToDynamicCoordinate> {
    public static final CustomPacketPayload.Type<PacketTeleportToDynamicCoordinate> ID = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "packet_teleport_dynamic_spawn"));
    public static final StreamCodec<RegistryFriendlyByteBuf, PacketTeleportToDynamicCoordinate> CODEC = StreamCodec.composite(
            ByteBufCodecs.DOUBLE, p -> p.xCoordinate,
            ByteBufCodecs.DOUBLE, p -> p.zCoordinate,
            ByteBufCodecs.BOOL, p -> p.welcomeNeeded,
            PacketTeleportToDynamicCoordinate::new
    );
    private final double xCoordinate;
    private final double zCoordinate;
    private final boolean welcomeNeeded;

    public PacketTeleportToDynamicCoordinate(double xCoordinate, double zCoordinate, boolean welcomeNeeded){
        this.xCoordinate = xCoordinate;
        this.zCoordinate = zCoordinate;
        this.welcomeNeeded = welcomeNeeded;
    }
    @Override
    public Type<PacketTeleportToDynamicCoordinate> type() {
        return ID;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, PacketTeleportToDynamicCoordinate> streamCodec() {
        return CODEC;
    }

    @Override
    public void process(ServerPacketContext context) {
        context.player().getServer().execute(() -> {
            Vector2d worldCoordinate = MiddleEarthMapUtils.getInstance().getWorldCoordinateFromInitialMap(xCoordinate, zCoordinate);
            MinecraftServer server = context.player().getServer();
            Vec3 coordinates = new Vec3(worldCoordinate.x, ModDimensions.getDimensionHeight((int)worldCoordinate.x, (int)worldCoordinate.y).y, worldCoordinate.y);
            ModDimensions.teleportPlayerToMe(context.player(), coordinates, true, welcomeNeeded);
        });
    }
}
