package net.nai.additions.forge;

import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.nai.additions.NAIAdditionsClient;
import net.nai.additions.client.particles.EssenceParticle;
import net.nai.additions.registry.NAIParticles;

public class NAIAdditionsForgeClient {

    public static void clientInit() {
        NAIAdditionsClient.init();

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(NAIAdditionsForgeClient::onRegisterParticleProviders);
    }

    public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(NAIParticles.VIRTUS_PARTICLE.get(), EssenceParticle.Virtus::new);
        event.registerSpriteSet(NAIParticles.NEFAS_PARTICLE.get(), EssenceParticle.Nefas::new);
        event.registerSpriteSet(NAIParticles.MALUM_PARTICLE.get(), EssenceParticle.Malum::new);
    }
}
