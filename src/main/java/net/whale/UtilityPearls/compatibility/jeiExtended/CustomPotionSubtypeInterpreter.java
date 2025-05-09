//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.whale.UtilityPearls.compatibility.jeiExtended;

import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CustomPotionSubtypeInterpreter implements ISubtypeInterpreter<ItemStack> {
    public static final CustomPotionSubtypeInterpreter INSTANCE = new CustomPotionSubtypeInterpreter();

    private CustomPotionSubtypeInterpreter() {
    }

    public @Nullable Object getSubtypeData(ItemStack ingredient, @NotNull UidContext context) {
        PotionContents contents = ingredient.get(DataComponents.POTION_CONTENTS);
        return contents == null ? null : contents.potion().orElse(null);
    }

    public @NotNull String getLegacyStringSubtypeInfo(@NotNull ItemStack ingredient, @NotNull UidContext context) {
        return this.getStringName(ingredient);
    }

    public String getStringName(ItemStack itemStack) {
        if (itemStack.getComponentsPatch().isEmpty()) {
            return "";
        } else {
            PotionContents contents = itemStack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
            String itemDescriptionId = itemStack.getItem().getDescriptionId();
            String potionEffectId = contents.potion().map(Holder::getRegisteredName).orElse("none");
            return itemDescriptionId + ".effect_id." + potionEffectId;
        }
    }
}
