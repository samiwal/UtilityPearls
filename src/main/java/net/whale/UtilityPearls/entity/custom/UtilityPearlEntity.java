package net.whale.UtilityPearls.entity.custom;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class UtilityPearlEntity extends ThrownEnderpearl {
    private static int LIFETIME_TICKS = 75;
    private Item item;
    private Player player;
    private int lifeTime = 0;
    private int applyTo;
    private PotionContents contents;
    public UtilityPearlEntity(EntityType<? extends ThrownEnderpearl> entityType, Level world, Player player, int applyTo, Item item,PotionContents contents) {
        super(entityType, world);
        this.setNoGravity(true);
        this.player = player;
        this.applyTo = applyTo;
        this.item = item;
        this.contents = contents;
    }
    public UtilityPearlEntity(EntityType<UtilityPearlEntity> entityType, Level level) {
        super(entityType,level);
    }

    @Override
    protected void onHit(HitResult pResult) {
        if (pResult instanceof EntityHitResult entityHitResult) {
            super.onHit(pResult);
            PotionContents contents = getItem().getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
            if(applyTo == 1)
                for (MobEffectInstance effect : contents.getAllEffects()) {
                    player.addEffect(new MobEffectInstance(
                            effect.getEffect(),
                            effect.getDuration()/4,
                            effect.getAmplifier(),
                            effect.isAmbient(),
                            effect.isVisible(),
                            effect.showIcon()
                    ));
                }
            else if(applyTo == 2 && entityHitResult.getEntity() instanceof LivingEntity livingEntity) {
                for (MobEffectInstance effect : contents.getAllEffects()) {
                    livingEntity.addEffect(new MobEffectInstance(
                            effect.getEffect(),
                            effect.getDuration()/4,
                            effect.getAmplifier(),
                            effect.isAmbient(),
                            effect.isVisible(),
                            effect.showIcon()
                    ));
                }
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {

    }

    @Override
    public void tick() {
        super.tick();
        lifeTime++;
        if (lifeTime > LIFETIME_TICKS) {
            returnToPlayer();
            discard();
        }
    }
    private void returnToPlayer() {
        if (this.getOwner() instanceof Player player && item != null) {
            ItemStack newItem = new ItemStack(item);
            newItem.set(DataComponents.POTION_CONTENTS,contents);
            if (!player.getInventory().add(newItem)){
                player.drop(newItem, false);
            }
        }
    }
}
