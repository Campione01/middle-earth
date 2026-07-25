package net.jukoz.me.network.packets.C2S;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.config.ModServerConfigs;
import net.jukoz.me.item.items.StarlightPhialItem;
import net.jukoz.me.network.contexts.ServerPacketContext;
import net.jukoz.me.network.packets.ClientToServerPacket;
import net.jukoz.me.resources.StateSaverAndLoader;
import net.jukoz.me.resources.datas.races.RaceUtil;
import net.jukoz.me.resources.persistent_datas.PlayerData;
import net.jukoz.me.utils.LoggerUtil;
import net.jukoz.me.world.dimension.ModDimensions;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;


public class PacketTeleportToCurrentOverworldSpawn extends ClientToServerPacket<PacketTeleportToCurrentOverworldSpawn> {
    public static final CustomPacketPayload.Type<PacketTeleportToCurrentOverworldSpawn> ID = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "packet_teleport_to_current_overworld_spawn"));
    public static final PacketTeleportToCurrentOverworldSpawn INSTANCE = new PacketTeleportToCurrentOverworldSpawn();
    public static final StreamCodec<RegistryFriendlyByteBuf, PacketTeleportToCurrentOverworldSpawn> CODEC = StreamCodec.unit(INSTANCE);

    @Override
    public Type<PacketTeleportToCurrentOverworldSpawn> type() {
        return ID;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, PacketTeleportToCurrentOverworldSpawn> streamCodec() {
        return CODEC;
    }



    @Override
    public void process(ServerPacketContext context) {
        try{
            context.player().getServer().execute(() -> {
                RaceUtil.reset(context.player());

                if(ModDimensions.isInMiddleEarth(context.player().level())){
                    ModDimensions.teleportPlayerToOverworld(context.player());
                    RaceUtil.reset(context.player());
                    if(ModServerConfigs.ENABLE_KEEP_RACE_ON_DIMENSION_SWAP){
                        RaceUtil.initializeRace(context.player());
                    } else {
                        RaceUtil.reset(context.player());
                    }

                    if(!context.player().isCreative() && context.player().getMainHandItem().getItem() instanceof StarlightPhialItem)
                        context.player().getItemInHand(InteractionHand.MAIN_HAND).shrink(1);
                }
            });
        } catch (Exception e){
            LoggerUtil.logError("PacketTeleportToCurrentOverworldSpawn::Apply - Tried applying the return to overworld packet",e);
        }
    }
}