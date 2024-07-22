package net.nai.additions.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
/**
* This class fixes <a herf="https://bugs.mojang.com/browse/MC-248272">MC-248272</a> for this version of Minecraft<p>
 * Made mainly to prevent soul mobs from spawning twice in {@link net.nai.additions.enchantments.SoulHunterEnchantment SoulHunterEnchantment} class<p>
 * <a herf="https://bugs.mojang.com/browse/MC-248272">MC-248272</a> has been fixed in 1.21, this class will be deleted in future Minecraft versions
 **/

    @Inject(method = "doPostHurtEffects", at = @At("HEAD"), cancellable = true)
    private static void doHurtEffects(LivingEntity livingEntity, Entity entity, CallbackInfo ci) {
        EnchantmentHelper.EnchantmentVisitor enchantmentVisitor = (enchantment, i) -> {
            enchantment.doPostHurt(livingEntity, entity, i);
        };
        if (livingEntity != null) {
            runIterationOnInventory(enchantmentVisitor, livingEntity.getAllSlots());
        } else if (entity instanceof Player) {
            runIterationOnItem(enchantmentVisitor, livingEntity.getMainHandItem());
        }
    }

    @Inject(method = "doPostDamageEffects", at = @At("HEAD"), cancellable = true)
    private static void doPostDamageEffects(LivingEntity livingEntity, Entity entity, CallbackInfo ci) {
        EnchantmentHelper.EnchantmentVisitor enchantmentVisitor = (enchantment, i) -> {
            enchantment.doPostAttack(livingEntity, entity, i);
        };
        if (livingEntity != null) {
            runIterationOnInventory(enchantmentVisitor, livingEntity.getAllSlots());
        } else if (livingEntity instanceof Player) {
            runIterationOnItem(enchantmentVisitor, livingEntity.getMainHandItem());
        }

        ci.cancel();
    }

    @Unique
    private static void runIterationOnInventory(EnchantmentHelper.EnchantmentVisitor enchantmentVisitor, Iterable<ItemStack> itemStacks) {
        EnchantmentHelper.runIterationOnInventory(enchantmentVisitor, itemStacks);
    }

    @Unique
    private static void runIterationOnItem(EnchantmentHelper.EnchantmentVisitor enchantmentVisitor, ItemStack itemStack) {
        EnchantmentHelper.runIterationOnItem(enchantmentVisitor, itemStack);
    }
}
