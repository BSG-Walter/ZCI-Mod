package com.zelash.zelashsclutchitems.item;

import com.zelash.zelashsclutchitems.ZelashsClutchItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.zelash.zelashsclutchitems.item.HammerItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ZelashsClutchItems.MODID);

    public static final DeferredItem<Item> STONE_HAMMER = ITEMS.register("stone_hammer",
            ()-> new HammerItem(net.minecraft.world.item.Tiers.STONE, 1, -2.8F,
                    new Item.Properties().tab(net.minecraft.world.item.CreativeModeTab.TAB_TOOLS));
    public static final DeferredItem<Item> IRON_HAMMER = ITEMS.register("iron_hammer",
            ()-> new HammerItem(net.minecraft.world.item.Tiers.IRON, 1, -2.8F,
                    new Item.Properties().tab(net.minecraft.world.item.CreativeModeTab.TAB_TOOLS));
    public static void register (IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
