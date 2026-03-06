package com.zelash.zelashsclutchitems.item;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

public class PaxelItem extends DiggerItem {

    public PaxelItem(Tier tier, TagKey<Block> mineable) {
        super(tier, mineable, new Item.Properties().attributes(DiggerItem.createAttributes(tier, 4.0F, -2.8F)));
    }

    public PaxelItem(Tier tier, TagKey<Block> mineable, Item.Properties properties) {
        super(tier, mineable, properties);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility toolAction) {
        return net.neoforged.neoforge.common.ItemAbilities.DEFAULT_AXE_ACTIONS.contains(toolAction) ||
                net.neoforged.neoforge.common.ItemAbilities.DEFAULT_SHOVEL_ACTIONS.contains(toolAction) ||
                net.neoforged.neoforge.common.ItemAbilities.DEFAULT_PICKAXE_ACTIONS.contains(toolAction);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState blockstate = level.getBlockState(blockpos);
        ItemStack itemstack = context.getItemInHand();

        // List of abilities to test in order of priority
        ItemAbility[] abilities = {
                ItemAbilities.SHOVEL_FLATTEN,
                ItemAbilities.AXE_STRIP,
                ItemAbilities.AXE_SCRAPE,
                ItemAbilities.AXE_WAX_OFF
        };

        for (ItemAbility ability : abilities) {
            BlockState modifiedState = blockstate.getToolModifiedState(context, ability, false);
            if (modifiedState != null) {
                // Determine the correct sound/event based on the successful ability
                if (ability == ItemAbilities.SHOVEL_FLATTEN) {
                    level.playSound(player, blockpos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                } else if (ability == ItemAbilities.AXE_STRIP) {
                    level.playSound(player, blockpos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
                } else if (ability == ItemAbilities.AXE_SCRAPE) {
                    level.playSound(player, blockpos, SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
                    level.levelEvent(player, 3005, blockpos, 0);
                } else if (ability == ItemAbilities.AXE_WAX_OFF) {
                    level.playSound(player, blockpos, SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
                    level.levelEvent(player, 3004, blockpos, 0);
                }

                if (!level.isClientSide) {
                    level.setBlock(blockpos, modifiedState, 11);
                    if (player != null) {
                        itemstack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
                    }
                }
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        return InteractionResult.PASS;
    }
}
