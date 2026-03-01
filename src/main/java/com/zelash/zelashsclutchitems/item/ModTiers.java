package com.zelash.zelashsclutchitems.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.SimpleTier;

public class ModTiers {

    public static final Tier STONE_HAMMER_TIER = new SimpleTier(
            Tiers.STONE.getIncorrectBlocksForDrops(),
            400,
            Tiers.STONE.getSpeed(),
            Tiers.STONE.getAttackDamageBonus(),
            Tiers.STONE.getEnchantmentValue(),
            () -> Tiers.STONE.getRepairIngredient()
    );

    public static final Tier IRON_HAMMER_TIER = new SimpleTier(
            Tiers.IRON.getIncorrectBlocksForDrops(),
            800,
            Tiers.IRON.getSpeed(),
            Tiers.IRON.getAttackDamageBonus(),
            Tiers.IRON.getEnchantmentValue(),
            () -> Tiers.IRON.getRepairIngredient()
    );

    public static final Tier GOLD_HAMMER_TIER = new SimpleTier(
            Tiers.GOLD.getIncorrectBlocksForDrops(),
            100,
            Tiers.GOLD.getSpeed(),
            Tiers.GOLD.getAttackDamageBonus(),
            Tiers.GOLD.getEnchantmentValue(),
            () -> Tiers.GOLD.getRepairIngredient()
    );

    public static final Tier DIAMOND_HAMMER_TIER = new SimpleTier(
            Tiers.DIAMOND.getIncorrectBlocksForDrops(),
            3500,
            Tiers.DIAMOND.getSpeed(),
            Tiers.DIAMOND.getAttackDamageBonus(),
            Tiers.DIAMOND.getEnchantmentValue(),
            () -> Tiers.DIAMOND.getRepairIngredient()
    );

    public static final Tier NETHERITE_HAMMER_TIER = new SimpleTier(
            Tiers.NETHERITE.getIncorrectBlocksForDrops(),
            6000,
            Tiers.NETHERITE.getSpeed(),
            Tiers.NETHERITE.getAttackDamageBonus(),
            Tiers.NETHERITE.getEnchantmentValue(),
            () -> Tiers.NETHERITE.getRepairIngredient()
    );
}
