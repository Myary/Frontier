package net.myarry.frontier;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.Commands;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber
public class ModCommands {

    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {

        event.getDispatcher().register(
                Commands.literal("frontierconfig")
                        .requires(source -> source.hasPermission(2))

                        .then(Commands.literal("set")

                                .then(Commands.literal("boolimit")
                                        .then(Commands.argument("value", BoolArgumentType.bool())
                                                .executes(context -> {
                                                    boolean value =
                                                            BoolArgumentType.getBool(context, "value");

                                                    FrontierConfig.BOOLIMIT = value;

                                                    return 1;
                                                })
                                        )
                                )
                                .then(Commands.literal("booadvensents")
                                        .then(Commands.argument("value", BoolArgumentType.bool())
                                                .executes(context -> {
                                                    boolean value =
                                                            BoolArgumentType.getBool(context, "value");

                                                    FrontierConfig.BOOADVENSENTS = value;

                                                    return 1;
                                                })
                                        )
                                )
                                .then(Commands.literal("bootime_mode")
                                        .then(Commands.argument("value", BoolArgumentType.bool())
                                                .executes(context -> {
                                                    boolean value =
                                                            BoolArgumentType.getBool(context, "value");

                                                    FrontierConfig.BOOTIME_MODE = value;

                                                    return 1;
                                                })
                                        )
                                )

                                .then(Commands.literal("limit")
                                        .then(Commands.argument("value", IntegerArgumentType.integer())
                                                .executes(context -> {
                                                    int value =
                                                            IntegerArgumentType.getInteger(context, "value");

                                                    FrontierConfig.LIMIT = value;

                                                    return 1;
                                                })
                                        )
                                )
                                .then(Commands.literal("timelimit")
                                        .then(Commands.argument("value", IntegerArgumentType.integer())
                                                .executes(context -> {
                                                    int value =
                                                            IntegerArgumentType.getInteger(context, "value");

                                                    FrontierConfig.TIME_LIMIT = value;

                                                    return 1;
                                                })
                                        )
                                )
                                .then(Commands.literal("barrier_increase_size")
                                        .then(Commands.argument("value", IntegerArgumentType.integer())
                                                .executes(context -> {
                                                    int value =
                                                            IntegerArgumentType.getInteger(context, "value");

                                                    FrontierConfig.BARRIER_INCREASE_SIZE = value;

                                                    return 1;
                                                })
                                        )
                                )
                                .then(Commands.literal("time_mode_speed")
                                        .then(Commands.argument("value", IntegerArgumentType.integer())
                                                .executes(context -> {
                                                    int value =
                                                            IntegerArgumentType.getInteger(context, "value");

                                                    FrontierConfig.TIME_MODE_SPEED = value;

                                                    return 1;
                                                })
                                        )
                                )

                        )
        );
    }
}
