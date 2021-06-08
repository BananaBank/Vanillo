package rusty.vanillo.generate;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import rusty.vanillo.Vanillo;
import rusty.vanillo.registry.VBlocks;

import javax.annotation.Nullable;

/**
 * @author TheDarkColour
 */
public class VBlockTagsProvider extends BlockTagsProvider {
    public VBlockTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, Vanillo.ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.RAILS).add(VBlocks.WOODEN_RAIL.get());
        tag(BlockTags.RAILS).add(VBlocks.GLOWSTONE_RAIL.get());
        tag(BlockTags.RAILS).add(VBlocks.DIAMOND_POWERED_RAIL.get());
        tag(BlockTags.RAILS).add(VBlocks.NETHERITE_POWERED_RAIL.get());
        tag(BlockTags.DRAGON_IMMUNE).add(VBlocks.VOID_ORE.get(), VBlocks.VOID_BLOCK.get());
        tag(BlockTags.WITHER_IMMUNE).add(VBlocks.VOID_ORE.get(), VBlocks.VOID_BLOCK.get());
    }
}
