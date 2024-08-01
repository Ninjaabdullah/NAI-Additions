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
            CompoundTag compoundTag = itemStack.getTagElement("nai_additions.captured_mob");
            assert compoundTag != null;
            String string = compoundTag.getString("prev_mob");
            list.add(Component.literal("Mob: ").withStyle(ChatFormatting.GRAY).append(Component.translatable(string).withStyle(ChatFormatting.GREEN)));
        }
    }
}
