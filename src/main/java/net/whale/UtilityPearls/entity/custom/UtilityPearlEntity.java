package net.whale.UtilityPearls.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.*;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import net.whale.UtilityPearls.command.UtilityPearlData;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Predicate;

public class UtilityPearlEntity extends ThrownEnderpearl {
    private static final TicketType<UtilityPearlEntity> PEARL_TICKET = TicketType.create("utility_pearl", (a,b) -> 0);
    private final Set<ChunkPos> loadedChunks = new HashSet<>();
    private Item item;
    private Level world;
    private int applyTo;
    private PotionContents contents;
    private Vec3 newPos;
    private Entity owner;
    private boolean doDamage;
    public UtilityPearlEntity(EntityType<? extends ThrownEnderpearl> entityType, Level world,Entity owner, int applyTo, Item item,PotionContents contents) {
        super(entityType, world);
        this.setNoGravity(true);
        this.world = world;
        this.applyTo = applyTo;
        this.item = item;
        this.contents = contents;
        this.owner = owner;

    }
    public UtilityPearlEntity(EntityType<UtilityPearlEntity> entityType, Level level) {
        super(entityType,level);
        this.world = level;
    }

    @Override
    protected void onHit(@NotNull HitResult pResult) {
        if (!(pResult instanceof EntityHitResult hitRes)) {
            return;
        }
        Entity hitEntity = hitRes.getEntity();
        if (owner == null || hitEntity == owner) {
            return;
        }
        if (owner instanceof ServerPlayer player && this.level() instanceof ServerLevel server) {
            onHitEntityNew(hitRes);
            if (player.isSleeping() && player.getSleepingPos().isPresent()) {
                player.stopSleepInBed(true, true);
            }
            if (player.isPassenger()) {
                player.stopRiding();
            }
            var evt = net.minecraftforge.event.ForgeEventFactory.onEnderPearlLand(
                    player, this.getX(), this.getY(), this.getZ(),
                    this, 5.0F, pResult);
            if (evt.isCanceled()) {
                discard();
                return;
            }
            if (server.getRandom().nextFloat() < 0.05F &&
                    server.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING)) {
                Endermite mite = EntityType.ENDERMITE.create(server);
                if (mite != null) {
                    mite.moveTo(player.getX(), player.getY(), player.getZ(),
                            player.getYRot(), player.getXRot());
                    server.addFreshEntity(mite);
                }
            }
            player.resetFallDistance();
            doDamage = true;
            Vec3 tpPosition = hitRes.getEntity().position().subtract(owner.getLookAngle().normalize().scale(1.5)).add(0,1.0,0);
            player.teleportTo(tpPosition.x,tpPosition.y,tpPosition.z);
            server.playSound(null,
                    this.getX(), this.getY(), this.getZ(),
                    SoundEvents.PLAYER_TELEPORT,
                    SoundSource.PLAYERS,
                    1.0F, 1.0F
            );
        }
    }
    @Override
    protected void onHitBlock(@NotNull BlockHitResult result) {}

    @Override
    protected void onHitEntity(EntityHitResult pResult) {}

    protected void onHitEntityNew(@NotNull EntityHitResult entityHitResult) {
        PotionContents contents = getItem().getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
        if (applyTo == 1) {
            for (MobEffectInstance effect : contents.getAllEffects()) {
                if (effect.getEffect().value().isInstantenous() && owner instanceof LivingEntity entity) {
                    effect.getEffect().value().applyInstantenousEffect(this, owner, entity, effect.getAmplifier(), 1);
                } else {
                    if (owner instanceof LivingEntity entity) entity.addEffect(new MobEffectInstance(
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
        else if (applyTo == 2 && entityHitResult.getEntity() instanceof LivingEntity livingEntity) {
            for (MobEffectInstance effect : contents.getAllEffects()) {
                if (effect.getEffect().value().isInstantenous()) {
                        effect.getEffect().value().applyInstantenousEffect(this, owner, livingEntity, effect.getAmplifier(), 1);
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
        if(doDamage){
            if(world.isClientSide){
                discard();
            } else {
                owner.hurt(this.damageSources().mobProjectile(this, (LivingEntity) owner), 7.0F);
                doDamage = false;
                discard();
            }
        } else {
            super.tick();
            if (!world.isClientSide) {
                if (owner == null){
                    if(getOwner() == null){
                        discard();
                        return;
                    } else{
                        owner = getOwner();
                    }
                }
                if (this.tickCount >= UtilityPearlData.get(((ServerLevel) world)).getLifetime()) {
                    returnToPlayer();
                } else if (!this.world.dimension().equals(owner.level().dimension())) {
                    returnToPlayer();
                }
                checkEntityCollision();
                loadChunksAhead();
            }
        }
    }

    @Override
    protected @NotNull ProjectileDeflection hitTargetOrDeflectSelf(@NotNull HitResult p_329816_) {
        return ProjectileDeflection.NONE;
    }

    private void loadChunksAhead() {
        if (!(world instanceof ServerLevel serverLevel)) return;
        ChunkPos current = new ChunkPos(this.blockPosition());
        Vec3 dir = this.getDeltaMovement().normalize().scale(5);
        BlockPos future = BlockPos.containing(this.position().add(dir));
        ChunkPos futureChunk = new ChunkPos(future);

        List<ChunkPos> toLoad = new ArrayList<>();
        toLoad.add(current);
        toLoad.add(futureChunk);

        for (ChunkPos pos : toLoad) {
            if (loadedChunks.add(pos)) {
                serverLevel.getChunkSource().addRegionTicket(PEARL_TICKET, pos, 2, this);
            }
        }
    }
    private void unloadChunks() {
        if (!(world instanceof ServerLevel serverLevel)) return;

        ServerChunkCache chunkSource = serverLevel.getChunkSource();
        for (ChunkPos pos : loadedChunks) {
            chunkSource.removeRegionTicket(PEARL_TICKET, pos, 2, this);
        }
        loadedChunks.clear();
    }
    @Override
    public boolean canChangeDimensions(@NotNull Level p_343286_, @NotNull Level p_343504_) {
        return false;
    }
    private void checkEntityCollision() {
        if(this.newPos == null){
            this.newPos = this.position();
        }
        Vec3 oldPos = this.newPos;
        this.newPos = this.position();
        EntityHitResult entityhitresult = getModEntityHitResult(this.level(),this,oldPos,this.newPos,new AABB(oldPos,this.newPos).inflate(1.5), e -> !e.isSpectator() && e.isPickable() && e != this.owner,0.25F);
        if(entityhitresult != null){
            onHit(entityhitresult);
        }
    }
    @Override
    public void remove(RemovalReason p_146834_) {
        unloadChunks();
        super.remove(p_146834_);
    }
    private void returnToPlayer() {
        if (this.owner instanceof Player sameplayer && item != null) {
            ItemStack newItem = new ItemStack(item);
            newItem.set(DataComponents.POTION_CONTENTS,contents);
            if(!sameplayer.hasInfiniteMaterials()) {
                if (!sameplayer.getInventory().add(newItem)) {
                    sameplayer.drop(newItem, false);
                } else {
                    level().playSound(
                            null,
                            this.owner.getX(), this.owner.getY() + 0.5, this.owner.getZ(),
                            SoundEvents.ITEM_PICKUP,
                            SoundSource.PLAYERS,
                            0.2F,
                            ((level().random.nextFloat() - level().random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                }
            }
        }
        discard();
    }
    @Nullable
    public static EntityHitResult getModEntityHitResult(
            Level p_150176_, Entity p_150177_, Vec3 p_150178_, Vec3 p_150179_, AABB p_150180_, Predicate<Entity> p_150181_, float p_150182_
    ) {
        double d0 = Double.MAX_VALUE;
        Entity entity = null;
        for (Entity entity1 : p_150176_.getEntities(p_150177_, p_150180_, p_150181_)) {
            ModAABB aabb = new ModAABB(entity1.getBoundingBox().getMinPosition(),entity1.getBoundingBox().getMaxPosition());
            aabb.inflate(p_150182_);
            Optional<Vec3> optional = aabb.clip(p_150178_, p_150179_);
            if (optional.isPresent()) {
                double d1 = p_150178_.distanceToSqr(optional.get());
                if (d1 < d0) {
                    entity = entity1;
                    d0 = d1;
                }
            }
            for (Vec3 point : aabb.getPossibleHitPoints(p_150178_,p_150179_)){
                Optional<Vec3> optional1 = aabb.isPointInEntity(point);
                if(optional1.isPresent()){
                    double d1 = p_150178_.distanceToSqr(optional1.get());
                    if (d1 < d0) {
                        entity = entity1;
                        d0 = d1;
                    }
                }
            }
        }
        if(entity != null){
            return new EntityHitResult(entity);
        }
        return null;
    }
}
