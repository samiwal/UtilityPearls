package net.whale.UtilityPearls.Item.custom;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnderpearlItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.whale.UtilityPearls.entity.ModEntities;
import net.whale.UtilityPearls.entity.custom.UtilityPearlEntity;

import java.util.List;

public class UtilityPearlItem extends EnderpearlItem {

    private final int applyTo;

    public UtilityPearlItem(Properties properties, int applyTo) {
        super(properties);
        this.applyTo = applyTo;
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack itemstack = super.getDefaultInstance();
        itemstack.set(DataComponents.POTION_CONTENTS, new PotionContents(Potions.POISON));
        return itemstack;
    }
    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> components, TooltipFlag tooltipFlag) {
        PotionContents potioncontents = itemStack.get(DataComponents.POTION_CONTENTS);
        if (potioncontents != null) {
            potioncontents.addPotionTooltip(components::add, 0.25F, tooltipContext.tickRate());
        }
    }
    @Override
    public String getDescriptionId(ItemStack pStack) {
        return Potion.getName(pStack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY).potion(), this.getDescriptionId() + ".effect.");
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand hand) {
        ItemStack itemstack = pPlayer.getItemInHand(hand);
        pLevel.playSound(
                null,
                pPlayer.getX(),
                pPlayer.getY(),
                pPlayer.getZ(),
                SoundEvents.ENDER_PEARL_THROW,
                SoundSource.NEUTRAL,
                0.5F,
                0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F)
        );
        pPlayer.getCooldowns().addCooldown(this, 10);
        if (!pLevel.isClientSide) {
            UtilityPearlEntity utilityPearl = new UtilityPearlEntity(ModEntities.UTILITY_PEARL.get(), pLevel,pPlayer,applyTo,itemstack.getItem(),itemstack.get(DataComponents.POTION_CONTENTS));
            utilityPearl.setPos(pPlayer.getX(),pPlayer.getEyeY(),pPlayer.getZ());
            utilityPearl.setOwner(pPlayer);
            utilityPearl.setItem(itemstack);
            utilityPearl.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 2.5F, 0.0F);
            pLevel.addFreshEntity(utilityPearl);
        }
        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        itemstack.consume(1, pPlayer);
        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }
}
