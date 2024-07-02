package net.nai.additions.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.nai.additions.NAIAdditions;
import net.nai.additions.blocks.entity.NefasLampBlockEntity;
import net.nai.additions.blocks.entity.VirtusLampBlockEntity;

public class NAIBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(NAIAdditions.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<BlockEntityType<VirtusLampBlockEntity>> VIRTUS_LAMP_ENTITY = BLOCK_ENTITY_TYPES.register("virtus_lamp_entity", () ->
            BlockEntityType.Builder.of(VirtusLampBlockEntity::new, NAIBlocks.VIRTUS_LAMP.get()).build(null));

    public static final RegistrySupplier<BlockEntityType<NefasLampBlockEntity>> NEFAS_LAMP_ENTITY = BLOCK_ENTITY_TYPES.register("nefas_lamp_entity", () ->
            BlockEntityType.Builder.of(NefasLampBlockEntity::new, NAIBlocks.NEFAS_LAMP.get()).build(null));

    public static void init() {
        BLOCK_ENTITY_TYPES.register();
    }
}
