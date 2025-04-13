package net.whale.UtilityPearls.compatibility;

import mezz.jei.api.ingredients.IIngredientTypeWithSubtypes;
import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.ISubtypeManager;
import mezz.jei.api.ingredients.subtypes.UidContext;


public class SubtypeManager implements ISubtypeManager {
    private final SubtypeInterpreters interpreters;

    public SubtypeManager(SubtypeInterpreters interpreters) {
        this.interpreters = interpreters;
    }

    @Override
    public <T> String getSubtypeInfo(IIngredientTypeWithSubtypes<?, T> ingredientType, T ingredient, UidContext context) {
        return interpreters.get(ingredientType, ingredient)
                .map(subtypeInterpreter -> subtypeInterpreter.apply(ingredient, context))
                .orElse(IIngredientSubtypeInterpreter.NONE);
    }
}
