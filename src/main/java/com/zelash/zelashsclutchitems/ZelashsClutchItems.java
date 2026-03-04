package com.zelash.zelashsclutchitems;

import com.zelash.zelashsclutchitems.item.ModCreativeTabs;
import com.zelash.zelashsclutchitems.item.ModDataComponentTypes;
import com.zelash.zelashsclutchitems.item.ModItems;
import com.zelash.zelashsclutchitems.block.ModBlocks;
import com.zelash.zelashsclutchitems.menu.ModMenus;
import com.zelash.zelashsclutchitems.recipe.ModRecipes;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ZelashsClutchItems.MODID)
public class ZelashsClutchItems {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "zelashsclutchitems";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public ZelashsClutchItems(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModMenus.register(modEventBus);
        ModDataComponentTypes.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        ModRecipes.register(modEventBus);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Common setup
    }
}
