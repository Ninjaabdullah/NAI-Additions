package net.nai.additions.misc;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.nai.additions.NAIAdditions;

public class NAITags {
    // Emitters Tag
    public static final TagKey<Block> EMITTERS = TagKey.create(Registries.BLOCK, new ResourceLocation(NAIAdditions.MOD_ID, "emitters"));
    // Soulless Entities Tag
    public static final TagKey<EntityType<?>> SOULLESS_ENTITIES = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(NAIAdditions.MOD_ID, "soulless_entities"));
}
