package com.zelash.zelashsclutchitems.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTiers {
    public static final Tier STONE_HAMMER_TIER = new Tier() {
        @Override
        public int getUses() {
            return 400;
        }

        @Override
        public float getSpeed() {
            return Tiers.STONE.getSpeed();
        }

        @Override
        public float getAttackDamageBonus() {
            return Tiers.STONE.getAttackDamageBonus();
        }

        @Override
        public TagKey<Block> getIncorrectBlocksForDrops() {
            return Tiers.STONE.getIncorrectBlocksForDrops();
        }

        @Override
        public int getEnchantmentValue() {
            return Tiers.STONE.getEnchantmentValue();
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Tiers.STONE.getRepairIngredient();
        }
    };

    public static final Tier IRON_HAMMER_TIER = new Tier() {
        @Override
        public int getUses() {
            return 800;
        }

        @Override
        public float getSpeed() {
            return Tiers.IRON.getSpeed();
        }

        @Override
        public float getAttackDamageBonus() {
            return Tiers.IRON.getAttackDamageBonus();
        }

        @Override
        public TagKey<Block> getIncorrectBlocksForDrops() {
            return Tiers.IRON.getIncorrectBlocksForDrops();
        }

        @Override
        public int getEnchantmentValue() {
            return Tiers.IRON.getEnchantmentValue();
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Tiers.IRON.getRepairIngredient();
        }
    };

    public static final Tier GOLD_HAMMER_TIER = new Tier() {
        @Override
        public int getUses() {
            return 100;
        }

        @Override
        public float getSpeed() {
            return Tiers.GOLD.getSpeed();
        }

        @Override
        public float getAttackDamageBonus() {
            return Tiers.GOLD.getAttackDamageBonus();
        }

        @Override
        public TagKey<Block> getIncorrectBlocksForDrops() {
            return Tiers.GOLD.getIncorrectBlocksForDrops();
        }

        @Override
        public int getEnchantmentValue() {
            return Tiers.GOLD.getEnchantmentValue();
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Tiers.GOLD.getRepairIngredient();
        }
    };

    public static final Tier DIAMOND_HAMMER_TIER = new Tier() {
        @Override
        public int getUses() {
            return 3500;
        }

        @Override
        public float getSpeed() {
            return Tiers.DIAMOND.getSpeed();
        }

        @Override
        public float getAttackDamageBonus() {
            return Tiers.DIAMOND.getAttackDamageBonus();
        }

        @Override
        public TagKey<Block> getIncorrectBlocksForDrops() {
            return Tiers.DIAMOND.getIncorrectBlocksForDrops();
        }

        @Override
        public int getEnchantmentValue() {
            return Tiers.DIAMOND.getEnchantmentValue();
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Tiers.DIAMOND.getRepairIngredient();
        }
    };

    public static final Tier NETHERITE_HAMMER_TIER = new Tier() {
        @Override
        public int getUses() {
            return 6000;
        }

        @Override
        public float getSpeed() {
            return Tiers.NETHERITE.getSpeed();
        }

        @Override
        public float getAttackDamageBonus() {
            return Tiers.NETHERITE.getAttackDamageBonus();
        }

        @Override
        public TagKey<Block> getIncorrectBlocksForDrops() {
            return Tiers.NETHERITE.getIncorrectBlocksForDrops();
        }

        @Override
        public int getEnchantmentValue() {
            return Tiers.NETHERITE.getEnchantmentValue();
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Tiers.NETHERITE.getRepairIngredient();
        }
    };
}
