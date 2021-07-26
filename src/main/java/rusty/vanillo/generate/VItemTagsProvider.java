package rusty.vanillo.generate;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import rusty.vanillo.Vanillo;

/**
 * @author TheDarkColour
 */
public class VItemTagsProvider extends ItemTagsProvider {
    public VItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTags, ExistingFileHelper helper) {
        super(dataGenerator, blockTags, Vanillo.ID, helper);
    }

    @Override
    protected void addTags() {
        copy(BlockTags.RAILS, ItemTags.RAILS);
    }
}
