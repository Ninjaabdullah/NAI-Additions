package net.nai.additions.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.nai.additions.NAIAdditions;
import net.nai.additions.blocks.NefasLampBlock;
import net.nai.additions.blocks.VirtusLampBlock;

public class NAIBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(NAIAdditions.MOD_ID, Registries.BLOCK);

    // Template for Blocks
    //public static final RegistrySupplier<Block> NAI_BLOCK = BLOCKS.register("nai_block", () ->
    //        new Block(BlockBehaviour.Properties.of()));

    // Reptianium Ores
    public static final RegistrySupplier<Block> REPTIANIUM_ORE = BLOCKS.register("reptianium_ore", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(10.0f)
                    .sound(SoundType.STONE),
                    UniformInt.of(5, 10)));

    public static final RegistrySupplier<Block> DEEPSLATE_REPTIANIUM_ORE = BLOCKS.register("deepslate_reptianium_ore", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.copy(REPTIANIUM_ORE.get())
                    .mapColor(MapColor.DEEPSLATE)
                    .requiresCorrectToolForDrops()
                    .strength(12.0f)
                    .sound(SoundType.DEEPSLATE),
                    UniformInt.of(5, 12)));

    // Reptianium Block
    public static final RegistrySupplier<Block> REPTIANIUM_BLOCK = BLOCKS.register("reptianium_block", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .requiresCorrectToolForDrops()
                    .strength(11.0f)
                    .sound(SoundType.NETHERITE_BLOCK)));

    // Lamps
    public static final RegistrySupplier<Block> VIRTUS_LAMP = BLOCKS.register("virtus_lamp", () ->
            new VirtusLampBlock(BlockBehaviour.Properties.of()
                    .strength(2.0f)
                    .sound(SoundType.AMETHYST)
                    .lightLevel((blockStatex) -> 15)));

    public static final RegistrySupplier<Block> NEFAS_LAMP = BLOCKS.register("nefas_lamp", () ->
            new NefasLampBlock(BlockBehaviour.Properties.of()
                    .strength(2.0f)
                    .sound(SoundType.AMETHYST)
                    .lightLevel((blockStatex) -> 6)));

    public static void init() {
        BLOCKS.register();
    }

}
