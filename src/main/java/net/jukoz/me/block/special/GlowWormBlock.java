package net.jukoz.me.block.special;

import com.mojang.serialization.MapCodec;
import net.jukoz.me.block.ModNatureBlocks;
import net.jukoz.me.item.ModResourceItems;
import net.minecraft.world.level.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GlowWormBlock extends GrowingPlantBodyBlock {
    public static final MapCodec<GlowWormBlock> CODEC = GlowWormBlock.simpleCodec(GlowWormBlock::new);
    public static final VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

    public GlowWormBlock(BlockBehaviour.Properties settings) {
        super(settings, Direction.DOWN, SHAPE, false);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        InteractionHand hand = player.getUsedItemHand();
        ItemStack itemStack = player.getItemInHand(hand);

        if (itemStack.is(Items.GLASS_BOTTLE)) {
            itemStack.shrink(1);
            world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
            if (itemStack.isEmpty()) {
                player.setItemInHand(hand, new ItemStack(ModResourceItems.GLOWWORM_BOTTLE));
            } else if (!player.getInventory().add(new ItemStack(ModResourceItems.GLOWWORM_BOTTLE))) {
                player.drop(new ItemStack(ModResourceItems.GLOWWORM_BOTTLE), false);
            }
            world.removeBlock(pos,false);
        }
        return InteractionResult.sidedSuccess(world.isClientSide);
    }

    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) ModNatureBlocks.GLOWWORM_WEBBING;
    }

    @Override
    protected MapCodec<GlowWormBlock> codec() {
        return CODEC;
    }
}
