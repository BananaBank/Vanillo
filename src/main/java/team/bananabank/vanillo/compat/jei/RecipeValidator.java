/*
package rusty.vanillo.compat.jei;

import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.ArrayList;
import java.util.List;

public class RecipeValidator<T extends Recipe<?>> {
    private final IRecipeCategory<T> category;

    public RecipeValidator(IRecipeCategory<T> category) {
        this.category = category;
    }

    public <C extends Container, R extends Recipe<C>> List<T> getResults(RecipeType<R> type) {
        RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();
        ArrayList<R> list = new ArrayList<>();

        for (R recipe : manager.getAllRecipesFor(type)) {
            if (isRecipeValid((T) recipe)) {
                list.add(recipe);
            }
        }

        return (List<T>) list;
    }

    public boolean isRecipeValid(T recipe) {
        if (recipe.isSpecial()) {
            return false;
        } else {
            if (!recipe.getResultItem().isEmpty()) {
                return true;
            } else {
                System.out.printf("Recipe has no output {}", recipe);
                return false;
            }
        }
    }
}
*/
