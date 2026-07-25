package net.jukoz.me.resources.datas;

import net.jukoz.me.MiddleEarth;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;

public enum FactionType {
    FACTION,
    SUBFACTION;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    public MutableComponent getName() {
        return Component.translatable("faction_type.".concat(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, toString()).toLanguageKey()));
    }
}
