package net.whale.UtilityPearls.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.whale.UtilityPearls.entity.custom.UtilityPearlEntity;
import net.whale.UtilityPearls.UtilityPearls;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, UtilityPearls.MOD_ID);

    public static final RegistryObject<EntityType<UtilityPearlEntity>> UTILITY_PEARL = ENTITY_TYPES.register("utility_pearl",
            () -> EntityType.Builder.<UtilityPearlEntity>of(UtilityPearlEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build(ResourceLocation.fromNamespaceAndPath(UtilityPearls.MOD_ID, "utility_pearl").toString()));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
