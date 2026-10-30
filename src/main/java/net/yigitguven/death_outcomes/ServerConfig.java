package net.yigitguven.death_outcomes;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue DROP_PLAYER_HEAD_ON_DEATH;
    public static final ModConfigSpec.BooleanValue DROP_PLAYER_HEAD_ONLY_WHEN_KILLED_BY_PLAYER;

    static {
        BUILDER.push("player_head_drops");

        DROP_PLAYER_HEAD_ON_DEATH = BUILDER
                .comment("Whether players should drop their head upon death.")
                .define("dropPlayerHeadOnDeath", true);

        DROP_PLAYER_HEAD_ONLY_WHEN_KILLED_BY_PLAYER = BUILDER
                .comment("Whether player heads should only drop if the death was caused directly or indirectly by another player.",
                         "If false (and dropPlayerHeadOnDeath is true), heads will drop on any death.")
                .define("dropPlayerHeadOnlyWhenKilledByPlayer", true);

        BUILDER.pop();
    }

    public static final ModConfigSpec SPEC = BUILDER.build();
}
