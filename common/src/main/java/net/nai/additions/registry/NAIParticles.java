package net.nai.additions.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.nai.additions.NAIAdditions;

public class NAIParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(NAIAdditions.MOD_ID, Registries.PARTICLE_TYPE);

    // Essence Particles
    public static final RegistrySupplier<SimpleParticleType> VIRTUS_PARTICLE = PARTICLE_TYPES.register("virtus", () ->
            new SimpleParticleType(false));
    public static final RegistrySupplier<SimpleParticleType> NEFAS_PARTICLE = PARTICLE_TYPES.register("nefas", () ->
            new SimpleParticleType(false));
    public static final RegistrySupplier<SimpleParticleType> MALUM_PARTICLE = PARTICLE_TYPES.register("malum", () ->
            new SimpleParticleType(false));

    public static void init() {
        PARTICLE_TYPES.register();
    }
}
