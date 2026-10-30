package net.yigitguven.death_outcomes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.LodestoneTracker;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.Optional;
import java.util.UUID;

@EventBusSubscriber(modid = DeathOutcomes.MODID)
public class CompassTrackingHandler {

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            savePlayerPosition(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player.level().isClientSide() || !(player instanceof ServerPlayer serverPlayer)) {
            return;
        }

        // Periodically update player's last known location and compasses
        if (serverPlayer.tickCount % 20 == 0) {
            savePlayerPosition(serverPlayer);
            updateCompassesInInventory(serverPlayer);
        }
    }

    private static void savePlayerPosition(ServerPlayer player) {
        PlayerLastKnownLocationData data = PlayerLastKnownLocationData.get(player.serverLevel());
        data.updateLocation(
                player.getUUID(),
                player.level().dimension().location().toString(),
                player.getBlockX(),
                player.getBlockY(),
                player.getBlockZ()
        );
    }

    private static void updateCompassesInInventory(ServerPlayer holdingPlayer) {
        MinecraftServer server = holdingPlayer.getServer();
        if (server == null) {
            return;
        }

        PlayerLastKnownLocationData locationData = PlayerLastKnownLocationData.get(holdingPlayer.serverLevel());

        for (int i = 0; i < holdingPlayer.getInventory().getContainerSize(); i++) {
            ItemStack stack = holdingPlayer.getInventory().getItem(i);
            if (stack.isEmpty() || !stack.is(Items.COMPASS)) {
                continue;
            }

            CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
            if (customData == null) {
                continue;
            }

            CompoundTag tag = customData.copyTag();
            if (!tag.hasUUID(PlayerTrackingCompassRecipe.TRACKING_TAG)) {
                continue;
            }

            UUID targetUuid = tag.getUUID(PlayerTrackingCompassRecipe.TRACKING_TAG);
            ServerPlayer onlineTarget = server.getPlayerList().getPlayer(targetUuid);

            GlobalPos targetPos = null;

            if (onlineTarget != null) {
                targetPos = GlobalPos.of(
                        onlineTarget.level().dimension(),
                        onlineTarget.blockPosition()
                );
            } else {
                PlayerLastKnownLocationData.PlayerLocation lastLoc = locationData.getLocation(targetUuid);
                if (lastLoc != null) {
                    ResourceLocation dimLoc = ResourceLocation.tryParse(lastLoc.dimension());
                    if (dimLoc != null) {
                        ResourceKey<Level> dimKey = ResourceKey.create(Registries.DIMENSION, dimLoc);
                        targetPos = GlobalPos.of(dimKey, new BlockPos(lastLoc.x(), lastLoc.y(), lastLoc.z()));
                    }
                }
            }

            if (targetPos != null) {
                LodestoneTracker currentTracker = stack.get(DataComponents.LODESTONE_TRACKER);
                boolean needsUpdate = true;
                if (currentTracker != null && currentTracker.target().isPresent()) {
                    GlobalPos currentPos = currentTracker.target().get();
                    if (currentPos.dimension().equals(targetPos.dimension()) && currentPos.pos().equals(targetPos.pos())) {
                        needsUpdate = false;
                    }
                }

                if (needsUpdate) {
                    stack.set(DataComponents.LODESTONE_TRACKER, new LodestoneTracker(Optional.of(targetPos), false));
                }
            }
        }
    }
}

