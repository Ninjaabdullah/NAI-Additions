package net.nai.additions.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.nai.additions.blocks.entity.VirtusLampBlockEntity;
import net.nai.additions.registry.NAIBlockEntityTypes;
import org.jetbrains.annotations.Nullable;

public class VirtusLampBlock extends BaseEntityBlock {

    public VirtusLampBlock(Properties properties) {
        super(properties);
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new VirtusLampBlockEntity(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, NAIBlockEntityTypes.VIRTUS_LAMP_ENTITY.get(), VirtusLampBlockEntity::tick);
    }

    @Override
    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {
        level.playSound(null, blockPos, SoundEvents.BEACON_ACTIVATE, SoundSource.BLOCKS, 1.0f, 1.0f);
        super.onPlace(blockState, level, blockPos, blockState2, bl);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        if(level.isClientSide) {
            int i = blockPos.getX();
            int j = blockPos.getY();
            int k = blockPos.getZ();
            BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
            for (int l = 0; l < 16; ++l) {
                mutableBlockPos.set(i + Mth.nextInt(randomSource, -32, 32), j - Mth.nextInt(randomSource, -32, 32), k + Mth.nextInt(randomSource, -32, 32));
                double d = (double) mutableBlockPos.getX() + randomSource.nextDouble();
                double e = (double) mutableBlockPos.getY() + randomSource.nextDouble();
                double f = (double) mutableBlockPos.getZ() + randomSource.nextDouble();
                BlockState blockState1 = level.getBlockState(mutableBlockPos);
                if(!blockState1.isCollisionShapeFullBlock(level, mutableBlockPos)) {
                    level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, d, e, f, 0.0, 0.0, 0.0);
                }
            }
        }
        super.animateTick(blockState, level, blockPos, randomSource);
    }

}
