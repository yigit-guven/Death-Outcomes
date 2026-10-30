package net.yigitguven.death_outcomes;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerContainerEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@EventBusSubscriber(modid = DeathOutcomes.MODID)
public class ServerSideCraftingFixer {

    @SubscribeEvent
    public static void onContainerOpen(PlayerContainerEvent.Open event) {
        if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) {
            return;
        }

        AbstractContainerMenu menu = event.getContainer();

        menu.addSlotListener(new ContainerListener() {
            @Override
            public void slotChanged(AbstractContainerMenu containerMenu, int slotIndex, ItemStack stack) {
                if (!(containerMenu instanceof CraftingMenu || containerMenu instanceof InventoryMenu)) {
                    return;
                }

                int resultSlotIndex = 0;
                Slot resultSlot = containerMenu.getSlot(resultSlotIndex);

                if (resultSlot.hasItem()) {
                    return;
                }

                int gridStart = 1;
                int gridSize = (containerMenu instanceof CraftingMenu) ? 9 : 4;
                int width = (containerMenu instanceof CraftingMenu) ? 3 : 2;
                int height = width;

                List<ItemStack> items = new ArrayList<>();
                boolean hasAny = false;
                for (int i = 0; i < gridSize; i++) {
                    Slot slot = containerMenu.getSlot(gridStart + i);
                    ItemStack item = slot.getItem();
                    items.add(item);
                    if (!item.isEmpty()) {
                        hasAny = true;
                    }
                }

                if (!hasAny) {
                    return;
                }

                CraftingInput input = CraftingInput.of(width, height, items);
                Optional<RecipeHolder<CraftingRecipe>> match = serverPlayer.serverLevel().getRecipeManager()
                        .getRecipeFor(RecipeType.CRAFTING, input, serverPlayer.serverLevel());

                if (match.isPresent() && match.get().value() instanceof PlayerTrackingCompassRecipe customRecipe) {
                    ItemStack assembled = customRecipe.assemble(input, serverPlayer.serverLevel().registryAccess());
                    if (!assembled.isEmpty()) {
                        resultSlot.set(assembled);
                        containerMenu.broadcastChanges();
                    }
                }
            }

            @Override
            public void dataChanged(AbstractContainerMenu containerMenu, int dataIndex, int value) {
            }
        });
    }
}
