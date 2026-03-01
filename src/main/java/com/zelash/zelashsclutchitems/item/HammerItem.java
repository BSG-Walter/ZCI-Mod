package com.zelash.zelashsclutchitems.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

/**
 * A simple hammer item that acts like a pickaxe but breaks a 3x3 area of blocks
 * on the horizontal plane when it mines a block.
 */
public class HammerItem extends PickaxeItem {
    public HammerItem(Tier tier, int attackDamageModifier, float attackSpeedModifier, Properties properties) {
        super(tier, attackDamageModifier, attackSpeedModifier, properties);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity entity) {
        // first let the normal pickaxe logic run (durability, drops for the central block, etc.)
        boolean ret = super.mineBlock(stack, world, state, pos, entity);

        if (!world.isClientSide && entity instanceof Player player) {
            // iterate through the 3x3 area centred on the originally mined block
            for (int dx = -1; dx <= 1; dx++) {
                for (int dz = -1; dz <= 1; dz++) {
                    if (dx == 0 && dz == 0) {
                        // central block already mined
                        continue;
                    }
                    BlockPos target = pos.offset(dx, 0, dz);
                    BlockState targetState = world.getBlockState(target);

                    // only break blocks that are not air and that this tool can harvest
                    if (!targetState.isAir() && stack.isCorrectToolForDrops(targetState)) {
                        world.destroyBlock(target, true, player);
                        stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                    }
                }
            }
        }
        return ret;
    }
}
