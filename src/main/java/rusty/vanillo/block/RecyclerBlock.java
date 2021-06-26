package rusty.vanillo.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import rusty.vanillo.registry.VSoundEvents;
import rusty.vanillo.tileentity.RecyclerTileEntity;

import javax.annotation.Nullable;
import java.util.Random;

public class RecyclerBlock extends HorizontalBlock {
    public RecyclerBlock(Properties properties) {
        super(properties);

        registerDefaultState(getStateDefinition().any().setValue(BlockStateProperties.LIT, false).setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.LIT, FACING);
    }

    @Override
    public RecyclerTileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new RecyclerTileEntity();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult result) {
        if (!level.isClientSide) {
            TileEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof INamedContainerProvider) {
                NetworkHooks.openGui((ServerPlayerEntity) playerIn, (INamedContainerProvider) tileEntity, tileEntity.getBlockPos());
            }
        }
        return ActionResultType.sidedSuccess(level.isClientSide);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        return this.defaultBlockState().setValue(FACING, p_196258_1_.getHorizontalDirection().getOpposite());
    }

    @Override
    public void animateTick(BlockState state, World level, BlockPos pos, Random rand) {
        /*if (state.getValue(BlockStateProperties.LIT)) {
            double d0 = pos.getX() + 0.5;
            double d1 = pos.getY();
            double d2 = pos.getZ() + 0.5;
            if (rand.nextDouble() < 0.1D) {
                level.playLocalSound(d0, d1, d2, VSoundEvents.RECYCLER.get(), SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }
        }*/
    }

    @Override
    public void tick(BlockState p_225534_1_, ServerWorld level, BlockPos pos, Random p_225534_4_) {
        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.5;
        double z = pos.getZ() + 0.5;

        level.playSound(null, x, y, z, VSoundEvents.RECYCLER.get(), SoundCategory.BLOCKS, 1.0f, 1.0f);
    }

    // From ChestBlock
    @Override
    public void onRemove(BlockState oldState, World level, BlockPos pos, BlockState newState, boolean p_196243_5_) {
        if (!oldState.is(newState.getBlock())) {
            TileEntity te = level.getBlockEntity(pos);

            if (te instanceof IInventory) {
                InventoryHelper.dropContents(level, pos, (IInventory) te);
                level.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(oldState, level, pos, newState, p_196243_5_);
        }
    }
}
