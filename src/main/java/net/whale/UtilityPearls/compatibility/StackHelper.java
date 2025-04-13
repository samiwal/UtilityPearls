package net.whale.UtilityPearls.compatibility;

import mezz.jei.api.helpers.IStackHelper;
import mezz.jei.api.ingredients.subtypes.ISubtypeManager;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public class StackHelper implements IStackHelper {
    private final ISubtypeManager subtypeManager;

    public StackHelper(ISubtypeManager subtypeManager) {
        this.subtypeManager = subtypeManager;
    }

    @Override
    public boolean isEquivalent(@Nullable ItemStack lhs, @Nullable ItemStack rhs, UidContext context) {
        if (lhs == rhs) {
            return true;
        }

        if (lhs == null || rhs == null) {
            return false;
        }

        if (lhs.getItem() != rhs.getItem()) {
            return false;
        }

        String keyLhs = getUniqueIdentifierForStack(lhs, context);
        String keyRhs = getUniqueIdentifierForStack(rhs, context);
        return keyLhs.equals(keyRhs);
    }

    @Override
    public String getUniqueIdentifierForStack(ItemStack stack, UidContext context) {
        String result = getRegistryNameForStack(stack);
        String subtypeInfo = subtypeManager.getSubtypeInfo(stack, context);
        if (!subtypeInfo.isEmpty()) {
            result = result + ':' + subtypeInfo;
        }
        return result;
    }

    public static String getRegistryNameForStack(ItemStack stack) {
        Item item = stack.getItem();
        return RegistryWrapper
                .getRegistry(Registries.ITEM)
                .getRegistryName(item)
                .map(ResourceLocation::toString)
                .orElseThrow(() -> {
                    String stackInfo = getItemStackInfo(stack);
                    return new IllegalStateException("Item has no registry name: " + stackInfo);
                });
    }
    public static String getItemStackInfo(@Nullable ItemStack itemStack){
        if (itemStack == null) {
            return "null";
        }
        Item item = itemStack.getItem();
        RegistryWrapper<Item> itemRegistry = RegistryWrapper.getRegistry(Registries.ITEM);

        final String itemName = itemRegistry.getRegistryName(item)
                .map(ResourceLocation::toString)
                .orElseGet(() -> {
                    if (item instanceof BlockItem) {
                        final String blockName;
                        Block block = ((BlockItem) item).getBlock();
                        if (block == null) {
                            blockName = "null";
                        } else {
                            RegistryWrapper<Block> blockRegistry = RegistryWrapper.getRegistry(Registries.BLOCK);
                            blockName = blockRegistry.getRegistryName(block)
                                    .map(ResourceLocation::toString)
                                    .orElseGet(() -> block.getClass().getName());
                        }
                        return "BlockItem(" + blockName + ")";
                    } else {
                        return item.getClass().getName();
                    }
                });
        String components = itemStack.getComponentsPatch().toString();
        return itemStack + " " + itemName + " nbt:" + components;    }
}
