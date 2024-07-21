package net.nai.additions;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.nai.additions.client.entities.NAIModelLayers;
import net.nai.additions.client.entities.SoulModel;
import net.nai.additions.client.entities.SoulRenderer;
import net.nai.additions.registry.NAIEntities;

// Told You
public class NAIAdditionsClient {

    @Environment(EnvType.CLIENT)
    public static void init() {

        EntityRendererRegistry.register(NAIEntities.SOUL, SoulRenderer::new);
        EntityModelLayerRegistry.register(NAIModelLayers.SOUL_LAYER, SoulModel::createBodyLayer);
    }
}
