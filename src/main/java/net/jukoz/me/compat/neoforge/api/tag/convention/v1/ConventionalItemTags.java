package net.jukoz.me.compat.neoforge.api.tag.convention.v1;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class ConventionalItemTags {
    public static final TagKey<Item> SHIELDS = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "shields"));

    private ConventionalItemTags() {
    }
}
