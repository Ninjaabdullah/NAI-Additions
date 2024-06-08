package net.nai.additions.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.nai.additions.misc.NAITags;
import net.nai.additions.registry.NAIEnchantments;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {

    @Shadow
    @NotNull
    public abstract ItemStack getItemBySlot(EquipmentSlot equipmentSlot);

    @Unique
    private int tickCounter1 = 40;
    @Unique
    private int tickCounter2 = 30;
    @Unique
    private int tickCounter3 = 20;
    @Unique
    private int tickCounter4 = 10;

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType_1, Level level_1) {
        super(entityType_1, level_1);
    }

    // Emission Finder Functionality
    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        ItemStack itemStack = this.getItemBySlot(EquipmentSlot.HEAD);
        int emissionFinderLevel = EnchantmentHelper.getItemEnchantmentLevel(NAIEnchantments.EMISSION_DETECTOR.get(), itemStack);
        if (emissionFinderLevel > 0) {
            BlockPos playerPos = this.blockPosition();
            BlockPos closestBlockPos = null;
            float closestDistance = Float.MAX_VALUE;

            for (int x = -12; x <= 12; x++) {
                for (int y = -12; y <= 12; y++) {
                    for (int z = -12; z <= 12; z++) {
                        BlockPos blockPos = playerPos.offset(x, y, z);
                        BlockState blockState = this.level().getBlockState(blockPos);
                        if (blockState.is(NAITags.EMITTERS)) {
                            float distance = (playerPos.getX() - blockPos.getX()) * (playerPos.getX() - blockPos.getX()) +
                                    (playerPos.getY() - blockPos.getY()) * (playerPos.getY() - blockPos.getY()) +
                                    (playerPos.getZ() - blockPos.getZ()) * (playerPos.getZ() - blockPos.getZ());
                            if (distance < closestDistance) {
                                closestDistance = distance;
                                closestBlockPos = blockPos;
                            }
                        }
                    }
                }
            }

            if (closestBlockPos != null) {
                if (closestDistance <= 144 && closestDistance > 81) {
                    tickCounter1++;
                    if (tickCounter1 >= 40) { // 2 seconds (40 ticks)
                        this.playSound(SoundEvents.NOTE_BLOCK_BIT.value(), this.getSoundVolume(), 1.15f);
                        tickCounter1 = 0;
                    }
                } else if (closestDistance <= 81 && closestDistance > 36) {
                    tickCounter2++;
                    if (tickCounter2 >= 30) { // 1.5 seconds (30 ticks)
                        this.playSound(SoundEvents.NOTE_BLOCK_BIT.value(), this.getSoundVolume(), 1.2f);
                        tickCounter2 = 0;
                    }
                } else if (closestDistance <= 36 && closestDistance > 9) {
                    tickCounter3++;
                    if (tickCounter3 >= 20) { // 1 second (20 ticks)
                        this.playSound(SoundEvents.NOTE_BLOCK_BIT.value(), this.getSoundVolume(), 1.25f);
                        tickCounter3 = 0;
                    }
                } else if (closestDistance <= 9) {
                    tickCounter4++;
                    if (tickCounter4 >= 10) { // 0.5 seconds (10 ticks)
                        this.playSound(SoundEvents.NOTE_BLOCK_BIT.value(), this.getSoundVolume(), 1.3f);
                        tickCounter4 = 0;
                    }
                }
            }
        }
    }
}
