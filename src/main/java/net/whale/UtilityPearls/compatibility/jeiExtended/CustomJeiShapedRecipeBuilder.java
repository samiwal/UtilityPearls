//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.whale.UtilityPearls.compatibility.jeiExtended;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import mezz.jei.api.recipe.vanilla.IJeiShapedRecipeBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import org.jetbrains.annotations.NotNull;

public class CustomJeiShapedRecipeBuilder implements IJeiShapedRecipeBuilder {
    private final CraftingBookCategory category;
    private final List<ItemStack> results;
    private final List<String> rows = Lists.newArrayList();
    private final Map<Character, Ingredient> key = Maps.newLinkedHashMap();
    private String group = "";

    public CustomJeiShapedRecipeBuilder(CraftingBookCategory category, List<ItemStack> results) {
        this.category = category;
        this.results = results;
    }

    public @NotNull CustomJeiShapedRecipeBuilder define(@NotNull Character $$0, @NotNull Ingredient $$1) {
        if (this.key.containsKey($$0)) {
            throw new IllegalArgumentException("Symbol '" + $$0 + "' is already defined!");
        } else if ($$0 == ' ') {
            throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
        } else {
            this.key.put($$0, $$1);
            return this;
        }
    }

    public CustomJeiShapedRecipeBuilder pattern(String $$0) {
        if (!this.rows.isEmpty() && $$0.length() != ((String)this.rows.getFirst()).length()) {
            throw new IllegalArgumentException("Pattern must be the same width on every line!");
        } else {
            this.rows.add($$0);
            return this;
        }
    }

    public CustomJeiShapedRecipeBuilder group(String $$0) {
        this.group = $$0;
        return this;
    }

    public CraftingRecipe build() {
        ShapedRecipePattern pattern = ShapedRecipePattern.of(this.key, this.rows);
        return new CustomJeiShapedRecipe(this.group, this.category, pattern, this.results);
    }
}
