package com.zelash.zelashsclutchitems.event;

import com.zelash.zelashsclutchitems.ZelashsClutchItems;
import com.zelash.zelashsclutchitems.block.custom.ElevatorBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@EventBusSubscriber(modid = ZelashsClutchItems.MODID)
public class ModEvents {

    // Keep track of players who were crouching in the previous tick
    private static final Map<UUID, Boolean> wasCrouching = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerJump(LivingEvent.LivingJumpEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            Level level = player.level();
            BlockPos pos = player.blockPosition();

            // Player might be slightly above the block
            BlockState stateBelow = level.getBlockState(pos.below());
            BlockState stateAtFeet = level.getBlockState(pos);

            // Check what block the player is standing on
            BlockPos targetBlockPos = null;
            if (stateBelow.getBlock() instanceof ElevatorBlock) {
                targetBlockPos = pos.below();
            } else if (stateAtFeet.getBlock() instanceof ElevatorBlock) {
                targetBlockPos = pos;
            }

            if (targetBlockPos != null) {
                teleportElevator(player, level, targetBlockPos, true);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player instanceof ServerPlayer serverPlayer) {
            UUID uuid = player.getUUID();
            boolean isCrouching = player.isCrouching();
            boolean wasCrouchingBefore = wasCrouching.getOrDefault(uuid, false);

            if (isCrouching && !wasCrouchingBefore) {
                // The player just started crouching this tick
                Level level = player.level();
                BlockPos pos = player.blockPosition();

                BlockState stateBelow = level.getBlockState(pos.below());
                BlockState stateAtFeet = level.getBlockState(pos);

                BlockPos targetBlockPos = null;
                if (stateBelow.getBlock() instanceof ElevatorBlock) {
                    targetBlockPos = pos.below();
                } else if (stateAtFeet.getBlock() instanceof ElevatorBlock) {
                    targetBlockPos = pos;
                }

                if (targetBlockPos != null) {
                    teleportElevator(serverPlayer, level, targetBlockPos, false);
                }
            }

            wasCrouching.put(uuid, isCrouching);
        }
    }

    private static void teleportElevator(ServerPlayer player, Level level, BlockPos elevatorPos, boolean up) {
        int minDistance = 2; // Minimally 2 blocks of distance
        int maxDistance = 16;
        int direction = up ? 1 : -1;

        for (int i = minDistance; i <= maxDistance; i++) {
            int yOffset = i * direction;
            BlockPos checkPos = elevatorPos.relative(net.minecraft.core.Direction.Axis.Y, yOffset);

            // Prevention of checking outside build height limits
            if (level.isOutsideBuildHeight(checkPos)) {
                break;
            }

            BlockState state = level.getBlockState(checkPos);
            if (state.getBlock() instanceof ElevatorBlock) {
                if (com.zelash.zelashsclutchitems.Config.REQUIRE_SAME_COLOR_ELEVATOR.get()) {
                    BlockState sourceState = level.getBlockState(elevatorPos);
                    if (!state.is(sourceState.getBlock())) {
                        continue; // Skip this elevator and keep looking
                    }
                }

                // Found next elevator
                // Check if there is space above it for the player
                BlockPos destPos1 = checkPos.above();
                BlockPos destPos2 = checkPos.above(2);

                if (level.getBlockState(destPos1).getCollisionShape(level, destPos1).isEmpty() &&
                        level.getBlockState(destPos2).getCollisionShape(level, destPos2).isEmpty()) {

                    player.connection.teleport(
                            player.getX(),
                            checkPos.getY() + 1.0,
                            player.getZ(),
                            player.getYRot(),
                            player.getXRot());

                    // Reset velocity and fall distance just in case
                    player.setDeltaMovement(0, 0, 0);
                    player.resetFallDistance();

                    // Play enderman teleport sound
                    level.playSound(null, checkPos, SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
                    break;
                }
            }
        }
    }
}
