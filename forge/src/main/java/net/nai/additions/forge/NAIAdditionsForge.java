package net.nai.additions.forge;

import dev.architectury.platform.forge.EventBuses;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import net.nai.additions.NAIAdditions;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(NAIAdditions.MOD_ID)
public class NAIAdditionsForge {
    public NAIAdditionsForge() {
		// Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(NAIAdditions.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        NAIAdditions.init();
        EnvExecutor.runInEnv(Env.CLIENT, () -> NAIAdditionsForgeClient::clientInit);
    }
}