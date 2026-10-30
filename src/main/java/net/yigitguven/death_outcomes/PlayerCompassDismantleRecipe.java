package net.yigitguven.death_outcomes;

import com.mojang.authlib.GameProfile;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class PlayerCompassDismantleRecipe extends CustomRecipe {

    public PlayerCompassDismantleRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        if (!ServerConfig.ENABLE_PLAYER_COMPASS_DISMANTLE_RECIPE.get()) {
            return false;
        }

        ItemStack targetStack = ItemStack.EMPTY;
        int count = 0;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) {
                continue;
            }
            count++;
            if (!stack.is(Items.COMPASS)) {
                return false;
            }
            targetStack = stack;
        }

        if (count != 1 || targetStack.isEmpty()) {
            return false;
        }

        CustomData customData = targetStack.get(DataComponents.CUSTOM_DATA);
        if (customData == null) {
            return false;
        }

        CompoundTag tag = customData.copyTag();
        return tag.hasUUID(PlayerTrackingCompassRecipe.TRACKING_TAG) || tag.contains(PlayerTrackingCompassRecipe.TRACKING_NAME_TAG);
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        if (!ServerConfig.ENABLE_PLAYER_COMPASS_DISMANTLE_RECIPE.get()) {
            return ItemStack.EMPTY;
        }

        ItemStack compass = ItemStack.EMPTY;
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (!stack.isEmpty() && stack.is(Items.COMPASS)) {
                compass = stack;
                break;
            }
        }

        if (compass.isEmpty()) {
            return ItemStack.EMPTY;
        }

        CustomData customData = compass.get(DataComponents.CUSTOM_DATA);
        if (customData == null) {
            return ItemStack.EMPTY;
        }

        CompoundTag tag = customData.copyTag();
        UUID targetUuid = tag.hasUUID(PlayerTrackingCompassRecipe.TRACKING_TAG) ? tag.getUUID(PlayerTrackingCompassRecipe.TRACKING_TAG) : null;
        String targetName = tag.getString(PlayerTrackingCompassRecipe.TRACKING_NAME_TAG);

        if (targetUuid == null && (targetName == null || targetName.isBlank())) {
            return ItemStack.EMPTY;
        }

        ItemStack headStack = new ItemStack(Items.PLAYER_HEAD);
        GameProfile profile = new GameProfile(
                targetUuid != null ? targetUuid : UUID.nameUUIDFromBytes(targetName.getBytes()),
                (targetName != null && !targetName.isBlank()) ? targetName : "Player"
        );
        headStack.set(DataComponents.PROFILE, new ResolvableProfile(profile));

        return headStack;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 1;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.PLAYER_COMPASS_DISMANTLE_SERIALIZER.get();
    }
}

