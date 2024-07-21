package net.nai.additions.fabric;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.nai.additions.NAIAdditions;
import net.fabricmc.api.ModInitializer;
import net.nai.additions.entities.custom.SoulEntity;
import net.nai.additions.registry.NAIEntities;

public class NAIAdditionsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        NAIAdditions.init();

        FabricDefaultAttributeRegistry.register(NAIEntities.SOUL.get(), SoulEntity.createAttributes());
    }
}