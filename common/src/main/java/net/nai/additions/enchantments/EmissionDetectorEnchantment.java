package net.nai.additions.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class EmissionDetectorEnchantment extends Enchantment {

    public EmissionDetectorEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR_HEAD,
                new EquipmentSlot[] {EquipmentSlot.HEAD});
    }
}
