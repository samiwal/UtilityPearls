package net.whale.UtilityPearls.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.whale.UtilityPearls.UtilityPearls;
import net.whale.UtilityPearls.entity.custom.UtilityPearlEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, UtilityPearls.MOD_ID);

   public static final RegistryObject<EntityType<UtilityPearlEntity>> UTILITY_PEARL = registerHelper("utility_pearl");

   public static RegistryObject<EntityType<UtilityPearlEntity>> registerHelper(String name) {
       return ENTITY_TYPES.register(name,
               () -> EntityType.Builder.<UtilityPearlEntity>of(UtilityPearlEntity::new, MobCategory.MISC)
                       .sized(0.3F, 0.3F)
                       .clientTrackingRange(6)
                       .updateInterval(1)
                       .build(ResourceLocation.fromNamespaceAndPath(UtilityPearls.MOD_ID,name).toString()));}

   public static void register(IEventBus eventBus){
       ENTITY_TYPES.register(eventBus);
   }
}
