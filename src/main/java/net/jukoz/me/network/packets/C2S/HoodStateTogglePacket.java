package net.jukoz.me.network.packets.C2S;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.item.items.armor.CustomHelmetItem;
import net.jukoz.me.item.items.armor.HoodHelmetItem;
import net.jukoz.me.network.contexts.ServerPacketContext;
import net.jukoz.me.network.packets.ClientToServerPacket;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import java.util.Objects;

public class HoodStateTogglePacket extends ClientToServerPacket<HoodStateTogglePacket> {
    public static final Type<HoodStateTogglePacket> ID = new Type<>(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "hood_state_toggle_packet"));
    public static final HoodStateTogglePacket INSTANCE = new HoodStateTogglePacket();
    public static final StreamCodec<RegistryFriendlyByteBuf, HoodStateTogglePacket> CODEC = StreamCodec.unit(INSTANCE);

    public HoodStateTogglePacket() {
    }

    @Override
    public Type<HoodStateTogglePacket> type() {
        return ID;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, HoodStateTogglePacket> streamCodec() {
        return CODEC;
    }

    @Override
    public void process(ServerPacketContext context) {
        try{
            Objects.requireNonNull(context.player().getServer()).execute(() -> {
                Player player = context.player();

                player.getArmorSlots().iterator().forEachRemaining(stack ->{
                    if (stack.getItem() instanceof HoodHelmetItem){
                        HoodHelmetItem.toggleHoodState(context.player(), stack);
                    }

                    if (stack.getItem() instanceof CustomHelmetItem){
                        CustomHelmetItem.toggleHoodState(context.player(), stack);
                    }
                });

            });
        }catch (Exception e){
            LoggerUtil.logError("HoodStatePacket error: ", e);
        }
    }
}
