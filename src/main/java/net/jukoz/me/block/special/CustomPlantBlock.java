package net.jukoz.me.block.special;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CustomPlantBlock extends BushBlock {
    public static final MapCodec<CustomPlantBlock> CODEC = CustomPlantBlock.simpleCodec(CustomPlantBlock::new);
    protected static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 13.0, 14.0);

    public CustomPlantBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected MapCodec<CustomPlantBlock> codec() {
        return CODEC;
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return (floor.is(BlockTags.DIRT) || floor.is(Blocks.FARMLAND)) && floor.isFaceSturdy(world, pos, Direction.UP);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}