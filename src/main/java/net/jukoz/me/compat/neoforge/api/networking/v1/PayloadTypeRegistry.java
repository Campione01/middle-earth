package net.jukoz.me.compat.neoforge.api.networking.v1;

import net.jukoz.me.compat.neoforge.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import java.util.ArrayList;
import java.util.List;

public final class PayloadTypeRegistry {
    private static final PayloadTypeRegistry S2C = new PayloadTypeRegistry();
    private static final PayloadTypeRegistry C2S = new PayloadTypeRegistry();
    private static final List<PayloadEntry<?>> PLAY_S2C = new ArrayList<>();
    private static final List<PayloadEntry<?>> PLAY_C2S = new ArrayList<>();
    private final List<PayloadEntry<?>> entries;

    private PayloadTypeRegistry() {
        this.entries = new ArrayList<>();
    }

    private PayloadTypeRegistry(List<PayloadEntry<?>> entries) {
        this.entries = entries;
    }

    public static PayloadTypeRegistry playS2C() {
        return new PayloadTypeRegistry(PLAY_S2C);
    }

    public static PayloadTypeRegistry playC2S() {
        return new PayloadTypeRegistry(PLAY_C2S);
    }

    public <T extends CustomPacketPayload> void register(CustomPacketPayload.Type<T> id, StreamCodec<?, T> codec) {
        entries.add(new PayloadEntry<>(id, codec));
    }

    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        PLAY_S2C.forEach(entry -> registerClientbound(registrar, entry));
        PLAY_C2S.forEach(entry -> registerServerbound(registrar, entry));
    }

    private static <T extends CustomPacketPayload> void registerClientbound(PayloadRegistrar registrar, PayloadEntry<T> entry) {
        registrar.playToClient(entry.type(), entry.registryCodec(), PayloadTypeRegistry::handleClientbound);
    }

    private static <T extends CustomPacketPayload> void registerServerbound(PayloadRegistrar registrar, PayloadEntry<T> entry) {
        registrar.playToServer(entry.type(), entry.registryCodec(), ServerPlayNetworking::dispatch);
    }

    private static <T extends CustomPacketPayload> void handleClientbound(T payload, IPayloadContext context) {
        ClientPlayNetworking.dispatch(payload, context);
    }

    private record PayloadEntry<T extends CustomPacketPayload>(CustomPacketPayload.Type<T> type, StreamCodec<?, T> codec) {
        @SuppressWarnings("unchecked")
        private StreamCodec<? super RegistryFriendlyByteBuf, T> registryCodec() {
            return (StreamCodec<? super RegistryFriendlyByteBuf, T>) codec;
        }
    }
}
