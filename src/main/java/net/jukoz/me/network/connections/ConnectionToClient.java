package net.jukoz.me.network.connections;

import net.jukoz.me.compat.neoforge.api.networking.v1.ServerPlayNetworking;
import net.jukoz.me.network.packets.ServerToClientPacket;
import net.minecraft.server.level.ServerPlayer;

public class ConnectionToClient implements IConnectionToClient{
    @Override
    public <T extends ServerToClientPacket<T>> void sendPacketToClient(T packet, ServerPlayer player) {
        ServerPlayNetworking.send(player, packet);
    }
}
