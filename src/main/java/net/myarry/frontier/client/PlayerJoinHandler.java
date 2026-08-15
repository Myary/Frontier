package net.myarry.frontier.client;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.border.WorldBorder;
import net.myarry.frontier.FrontierConfig;
import net.myarry.frontier.barrier.WorldBorderData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber
public class PlayerJoinHandler {

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {

        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        ServerLevel level = player.serverLevel();

        WorldBorderData data = WorldBorderData.get(level);

        if (data.initialized()) {
            return;
        }

        BlockPos pos = player.blockPosition();

        level.setDefaultSpawnPos(pos, 0.0F);

        WorldBorder border = level.getWorldBorder();

        border.setCenter(
                pos.getX() + 0.5D,
                pos.getZ() + 0.5D
        );

        border.setSize(FrontierConfig.INITIAL_BARRIER);

        data.setInitialized();
    }
}