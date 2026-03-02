package com.zelash.zelashsclutchitems.recipe;

import com.mojang.serialization.MapCodec;
import com.zelash.zelashsclutchitems.item.ModDataComponentTypes;
import com.zelash.zelashsclutchitems.item.XPTomeItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class XPTomeCraftingRecipe implements CraftingRecipe {
    private final ShapedRecipe baseRecipe;

    public XPTomeCraftingRecipe(ShapedRecipe baseRecipe) {
        this.baseRecipe = baseRecipe;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        return this.baseRecipe.matches(input, level);
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider provider) {
        ItemStack output = this.baseRecipe.assemble(input, provider);

        int totalXp = 0;
        for (int i = 0; i < input.size(); i++) {
            ItemStack slotStack = input.getItem(i);
            if (!slotStack.isEmpty() && slotStack.getItem() instanceof XPTomeItem) {
                totalXp += slotStack.getOrDefault(ModDataComponentTypes.STORED_XP, 0);
            }
        }

        if (totalXp > 0 && output.getItem() instanceof XPTomeItem) {
            output.set(ModDataComponentTypes.STORED_XP, totalXp);
        }

        return output;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return this.baseRecipe.canCraftInDimensions(width, height);
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.baseRecipe.getResultItem(provider);
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.baseRecipe.getIngredients();
    }

    @Override
    public boolean isSpecial() {
        return this.baseRecipe.isSpecial();
    }

    @Override
    public CraftingBookCategory category() {
        return this.baseRecipe.category();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.XP_TOME_CRAFTING.get();
    }

    public static class Serializer implements RecipeSerializer<XPTomeCraftingRecipe> {
        public static final MapCodec<XPTomeCraftingRecipe> CODEC = ShapedRecipe.Serializer.CODEC.xmap(
                XPTomeCraftingRecipe::new,
                recipe -> recipe.baseRecipe
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, XPTomeCraftingRecipe> STREAM_CODEC = StreamCodec.of(
                (buf, recipe) -> ShapedRecipe.Serializer.STREAM_CODEC.encode(buf, recipe.baseRecipe),
                buf -> new XPTomeCraftingRecipe(ShapedRecipe.Serializer.STREAM_CODEC.decode(buf))
        );

        @Override
        public MapCodec<XPTomeCraftingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, XPTomeCraftingRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
