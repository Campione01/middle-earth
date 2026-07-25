package net.jukoz.me.network.packets.C2S;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.special.forge.ForgeBlockEntity;
import net.jukoz.me.network.contexts.ServerPacketContext;
import net.jukoz.me.network.packets.ClientToServerPacket;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public class ForgeOutputPacket extends ClientToServerPacket<ForgeOutputPacket> {
    public static final CustomPacketPayload.Type<ForgeOutputPacket> ID = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "forge_output_packet"));
    public static final StreamCodec<RegistryFriendlyByteBuf, ForgeOutputPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, p -> p.amount,
            ByteBufCodecs.DOUBLE, p -> p.x,
            ByteBufCodecs.DOUBLE, p -> p.y,
            ByteBufCodecs.DOUBLE, p -> p.z,
            ForgeOutputPacket::new
    );

    public int getAmount() {
        return amount;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    private final int amount;
    private final double x;
    private final double y;
    private final double z;

    public ForgeOutputPacket(int amount, double x, double y, double z) {
        this.amount = amount;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public Type<ForgeOutputPacket> type() {
        return ID;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, ForgeOutputPacket> streamCodec() {
        return CODEC;
    }

    @Override
    public void process(ServerPacketContext context) {
        try{
            context.player().getServer().execute(() -> {
                Vec3 coordinates = new Vec3(x, y, z);
                ForgeBlockEntity.outputItemStack(amount, coordinates, context.player());
            });
        }catch (Exception e){
            LoggerUtil.logError("PacketForgeOutput error: ", e);
        }
    }
}
