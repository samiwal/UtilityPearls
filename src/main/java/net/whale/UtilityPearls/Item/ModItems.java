package net.whale.UtilityPearls.Item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.whale.UtilityPearls.Item.custom.UtilityPearlItem;
import net.whale.UtilityPearls.UtilityPearls;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, UtilityPearls.MOD_ID);

    public static final RegistryObject<Item> UTILITY_PEARL_ITEM = ITEMS.register("utility_pearl",
            () -> new UtilityPearlItem(new Item.Properties().stacksTo(64)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
