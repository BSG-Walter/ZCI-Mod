package com.zelash.zelashsclutchitems.network;

import com.zelash.zelashsclutchitems.ZelashsClutchItems;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import com.zelash.zelashsclutchitems.item.ModItems;
import com.zelash.zelashsclutchitems.item.CraftingStickItem;
import com.zelash.zelashsclutchitems.menu.ItemCraftingMenu;

public record OpenCraftingStickPayload() implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<OpenCraftingStickPayload> TYPE = 
        new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(ZelashsClutchItems.MODID, "open_crafting_stick"));

    public static final StreamCodec<ByteBuf, OpenCraftingStickPayload> STREAM_CODEC = StreamCodec.unit(new OpenCraftingStickPayload());

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (player instanceof ServerPlayer serverPlayer) {
                if (CraftingStickItem.hasCraftingStickInHotbar(serverPlayer)) {
                    CraftingStickItem.openCraftingMenu(serverPlayer);
                }
            }
        });
    }
}
