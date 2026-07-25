package net.jukoz.me.compat.neoforge.api.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class OxidizableBlocksRegistry {
    private static final Map<Block, Block> OXIDIZABLES = new LinkedHashMap<>();
    private static final Map<Block, Block> PREVIOUS_OXIDIZABLES = new LinkedHashMap<>();
    private static final Map<Block, Block> WAXABLES = new LinkedHashMap<>();
    private static final Map<Block, Block> UNWAXED = new LinkedHashMap<>();

    static {
        NeoForge.EVENT_BUS.addListener(OxidizableBlocksRegistry::onToolModification);
    }

    private OxidizableBlocksRegistry() {
    }

    public static void registerOxidizableBlockPair(Block lessOxidized, Block moreOxidized) {
        OXIDIZABLES.put(lessOxidized, moreOxidized);
        PREVIOUS_OXIDIZABLES.put(moreOxidized, lessOxidized);
    }

    public static void registerWaxableBlockPair(Block unwaxed, Block waxed) {
        WAXABLES.put(unwaxed, waxed);
        UNWAXED.put(waxed, unwaxed);
    }

    public static Map<Block, Block> oxidizables() {
        return Collections.unmodifiableMap(OXIDIZABLES);
    }

    public static Map<Block, Block> waxables() {
        return Collections.unmodifiableMap(WAXABLES);
    }

    private static void onToolModification(BlockEvent.BlockToolModificationEvent event) {
        Block target = null;
        if (event.getItemAbility() == ItemAbilities.AXE_SCRAPE) {
            target = PREVIOUS_OXIDIZABLES.get(event.getState().getBlock());
        } else if (event.getItemAbility() == ItemAbilities.AXE_WAX_OFF) {
            target = UNWAXED.get(event.getState().getBlock());
        }
        if (target != null) {
            BlockState state = target.withPropertiesOf(event.getState());
            event.setFinalState(state);
        }
    }
}
