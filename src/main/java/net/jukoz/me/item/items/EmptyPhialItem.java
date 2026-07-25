package net.jukoz.me.item.items;

import net.jukoz.me.item.ModResourceItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class EmptyPhialItem  extends Item {
    public EmptyPhialItem(Item.Properties settings) {
        super(settings);
    }

    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);

        BlockHitResult blockHitResult = getPlayerPOVHitResult(world, user, ClipContext.Fluid.SOURCE_ONLY);
        if (blockHitResult.getType() != HitResult.Type.MISS) {
            if (blockHitResult.getType() == HitResult.Type.BLOCK) {
                BlockPos blockPos = blockHitResult.getBlockPos();
                if (!world.mayInteract(user, blockPos)) {
                    return InteractionResultHolder.pass(itemStack);
                }

                if (world.getFluidState(blockPos).is(FluidTags.WATER)) {
                    world.playSound(user, user.getX(), user.getY(), user.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, 1.15F);
                    world.gameEvent(user, GameEvent.FLUID_PICKUP, blockPos);
                    return InteractionResultHolder.sidedSuccess(this.fill(itemStack, user, new ItemStack(ModResourceItems.WATER_PHIAL)), world.isClientSide());
                }
            }

        }
        return InteractionResultHolder.pass(itemStack);
    }

    protected ItemStack fill(ItemStack stack, Player player, ItemStack outputStack) {
        player.awardStat(Stats.ITEM_USED.get(this));
        return ItemUtils.createFilledResult(stack, player, outputStack);
    }
}
