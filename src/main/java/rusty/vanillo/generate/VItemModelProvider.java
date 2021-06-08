package rusty.vanillo.generate;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import rusty.vanillo.Vanillo;
import net.minecraftforge.client.model.generators.ItemModelProvider;

public class VItemModelProvider extends ItemModelProvider {
    public VItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Vanillo.ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        dirtModels();
    }

    private void dirtModels() {
        // Creates dirt slab models that only take one line defining the parent.
        withExistingParent("dirt_slab", modLoc("block/dirt_slab"));
        withExistingParent("coarse_dirt_slab", modLoc("block/coarse_dirt_slab"));
    }
}
