package net.whale.UtilityPearls.compatibility;

import mezz.jei.api.ingredients.IIngredientTypeWithSubtypes;
import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.ISubtypeManager;
import mezz.jei.api.ingredients.subtypes.UidContext;


public class CustomSubtypeManager implements ISubtypeManager {
    private final CustomSubtypeInterpreters interpreters;

    public CustomSubtypeManager(CustomSubtypeInterpreters interpreters) {
        this.interpreters = interpreters;
    }
    @Override
    public <T> String getSubtypeInfo(IIngredientTypeWithSubtypes<?, T> ingredientType, T ingredient, UidContext context) {
        return interpreters.get(ingredientType, ingredient)
                .map(subtypeInterpreter -> subtypeInterpreter.apply(ingredient, context))
                .orElse(IIngredientSubtypeInterpreter.NONE);
    }

    @Override
    public <T, B> boolean hasSubtypes(IIngredientTypeWithSubtypes<B, T> iIngredientTypeWithSubtypes, T t) {
        return true;
    }
}
