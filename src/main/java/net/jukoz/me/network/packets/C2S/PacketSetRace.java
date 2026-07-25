package net.jukoz.me.network.packets.C2S;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.network.contexts.ServerPacketContext;
import net.jukoz.me.network.packets.ClientToServerPacket;
import net.jukoz.me.resources.datas.factions.Faction;
import net.jukoz.me.resources.datas.factions.FactionLookup;
import net.jukoz.me.resources.datas.factions.FactionUtil;
import net.jukoz.me.resources.datas.races.RaceLookup;
import net.jukoz.me.resources.datas.races.RaceUtil;
import net.jukoz.me.utils.IdentifierUtil;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;

public class PacketSetRace extends ClientToServerPacket<PacketSetRace>
{
    public static final Type<PacketSetRace> ID = new Type<>(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "packet_set_race"));

    public static final StreamCodec<RegistryFriendlyByteBuf, PacketSetRace> CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, p -> p.race,
            PacketSetRace::new
    );

    private final String race;


    public PacketSetRace(String race){
        this.race = race;
    }

    @Override
    public Type<PacketSetRace> type() {
        return ID;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, PacketSetRace> streamCodec() {
        return CODEC;
    }

    @Override
    public void process(ServerPacketContext context) {
        MinecraftServer server = context.player().getServer();
        server.execute(() -> {
            try{
                RaceUtil.updateRace(context.player(), RaceLookup.getRace(context.player().level(), IdentifierUtil.getIdentifierFromString(race)), true);
            } catch (Exception e){
                LoggerUtil.logError("PacketSetRace::Tried setting race for player.", e);
            }
        });
    }
}