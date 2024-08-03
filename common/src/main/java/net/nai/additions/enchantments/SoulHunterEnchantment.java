package net.nai.additions.enchantments;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.nai.additions.entities.custom.SoulEntity;
import net.nai.additions.misc.NAITags;
import net.nai.additions.registry.NAIEntities;

public class SoulHunterEnchantment extends Enchantment {
    public SoulHunterEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.WEAPON,
                new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public void doPostAttack(LivingEntity livingEntity, Entity entity, int i) {
        super.doPostAttack(livingEntity, entity, i);

        if (!livingEntity.level().isClientSide()) {
            EntityType<?> entityType = entity.getType();
            if (!entityType.is(NAITags.SOULLESS_ENTITIES) && entity instanceof LivingEntity) {
                ServerLevel level = ((ServerLevel) livingEntity.level());
                BlockPos pos = entity.blockPosition();
                if (!entity.isAlive()) {
                    float rand = livingEntity.getRandom().nextFloat();
                    if (rand <= 0.10f * i) {
                        SoulEntity soul = NAIEntities.SOUL.get().spawn(level, pos, MobSpawnType.TRIGGERED);
                        if (soul != null) {
                            soul.setPreviousCarrier(entityType.toString());
                            soul.setPreviousCarrierID(EntityType.getKey(entityType).toString());
                            soul.setPreviousCarrierUUID(entity.getUUID());
                            soul.setDespawnTimer(20 * 2 * i);
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
