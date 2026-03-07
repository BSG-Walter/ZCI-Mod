package com.zelash.zelashsclutchitems.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.ItemStack;

public class MobLassoItem extends SuperiorMobLassoItem {

    public MobLassoItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canCapture(LivingEntity entity, ItemStack stack) {
        if (!super.canCapture(entity, stack))
            return false;
        if (entity instanceof AbstractVillager)
            return false;
        if (!entity.getType().getCategory().isFriendly())
            return false;
        return true;
    }
}
