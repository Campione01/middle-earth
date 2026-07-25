package net.jukoz.me.block.special;

import net.jukoz.me.particles.ModParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import java.util.OptionalInt;

public class ModLeavesBlock extends LeavesBlock {
    final protected boolean castShadow;
    private final ParticleOptions particleType;
    public ModLeavesBlock(Properties settings, boolean castShadow, ParticleOptions particleType) {
        super(settings);
        this.castShadow = castShadow;
        this.particleType = particleType;
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        world.setBlock(pos, updateDistance(state, world, pos), Block.UPDATE_ALL);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        int i;
        if (state.getValue(WATERLOGGED).booleanValue()) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }
        if ((i = getDistanceAt(neighborState) + 1) != 1 || state.getValue(DISTANCE) != i) {
            world.scheduleTick(pos, this, 1);
        }
        return state;
    }

    private static BlockState updateDistance(BlockState state, LevelAccessor world, BlockPos pos) {
        int i = 7;
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        for(int x = -1; x <= 1; x++) {
            for(int y = -1; y <= 1; y++) {
                for(int z = -1; z <= 1; z++) {
                    if(x == 0 && y == 0 && z == 0) continue;
                    mutable.setWithOffset(pos, x, y, z);
                    i = Math.min(i, getDistanceAt(world.getBlockState(mutable)) + 1);
                    if (i == 1) break;
                }
            }
        }

        return state.setValue(DISTANCE, i);
    }

    private static int getDistanceAt(BlockState state) {
        return LeavesBlock.getOptionalDistanceAt(state).orElse(7);
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter world, BlockPos pos) {
        if(castShadow) return super.getLightBlock(state, world, pos);
        return 0;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
        BlockState blockState = (this.defaultBlockState().setValue(PERSISTENT, true)).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
        return updateDistance(blockState, ctx.getLevel(), ctx.getClickedPos());
    }

    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        super.animateTick(state, world, pos, random);
        if(this.particleType != null){
            if (random.nextInt(10) == 0) {
                BlockPos blockPos = pos.below();
                BlockState blockState = world.getBlockState(blockPos);
                if (!isFaceFull(blockState.getCollisionShape(world, blockPos), Direction.UP)) {
                    ParticleUtils.spawnParticleBelow(world, pos, random, this.particleType);
                }
            }
        }

    }
}
