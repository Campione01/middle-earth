package net.jukoz.me.compat.neoforge.api.screenhandler.v1;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;

import java.util.OptionalInt;

public interface ExtendedScreenHandlerFactory<T> extends MenuProvider {
    T getScreenOpeningData(ServerPlayer player);

    static <T> OptionalInt open(ServerPlayer player, ExtendedScreenHandlerFactory<T> factory, StreamCodec<? super RegistryFriendlyByteBuf, T> codec) {
        return player.openMenu(factory, buffer -> codec.encode(buffer, factory.getScreenOpeningData(player)));
    }
}
