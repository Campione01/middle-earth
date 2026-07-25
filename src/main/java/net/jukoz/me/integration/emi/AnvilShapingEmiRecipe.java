package net.jukoz.me.integration.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.recipe.AnvilShapingRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AnvilShapingEmiRecipe implements EmiRecipe {
    private final ResourceLocation id;
    private final EmiIngredient input;
    private final EmiStack output;

    public AnvilShapingEmiRecipe(AnvilShapingRecipe recipe){
        this.id = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "/shaping_anvil" + "/" + BuiltInRegistries.ITEM.getKey(recipe.getOutput().getItem()).getPath());
        this.input = EmiIngredient.of(recipe.getIngredient());
        this.output = EmiStack.of(recipe.getOutput());
    }
    @Override
    public EmiRecipeCategory getCategory() {
        return ModEmiPlugin.ANVIL_SHAPING_CATEGORY;
    }

    @Override
    public @Nullable ResourceLocation getId() {
        return id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return List.of(input);
    }

    @Override
    public List<EmiStack> getOutputs() {
        return List.of(output);
    }

    @Override
    public int getDisplayWidth() {
        return 76;
    }

    @Override
    public int getDisplayHeight() {
        return 83;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addSlot(input, 0, 0);

        widgets.addSlot(output, 54, 0).recipeContext(this);
    }
}
