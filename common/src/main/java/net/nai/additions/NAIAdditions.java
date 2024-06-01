package net.nai.additions;

import net.nai.additions.registry.*;
import net.nai.additions.worldgen.NAIWorldGeneration;

public class NAIAdditions
{
	public static final String MOD_ID = "nai_additions";

	public static void init() {
		NAITabs.init();
		NAIBlocks.init();
		NAIItems.init();
		NAIWorldGeneration.init();
	}
}
