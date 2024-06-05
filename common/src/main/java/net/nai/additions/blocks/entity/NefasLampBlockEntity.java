package net.nai.additions.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.nai.additions.registry.NAIBlockEntityTypes;

public class NefasLampBlockEntity extends BlockEntity {
    public NefasLampBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(NAIBlockEntityTypes.NEFAS_LAMP_ENTITY.get(), blockPos, blockState);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, NefasLampBlockEntity nefasLampBlockEntity) {
    }
}
