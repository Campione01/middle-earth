package net.jukoz.me.compat.neoforge.impl.recipe.ingredient;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.Ingredient;

public final class CustomIngredientImpl {
    public static final Codec<Ingredient> CODEC_NONEMPTY = Ingredient.CODEC_NONEMPTY;
    public static final StreamCodec<RegistryFriendlyByteBuf, Ingredient> CONTENTS_STREAM_CODEC = Ingredient.CONTENTS_STREAM_CODEC;

    private CustomIngredientImpl() {
    }
}
