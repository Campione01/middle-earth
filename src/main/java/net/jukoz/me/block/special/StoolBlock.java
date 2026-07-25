package net.jukoz.me.block.special;

import net.minecraft.world.level.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class StoolBlock extends SeatBlock implements SimpleWaterloggedBlock {

    public StoolBlock(Properties settings) {
        super(settings);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch(state.getValue(FACING)) {
            case WEST, EAST -> Block.box(3, 0, 0, 13, 10, 16);
            case SOUTH, NORTH -> Block.box(0, 0, 3, 16, 10, 13);
            default -> Shapes.box(1,1,1,1,1,1);
        };
    }
}
