package rusty.vanillo.generate;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.IForgeRegistryEntry;
import rusty.vanillo.Vanillo;
import rusty.vanillo.registry.VBlocks;
import rusty.vanillo.registry.VItems;

/**
 * @author cfrishausen, TheDarkColour
 */
public class VItemModelProvider extends ItemModelProvider {
    public VItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Vanillo.ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        decorationBlocks();
        voidGear();
        rails();
        remnantsOfRift();

        // Recycler
        generic3d(VItems.RECYCLER);
    }

    private void remnantsOfRift() {
        generic3d(VItems.VOID_BLOCK, VItems.VOID_ORE);
        generic2d(VItems.VOID_SHARD);
        generic2d(VItems.VOID_CRYSTAL);
    }

    private void rails() {
        generic2d(VItems.WOODEN_RAIL);
        generic2d(VItems.GLOWSTONE_RAIL);
        generic2d(VItems.DIAMOND_POWERED_RAIL);
        generic2d(VItems.NETHERITE_POWERED_RAIL);
        generic2d(VItems.VOID_POWERED_RAIL);
    }

    private void decorationBlocks() {
        // Creates dirt slab models that only take one line defining the parent.
        generic3d(VBlocks.DIRT_SLAB);
        generic3d(VBlocks.COARSE_DIRT_SLAB);

        generic3d(VItems.BRICK_BRICKS, VItems.BRICK_BRICK_SLAB, VItems.BRICK_BRICK_STAIRS);
        generic3d(VItems.STONE_BRICK_BRICKS, VItems.STONE_BRICK_BRICK_SLAB, VItems.STONE_BRICK_BRICK_STAIRS);
    }

    private void toolModels(RegistryObject<Item> item) {
        String itemName = item.getId().getPath();

        singleTexture(itemName, mcLoc("item/handheld"),
                "layer0", modLoc("item/" + itemName));

    }

    private void voidGear(){
        // Tools
        toolModels(VItems.VOID_AXE);
        toolModels(VItems.VOID_PICKAXE);
        toolModels(VItems.VOID_HOE);
        toolModels(VItems.VOID_SWORD);
        toolModels(VItems.VOID_SHOVEL);

        // Armor
        generic2d(VItems.VOID_HELMET);
        generic2d(VItems.VOID_CHESTPLATE);
        generic2d(VItems.VOID_LEGGINGS);
        generic2d(VItems.VOID_BOOTS);
    }

    @SafeVarargs
    private final <T extends ItemLike & IForgeRegistryEntry<T>> void generic3d(RegistryObject<T>... objects) {
        for (RegistryObject<? extends ItemLike> obj : objects) {
            generic3d(obj);
        }
    }

    // Generic 3d item model like anvil or a dirt block. Requires Block form.
    private void generic3d(RegistryObject<? extends ItemLike> supplier) {
        String path = supplier.getId().getPath();
        withExistingParent(path, modLoc(BLOCK_FOLDER + "/" + path));
    }

    // Generic 2d item model like lantern or hopper. Requires Item form.
    private void generic2d(RegistryObject<? extends ItemLike> supplier) {
        String path = supplier.getId().getPath();
        getBuilder(path).parent(new ModelFile.UncheckedModelFile(mcLoc("item/generated"))).texture("layer0", modLoc("item/" + path));
    }
}
