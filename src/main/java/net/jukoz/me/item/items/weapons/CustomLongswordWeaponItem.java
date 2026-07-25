package net.jukoz.me.item.items.weapons;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.item.utils.ModWeaponTypes;
import net.jukoz.me.utils.ModFactions;
import net.jukoz.me.utils.ModSubFactions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;

public class CustomLongswordWeaponItem extends ReachWeaponItem {
    public static final ResourceLocation ENTITY_INTERACTION_RANGE_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "entity_interaction_range");

    public CustomLongswordWeaponItem(Tier toolMaterial) {
        super(toolMaterial, ModWeaponTypes.LONGSWORD);
    }

    public CustomLongswordWeaponItem(Tier toolMaterial, ModFactions faction) {
        super(toolMaterial, faction, ModWeaponTypes.LONGSWORD);
    }

    public CustomLongswordWeaponItem(Tier toolMaterial, ModSubFactions subFaction) {
        super(toolMaterial, subFaction, ModWeaponTypes.LONGSWORD);
    }
}
