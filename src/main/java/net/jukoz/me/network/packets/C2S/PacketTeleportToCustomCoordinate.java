package net.jukoz.me.network.packets.C2S;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.network.contexts.ServerPacketContext;
import net.jukoz.me.network.packets.ClientToServerPacket;
import net.jukoz.me.world.dimension.ModDimensions;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public class PacketTeleportToCustomCoordinate extends ClientToServerPacket<PacketTeleportToCustomCoordinate> {
    public static final Type<PacketTeleportToCustomCoordinate> ID = new Type<>(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "packet_teleport_custom_spawn"));
    public static final StreamCodec<RegistryFriendlyByteBuf, PacketTeleportToCustomCoordinate> CODEC = StreamCodec.composite(
            ByteBufCodecs.DOUBLE, p -> p.xCoordinate,
            ByteBufCodecs.DOUBLE, p -> p.yCoordinate,
            ByteBufCodecs.DOUBLE, p -> p.zCoordinate,
            ByteBufCodecs.BOOL, p -> p.welcomeNeeded,
            PacketTeleportToCustomCoordinate::new
    );
    private final double xCoordinate;
    private final double yCoordinate;
    private final double zCoordinate;
    private final boolean welcomeNeeded;

    public PacketTeleportToCustomCoordinate(Double xCoordinate, Double yCoordinate, Double zCoordinate, boolean welcomeNeeded){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.zCoordinate = zCoordinate;
        this.welcomeNeeded = welcomeNeeded;
    }
    @Override
    public Type<PacketTeleportToCustomCoordinate> type() {
        return ID;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, PacketTeleportToCustomCoordinate> streamCodec() {
        return CODEC;
    }

    @Override
    public void process(ServerPacketContext context) {
        context.player().getServer().execute(() -> {
            Vec3 coordinates = new Vec3(xCoordinate, yCoordinate, zCoordinate);
            ModDimensions.teleportPlayerToMe(context.player(), coordinates, true, welcomeNeeded);
        });
    }
}
