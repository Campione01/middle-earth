package net.jukoz.me.item.utils.armor;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorMaterial;


public record ExtendedArmorMaterial(Holder<ArmorMaterial> material, int durabilityModifier, ModArmorMaterials.Tiers tier) {
}
