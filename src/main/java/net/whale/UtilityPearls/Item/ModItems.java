package net.whale.UtilityPearls.Item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.whale.UtilityPearls.Item.custom.UtilityPearlItem;
import net.whale.UtilityPearls.UtilityPearls;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, UtilityPearls.MOD_ID);

    public static final RegistryObject<Item> UTILITY_PEARL = registerPearl("utility_pearl",new MobEffectInstance(MobEffects.POISON,0),0);

    public static final RegistryObject<Item> NIGHT_VISION_PEARL_1 = registerPearl("night_vision_pearl_1", new MobEffectInstance(MobEffects.NIGHT_VISION, 2000), 1);
    public static final RegistryObject<Item> NIGHT_VISION_PEARL_2 = registerPearl("night_vision_pearl_2", new MobEffectInstance(MobEffects.NIGHT_VISION, 2000), 2);

    public static final RegistryObject<Item> SPEED_PEARL_1 = registerPearl("speed_pearl_1", new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300,1), 1);
    public static final RegistryObject<Item> SPEED_PEARL_2 = registerPearl("speed_pearl_2", new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300,1), 2);

    public static final RegistryObject<Item> FIRE_RESISTANCE_PEARL_1 = registerPearl("fire_resistance_pearl_1", new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1500), 1);
    public static final RegistryObject<Item> FIRE_RESISTANCE_PEARL_2 = registerPearl("fire_resistance_pearl_2", new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1500), 2);

    public static final RegistryObject<Item> WATER_BREATHING_PEARL_1 = registerPearl("water_breathing_pearl_1", new MobEffectInstance(MobEffects.WATER_BREATHING, 2000), 1);
    public static final RegistryObject<Item> WATER_BREATHING_PEARL_2 = registerPearl("water_breathing_pearl_2", new MobEffectInstance(MobEffects.WATER_BREATHING, 2000), 2);

    public static final RegistryObject<Item> REGENERATION_PEARL_1 = registerPearl("regeneration_pearl_1", new MobEffectInstance(MobEffects.REGENERATION, 200), 1);
    public static final RegistryObject<Item> REGENERATION_PEARL_2 = registerPearl("regeneration_pearl_2", new MobEffectInstance(MobEffects.REGENERATION, 200), 2);

    public static final RegistryObject<Item> STRENGTH_PEARL_1 = registerPearl("strength_pearl_1", new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300,1), 1);
    public static final RegistryObject<Item> STRENGTH_PEARL_2 = registerPearl("strength_pearl_2", new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300,1), 2);

    public static final RegistryObject<Item> INVISIBILITY_PEARL_1 = registerPearl("invisibility_pearl_1", new MobEffectInstance(MobEffects.INVISIBILITY, 200), 1);
    public static final RegistryObject<Item> INVISIBILITY_PEARL_2 = registerPearl("invisibility_pearl_2", new MobEffectInstance(MobEffects.INVISIBILITY, 200), 2);

    public static final RegistryObject<Item> LUCK_PEARL_1 = registerPearl("luck_pearl_1", new MobEffectInstance(MobEffects.LUCK, 4000), 1);
    public static final RegistryObject<Item> LUCK_PEARL_2 = registerPearl("luck_pearl_2", new MobEffectInstance(MobEffects.LUCK, 4000), 2);

    public static final RegistryObject<Item> TURTLE_MASTER_PEARL_1 = registerPearl("turtle_master_pearl_1", new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 400, 3),new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,1600,3), 1);
    public static final RegistryObject<Item> TURTLE_MASTER_PEARL_2 = registerPearl("turtle_master_pearl_2", new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 400, 3),new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,1600,3), 2);

    public static final RegistryObject<Item> INSTANT_HEALTH_PEARL_1 = registerPearl("instant_health_pearl_1", new MobEffectInstance(MobEffects.HEAL, 1, 2), 1);
    public static final RegistryObject<Item> INSTANT_HEALTH_PEARL_2 = registerPearl("instant_health_pearl_2", new MobEffectInstance(MobEffects.HEAL, 1, 2), 2);

    public static final RegistryObject<Item> INSTANT_DAMAGE_PEARL_1 = registerPearl("instant_damage_pearl_1", new MobEffectInstance(MobEffects.HARM, 1, 1), 1);
    public static final RegistryObject<Item> INSTANT_DAMAGE_PEARL_2 = registerPearl("instant_damage_pearl_2", new MobEffectInstance(MobEffects.HARM, 1, 1), 2);

    public static final RegistryObject<Item> JUMP_BOOST_PEARL_1 = registerPearl("jump_boost_pearl_1", new MobEffectInstance(MobEffects.JUMP, 600, 2), 1);
    public static final RegistryObject<Item> JUMP_BOOST_PEARL_2 = registerPearl("jump_boost_pearl_2", new MobEffectInstance(MobEffects.JUMP, 600, 2), 2);

    public static final RegistryObject<Item> POISON_PEARL_1 = registerPearl("poison_pearl_1", new MobEffectInstance(MobEffects.POISON, 500), 1);
    public static final RegistryObject<Item> POISON_PEARL_2 = registerPearl("poison_pearl_2", new MobEffectInstance(MobEffects.POISON, 500), 2);

    public static final RegistryObject<Item> SLOWNESS_PEARL_1 = registerPearl("slowness_pearl_1", new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 800), 1);
    public static final RegistryObject<Item> SLOWNESS_PEARL_2 = registerPearl("slowness_pearl_2", new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 800), 2);

    public static final RegistryObject<Item> WEAKNESS_PEARL_1 = registerPearl("weakness_pearl_1", new MobEffectInstance(MobEffects.WEAKNESS, 1600), 1);
    public static final RegistryObject<Item> WEAKNESS_PEARL_2 = registerPearl("weakness_pearl_2", new MobEffectInstance(MobEffects.WEAKNESS, 1600), 2);

    public static final RegistryObject<Item> SLOW_FALLING_PEARL_1 = registerPearl("slow_falling_pearl_1", new MobEffectInstance(MobEffects.SLOW_FALLING, 400, 2), 1);
    public static final RegistryObject<Item> SLOW_FALLING_PEARL_2 = registerPearl("slow_falling_pearl_2", new MobEffectInstance(MobEffects.SLOW_FALLING, 400, 2), 2);

    private static RegistryObject<Item> registerPearl(String name, MobEffectInstance effect, int applyTo) {
        return ITEMS.register(name, () -> new UtilityPearlItem(new Item.Properties().stacksTo(16), effect, applyTo));
    }
    private static RegistryObject<Item> registerPearl(String name, MobEffectInstance effect,MobEffectInstance effect2, int applyTo) {
        return ITEMS.register(name, () -> new UtilityPearlItem(new Item.Properties().stacksTo(16), effect,effect2, applyTo));
    }

        public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
