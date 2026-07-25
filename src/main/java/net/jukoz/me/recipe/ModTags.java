package net.jukoz.me.recipe;

import net.jukoz.me.MiddleEarth;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static TagKey<Item> DYEABLE = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "dyeable"));
}
