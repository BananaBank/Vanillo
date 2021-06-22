package rusty.vanillo.registry;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rusty.vanillo.Vanillo;
import rusty.vanillo.container.RecyclerContainer;

public class VContainerTypes {

    public static final DeferredRegister<ContainerType<?>> CONTAINERS  = DeferredRegister.create(ForgeRegistries.CONTAINERS, Vanillo.ID);

    public static final RegistryObject<ContainerType<RecyclerContainer>> RECYCLER = CONTAINERS.register("recycler", () -> new ContainerType<>(RecyclerContainer::new));
}
