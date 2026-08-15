package net.myarry.frontier.barrier;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public class WorldBorderData extends SavedData {

    private static final String NAME = "world_border_data";

    private boolean initialized;

    public WorldBorderData() {
        this.initialized = false;
    }

    public static WorldBorderData load(
            CompoundTag tag,
            HolderLookup.Provider provider
    ) {
        WorldBorderData data = new WorldBorderData();

        data.initialized = tag.getBoolean("Initialized");

        return data;
    }

    @Override
    public CompoundTag save(
            CompoundTag tag,
            HolderLookup.Provider provider
    ) {
        tag.putBoolean("Initialized", initialized);

        return tag;
    }

    public static WorldBorderData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
                new SavedData.Factory<>(
                        WorldBorderData::new,
                        WorldBorderData::load,
                        null
                ),
                NAME
        );
    }

    public boolean initialized() {
        return initialized;
    }

    public void setInitialized() {
        this.initialized = true;
        setDirty();
    }
}