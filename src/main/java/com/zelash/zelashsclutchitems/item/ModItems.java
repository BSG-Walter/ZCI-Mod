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

    public static final DeferredItem<Item> STONE_PAXEL = ITEMS.register("stone_paxel",
            ()-> new PaxelItem(ModTiers.STONE_HAMMER_TIER, ModTags.Blocks.MINEABLE_WITH_PAXEL, new Item.Properties()
            .attributes(PaxelItem.createAttributes(ModTiers.STONE_HAMMER_TIER, 4.0F, -2.8F))));
    public static final DeferredItem<Item> IRON_PAXEL = ITEMS.register("iron_paxel",
            ()-> new PaxelItem(ModTiers.IRON_HAMMER_TIER, ModTags.Blocks.MINEABLE_WITH_PAXEL, new Item.Properties()
            .attributes(PaxelItem.createAttributes(ModTiers.IRON_HAMMER_TIER, 4.0F, -2.8F))));
    public static final DeferredItem<Item> GOLDEN_PAXEL = ITEMS.register("golden_paxel",
            ()-> new PaxelItem(ModTiers.GOLD_HAMMER_TIER, ModTags.Blocks.MINEABLE_WITH_PAXEL, new Item.Properties()
            .attributes(PaxelItem.createAttributes(ModTiers.GOLD_HAMMER_TIER, 4.0F, -2.8F))));
    public static final DeferredItem<Item> DIAMOND_PAXEL = ITEMS.register("diamond_paxel",
            ()-> new PaxelItem(ModTiers.DIAMOND_HAMMER_TIER, ModTags.Blocks.MINEABLE_WITH_PAXEL, new Item.Properties()
            .attributes(PaxelItem.createAttributes(ModTiers.DIAMOND_HAMMER_TIER, 4.0F, -2.8F))));
    public static final DeferredItem<Item> NETHERITE_PAXEL = ITEMS.register("netherite_paxel",
            ()-> new PaxelItem(ModTiers.NETHERITE_HAMMER_TIER, ModTags.Blocks.MINEABLE_WITH_PAXEL, new Item.Properties()
            .attributes(PaxelItem.createAttributes(ModTiers.NETHERITE_HAMMER_TIER, 4.0F, -2.8F))
            .fireResistant()));

    public static final DeferredItem<Item> XP_TOME = ITEMS.register("xp_tome",
            () -> new XPTomeItem(new Item.Properties().stacksTo(1), com.zelash.zelashsclutchitems.Config.XPTOME_MAX_XP));
    public static final DeferredItem<Item> LARGE_XP_TOME = ITEMS.register("large_xp_tome",
            () -> new XPTomeItem(new Item.Properties().stacksTo(1), com.zelash.zelashsclutchitems.Config.LARGE_XPTOME_MAX_XP));

    public static void register (IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
