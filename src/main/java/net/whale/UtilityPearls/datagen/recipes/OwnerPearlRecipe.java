package net.whale.UtilityPearls.datagen.recipes;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.inventory.CraftingContainer;
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
        System.out.println("Recipe Started");
        if (pInv.width() == 3 && pInv.height() == 3) {
            for (int i = 0; i < pInv.width(); i++) {
                for (int j = 0; j < pInv.height(); j++) {
                    ItemStack itemstack = pInv.getItem(i, j);
                    if (itemstack.isEmpty()) {
                        System.out.println("Empty");
                        return false;
                    }
                    if (i == 2 && j == 2) {
                        if (!itemstack.is(Items.POTION)) {
                            System.out.println("No Potion");
                            return false;
                        }
                    } else if (!itemstack.is(ModItems.UTILITY_PEARL.get())) {
                        System.out.println("No Pearl");
                        return false;
                    }
                }
            }
            return true;
        } else {
            System.out.println("Wrong Size");
            return false;
        }
    }
    @Override
    public ItemStack assemble(CraftingInput p_44513_, HolderLookup.Provider p_333824_) {
        ItemStack itemstack = p_44513_.getItem(2 , 2);
        if (!itemstack.is(Items.POTION)) {
            return ItemStack.EMPTY;
        } else {
            ItemStack itemstack1 = new ItemStack(ModItems.UTILITY_PEARL_OWNER.get(), 8);
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
        System.out.println("Serializer Found");
        return ModRecipeSerializers.OWNER_PEARL_RECIPE.get();
    }
}
