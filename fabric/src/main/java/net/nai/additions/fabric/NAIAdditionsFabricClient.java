package net.nai.additions.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.nai.additions.NAIAdditionsClient;
import net.nai.additions.client.particles.EssenceParticle;
import net.nai.additions.registry.NAIParticles;

public class NAIAdditionsFabricClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        NAIAdditionsClient.init();

        ParticleFactoryRegistry.getInstance().register(NAIParticles.VIRTUS_PARTICLE.get(), EssenceParticle.Virtus::new);
        ParticleFactoryRegistry.getInstance().register(NAIParticles.NEFAS_PARTICLE.get(), EssenceParticle.Nefas::new);
        ParticleFactoryRegistry.getInstance().register(NAIParticles.MALUM_PARTICLE.get(), EssenceParticle.Malum::new);
    }
}
