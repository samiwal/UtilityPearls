package net.whale.UtilityPearls.compatibility;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.ingredients.subtypes.ISubtypeManager;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraftforge.registries.ForgeRegistries;
import net.whale.UtilityPearls.Item.ModItems;
import net.whale.UtilityPearls.UtilityPearls;

@JeiPlugin
public class UtilityPearlJeiPlugin implements IModPlugin {

    private static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(UtilityPearls.MOD_ID, "jei_plugin");
    public static final RecipeType<CraftingRecipe> CRAFTING_RECIPE_TYPE = RecipeType.create("utility_pearls", "crafting", CraftingRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(ModItems.UTILITY_PEARL_OWNER.get(), (stack,level) -> {
            PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);
            if (contents != null) {
                return contents.potion()
                        .map(holder -> ForgeRegistries.POTIONS.getKey(holder.value()).toString())
                        .orElse("none");
            }
            return "none";
        });
        registration.registerSubtypeInterpreter(ModItems.UTILITY_PEARL_HIT_ENTITY.get(), (stack,level) -> {
            PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);
            if (contents != null) {
                return contents.potion()
                        .map(holder -> ForgeRegistries.POTIONS.getKey(holder.value()).toString())
                        .orElse("none");
            }
            return "none";
        });
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        ISubtypeManager subtypeManager = new CustomSubtypeManager(new CustomSubtypeInterpreters());
        CustomStackHelper customStackHelper = new CustomStackHelper(subtypeManager);

        registration.addRecipes(RecipeTypes.CRAFTING, UtilityPearlHitEntityRecipeMaker.createRecipes(customStackHelper));
        registration.addRecipes(RecipeTypes.CRAFTING, UtilityPearlOwnerRecipeMaker.createRecipes(customStackHelper));
    }

}
