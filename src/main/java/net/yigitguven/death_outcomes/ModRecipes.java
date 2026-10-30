package net.yigitguven.death_outcomes;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, DeathOutcomes.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, SimpleCraftingRecipeSerializer<PlayerTrackingCompassRecipe>> PLAYER_TRACKING_COMPASS_SERIALIZER =
            RECIPE_SERIALIZERS.register("crafting_special_player_tracking_compass",
                    () -> new SimpleCraftingRecipeSerializer<>(PlayerTrackingCompassRecipe::new));

    public static final DeferredHolder<RecipeSerializer<?>, SimpleCraftingRecipeSerializer<PlayerCompassDismantleRecipe>> PLAYER_COMPASS_DISMANTLE_SERIALIZER =
            RECIPE_SERIALIZERS.register("crafting_special_player_compass_dismantle",
                    () -> new SimpleCraftingRecipeSerializer<>(PlayerCompassDismantleRecipe::new));

    public static void register(IEventBus modEventBus) {
        RECIPE_SERIALIZERS.register(modEventBus);
    }
}
