package com.zelash.zelashsclutchitems.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SuperiorMobLassoItem extends Item {
    public SuperiorMobLassoItem(Properties properties) {
        super(properties);
    }

    public boolean canCapture(LivingEntity entity, ItemStack stack) {
        if (stack.has(DataComponents.ENTITY_DATA))
            return false;
        if (entity.getType().is(ModTags.EntityTypes.MOB_LASSO_BLACKLIST))
            return false;
        return (entity instanceof Mob);
    }

    public InteractionResult useLasso(ItemStack stack, Player player, LivingEntity interactionTarget,
            InteractionHand usedHand) {

        Level level = interactionTarget.level();
        if (level.isClientSide) {
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        if (level instanceof ServerLevel serverLevel) {
            CompoundTag tag = new CompoundTag();
            if (interactionTarget.save(tag)) {
                stack.set(DataComponents.ENTITY_DATA, CustomData.of(tag));
                player.setItemInHand(usedHand, stack);
                interactionTarget.discard();

                serverLevel.playSound(null, player.blockPosition(), SoundEvents.LEASH_KNOT_BREAK,
                        SoundSource.PLAYERS, 1.0F, 1.0F);
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResult.SUCCESS;
        }

        ItemStack stack = context.getItemInHand();
        CustomData customData = stack.getOrDefault(DataComponents.ENTITY_DATA, CustomData.EMPTY);

        if (customData.isEmpty()) {
            return InteractionResult.PASS;
        }

        CompoundTag tag = customData.copyTag();

        BlockPos blockpos = context.getClickedPos();
        Direction direction = context.getClickedFace();
        BlockPos spawnPos = level.getBlockState(blockpos).getCollisionShape(level, blockpos).isEmpty() ? blockpos
                : blockpos.relative(direction);

        // Remove UUID so a new entity can be spawned if needed, or keep it to restore
        // exactly.
        // Usually restore exactly is better for lasso.
        tag.remove("Pos"); // Remove saved position to spawn where clicked

        Optional<Entity> spawnedEntity = EntityType.create(tag, serverLevel);

        if (spawnedEntity.isPresent()) {
            Entity entity = spawnedEntity.get();
            entity.setPos(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5);

            if (serverLevel.addFreshEntity(entity)) {
                stack.remove(DataComponents.ENTITY_DATA);
                serverLevel.gameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, spawnPos);
                serverLevel.playSound(null, spawnPos, SoundEvents.LEASH_KNOT_PLACE, SoundSource.PLAYERS, 1.0F, 1.0F);
                return InteractionResult.CONSUME;
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        CustomData customData = stack.getOrDefault(DataComponents.ENTITY_DATA, CustomData.EMPTY);

        if (!customData.isEmpty()) {
            CompoundTag tag = customData.copyTag();

            // Get Name
            if (tag.contains("id", 8)) {
                String id = tag.getString("id");
                Optional<EntityType<?>> type = EntityType.byString(id);
                if (type.isPresent()) {
                    tooltipComponents.add(type.get().getDescription().copy().withStyle(ChatFormatting.GREEN));
                }
            }

            // Get UUID
            if (tag.hasUUID("UUID")) {
                UUID uuid = tag.getUUID("UUID");
                tooltipComponents.add(Component.literal("UUID: " + uuid.toString()).withStyle(ChatFormatting.GRAY));
            }
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
