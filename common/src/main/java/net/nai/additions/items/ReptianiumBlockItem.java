package net.nai.additions.items;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class ReptianiumBlockItem extends BlockItem {
    public ReptianiumBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void onDestroyed(ItemEntity itemEntity) {
        if(!itemEntity.level().isClientSide && itemEntity.isOnFire()) {
            itemEntity.level().explode(itemEntity, itemEntity.getX(), itemEntity.getY(0.0625), itemEntity.getZ(), 200.0f, Level.ExplosionInteraction.TNT);
        }
    }
}
