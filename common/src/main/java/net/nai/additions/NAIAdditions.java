package net.nai.additions;

import net.nai.additions.registry.*;
import net.nai.additions.worldgen.NAIWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NAIAdditions
{
	public static final String MOD_ID = "nai_additions";
	public static final Logger LOGGER = LoggerFactory.getLogger("NAI Additions");

	public static void init() {
		NAITabs.init();
		NAIBlocks.init();
		NAIItems.init();
		NAIEnchantments.init();
		NAIWorldGeneration.init();
		NAIBlockEntityTypes.init();
		NAILootTables.init();
		NAITrades.init();
	}
}
