package rusty.vanillo.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import rusty.vanillo.Vanillo;
import rusty.vanillo.client.screen.RecyclerScreen;
import rusty.vanillo.recipe.RecyclingRecipe;
import rusty.vanillo.recipe.VRecipeTypes;
import rusty.vanillo.registry.VItems;

@JeiPlugin
public class VanilloJeiPlugin implements IModPlugin {
    private RecyclingRecipeCategory RECYCLING_CATEGORY;

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Vanillo.ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();

        RECYCLING_CATEGORY = new RecyclingRecipeCategory(guiHelper);

        registration.addRecipeCategories(RECYCLING_CATEGORY);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeValidator<RecyclingRecipe> validator = new RecipeValidator<>(RECYCLING_CATEGORY);
        registration.addRecipes(validator.getResults(VRecipeTypes.RECYCLING), RecyclingRecipeCategory.ID);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(RecyclerScreen.class, 78, 32, 28, 23, new ResourceLocation[]{RecyclingRecipeCategory.ID, VanillaRecipeCategoryUid.FUEL});
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        ItemStack recycler = new ItemStack(VItems.RECYCLER.get());

        registration.addRecipeCatalyst(recycler, RecyclingRecipeCategory.ID);
        registration.addRecipeCatalyst(recycler, VanillaRecipeCategoryUid.FUEL);
    }
}
