package net.yigitguven.death_outcomes;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = DeathOutcomes.MODID)
public class ModRecipes {

    public static final ResourceLocation PLAYER_TRACKING_COMPASS_ID =
            ResourceLocation.fromNamespaceAndPath(DeathOutcomes.MODID, "player_tracking_compass");
    public static final ResourceLocation PLAYER_COMPASS_DISMANTLE_ID =
            ResourceLocation.fromNamespaceAndPath(DeathOutcomes.MODID, "player_compass_dismantle");

    @SubscribeEvent
    public static void onServerAboutToStart(ServerAboutToStartEvent event) {
        ensureRecipesRegistered(event.getServer().getRecipeManager());
    }

    @SubscribeEvent
    public static void onDatapackSync(OnDatapackSyncEvent event) {
        ensureRecipesRegistered(event.getPlayerList().getServer().getRecipeManager());
    }

    public static void ensureRecipesRegistered(RecipeManager recipeManager) {
        if (recipeManager.byKey(PLAYER_TRACKING_COMPASS_ID).isPresent()
                && recipeManager.byKey(PLAYER_COMPASS_DISMANTLE_ID).isPresent()) {
            return;
        }

        List<RecipeHolder<?>> recipes = new ArrayList<>(recipeManager.getRecipes());
        recipes.removeIf(holder -> holder.id().equals(PLAYER_TRACKING_COMPASS_ID)
                || holder.id().equals(PLAYER_COMPASS_DISMANTLE_ID));

        recipes.add(new RecipeHolder<>(
                PLAYER_TRACKING_COMPASS_ID,
                new PlayerTrackingCompassRecipe(CraftingBookCategory.EQUIPMENT)
        ));
        recipes.add(new RecipeHolder<>(
                PLAYER_COMPASS_DISMANTLE_ID,
                new PlayerCompassDismantleRecipe(CraftingBookCategory.MISC)
        ));

        recipeManager.replaceRecipes(recipes);
    }
}
