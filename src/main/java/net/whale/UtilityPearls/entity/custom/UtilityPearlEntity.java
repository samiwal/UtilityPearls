package net.whale.UtilityPearls.entity.custom;

import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.whale.UtilityPearls.command.UtilityPearlData;

public class UtilityPearlEntity extends ThrownEnderpearl {
    private Item item;
    private Player player;
    private Level world;
    private int lifeTime = 0;
    private int applyTo;
    private PotionContents contents;
    public UtilityPearlEntity(EntityType<? extends ThrownEnderpearl> entityType, Level world, Player player, int applyTo, Item item,PotionContents contents) {
        super(entityType, world);
        this.setNoGravity(true);
        this.world = world;
        this.player = player;
        this.applyTo = applyTo;
        this.item = item;
        this.contents = contents;
    }
    public UtilityPearlEntity(EntityType<UtilityPearlEntity> entityType, Level level) {
        super(entityType,level);
        this.world = level;
    }

    @Override
    protected void onHit(HitResult pResult) {
        if (pResult instanceof EntityHitResult entityHitResult) {
            super.onHit(pResult);
            onHitEntity(entityHitResult);
        }
    }
    @Override
    protected void onHitBlock(BlockHitResult result) {
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        PotionContents contents = getItem().getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
        if (applyTo == 1)
            for (MobEffectInstance effect : contents.getAllEffects()) {
                if (effect.getEffect().value().isInstantenous()) {
                    effect.getEffect().value().applyInstantenousEffect(this, player, player, effect.getAmplifier(), 0.5);
                } else {
                    player.addEffect(new MobEffectInstance(
                            effect.getEffect(),
                            effect.getDuration() / 4,
                            effect.getAmplifier(),
                            effect.isAmbient(),
                            effect.isVisible(),
                            effect.showIcon()
                    ));
                }
            }
        else if (applyTo == 2 && entityHitResult.getEntity() instanceof LivingEntity livingEntity) {
            for (MobEffectInstance effect : contents.getAllEffects()) {
                if (effect.getEffect().value().isInstantenous()) {
                    effect.getEffect().value().applyInstantenousEffect(this, player, livingEntity, effect.getAmplifier(), 0.5);
                } else {
                    livingEntity.addEffect(new MobEffectInstance(
                            effect.getEffect(),
                            effect.getDuration() / 4,
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
    public void tick() {
        super.tick();
        if(!world.isClientSide) {
            Vec3 start = this.position();
            Vec3 end = start.add(this.getDeltaMovement());

            HitResult hitResult = ProjectileUtil.getEntityHitResult(this.level(), this, start, end, this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0),
                    entity -> !entity.isSpectator() && entity.isAlive() && entity.isPickable() && entity != this.getOwner()
            );

            if (hitResult != null && hitResult.getType() == HitResult.Type.ENTITY) {
                EntityHitResult entityHitResult = (EntityHitResult) hitResult;
                onHit(entityHitResult);
            }
            lifeTime++;
            if (lifeTime > UtilityPearlData.get(((ServerLevel) world)).getLifetime()) {
                returnToPlayer();
                discard();
            }
        }
    }
    private void returnToPlayer() {
        if (this.getOwner() instanceof Player player && item != null) {
            ItemStack newItem = new ItemStack(item);
            newItem.set(DataComponents.POTION_CONTENTS,contents);
            if(!player.hasInfiniteMaterials()) {
                if (!player.getInventory().add(newItem)) {
                    player.drop(newItem, false);
                } else {
                    level().playSound(
                            null,
                            this.getOwner().getX(), this.getOwner().getY() + 0.5, this.getOwner().getZ(),
                            SoundEvents.ITEM_PICKUP,
                            SoundSource.PLAYERS,
                            0.2F,
                            ((level().random.nextFloat() - level().random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                }
            }
        }
    }
}
