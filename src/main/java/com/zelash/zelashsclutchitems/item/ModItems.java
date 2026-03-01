package com.zelash.zelashsclutchitems.item;

import com.zelash.zelashsclutchitems.ZelashsClutchItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ZelashsClutchItems.MODID);

    public static final DeferredItem<Item> STONE_HAMMER = ITEMS.register("stone_hammer",
            ()-> new HammerItem(net.minecraft.world.item.Tiers.STONE, new Item.Properties()
            .attributes(PickaxeItem.createAttributes(net.minecraft.world.item.Tiers.STONE, 7.0F, -3.1F))
            .durability(400)
            , 1));
    public static final DeferredItem<Item> IRON_HAMMER = ITEMS.register("iron_hammer",
            ()-> new HammerItem(net.minecraft.world.item.Tiers.IRON, new Item.Properties()
            .attributes(PickaxeItem.createAttributes(net.minecraft.world.item.Tiers.IRON, 7.0F, -3.1F))
            .durability(800)
            , 1));
    public static void register (IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
