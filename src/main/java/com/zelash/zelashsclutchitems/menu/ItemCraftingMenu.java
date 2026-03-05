package com.zelash.zelashsclutchitems.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;

public class ItemCraftingMenu extends CraftingMenu {

    public ItemCraftingMenu(int containerId, Inventory playerInventory) {
        // Use a dummy ContainerLevelAccess that just provides the player's current position
        // so the crafting logic can still run without a physical block
        super(containerId, playerInventory, ContainerLevelAccess.create(playerInventory.player.level(), playerInventory.player.blockPosition()));
    }

    public ItemCraftingMenu(int containerId, Inventory playerInventory, ContainerLevelAccess access) {
        super(containerId, playerInventory, access);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
