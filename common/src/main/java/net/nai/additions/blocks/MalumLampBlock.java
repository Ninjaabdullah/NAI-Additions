package net.nai.additions.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.nai.additions.registry.NAIParticles;
import org.jetbrains.annotations.Nullable;

public class MalumLampBlock extends Block {
    public MalumLampBlock(Properties properties) {
        super(properties);
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
                                    double particleZ = blockPos.getZ() + z + randomSource.nextDouble();
                                    double particleY = level.getHeight(Heightmap.Types.WORLD_SURFACE, (int) particleX, (int) particleZ);
                                    mutableBlockPos.set(particleX, particleY, particleZ);
                                    BlockState blockState1 = level.getBlockState(mutableBlockPos);
                                    if (!blockState1.isCollisionShapeFullBlock(level, mutableBlockPos)) {
                                        level.addParticle(NAIParticles.MALUM_PARTICLE.get(), particleX, particleY + 0.5, particleZ, 0.0, 0.075, 0.0);
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
        level.playSound(null, blockPos, SoundEvents.BEACON_ACTIVATE, SoundSource.BLOCKS, 2.0f, 0.2f);
        super.onPlace(blockState, level, blockPos, blockState2, bl);
    }
}
