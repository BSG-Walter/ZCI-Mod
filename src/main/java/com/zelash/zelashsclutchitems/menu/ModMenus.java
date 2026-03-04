package com.zelash.zelashsclutchitems.menu;

import com.zelash.zelashsclutchitems.ZelashsClutchItems;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.minecraft.world.flag.FeatureFlags;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = 
            DeferredRegister.create(net.minecraft.core.registries.Registries.MENU, ZelashsClutchItems.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<ItemCraftingMenu>> CRAFTING_STICK_MENU = 
            MENUS.register("crafting_stick_menu", () -> new MenuType<>(ItemCraftingMenu::new, FeatureFlags.DEFAULT_FLAGS));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
