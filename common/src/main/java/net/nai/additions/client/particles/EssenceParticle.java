package net.nai.additions.client.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

@Environment(EnvType.CLIENT)
public class EssenceParticle extends TextureSheetParticle {
    private final SpriteSet spriteSet;

    public EssenceParticle(ClientLevel level, double x, double y, double z, double d, double e, double f, SpriteSet spriteSet) {
        super(level, x, y, z, d, e, f);
        this.friction = 0.96f;
        this.spriteSet = spriteSet;
        this.xd = this.xd * 0.009999999776482582 + d;
        this.yd = this.yd * 0.009999999776482582 + e;
        this.zd = this.zd * 0.009999999776482582 + f;
        this.x += (this.random.nextFloat() - this.random.nextFloat()) * 0.05F;
        this.y += (this.random.nextFloat() - this.random.nextFloat()) * 0.05F;
        this.z += (this.random.nextFloat() - this.random.nextFloat()) * 0.05F;
        this.lifetime = (int)(12.0 / (Math.random() * 0.8 + 0.2)) + 4;
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.spriteSet);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_LIT;
    }

    @Override
    public void move(double d, double e, double f) {
        this.setBoundingBox(this.getBoundingBox().move(d, e, f));
        this.setLocationFromBoundingbox();
    }

    @Override
    public float getQuadSize(float f) {
        float g = ((float)this.age + f) / (float)this.lifetime;
        return this.quadSize * (1.0F - g * g * 0.5F);
    }

    @Override
    public int getLightColor(float f) {
        return 240;
    }

    @Environment(EnvType.CLIENT)
    public static class Virtus implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Virtus(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double d, double e, double f) {
            EssenceParticle particle = new EssenceParticle(world, x, y, z, d, e, f, spriteSet);
            particle.setColor(0.4549f, 0.9333f, 0.949f);
            particle.pickSprite(this.spriteSet);
            return particle;
        }
    }

    @Environment(EnvType.CLIENT)
    public static class Nefas implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Nefas(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double d, double e, double f) {
            EssenceParticle particle = new EssenceParticle(world, x, y, z, d, e, f, spriteSet);
            particle.setColor(0.8352f, 0.8352f, 0.8352f);
            particle.pickSprite(this.spriteSet);
            return particle;
        }
    }

    @Environment(EnvType.CLIENT)
    public static class Malum implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Malum(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double d, double e, double f) {
            EssenceParticle particle = new EssenceParticle(world, x, y, z, d, e, f, spriteSet);
            particle.setColor(0.7804f, 0.3569f, 0.9490f);
            particle.pickSprite(this.spriteSet);
            return particle;
        }
    }
}
