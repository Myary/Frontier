package net.myarry.frontier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.neoforged.fml.loading.FMLPaths;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;


public class FrontierConfig {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final Path CONFIG_PATH =
            FMLPaths.CONFIGDIR.get().resolve("frontier.json");

    public static int INITIAL_BARRIER = 1;
    public static Boolean BOOLIMIT = Boolean.FALSE;
    public static Boolean BOOTIME_MODE = Boolean.FALSE;
    public static Boolean BOOADVENSENTS = Boolean.FALSE;

    public static int LIMIT = 64;
    public static int TIME_LIMIT = 60;

    public static int BARRIER_INCREASE_SIZE = 1;
    public static int TIME_MODE_SPEED = 20;

    public static int ADVENSENTS = 2;
    public static int GOAL = 4;
    public static int CHALLENGE = 6;
    public static double BARRIER_SPEED =2.0D;

    public static int SMALL_BAR =4;
    public static int BIG_BAR = 8;



    public static void load() {
        try {
            if (!Files.exists(CONFIG_PATH)) {
                save();
                return;
            }
            try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {

                FrontierConfigData data =GSON.fromJson(reader, FrontierConfigData.class);
                if (data == null) {
                    save();
                    return;
                }

                INITIAL_BARRIER = data.initialBarrier;
                BOOLIMIT = data.booLimit;
                BOOTIME_MODE = data.booTimeMode;
                BOOADVENSENTS = data.booAdvensents;

                LIMIT = data.limit;
                TIME_LIMIT = data.timeLimit;

                BARRIER_INCREASE_SIZE = data.barrierIncreaseSize;
                TIME_MODE_SPEED = data.timeModeSpeed;

                ADVENSENTS = data.advensents;
                GOAL = data.goal;
                CHALLENGE = data.challenge;

                BARRIER_SPEED = data.barrierSpeed;
                SMALL_BAR = data.smallbar;
                BIG_BAR = data.bigbar;

            }
        } catch (Exception e) {FrontierMod.LOGGER.error("Failed to load Frontier config!",e);
            save();
        }
    }
    public static void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());

            FrontierConfigData data = new FrontierConfigData();

            data.initialBarrier = INITIAL_BARRIER;
            data.booLimit = BOOLIMIT;
            data.booTimeMode = BOOTIME_MODE;
            data.booAdvensents = BOOADVENSENTS;

            data.limit = LIMIT;
            data.timeLimit = TIME_LIMIT;

            data.barrierIncreaseSize = BARRIER_INCREASE_SIZE;
            data.timeModeSpeed = TIME_MODE_SPEED;

            data.advensents = ADVENSENTS;
            data.goal = GOAL;
            data.challenge = CHALLENGE;

            data.barrierSpeed = BARRIER_SPEED;

            data.smallbar = SMALL_BAR;
            data.bigbar = BIG_BAR;

            try (Writer writer = Files.newBufferedWriter(CONFIG_PATH)) {
                GSON.toJson(data, writer);
            }
        } catch (Exception e) {
            FrontierMod.LOGGER.error("Failed to save Frontier config!",e);
        }
    }
    private static class FrontierConfigData {

        int initialBarrier = 1;

        boolean booLimit = false;
        boolean booTimeMode = false;
        boolean booAdvensents = false;

        int limit = 64;
        int timeLimit = 60;

        int barrierIncreaseSize = 1;
        int timeModeSpeed = 20;

        int advensents = 2;
        int goal = 4;
        int challenge = 6;

        double barrierSpeed = 2;

        int smallbar = 4;
        int bigbar = 8;
    }
}

