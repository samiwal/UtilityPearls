package net.whale.UtilityPearls.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.whale.UtilityPearls.Item.ModItems;

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
        registerUtilityPearl1Recipe(pRecipeOutput,ModItems.INVISIBILITY_PEARL_1.get(), PotionContents.createItemStack(Items.POTION,Potions.INVISIBILITY).getItem());
        registerUtilityPearl2Recipe(pRecipeOutput,ModItems.INVISIBILITY_PEARL_2.get(), PotionContents.createItemStack(Items.POTION,Potions.INVISIBILITY).getItem());

        registerUtilityPearl1Recipe(pRecipeOutput, ModItems.NIGHT_VISION_PEARL_1.get(), PotionContents.createItemStack(Items.POTION, Potions.NIGHT_VISION).getItem());
        registerUtilityPearl2Recipe(pRecipeOutput, ModItems.NIGHT_VISION_PEARL_2.get(), PotionContents.createItemStack(Items.POTION, Potions.NIGHT_VISION).getItem());

        registerUtilityPearl1Recipe(pRecipeOutput, ModItems.SPEED_PEARL_1.get(), PotionContents.createItemStack(Items.POTION, Potions.SWIFTNESS).getItem());
        registerUtilityPearl2Recipe(pRecipeOutput, ModItems.SPEED_PEARL_2.get(), PotionContents.createItemStack(Items.POTION, Potions.SWIFTNESS).getItem());

        registerUtilityPearl1Recipe(pRecipeOutput, ModItems.FIRE_RESISTANCE_PEARL_1.get(), PotionContents.createItemStack(Items.POTION, Potions.FIRE_RESISTANCE).getItem());
        registerUtilityPearl2Recipe(pRecipeOutput, ModItems.FIRE_RESISTANCE_PEARL_2.get(), PotionContents.createItemStack(Items.POTION, Potions.FIRE_RESISTANCE).getItem());

        registerUtilityPearl1Recipe(pRecipeOutput, ModItems.WATER_BREATHING_PEARL_1.get(), PotionContents.createItemStack(Items.POTION, Potions.WATER_BREATHING).getItem());
        registerUtilityPearl2Recipe(pRecipeOutput, ModItems.WATER_BREATHING_PEARL_2.get(), PotionContents.createItemStack(Items.POTION, Potions.WATER_BREATHING).getItem());

        registerUtilityPearl1Recipe(pRecipeOutput, ModItems.REGENERATION_PEARL_1.get(), PotionContents.createItemStack(Items.POTION, Potions.REGENERATION).getItem());
        registerUtilityPearl2Recipe(pRecipeOutput, ModItems.REGENERATION_PEARL_2.get(), PotionContents.createItemStack(Items.POTION, Potions.REGENERATION).getItem());

        registerUtilityPearl1Recipe(pRecipeOutput, ModItems.STRENGTH_PEARL_1.get(), PotionContents.createItemStack(Items.POTION, Potions.STRENGTH).getItem());
        registerUtilityPearl2Recipe(pRecipeOutput, ModItems.STRENGTH_PEARL_2.get(), PotionContents.createItemStack(Items.POTION, Potions.STRENGTH).getItem());

        registerUtilityPearl1Recipe(pRecipeOutput, ModItems.LUCK_PEARL_1.get(), PotionContents.createItemStack(Items.POTION, Potions.LUCK).getItem());
        registerUtilityPearl2Recipe(pRecipeOutput, ModItems.LUCK_PEARL_2.get(), PotionContents.createItemStack(Items.POTION, Potions.LUCK).getItem());

        registerUtilityPearl1Recipe(pRecipeOutput, ModItems.TURTLE_MASTER_PEARL_1.get(), PotionContents.createItemStack(Items.POTION, Potions.TURTLE_MASTER).getItem());
        registerUtilityPearl2Recipe(pRecipeOutput, ModItems.TURTLE_MASTER_PEARL_2.get(), PotionContents.createItemStack(Items.POTION, Potions.TURTLE_MASTER).getItem());

        registerUtilityPearl1Recipe(pRecipeOutput, ModItems.INSTANT_HEALTH_PEARL_1.get(), PotionContents.createItemStack(Items.POTION, Potions.HEALING).getItem());
        registerUtilityPearl2Recipe(pRecipeOutput, ModItems.INSTANT_HEALTH_PEARL_2.get(), PotionContents.createItemStack(Items.POTION, Potions.HEALING).getItem());

        registerUtilityPearl1Recipe(pRecipeOutput, ModItems.INSTANT_DAMAGE_PEARL_1.get(), PotionContents.createItemStack(Items.POTION, Potions.HARMING).getItem());
        registerUtilityPearl2Recipe(pRecipeOutput, ModItems.INSTANT_DAMAGE_PEARL_2.get(), PotionContents.createItemStack(Items.POTION, Potions.HARMING).getItem());

        registerUtilityPearl1Recipe(pRecipeOutput, ModItems.JUMP_BOOST_PEARL_1.get(), PotionContents.createItemStack(Items.POTION, Potions.LEAPING).getItem());
        registerUtilityPearl2Recipe(pRecipeOutput, ModItems.JUMP_BOOST_PEARL_2.get(), PotionContents.createItemStack(Items.POTION, Potions.LEAPING).getItem());

        registerUtilityPearl1Recipe(pRecipeOutput, ModItems.POISON_PEARL_1.get(), PotionContents.createItemStack(Items.POTION, Potions.POISON).getItem());
        registerUtilityPearl2Recipe(pRecipeOutput, ModItems.POISON_PEARL_2.get(), PotionContents.createItemStack(Items.POTION, Potions.POISON).getItem());

        registerUtilityPearl1Recipe(pRecipeOutput, ModItems.SLOWNESS_PEARL_1.get(), PotionContents.createItemStack(Items.POTION, Potions.SLOWNESS).getItem());
        registerUtilityPearl2Recipe(pRecipeOutput, ModItems.SLOWNESS_PEARL_2.get(), PotionContents.createItemStack(Items.POTION, Potions.SLOWNESS).getItem());

        registerUtilityPearl1Recipe(pRecipeOutput, ModItems.WEAKNESS_PEARL_1.get(), PotionContents.createItemStack(Items.POTION, Potions.WEAKNESS).getItem());
        registerUtilityPearl2Recipe(pRecipeOutput, ModItems.WEAKNESS_PEARL_2.get(), PotionContents.createItemStack(Items.POTION, Potions.WEAKNESS).getItem());

        registerUtilityPearl1Recipe(pRecipeOutput, ModItems.SLOW_FALLING_PEARL_1.get(), PotionContents.createItemStack(Items.POTION, Potions.SLOW_FALLING).getItem());
        registerUtilityPearl2Recipe(pRecipeOutput, ModItems.SLOW_FALLING_PEARL_2.get(), PotionContents.createItemStack(Items.POTION, Potions.SLOW_FALLING).getItem());

    }
    public static void registerUtilityPearl1Recipe(RecipeOutput recipeOutput, Item utilityPearl, Item potion) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, utilityPearl, 8)
                .define('E', Items.ENDER_PEARL)
                .define('P', potion)
                .pattern("EEE")
                .pattern("EEE")
                .pattern("EEP")
                .unlockedBy("never_unlock", has(Items.BARRIER))
                .save(recipeOutput);
    }
    public static void registerUtilityPearl2Recipe(RecipeOutput recipeOutput, Item utilityPearl, Item potion) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, utilityPearl, 8)
                .define('E', Items.ENDER_PEARL)
                .define('P', potion)
                .pattern("PEE")
                .pattern("EEE")
                .pattern("EEE")
                .unlockedBy("never_unlock", has(Items.BARRIER))
                .save(recipeOutput);
    }
}
