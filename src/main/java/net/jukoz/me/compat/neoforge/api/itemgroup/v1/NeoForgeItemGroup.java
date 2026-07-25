package net.jukoz.me.compat.neoforge.api.itemgroup.v1;

import net.minecraft.world.item.CreativeModeTab;

public final class NeoForgeItemGroup {
    private NeoForgeItemGroup() {
    }

    public static CreativeModeTab.Builder builder() {
        return CreativeModeTab.builder();
    }
}
