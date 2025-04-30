package net.whale.UtilityPearls.datagen.recipes;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.whale.UtilityPearls.Item.ModItems;
import net.whale.UtilityPearls.datagen.ModRecipeSerializers;

public class OwnerPearlRecipe extends CustomRecipe {
    public OwnerPearlRecipe(CraftingBookCategory pCategory) {
        super(pCategory);
    }
    @Override
    public boolean matches(CraftingInput pInv, Level pLevel) {
        int count = 0;
        int size = pInv.width() -1 + pInv.width() * (pInv.height()-1);
        ItemStack potionStack = pInv.getItem(size);
        if(!potionStack.is(Items.POTION)){
            return false;
        }
        for (int i = 0; i <= size; i++) {
            if(!pInv.getItem(i).isEmpty()) {
                ItemStack itemstack = pInv.getItem(i);
                if(i==size){
                } else if (itemstack.is(ModItems.UTILITY_PEARL.get())) {
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
    public ItemStack assemble(CraftingInput p_44513_, HolderLookup.Provider p_333824_) {
        ItemStack itemstack = p_44513_.getItem(p_44513_.width()-1 , p_44513_.height()-1);
        if (!itemstack.is(Items.POTION)) {
            return ItemStack.EMPTY;
        } else if (getInstantaniousPotioneffect(itemstack)){
            ItemStack itemstack1 = new ItemStack(ModItems.UTILITY_PEARL_OWNER.get(), 2);
            itemstack1.set(DataComponents.POTION_CONTENTS, itemstack.get(DataComponents.POTION_CONTENTS));
            return itemstack1;
        } else if (!getInstantaniousPotioneffect(itemstack)) {
            ItemStack itemstack1 = new ItemStack(ModItems.UTILITY_PEARL_OWNER.get(), 8);
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
        return ModRecipeSerializers.OWNER_PEARL_RECIPE.get();
    }
}
