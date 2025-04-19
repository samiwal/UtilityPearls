package net.whale.UtilityPearls.datagen;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.whale.UtilityPearls.UtilityPearls;
import net.whale.UtilityPearls.datagen.recipes.HitEntityPearlRecipe;
import net.whale.UtilityPearls.datagen.recipes.OwnerPearlRecipe;

import static net.minecraftforge.registries.ForgeRegistries.RECIPE_SERIALIZERS;

public class ModRecipeSerializers<T extends Recipe<?>> {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(RECIPE_SERIALIZERS, UtilityPearls.MOD_ID);

    public static final RegistryObject<RecipeSerializer<OwnerPearlRecipe>> OWNER_PEARL_RECIPE =
            SERIALIZERS.register("utility_pearl_owner", () -> new SimpleCraftingRecipeSerializer<>(OwnerPearlRecipe::new));
    public static final RegistryObject<RecipeSerializer<HitEntityPearlRecipe>> HIT_ENTITY_PEARL_RECIPE =
            SERIALIZERS.register("utility_pearl_hit_entity", () -> new SimpleCraftingRecipeSerializer<>(HitEntityPearlRecipe::new));

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
