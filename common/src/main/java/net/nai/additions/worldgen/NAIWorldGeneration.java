package net.nai.additions.worldgen;

import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.registry.level.biome.BiomeModifications;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.nai.additions.NAIAdditions;

public class NAIWorldGeneration {

    public static void init() {
        LifecycleEvent.SETUP.register( () ->
                BiomeModifications.addProperties((biomeContext, mutable) -> {
                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,
                            ResourceKey.create(Registries.PLACED_FEATURE,
                                    new ResourceLocation(NAIAdditions.MOD_ID + ":ore_reptianium")));
                }));
    }
}
