package net.yigitguven.death_outcomes;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue DROP_PLAYER_HEAD_ON_DEATH;
    public static final ModConfigSpec.BooleanValue DROP_PLAYER_HEAD_ONLY_WHEN_KILLED_BY_PLAYER;
    public static final ModConfigSpec.BooleanValue DROP_PLAYER_HEAD_ONLY_ONCE_EVER;
    public static final ModConfigSpec.BooleanValue ENABLE_PLAYER_TRACKING_COMPASS_RECIPE;
    public static final ModConfigSpec.BooleanValue ENABLE_PLAYER_COMPASS_DISMANTLE_RECIPE;

    static {
        BUILDER.push("player_head_drops");

        DROP_PLAYER_HEAD_ON_DEATH = BUILDER
                .comment("Whether players should drop their head upon death.")
                .define("dropPlayerHeadOnDeath", true);

        DROP_PLAYER_HEAD_ONLY_WHEN_KILLED_BY_PLAYER = BUILDER
                .comment("Whether player heads should only drop if the death was caused directly or indirectly by another player.",
                         "If false (and dropPlayerHeadOnDeath is true), heads will drop on any death.")
                .define("dropPlayerHeadOnlyWhenKilledByPlayer", true);

        DROP_PLAYER_HEAD_ONLY_ONCE_EVER = BUILDER
                .comment("Whether a player's head should only ever drop once. Once dropped, subsequent deaths will not drop their head.",
                         "Persisted across server restarts per world.")
                .define("dropPlayerHeadOnlyOnceEver", false);

        BUILDER.pop();

        BUILDER.push("tracking_compass");

        ENABLE_PLAYER_TRACKING_COMPASS_RECIPE = BUILDER
                .comment("Whether crafting a tracking compass by combining a compass and player head is enabled.")
                .define("enablePlayerTrackingCompassRecipe", true);

        ENABLE_PLAYER_COMPASS_DISMANTLE_RECIPE = BUILDER
                .comment("Whether placing a Player's Compass in a crafting grid dismantles/recovers the player's head back.",
                         "By default false.")
                .define("enablePlayerCompassDismantleRecipe", false);

        BUILDER.pop();
    }

    public static final ModConfigSpec SPEC = BUILDER.build();
}
