package net.jukoz.me.network.packets.S2C;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.network.packets.ServerToClientPacket;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public class PacketOnboardingResult extends ServerToClientPacket<PacketOnboardingResult> {
    public static final CustomPacketPayload.Type<PacketOnboardingResult> ID = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "packet_onboarding_result"));
    public static final StreamCodec<RegistryFriendlyByteBuf, PacketOnboardingResult> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, p -> p.havePlayerData,
            ByteBufCodecs.BOOL, p -> p.canChangeFaction,
            ByteBufCodecs.BOOL, p -> p.canReturnToOverworld,
            ByteBufCodecs.FLOAT, p -> p.delayOnTeleportationConfirm,
            PacketOnboardingResult::new
    );

    private final boolean havePlayerData;
    private final boolean canChangeFaction;
    private final boolean canReturnToOverworld;
    private final float delayOnTeleportationConfirm;

    public PacketOnboardingResult(boolean havePlayerData, boolean canChangeFaction, boolean canReturnToOverworld, float delayOnTeleportationConfirm) {
        this.havePlayerData = havePlayerData;
        this.canChangeFaction = canChangeFaction;
        this.canReturnToOverworld = canReturnToOverworld;
        this.delayOnTeleportationConfirm = delayOnTeleportationConfirm;
    }

    @Override
    public Type<PacketOnboardingResult> type() {
        return ID;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, PacketOnboardingResult> streamCodec() {
        return CODEC;
    }

    public boolean havePlayerData() {
        return havePlayerData;
    }

    public boolean canReturnToOverworld() {
        return canReturnToOverworld;
    }

    public float delayOnTeleportationConfirm() {
        return delayOnTeleportationConfirm;
    }
}
