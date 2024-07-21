package net.nai.additions.forge.events;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nai.additions.NAIAdditions;
import net.nai.additions.entities.custom.SoulEntity;
import net.nai.additions.registry.NAIEntities;

@Mod.EventBusSubscriber(modid = NAIAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NAIEventBusEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(NAIEntities.SOUL.get(), SoulEntity.createAttributes().build());
    }
}
