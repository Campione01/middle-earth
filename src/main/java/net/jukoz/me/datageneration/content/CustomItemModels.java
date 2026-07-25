package net.jukoz.me.datageneration.content;

import net.jukoz.me.MiddleEarth;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import java.util.Optional;

public class CustomItemModels {
    public static final ModelTemplate BIG_WEAPON = new ModelTemplate(Optional.of(
            ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "item/big_weapon")), Optional.empty(), TextureSlot.LAYER0);
    public static final ModelTemplate BOW = new ModelTemplate(Optional.of(
            ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "item/bow")), Optional.empty(), TextureSlot.LAYER0);
    public static final ModelTemplate LONGBOW = new ModelTemplate(Optional.of(
            ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "item/longbow")), Optional.empty(), TextureSlot.LAYER0);

    public static final ModelTemplate CROSSBOW = new ModelTemplate(Optional.of(
            ResourceLocation.parse("item/crossbow")), Optional.empty(), TextureSlot.LAYER0);

    public static final ModelTemplate TEMPLATE_SPAWN_EGG = new ModelTemplate(Optional.of(
            ResourceLocation.fromNamespaceAndPath("minecraft", "item/template_spawn_egg")), Optional.empty());
}
