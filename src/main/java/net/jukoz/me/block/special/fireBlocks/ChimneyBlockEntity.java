package net.jukoz.me.block.special.fireBlocks;

import net.jukoz.me.block.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class ChimneyBlockEntity extends ToggleableFireBlockEntity {

    public ChimneyBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CHIMNEY, pos, state);
    }
}
