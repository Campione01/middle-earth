package net.jukoz.me.compat.neoforge.api.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class StrippableBlockRegistry {
    private static final Map<Block, Block> STRIPPABLES = new LinkedHashMap<>();

    static {
        NeoForge.EVENT_BUS.addListener(StrippableBlockRegistry::onToolModification);
    }

    private StrippableBlockRegistry() {
    }

    public static void register(Block input, Block stripped) {
        STRIPPABLES.put(input, stripped);
    }

    public static Map<Block, Block> entries() {
        return Collections.unmodifiableMap(STRIPPABLES);
    }

    private static void onToolModification(BlockEvent.BlockToolModificationEvent event) {
        if (event.getItemAbility() != ItemAbilities.AXE_STRIP) {
            return;
        }
        Block stripped = STRIPPABLES.get(event.getState().getBlock());
        if (stripped != null) {
            BlockState strippedState = stripped.withPropertiesOf(event.getState());
            event.setFinalState(strippedState);
        }
    }
}
