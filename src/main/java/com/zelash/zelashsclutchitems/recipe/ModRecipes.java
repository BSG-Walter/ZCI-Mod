package com.zelash.zelashsclutchitems.recipe;

import com.zelash.zelashsclutchitems.ZelashsClutchItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, ZelashsClutchItems.MODID);

    public static final Supplier<RecipeSerializer<XPTomeCraftingRecipe>> XP_TOME_CRAFTING =
            RECIPE_SERIALIZERS.register("xp_tome_crafting", XPTomeCraftingRecipe.Serializer::new);

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
    }
}
