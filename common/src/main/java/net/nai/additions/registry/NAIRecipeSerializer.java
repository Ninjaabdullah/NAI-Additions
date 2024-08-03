package net.nai.additions.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.nai.additions.NAIAdditions;
import net.nai.additions.items.crafting.SpawnEggRecipe;

public class NAIRecipeSerializer {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(NAIAdditions.MOD_ID, Registries.RECIPE_SERIALIZER);

    public static final RegistrySupplier<RecipeSerializer<SpawnEggRecipe>> SPAWN_EGG_RECIPE = RECIPE_SERIALIZERS.register("crafting_special_spawn_egg", () ->
            new SimpleCraftingRecipeSerializer<>(SpawnEggRecipe::new));

    public static void init() {
        RECIPE_SERIALIZERS.register();
    }
}
