package net.nai.additions.items;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CapturedSoulItem extends Item {
    public CapturedSoulItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        if (itemStack.hasTag()) {
            CompoundTag compoundTag = itemStack.getTag();
            assert compoundTag != null;
            String string = compoundTag.getString("nai_additions.captured_mob");
            list.add(Component.literal("Mob: ").append(Component.translatable(string).withStyle(ChatFormatting.GREEN)));
        }
    }
}
