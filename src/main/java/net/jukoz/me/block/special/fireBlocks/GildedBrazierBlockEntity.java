package net.jukoz.me.block.special.fireBlocks;

import net.jukoz.me.block.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class GildedBrazierBlockEntity extends ToggleableFireBlockEntity {

    public GildedBrazierBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GILDED_BIG_BRAZIER, pos, state);
    }
}
