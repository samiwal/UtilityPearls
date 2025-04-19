package net.whale.UtilityPearls.compatibility;

import mezz.jei.api.ingredients.IIngredientTypeWithSubtypes;
import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;

import java.util.Optional;

public class CustomSubtypeInterpreters {
    private final Table<IIngredientTypeWithSubtypes<?, ?>, Object, IIngredientSubtypeInterpreter<?>> table;

    public CustomSubtypeInterpreters() {
        this.table = Table.identityHashBasedTable();
    }

    public <B, I> Optional<IIngredientSubtypeInterpreter<I>> get(IIngredientTypeWithSubtypes<B, I> type, I ingredient) {
        B base = type.getBase(ingredient);
        IIngredientSubtypeInterpreter<?> interpreter = table.get(type, base);
        @SuppressWarnings("unchecked")
        IIngredientSubtypeInterpreter<I> cast = (IIngredientSubtypeInterpreter<I>) interpreter;
        return Optional.ofNullable(cast);
    }
}
