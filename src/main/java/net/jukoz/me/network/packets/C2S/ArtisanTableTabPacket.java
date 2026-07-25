package net.jukoz.me.network.packets.C2S;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.gui.artisantable.ArtisanTableScreenHandler;
import net.jukoz.me.network.contexts.ServerPacketContext;
import net.jukoz.me.network.packets.ClientToServerPacket;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class ArtisanTableTabPacket extends ClientToServerPacket<ArtisanTableTabPacket> {
    public static final Type<ArtisanTableTabPacket> ID = new Type<>(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "artisan_table_tab_packet"));
    public static final StreamCodec<RegistryFriendlyByteBuf, ArtisanTableTabPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, p -> p.shapeId,
            ByteBufCodecs.INT, p -> p.syncId,
            ArtisanTableTabPacket::new
    );

    public String getShapeId() {
        return shapeId;
    }

    public int getSyncId() {
        return syncId;
    }

    private final String shapeId;
    private final int syncId;

    public ArtisanTableTabPacket(String shapeId, int syncId) {
        this.shapeId = shapeId;
        this.syncId = syncId;
    }

    @Override
    public Type<ArtisanTableTabPacket> type() {
        return ID;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, ArtisanTableTabPacket> streamCodec() {
        return CODEC;
    }

    @Override
    public void process(ServerPacketContext context) {
        try{
            context.player().getServer().execute(() -> {
                ServerPlayer player = context.player();
                AbstractContainerMenu screenHandler = player.containerMenu;
                if (screenHandler.containerId == this.syncId && screenHandler instanceof ArtisanTableScreenHandler artisanTableScreenHandler) {
                    artisanTableScreenHandler.changeTab(shapeId);
                }
            });
        }catch (Exception e){
            LoggerUtil.logError("Artisan Table Packet error: ", e);
        }
    }
}
