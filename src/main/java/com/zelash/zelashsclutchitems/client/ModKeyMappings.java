package com.zelash.zelashsclutchitems.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.zelash.zelashsclutchitems.ZelashsClutchItems;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = ZelashsClutchItems.MODID, value = Dist.CLIENT)
public class ModKeyMappings {
    public static final String CATEGORY = "key.categories." + ZelashsClutchItems.MODID;

    // Default key: C
    public static final Lazy<KeyMapping> OPEN_CRAFTING_STICK = Lazy.of(() -> new KeyMapping(
            "key." + ZelashsClutchItems.MODID + ".open_crafting_stick",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_C,
            CATEGORY
    ));

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(OPEN_CRAFTING_STICK.get());
    }
}
