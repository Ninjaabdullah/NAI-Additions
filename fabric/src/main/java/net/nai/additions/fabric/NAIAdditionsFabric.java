package net.nai.additions.fabric;

import net.nai.additions.NAIAdditions;
import net.fabricmc.api.ModInitializer;

public class NAIAdditionsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        NAIAdditions.init();
    }
}