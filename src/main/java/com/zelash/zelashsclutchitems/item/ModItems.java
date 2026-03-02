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
            ()-> new HammerItem(ModTiers.STONE_HAMMER_TIER));
    public static final DeferredItem<Item> IRON_HAMMER = ITEMS.register("iron_hammer",
            ()-> new HammerItem(ModTiers.IRON_HAMMER_TIER));
    public static final DeferredItem<Item> GOLDEN_HAMMER = ITEMS.register("golden_hammer",
            ()-> new HammerItem(ModTiers.GOLD_HAMMER_TIER));
    public static final DeferredItem<Item> DIAMOND_HAMMER = ITEMS.register("diamond_hammer",
            ()-> new HammerItem(ModTiers.DIAMOND_HAMMER_TIER));
    public static final DeferredItem<Item> NETHERITE_HAMMER = ITEMS.register("netherite_hammer",
            ()-> new HammerItem(ModTiers.NETHERITE_HAMMER_TIER, new Item.Properties()
            .attributes(PickaxeItem.createAttributes(ModTiers.NETHERITE_HAMMER_TIER, 6.0F, -3.3F))
            .fireResistant()));
    public static void register (IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
