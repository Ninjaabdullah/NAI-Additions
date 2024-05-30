package net.nai.additions;

import net.nai.additions.registry.NAIBlocks;
import net.nai.additions.registry.NAIItems;
import net.nai.additions.registry.NAITabs;

public class NAIAdditions
{
	public static final String MOD_ID = "nai_additions";

	public static void init() {
		NAITabs.init();
		NAIBlocks.init();
		NAIItems.init();
	}
}
