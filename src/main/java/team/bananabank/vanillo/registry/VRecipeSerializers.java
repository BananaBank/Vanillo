package team.bananabank.vanillo.registry;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import team.bananabank.vanillo.Vanillo;
import team.bananabank.vanillo.recipe.RecyclingRecipe;

public class VRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Vanillo.ID);

    public static final RegistryObject<SimpleCookingSerializer<RecyclingRecipe>> RECYCLING = SERIALIZERS.register("recycling", () -> new SimpleCookingSerializer<>((pId, pGroup, pCategory, pIngredient, pResult, pExperience, pCookingTime) -> new RecyclingRecipe(pId, pGroup, pIngredient, pResult, pExperience, pCookingTime), 200));
}
