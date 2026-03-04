package com.zelash.zelashsclutchitems.network;

import com.zelash.zelashsclutchitems.ZelashsClutchItems;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = ZelashsClutchItems.MODID)
public class ModNetworking {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");

        registrar.playToServer(
            OpenCraftingStickPayload.TYPE,
            OpenCraftingStickPayload.STREAM_CODEC,
            (payload, context) -> payload.handle(context)
        );
    }
}
