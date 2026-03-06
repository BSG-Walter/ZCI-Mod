package com.zelash.zelashsclutchitems.item;

import com.zelash.zelashsclutchitems.Config;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Supplier;

public class XPTomeItem extends Item {
    protected final Supplier<Integer> maxCapacitySupplier;

    public XPTomeItem(Properties properties, Supplier<Integer> maxCapacitySupplier) {
        super(properties);
        this.maxCapacitySupplier = maxCapacitySupplier;
    }

    // Replaces player.giveExperiencePoints with a precise total XP calculator to
    // avoid rounding and level bugs
    private void addPlayerXP(Player player, int amount) {
        int targetXp = getTotalXp(player) + amount;
        if (targetXp < 0)
            targetXp = 0;

        player.totalExperience = targetXp;
        player.experienceLevel = 0;
        player.experienceProgress = 0;

        while (targetXp > 0) {
            int xpToNext = player.getXpNeededForNextLevel();
            if (targetXp >= xpToNext) {
                targetXp -= xpToNext;
                player.experienceLevel++;
            } else {
                player.experienceProgress = (float) targetXp / (float) xpToNext;
                targetXp = 0;
            }
        }
    }

    private int getTotalXp(Player player) {
        int xp = 0;
        for (int i = 0; i < player.experienceLevel; i++) {
            xp += getXpNeededForNextLevel(i);
        }
        xp += Math.round(player.experienceProgress * player.getXpNeededForNextLevel());
        return xp;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            int storedXp = itemStack.getOrDefault(ModDataComponentTypes.STORED_XP, 0);

            if (player.isCrouching()) {
                // Store XP
                int maxCapacity = this.maxCapacitySupplier.get();
                if (storedXp < maxCapacity && player.totalExperience > 0) {
                    int xpToStore = 0;

                    if (Config.XPTOME_TRANSFER_ONE_LEVEL_AT_A_TIME.get()) {
                        int currentLevelProgress = Math
                                .round(player.experienceProgress * player.getXpNeededForNextLevel());
                        xpToStore = currentLevelProgress > 0 ? currentLevelProgress
                                : getXpNeededForNextLevel(player.experienceLevel - 1);
                    } else {
                        xpToStore = getTotalXp(player);
                    }

                    int availableSpace = maxCapacity - storedXp;
                    int actualStored = Math.min(xpToStore, availableSpace);

                    if (actualStored > 0) {
                        addPlayerXP(player, -actualStored);
                        itemStack.set(ModDataComponentTypes.STORED_XP, storedXp + actualStored);
                        level.playSound(null, player.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP,
                                SoundSource.PLAYERS, 0.5f, 1.0f);
                        return InteractionResultHolder.success(itemStack);
                    }
                }
            } else {
                // Retrieve XP
                if (storedXp > 0) {
                    int rawXpToExtract = storedXp;

                    if (Config.XPTOME_TRANSFER_ONE_LEVEL_AT_A_TIME.get()) {
                        int currentLevelXpNeeded = player.getXpNeededForNextLevel();
                        int missingForNextLevel = currentLevelXpNeeded
                                - Math.round(player.experienceProgress * currentLevelXpNeeded);
                        rawXpToExtract = Math.min(storedXp,
                                missingForNextLevel > 0 ? missingForNextLevel : player.getXpNeededForNextLevel());
                    }

                    double retrievalPercentage = Config.XPTOME_RETRIEVAL_PERCENTAGE.get();
                    int xpToGive = (int) Math.round(rawXpToExtract * retrievalPercentage);

                    if (xpToGive > 0) {
                        addPlayerXP(player, xpToGive);
                    }

                    itemStack.set(ModDataComponentTypes.STORED_XP, storedXp - rawXpToExtract);
                    level.playSound(null, player.blockPosition(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 0.5f,
                            1.0f);
                    return InteractionResultHolder.success(itemStack);
                }
            }
        }

        return InteractionResultHolder.pass(itemStack);
    }

    private int getXpNeededForNextLevel(int level) {
        if (level >= 30) {
            return 112 + (level - 30) * 9;
        } else if (level >= 15) {
            return 37 + (level - 15) * 5;
        } else {
            return 7 + level * 2;
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        int storedXp = stack.getOrDefault(ModDataComponentTypes.STORED_XP, 0);
        int maxCapacity = this.maxCapacitySupplier.get();
        tooltipComponents.add(Component.translatable("tooltip.zelashsclutchitems.xp_tome.stored", storedXp, maxCapacity)
                .withStyle(ChatFormatting.GREEN));
        tooltipComponents.add(Component.translatable("tooltip.zelashsclutchitems.xp_tome.store_instruction")
                .withStyle(ChatFormatting.GRAY));
        tooltipComponents.add(Component.translatable("tooltip.zelashsclutchitems.xp_tome.retrieve_instruction")
                .withStyle(ChatFormatting.GRAY));
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return stack.getOrDefault(ModDataComponentTypes.STORED_XP, 0) > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        int storedXp = stack.getOrDefault(ModDataComponentTypes.STORED_XP, 0);
        int maxCapacity = this.maxCapacitySupplier.get();
        return Math.round(13.0F * storedXp / maxCapacity);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return 0x80FF20; // Experience green
    }
}
