package net.whale.UtilityPearls.compatibility;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.whale.UtilityPearls.Item.ModItems;
import net.whale.UtilityPearls.UtilityPearls;
import net.whale.UtilityPearls.compatibility.jeiExtended.CustomPotionSubtypeInterpreter;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class UtilityPearlJeiPlugin implements IModPlugin {

    private static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(UtilityPearls.MOD_ID, "jei_plugin");
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(ModItems.UTILITY_PEARL_OWNER.get(), CustomPotionSubtypeInterpreter.INSTANCE);
        registration.registerSubtypeInterpreter(ModItems.UTILITY_PEARL_HIT_ENTITY.get(), CustomPotionSubtypeInterpreter.INSTANCE);
    }
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(RecipeTypes.CRAFTING, UtilityPearlHitEntityRecipeMaker.createRecipes());
        registration.addRecipes(RecipeTypes.CRAFTING, UtilityPearlOwnerRecipeMaker.createRecipes());
    }

}
