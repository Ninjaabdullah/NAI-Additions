package net.nai.additions.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.nai.additions.NAIAdditions;
import net.nai.additions.entities.custom.SoulEntity;

public class NAIEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(NAIAdditions.MOD_ID, Registries.ENTITY_TYPE);

    // Soul Mob
    public static final RegistrySupplier<EntityType<SoulEntity>> SOUL = ENTITY_TYPES.register("soul", () ->
            EntityType.Builder.of(SoulEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.95f).build("soul"));

    public static void init() {
        ENTITY_TYPES.register();
    }
}