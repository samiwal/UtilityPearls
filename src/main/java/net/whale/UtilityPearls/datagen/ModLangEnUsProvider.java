package net.whale.UtilityPearls.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.common.data.LanguageProvider;
import net.whale.UtilityPearls.UtilityPearls;

public class ModLangEnUsProvider extends LanguageProvider {
    public ModLangEnUsProvider(PackOutput output) {
        super(output, UtilityPearls.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        for (Potion potion : BuiltInRegistries.POTION) {
            ResourceLocation id = BuiltInRegistries.POTION.getKey(potion);
            if (id == null || id.getNamespace().equals("forge")) continue;
            String path = id.getPath();
            String display = toReadableName(path);

            add("item.utility_pearls.utility_pearl_owner.effect." + path,
                    display + "Utility Pearl (Owner)");

            add("item.utility_pearls.utility_pearl_hit_entity.effect." + path,
                    display + "Utility Pearl (Hit Entity)");
        }
        add("item.utility_pearls.utility_pearl.effect.empty","Utility Pearl");
        add("item.utility_pearls.utility_pearl_owner.effect.empty","Every Utility Pearl (Owner)");
        add("item.utility_pearls.utility_pearl_hit_entity.effect.empty","Every Utility Pearl (Hit Entity)");
    }

    private String toReadableName(String path) {
        String name = path.replace('_', ' ');
        return capitalize(name);
    }

    private String capitalize(String str) {
        String[] words = str.split(" ");
        StringBuilder formattedName = new StringBuilder();
        for (String word : words) {
            formattedName.append(word.substring(0, 1).toUpperCase());
            formattedName.append(word.substring(1).toLowerCase());
            formattedName.append(" ");
        }
        return formattedName.toString();
    }
}
