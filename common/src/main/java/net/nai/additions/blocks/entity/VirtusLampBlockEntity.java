package net.nai.additions.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.nai.additions.registry.NAIBlockEntityTypes;

public class VirtusLampBlockEntity extends BlockEntity {
    public VirtusLampBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(NAIBlockEntityTypes.VIRTUS_LAMP_ENTITY.get(), blockPos, blockState);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, VirtusLampBlockEntity virtusLampBlockEntity) {
    }
}
