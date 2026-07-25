package net.jukoz.me.item.items;

import net.jukoz.me.utils.ClientSideAccess;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MiddleEarthMapItem extends Item {
    public MiddleEarthMapItem(Properties settings) {
        super(settings);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        if(world.isClientSide) {
            ClientSideAccess.openMiddleEarthMap();
        }
        return super.use(world, user, hand);
    }
}
