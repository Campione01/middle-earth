package net.jukoz.me.compat.neoforge.api.registry;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class CompostingChanceRegistry {
    public static final CompostingChanceRegistry INSTANCE = new CompostingChanceRegistry();
    private final Map<Item, Float> compostables = new LinkedHashMap<>();

    private CompostingChanceRegistry() {
    }

    public void add(ItemLike item, float chance) {
        Item itemEntry = item.asItem();
        compostables.put(itemEntry, chance);
        ComposterBlock.COMPOSTABLES.put(itemEntry, chance);
    }

    public Map<Item, Float> entries() {
        return Collections.unmodifiableMap(compostables);
    }
}
