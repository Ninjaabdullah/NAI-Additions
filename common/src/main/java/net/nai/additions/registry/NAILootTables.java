package net.nai.additions.registry;

import dev.architectury.event.events.common.LootEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class NAILootTables {

    public static void init() {
        LootEvent.MODIFY_LOOT_TABLE.register((lootTables, id, context, builtin) -> {
//            // Check that the loot table is dirt and built-in
//            if (builtin && Blocks.DIRT.getLootTable().equals(id)) {
//                // Create a loot pool with a single item entry of Items.DIAMOND
//                LootPool.Builder pool = LootPool.lootPool().add(LootItem.lootTableItem(Items.DIAMOND));
//                context.addPool(pool);
//            }

            // Reptianium Template in Ancient City
            if (builtin && BuiltInLootTables.ANCIENT_CITY.equals(id)) {
                LootPool.Builder pool = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.125f))
                        .add(LootItem.lootTableItem(NAIItems.REPTIANIUM_UPGRADE_SMITHING_TEMPLATE.get()));
                context.addPool(pool);
            }

            // Virtus Essence in Woodland Mansion
            if (builtin && BuiltInLootTables.WOODLAND_MANSION.equals(id)) {
                LootPool.Builder pool = LootPool.lootPool()
                        .setRolls(UniformGenerator.between(1,6))
                        .when(LootItemRandomChanceCondition.randomChance(0.3f))
                        .add(LootItem.lootTableItem(NAIItems.VIRTUS_ESSENCE.get()));
                context.addPool(pool);
            }

            // Virtus Essence in Pillager Outpost
            if (builtin && BuiltInLootTables.PILLAGER_OUTPOST.equals(id)) {
                LootPool.Builder pool = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.125f))
                        .add(LootItem.lootTableItem(NAIItems.VIRTUS_ESSENCE.get()));
                context.addPool(pool);
            }

            // Nefas Essence from Vex
            if (builtin && EntityType.VEX.getDefaultLootTable().equals(id)) {
                LootPool.Builder pool = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(NAIItems.NEFAS_ESSENCE.get()));
                context.addPool(pool);
            }
        });
    }
}
