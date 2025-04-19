package net.whale.UtilityPearls.Item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.whale.UtilityPearls.Item.custom.UtilityPearlItem;
import net.whale.UtilityPearls.UtilityPearls;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, UtilityPearls.MOD_ID);

    public static final RegistryObject<Item> UTILITY_PEARL = ITEMS.register("utility_pearl", () -> new UtilityPearlItem(new Item.Properties().stacksTo(16).component(DataComponents.POTION_CONTENTS, PotionContents.EMPTY),0));
    public static final RegistryObject<Item> UTILITY_PEARL_OWNER = ITEMS.register("utility_pearl_owner", () -> new UtilityPearlItem(new Item.Properties().stacksTo(16).component(DataComponents.POTION_CONTENTS, PotionContents.EMPTY),1));
    public static final RegistryObject<Item> UTILITY_PEARL_HIT_ENTITY = ITEMS.register("utility_pearl_hit_entity", () -> new UtilityPearlItem(new Item.Properties().stacksTo(16).component(DataComponents.POTION_CONTENTS, PotionContents.EMPTY),2));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
