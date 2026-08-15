package net.myarry.frontier.barrier;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;
import net.myarry.frontier.FrontierConfig;

public class BorderExpansionData extends SavedData {

    public static final String DATA_NAME = "border_expansion_data";

    public static final Factory<BorderExpansionData> FACTORY =
            new Factory<>(
                    BorderExpansionData::new,
                    BorderExpansionData::load
            );

    private int modeTimer;

    private int limitTimer;

    private double gainedThisPeriod;

    public BorderExpansionData() {
        this.modeTimer = 0;
        this.limitTimer = 0;
        this.gainedThisPeriod = 0.0D;
    }

    public static BorderExpansionData load(
            CompoundTag tag,
            HolderLookup.Provider provider
    ) {

        BorderExpansionData data =
                new BorderExpansionData();

        data.modeTimer =
                tag.getInt("ModeTimer");

        data.limitTimer =
                tag.getInt("LimitTimer");

        data.gainedThisPeriod =
                tag.getDouble("GainedThisPeriod");

        return data;
    }

    @Override
    public CompoundTag save(
            CompoundTag tag,
            HolderLookup.Provider provider
    ) {

        tag.putInt(
                "ModeTimer",
                modeTimer
        );

        tag.putInt(
                "LimitTimer",
                limitTimer
        );

        tag.putDouble(
                "GainedThisPeriod",
                gainedThisPeriod
        );

        return tag;
    }

    public int getModeTimer() {
        return modeTimer;
    }

    public void setModeTimer(int modeTimer) {
        this.modeTimer = modeTimer;
        setDirty();
    }

    public int getLimitTimer() {
        return limitTimer;
    }

    public void setLimitTimer(int limitTimer) {
        this.limitTimer = limitTimer;
        setDirty();
    }

    public double getGainedThisPeriod() {
        return gainedThisPeriod;
    }

    public void setGainedThisPeriod(double gainedThisPeriod) {
        this.gainedThisPeriod = gainedThisPeriod;
        setDirty();
    }

    public void tickTimers() {
        modeTimer++;
        limitTimer++;
        setDirty();
    }

    public void resetPeriod() {
        modeTimer = 0;
        limitTimer = 0;
        gainedThisPeriod = 0.0D;

        setDirty();
    }

    public double tryAddExpansion(double amount) {

        if (amount <= 0.0D) {
            return 0.0D;
        }
        if (!FrontierConfig.BOOLIMIT) {

            gainedThisPeriod += amount;
            setDirty();

            return amount;
        }
        double remaining =
                FrontierConfig.LIMIT - gainedThisPeriod;

        if (remaining <= 0.0D) {
            return 0.0D;
        }

        double allowed =
                Math.min(amount, remaining);

        gainedThisPeriod += allowed;

        setDirty();

        return allowed;
    }
}