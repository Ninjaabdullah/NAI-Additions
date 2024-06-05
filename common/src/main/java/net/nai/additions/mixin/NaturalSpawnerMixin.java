package net.nai.additions.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.LevelChunk;
import net.nai.additions.registry.NAIBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin (NaturalSpawner.class)
public class NaturalSpawnerMixin {

    @Inject(method = "isValidSpawnPostitionForType", at = @At("RETURN"), cancellable = true)
    private static void isValidSpawnPositionForType(ServerLevel serverLevel, MobCategory mobCategory, StructureManager structureManager, ChunkGenerator chunkGenerator, MobSpawnSettings.SpawnerData spawnerData, BlockPos.MutableBlockPos mutableBlockPos, double d, CallbackInfoReturnable<Boolean> cir) {
        int minX = SectionPos.blockToSectionCoord(mutableBlockPos.getX() - 32);
        int minY = SectionPos.blockToSectionCoord(mutableBlockPos.getY() - 32);
        int minZ = SectionPos.blockToSectionCoord(mutableBlockPos.getZ() - 32);
        int maxX = SectionPos.blockToSectionCoord(mutableBlockPos.getX() + 32);
        int maxY = SectionPos.blockToSectionCoord(mutableBlockPos.getY() + 32);
        int maxZ = SectionPos.blockToSectionCoord(mutableBlockPos.getZ() + 32);
        for (int chunkX = minX; chunkX <= maxX; chunkX++) {
            for (int chunkY = minY; chunkY <= maxY; chunkY++) {
                for (int chunkZ = minZ; chunkZ <= maxZ; chunkZ++) {
                    LevelChunk chunk = serverLevel.getChunkSource().getChunk(chunkX, chunkZ, false);
                    if (chunk != null) {
                        for (BlockPos blockPos : chunk.getBlockEntitiesPos()) {
                            BlockState blockState = chunk.getBlockState(blockPos);
                            if (blockState.getBlock() == NAIBlocks.VIRTUS_LAMP.get()) {
                                if (isWithinCube(blockPos, mutableBlockPos) && mobCategory == MobCategory.MONSTER) {
                                    cir.setReturnValue(false);
                                    return;
                                }
                            }
                            if (blockState.getBlock() == NAIBlocks.NEFAS_LAMP.get()) {
                                if (isWithinCube(blockPos, mutableBlockPos) && mobCategory == MobCategory.CREATURE) {
                                    cir.setReturnValue(false);
                                    return;
                                }
                            }
                        }
                    }
                }

            }

        }
    }
    @Unique
    private static boolean isWithinCube(BlockPos blockPos, BlockPos.MutableBlockPos mutableBlockPos) {
        return Mth.abs(mutableBlockPos.getX() - blockPos.getX()) <= 32 &&
                Mth.abs(mutableBlockPos.getY() - blockPos.getY()) <= 32 &&
                Mth.abs(mutableBlockPos.getZ() - blockPos.getZ()) <= 32;
    }
}
