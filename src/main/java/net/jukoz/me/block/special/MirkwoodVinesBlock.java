package net.jukoz.me.block.special;

import com.mojang.serialization.MapCodec;
import net.jukoz.me.block.ModNatureBlocks;
import net.jukoz.me.item.ModResourceItems;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MirkwoodVinesBlock extends GrowingPlantBodyBlock {
    public static final MapCodec<MirkwoodVinesBlock> CODEC = MirkwoodVinesBlock.simpleCodec(MirkwoodVinesBlock::new);
    public static final VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

    public MirkwoodVinesBlock(Properties settings) {
        super(settings, Direction.DOWN, SHAPE, false);
    }

    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) ModNatureBlocks.MIRKWOOD_VINES;
    }

    @Override
    protected MapCodec<MirkwoodVinesBlock> codec() {
        return CODEC;
    }
}
