package net.nai.additions.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.nai.additions.NAIAdditions;
import net.nai.additions.enchantments.EmissionDetectorEnchantment;

public class NAIEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(NAIAdditions.MOD_ID, Registries.ENCHANTMENT);

    public static final RegistrySupplier<Enchantment> EMISSION_DETECTOR = ENCHANTMENTS.register("emission_detector", EmissionDetectorEnchantment::new);

    public static void init() {
        ENCHANTMENTS.register();
    }
}
