package net.myarry.frontier.barrier;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.border.WorldBorder;
import net.myarry.frontier.FrontierConfig;

public class BarrierManager {

    /**
     * Вызывается один раз за серверный тик.
     */
    public void tick(MinecraftServer server) {

        ServerLevel overworld = server.overworld();

        BorderExpansionData data =
                overworld.getDataStorage().computeIfAbsent(
                        BorderExpansionData.FACTORY,
                        BorderExpansionData.DATA_NAME
                );
        handleLimitTimer(data);
        // АВТОРАСШИРЕНИЕ ВЫКЛЮЧЕНО
        if (!FrontierConfig.BOOTIME_MODE){
            return;
        }

        if (!FrontierConfig.BOOLIMIT
                || data.getGainedThisPeriod() < FrontierConfig.LIMIT) {

            data.setModeTimer(
                    data.getModeTimer() + 1
            );
        }
        handleBarrierIncrease(server, data);
    }

    /**
     * Таймер сброса лимита.
     */
    private void handleLimitTimer(
            BorderExpansionData data
    ) {

        if (!FrontierConfig.BOOLIMIT) {
            return;
        }

        int resetTimeTicks =
                secondsToTicks(
                        FrontierConfig.TIME_LIMIT
                );
        if (data.getLimitTimer() >= resetTimeTicks) {
            data.setLimitTimer(0);
            data.setGainedThisPeriod(0.0D);
            data.setModeTimer(0);
        }
        else {

            data.setLimitTimer(
                    data.getLimitTimer() + 1
            );
        }
    }
    public void increaseFromItem(
            MinecraftServer server,
            ServerPlayer player,
            double amount
    ) {
        if (amount <= 0.0D) {
            return;
        }

        ServerLevel overworld = server.overworld();

        BorderExpansionData data =
                overworld.getDataStorage().computeIfAbsent(
                        BorderExpansionData.FACTORY,
                        BorderExpansionData.DATA_NAME
                );

        /*
         * Проверяем лимит.
         */
        if (FrontierConfig.BOOLIMIT) {

            double remaining =
                    FrontierConfig.LIMIT
                            - data.getGainedThisPeriod();

            /*
             * Лимит полностью исчерпан.
             */
            if (remaining <= 0.0D) {

                player.displayClientMessage(
                        Component.translatable(
                                "message.frontier.barrier_limit"
                        ),
                        true
                );

                return;
            }
            amount = Math.min(
                    amount,
                    remaining
            );
        }

        WorldBorder border =
                overworld.getWorldBorder();

        double currentSize =
                border.getSize();

        double newSize =
                currentSize + amount;

        if (newSize <= 1.0D) {
            newSize = 1.0D;
        }

        double realIncrease =
                newSize - currentSize;

        if (realIncrease <= 0.0D) {
            return;
        }

        long speedTicks =
                secondsToTicks(
                        FrontierConfig.BARRIER_SPEED
                );

        long speedMilliseconds =
                speedTicks * 50L;

        border.lerpSizeBetween(
                currentSize,
                newSize,
                speedMilliseconds
        );

        data.setGainedThisPeriod(
                data.getGainedThisPeriod()
                        + realIncrease
        );
        if (!player.getAbilities().instabuild) {

            ItemStack stack =
                    player.getMainHandItem();
            stack.shrink(1);
        }
    }

    /**
     * Автоматическое расширение барьера.
     */
    private void handleBarrierIncrease(
            MinecraftServer server,
            BorderExpansionData data
    ) {

        int modeTimeTicks =
                secondsToTicks(
                        FrontierConfig.TIME_MODE_SPEED
                );
        if (data.getModeTimer() < modeTimeTicks) {
            return;
        }
        double increase =
                FrontierConfig.BARRIER_INCREASE_SIZE;
        if (increase <= 0.0D) {
            data.setModeTimer(0);
            return;
        }
        if (FrontierConfig.BOOLIMIT) {

            double remaining =
                    FrontierConfig.LIMIT
                            - data.getGainedThisPeriod();
            if (remaining <= 0.0D) {
                return;
            }
            increase = Math.min(increase,remaining);
        }
        data.setModeTimer(0);
        ServerLevel overworld =server.overworld();

        WorldBorder border =  overworld.getWorldBorder();

        double currentSize = border.getSize();

        double newSize =currentSize + increase;

        if (newSize <= 1.0D) {
            newSize = 1.0D;
        }

        double realIncrease = newSize - currentSize;

        if (realIncrease <= 0.0D) {
            return;
        }

        long speedTicks =secondsToTicks(FrontierConfig.BARRIER_SPEED);

        long speedMilliseconds =
                speedTicks * 50L;

        border.lerpSizeBetween(
                currentSize,
                newSize,
                speedMilliseconds
        );

        data.setGainedThisPeriod(
                data.getGainedThisPeriod()
                        + realIncrease
        );
    }
    /**
     * Расширение от достижения.
     */
    public void increaseFromAdvancement(MinecraftServer server,double increase)
    {
        if (!FrontierConfig.BOOADVENSENTS) {
            return;
        }
        if (increase <= 0.0D) {
            return;
        }
        ServerLevel overworld =
                server.overworld();
        BorderExpansionData data =
                overworld.getDataStorage().computeIfAbsent(
                        BorderExpansionData.FACTORY,
                        BorderExpansionData.DATA_NAME
                );
        if (FrontierConfig.BOOLIMIT) {

            double remaining =
                    FrontierConfig.LIMIT
                            - data.getGainedThisPeriod();
            if (remaining <= 0.0D) {
                return;
            }
            increase = Math.min(increase,remaining);
        }

        WorldBorder border =overworld.getWorldBorder();

        double currentSize = border.getSize();

        double newSize =currentSize + increase;

        if (newSize <= 1.0D) {
            newSize = 1.0D;
        }

        double realIncrease =newSize - currentSize;

        if (realIncrease <= 0.0D) {
            return;
        }

        long speedTicks =secondsToTicks(FrontierConfig.BARRIER_SPEED);

        long speedMilliseconds =speedTicks * 50L;

        border.lerpSizeBetween(
                currentSize,
                newSize,
                speedMilliseconds
        );

        data.setGainedThisPeriod(
                data.getGainedThisPeriod()
                        + realIncrease
        );
    }
    private int secondsToTicks(
            double seconds
    ) {

        return Math.max(
                1,
                (int) Math.round(
                        seconds * 20.0D
                )
        );
    }
}