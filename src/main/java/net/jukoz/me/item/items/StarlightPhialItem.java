package net.jukoz.me.item.items;

import net.jukoz.me.compat.neoforge.api.client.networking.v1.ClientPlayNetworking;
import net.jukoz.me.network.packets.C2S.PacketOnboardingRequest;
import net.jukoz.me.world.dimension.ModDimensions;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class StarlightPhialItem extends Item {
    public StarlightPhialItem(Properties settings) {
        super(settings);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if(world.isClientSide){
            ClientPlayNetworking.send(new PacketOnboardingRequest());
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
