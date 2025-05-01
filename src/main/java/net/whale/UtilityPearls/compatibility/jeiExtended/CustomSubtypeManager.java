package net.whale.UtilityPearls.compatibility.jeiExtended;

import mezz.jei.api.ingredients.IIngredientTypeWithSubtypes;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.ISubtypeManager;
import mezz.jei.api.ingredients.subtypes.UidContext;
import org.jetbrains.annotations.Nullable;


public class CustomSubtypeManager implements ISubtypeManager {
    private final CustomSubtypeInterpreters interpreters;

    public CustomSubtypeManager(CustomSubtypeInterpreters interpreters) {
        this.interpreters = interpreters;
    }

    @Override
    public @Nullable <T> Object getSubtypeData(IIngredientTypeWithSubtypes<?, T> ingredientType, T ingredient, UidContext context) {
        return interpreters.get(ingredientType, ingredient)
                .map(subtypeInterpreter -> subtypeInterpreter.apply(ingredient, context))
                .orElse(IIngredientSubtypeInterpreter.NONE);
    }

    @Override
    public @Nullable <B, T> Object getSubtypeData(IIngredientTypeWithSubtypes<B, T> iIngredientTypeWithSubtypes, ITypedIngredient<T> iTypedIngredient, UidContext uidContext) {
        return null;
    }
    @Override
    public <T> String getSubtypeInfo(IIngredientTypeWithSubtypes<?, T> iIngredientTypeWithSubtypes, T t, UidContext uidContext) {
        return "";
    }
    @Override
    public <T, B> boolean hasSubtypes(IIngredientTypeWithSubtypes<B, T> iIngredientTypeWithSubtypes, T t) {
        return true;
    }
}
