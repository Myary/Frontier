package net.myarry.frontier.barrier;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.myarry.frontier.FrontierConfig;

public class SmallBarrierItem extends Item {

    public SmallBarrierItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(
            Level level,
            Player player,
            InteractionHand hand
    ) {

        ItemStack stack = player.getItemInHand(hand);

        if (level.isClientSide()) {
            return InteractionResultHolder.success(stack);
        }

        if (!(player instanceof ServerPlayer serverPlayer)) {
            return InteractionResultHolder.pass(stack);
        }

        BarrierTickHandler.BARRIER_MANAGER
                .increaseFromItem(
                        serverPlayer.server,
                        serverPlayer,
                        FrontierConfig.SMALL_BAR
                );
        player.getCooldowns().addCooldown(
                this,
                20 * 10
        );

        return InteractionResultHolder.success(stack);
    }
}