package net.whale.UtilityPearls.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.whale.UtilityPearls.Item.ModItems;
import net.whale.UtilityPearls.UtilityPearls;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput p_248933_, CompletableFuture<HolderLookup.Provider> p_333797_) {
        super(p_248933_, p_333797_);
    }
    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.UTILITY_PEARL.get(),1)
                .requires(Items.ENDER_PEARL)
                .unlockedBy(getHasName(Items.ENDER_PEARL),has(Items.ENDER_PEARL))
                .save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.ENDER_PEARL,1)
                .requires(ModItems.UTILITY_PEARL.get())
                .unlockedBy(getHasName(ModItems.UTILITY_PEARL.get()),has(ModItems.UTILITY_PEARL.get()))
                .save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.UTILITY_PEARL.get(),1)
                .requires(ModItems.UTILITY_PEARL_OWNER.get())
                .unlockedBy(getHasName(ModItems.UTILITY_PEARL_OWNER.get()),has(ModItems.UTILITY_PEARL.get()))
                .save(pRecipeOutput, ResourceLocation.fromNamespaceAndPath(UtilityPearls.MOD_ID, "utility_pearl_from_owner"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.UTILITY_PEARL.get(),1)
                .requires(ModItems.UTILITY_PEARL_HIT_ENTITY.get())
                .unlockedBy(getHasName(ModItems.UTILITY_PEARL_HIT_ENTITY.get()),has(ModItems.UTILITY_PEARL.get()))
                .save(pRecipeOutput, ResourceLocation.fromNamespaceAndPath(UtilityPearls.MOD_ID, "utility_pearl_from_hit_entity"));
    }
}
