package com.zelash.zelashsclutchitems.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import com.zelash.zelashsclutchitems.menu.ItemCraftingMenu;

public class CraftingStickItem extends Item {
    private static final Component CONTAINER_TITLE = Component.translatable("container.crafting");

    public CraftingStickItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (!level.isClientSide()) {
            openCraftingMenu(player);
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(usedHand), level.isClientSide());
    }
    public static void openCraftingMenu(Player player) {
        player.openMenu(new SimpleMenuProvider(
            (containerId, playerInventory, playerEntity) -> new ItemCraftingMenu(containerId, playerInventory),
            CONTAINER_TITLE
        ));
    }

    public static boolean hasCraftingStickInHotbar(Player player) {
        for (int i = 0; i < 9; i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (stack.getItem() instanceof CraftingStickItem) {
                return true;
            }
        }
        return false;
    }
}
