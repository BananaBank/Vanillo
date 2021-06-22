package rusty.vanillo.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SnowBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import rusty.vanillo.registry.VBlocks;

import java.util.List;
import java.util.Random;

public class VGrassSlab extends SlabBlock implements IGrowable {
    public static final BooleanProperty SNOWY = BlockStateProperties.SNOWY;



    public VGrassSlab(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
        return state.getValue(TYPE) != SlabType.BOTTOM && plantable.getPlantType(world, pos) == PlantType.PLAINS;
    }

    //From GrassBlock
    public boolean isValidBonemealTarget(IBlockReader reader, BlockPos pos, BlockState state, boolean p_176473_4_) {
        return state.getValue(TYPE) != SlabType.BOTTOM && reader.getBlockState(pos.above()).isAir();
    }

    public boolean isBonemealSuccess(World world, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerWorld servWorld, Random rand, BlockPos pos, BlockState state) {
        BlockPos blockpos = pos.above();
        BlockState blockstate = Blocks.GRASS.defaultBlockState();

        label48:
        for (int i = 0; i < 128; ++i) {
            BlockPos blockpos1 = blockpos;

            for (int j = 0; j < i / 16; ++j) {
                blockpos1 = blockpos1.offset(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
                if (!servWorld.getBlockState(blockpos1.below()).is(this) || servWorld.getBlockState(blockpos1).isCollisionShapeFullBlock(servWorld, blockpos1)) {
                    continue label48;
                }
            }

            BlockState blockstate2 = servWorld.getBlockState(blockpos1);
            if (blockstate2.is(blockstate.getBlock()) && rand.nextInt(10) == 0) {
                ((IGrowable)blockstate.getBlock()).performBonemeal(servWorld, rand, blockpos1, blockstate2);
            }

            if (blockstate2.isAir()) {
                BlockState blockstate1;
                if (rand.nextInt(8) == 0) {
                    List<ConfiguredFeature<?, ?>> list = servWorld.getBiome(blockpos1).getGenerationSettings().getFlowerFeatures();
                    if (list.isEmpty()) {
                        continue;
                    }

                    ConfiguredFeature<?, ?> configuredfeature = list.get(0);
                    FlowersFeature flowersfeature = (FlowersFeature)configuredfeature.feature;
                    blockstate1 = flowersfeature.getRandomFlower(rand, blockpos1, configuredfeature.config());
                } else {
                    blockstate1 = blockstate;
                }

                if (blockstate1.canSurvive(servWorld, blockpos1)) {
                    servWorld.setBlock(blockpos1, blockstate1, 3);
                }
            }
        }

    }


    //From SpreadableSnowyDirtBlock
    private static boolean canBeGrass(BlockState state, IWorldReader reader, BlockPos pos) {
        BlockPos blockpos = pos.above();
        BlockState blockstate = reader.getBlockState(blockpos);
        if (blockstate.is(Blocks.SNOW) && blockstate.getValue(SnowBlock.LAYERS) == 1) {
            return true;
        } else if (blockstate.getFluidState().getAmount() == 8) {
            return false;
        } else {
            int k = blockstate.getLightBlock(reader, blockpos);
            int i = LightEngine.getLightBlockInto(reader, state, pos, blockstate, blockpos, Direction.UP, k);
            return i < reader.getMaxLightLevel();
        }
    }

    private static boolean canPropagate(BlockState state, IWorldReader reader, BlockPos pos) {
        BlockPos blockpos = pos.above();
        return canBeGrass(state, reader, pos) && !reader.getFluidState(blockpos).is(FluidTags.WATER);
    }

    public void randomTick(BlockState state, ServerWorld servWorld, BlockPos pos, Random rand) {
        // ! means not
        if (!canBeGrass(state, servWorld, pos)) {
            if (!servWorld.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            // Sets the block to a dirt slab with the same half property as this one
            servWorld.setBlockAndUpdate(pos, VBlocks.DIRT_SLAB.get().defaultBlockState().setValue(TYPE, state.getValue(TYPE))); //Blocks.DIRT
        } else {
            if (servWorld.getMaxLocalRawBrightness(pos.above()) >= 9) {
                BlockState blockstate = this.defaultBlockState();

                for(int i = 0; i < 4; ++i) {
                    BlockPos blockpos = pos.offset(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
                    BlockState otherState = servWorld.getBlockState(blockpos);
                    if (canPropagate(blockstate, servWorld, blockpos)) {
                        if (otherState.is(VBlocks.DIRT_SLAB.get())) {
                            servWorld.setBlockAndUpdate(blockpos, blockstate.setValue(TYPE, otherState.getValue(TYPE)).setValue(SNOWY, servWorld.getBlockState(blockpos.above()).is(Blocks.SNOW)));
                        } else if (otherState.is(Blocks.DIRT)) {
                            servWorld.setBlockAndUpdate(blockpos, blockstate.setValue(SNOWY, servWorld.getBlockState(blockpos.above()).is(Blocks.SNOW)));
                        }
                    }
                }
            }

        }
    }


    // From SnowyDirtBlock
    public BlockState updateShape(BlockState selfState, Direction fromDirection, BlockState fromState, IWorld world, BlockPos pos, BlockPos fromPos) {
        if (fromDirection != Direction.UP) {
            return super.updateShape(selfState, fromDirection, fromState, world, pos, fromPos);
        } else {
            return super.updateShape(selfState, fromDirection, fromState, world, pos, fromPos).setValue(SNOWY, fromState.is(Blocks.SNOW_BLOCK) || fromState.is(Blocks.SNOW));
        }
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().above());
        // Call super for correct slab halves
        return super.getStateForPlacement(context).setValue(SNOWY, blockstate.is(Blocks.SNOW_BLOCK) || blockstate.is(Blocks.SNOW));
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(SNOWY, TYPE, WATERLOGGED);
    }
}
