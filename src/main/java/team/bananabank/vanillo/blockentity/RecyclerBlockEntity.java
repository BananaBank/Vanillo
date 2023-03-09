package team.bananabank.vanillo.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import team.bananabank.vanillo.menu.RecyclerMenu;
import team.bananabank.vanillo.registry.VBlockEntities;
import team.bananabank.vanillo.registry.VBlocks;
import team.bananabank.vanillo.registry.VRecipeTypes;

public class RecyclerBlockEntity extends AbstractFurnaceBlockEntity {
    public RecyclerBlockEntity(BlockPos pos, BlockState state) {
        super(VBlockEntities.RECYCLER_ENTITY.get(), pos, state, VRecipeTypes.RECYCLING.get());
    }

    public static void tick(Level level, BlockPos pos, BlockState state, RecyclerBlockEntity recycler) {
        AbstractFurnaceBlockEntity.serverTick(level, pos, state, recycler);

        // Play sound at correct time and don't play if someone takes item out
        int progress = recycler.dataAccess.get(2);
        // Pretty much just plays new sound after old one finishes and doesn't play if the sound will play over the end of the recipe
        // If half the sound still plays in the current recipe then the other half will play into the other recipe
        if (!level.isClientSide && ((progress - 1) % 32 == 0 && (progress + 16 < recycler.dataAccess.get(3)))) {
            level.scheduleTick(pos, VBlocks.RECYCLER.get(), 0);
        }
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.recycler");
    }

    @Override
    protected AbstractContainerMenu createMenu(int windowId, Inventory playerInv) {
        return new RecyclerMenu(windowId, playerInv, this, dataAccess);
    }
}
