package com.zelash.zelashsclutchitems.block;

import com.zelash.zelashsclutchitems.ZelashsClutchItems;
import com.zelash.zelashsclutchitems.block.custom.ElevatorBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ZelashsClutchItems.MODID);

    public static final DeferredBlock<Block> ELEVATOR = BLOCKS.registerBlock("elevator", ElevatorBlock::new,
            BlockBehaviour.Properties.of()
                    .strength(0.8F)
                    .sound(SoundType.WOOL));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
