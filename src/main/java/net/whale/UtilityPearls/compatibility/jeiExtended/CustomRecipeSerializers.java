//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.whale.UtilityPearls.compatibility.jeiExtended;

import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class CustomRecipeSerializers {
    @Nullable
    private static CustomRecipeSerializers INSTANCE;
    private final Supplier<RecipeSerializer<?>> jeiShapedRecipeSerializer;

    private CustomRecipeSerializers(Supplier<RecipeSerializer<?>> jeiShapedRecipeSerializer) {
        this.jeiShapedRecipeSerializer = jeiShapedRecipeSerializer;
    }

    public static RecipeSerializer<?> getJeiShapedRecipeSerializer() {
        if (INSTANCE == null) {
            throw new IllegalStateException("Recipe serializer not yet initialized");
        } else {
            return INSTANCE.jeiShapedRecipeSerializer.get();
        }
    }
}
