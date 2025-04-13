package net.whale.UtilityPearls;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.whale.UtilityPearls.Item.ModItems;
import net.whale.UtilityPearls.datagen.ModRecipeSerializers;
import net.whale.UtilityPearls.entity.ModEntities;
import org.slf4j.Logger;


@Mod(UtilityPearls.MOD_ID)
public class UtilityPearls
{
    public static final String MOD_ID = "utility_pearls";
    private static final Logger LOGGER = LogUtils.getLogger();

    public UtilityPearls(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        ModItems.register(modEventBus);
        ModEntities.register(modEventBus);
        ModRecipeSerializers.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.COMBAT || event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            for (Potion potion : BuiltInRegistries.POTION) {
                ItemStack hitEntityStack = new ItemStack(ModItems.UTILITY_PEARL_HIT_ENTITY.get());
                hitEntityStack.set(DataComponents.POTION_CONTENTS, new PotionContents(BuiltInRegistries.POTION.wrapAsHolder(potion)));
                event.accept(hitEntityStack);

                ItemStack ownerStack = new ItemStack(ModItems.UTILITY_PEARL_OWNER.get());
                ownerStack.set(DataComponents.POTION_CONTENTS,new PotionContents(BuiltInRegistries.POTION.wrapAsHolder(potion)));
                event.accept(ownerStack);
            }
            event.accept(ModItems.UTILITY_PEARL);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.UTILITY_PEARL.get(), ThrownItemRenderer::new);
            Minecraft.getInstance().getItemColors().register(
                    (stack, tintIndex) -> {
                        if (tintIndex == 1 && stack.has(DataComponents.POTION_CONTENTS)) {
                            return stack.get(DataComponents.POTION_CONTENTS).getColor();
                        }
                        return -1;
                    },
                    ModItems.UTILITY_PEARL_HIT_ENTITY.get(),
                    ModItems.UTILITY_PEARL_OWNER.get()
            );
        }
    }
}
