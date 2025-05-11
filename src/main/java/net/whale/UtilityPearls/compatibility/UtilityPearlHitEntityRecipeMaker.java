
package net.whale.UtilityPearls.compatibility;

import mezz.jei.api.helpers.IStackHelper;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.*;
import net.whale.UtilityPearls.Item.ModItems;
import net.whale.UtilityPearls.UtilityPearls;

import java.util.List;
import java.util.Optional;

public final class UtilityPearlHitEntityRecipeMaker {

    public static List<RecipeHolder<CraftingRecipe>> createRecipes(IStackHelper stackHelper) {
        String group = "whale.utility_pearl";
        ItemStack pearlStack = new ItemStack(ModItems.UTILITY_PEARL.get());
        Ingredient pearlIngredient = Ingredient.of(pearlStack);

        RegistryWrapper<Potion> potionRegistry = RegistryWrapper.getRegistry(Registries.POTION);
        return potionRegistry.getHolderStream()
                .map(potion -> {
                    ItemStack input = PotionContents.createItemStack(Items.POTION, potion);
                    ItemStack output = PotionContents.createItemStack(ModItems.UTILITY_PEARL_HIT_ENTITY.get(), potion);
                    output.setCount(8);

                    Ingredient potionIngredient = new JeiIngredient(input, stackHelper);
                    NonNullList<Ingredient> inputs = NonNullList.of(Ingredient.EMPTY,
                            potionIngredient, pearlIngredient, pearlIngredient,
                            pearlIngredient, pearlIngredient, pearlIngredient,
                            pearlIngredient, pearlIngredient, pearlIngredient
                    );
                    for (MobEffectInstance effect : input.get(DataComponents.POTION_CONTENTS).getAllEffects()) {
                        if (effect.getEffect().value().isInstantenous()){
                            inputs = NonNullList.of(Ingredient.EMPTY,
                                    potionIngredient,Ingredient.EMPTY,Ingredient.EMPTY,
                                    Ingredient.EMPTY,pearlIngredient,Ingredient.EMPTY,
                                    Ingredient.EMPTY,Ingredient.EMPTY,pearlIngredient);
                            output.setCount(2);
                        }
                    }
                    ResourceLocation id = ResourceLocation.fromNamespaceAndPath(UtilityPearls.MOD_ID, "whale.utility_pearl." + output.getDescriptionId());
                    ShapedRecipePattern pattern = new ShapedRecipePattern(3, 3, inputs, Optional.empty());
                    CraftingRecipe recipe = new ShapedRecipe(group, CraftingBookCategory.MISC, pattern, output);
                    return new RecipeHolder<>(id, recipe);
                })
                .toList();
    }
}
