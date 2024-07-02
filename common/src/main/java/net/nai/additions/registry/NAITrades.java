package net.nai.additions.registry;

import dev.architectury.registry.level.entity.trade.SimpleTrade;
import dev.architectury.registry.level.entity.trade.TradeRegistry;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class NAITrades {

    public static void init() {
        // Reptianium Template Trade
        NAIItems.REPTIANIUM_UPGRADE_SMITHING_TEMPLATE.listen((item ->
        TradeRegistry.registerVillagerTrade(VillagerProfession.TOOLSMITH, 5,
                new SimpleTrade(
                        new ItemStack(Items.EMERALD, 64),
                        ItemStack.EMPTY,
                        NAIItems.REPTIANIUM_UPGRADE_SMITHING_TEMPLATE.get().getDefaultInstance(),
                        1,
                        50,
                        0.5f))
        ));
    }
}
