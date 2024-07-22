package net.nai.additions.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.nai.additions.NAIAdditions;
import net.nai.additions.enchantments.EmissionDetectorEnchantment;
import net.nai.additions.enchantments.SoulHunterEnchantment;

public class NAIEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(NAIAdditions.MOD_ID, Registries.ENCHANTMENT);

    // Emission Detector Enchantment
    public static final RegistrySupplier<Enchantment> EMISSION_DETECTOR = ENCHANTMENTS.register("emission_detector", EmissionDetectorEnchantment::new);
    // Soul Hunter Enchantment
    public static final RegistrySupplier<Enchantment> SOUL_HUNTER = ENCHANTMENTS.register("soul_hunter", SoulHunterEnchantment::new);

    public static void init() {
        ENCHANTMENTS.register();
    }
}
