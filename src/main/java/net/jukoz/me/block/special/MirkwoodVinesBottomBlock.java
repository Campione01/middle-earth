package net.jukoz.me.block.special;

import com.mojang.serialization.MapCodec;
import net.jukoz.me.block.ModNatureBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MirkwoodVinesBottomBlock extends GrowingPlantHeadBlock {
    public static final MapCodec<MirkwoodVinesBottomBlock> CODEC = MirkwoodVinesBottomBlock.simpleCodec(MirkwoodVinesBottomBlock::new);

    protected static final VoxelShape SHAPE = Block.box(4.0, 9.0, 4.0, 12.0, 16.0, 12.0);

    public MirkwoodVinesBottomBlock(Properties settings) {
        super(settings, Direction.DOWN, SHAPE, false, 0.05);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockPos blockPos = pos.relative(this.growthDirection.getOpposite());
        BlockState blockState = world.getBlockState(blockPos);
        if (!this.canAttachTo(blockState)) {
            return false;
        } else {
            return isBlockStateValid(blockState, world, blockPos);
        }
    }

    public boolean isBlockStateValid(BlockState blockState, LevelReader world, BlockPos blockPos) {
        boolean isValid = blockState.is(BlockTags.LOGS) || blockState.is(BlockTags.LEAVES) || blockState.is(this.getHeadBlock())
                || blockState.is(this.getBodyBlock()) || blockState.isFaceSturdy(world, blockPos, this.growthDirection);
        return isValid;
    }

    @Override
    protected MapCodec<? extends GrowingPlantHeadBlock> codec() {
        return CODEC;
    }

    protected int getBlocksToGrowWhenBonemealed(RandomSource random) {
        return random.nextInt(5);
    }

    protected Block getBodyBlock() {
        return ModNatureBlocks.MIRKWOOD_VINES_PLANT;
    }

    protected boolean canGrowInto(BlockState state) {
        return NetherVines.isValidGrowthState(state);
    }
}
