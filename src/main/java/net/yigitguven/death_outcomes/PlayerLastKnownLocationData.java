package net.yigitguven.death_outcomes;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerLastKnownLocationData extends SavedData {

    private static final String DATA_NAME = "death_outcomes_player_locations";
    private final Map<UUID, PlayerLocation> locations = new HashMap<>();

    public record PlayerLocation(String dimension, int x, int y, int z) {
        public CompoundTag toTag() {
            CompoundTag tag = new CompoundTag();
            tag.putString("Dimension", dimension);
            tag.putInt("X", x);
            tag.putInt("Y", y);
            tag.putInt("Z", z);
            return tag;
        }

        public static PlayerLocation fromTag(CompoundTag tag) {
            return new PlayerLocation(
                    tag.getString("Dimension"),
                    tag.getInt("X"),
                    tag.getInt("Y"),
                    tag.getInt("Z")
            );
        }
    }

    public PlayerLastKnownLocationData() {
    }

    public static PlayerLastKnownLocationData load(CompoundTag tag, HolderLookup.Provider registries) {
        PlayerLastKnownLocationData data = new PlayerLastKnownLocationData();
        ListTag list = tag.getList("Locations", Tag.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag entry = list.getCompound(i);
            UUID uuid = entry.getUUID("UUID");
            PlayerLocation loc = PlayerLocation.fromTag(entry.getCompound("Location"));
            data.locations.put(uuid, loc);
        }
        return data;
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag tag, HolderLookup.Provider registries) {
        ListTag list = new ListTag();
        for (Map.Entry<UUID, PlayerLocation> entry : locations.entrySet()) {
            CompoundTag item = new CompoundTag();
            item.putUUID("UUID", entry.getKey());
            item.put("Location", entry.getValue().toTag());
            list.add(item);
        }
        tag.put("Locations", list);
        return tag;
    }

    public static SavedData.Factory<PlayerLastKnownLocationData> factory() {
        return new SavedData.Factory<>(
                PlayerLastKnownLocationData::new,
                PlayerLastKnownLocationData::load
        );
    }

    public static PlayerLastKnownLocationData get(ServerLevel level) {
        ServerLevel overworld = level.getServer().overworld();
        return overworld.getDataStorage().computeIfAbsent(factory(), DATA_NAME);
    }

    public void updateLocation(UUID uuid, String dimension, int x, int y, int z) {
        locations.put(uuid, new PlayerLocation(dimension, x, y, z));
        setDirty();
    }

    public PlayerLocation getLocation(UUID uuid) {
        return locations.get(uuid);
    }
}

