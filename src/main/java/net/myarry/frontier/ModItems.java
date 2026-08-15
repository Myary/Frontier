package net.myarry.frontier;

import net.minecraft.world.item.Item;
import net.myarry.frontier.barrier.BigBarrierItem;
import net.myarry.frontier.barrier.SmallBarrierItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FrontierMod.MOD_ID);

    public static final DeferredItem<Item> SMALL_BARRIER =
            ITEMS.register(
                    "small_barrier",
                    () -> new SmallBarrierItem(
                            new Item.Properties()
                    )
            );

    public static final DeferredItem<Item> BIG_BARRIER =
            ITEMS.register(
                    "big_barrier",
                    () -> new BigBarrierItem(
                            new Item.Properties()
                    )
            );


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
