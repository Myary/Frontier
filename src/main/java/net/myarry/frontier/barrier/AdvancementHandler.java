package net.myarry.frontier.barrier;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.server.level.ServerLevel;
import net.myarry.frontier.FrontierConfig;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.AdvancementEvent;


@EventBusSubscriber
public class AdvancementHandler {

    @SubscribeEvent
    public static void onAdvancement(
            AdvancementEvent.AdvancementEarnEvent event
    ) {

        if (!FrontierConfig.BOOADVENSENTS) {
            return;
        }

        ServerLevel level =
                (ServerLevel) event.getEntity().level();

        AdvancementHolder holder =
                event.getAdvancement();

        Advancement advancement =
                holder.value();

        AdvancementType type = advancement.display()
                .orElseThrow()
                .getType();

        double increase = switch (type) {
            case TASK -> FrontierConfig.ADVENSENTS;
            case GOAL -> FrontierConfig.GOAL;
            case CHALLENGE -> FrontierConfig.CHALLENGE;
        };

        BarrierTickHandler.BARRIER_MANAGER
                .increaseFromAdvancement(
                        level.getServer(),
                        increase
                );
    }
}
