package net.jukoz.me.block.special.fireBlocks;

import net.jukoz.me.block.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class SmallBrazierBlockEntity extends ToggleableFireBlockEntity {

    public SmallBrazierBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SMALL_BRAZIER, pos, state);
    }
}
