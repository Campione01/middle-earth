package net.jukoz.me.network.contexts;

import net.jukoz.me.network.connections.IConnectionToServer;
import net.minecraft.world.entity.player.Player;

public record ClientPacketContext(Player player, IConnectionToServer connection) {

}