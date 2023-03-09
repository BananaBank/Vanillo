package team.bananabank.vanillo.registry;

import team.bananabank.vanillo.menu.RecyclerMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import team.bananabank.vanillo.Vanillo;

public class VMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, Vanillo.ID);

    public static final RegistryObject<MenuType<RecyclerMenu>> RECYCLER = MENUS.register("recycler", () -> new MenuType<>(RecyclerMenu::new));
}
