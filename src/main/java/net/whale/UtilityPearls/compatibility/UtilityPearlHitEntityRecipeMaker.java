package net.whale.UtilityPearls.compatibility;

import net.minecraft.core.Registry;
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
import net.whale.UtilityPearls.compatibility.jeiExtended.CustomJeiShapedRecipeBuilder;
import net.whale.UtilityPearls.compatibility.jeiExtended.CustomRegistryUtil;

import java.util.List;

public final class UtilityPearlHitEntityRecipeMaker {
    public static List<RecipeHolder<CraftingRecipe>> createRecipes() {
        String group = "whale.utility_pearl";
        ItemStack pearlStack = new ItemStack(ModItems.UTILITY_PEARL.get());
        Ingredient pearlIngredient = Ingredient.of(pearlStack);
        Registry<Potion> potionRegistry = CustomRegistryUtil.getRegistry(Registries.POTION);
        return potionRegistry.holders().map(potion -> {
                    ItemStack input = PotionContents.createItemStack(Items.POTION, potion);
                    ItemStack output = PotionContents.createItemStack(ModItems.UTILITY_PEARL_HIT_ENTITY.get(), potion);
                    Ingredient potionIngredient = Ingredient.of(input);
                    CraftingRecipe recipe = new CustomJeiShapedRecipeBuilder(CraftingBookCategory.MISC,List.of(output)).group(group).define('a', pearlIngredient).define('p', potionIngredient).pattern("paa").pattern("aaa").pattern("aaa").build();
                    output.setCount(8);
                    for (MobEffectInstance effect : input.get(DataComponents.POTION_CONTENTS).getAllEffects()) {
                        if (effect.getEffect().value().isInstantenous()) {
                            recipe = new CustomJeiShapedRecipeBuilder(CraftingBookCategory.MISC,List.of(output)).group(group).define('a', pearlIngredient).define('p', potionIngredient).pattern("p  ").pattern(" a ").pattern("  a").build();
                            output.setCount(2);
                        }
                    }
                    ResourceLocation id = ResourceLocation.fromNamespaceAndPath(UtilityPearls.MOD_ID, "whale.utility_pearl." + output.getDescriptionId());
                    return new RecipeHolder<>(id, recipe);
                })
                .toList();
    }
}
