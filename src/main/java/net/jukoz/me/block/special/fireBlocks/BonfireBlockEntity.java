package net.jukoz.me.block.special.fireBlocks;

import net.jukoz.me.block.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BonfireBlockEntity extends ToggleableFireBlockEntity {

    public BonfireBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BONFIRE, pos, state);
    }


}
