package rusty.vanillo.generate;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PoweredRailBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fmllegacy.RegistryObject;
import rusty.vanillo.Vanillo;
import rusty.vanillo.registry.VBlocks;

/**
 * @author cfrishausen
 */
public class VBlockStatesProvider extends BlockStateProvider {
    public VBlockStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Vanillo.ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Creating Models
        //dirtSlabModels(VBlocks.DIRT_SLAB, Blocks.DIRT);
        //dirtSlabModels(VBlocks.COARSE_DIRT_SLAB, Blocks.COARSE_DIRT);

        // Creating Blockstates + Models
        ResourceLocation dirtLoc = mcLoc("block/" + Blocks.DIRT.getRegistryName().getPath());
        ResourceLocation coarseDirtLoc = mcLoc("block/" + Blocks.COARSE_DIRT.getRegistryName().getPath());
        // First parameter is slab block
        // Second parameter is full block model
        // Third parameter is slab texture
        slabBlock(VBlocks.DIRT_SLAB.get(), dirtLoc, dirtLoc);
        slabBlock(VBlocks.COARSE_DIRT_SLAB.get(), coarseDirtLoc, coarseDirtLoc);

        // Brick Bricks
        simpleBlock(VBlocks.BRICK_BRICKS.get());
        slab(VBlocks.BRICK_BRICK_SLAB, modLoc("block/brick_bricks"));
        stairsBlock(VBlocks.BRICK_BRICK_STAIRS.get(), modLoc("block/brick_bricks"));

        // Stone Brick Bricks
        simpleBlock(VBlocks.STONE_BRICK_BRICKS.get());
        slab(VBlocks.STONE_BRICK_BRICK_SLAB, modLoc("block/stone_brick_bricks"));
        stairsBlock(VBlocks.STONE_BRICK_BRICK_STAIRS.get(), modLoc("block/stone_brick_bricks"));

        // Rails
        poweredRail(VBlocks.DIAMOND_POWERED_RAIL);
        poweredRail(VBlocks.NETHERITE_POWERED_RAIL);
        poweredRail(VBlocks.VOID_POWERED_RAIL);
    }

    private void slab(RegistryObject<? extends SlabBlock> supplier, ResourceLocation texture) {
        slabBlock(supplier.get(), texture, texture);
    }

    private void curvedRail(RegistryObject<Block> rail) {
        String name = rail.get().getRegistryName().getPath();

        ModelFile normalRail = railModel(name, "rail_flat", name);
        ModelFile raisedNe = railModel(name + "_raised_ne", "template_rail_raised_ne", name);
        ModelFile raisedSw = railModel(name + "raised_sw", "template_rail_raised_sw", name);
        ModelFile corner = railModel(name + "_corner", "rail_curved", name + "_corner");
    }

    private void poweredRail(RegistryObject<Block> rail) {
        String name = rail.get().getRegistryName().getPath();

        // Unpowered models
        ModelFile normalRail = railModel(name, "rail_flat", name);
        ModelFile raisedNe = railModel(name + "_raised_ne", "template_rail_raised_ne", name);
        ModelFile raisedSw = railModel(name + "raised_sw", "template_rail_raised_sw", name);

        // Powered models
        ModelFile normalRailOn = railModel(name + "_on", "rail_flat", name + "_on");
        ModelFile raisedNeOn = railModel(name + "_on_raised_ne", "template_rail_raised_ne", name + "_on");
        ModelFile raisedSwOn = railModel(name + "_on_raised_sw", "template_rail_raised_sw", name + "_on");

        // Blockstate
        VariantBlockStateBuilder builder = getVariantBuilder(rail.get());

        poweredRailStates(builder, false, normalRail, raisedNe, raisedSw); // Add non powered rails
        poweredRailStates(builder, true, normalRailOn, raisedNeOn, raisedSwOn); // Add powered rails
    }

    //private void curvedRailStates(VariantBlockStateBuilder builder, boolean )

    private void poweredRailStates(VariantBlockStateBuilder builder, boolean isPoweredOn, ModelFile normal, ModelFile raisedNe, ModelFile raisedSw) {
        VariantBlockStateBuilder.PartialBlockstate partialState = builder.partialState().with(PoweredRailBlock.POWERED, isPoweredOn);

        partialState.with(PoweredRailBlock.SHAPE, RailShape.ASCENDING_EAST).modelForState().modelFile(raisedNe).rotationY(90).addModel();
        partialState.with(PoweredRailBlock.SHAPE, RailShape.ASCENDING_NORTH).modelForState().modelFile(raisedNe).addModel();
        partialState.with(PoweredRailBlock.SHAPE, RailShape.ASCENDING_SOUTH).modelForState().modelFile(raisedSw).addModel();
        partialState.with(PoweredRailBlock.SHAPE, RailShape.ASCENDING_WEST).modelForState().modelFile(raisedSw).rotationY(90).addModel();
        partialState.with(PoweredRailBlock.SHAPE, RailShape.EAST_WEST).modelForState().modelFile(normal).rotationY(90).addModel();
        partialState.with(PoweredRailBlock.SHAPE, RailShape.NORTH_SOUTH).modelForState().modelFile(normal).addModel();
    }

    private ModelFile railModel(String modelName, String modelParent, String texture) {
        return models().getBuilder(modelName)
                .parent(new ModelFile.UncheckedModelFile(mcLoc("block/" + modelParent)))
                .texture("rail", modLoc("block/" + texture));
    }
}

