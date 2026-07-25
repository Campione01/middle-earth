package net.jukoz.me.block.special.fireBlocks;

import net.jukoz.me.block.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BrazierBlockEntity extends ToggleableFireBlockEntity {

    public BrazierBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BIG_BRAZIER, pos, state);
    }
}
