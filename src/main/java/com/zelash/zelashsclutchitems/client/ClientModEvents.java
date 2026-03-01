package com.zelash.zelashsclutchitems.client;

import com.zelash.zelashsclutchitems.ZelashsClutchItems;
import com.zelash.zelashsclutchitems.item.HammerItem;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderHighlightEvent;

import java.util.List;

@EventBusSubscriber(modid = ZelashsClutchItems.MODID, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void onBlockHighlight(RenderHighlightEvent.Block event) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            if (player.isCrouching()) return; // Don't show large highlight when crouching
            
            ItemStack mainHandItem = player.getMainHandItem();
            if (mainHandItem.getItem() instanceof HammerItem hammer) {
                HitResult hitResult = event.getTarget();
                if (hitResult.getType() == HitResult.Type.BLOCK) {
                    BlockHitResult blockHitResult = (BlockHitResult) hitResult;
                    BlockPos centerPos = blockHitResult.getBlockPos();
                    int radius = hammer.getRadius();
                    
                    // We can't cast player to ServerPlayer in client side cleanly, so we re-implement the logic for client side.
                    net.minecraft.core.Direction face = blockHitResult.getDirection();
                    net.minecraft.core.Direction.Axis axis = face.getAxis();
                    
                    int minX = axis == net.minecraft.core.Direction.Axis.X ? 0 : -radius;
                    int maxX = axis == net.minecraft.core.Direction.Axis.X ? 0 : radius;
                    int minY = axis == net.minecraft.core.Direction.Axis.Y ? 0 : -radius;
                    int maxY = axis == net.minecraft.core.Direction.Axis.Y ? 0 : radius;
                    int minZ = axis == net.minecraft.core.Direction.Axis.Z ? 0 : -radius;
                    int maxZ = axis == net.minecraft.core.Direction.Axis.Z ? 0 : radius;

                    com.mojang.blaze3d.vertex.PoseStack poseStack = event.getPoseStack();
                    com.mojang.blaze3d.vertex.VertexConsumer vertexConsumer = event.getMultiBufferSource().getBuffer(net.minecraft.client.renderer.RenderType.lines());
                    net.minecraft.world.phys.Vec3 cameraPos = event.getCamera().getPosition();

                    for (int x = minX; x <= maxX; x++) {
                        for (int y = minY; y <= maxY; y++) {
                            for (int z = minZ; z <= maxZ; z++) {
                                if (x == 0 && y == 0 && z == 0) continue; // Original is rendered by default
                                
                                BlockPos offsetPos = centerPos.offset(x, y, z);
                                net.minecraft.world.level.block.state.BlockState state = player.level().getBlockState(offsetPos);
                                if (!state.isAir()) {
                                    net.minecraft.world.phys.shapes.VoxelShape shape = state.getShape(player.level(), offsetPos);
                                    if (!shape.isEmpty()) {
                                        double dx = offsetPos.getX() - cameraPos.x;
                                        double dy = offsetPos.getY() - cameraPos.y;
                                        double dz = offsetPos.getZ() - cameraPos.z;
                                        
                                        // Use standard shape renderer method mapped for NeoForge 1.21.1. Depending on mapping, it might be renderShape(..., float, float, float, float) or similar mapped methods.
                                        // Let's use the buffer directly to render the wireframe.
                                        net.minecraft.client.renderer.LevelRenderer.renderVoxelShape(
                                            poseStack, 
                                            vertexConsumer, 
                                            shape, 
                                            dx, dy, dz, 
                                            0.0F, 0.0F, 0.0F, 0.4F, true
                                        );
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
