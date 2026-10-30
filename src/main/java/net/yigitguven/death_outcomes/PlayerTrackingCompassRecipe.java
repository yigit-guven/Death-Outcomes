package net.yigitguven.death_outcomes;

import com.mojang.authlib.GameProfile;
import net.minecraft.ChatFormatting;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class PlayerTrackingCompassRecipe extends CustomRecipe {

    public static final String TRACKING_TAG = "death_outcomes_tracking_player";
    public static final String TRACKING_NAME_TAG = "death_outcomes_tracking_name";

    public PlayerTrackingCompassRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        if (!ServerConfig.ENABLE_PLAYER_TRACKING_COMPASS_RECIPE.get()) {
            return false;
        }

        ItemStack compass = ItemStack.EMPTY;
        ItemStack playerHead = ItemStack.EMPTY;
        int count = 0;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) {
                continue;
            }
            count++;

            if (stack.is(Items.COMPASS)) {
                if (!compass.isEmpty()) {
                    return false;
                }
                compass = stack;
            } else if (stack.is(Items.PLAYER_HEAD)) {
                if (!playerHead.isEmpty()) {
                    return false;
                }
                playerHead = stack;
            } else {
                return false;
            }
        }

        if (count != 2 || compass.isEmpty() || playerHead.isEmpty()) {
            return false;
        }

        ResolvableProfile profile = playerHead.get(DataComponents.PROFILE);
        return profile != null && (profile.id().isPresent() || !profile.name().orElse("").isEmpty());
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        if (!ServerConfig.ENABLE_PLAYER_TRACKING_COMPASS_RECIPE.get()) {
            return ItemStack.EMPTY;
        }

        ItemStack playerHead = ItemStack.EMPTY;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.is(Items.PLAYER_HEAD)) {
                playerHead = stack;
                break;
            }
        }

        if (playerHead.isEmpty()) {
            return ItemStack.EMPTY;
        }

        ResolvableProfile profile = playerHead.get(DataComponents.PROFILE);
        if (profile == null) {
            return ItemStack.EMPTY;
        }

        GameProfile gameProfile = profile.gameProfile();
        UUID targetUuid = gameProfile.getId();
        String targetName = gameProfile.getName();

        ItemStack result = new ItemStack(Items.COMPASS);

        // Purple legendary look
        result.set(DataComponents.RARITY, Rarity.EPIC);
        result.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true);

        String displayName = (targetName != null && !targetName.isBlank()) ? targetName : "Player";
        Component customName = Component.literal(displayName + "'s Compass")
                .withStyle(ChatFormatting.DARK_PURPLE);
        result.set(DataComponents.CUSTOM_NAME, customName);

        // Store custom tracking data
        CustomData.update(DataComponents.CUSTOM_DATA, result, tag -> {
            if (targetUuid != null) {
                tag.putUUID(TRACKING_TAG, targetUuid);
            }
            tag.putString(TRACKING_NAME_TAG, displayName);
        });

        return result;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.PLAYER_TRACKING_COMPASS_SERIALIZER.get();
    }
}
