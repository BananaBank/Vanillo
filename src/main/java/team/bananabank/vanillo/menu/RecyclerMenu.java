package team.bananabank.vanillo.menu;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import team.bananabank.vanillo.registry.VRecipeTypes;
import team.bananabank.vanillo.registry.VMenuTypes;

public class RecyclerMenu extends AbstractFurnaceMenu {
    public RecyclerMenu(int windowId, Inventory playerInv) {
        super(VMenuTypes.RECYCLER.get(), VRecipeTypes.RECYCLING.get(), RecipeBookType.BLAST_FURNACE, windowId, playerInv);
    }

    public RecyclerMenu(int windowId, Inventory playerInv, Container tile, ContainerData dataSlots) {
        super(VMenuTypes.RECYCLER.get(), VRecipeTypes.RECYCLING.get(), RecipeBookType.BLAST_FURNACE, windowId, playerInv, tile, dataSlots);
    }
}