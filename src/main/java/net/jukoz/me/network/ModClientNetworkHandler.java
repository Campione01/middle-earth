package net.jukoz.me.network;

import net.jukoz.me.compat.neoforge.api.client.networking.v1.ClientPlayNetworking;
import net.jukoz.me.client.screens.ReturnConfirmationScreen;
import net.jukoz.me.client.screens.faction_selection.FactionSelectionScreen;
import net.jukoz.me.network.connections.IConnectionToServer;
import net.jukoz.me.network.contexts.ClientPacketContext;
import net.jukoz.me.network.handlers.OnboardingScreenHandler;
import net.jukoz.me.network.packets.S2C.PacketForceOnboardingScreen;
import net.jukoz.me.network.packets.S2C.PacketOnboardingResult;
import net.jukoz.me.network.packets.ServerToClientPacket;
import net.jukoz.me.world.dimension.ModDimensions;
import net.minecraft.client.Minecraft;

import java.util.function.BiConsumer;

public class ModClientNetworkHandler {
    public static void register(IConnectionToServer connection) {
        // Application [CLIENT SIDE]
        ClientPlayNetworking.registerGlobalReceiver(PacketForceOnboardingScreen.ID, wrapClientHandler(connection, ModClientNetworkHandler::handleForceOnboardingScreen));
        ClientPlayNetworking.registerGlobalReceiver(PacketOnboardingResult.ID, wrapClientHandler(connection, ModClientNetworkHandler::handleOnboardingResult));

    }

    private static void handleForceOnboardingScreen(PacketForceOnboardingScreen packet, ClientPacketContext context) {
        float delay = packet.delayOnTeleportationConfirm();
        if(context.player().hasInfiniteMaterials())
            delay = 0;
        Minecraft client = Minecraft.getInstance();
        client.setScreen(new FactionSelectionScreen(delay));
    }

    private static void handleOnboardingResult(PacketOnboardingResult packet, ClientPacketContext context) {
        float delay = packet.delayOnTeleportationConfirm();
        if(context.player().hasInfiniteMaterials())
            delay = 0;
        if(ModDimensions.isInMiddleEarth(context.player().level())){
            if(!packet.canReturnToOverworld()){
                return;
            }
            Minecraft client = Minecraft.getInstance();
            client.setScreen(new ReturnConfirmationScreen(delay));
        } else if(ModDimensions.isInOverworld(context.player().level())){
            OnboardingScreenHandler.handle(context, packet.havePlayerData(), delay);
        }
    }

    private static <T extends ServerToClientPacket<T>> ClientPlayNetworking.PlayPayloadHandler<T> wrapClientHandler(IConnectionToServer connection, BiConsumer<T, ClientPacketContext> consumer) {
        return (t, payloadContext) -> {
            var clientPacketContext = new ClientPacketContext(payloadContext.player(), connection);
            consumer.accept(t, clientPacketContext);
        };
    }
}
