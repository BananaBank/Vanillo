package rusty.vanillo.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fmllegacy.network.NetworkHooks;
import rusty.vanillo.registry.VBlockEntities;
import rusty.vanillo.registry.VSoundEvents;
import rusty.vanillo.tileentity.RecyclerBlockEntity;

import javax.annotation.Nullable;
import java.util.Random;

public class RecyclerBlock extends HorizontalDirectionalBlock implements EntityBlock {
    public RecyclerBlock(Properties properties) {
        super(properties);

        registerDefaultState(getStateDefinition().any().setValue(BlockStateProperties.LIT, false).setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.LIT, FACING);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide) {
            return createTickerHelper(type, VBlockEntities.RECYCLER_ENTITY.get(), RecyclerBlockEntity::tick);
        } else {
            return createTickerHelper(type, VBlockEntities.RECYCLER_ENTITY.get(), AbstractFurnaceBlockEntity::serverTick);
        }
    }

    @Nullable
    private static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> type, BlockEntityType<E> expected, BlockEntityTicker<? super E> ticker) {
        return expected == type ? (BlockEntityTicker<A>) ticker : null;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new RecyclerBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult result) {
        if (!level.isClientSide) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof MenuProvider) {
                NetworkHooks.openGui((ServerPlayer) playerIn, (MenuProvider) tileEntity, tileEntity.getBlockPos());
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public void tick(BlockState p_225534_1_, ServerLevel level, BlockPos pos, Random p_225534_4_) {
        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.5;
        double z = pos.getZ() + 0.5;

        level.playSound(null, x, y, z, VSoundEvents.RECYCLER.get(), SoundSource.BLOCKS, 1.0f, 1.0f);
    }

    // From ChestBlock
    @Override
    public void onRemove(BlockState oldState, Level level, BlockPos pos, BlockState newState, boolean p_196243_5_) {
        if (!oldState.is(newState.getBlock())) {
            BlockEntity te = level.getBlockEntity(pos);

            if (te instanceof Container) {
                Containers.dropContents(level, pos, (Container) te);
                level.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(oldState, level, pos, newState, p_196243_5_);
        }
    }
}
