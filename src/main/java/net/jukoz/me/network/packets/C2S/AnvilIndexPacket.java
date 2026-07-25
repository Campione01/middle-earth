package net.jukoz.me.network.packets.C2S;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.special.shapingAnvil.TreatedAnvilBlockEntity;
import net.jukoz.me.network.contexts.ServerPacketContext;
import net.jukoz.me.network.packets.ClientToServerPacket;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public class AnvilIndexPacket extends ClientToServerPacket<AnvilIndexPacket> {
    public static final Type<AnvilIndexPacket> ID = new Type<>(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "anvil_index_packet"));
    public static final StreamCodec<RegistryFriendlyByteBuf, AnvilIndexPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, p -> p.left,
            ByteBufCodecs.DOUBLE, p -> p.x,
            ByteBufCodecs.DOUBLE, p -> p.y,
            ByteBufCodecs.DOUBLE, p -> p.z,
            AnvilIndexPacket::new
    );

    public boolean getAmount() {
        return left;
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

    private final boolean left;
    private final double x;
    private final double y;
    private final double z;

    public AnvilIndexPacket(boolean left, double x, double y, double z) {
        this.left = left;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public Type<AnvilIndexPacket> type() {
        return ID;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, AnvilIndexPacket> streamCodec() {
        return CODEC;
    }

    @Override
    public void process(ServerPacketContext context) {
        try{
            context.player().getServer().execute(() -> {
                Vec3 coordinates = new Vec3(x, y, z);
                TreatedAnvilBlockEntity.updateIndex(left, coordinates, context.player());
            });
        }catch (Exception e){
            LoggerUtil.logError("PacketAnvilIndex error: ", e);
        }
    }
}
