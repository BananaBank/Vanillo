package rusty.vanillo.registry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rusty.vanillo.Vanillo;
import rusty.vanillo.block.RecyclerBlock;
import rusty.vanillo.block.VPoweredRailBlock;
import rusty.vanillo.block.VRailBlock;
import rusty.vanillo.block.VoidOre;

import java.util.function.Supplier;

public final class VBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Vanillo.ID);

    private static final AbstractBlock.IPositionPredicate NEVER = (s, e, x) -> false;

    // Extra slab blocks
    public static final RegistryObject<SlabBlock> DIRT_SLAB = registerCustomSlab("dirt_slab", () -> AbstractBlock.Properties.copy(Blocks.DIRT).harvestTool(ToolType.SHOVEL));
    public static final RegistryObject<SlabBlock> COARSE_DIRT_SLAB = registerCustomSlab("coarse_dirt_slab", () -> AbstractBlock.Properties.copy(Blocks.COARSE_DIRT).harvestTool(ToolType.SHOVEL));
    //public static final RegistryObject<VGrassSlab> GRASS_SLAB = BLOCKS.register("grass_slab", () -> new VGrassSlab(AbstractBlock.Properties.of(Material.GRASS).randomTicks().strength(0.6F).sound(SoundType.GRASS).noOcclusion()));

    // Stone Brick Bricks + Brick Bricks
    public static final RegistryObject<Block> BRICK_BRICKS = BLOCKS.register("brick_bricks", () -> new Block(AbstractBlock.Properties.copy(Blocks.BRICKS)));
    public static final RegistryObject<SlabBlock> BRICK_BRICK_SLAB = registerSlab("brick_brick_slab", BRICK_BRICKS);
    public static final RegistryObject<StairsBlock> BRICK_BRICK_STAIRS = registerStairs("brick_brick_stairs", BRICK_BRICKS);
    public static final RegistryObject<Block> STONE_BRICK_BRICKS = BLOCKS.register("stone_brick_bricks", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE_BRICKS)));
    public static final RegistryObject<SlabBlock> STONE_BRICK_BRICK_SLAB = registerSlab("stone_brick_brick_slab", STONE_BRICK_BRICKS);
    public static final RegistryObject<StairsBlock> STONE_BRICK_BRICK_STAIRS = registerStairs("stone_brick_brick_stairs", STONE_BRICK_BRICKS);

    // Recycler
    public static final RegistryObject<Block> RECYCLER = BLOCKS.register("recycler", () -> new RecyclerBlock(Block.Properties.of(Material.HEAVY_METAL).sound(SoundType.ANVIL).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).lightLevel(state -> state.getValue(BlockStateProperties.LIT) ? 13 : 0)));

    // Rails
    public static final RegistryObject<Block> WOODEN_RAIL = BLOCKS.register("wooden_rail", () -> new VRailBlock(0.1f, Block.Properties.of(Material.DECORATION).harvestTool(ToolType.AXE).sound(SoundType.LADDER).noCollission().strength(0.7F)));
    public static final RegistryObject<Block> GLOWSTONE_RAIL = BLOCKS.register("glowstone_rail", () -> new VRailBlock(0.6f, Block.Properties.of(Material.DECORATION).harvestTool(ToolType.PICKAXE).sound(SoundType.GLASS).noCollission().strength(0.7F).lightLevel(blockState -> 14)));
    public static final RegistryObject<Block> DIAMOND_POWERED_RAIL = BLOCKS.register("diamond_powered_rail", () -> new VPoweredRailBlock(0.75f, Block.Properties.of(Material.DECORATION).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL).noCollission().strength(0.7F)));
    public static final RegistryObject<Block> NETHERITE_POWERED_RAIL = BLOCKS.register("netherite_powered_rail", () -> new VPoweredRailBlock(1f, Block.Properties.of(Material.DECORATION).harvestTool(ToolType.PICKAXE).sound(SoundType.NETHERITE_BLOCK).noCollission().strength(0.7F)));
    public static final RegistryObject<Block> VOID_POWERED_RAIL = BLOCKS.register("void_powered_rail", () -> new VPoweredRailBlock(2.5f, Block.Properties.of(Material.DECORATION).harvestTool(ToolType.PICKAXE).sound(SoundType.NETHERITE_BLOCK).noCollission().strength(0.7F).lightLevel(blockState -> 9)));

    // Void Equipment
    public static final RegistryObject<Block> VOID_ORE = BLOCKS.register("void_ore", () -> new VoidOre(AbstractBlock.Properties.of(Material.METAL).sound(SoundType.ANCIENT_DEBRIS).harvestTool(ToolType.PICKAXE).harvestLevel(4).requiresCorrectToolForDrops().strength(50.0F, 1200.0F).lightLevel(blockState -> 13)));
    public static final RegistryObject<Block> VOID_BLOCK = BLOCKS.register("void_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).harvestTool(ToolType.PICKAXE).harvestLevel(4).requiresCorrectToolForDrops().strength(50.0F, 1200.0F).lightLevel(blockState -> 9).noOcclusion().isViewBlocking(NEVER)));

    public static RegistryObject<SlabBlock> registerSlab(String name, Supplier<Block> block) {
        return registerCustomSlab(name, () -> AbstractBlock.Properties.copy(block.get()));
    }

    public static RegistryObject<SlabBlock> registerCustomSlab(String name, Supplier<AbstractBlock.Properties> properties) {
        return BLOCKS.register(name, () -> new SlabBlock(properties.get()));
    }

    public static RegistryObject<StairsBlock> registerStairs(String name, Supplier<Block> block) {
        return BLOCKS.register(name, () -> {
            Block get = block.get();
            return new StairsBlock(get::defaultBlockState, AbstractBlock.Properties.copy(get));
        });
    }
}