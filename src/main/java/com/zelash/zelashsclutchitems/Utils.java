package com.zelash.zelashsclutchitems;

public class Utils {
    public static void logDebug(String message) {
        if (Config.ENABLE_DEBUG_LOGGING.get()) {
            ZelashsClutchItems.LOGGER.info("[DEBUG] " + message);
        }
    }
}
