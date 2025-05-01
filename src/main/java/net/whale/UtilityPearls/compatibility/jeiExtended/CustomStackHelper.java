//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.whale.UtilityPearls.compatibility;

import java.util.List;
import java.util.Objects;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IStackHelper;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.api.ingredients.subtypes.ISubtypeManager;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class CustomStackHelper implements IStackHelper {
    private final ISubtypeManager subtypeManager;

    public CustomStackHelper(ISubtypeManager subtypeManager) {
        this.subtypeManager = subtypeManager;
    }

    public boolean isEquivalent(@Nullable ItemStack lhs, @Nullable ItemStack rhs, UidContext context) {
        if (lhs == rhs) {
            return true;
        } else if (lhs != null && rhs != null) {
            if (lhs.getItem() != rhs.getItem()) {
                return false;
            } else {
                Object keyLhs = this.subtypeManager.getSubtypeData(lhs, context);
                Object keyRhs = this.subtypeManager.getSubtypeData(rhs, context);
                return Objects.equals(keyLhs, keyRhs);
            }
        } else {
            return false;
        }
    }

    @Override
    public String getUniqueIdentifierForStack(ItemStack itemStack, UidContext uidContext) {
        return "";
    }

    public Object getUidForStack(ItemStack stack, UidContext context) {
        Item item = stack.getItem();
        Object subtypeData = this.subtypeManager.getSubtypeData(stack, context);
        return subtypeData != null ? List.of(item, subtypeData) : item;
    }

    public Object getUidForStack(ITypedIngredient<ItemStack> typedIngredient, UidContext context) {
        Item item = typedIngredient.getBaseIngredient(VanillaTypes.ITEM_STACK);
        Object subtypeData = this.subtypeManager.getSubtypeData(VanillaTypes.ITEM_STACK, typedIngredient, context);
        return subtypeData != null ? List.of(item, subtypeData) : item;
    }
}
