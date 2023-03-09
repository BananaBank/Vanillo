package team.bananabank.vanillo.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import team.bananabank.vanillo.Vanillo;
import team.bananabank.vanillo.recipe.RecyclingRecipe;

public class VRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> BLOCKS = DeferredRegister.create(Registries.RECIPE_TYPE, Vanillo.ID);

    public static final RegistryObject<RecipeType<RecyclingRecipe>> RECYCLING = BLOCKS.register("recycling", () -> RecipeType.simple(new ResourceLocation(Vanillo.ID, "recycling")));
}
