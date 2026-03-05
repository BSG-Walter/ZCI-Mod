package com.zelash.zelashsclutchitems.item;

import com.zelash.zelashsclutchitems.ZelashsClutchItems;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.PickaxeItem;
import com.zelash.zelashsclutchitems.block.ModBlocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
        public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ZelashsClutchItems.MODID);

        public static final DeferredItem<Item> STONE_HAMMER = ITEMS.register("stone_hammer",
                        () -> new HammerItem(ModTiers.STONE_HAMMER_TIER));
        public static final DeferredItem<Item> IRON_HAMMER = ITEMS.register("iron_hammer",
                        () -> new HammerItem(ModTiers.IRON_HAMMER_TIER));
        public static final DeferredItem<Item> GOLDEN_HAMMER = ITEMS.register("golden_hammer",
                        () -> new HammerItem(ModTiers.GOLD_HAMMER_TIER));
        public static final DeferredItem<Item> DIAMOND_HAMMER = ITEMS.register("diamond_hammer",
                        () -> new HammerItem(ModTiers.DIAMOND_HAMMER_TIER));
        public static final DeferredItem<Item> NETHERITE_HAMMER = ITEMS.register("netherite_hammer",
                        () -> new HammerItem(ModTiers.NETHERITE_HAMMER_TIER, new Item.Properties()
                                        .attributes(PickaxeItem.createAttributes(ModTiers.NETHERITE_HAMMER_TIER, 6.0F,
                                                        -3.3F))
                                        .fireResistant()));

        public static final DeferredItem<Item> STONE_PAXEL = ITEMS.register("stone_paxel",
                        () -> new PaxelItem(ModTiers.STONE_HAMMER_TIER, ModTags.Blocks.MINEABLE_WITH_PAXEL));
        public static final DeferredItem<Item> IRON_PAXEL = ITEMS.register("iron_paxel",
                        () -> new PaxelItem(ModTiers.IRON_HAMMER_TIER, ModTags.Blocks.MINEABLE_WITH_PAXEL));
        public static final DeferredItem<Item> GOLDEN_PAXEL = ITEMS.register("golden_paxel",
                        () -> new PaxelItem(ModTiers.GOLD_HAMMER_TIER, ModTags.Blocks.MINEABLE_WITH_PAXEL));
        public static final DeferredItem<Item> DIAMOND_PAXEL = ITEMS.register("diamond_paxel",
                        () -> new PaxelItem(ModTiers.DIAMOND_HAMMER_TIER, ModTags.Blocks.MINEABLE_WITH_PAXEL));
        public static final DeferredItem<Item> NETHERITE_PAXEL = ITEMS.register("netherite_paxel",
                        () -> new PaxelItem(ModTiers.NETHERITE_HAMMER_TIER, ModTags.Blocks.MINEABLE_WITH_PAXEL,
                                        new Item.Properties()
                                                        .attributes(DiggerItem.createAttributes(
                                                                        ModTiers.NETHERITE_HAMMER_TIER, 4.0F, -2.8F))
                                                        .fireResistant()));

        public static final DeferredItem<Item> XP_TOME = ITEMS.register("xp_tome",
                        () -> new XPTomeItem(new Item.Properties().stacksTo(1),
                                        com.zelash.zelashsclutchitems.Config.XPTOME_MAX_XP));
        public static final DeferredItem<Item> LARGE_XP_TOME = ITEMS.register("large_xp_tome",
                        () -> new XPTomeItem(new Item.Properties().stacksTo(1),
                                        com.zelash.zelashsclutchitems.Config.LARGE_XPTOME_MAX_XP));

        public static final DeferredItem<Item> CRAFTING_STICK = ITEMS.register("crafting_stick",
                        () -> new CraftingStickItem(new Item.Properties().stacksTo(1)));

        public static final DeferredItem<BlockItem> WHITE_ELEVATOR = ITEMS.registerSimpleBlockItem("white_elevator",
                        ModBlocks.WHITE_ELEVATOR);
        public static final DeferredItem<BlockItem> ORANGE_ELEVATOR = ITEMS.registerSimpleBlockItem("orange_elevator",
                        ModBlocks.ORANGE_ELEVATOR);
        public static final DeferredItem<BlockItem> MAGENTA_ELEVATOR = ITEMS.registerSimpleBlockItem("magenta_elevator",
                        ModBlocks.MAGENTA_ELEVATOR);
        public static final DeferredItem<BlockItem> LIGHT_BLUE_ELEVATOR = ITEMS
                        .registerSimpleBlockItem("light_blue_elevator", ModBlocks.LIGHT_BLUE_ELEVATOR);
        public static final DeferredItem<BlockItem> YELLOW_ELEVATOR = ITEMS.registerSimpleBlockItem("yellow_elevator",
                        ModBlocks.YELLOW_ELEVATOR);
        public static final DeferredItem<BlockItem> LIME_ELEVATOR = ITEMS.registerSimpleBlockItem("lime_elevator",
                        ModBlocks.LIME_ELEVATOR);
        public static final DeferredItem<BlockItem> PINK_ELEVATOR = ITEMS.registerSimpleBlockItem("pink_elevator",
                        ModBlocks.PINK_ELEVATOR);
        public static final DeferredItem<BlockItem> GRAY_ELEVATOR = ITEMS.registerSimpleBlockItem("gray_elevator",
                        ModBlocks.GRAY_ELEVATOR);
        public static final DeferredItem<BlockItem> LIGHT_GRAY_ELEVATOR = ITEMS
                        .registerSimpleBlockItem("light_gray_elevator", ModBlocks.LIGHT_GRAY_ELEVATOR);
        public static final DeferredItem<BlockItem> CYAN_ELEVATOR = ITEMS.registerSimpleBlockItem("cyan_elevator",
                        ModBlocks.CYAN_ELEVATOR);
        public static final DeferredItem<BlockItem> PURPLE_ELEVATOR = ITEMS.registerSimpleBlockItem("purple_elevator",
                        ModBlocks.PURPLE_ELEVATOR);
        public static final DeferredItem<BlockItem> BLUE_ELEVATOR = ITEMS.registerSimpleBlockItem("blue_elevator",
                        ModBlocks.BLUE_ELEVATOR);
        public static final DeferredItem<BlockItem> BROWN_ELEVATOR = ITEMS.registerSimpleBlockItem("brown_elevator",
                        ModBlocks.BROWN_ELEVATOR);
        public static final DeferredItem<BlockItem> GREEN_ELEVATOR = ITEMS.registerSimpleBlockItem("green_elevator",
                        ModBlocks.GREEN_ELEVATOR);
        public static final DeferredItem<BlockItem> RED_ELEVATOR = ITEMS.registerSimpleBlockItem("red_elevator",
                        ModBlocks.RED_ELEVATOR);
        public static final DeferredItem<BlockItem> BLACK_ELEVATOR = ITEMS.registerSimpleBlockItem("black_elevator",
                        ModBlocks.BLACK_ELEVATOR);

        public static void register(IEventBus eventBus) {
                ITEMS.register(eventBus);
        }
}
