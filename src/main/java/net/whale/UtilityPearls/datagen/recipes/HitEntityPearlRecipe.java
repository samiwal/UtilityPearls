package net.whale.UtilityPearls.datagen.recipes;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.whale.UtilityPearls.Item.ModItems;
import net.whale.UtilityPearls.datagen.ModRecipeSerializers;

public class HitEntityPearlRecipe extends CustomRecipe {
    public HitEntityPearlRecipe(CraftingBookCategory pCategory) {
        super(pCategory);
    }

    public boolean matches(CraftingContainer pInv, Level pLevel) {
        if (pInv.getWidth() == 3 && pInv.getHeight() == 3) {
            for (int i = 0; i < pInv.getWidth(); i++) {
                for (int j = 0; j < pInv.getHeight(); j++) {
                    ItemStack itemstack = pInv.getItem(i + j * pInv.getWidth());
                    if (itemstack.isEmpty()) {
                        return false;
                    }
                    if (i == 0 && j == 0) {
                        if (!itemstack.is(Items.POTION)) {
                            return false;
                        }
                    } else if (!itemstack.is(ModItems.UTILITY_PEARL.get())) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public ItemStack assemble(CraftingContainer p_44513_, HolderLookup.Provider p_333824_) {
        ItemStack itemstack = p_44513_.getItem(0);
        if (!itemstack.is(Items.POTION)) {
            return ItemStack.EMPTY;
        } else {
            ItemStack itemstack1 = new ItemStack(ModItems.UTILITY_PEARL_HIT_ENTITY.get(), 8);
            itemstack1.set(DataComponents.POTION_CONTENTS, itemstack.get(DataComponents.POTION_CONTENTS));
            return itemstack1;
        }
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
