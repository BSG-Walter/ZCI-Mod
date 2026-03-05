package com.zelash.zelashsclutchitems.block;

import com.zelash.zelashsclutchitems.ZelashsClutchItems;
import com.zelash.zelashsclutchitems.block.custom.ElevatorBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
        public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ZelashsClutchItems.MODID);

        public static final DeferredBlock<Block> WHITE_ELEVATOR = BLOCKS.registerBlock("white_elevator",
                        ElevatorBlock::new,
                        BlockBehaviour.Properties.of().strength(0.8F).sound(SoundType.WOOL));

        public static final DeferredBlock<Block> ORANGE_ELEVATOR = BLOCKS.registerBlock("orange_elevator",
                        ElevatorBlock::new,
                        BlockBehaviour.Properties.of().strength(0.8F).sound(SoundType.WOOL));

        public static final DeferredBlock<Block> MAGENTA_ELEVATOR = BLOCKS.registerBlock("magenta_elevator",
                        ElevatorBlock::new,
                        BlockBehaviour.Properties.of().strength(0.8F).sound(SoundType.WOOL));

        public static final DeferredBlock<Block> LIGHT_BLUE_ELEVATOR = BLOCKS.registerBlock("light_blue_elevator",
                        ElevatorBlock::new,
                        BlockBehaviour.Properties.of().strength(0.8F).sound(SoundType.WOOL));

        public static final DeferredBlock<Block> YELLOW_ELEVATOR = BLOCKS.registerBlock("yellow_elevator",
                        ElevatorBlock::new,
                        BlockBehaviour.Properties.of().strength(0.8F).sound(SoundType.WOOL));

        public static final DeferredBlock<Block> LIME_ELEVATOR = BLOCKS.registerBlock("lime_elevator",
                        ElevatorBlock::new,
                        BlockBehaviour.Properties.of().strength(0.8F).sound(SoundType.WOOL));

        public static final DeferredBlock<Block> PINK_ELEVATOR = BLOCKS.registerBlock("pink_elevator",
                        ElevatorBlock::new,
                        BlockBehaviour.Properties.of().strength(0.8F).sound(SoundType.WOOL));

        public static final DeferredBlock<Block> GRAY_ELEVATOR = BLOCKS.registerBlock("gray_elevator",
                        ElevatorBlock::new,
                        BlockBehaviour.Properties.of().strength(0.8F).sound(SoundType.WOOL));

        public static final DeferredBlock<Block> LIGHT_GRAY_ELEVATOR = BLOCKS.registerBlock("light_gray_elevator",
                        ElevatorBlock::new,
                        BlockBehaviour.Properties.of().strength(0.8F).sound(SoundType.WOOL));

        public static final DeferredBlock<Block> CYAN_ELEVATOR = BLOCKS.registerBlock("cyan_elevator",
                        ElevatorBlock::new,
                        BlockBehaviour.Properties.of().strength(0.8F).sound(SoundType.WOOL));

        public static final DeferredBlock<Block> PURPLE_ELEVATOR = BLOCKS.registerBlock("purple_elevator",
                        ElevatorBlock::new,
                        BlockBehaviour.Properties.of().strength(0.8F).sound(SoundType.WOOL));

        public static final DeferredBlock<Block> BLUE_ELEVATOR = BLOCKS.registerBlock("blue_elevator",
                        ElevatorBlock::new,
                        BlockBehaviour.Properties.of().strength(0.8F).sound(SoundType.WOOL));

        public static final DeferredBlock<Block> BROWN_ELEVATOR = BLOCKS.registerBlock("brown_elevator",
                        ElevatorBlock::new,
                        BlockBehaviour.Properties.of().strength(0.8F).sound(SoundType.WOOL));

        public static final DeferredBlock<Block> GREEN_ELEVATOR = BLOCKS.registerBlock("green_elevator",
                        ElevatorBlock::new,
                        BlockBehaviour.Properties.of().strength(0.8F).sound(SoundType.WOOL));

        public static final DeferredBlock<Block> RED_ELEVATOR = BLOCKS.registerBlock("red_elevator", ElevatorBlock::new,
                        BlockBehaviour.Properties.of().strength(0.8F).sound(SoundType.WOOL));

        public static final DeferredBlock<Block> BLACK_ELEVATOR = BLOCKS.registerBlock("black_elevator",
                        ElevatorBlock::new,
                        BlockBehaviour.Properties.of().strength(0.8F).sound(SoundType.WOOL));

        public static void register(IEventBus eventBus) {
                BLOCKS.register(eventBus);
        }
}
