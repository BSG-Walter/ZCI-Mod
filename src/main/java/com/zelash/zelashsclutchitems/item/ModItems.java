package com.zelash.zelashsclutchitems.item;

import com.zelash.zelashsclutchitems.ZelashsClutchItems;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ZelashsClutchItems.MODID);

    public static final DeferredItem<Item> STONE_HAMMER = ITEMS.register("stone_hammer",
            ()-> new Item(new Item.Properties()));
    public static final DeferredItem<Item> IRON_HAMMER = ITEMS.register("iron_hammer",
            ()-> new Item(new Item.Properties()));
    public static void register (IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
