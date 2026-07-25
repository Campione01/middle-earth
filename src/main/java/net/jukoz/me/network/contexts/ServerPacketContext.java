package net.jukoz.me.network.contexts;

import net.jukoz.me.network.connections.IConnectionToClient;
import net.minecraft.server.level.ServerPlayer;

public record ServerPacketContext(ServerPlayer player, IConnectionToClient connection) {
}
