package net.nai.additions.items.crafting;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.nai.additions.items.CapturedSoulItem;
import net.nai.additions.registry.NAIItems;
import net.nai.additions.registry.NAIRecipeSerializer;
import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.Objects;

public class SpawnEggRecipe extends CustomRecipe {
    private static final Ingredient LIFE_ESSENCE_INGREDIENT = Ingredient.of(NAIItems.LIFE_ESSENCE.get());

    public SpawnEggRecipe(ResourceLocation resourceLocation, CraftingBookCategory craftingBookCategory) {
        super(resourceLocation, craftingBookCategory);
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        ItemStack itemStack = ItemStack.EMPTY;
        ArrayList<ItemStack> list = Lists.newArrayList();
        for (int i = 0; i < container.getContainerSize(); ++i) {
            ItemStack itemStack1 = container.getItem(i);
            ItemStack itemStack2 = container.getItem(i);
            if (itemStack1.isEmpty()) continue;
            if (itemStack1.getItem() instanceof CapturedSoulItem) {
                if (!itemStack.isEmpty()) {
                    return false;
                }
                itemStack = itemStack1;
                continue;
            }
            if (itemStack1.getItem() instanceof EggItem) {
                list.add(itemStack1);
                continue;
            }
            if (itemStack2.isEmpty()) continue;
            if (LIFE_ESSENCE_INGREDIENT.test(itemStack2)) {
                list.add(itemStack2);
                continue;
            }
            return false;
        }
        return !itemStack.isEmpty() && list.size() == 2;
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
        ItemStack itemStack = ItemStack.EMPTY;
        for (int i = 0; i < container.getContainerSize(); ++i) {
            ItemStack itemStack1 = container.getItem(i);
            if (itemStack1.isEmpty()) continue;
            Item item = itemStack1.getItem();
            if (item instanceof EggItem) continue;
            if (LIFE_ESSENCE_INGREDIENT.test(itemStack1)) continue;
            if (item instanceof CapturedSoulItem) {
                if (!itemStack.isEmpty()) {
                    return ItemStack.EMPTY;
                }
                itemStack = itemStack1.copy();
            }
        }
        String id = Objects.requireNonNull(itemStack.getTagElement("nai_additions.captured_mob")).getString("prev_mob_id");
        EntityType<?> entityType = EntityType.byString(id).orElseThrow(() -> new IllegalStateException("Invalid entity type: " + id));
        return Objects.requireNonNull(SpawnEggItem.byId(entityType)).getDefaultInstance();
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return i * j >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return NAIRecipeSerializer.SPAWN_EGG_RECIPE.get();
    }
}
