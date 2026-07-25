package net.jukoz.me.compat.neoforge.api.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;

public final class FlammableBlockRegistry {
    private static final FlammableBlockRegistry DEFAULT = new FlammableBlockRegistry();

    private FlammableBlockRegistry() {
    }

    public static FlammableBlockRegistry getDefaultInstance() {
        return DEFAULT;
    }

    public void add(Block block, int burnChance, int spreadChance) {
        ((FireBlock) Blocks.FIRE).setFlammable(block, burnChance, spreadChance);
    }
}
