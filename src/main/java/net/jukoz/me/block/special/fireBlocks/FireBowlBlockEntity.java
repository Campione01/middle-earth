package net.jukoz.me.block.special.fireBlocks;

import net.jukoz.me.block.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class FireBowlBlockEntity extends ToggleableFireBlockEntity {

    public FireBowlBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FIRE_BOWL, pos, state);
    }
}
