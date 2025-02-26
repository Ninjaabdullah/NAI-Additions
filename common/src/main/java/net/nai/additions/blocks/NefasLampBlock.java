package net.nai.additions.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.nai.additions.blocks.entity.NefasLampBlockEntity;
import net.nai.additions.registry.NAIBlockEntityTypes;
import net.nai.additions.registry.NAIParticles;
import org.jetbrains.annotations.Nullable;

public class NefasLampBlock extends BaseEntityBlock {

    public NefasLampBlock(Properties properties) {
        super(properties);
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new NefasLampBlockEntity(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, NAIBlockEntityTypes.NEFAS_LAMP_ENTITY.get(), NefasLampBlockEntity::tick);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
        RandomSource randomSource = RandomSource.create();
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        new Thread(() -> {
            try {
                for (int radius = 0; radius <= 32; radius++) {
                    for (int x = -radius; x <= radius; x++) {
                        for (int z = -radius; z <= radius; z++) {
                            if (Mth.abs(x) == radius || Mth.abs(z) == radius) {
                                for (int amount = 0; amount < 4; ++amount) {
                                    double particleX = blockPos.getX() + x + randomSource.nextDouble();
                                    double particleY = blockPos.getY();
                                    double particleZ = blockPos.getZ() + z + randomSource.nextDouble();
                                    mutableBlockPos.set(particleX, particleY, particleZ);
                                    BlockState blockState1 = level.getBlockState(mutableBlockPos);
                                    if (!blockState1.isCollisionShapeFullBlock(level, mutableBlockPos)) {
                                        level.addParticle(NAIParticles.NEFAS_PARTICLE.get(), particleX, particleY + 0.5, particleZ, 0.0, 0.075, 0.0);
                                    }
                                }
                            }
                        }
                    }
                    Thread.sleep(120);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
        super.setPlacedBy(level, blockPos, blockState, livingEntity, itemStack);
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
                    level.addParticle(NAIParticles.NEFAS_PARTICLE.get(), d, e, f, 0.0, 0.075, 0.0);
                }
            }
        }
        super.animateTick(blockState, level, blockPos, randomSource);
    }

}
