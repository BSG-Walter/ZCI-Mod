package com.zelash.zelashsclutchitems.item;

import com.zelash.zelashsclutchitems.ZelashsClutchItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> MINEABLE_WITH_PAXEL = tag("mineable/paxel");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(ZelashsClutchItems.MODID, name));
        }
    }
}
