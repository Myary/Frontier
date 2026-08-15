    package net.myarry.frontier.client;

    import me.shedaniel.clothconfig2.api.ConfigBuilder;
    import me.shedaniel.clothconfig2.api.ConfigCategory;
    import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;

    import net.minecraft.ChatFormatting;
    import net.minecraft.client.gui.screens.Screen;
    import net.minecraft.network.chat.Component;
    import net.myarry.frontier.FrontierConfig;
    import net.myarry.frontier.FrontierMod;

    public class ModConfigScreen {



        public static Screen create(Screen parent) {

            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Component.translatable(
                            "title." + FrontierMod.MOD_ID + ".config"
                    ));

            builder.setSavingRunnable(FrontierConfig::save);
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            // GENERAL

            ConfigCategory general = builder.getOrCreateCategory(
                    Component.translatable(
                            "category." + FrontierMod.MOD_ID + ".general"
                    ).withStyle(ChatFormatting.AQUA)
            );
            // INITIAL BARRIER
            general.addEntry(
                    entryBuilder.startIntField(Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".initial_barrier"
                                    ).withStyle(ChatFormatting.AQUA),
                                    FrontierConfig.INITIAL_BARRIER
                            )
                            .setDefaultValue(1)
                            .setTooltip(
                                    Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".initial_barrier.tooltip"
                                    )
                            )
                            .setSaveConsumer(value ->
                                    FrontierConfig.INITIAL_BARRIER = value
                            )
                            .build()
            );
            // BARRIER SPEED
            general.addEntry(
                    entryBuilder.startDoubleField(Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".barrier_speed"
                                    ).withStyle(ChatFormatting.AQUA),
                                    FrontierConfig.BARRIER_SPEED
                            )
                            .setDefaultValue(1)
                            .setTooltip(
                                    Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".barrier_speed.tooltip"
                                    )
                            )
                            .setSaveConsumer(value ->
                                    FrontierConfig.BARRIER_SPEED = value
                            )
                            .build()
            );
            // BOO LIMIT
            general.addEntry(
                    entryBuilder.startBooleanToggle(
                                    Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".boolimit"
                                    ).withStyle(ChatFormatting.AQUA),
                                    FrontierConfig.BOOLIMIT
                            )
                            .setDefaultValue(false)
                            .setTooltip(
                                    Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".boolimit.tooltip"
                                    )
                            )
                            .setSaveConsumer(value ->
                                    FrontierConfig.BOOLIMIT = value
                            )
                            .build()
            );
            // LIMIT
            general.addEntry(
                    entryBuilder.startIntField(
                                    Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".limit"
                                    ).withStyle(ChatFormatting.AQUA),
                                    FrontierConfig.LIMIT
                            )
                            .setDefaultValue(64)
                            .setTooltip(
                                    Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".limit.tooltip"
                                    )
                            )
                            .setSaveConsumer(value ->
                                    FrontierConfig.LIMIT = value
                            )
                            .build()
            );
            //TIME LIMIT
            general.addEntry(
                    entryBuilder.startIntField(
                                    Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".timelimit"
                                    ).withStyle(ChatFormatting.AQUA),
                                    FrontierConfig.TIME_LIMIT
                            )
                            .setDefaultValue(3600)
                            .setTooltip(
                                    Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".timelimit.tooltip"
                                    )
                            )
                            .setSaveConsumer(value ->
                                    FrontierConfig.TIME_LIMIT = value
                            )
                            .build()
            );
            //SMALL BAR
            general.addEntry(
                    entryBuilder.startIntField(Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".small_bar"
                                    ).withStyle(ChatFormatting.AQUA),
                                    FrontierConfig.SMALL_BAR
                            )
                            .setDefaultValue(4)
                            .setTooltip(
                                    Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".small_bar.tooltip"
                                    )
                            )
                            .setSaveConsumer(value ->
                                    FrontierConfig.SMALL_BAR = value
                            )
                            .build()
            );
            //BIG BAR
            general.addEntry(
                    entryBuilder.startIntField(Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".big_bar"
                                    ).withStyle(ChatFormatting.AQUA),
                                    FrontierConfig.BIG_BAR
                            )
                            .setDefaultValue(8)
                            .setTooltip(
                                    Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".big_bar.tooltip"
                                    )
                            )
                            .setSaveConsumer(value ->
                                    FrontierConfig.BIG_BAR = value
                            )
                            .build()
            );

            // MODE SETTINGS

            ConfigCategory mode_setting = builder.getOrCreateCategory(
                    Component.translatable(
                            "category." + FrontierMod.MOD_ID + ".mode_setting"
                    ).withStyle(ChatFormatting.AQUA)
            );
            //BOO TIME MODE
            mode_setting.addEntry(
                    entryBuilder.startBooleanToggle(
                                    Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".bootimemode"
                                    ).withStyle(ChatFormatting.GOLD),
                                    FrontierConfig.BOOTIME_MODE
                            )
                            .setDefaultValue(false)
                            .setTooltip(
                                    Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".bootimemode.tooltip"
                                    )
                            )
                            .setSaveConsumer(value ->
                                    FrontierConfig.BOOTIME_MODE = value
                            )
                            .build()
            );
            // BARRIER INCREASE SIZE
            mode_setting.addEntry(
                    entryBuilder.startIntField(
                                    Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".barrier_increase_size"
                                    ).withStyle(ChatFormatting.GOLD),
                                    FrontierConfig.BARRIER_INCREASE_SIZE
                            )
                            .setDefaultValue(2)
                            .setTooltip(
                                    Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".barrier_increase_size.tooltip"
                                    )
                            )
                            .setSaveConsumer(value ->
                                    FrontierConfig.BARRIER_INCREASE_SIZE = value
                            )
                            .build()
            );
            // TIME MODE SPEED
            mode_setting.addEntry(
                    entryBuilder.startIntField(
                                    Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".time_mode_speed"
                                    ).withStyle(ChatFormatting.GOLD),
                                    FrontierConfig.TIME_MODE_SPEED
                            )
                            .setDefaultValue(1800)
                            .setTooltip(
                                    Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".time_mode_speed.tooltip"
                                    )
                            )
                            .setSaveConsumer(value ->
                                    FrontierConfig.TIME_MODE_SPEED = value
                            )
                            .build()
            );

            // BOO ADVENSENTS
            mode_setting.addEntry(
                    entryBuilder.startBooleanToggle(
                                    Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".booadvensents"
                                    ).withStyle(ChatFormatting.DARK_GREEN),
                                    FrontierConfig.BOOADVENSENTS
                            )
                            .setDefaultValue(false)
                            .setTooltip(
                                    Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".booadvensents.tooltip"
                                    )
                            )
                            .setSaveConsumer(value ->
                                    FrontierConfig.BOOADVENSENTS = value
                            )
                            .build()
            );
            // ADVENSENTS
            mode_setting.addEntry(
                    entryBuilder.startIntField(
                                    Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".advensents"
                                    ).withStyle(ChatFormatting.DARK_GREEN),
                                    FrontierConfig.ADVENSENTS
                            )
                            .setDefaultValue(2)
                            .setTooltip(
                                    Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".advensents.tooltip"
                                    )
                            )
                            .setSaveConsumer(value ->
                                    FrontierConfig.ADVENSENTS = value
                            )
                            .build()
            );
            // GOAL
            mode_setting.addEntry(
                    entryBuilder.startIntField(
                                    Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".goal"
                                    ).withStyle(ChatFormatting.DARK_GREEN),
                                    FrontierConfig.GOAL
                            )
                            .setDefaultValue(10)
                            .setTooltip(
                                    Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".goal.tooltip"
                                    )
                            )
                            .setSaveConsumer(value ->
                                    FrontierConfig.GOAL = value
                            )
                            .build()
            );
            // CHALLENGE
            mode_setting.addEntry(
                    entryBuilder.startIntField(
                                    Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".challenge"
                                    ).withStyle(ChatFormatting.DARK_GREEN),
                                    FrontierConfig.CHALLENGE
                            )
                            .setDefaultValue(20)
                            .setTooltip(
                                    Component.translatable(
                                            "config." + FrontierMod.MOD_ID + ".challenge.tooltip"
                                    )
                            )
                            .setSaveConsumer(value ->
                                    FrontierConfig.CHALLENGE = value
                            )
                            .build()
            );
            return builder.build();
        }
    }