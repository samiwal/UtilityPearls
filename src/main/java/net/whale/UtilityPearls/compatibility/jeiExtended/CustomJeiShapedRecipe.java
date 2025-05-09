//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.whale.UtilityPearls.compatibility.jeiExtended;

import java.util.List;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CustomJeiShapedRecipe implements CraftingRecipe {
    private final ShapedRecipePattern pattern;
    private final List<ItemStack> results;
    private final String group;
    private final CraftingBookCategory category;

    public CustomJeiShapedRecipe(String group, CraftingBookCategory category, ShapedRecipePattern pattern, List<ItemStack> results) {
        this.group = group;
        this.category = category;
        this.pattern = pattern;
        this.results = results;
    }

    public @NotNull RecipeSerializer<?> getSerializer() {
        return CustomRecipeSerializers.getJeiShapedRecipeSerializer();
    }

    public @NotNull String getGroup() {
        return this.group;
    }

    public @NotNull CraftingBookCategory category() {
        return this.category;
    }

    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider registries) {
        return this.results.getFirst();
    }

    public @NotNull NonNullList<Ingredient> getIngredients() {
        return this.pattern.ingredients();
    }

    public boolean showNotification() {
        return false;
    }

    public boolean canCraftInDimensions(int width, int height) {
        return width >= this.pattern.width() && height >= this.pattern.height();
    }
    public boolean matches(@NotNull CraftingInput input, @NotNull Level level) {
        return this.pattern.matches(input);
    }

    public @NotNull ItemStack assemble(@NotNull CraftingInput input, HolderLookup.@NotNull Provider registries) {
        return this.getResultItem(registries).copy();
    }
    public boolean isIncomplete() {
        NonNullList<Ingredient> nonNullList = this.getIngredients();
        return nonNullList.isEmpty() || nonNullList.stream().filter((ingredient) -> !ingredient.isEmpty()).anyMatch((ingredient) -> ingredient.getItems().length == 0);
    }
}
