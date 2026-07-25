package net.jukoz.me.compat.neoforge.api.client.networking.v1;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.LinkedHashMap;
import java.util.Map;

public final class ClientPlayNetworking {
    private static final Map<CustomPacketPayload.Type<?>, PlayPayloadHandler<?>> RECEIVERS = new LinkedHashMap<>();

    private ClientPlayNetworking() {
    }

    public static void send(CustomPacketPayload payload) {
        PacketDistributor.sendToServer(payload);
    }

    public static boolean canSend(CustomPacketPayload.Type<?> id) {
        return Minecraft.getInstance().getConnection() != null;
    }

    public static <T extends CustomPacketPayload> void registerGlobalReceiver(CustomPacketPayload.Type<T> id,
                                                                              PlayPayloadHandler<T> handler) {
        RECEIVERS.put(id, handler);
    }

    @SuppressWarnings("unchecked")
    public static <T extends CustomPacketPayload> void dispatch(T payload, IPayloadContext context) {
        PlayPayloadHandler<T> handler = (PlayPayloadHandler<T>) RECEIVERS.get(payload.type());
        if (handler != null) {
            context.enqueueWork(() -> handler.receive(payload, new Context()));
        }
    }

    @FunctionalInterface
    public interface PlayPayloadHandler<T extends CustomPacketPayload> {
        void receive(T payload, Context context);
    }

    public static final class Context {
        public Minecraft client() {
            return Minecraft.getInstance();
        }

        public LocalPlayer player() {
            return Minecraft.getInstance().player;
        }

        public void execute(Runnable runnable) {
            Minecraft.getInstance().execute(runnable);
        }
    }
}
