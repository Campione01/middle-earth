package net.jukoz.me.compat.neoforge.api.registry;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class TillableBlockRegistry {
    private static final Map<Block, Entry> TILLABLES = new LinkedHashMap<>();

    static {
        NeoForge.EVENT_BUS.addListener(TillableBlockRegistry::onToolModification);
    }

    private TillableBlockRegistry() {
    }

    public static void register(Block block, Predicate<UseOnContext> usagePredicate, Consumer<UseOnContext> tillingAction) {
        TILLABLES.put(block, new Entry(usagePredicate, tillingAction));
    }

    private static void onToolModification(BlockEvent.BlockToolModificationEvent event) {
        if (event.getItemAbility() != ItemAbilities.HOE_TILL) {
            return;
        }
        Entry entry = TILLABLES.get(event.getState().getBlock());
        if (entry != null && entry.usagePredicate().test(event.getContext())) {
            event.setFinalState(Blocks.FARMLAND.defaultBlockState());
        }
    }

    private record Entry(Predicate<UseOnContext> usagePredicate, Consumer<UseOnContext> tillingAction) {
    }
}
