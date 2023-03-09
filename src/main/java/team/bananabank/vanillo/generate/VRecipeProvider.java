package team.bananabank.vanillo.generate;

import com.google.common.collect.Maps;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import team.bananabank.vanillo.Vanillo;
import team.bananabank.vanillo.registry.VItems;

import java.util.Map;
import java.util.function.Consumer;

/**
 * @author TheDarkColour, cfrishausen, DJRoundTable
 */
public class VRecipeProvider extends RecipeProvider {
    public VRecipeProvider(PackOutput gen) {
        super(gen);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        createSlabRecipes(consumer);
        createBrickBrickRecipes(consumer);
        createRailRecipes(consumer);
        createVoidEquipmentRecipes(consumer);
        addRecyclingRecipes(consumer);
        createBlockRecipes(consumer);
        createFoodRecipes(consumer);
    }

    private void createBrickBrickRecipes(Consumer<FinishedRecipe> consumer) {
        brickBricks(consumer, Items.BRICKS, Ingredient.of(Items.BRICKS), VItems.BRICK_BRICKS.get());
        brickBricks(consumer, Items.STONE_BRICKS, Ingredient.of(Items.STONE_BRICKS, Items.STONE), VItems.STONE_BRICK_BRICKS.get());

        makeSlabsRecipe(consumer, VItems.BRICK_BRICKS.get(), Ingredient.of(VItems.BRICK_BRICKS.get()), VItems.BRICK_BRICK_SLAB.get());
        makeSlabsRecipe(consumer, VItems.STONE_BRICK_BRICKS.get(), Ingredient.of(Items.STONE_BRICKS, Items.STONE, VItems.STONE_BRICK_BRICKS.get()), VItems.STONE_BRICK_BRICK_SLAB.get());

        makeStairsRecipe(consumer, VItems.BRICK_BRICKS.get(), Ingredient.of(VItems.BRICK_BRICKS.get()), VItems.BRICK_BRICK_STAIRS.get());
        makeStairsRecipe(consumer, VItems.STONE_BRICK_BRICKS.get(), Ingredient.of(Items.STONE_BRICKS, Items.STONE, VItems.STONE_BRICK_BRICKS.get()), VItems.STONE_BRICK_BRICK_STAIRS.get());
    }

    private void createSlabRecipes(Consumer<FinishedRecipe> consumer) {
        // Puts slabs and their related blocks into map.
        Map<Item, Item> slabsAndBlocks = Maps.newHashMap();

        // Dirt and coarse dirt slabs
        makeSlabsRecipe(consumer, Items.DIRT, Ingredient.of(Items.DIRT), VItems.DIRT_SLAB.get());
        makeSlabsRecipe(consumer, Items.COARSE_DIRT, Ingredient.of(Items.COARSE_DIRT), VItems.COARSE_DIRT_SLAB.get());

        slabsAndBlocks.put(VItems.COARSE_DIRT_SLAB.get(), Items.COARSE_DIRT);
        slabsAndBlocks.put(VItems.DIRT_SLAB.get(), Items.DIRT);

        slabsAndBlocks.put(VItems.BRICK_BRICK_SLAB.get(), VItems.BRICK_BRICKS.get());
        slabsAndBlocks.put(VItems.STONE_BRICK_BRICK_SLAB.get(), VItems.STONE_BRICK_BRICKS.get());

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
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, slabsAndBlocks.get(slab), 1)
                    .pattern("X")
                    .pattern("X")
                    .define('X', Ingredient.of(slab))
                    .unlockedBy("has_item", has(slab))
                    // avoid overriding vanilla recipes
                    .save(consumer, new ResourceLocation(Vanillo.ID, BuiltInRegistries.ITEM.getKey(slab).getPath() + "_from_slabs"));
        }
    }

    private void createBlockRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, VItems.RECYCLER.get(), 1)
                .define('D', Tags.Items.STORAGE_BLOCKS_IRON)
                .define('R', Tags.Items.GEMS_DIAMOND)
                .define('S', Items.HOPPER)
                .define('F', Items.SMOOTH_STONE)
                .define('A', Items.BLAST_FURNACE)
                .pattern(" R ")
                .pattern("DSD")
                .pattern("FAF")
                .unlockedBy("has_item", has(Items.BLAST_FURNACE))
                .save(consumer);
    }

    private void createRailRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, VItems.DIAMOND_POWERED_RAIL.get(), 12)
                .define('D', Tags.Items.GEMS_DIAMOND)
                .define('R', Tags.Items.DUSTS_REDSTONE)
                .define('S', Tags.Items.RODS_WOODEN)
                .pattern("DRD")
                .pattern("DSD")
                .pattern("DRD")
                .unlockedBy("has_item", has(Items.REDSTONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, VItems.NETHERITE_POWERED_RAIL.get(), 12)
                .define('D', Tags.Items.INGOTS_NETHERITE)
                .define('R', Tags.Items.DUSTS_REDSTONE)
                .define('S', Tags.Items.RODS_WOODEN)
                .pattern("DRD")
                .pattern("DSD")
                .pattern("DRD")
                .unlockedBy("has_item", has(Items.REDSTONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, VItems.VOID_POWERED_RAIL.get(), 6)
                .define('D', VItems.VOID_SHARD.get())
                .define('R', Tags.Items.DUSTS_REDSTONE)
                .define('S', Tags.Items.RODS_WOODEN)
                .pattern("DRD")
                .pattern("DSD")
                .pattern("DRD")
                .unlockedBy("has_item", has(VItems.VOID_SHARD.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, VItems.WOODEN_RAIL.get(), 8)
                .define('D', ItemTags.PLANKS)
                .define('S', Tags.Items.RODS_WOODEN)
                .pattern("D D")
                .pattern("DSD")
                .pattern("D D")
                .unlockedBy("has_item", has(ItemTags.PLANKS))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, VItems.GLOWSTONE_RAIL.get(), 16)
                .define('D', Tags.Items.INGOTS_IRON)
                .define('S', Tags.Items.RODS_WOODEN)
                .define('A', Tags.Items.DUSTS_GLOWSTONE)
                .pattern("D D")
                .pattern("DSD")
                .pattern("DAD")
                .unlockedBy("has_item", has(Tags.Items.DUSTS_GLOWSTONE))
                .save(consumer);

    }

    private void createFoodRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, VItems.ENDER_OMELETTE.get())
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


    private void createVoidEquipmentRecipes(Consumer<FinishedRecipe> consumer) {
        Item voidIngot = VItems.VOID_SHARD.get();

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VItems.VOID_SHARD.get())
                .requires(VItems.VOID_CRYSTAL.get(), 4)
                //.requires(VItems.VOID_ESSENCE.get(), 1)
                .requires(Items.DRAGON_BREATH, 1)
                .requires(Items.ENDER_EYE, 1)
                .unlockedBy("has_item", has(VItems.VOID_CRYSTAL.get()))
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
        smithing(consumer, Ingredient.of(Items.BOW), voidIngot, VItems.VOID_BOW.get());

        // rails
        smithing(consumer, Ingredient.of(VItems.NETHERITE_POWERED_RAIL.get()), voidIngot, VItems.VOID_POWERED_RAIL.get());

        // void block
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, VItems.VOID_BLOCK.get())
                .define('x', VItems.VOID_SHARD.get())
                .pattern("xxx")
                .pattern("xxx")
                .pattern("xxx")
                .unlockedBy("has_item", has(VItems.VOID_SHARD.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VItems.VOID_SHARD.get(), 9)
                .requires(VItems.VOID_BLOCK.get())
                .unlockedBy("has_item", has(VItems.VOID_SHARD.get()))
                .save(consumer, "void_crystal_from_block");
    }

    private void makeSlabsRecipe(Consumer<FinishedRecipe> consumer, Item unlockTrigger, Ingredient stonecutterIngredient, Item slab) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, slab, 6)
                .pattern("XXX")
                .define('X', Ingredient.of(unlockTrigger))
                .unlockedBy("has_item", has(unlockTrigger))
                .save(consumer, BuiltInRegistries.ITEM.getKey(slab));
        stonecutter(consumer, unlockTrigger, stonecutterIngredient, slab, 2);
    }

    private void makeStairsRecipe(Consumer<FinishedRecipe> consumer, Item unlockTrigger, Ingredient stonecutterIngredient, Item stairs) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stairs, 4)
                .pattern("X  ")
                .pattern("XX ")
                .pattern("XXX")
                .define('X', Ingredient.of(unlockTrigger))
                .unlockedBy("has_item", has(unlockTrigger))
                .save(consumer, BuiltInRegistries.ITEM.getKey(stairs));
        stonecutter(consumer, unlockTrigger, stonecutterIngredient, stairs, 1);
    }

    private void brickBricks(Consumer<FinishedRecipe> consumer, Item unlockTrigger, Ingredient stonecutterIngredient, Item result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 4)
                .pattern("XX")
                .pattern("XX")
                .define('X', Ingredient.of(unlockTrigger))
                .unlockedBy("has_item", has(unlockTrigger))
                .save(consumer, BuiltInRegistries.ITEM.getKey(result));
        stonecutter(consumer, unlockTrigger, stonecutterIngredient, result, 1);
    }

    private void stonecutter(Consumer<FinishedRecipe> consumer, Item unlockTrigger, Ingredient input, Item result, int amount) {
        SingleItemRecipeBuilder.stonecutting(input, RecipeCategory.BUILDING_BLOCKS, result, amount)
                .unlockedBy("has_item", has(unlockTrigger))
                .save(consumer, new ResourceLocation(Vanillo.ID, BuiltInRegistries.ITEM.getKey(result).getPath() + "_stonecutting"));
    }

    /**
     * Creates a smithing recipe.
     * @param consumer Recipe consumer to save the recipe as a file
     * @param firstSlot Tool/item to upgrade (first slot)
     * @param secondSlot Upgrade material (second slot)
     * @param result Resulting upgraded item
     */
    private void smithing(Consumer<FinishedRecipe> consumer, Ingredient firstSlot, Item secondSlot, Item result) {
        UpgradeRecipeBuilder.smithing(firstSlot, Ingredient.of(secondSlot), RecipeCategory.TOOLS, result).unlocks("has_item", has(secondSlot)).save(consumer, BuiltInRegistries.ITEM.getKey(result).getPath() + "_smithing");
    }

    // Recycling
    public void recycling(Consumer<FinishedRecipe> consumer, Ingredient input, ItemStack output, float xp, int duration, String suffix) { // 200 duration is normal for furnace speed
        RecycleRecipeBuilder.recycling(input, output, xp, duration).unlockedBy("has_recycler", has(output.getItem())).save(consumer, output.getItem() + "_from_recycling" + suffix);
    }

    public void recycling(Consumer<FinishedRecipe> consumer, Ingredient input, ItemStack output, float xp, int duration) { // 200 duration is normal for furnace speed
        recycling(consumer, input, output, xp, duration, "");
    }

    public void recycling(Consumer<FinishedRecipe> consumer, ItemLike input, ItemStack output, float xp, int duration) { // 200 duration is normal for furnace speed
        recycling(consumer, Ingredient.of(input), output, xp, duration, "_" + BuiltInRegistries.ITEM.getKey(input.asItem()).getPath());
    }

    public void recycling(Consumer<FinishedRecipe> consumer, ItemLike input, ItemLike output, float xp, int duration) { // 200 duration is normal for furnace speed
        recycling(consumer, Ingredient.of(input), new ItemStack(output, 1), xp, duration, "_" + BuiltInRegistries.ITEM.getKey(input.asItem()).getPath());
    }

    private void addRecyclingRecipes(Consumer<FinishedRecipe> consumer) {
        // Furnace Xp for cobble is 0.1, Charcoal is 0.15, Cooked Beef is 0.35, Iron Ingot is 0.7, Gold Ingot is 1.0
        // Check usages (alt F7) of CookingRecipeBuilder.smelting to see more
        // Example recipe

        // Resource Items - Wood, Stone, Wool, Clay, Sticks,
        // Primitive Items - Made entirely of resource items. (Crafting Table)
        // Intermediate Items - Consists of some metals and resource items, requires a couple steps to obtain. (Metal Tool)
        // Advanced Items - Consists of Heavy Metals, Requires many steps to obtain. (Enchant table, Anvil)

        // Resource 0.1f ; 100 - 125, Primitive 0.15f ; 125 - 150, Intermediate 0.35f - 0.7f ; 200 - 400, Advanced 0.7f - 1.0f ; 400 - 800

        // Wool Recycling Recipes
        recycling(consumer,
                Ingredient.of(Items.WHITE_WOOL, Items.BLACK_WOOL, Items.GRAY_WOOL, Items.LIGHT_GRAY_WOOL, Items.BROWN_WOOL, Items.RED_WOOL, Items.ORANGE_WOOL, Items.YELLOW_WOOL, Items.LIME_WOOL, Items.GREEN_WOOL, Items.LIGHT_BLUE_WOOL, Items.CYAN_WOOL, Items.BLUE_WOOL, Items.PURPLE_WOOL, Items.MAGENTA_WOOL, Items.PINK_WOOL),
                new ItemStack(Items.STRING, 2),
                0.1f,
                100); //TODO Add Loot table to output. Add dye drops to loot table

        // Tile Entity Recycling Recipes
        recycling(consumer, Items.FURNACE, new ItemStack(Items.COBBLESTONE, 7), 0.15f, 150);
        recycling(consumer, Items.ENDER_CHEST, new ItemStack(Items.OBSIDIAN, 5), 0.7f, 400); // Add Ender Eye to loot table
        recycling(consumer, Items.CHEST, new ItemStack(Items.OAK_PLANKS, 6), 0.15f, 150);
        recycling(consumer, Items.ENCHANTING_TABLE, new ItemStack(Items.OBSIDIAN, 4), 0.7f, 400); // Add Diamond and Book to loot table
        recycling(consumer, Items.JUKEBOX, Items.DIAMOND, 0.35f, 200); //Add planks to loot table
        recycling(consumer, Items.SMOKER, new ItemStack(Items.OAK_LOG, 3), 0.15f, 150); // Add cobblestone to loot table

        // Crafting Block Recycling Recipes
        recycling(consumer, Items.CRAFTING_TABLE, new ItemStack(Items.OAK_PLANKS, 3), 0.15f, 100);
        recycling(consumer, Items.ANVIL, new ItemStack(Items.IRON_BLOCK, 2), 0.7f, 400);
        recycling(consumer, Items.BOOKSHELF, new ItemStack(Items.BOOK, 2), 0.15f, 150);

        // Block Recycling Recipes
        recycling(consumer,
                Ingredient.of(Items.WHITE_BED, Items.BLACK_BED, Items.GRAY_BED, Items.LIGHT_GRAY_BED, Items.BROWN_BED, Items.RED_BED, Items.ORANGE_BED, Items.YELLOW_BED, Items.LIME_BED, Items.GREEN_BED, Items.LIGHT_BLUE_BED, Items.CYAN_BED, Items.BLUE_BED, Items.PURPLE_BED, Items.MAGENTA_BED, Items.PINK_BED),
                new ItemStack(Items.WHITE_WOOL, 2),
                0.15f,
                125);
        recycling(consumer, Items.PISTON, Items.IRON_INGOT, 0.35f, 200); // Add Planks, Cobblestone, redstone to loot table
        recycling(consumer, Items.STICKY_PISTON, Items.SLIME_BALL, 0.35f, 200); // Add Iron, Planks, Cobblestone, redstone to loot table
        recycling(consumer, Items.LADDER, new ItemStack(Items.STICK, 5), 0.1f, 100);
        recycling(consumer, Items.LANTERN, new ItemStack(Items.IRON_NUGGET, 6), 0.35f, 200);
        recycling(consumer, Items.OBSERVER, new ItemStack(Items.QUARTZ, 2), 0.35f, 200);
        recycling(consumer, Items.CHAIN, new ItemStack(Items.IRON_INGOT, 1), 0.35f, 200);
        recycling(consumer, Items.TNT, new ItemStack(Items.GUNPOWDER, 3), 0.15f, 150);

        // Item Recycling Recipes

        recycling(consumer, Items.COMPASS, new ItemStack(Items.IRON_INGOT, 2), 0.35f, 200);
        recycling(consumer, Items.CLOCK, new ItemStack(Items.GOLD_INGOT, 2), 0.35f, 200);
        recycling(consumer, Items.SADDLE, new ItemStack(Items.LEATHER, 5), 0.15f, 150);
        recycling(consumer, Items.BUCKET, new ItemStack(Items.IRON_INGOT, 2), 0.35f, 200);
        recycling(consumer, Items.ARMOR_STAND, new ItemStack(Items.STICK, 3), 0.15f, 150);
        recycling(consumer, Items.VILLAGER_SPAWN_EGG, new ItemStack(Items.EMERALD, 3), 1.0f, 800);

        // Tool Recycling Recipes

        recycling(consumer,
                Ingredient.of(Items.WOODEN_AXE, Items.WOODEN_HOE, Items.WOODEN_PICKAXE, Items.WOODEN_SHOVEL, Items.WOODEN_SWORD),
                new ItemStack(Items.STICK, 3),
                0.15f,
                100,
                "_tools");

        recycling(consumer,
                Ingredient.of(Items.STONE_AXE, Items.STONE_HOE, Items.STONE_PICKAXE, Items.STONE_SHOVEL, Items.STONE_SWORD),
                new ItemStack(Items.COBBLESTONE, 1),
                0.15f,
                125,
                "_tools");

        recycling(consumer,
                Ingredient.of(Items.IRON_AXE, Items.IRON_HOE, Items.IRON_PICKAXE, Items.IRON_SHOVEL, Items.IRON_SWORD),
                new ItemStack(Items.IRON_INGOT, 1),
                0.35f,
                200,
                "_tools"
        );

        recycling(consumer,
                Ingredient.of(Items.GOLDEN_AXE, Items.GOLDEN_HOE, Items.GOLDEN_PICKAXE, Items.GOLDEN_SHOVEL, Items.GOLDEN_SWORD),
                new ItemStack(Items.GOLD_INGOT, 1),
                0.35f,
                200,
                "_tools");

        recycling(consumer,
                Ingredient.of(Items.DIAMOND_AXE, Items.DIAMOND_HOE, Items.DIAMOND_PICKAXE, Items.DIAMOND_SHOVEL, Items.DIAMOND_SWORD),
                new ItemStack(Items.DIAMOND, 1),
                0.7f,
                400,
                "_tools");

        recycling(consumer,
                Ingredient.of(Items.NETHERITE_AXE, Items.NETHERITE_HOE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_SWORD),
                new ItemStack(Items.NETHERITE_SCRAP, 3),
                1.0f,
                800,
                "_tools");

        recycling(consumer,
                Ingredient.of(VItems.VOID_AXE.get(), VItems.VOID_HOE.get() , VItems.VOID_PICKAXE.get(), VItems.VOID_SHOVEL.get(), VItems.VOID_SWORD.get()),
                new ItemStack(VItems.VOID_CRYSTAL.get(), 3),
                1.0f,
                800,
                "_tools");

        // Armor Recycling Recipes

        recycling(consumer,
                Ingredient.of(Items.LEATHER_HELMET, Items.LEATHER_CHESTPLATE, Items.LEATHER_LEGGINGS, Items.LEATHER_BOOTS),
                new ItemStack(Items.LEATHER, 2),
                0.15f,
                150,
                "_armor");

        recycling(consumer,
                Ingredient.of(Items.IRON_HELMET, Items.IRON_CHESTPLATE, Items.IRON_LEGGINGS, Items.IRON_BOOTS),
                new ItemStack(Items.IRON_INGOT, 2),
                0.35f,
                200,
                "_armor");

        recycling(consumer,
                Ingredient.of(Items.GOLDEN_HELMET, Items.GOLDEN_CHESTPLATE, Items.GOLDEN_LEGGINGS, Items.GOLDEN_BOOTS),
                new ItemStack(Items.GOLD_INGOT, 2),
                0.35f,
                200,
                "_armor");

        recycling(consumer,
                Ingredient.of(Items.DIAMOND_HELMET, Items.DIAMOND_CHESTPLATE, Items.DIAMOND_LEGGINGS, Items.DIAMOND_BOOTS),
                new ItemStack(Items.DIAMOND, 2),
                0.7f,
                400,
                "_armor");

        recycling(consumer,
                Ingredient.of(Items.NETHERITE_HELMET, Items.NETHERITE_CHESTPLATE, Items.NETHERITE_LEGGINGS, Items.NETHERITE_BOOTS),
                new ItemStack(Items.NETHERITE_SCRAP, 3),
                1.0f,
                800,
                "_armor");

        recycling(consumer,
                Ingredient.of(VItems.VOID_HELMET.get(), VItems.VOID_CHESTPLATE.get() , VItems.VOID_LEGGINGS.get(), VItems.VOID_BOOTS.get()),
                new ItemStack(VItems.VOID_CRYSTAL.get(), 3),
                1.0f,
                800,
                "_armor");
    }
}
