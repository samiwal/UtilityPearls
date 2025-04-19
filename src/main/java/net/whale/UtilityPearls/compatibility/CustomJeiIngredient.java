package net.whale.UtilityPearls.compatibility;

import mezz.jei.api.helpers.IStackHelper;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class CustomJeiIngredient extends Ingredient {
    private final ItemStack stack;
    private final IStackHelper stackHelper;

    public CustomJeiIngredient(ItemStack stack, IStackHelper stackHelper) {
        super(Stream.of(new ItemValue(stack)));
        this.stack = stack;
        this.stackHelper = stackHelper;
    }

    @Override
    public boolean test(@Nullable ItemStack input) {
        return stackHelper.isEquivalent(input, this.stack, UidContext.Ingredient);
    }

    @Override
    public boolean isSimple() {
        return false;
    }
}
