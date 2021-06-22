package rusty.vanillo.tileentity;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import rusty.vanillo.container.RecyclerContainer;
import rusty.vanillo.recipe.VRecipeTypes;
import rusty.vanillo.registry.VBlocks;
import rusty.vanillo.registry.VTileEntities;

public class RecyclerTileEntity extends AbstractFurnaceTileEntity {
    public RecyclerTileEntity() {
        super(VTileEntities.RECYCLER_ENTITY.get(), VRecipeTypes.RECYCLING);
    }

    @Override
    public void tick() {
        super.tick();

        // Play sound at correct time and don't play if someone takes item out
        int progress = dataAccess.get(2);
        // Pretty much just plays new sound after old one finishes and doesn't play if the sound will play over the end of the recipe
        // If half the sound still plays in the current recipe then the other half will play into the other recipe
        if (!level.isClientSide && ((progress - 1) % 32 == 0 && (progress + 16 < dataAccess.get(3)))) {
            level.getBlockTicks().scheduleTick(getBlockPos(), VBlocks.RECYCLER.get(), 0);
        }
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.recycler");
    }

    @Override
    protected Container createMenu(int windowId, PlayerInventory playerInv) {
        return new RecyclerContainer(windowId, playerInv, this, dataAccess);
    }
}
