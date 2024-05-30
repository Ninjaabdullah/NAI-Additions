package net.nai.additions.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.nai.additions.NAIAdditions;
import net.nai.additions.items.ReptianiumBlockItem;

public class NAIItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(NAIAdditions.MOD_ID, Registries.ITEM);

    // Common Properties shared between all Items
    public static final Item.Properties commonProperties = new Item.Properties().arch$tab(NAITabs.NAI_ADDITIONS_TAB);

    // Template for Items
    //public static final RegistrySupplier<Item> NAI_ITEM = ITEMS.register("nai_item", () ->
    //        new Item(new Item.Properties()));

    // Template for Block Items
    //public static final RegistrySupplier<Item> NAI_BLOCK = ITEMS.register("nai_block", () ->
    //        new BlockItem(NAIBlocks.NAI_BLOCK.get(), new Item.Properties()));

    // Reptianium Ores Items
    public static final RegistrySupplier<Item> REPTIANIUM_ORE = ITEMS.register("reptianium_ore", () ->
            new BlockItem(NAIBlocks.REPTIANIUM_ORE.get(), commonProperties.rarity(Rarity.EPIC)));

    public static final RegistrySupplier<Item> DEEPSLATE_REPTIANIUM_ORE = ITEMS.register("deepslate_reptianium_ore", () ->
            new BlockItem(NAIBlocks.DEEPSLATE_REPTIANIUM_ORE.get(), commonProperties.rarity(Rarity.EPIC)));

    // Reptianium Block Item
    public static final RegistrySupplier<Item> REPTIANIUM_BLOCK = ITEMS.register("reptianium_block", () ->
            new ReptianiumBlockItem(NAIBlocks.REPTIANIUM_BLOCK.get(), commonProperties.rarity(Rarity.EPIC)));

    // Reptianium Misc
    public static final RegistrySupplier<Item> REPTIANIUM_SHARD = ITEMS.register("reptianium_shard", () ->
            new Item(commonProperties.rarity(Rarity.EPIC)));

    public static final RegistrySupplier<Item> REPTIANIUM_INGOT = ITEMS.register("reptianium_ingot", () ->
            new Item(commonProperties.rarity(Rarity.EPIC).fireResistant()));

    public static void init() {
        ITEMS.register();
    }
}