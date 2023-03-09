package team.bananabank.vanillo.generate;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import team.bananabank.vanillo.Vanillo;
import team.bananabank.vanillo.registry.VBlocks;

import java.util.concurrent.CompletableFuture;

/**
 * @author TheDarkColour
 */
public class VBlockTagsProvider extends BlockTagsProvider {
    public VBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Vanillo.ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.RAILS).add(VBlocks.WOODEN_RAIL.get());
        tag(BlockTags.RAILS).add(VBlocks.GLOWSTONE_RAIL.get());
        tag(BlockTags.RAILS).add(VBlocks.DIAMOND_POWERED_RAIL.get());
        tag(BlockTags.RAILS).add(VBlocks.NETHERITE_POWERED_RAIL.get());
        tag(BlockTags.RAILS).add(VBlocks.VOID_POWERED_RAIL.get());
        tag(BlockTags.DRAGON_IMMUNE).add(VBlocks.VOID_ORE.get(), VBlocks.VOID_BLOCK.get());
        tag(BlockTags.WITHER_IMMUNE).add(VBlocks.VOID_ORE.get(), VBlocks.VOID_BLOCK.get());

        tag(BlockTags.MINEABLE_WITH_AXE).add(VBlocks.WOODEN_RAIL.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(VBlocks.RECYCLER.get(), VBlocks.GLOWSTONE_RAIL.get(), VBlocks.DIAMOND_POWERED_RAIL.get(), VBlocks.NETHERITE_POWERED_RAIL.get(), VBlocks.VOID_POWERED_RAIL.get(), VBlocks.VOID_ORE.get(), VBlocks.VOID_BLOCK.get());
        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(VBlocks.DIRT_SLAB.get(), VBlocks.COARSE_DIRT_SLAB.get());
        tag(Tags.Blocks.NEEDS_NETHERITE_TOOL).add(VBlocks.VOID_ORE.get(), VBlocks.VOID_BLOCK.get());
    }
}
