package rusty.vanillo.registry;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rusty.vanillo.Vanillo;
import rusty.vanillo.recipe.RecyclingRecipe;

public class VRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Vanillo.ID);

    public static final RegistryObject<SimpleCookingSerializer<RecyclingRecipe>> RECYCLING = SERIALIZERS.register("recycling", () -> new SimpleCookingSerializer<>(RecyclingRecipe::new, 200));
}
