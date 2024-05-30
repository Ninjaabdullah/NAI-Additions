package net.nai.additions.registry;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.nai.additions.NAIAdditions;

public class NAITabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(NAIAdditions.MOD_ID, Registries.CREATIVE_MODE_TAB);
    public static final RegistrySupplier<CreativeModeTab> NAI_ADDITIONS_TAB = TABS.register("nai_additions_tab", () ->
            CreativeTabRegistry.create(Component.translatable("itemGroup." + NAIAdditions.MOD_ID + ".nai_additions_tab"),
                    () -> new ItemStack(Items.DIAMOND)
            ));

    public static void init() {
        TABS.register();
    }
}
