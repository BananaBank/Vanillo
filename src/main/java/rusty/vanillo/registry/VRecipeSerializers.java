package rusty.vanillo.registry;

import net.minecraft.item.crafting.CookingRecipeSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rusty.vanillo.Vanillo;
import rusty.vanillo.recipe.RecyclingRecipe;

public class VRecipeSerializers {
    public static final DeferredRegister<IRecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Vanillo.ID);

    public static final RegistryObject<CookingRecipeSerializer<RecyclingRecipe>> RECYCLING = SERIALIZERS.register("recycling", () -> new CookingRecipeSerializer<>(RecyclingRecipe::new, 200));
}
