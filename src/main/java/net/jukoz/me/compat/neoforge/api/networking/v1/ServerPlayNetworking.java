package net.jukoz.me.compat.neoforge.api.networking.v1;

import net.minecraft.world.entity.player.Player;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.LinkedHashMap;
import java.util.Map;

public final class ServerPlayNetworking {
    private static final Map<CustomPacketPayload.Type<?>, PlayPayloadHandler<?>> RECEIVERS = new LinkedHashMap<>();

    private ServerPlayNetworking() {
    }

    public static <T extends CustomPacketPayload> void registerGlobalReceiver(CustomPacketPayload.Type<T> id,
                                                                              PlayPayloadHandler<T> handler) {
        RECEIVERS.put(id, handler);
    }

    public static void send(ServerPlayer player, CustomPacketPayload payload) {
        PacketDistributor.sendToPlayer(player, payload);
    }

    @SuppressWarnings("unchecked")
    public static <T extends CustomPacketPayload> void dispatch(T payload, IPayloadContext context) {
        PlayPayloadHandler<T> handler = (PlayPayloadHandler<T>) RECEIVERS.get(payload.type());
        Player player = context.player();
        if (handler != null && player instanceof ServerPlayer serverPlayer) {
            context.enqueueWork(() -> handler.receive(payload, new Context(serverPlayer)));
        }
    }

    @FunctionalInterface
    public interface PlayPayloadHandler<T extends CustomPacketPayload> {
        void receive(T payload, Context context);
    }

    public static final class Context {
        private final ServerPlayer player;

        public Context(ServerPlayer player) {
            this.player = player;
        }

        public ServerPlayer player() {
            return player;
        }

        public MinecraftServer server() {
            return player.server;
        }

        public void execute(Runnable runnable) {
            server().execute(runnable);
        }
    }
}
