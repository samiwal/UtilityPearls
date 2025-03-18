package net.whale.UtilityPearls.Item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnderpearlItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.whale.UtilityPearls.entity.ModEntities;
import net.whale.UtilityPearls.entity.custom.UtilityPearlEntity;

public class UtilityPearlItem extends EnderpearlItem {
        public UtilityPearlItem(Properties properties) {
            super(properties);
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
        pPlayer.getCooldowns().addCooldown(this, 5);
        if (!pLevel.isClientSide) {
            UtilityPearlEntity utilityPearl = new UtilityPearlEntity(ModEntities.UTILITY_PEARL.get(), pLevel,pPlayer);
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
