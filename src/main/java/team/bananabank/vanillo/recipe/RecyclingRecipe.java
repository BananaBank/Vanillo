package team.bananabank.vanillo.recipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import team.bananabank.vanillo.registry.VItems;
import team.bananabank.vanillo.registry.VRecipeSerializers;
import team.bananabank.vanillo.registry.VRecipeTypes;

public class RecyclingRecipe extends AbstractCookingRecipe {
    public RecyclingRecipe(ResourceLocation id, String group, Ingredient input, ItemStack result, float exp, int duration) {
        super(VRecipeTypes.RECYCLING.get(), id, group, null, input, result, exp, duration);
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(VItems.RECYCLER.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return VRecipeSerializers.RECYCLING.get();
    }
}
