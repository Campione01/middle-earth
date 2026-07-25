package net.jukoz.me.compat.neoforge.impl.recipe.ingredient.builtin;

import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.world.item.crafting.Ingredient;

public final class ComponentsIngredient {
    private final Ingredient base;

    public ComponentsIngredient(Ingredient base, DataComponentPatch patch) {
        this.base = base;
    }

    public Ingredient toVanilla() {
        return base;
    }
}
