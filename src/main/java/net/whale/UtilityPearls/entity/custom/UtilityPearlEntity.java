package net.whale.UtilityPearls.entity.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class UtilityPearlEntity extends ThrownEnderpearl {
    private static int LIFETIME_TICKS = 50;
    private MobEffectInstance effect2;
    private MobEffectInstance effect;
    private ItemStack item;
    private int applyTo;
    private Player player;
    private int lifeTime = 0;
    public UtilityPearlEntity(EntityType<? extends ThrownEnderpearl> entityType, Level world, Player player, MobEffectInstance effect,MobEffectInstance effect2, int applyTo,ItemStack item) {
        super(entityType, world);
        this.setNoGravity(true);
        this.effect = effect;
        this.effect2 = effect2;
        this.applyTo = applyTo;
        this.player = player;
        this.item = item;
    }
    public UtilityPearlEntity(EntityType<UtilityPearlEntity> entityType, Level level) {
        super(entityType,level);
    }

    @Override
    protected void onHit(HitResult pResult) {
        if (pResult instanceof EntityHitResult) {
            super.onHit(pResult);
            if (applyTo == 1) {
                player.addEffect(new MobEffectInstance(effect.getEffect(),effect.getDuration(),effect.getAmplifier()));
                if (effect2 != null) {
                    player.addEffect(new MobEffectInstance(effect2));
                }
            } else if (applyTo == 2) {
                if(((EntityHitResult) pResult).getEntity() instanceof LivingEntity livingEntity){
                    livingEntity.addEffect(new MobEffectInstance(effect.getEffect(),effect.getDuration(),effect.getAmplifier()));
                    if (effect2 != null) {
                        livingEntity.addEffect(new MobEffectInstance(effect2));
                    }
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
            if (!player.getInventory().add(new ItemStack(item.getItem()))) {
                player.drop(new ItemStack(item.getItem()), false);
            }
        }
    }
}
