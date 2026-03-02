package com.zelash.zelashsclutchitems;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
            .comment("Whether to log the dirt block on common setup")
            .define("logDirtBlock", true);

    public static final ModConfigSpec.IntValue MAGIC_NUMBER = BUILDER
            .comment("A magic number")
            .defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);

    public static final ModConfigSpec.ConfigValue<String> MAGIC_NUMBER_INTRODUCTION = BUILDER
            .comment("What you want the introduction message to be for the magic number")
            .define("magicNumberIntroduction", "The magic number is... ");

    public static final ModConfigSpec.BooleanValue ENABLE_DEBUG_LOGGING = BUILDER
            .comment("Whether to print additional debug information to the server console")
            .define("enableDebugLogging", false);

    // a list of strings that are treated as resource locations for items
    public static final ModConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
            .comment("A list of items to log on common setup.")
            .defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), () -> "", Config::validateItemName);

    public static final ModConfigSpec.IntValue XPTOME_MAX_XP = BUILDER
            .comment("Defines the total amount of experience points an XP Tome can store. Default is 1395, which equals exactly 30 experience levels.")
            .defineInRange("xptome_max_xp", 1395, 1, Integer.MAX_VALUE);

    public static final ModConfigSpec.DoubleValue XPTOME_RETRIEVAL_PERCENTAGE = BUILDER
            .comment("Determines the return rate when extracting experience from the tome. For instance, a value of 0.75 means you get back 75% of the stored XP, effectively costing 25%. Values might not be perfectly exact due to how Minecraft handles XP.")
            .defineInRange("xptome_retrieval_percentage", 1.0, 0.0, 1.0);

    public static final ModConfigSpec.BooleanValue XPTOME_TRANSFER_ONE_LEVEL_AT_A_TIME = BUILDER
            .comment("If enabled, depositing and retrieving XP will be done one level at a time, preventing you from storing or retrieving multiple levels at once.")
            .define("xptome_transfer_one_level_at_a_time", true);

    static final ModConfigSpec SPEC = BUILDER.build();

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }
}
