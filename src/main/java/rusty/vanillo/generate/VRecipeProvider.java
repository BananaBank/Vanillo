package rusty.vanillo.generate;

import com.google.common.collect.Maps;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.data.SmithingRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import rusty.vanillo.registry.VItems;

import java.util.Map;
import java.util.function.Consumer;

/**
 * @author TheDarkColour, cfrishausen, DJRoundTable
 */
public class VRecipeProvider extends RecipeProvider {
    public VRecipeProvider(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        createSlabRecipes(consumer);
        createRailRecipes(consumer);
        createVoidEquipmentRecipes(consumer);
        createFoodRecipes(consumer);
    }

    private void createFoodRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(VItems.ENDER_OMELETTE.get())
                .define('D', Items.BLAZE_POWDER)
                .define('R', Items.ENDER_EYE)
                .define('S', Items.MAGMA_CREAM)
                .define('X', Items.MILK_BUCKET)
                .define('Z', Items.DRAGON_EGG)
                .pattern(" S ")
                .pattern("XZX")
                .pattern("DRD")
                .unlockedBy("has_item", has(Items.DRAGON_EGG))
                .save(consumer);
    }

    private void createSlabRecipes(Consumer<IFinishedRecipe> localConsumer) {
        // Puts slabs and their related blocks into map.
        Map<Item, Item> slabsAndBlocks = Maps.newHashMap();

        slabsAndBlocks.put(Items.OAK_SLAB, Items.OAK_PLANKS);
        slabsAndBlocks.put(Items.SPRUCE_SLAB, Items.SPRUCE_PLANKS);
        slabsAndBlocks.put(Items.BIRCH_SLAB, Items.BIRCH_PLANKS);
        slabsAndBlocks.put(Items.JUNGLE_SLAB, Items.JUNGLE_PLANKS);
        slabsAndBlocks.put(Items.ACACIA_SLAB, Items.ACACIA_PLANKS);
        slabsAndBlocks.put(Items.DARK_OAK_SLAB, Items.DARK_OAK_PLANKS);
        slabsAndBlocks.put(Items.CRIMSON_SLAB, Items.CRIMSON_PLANKS);
        slabsAndBlocks.put(Items.WARPED_SLAB, Items.WARPED_PLANKS);
        slabsAndBlocks.put(Items.STONE_SLAB, Items.STONE);
        slabsAndBlocks.put(Items.SMOOTH_STONE_SLAB, Items.SMOOTH_STONE);
        slabsAndBlocks.put(Items.CUT_STANDSTONE_SLAB, Items.CUT_SANDSTONE);
        slabsAndBlocks.put(Items.COBBLESTONE_SLAB, Items.COBBLESTONE);
        slabsAndBlocks.put(Items.BRICK_SLAB, Items.BRICK);
        slabsAndBlocks.put(Items.CUT_RED_SANDSTONE_SLAB, Items.CUT_RED_SANDSTONE);
        slabsAndBlocks.put(Items.PURPUR_SLAB, Items.PURPUR_BLOCK);
        slabsAndBlocks.put(Items.PRISMARINE_SLAB, Items.PRISMARINE);
        slabsAndBlocks.put(Items.PRISMARINE_BRICK_SLAB, Items.PRISMARINE_BRICKS);
        slabsAndBlocks.put(Items.DARK_PRISMARINE_SLAB, Items.DARK_PRISMARINE);
        slabsAndBlocks.put(Items.POLISHED_GRANITE_SLAB, Items.POLISHED_GRANITE);
        slabsAndBlocks.put(Items.SMOOTH_RED_SANDSTONE_SLAB, Items.SMOOTH_RED_SANDSTONE);
        slabsAndBlocks.put(Items.MOSSY_STONE_BRICK_SLAB, Items.MOSSY_STONE_BRICKS);
        slabsAndBlocks.put(Items.POLISHED_DIORITE_SLAB, Items.POLISHED_DIORITE);
        slabsAndBlocks.put(Items.MOSSY_COBBLESTONE_SLAB, Items.MOSSY_COBBLESTONE);
        slabsAndBlocks.put(Items.END_STONE_BRICK_SLAB, Items.END_STONE_BRICKS);
        slabsAndBlocks.put(Items.SMOOTH_SANDSTONE_SLAB, Items.SMOOTH_SANDSTONE);
        slabsAndBlocks.put(Items.SMOOTH_QUARTZ_SLAB, Items.SMOOTH_QUARTZ);
        slabsAndBlocks.put(Items.GRANITE_SLAB, Items.GRANITE);
        slabsAndBlocks.put(Items.ANDESITE_SLAB, Items.ANDESITE);
        slabsAndBlocks.put(Items.RED_NETHER_BRICK_SLAB, Items.RED_NETHER_BRICKS);
        slabsAndBlocks.put(Items.POLISHED_ANDESITE_SLAB, Items.POLISHED_ANDESITE);
        slabsAndBlocks.put(Items.DIORITE_SLAB, Items.DIORITE);
        slabsAndBlocks.put(Items.BLACKSTONE_SLAB, Items.BLACKSTONE);
        slabsAndBlocks.put(Items.POLISHED_BLACKSTONE_BRICK_SLAB, Items.POLISHED_BLACKSTONE_BRICKS);

        // Iterates map keys which are slab items.
        for (Item slab : slabsAndBlocks.keySet()) {
            // Creates recipe from each slab to its related blockItem.
            ShapedRecipeBuilder.shaped(slabsAndBlocks.get(slab).getItem(), 1)
                    .pattern("X")
                    .pattern("X")
                    .define('X', Ingredient.of(slab))
                    .unlockedBy("has_item", has(slab.getItem()))
                    .save(localConsumer);
        }
    }

    private void createRailRecipes(Consumer<IFinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(VItems.DIAMOND_POWERED_RAIL.get(), 12)
                .define('D', Tags.Items.GEMS_DIAMOND)
                .define('R', Tags.Items.DUSTS_REDSTONE)
                .define('S', Tags.Items.RODS_WOODEN)
                .pattern("DRD")
                .pattern("DSD")
                .pattern("DRD")
                .unlockedBy("has_item", has(Items.REDSTONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(VItems.NETHERITE_POWERED_RAIL.get(), 12)
                .define('D', Tags.Items.INGOTS_NETHERITE)
                .define('R', Tags.Items.DUSTS_REDSTONE)
                .define('S', Tags.Items.RODS_WOODEN)
                .pattern("DRD")
                .pattern("DSD")
                .pattern("DRD")
                .unlockedBy("has_item", has(Items.REDSTONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(VItems.WOODEN_RAIL.get(), 8)
                .define('D', ItemTags.PLANKS)
                .define('S', Tags.Items.RODS_WOODEN)
                .pattern("D D")
                .pattern("DSD")
                .pattern("D D")
                .unlockedBy("has_item", has(ItemTags.PLANKS))
                .save(consumer);

    }

    private void createVoidEquipmentRecipes(Consumer<IFinishedRecipe> consumer) {
        Item voidIngot = VItems.VOID_CRYSTAL.get();

        ShapelessRecipeBuilder.shapeless(VItems.VOID_CRYSTAL.get())
                .requires(VItems.VOID_SHARD.get(), 4)
                .requires(VItems.VOID_ESSENCE.get(), 1)
                .requires(Items.DRAGON_BREATH, 1)
                .unlockedBy("has_item", has(VItems.VOID_SHARD.get()))
                .save(consumer);

        // void armor
        smithing(consumer, Ingredient.of(Items.NETHERITE_BOOTS), voidIngot, VItems.VOID_BOOTS.get());
        smithing(consumer, Ingredient.of(Items.NETHERITE_LEGGINGS), voidIngot, VItems.VOID_LEGGINGS.get());
        smithing(consumer, Ingredient.of(Items.NETHERITE_CHESTPLATE), voidIngot, VItems.VOID_CHESTPLATE.get());
        smithing(consumer, Ingredient.of(Items.NETHERITE_HELMET), voidIngot, VItems.VOID_HELMET.get());
        // void tools
        smithing(consumer, Ingredient.of(Items.NETHERITE_SWORD), voidIngot, VItems.VOID_SWORD.get());
        smithing(consumer, Ingredient.of(Items.NETHERITE_SHOVEL), voidIngot, VItems.VOID_SHOVEL.get());
        smithing(consumer, Ingredient.of(Items.NETHERITE_PICKAXE), voidIngot, VItems.VOID_PICKAXE.get());
        smithing(consumer, Ingredient.of(Items.NETHERITE_AXE), voidIngot, VItems.VOID_AXE.get());
        smithing(consumer, Ingredient.of(Items.NETHERITE_HOE), voidIngot, VItems.VOID_HOE.get());

        // void block
        ShapedRecipeBuilder.shaped(VItems.VOID_BLOCK.get())
                .define('x', VItems.VOID_CRYSTAL.get())
                .pattern("xxx")
                .pattern("xxx")
                .pattern("xxx")
                .unlockedBy("has_item", has(VItems.VOID_CRYSTAL.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(VItems.VOID_CRYSTAL.get(), 9)
                .requires(VItems.VOID_BLOCK.get())
                .unlockedBy("has_item", has(VItems.VOID_CRYSTAL.get()))
                .save(consumer, "void_crystal_from_block");
    }

    /**
     * Creates a smithing recipe.
     * @param consumer Recipe consumer to save the recipe as a file
     * @param firstSlot Tool/item to upgrade (first slot)
     * @param secondSlot Upgrade material (second slot)
     * @param result Resulting upgraded item
     */
    private void smithing(Consumer<IFinishedRecipe> consumer, Ingredient firstSlot, Item secondSlot, Item result) {
        SmithingRecipeBuilder.smithing(firstSlot, Ingredient.of(secondSlot), result).unlocks("has_item", has(secondSlot)).save(consumer, result.getRegistryName().getPath() + "_smithing");
    }
}
