package net.nai.additions.client.entities;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.nai.additions.NAIAdditions;
import net.nai.additions.entities.custom.SoulEntity;
import org.jetbrains.annotations.Nullable;

public class SoulRenderer extends MobRenderer<SoulEntity, SoulModel<SoulEntity>> {
    public SoulRenderer(EntityRendererProvider.Context context) {
        super(context, new SoulModel<>(context.bakeLayer(NAIModelLayers.SOUL_LAYER)), 0f);
    }

    @Override
    public ResourceLocation getTextureLocation(SoulEntity entity) {
        return new ResourceLocation(NAIAdditions.MOD_ID, "textures/entity/soul.png");
    }

    @Override
    protected int getBlockLightLevel(SoulEntity entity, BlockPos blockPos) {
        return 15;
    }

    @Nullable
    @Override
    protected RenderType getRenderType(SoulEntity livingEntity, boolean bl, boolean bl2, boolean bl3) {
        return RenderType.entityTranslucent(getTextureLocation(livingEntity));
    }

    
}
