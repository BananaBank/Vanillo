package rusty.vanillo.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import rusty.vanillo.registry.VItems;
import rusty.vanillo.registry.VRecipeSerializers;

public class RecyclingRecipe extends AbstractCookingRecipe {
    public RecyclingRecipe(ResourceLocation id, String group, Ingredient input, ItemStack result, float exp, int duration) {
        super(VRecipeTypes.RECYCLING, id, group, input, result, exp, duration);
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(VItems.RECYCLER.get());
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return VRecipeSerializers.RECYCLING.get();
    }
}
