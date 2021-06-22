package rusty.vanillo.container;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.item.crafting.RecipeBookCategory;
import net.minecraft.util.IIntArray;
import rusty.vanillo.recipe.VRecipeTypes;
import rusty.vanillo.registry.VContainerTypes;

// red because you need to implement the method
public class RecyclerContainer extends AbstractFurnaceContainer {

    public RecyclerContainer(int windowId, PlayerInventory playerInv) {
        super(VContainerTypes.RECYCLER.get(), VRecipeTypes.RECYCLING, RecipeBookCategory.BLAST_FURNACE, windowId, playerInv);
    }

    public RecyclerContainer(int windowId, PlayerInventory playerInv, IInventory tile, IIntArray dataSlots) {
        super(VContainerTypes.RECYCLER.get(), VRecipeTypes.RECYCLING, RecipeBookCategory.BLAST_FURNACE, windowId, playerInv, tile, dataSlots);
    }
}