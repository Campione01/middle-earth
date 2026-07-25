package net.jukoz.me.block.special.beds;

import net.jukoz.me.block.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CustomBedBlockEntity extends BlockEntity {

    public CustomBedBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BED, pos, state);
    }

    public CustomBedBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}
