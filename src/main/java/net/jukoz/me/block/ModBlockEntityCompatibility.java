package net.jukoz.me.block;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

public final class ModBlockEntityCompatibility {
    private ModBlockEntityCompatibility() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(ModBlockEntityCompatibility::addValidBlocks);
    }

    private static void addValidBlocks(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityType.BARREL, ModDecorativeBlocks.THIN_BARREL, ModDecorativeBlocks.SMALL_CRATE);
    }
}
