package net.whale.UtilityPearls.datagen.recipes;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.whale.UtilityPearls.Item.ModItems;
import net.whale.UtilityPearls.datagen.ModRecipeSerializers;
import org.jetbrains.annotations.NotNull;

public class HitEntityPearlRecipe extends CustomRecipe {
    public HitEntityPearlRecipe(CraftingBookCategory pCategory) {
        super(pCategory);
    }

    @Override
    public boolean matches(CraftingInput pInv, Level pLevel) {
        int count = 0;
        int size = pInv.width() -1 + pInv.width() * (pInv.height()-1);
        ItemStack potionStack = pInv.getItem(0);
        if(!potionStack.is(Items.POTION)){
            return false;
        }
        for (int i = 1; i <= size; i++) {
            if(!pInv.getItem(i).isEmpty()) {
                ItemStack itemstack = pInv.getItem(i);
                if (itemstack.is(ModItems.UTILITY_PEARL.get())) {
                    count++;
                }
            }
        }
        if(count == 2 && getInstantaniousPotioneffect(potionStack)) {
            return true;
        }
        return count == 8 && !getInstantaniousPotioneffect(potionStack);
    }
    @Override
    public @NotNull ItemStack assemble(CraftingInput p_44513_, HolderLookup.Provider p_333824_) {
        ItemStack itemstack = p_44513_.getItem(0);
        if (!itemstack.is(Items.POTION)) {
            return ItemStack.EMPTY;
        } else if (getInstantaniousPotioneffect(itemstack)){
            ItemStack itemstack1 = new ItemStack(ModItems.UTILITY_PEARL_HIT_ENTITY.get(), 2);
            itemstack1.set(DataComponents.POTION_CONTENTS, itemstack.get(DataComponents.POTION_CONTENTS));
            return itemstack1;
        } else if (!getInstantaniousPotioneffect(itemstack)) {
            ItemStack itemstack1 = new ItemStack(ModItems.UTILITY_PEARL_HIT_ENTITY.get(), 8);
            itemstack1.set(DataComponents.POTION_CONTENTS, itemstack.get(DataComponents.POTION_CONTENTS));
            return itemstack1;
        }
        return ItemStack.EMPTY;
    }
    public boolean getInstantaniousPotioneffect(ItemStack stack){
        for (MobEffectInstance effect : stack.get(DataComponents.POTION_CONTENTS).getAllEffects()) {
            if (effect.getEffect().value().isInstantenous()) {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth >= 3 && pHeight >= 3;
    }
    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.HIT_ENTITY_PEARL_RECIPE.get();
    }
}
