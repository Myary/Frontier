package net.myarry.frontier.barrier;

import net.minecraft.server.MinecraftServer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

@EventBusSubscriber
public class BarrierTickHandler {

    public static final BarrierManager BARRIER_MANAGER =
            new BarrierManager();

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        MinecraftServer server = event.getServer();
        BARRIER_MANAGER.tick(server);
    }
}
