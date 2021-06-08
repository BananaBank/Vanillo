package rusty.vanillo.generate;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;
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
        dirtSlabModels(VBlocks.DIRT_SLAB, Blocks.DIRT);
        dirtSlabModels(VBlocks.COARSE_DIRT_SLAB, Blocks.COARSE_DIRT);

        // Creating Blockstates
        ResourceLocation dirtLoc = mcLoc("block/" + Blocks.DIRT.getRegistryName().getPath());
        ResourceLocation coarseDirtLoc = mcLoc("block/" + Blocks.COARSE_DIRT.getRegistryName().getPath());
        // First parameter is slab block
        // Second parameter is full block model
        // Third parameter is slab texture
        slabBlock(VBlocks.DIRT_SLAB.get(), dirtLoc, dirtLoc);
        slabBlock(VBlocks.COARSE_DIRT_SLAB.get(), coarseDirtLoc, coarseDirtLoc);
    }

    public void dirtSlabModels(RegistryObject<SlabBlock> slab, Block baseBlock) {
        String registryName = slab.get().getRegistryName().getPath(); // String registry name of the slab.
        String registryNameBase = baseBlock.getRegistryName().getPath(); // String registry name of double slab block.

        // Each Block needs two models, one for bottom slab and one for top.
        models().getBuilder(registryName)
                .parent(models().getExistingFile(mcLoc("block/slab"))) // Bottom slab specificaiton.
                .texture("bottom", mcLoc("block/" + registryNameBase))
                .texture("top", mcLoc("block/" + registryNameBase))
                .texture("side", mcLoc("block/" + registryNameBase));
        models().getBuilder(registryName)
                .parent(models().getExistingFile(mcLoc("block/slab_top"))) // Top slab specification.
                .texture("bottom", mcLoc("block/" + registryNameBase))
                .texture("top", mcLoc("block/" + registryNameBase))
                .texture("side", mcLoc("block/" + registryNameBase));
    }
}
