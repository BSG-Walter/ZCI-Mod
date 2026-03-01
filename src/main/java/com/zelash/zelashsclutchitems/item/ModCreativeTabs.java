package com.zelash.zelashsclutchitems.item;

import com.zelash.zelashsclutchitems.ZelashsClutchItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ZelashsClutchItems.MODID);

    public static final Supplier<CreativeModeTab> ZELASHS_CLUTCH_ITEMS_TAB = CREATIVE_MODE_TABS.register("zelashs_clutch_items_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab." + ZelashsClutchItems.MODID + ".tab"))
                    .icon(() -> new ItemStack(ModItems.DIAMOND_HAMMER.get()))
                    .displayItems((params, output) -> {
                        output.accept(ModItems.STONE_HAMMER.get());
                        output.accept(ModItems.IRON_HAMMER.get());
                        output.accept(ModItems.GOLDEN_HAMMER.get());
                        output.accept(ModItems.DIAMOND_HAMMER.get());
                        output.accept(ModItems.NETHERITE_HAMMER.get());
                    })
                    .build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
