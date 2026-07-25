package net.jukoz.me.network.packets;

import net.jukoz.me.network.contexts.ServerPacketContext;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public abstract class ClientToServerPacket<T extends ClientToServerPacket<T>> implements CustomPacketPayload {
    @Override
    public abstract CustomPacketPayload.Type<T> type();


    public abstract StreamCodec<RegistryFriendlyByteBuf, T> streamCodec();
    public abstract void process(ServerPacketContext context);
}