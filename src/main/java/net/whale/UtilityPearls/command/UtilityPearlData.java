package net.whale.UtilityPearls.command;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

public class UtilityPearlData extends SavedData {
    public static final String ID = "utilitypearl_data";

    private float speed = 2.5F;
    private int lifetime = 75;

    public static UtilityPearlData load(CompoundTag tag,HolderLookup.Provider provider) {
        UtilityPearlData data = new UtilityPearlData();
        data.speed = tag.getFloat("speed");
        data.lifetime = tag.getInt("lifetime");
        return data;
    }
    public static final SavedData.Factory<UtilityPearlData> FACTORY = new SavedData.Factory<>(
            UtilityPearlData::new,
            UtilityPearlData::load,
            DataFixTypes.LEVEL
    );
    public static UtilityPearlData get(ServerLevel storage) {
        return storage.getDataStorage().computeIfAbsent(FACTORY, ID);
    }
    @Override
    public @NotNull CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
        tag.putFloat("speed", speed);
        tag.putInt("lifetime", lifetime);
        return tag;
    }
    public float getSpeed() { return speed; }
    public void setSpeed(float speed) {
        this.speed = speed;
        setDirty();
    }
    public int getLifetime() { return lifetime; }
    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
        setDirty();
    }
}
