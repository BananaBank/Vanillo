package team.bananabank.vanillo.generate;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import team.bananabank.vanillo.Vanillo;

import java.util.concurrent.CompletableFuture;

/**
 * @author TheDarkColour
 */
public class VItemTagsProvider extends ItemTagsProvider {
    public VItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, BlockTagsProvider blockTags, ExistingFileHelper helper) {
        super(output, lookup, blockTags, Vanillo.ID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        copy(BlockTags.RAILS, ItemTags.RAILS);

    }
}
