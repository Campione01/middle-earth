package net.jukoz.me.network.packets.S2C;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.network.packets.ServerToClientPacket;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public class PacketForceOnboardingScreen extends ServerToClientPacket<PacketForceOnboardingScreen> {
    public static final CustomPacketPayload.Type<PacketForceOnboardingScreen> ID = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "packet_force_onboarding_screen"));
    public static final StreamCodec<RegistryFriendlyByteBuf, PacketForceOnboardingScreen> CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT, p -> p.delayOnTeleportationConfirm,
            PacketForceOnboardingScreen::new
    );
    private final float delayOnTeleportationConfirm;

    public PacketForceOnboardingScreen(float delayOnTeleportationConfirm) {
        this.delayOnTeleportationConfirm = delayOnTeleportationConfirm;
    }

    @Override
    public CustomPacketPayload.Type<PacketForceOnboardingScreen> type() {
        return ID;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, PacketForceOnboardingScreen> streamCodec() {
        return CODEC;
    }

    public float delayOnTeleportationConfirm() {
        return delayOnTeleportationConfirm;
    }
}
