package rusty.vanillo.registry;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rusty.vanillo.Vanillo;
import rusty.vanillo.container.RecyclerMenu;

public class VMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Vanillo.ID);

    public static final RegistryObject<MenuType<RecyclerMenu>> RECYCLER = MENUS.register("recycler", () -> new MenuType<>(RecyclerMenu::new));
}
