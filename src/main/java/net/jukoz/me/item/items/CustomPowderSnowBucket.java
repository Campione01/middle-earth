package net.jukoz.me.item.items;

import net.jukoz.me.item.ModToolItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.SolidBucketItem;
import net.minecraft.world.level.block.Block;

public class CustomPowderSnowBucket extends SolidBucketItem {
    public CustomPowderSnowBucket(Block block, SoundEvent placeSound, Properties settings) {
        super(block, placeSound, settings);
    }

    /*@Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ActionResult actionResult = super.useOnBlock(context);
        PlayerEntity playerEntity = context.getPlayer();
        if (actionResult.isAccepted() && playerEntity != null && !playerEntity.isCreative()) {
            Hand hand = context.getHand();
            playerEntity.setStackInHand(hand, ModToolItems.BRONZE_BUCKET.getDefaultStack());
        }

        return actionResult;
    }*/
}
