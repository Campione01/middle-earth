package net.jukoz.me.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.jukoz.me.compat.neoforge.impl.recipe.ingredient.CustomIngredientImpl;
import net.jukoz.me.block.ModDecorativeBlocks;
import net.jukoz.me.block.special.forge.MultipleStackRecipeInput;
import net.jukoz.me.item.ModDataComponentTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import java.util.List;
import java.util.Objects;

public class ArtisanRecipe implements Recipe<MultipleStackRecipeInput> {
    public final String category;
    public final ItemStack output;
    public final String disposition;
    public final List<Ingredient> inputs;

    public ArtisanRecipe(String category, ItemStack output, List<Ingredient> recipeItems, String disposition) {
        this.category = category;
        this.output = output;
        this.inputs = recipeItems;
        this.disposition = disposition;
    }

    public ArtisanRecipe(String category, ItemStack output, List<Ingredient> recipeItems) {
        this.category = category;
        this.output = output;
        this.inputs = recipeItems;
        this.disposition = null;
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(ModDecorativeBlocks.ARTISAN_TABLE);
    }

    @Override
    public boolean matches(MultipleStackRecipeInput input, Level world) {
        int i = 0;
        for (int j = 0; j < input.size(); j++) {
            ItemStack itemStack = input.getItem(j);
            if (itemStack.isEmpty()) continue;
            i++;
        }

        if(i != this.inputs.size()) return false;

        for (int j = 0; j < inputs.size(); j++) {
            Ingredient ingredient = this.inputs.get(j);
            if (!ingredient.test(input.getItem(j))) {
                return false;
            }

            if (ingredient.getItems().length == 1){
                for (ItemStack itemStack2 : ingredient.getItems()) {
                    if (!Objects.equals(itemStack2.get(DataComponents.TRIM), input.getItem(j).get(DataComponents.TRIM))) return false;
                }
            }
        }

        return true;
    }

    @Override
    public ItemStack assemble(MultipleStackRecipeInput input, HolderLookup.Provider lookup) {
        return this.output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registriesLookup) {
        return output;
    }

    public ItemStack getOutput() {
        return output;
    }

    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> defaultedList = NonNullList.create();
        defaultedList.addAll(this.inputs);
        return defaultedList;
    }

    public String getDisposition() {
        return disposition;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<ArtisanRecipe> {
        private Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = "artisan_table";
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public static class Serializer implements RecipeSerializer<ArtisanRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "artisan_table";
        private final MapCodec<ArtisanRecipe> codec;
        private final StreamCodec<RegistryFriendlyByteBuf, ArtisanRecipe> packetCodec;

        protected Serializer() {
            this.codec = RecordCodecBuilder.mapCodec((instance) -> instance.group(
                    Codec.STRING.fieldOf("category").forGetter(recipe -> recipe.category),
                    ItemStack.CODEC.fieldOf("output").forGetter(recipe -> recipe.output),
                    CustomIngredientImpl.CODEC_NONEMPTY.listOf().fieldOf("ingredients").forGetter(recipe -> recipe.inputs),
                    Codec.STRING.fieldOf("disposition").forGetter(recipe -> recipe.disposition)
            ).apply(instance, ArtisanRecipe::new));

            this.packetCodec = StreamCodec.of(ArtisanRecipe.Serializer::write, ArtisanRecipe.Serializer::read);
        }

        @Override
        public MapCodec<ArtisanRecipe> codec() {
            return this.codec;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ArtisanRecipe> streamCodec() {
            return this.packetCodec;
        }

        private static ArtisanRecipe read(RegistryFriendlyByteBuf buf) {
            String category = buf.readUtf();
            ItemStack output = ItemStack.STREAM_CODEC.decode(buf);
            int i = buf.readVarInt();
            NonNullList<Ingredient> defaultedList = NonNullList.withSize(i, Ingredient.EMPTY);
            defaultedList.replaceAll(empty -> CustomIngredientImpl.CONTENTS_STREAM_CODEC.decode(buf));
            String disposition = buf.readUtf();
            return new ArtisanRecipe(category, output, defaultedList, disposition);
        }

        private static void write(RegistryFriendlyByteBuf buf, ArtisanRecipe recipe) {
            buf.writeUtf(recipe.category);
            ItemStack.STREAM_CODEC.encode(buf, recipe.output);
            buf.writeVarInt(recipe.inputs.size());
            for (Ingredient ingredient : recipe.inputs) {
                CustomIngredientImpl.CONTENTS_STREAM_CODEC.encode(buf, ingredient);
            }
            buf.writeUtf(recipe.disposition);
        }
    }
}
