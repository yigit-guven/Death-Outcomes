package net.yigitguven.death_outcomes;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(DeathOutcomes.MODID)
public class DeathOutcomes {
    public static final String MODID = "death_outcomes";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DeathOutcomes(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("Initializing {}", MODID);
        modContainer.registerConfig(ModConfig.Type.SERVER, ServerConfig.SPEC);
    }
}
