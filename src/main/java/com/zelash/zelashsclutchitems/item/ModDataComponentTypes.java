package com.zelash.zelashsclutchitems.item;

import net.minecraft.core.registries.Registries;
import com.mojang.serialization.Codec;
import com.zelash.zelashsclutchitems.ZelashsClutchItems;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModDataComponentTypes {
    public static final DeferredRegister.DataComponents DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, ZelashsClutchItems.MODID);

    public static final Supplier<DataComponentType<Integer>> STORED_XP = DATA_COMPONENT_TYPES.registerComponentType("stored_xp",
            builder -> builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.VAR_INT));

    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
