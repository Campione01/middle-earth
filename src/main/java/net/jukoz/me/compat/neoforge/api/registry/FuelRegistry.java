package net.jukoz.me.compat.neoforge.api.registry;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class FuelRegistry {
    public static final FuelRegistry INSTANCE = new FuelRegistry();
    private final Map<Item, Integer> fuels = new LinkedHashMap<>();

    private FuelRegistry() {
        NeoForge.EVENT_BUS.addListener(this::onFuelBurnTime);
    }

    public void add(ItemLike item, int ticks) {
        add(item.asItem(), ticks);
    }

    public void add(Item item, int ticks) {
        if (item != Items.AIR && ticks > 0) {
            fuels.put(item, ticks);
        }
    }

    public void add(Block block, int ticks) {
        add(block.asItem(), ticks);
    }

    public Map<Item, Integer> entries() {
        return Collections.unmodifiableMap(fuels);
    }

    private void onFuelBurnTime(FurnaceFuelBurnTimeEvent event) {
        Integer burnTime = fuels.get(event.getItemStack().getItem());
        if (burnTime != null) {
            event.setBurnTime(burnTime);
        }
    }
}
