package net.nai.additions.registry;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.nai.additions.NAIAdditions;
import net.nai.additions.items.ReptianiumBlockItem;
import net.nai.additions.items.armor.NAIArmorMaterials;
import net.nai.additions.items.tools.NAIAxeItem;
import net.nai.additions.items.tools.NAIHoeItem;
import net.nai.additions.items.tools.NAIPickaxeItem;
import net.nai.additions.items.tools.NAIToolTiers;

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

    // Reptianium Tools
    public static final RegistrySupplier<Item> REPTIANIUM_SWORD = ITEMS.register("reptianium_sword", () ->
            new SwordItem(NAIToolTiers.REPTIANIUM, 1, -2.4f, commonProperties.rarity(Rarity.EPIC).fireResistant()));

    public static final RegistrySupplier<Item> REPTIANIUM_PICKAXE = ITEMS.register("reptianium_pickaxe", () ->
            new NAIPickaxeItem(NAIToolTiers.REPTIANIUM, -5, -2.4f, commonProperties.rarity(Rarity.EPIC).fireResistant()));

    public static final RegistrySupplier<Item> REPTIANIUM_AXE = ITEMS.register("reptianium_axe", () ->
            new NAIAxeItem(NAIToolTiers.REPTIANIUM, 4, -3.0f, commonProperties.rarity(Rarity.EPIC).fireResistant()));

    public static final RegistrySupplier<Item> REPTIANIUM_SHOVEL = ITEMS.register("reptianium_shovel", () ->
            new ShovelItem(NAIToolTiers.REPTIANIUM, -5, -2.4f, commonProperties.rarity(Rarity.EPIC).fireResistant()));

    public static final RegistrySupplier<Item> REPTIANIUM_HOE = ITEMS.register("reptianium_hoe", () ->
            new NAIHoeItem(NAIToolTiers.REPTIANIUM, -14, 1.0f,commonProperties.rarity(Rarity.EPIC).fireResistant()));

    // Reptianium Armor
    public static final RegistrySupplier<Item> REPTIANIUM_HELMET = ITEMS.register("reptianium_helmet", () ->
            new ArmorItem(NAIArmorMaterials.REPTIANIUM, ArmorItem.Type.HELMET, commonProperties.rarity(Rarity.EPIC).fireResistant()));

    public static final RegistrySupplier<Item> REPTIANIUM_CHESTPLATE = ITEMS.register("reptianium_chestplate", () ->
            new ArmorItem(NAIArmorMaterials.REPTIANIUM, ArmorItem.Type.CHESTPLATE, commonProperties.rarity(Rarity.EPIC).fireResistant()));

    public static final RegistrySupplier<Item> REPTIANIUM_LEGGINGS = ITEMS.register("reptianium_leggings", () ->
            new ArmorItem(NAIArmorMaterials.REPTIANIUM, ArmorItem.Type.LEGGINGS, commonProperties.rarity(Rarity.EPIC).fireResistant()));

    public static final RegistrySupplier<Item> REPTIANIUM_BOOTS = ITEMS.register("reptianium_boots", () ->
            new ArmorItem(NAIArmorMaterials.REPTIANIUM, ArmorItem.Type.BOOTS, commonProperties.rarity(Rarity.EPIC).fireResistant()));

    // Reptianium Upgrade
    public static final RegistrySupplier<Item> REPTIANIUM_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("reptianium_upgrade_smithing_template", () ->
            new SmithingTemplateItem(
                    Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(NAIAdditions.MOD_ID, "smithing_template.reptianium_upgrade.applies_to"))).withStyle(ChatFormatting.BLUE),
                    Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(NAIAdditions.MOD_ID, "smithing_template.reptianium_upgrade.ingredients"))).withStyle(ChatFormatting.BLUE),
                    Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation(NAIAdditions.MOD_ID, "reptianium_upgrade"))).withStyle(ChatFormatting.GRAY),
                    Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(NAIAdditions.MOD_ID, "smithing_template.reptianium_upgrade.base_slot_description"))),
                    Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(NAIAdditions.MOD_ID, "smithing_template.reptianium_upgrade.additions_slot_description"))),
                    SmithingTemplateItem.createNetheriteUpgradeIconList(),
                    SmithingTemplateItem.createNetheriteUpgradeMaterialList()
            ));

    public static void init() {
        ITEMS.register();
        CreativeTabRegistry.append(NAITabs.NAI_ADDITIONS_TAB, REPTIANIUM_UPGRADE_SMITHING_TEMPLATE);
    }
}