package rusty.vanillo.container;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import rusty.vanillo.recipe.VRecipeTypes;
import rusty.vanillo.registry.VMenuTypes;

// red because you need to implement the method
public class RecyclerMenu extends AbstractFurnaceMenu {

    public RecyclerMenu(int windowId, Inventory playerInv) {
        super(VMenuTypes.RECYCLER.get(), VRecipeTypes.RECYCLING, RecipeBookType.BLAST_FURNACE, windowId, playerInv);
    }

    public RecyclerMenu(int windowId, Inventory playerInv, Container tile, ContainerData dataSlots) {
        super(VMenuTypes.RECYCLER.get(), VRecipeTypes.RECYCLING, RecipeBookType.BLAST_FURNACE, windowId, playerInv, tile, dataSlots);
    }
}