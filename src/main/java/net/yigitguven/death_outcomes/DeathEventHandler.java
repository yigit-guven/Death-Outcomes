package net.yigitguven.death_outcomes;

import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ResolvableProfile;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;

@EventBusSubscriber(modid = DeathOutcomes.MODID)
public class DeathEventHandler {

    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer victim)) {
            return;
        }

        if (!ServerConfig.DROP_PLAYER_HEAD_ON_DEATH.get()) {
            return;
        }

        DamageSource damageSource = event.getSource();
        if (ServerConfig.DROP_PLAYER_HEAD_ONLY_WHEN_KILLED_BY_PLAYER.get()) {
            if (!isCausedByPlayer(damageSource, victim)) {
                return;
            }
        }

        ItemStack headStack = new ItemStack(Items.PLAYER_HEAD);
        headStack.set(DataComponents.PROFILE, new ResolvableProfile(victim.getGameProfile()));

        ItemEntity headEntity = new ItemEntity(
                victim.level(),
                victim.getX(),
                victim.getY(),
                victim.getZ(),
                headStack
        );
        headEntity.setDefaultPickUpDelay();

        event.getDrops().add(headEntity);
    }

    private static boolean isCausedByPlayer(DamageSource source, ServerPlayer victim) {
        Entity directEntity = source.getDirectEntity();
        if (directEntity instanceof ServerPlayer attacker && !attacker.getUUID().equals(victim.getUUID())) {
            return true;
        }

        Entity trueSource = source.getEntity();
        if (trueSource instanceof ServerPlayer attacker && !attacker.getUUID().equals(victim.getUUID())) {
            return true;
        }

        return false;
    }
}

