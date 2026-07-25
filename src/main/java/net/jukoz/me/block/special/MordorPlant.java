package net.jukoz.me.block.special;

import com.mojang.serialization.MapCodec;
import net.jukoz.me.block.StoneBlockSets;
import net.minecraft.world.level.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MordorPlant extends CustomPlantBlock implements BonemealableBlock {
    public static final MapCodec<MordorPlant> CODEC = MordorPlant.simpleCodec(MordorPlant::new);
    protected static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 13.0, 14.0);

    public MordorPlant(BlockBehaviour.Properties settings) {
        super(settings);
    }


    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return  floor.is(BlockTags.DIRT) || floor.is(BlockTags.SAND) || floor.is(StoneBlockSets.ASHEN_STONE.base())
                || floor.is(Blocks.BASALT) || floor.is(Blocks.FARMLAND);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        DoublePlantBlock tallPlantBlock = (DoublePlantBlock)(state.is(Blocks.FERN) ? Blocks.LARGE_FERN : Blocks.TALL_GRASS);
        if (tallPlantBlock.defaultBlockState().canSurvive(world, pos) && world.isEmptyBlock(pos.above())) {
            DoublePlantBlock.placeAt(world, tallPlantBlock.defaultBlockState(), pos, 2);
        }
    }
}