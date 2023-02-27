package rusty.vanillo.recipe;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import rusty.vanillo.Vanillo;

public class VRecipeTypes {
    public static final RecipeType<RecyclingRecipe> RECYCLING = Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(Vanillo.ID, "recycling"), new RecipeType<RecyclingRecipe>() {
        @Override
        public String toString() {
            return "recycling";
        }
    });
}
