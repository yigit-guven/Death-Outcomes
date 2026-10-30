package net.yigitguven.death_outcomes;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayerHeadDropHistoryData extends SavedData {

    private static final String DATA_NAME = "death_outcomes_head_drop_history";
    private final Set<UUID> playersWhoseHeadsDropped = new HashSet<>();

    public PlayerHeadDropHistoryData() {
    }

    public static PlayerHeadDropHistoryData load(CompoundTag tag, HolderLookup.Provider registries) {
        PlayerHeadDropHistoryData data = new PlayerHeadDropHistoryData();
        ListTag list = tag.getList("DroppedHeadPlayers", Tag.TAG_STRING);
        for (int i = 0; i < list.size(); i++) {
            try {
                data.playersWhoseHeadsDropped.add(UUID.fromString(list.getString(i)));
            } catch (IllegalArgumentException ignored) {
            }
        }
        return data;
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag tag, HolderLookup.Provider registries) {
        ListTag list = new ListTag();
        for (UUID uuid : playersWhoseHeadsDropped) {
            list.add(StringTag.valueOf(uuid.toString()));
        }
        tag.put("DroppedHeadPlayers", list);
        return tag;
    }

    public static SavedData.Factory<PlayerHeadDropHistoryData> factory() {
        return new SavedData.Factory<>(
                PlayerHeadDropHistoryData::new,
                PlayerHeadDropHistoryData::load
        );
    }

    public static PlayerHeadDropHistoryData get(ServerLevel level) {
        ServerLevel overworld = level.getServer().overworld();
        return overworld.getDataStorage().computeIfAbsent(factory(), DATA_NAME);
    }

    public boolean hasDroppedHead(UUID uuid) {
        return playersWhoseHeadsDropped.contains(uuid);
    }

    public void markHeadDropped(UUID uuid) {
        if (playersWhoseHeadsDropped.add(uuid)) {
            setDirty();
        }
    }
}

