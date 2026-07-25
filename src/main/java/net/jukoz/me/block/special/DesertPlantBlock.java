package net.jukoz.me.block.special;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class DesertPlantBlock extends CustomPlantBlock {
    public static final MapCodec<DesertPlantBlock> CODEC = DesertPlantBlock.simpleCodec(DesertPlantBlock::new);

    public DesertPlantBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.is(BlockTags.DIRT) || floor.is(BlockTags.SAND) || floor.is(Blocks.FARMLAND);
    }
}