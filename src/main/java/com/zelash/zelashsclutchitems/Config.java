package com.zelash.zelashsclutchitems;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    
    public static final ModConfigSpec.BooleanValue ENABLE_DEBUG_LOGGING = BUILDER
            .comment("Whether to print additional debug information to the server console")
            .define("enableDebugLogging", false);

    public static final ModConfigSpec.IntValue XPTOME_MAX_XP = BUILDER
            .comment("Defines the total amount of experience points an XP Tome can store. Default is 1395, which equals exactly 30 experience levels.")
            .defineInRange("xptome_max_xp", 1395, 1, Integer.MAX_VALUE);

    public static final ModConfigSpec.IntValue LARGE_XPTOME_MAX_XP = BUILDER
            .comment("Defines the total amount of experience points a Large XP Tome can store. Default is 13950, which is 10x the base XP Tome.")
            .defineInRange("large_xptome_max_xp", 13950, 1, Integer.MAX_VALUE);

    public static final ModConfigSpec.DoubleValue XPTOME_RETRIEVAL_PERCENTAGE = BUILDER
            .comment("Determines the return rate when extracting experience from the tome. For instance, a value of 0.75 means you get back 75% of the stored XP, effectively costing 25%. Values might not be perfectly exact due to how Minecraft handles XP.")
            .defineInRange("xptome_retrieval_percentage", 1.0, 0.0, 1.0);

    public static final ModConfigSpec.BooleanValue XPTOME_TRANSFER_ONE_LEVEL_AT_A_TIME = BUILDER
            .comment("If enabled, depositing and retrieving XP will be done one level at a time, preventing you from storing or retrieving multiple levels at once.")
            .define("xptome_transfer_one_level_at_a_time", true);

    static final ModConfigSpec SPEC = BUILDER.build();
}
