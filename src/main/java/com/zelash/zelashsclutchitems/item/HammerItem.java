package com.zelash.zelashsclutchitems.item;

import com.zelash.zelashsclutchitems.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class HammerItem extends PickaxeItem {

    public HammerItem(Tier tier) {
        super(tier, new Item.Properties().attributes(PickaxeItem.createAttributes(tier, 6.0F, -3.3F)));
    }

    public HammerItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    private static final Map<UUID, Integer> PLAYER_RADII = new ConcurrentHashMap<>();

    public static int getRadius(Player player) {
        return PLAYER_RADII.getOrDefault(player.getUUID(), 1); // Default 3x3
    }

    @Override
    public net.minecraft.world.InteractionResultHolder<ItemStack> use(Level level, Player player, net.minecraft.world.InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player.isCrouching()) {
            int currentRadius = getRadius(player);
            int newRadius = currentRadius + 1;
            if (newRadius > 3) {
                newRadius = 1; // Loop back to 3x3
            }
            
            PLAYER_RADII.put(player.getUUID(), newRadius);
            
            if (!level.isClientSide) {
                String area = (newRadius * 2 + 1) + "x" + (newRadius * 2 + 1);
                Utils.logDebug("Hammer area changed for player " + player.getName().getString() + " to " + area);
                player.displayClientMessage(net.minecraft.network.chat.Component.translatable("message.zelashsclutchitems.hammer_area_changed", area), true);
            }
            
            return net.minecraft.world.InteractionResultHolder.success(stack);
        }
        return super.use(level, player, hand);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        if (entityLiving instanceof ServerPlayer player) {
            if (!player.isCrouching()) {
                // Use pick to determine hit face
                HitResult trace = player.pick(20.0D, 0.0F, false);
                if (trace.getType() == HitResult.Type.BLOCK) {
                    BlockHitResult blockTrace = (BlockHitResult) trace;
                    Direction face = blockTrace.getDirection();
                    breakArea(player, level, pos, face, stack, getRadius(player));
                }
            }
        }
        return super.mineBlock(stack, level, state, pos, entityLiving);
    }

    private void breakArea(ServerPlayer player, Level level, BlockPos center, Direction face, ItemStack tool, int radius) {
        if (level.isClientSide) return;

        Direction.Axis axis = face.getAxis();
        int minX = axis == Direction.Axis.X ? 0 : -radius;
        int maxX = axis == Direction.Axis.X ? 0 : radius;
        int minY = axis == Direction.Axis.Y ? 0 : -radius;
        int maxY = axis == Direction.Axis.Y ? 0 : radius;
        int minZ = axis == Direction.Axis.Z ? 0 : -radius;
        int maxZ = axis == Direction.Axis.Z ? 0 : radius;

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    if (x == 0 && y == 0 && z == 0) continue; // Original block handled by normal breaking

                    BlockPos targetPos = center.offset(x, y, z);
                    BlockState targetState = level.getBlockState(targetPos);

                    if (!targetState.isAir() && targetState.getDestroySpeed(level, targetPos) >= 0) {
                        if (tool.isCorrectToolForDrops(targetState)) {
                            level.destroyBlock(targetPos, true, player);
                            tool.hurtAndBreak(1, player, LivingEntity.getSlotForHand(player.getUsedItemHand()));
                        }
                    }
                }
            }
        }
    }

    public static List<BlockPos> getBlocksToBeDestroyed(int radius, BlockPos centerPos, ServerPlayer player) {
        List<BlockPos> positions = new ArrayList<>();
        HitResult trace = player.pick(20.0D, 0.0F, false);
        if (trace.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockTrace = (BlockHitResult) trace;
            Direction face = blockTrace.getDirection();
            Direction.Axis axis = face.getAxis();
            
            int minX = axis == Direction.Axis.X ? 0 : -radius;
            int maxX = axis == Direction.Axis.X ? 0 : radius;
            int minY = axis == Direction.Axis.Y ? 0 : -radius;
            int maxY = axis == Direction.Axis.Y ? 0 : radius;
            int minZ = axis == Direction.Axis.Z ? 0 : -radius;
            int maxZ = axis == Direction.Axis.Z ? 0 : radius;

            for (int x = minX; x <= maxX; x++) {
                for (int y = minY; y <= maxY; y++) {
                    for (int z = minZ; z <= maxZ; z++) {
                        positions.add(centerPos.offset(x, y, z));
                    }
                }
            }
        }
        return positions;
    }

    public static List<BlockPos> getBlocksToBeDestroyed(BlockPos centerPos, ServerPlayer player) {
        return getBlocksToBeDestroyed(getRadius(player), centerPos, player);
    }
}
