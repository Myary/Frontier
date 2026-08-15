package net.myarry.frontier.client;

import net.minecraft.world.item.CreativeModeTabs;
import net.myarry.frontier.ModItems;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@EventBusSubscriber
public class CreativeTabHandler {

    @SubscribeEvent
    public static void onCreativeTab(
            BuildCreativeModeTabContentsEvent event
    ) {

        if (event.getTabKey() != CreativeModeTabs.TOOLS_AND_UTILITIES) {
            return;
        }

        event.accept(ModItems.SMALL_BARRIER);
        event.accept(ModItems.BIG_BARRIER);
    }
}