package net.nai.additions.entities.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.nai.additions.registry.NAIItems;

import java.util.UUID;

public class SoulEntity extends PathfinderMob {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private String previousCarrier;
    private String previousCarrierID;
    private UUID previousCarrierUUID;
    private int despawnTimer;

    public SoulEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new FlyingMoveControl(this, 10, false);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0f);
        this.previousCarrier = "";
        this.previousCarrierID = "";
        this.previousCarrierUUID = null;
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation flyingPathNavigation = new FlyingPathNavigation(this, level);
        flyingPathNavigation.setCanFloat(true);
        return flyingPathNavigation;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    protected void updateWalkAnimation(float partialTick) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(partialTick * 5f, 1f);
        } else {
            f = 0f;
        }

        this.walkAnimation.update(f, 0.2f);
    }

    @Override
    public void setNoGravity(boolean bl) {
        super.setNoGravity(true);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomFlyingGoal(this, 1.0));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, LivingEntity.class, 6.0f));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    public String getPreviousCarrier() {
        return this.previousCarrier;
    }

    public String getPreviousCarrierID() {
        return this.previousCarrierID;
    }

    public UUID getPreviousCarrierUUID() {
        return this.previousCarrierUUID;
    }

    public int getDespawnTimer() {
        return this.despawnTimer;
    }

    public void setPreviousCarrier(String previousCarrier) {
        this.previousCarrier = previousCarrier;
    }

    public void setPreviousCarrierID(String previousCarrierID) {
        this.previousCarrierID = previousCarrierID;
    }

    public void setPreviousCarrierUUID(UUID previousCarrierUUID) {
        this.previousCarrierUUID = previousCarrierUUID;
    }

    public void setDespawnTimer(int despawnTimer) {
        this.despawnTimer = despawnTimer;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putString("PreviousCarrier", this.previousCarrier);
        compoundTag.putString("PreviousCarrierID", this.previousCarrierID);
        compoundTag.putUUID("PreviousCarrierUUID", this.previousCarrierUUID);
        compoundTag.putInt("DespawnTimer", this.getDespawnTimer());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.previousCarrier = compoundTag.getString("PreviousCarrier");
        this.previousCarrierID = compoundTag.getString("PreviousCarrierID");
        if (this.previousCarrierUUID != null) {
            this.previousCarrierUUID = compoundTag.getUUID("PreviousCarrierUUID");
        }
        this.setDespawnTimer(compoundTag.getInt("DespawnTimer"));
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if (itemStack.is(Items.GLASS_BOTTLE)) {
            CompoundTag prevCarrierNBTData = new CompoundTag();
            CompoundTag idNBTData = new CompoundTag();
            CompoundTag uuidNBTData = new CompoundTag();
            if (previousCarrierUUID != null) {
                prevCarrierNBTData.putString("prev_mob", getPreviousCarrier());
                idNBTData.putString("prev_mob_id", getPreviousCarrierID());
                uuidNBTData.putUUID("prev_mob_uuid", getPreviousCarrierUUID());
            }
            ItemStack itemStack1 = NAIItems.CAPTURED_SOUL.get().getDefaultInstance();
            itemStack1.getOrCreateTagElement("nai_additions.captured_mob").merge(prevCarrierNBTData).merge(idNBTData).merge(uuidNBTData);
            ItemStack itemStack2 = ItemUtils.createFilledResult(itemStack, player, itemStack1);
            player.setItemInHand(interactionHand, itemStack2);
            this.remove(RemovalReason.DISCARDED);
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, interactionHand);
    }

    @Override
    public boolean hurt(DamageSource damageSource, float f) {
        if (damageSource == damageSources().genericKill() && f == Float.MAX_VALUE) {
            return super.hurt(damageSource, f);
        } else {
            return false;
        }
    }

    @Override
    public boolean causeFallDamage(float f, float g, DamageSource damageSource) {
        return false;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    protected void customServerAiStep() {
        if (getDespawnTimer() > 0) {
            int i = getDespawnTimer() - 1;
            if (i <= 0) {
                this.remove(RemovalReason.DISCARDED);
            }
            setDespawnTimer(i);
        }
        super.customServerAiStep();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createLivingAttributes()
                .add(Attributes.FOLLOW_RANGE, 10D)
                .add(Attributes.FLYING_SPEED, 0.3D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D);
    }

}
