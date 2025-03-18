package net.whale.UtilityPearls.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.whale.UtilityPearls.Item.ModItems;

public class UtilityPearlEntity extends ThrownEnderpearl {
    private static final int LIFETIME_TICKS = 70;
    private int lifeTime = 0;
    public UtilityPearlEntity(EntityType<? extends ThrownEnderpearl> entityType, Level world,Player player) {
        super(entityType, world);
        this.setPos(player.getX(), player.getEyeY(), player.getZ());
        this.setNoGravity(true);
        this.noPhysics = true;
    }
    public UtilityPearlEntity(EntityType<UtilityPearlEntity> entityType, Level level) {
        super(entityType,level);
    }

    @Override
    protected void onHit(HitResult pResult) {
        if (pResult instanceof EntityHitResult) {
            super.onHit(pResult);
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
        if (this.getOwner() instanceof Player player) {
            ItemStack pearlStack = new ItemStack(ModItems.UTILITY_PEARL_ITEM.get());
            if (!player.getInventory().add(pearlStack)) {
                player.drop(pearlStack, false);
            }
        }
    }
}
