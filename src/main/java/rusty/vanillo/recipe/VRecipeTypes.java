package rusty.vanillo.recipe;

import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import rusty.vanillo.Vanillo;

public class VRecipeTypes {
    public static final IRecipeType<RecyclingRecipe> RECYCLING = Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(Vanillo.ID, "recycling"), new IRecipeType<RecyclingRecipe>() {
        @Override
        public String toString() {
            return "recycling";
        }
    });
}
