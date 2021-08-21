package rusty.vanillo.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fmllegacy.RegistryObject;
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

    private static final BlockBehaviour.StatePredicate NEVER = (s, e, x) -> false;

    // Extra slab blocks
    public static final RegistryObject<SlabBlock> DIRT_SLAB = registerCustomSlab("dirt_slab", () -> BlockBehaviour.Properties.copy(Blocks.DIRT));
    public static final RegistryObject<SlabBlock> COARSE_DIRT_SLAB = registerCustomSlab("coarse_dirt_slab", () -> BlockBehaviour.Properties.copy(Blocks.COARSE_DIRT));

    // Stone Brick Bricks + Brick Bricks
    public static final RegistryObject<Block> BRICK_BRICKS = BLOCKS.register("brick_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistryObject<SlabBlock> BRICK_BRICK_SLAB = registerSlab("brick_brick_slab", BRICK_BRICKS);
    public static final RegistryObject<StairBlock> BRICK_BRICK_STAIRS = registerStairs("brick_brick_stairs", BRICK_BRICKS);
    public static final RegistryObject<Block> STONE_BRICK_BRICKS = BLOCKS.register("stone_brick_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
    public static final RegistryObject<SlabBlock> STONE_BRICK_BRICK_SLAB = registerSlab("stone_brick_brick_slab", STONE_BRICK_BRICKS);
    public static final RegistryObject<StairBlock> STONE_BRICK_BRICK_STAIRS = registerStairs("stone_brick_brick_stairs", STONE_BRICK_BRICKS);

    // Recycler
    public static final RegistryObject<Block> RECYCLER = BLOCKS.register("recycler", () -> new RecyclerBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).sound(SoundType.ANVIL).strength(5.0f).requiresCorrectToolForDrops().lightLevel(state -> state.getValue(BlockStateProperties.LIT) ? 13 : 0)));

    // Rails
    public static final RegistryObject<Block> WOODEN_RAIL = BLOCKS.register("wooden_rail", () -> new VRailBlock(0.1f, BlockBehaviour.Properties.of(Material.DECORATION).sound(SoundType.LADDER).noCollission().strength(0.7F)));
    public static final RegistryObject<Block> GLOWSTONE_RAIL = BLOCKS.register("glowstone_rail", () -> new VRailBlock(0.6f, BlockBehaviour.Properties.of(Material.DECORATION).sound(SoundType.GLASS).noCollission().strength(0.7F).lightLevel(blockState -> 14)));
    public static final RegistryObject<Block> DIAMOND_POWERED_RAIL = BLOCKS.register("diamond_powered_rail", () -> new VPoweredRailBlock(0.75f, BlockBehaviour.Properties.of(Material.DECORATION).sound(SoundType.METAL).noCollission().strength(0.7F)));
    public static final RegistryObject<Block> NETHERITE_POWERED_RAIL = BLOCKS.register("netherite_powered_rail", () -> new VPoweredRailBlock(1f, BlockBehaviour.Properties.of(Material.DECORATION).sound(SoundType.NETHERITE_BLOCK).noCollission().strength(0.7F)));
    public static final RegistryObject<Block> VOID_POWERED_RAIL = BLOCKS.register("void_powered_rail", () -> new VPoweredRailBlock(2.5f, BlockBehaviour.Properties.of(Material.DECORATION).sound(SoundType.NETHERITE_BLOCK).noCollission().strength(0.7F).lightLevel(blockState -> 9)));

    // Void Equipment
    public static final RegistryObject<Block> VOID_ORE = BLOCKS.register("void_ore", () -> new VoidOre(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.ANCIENT_DEBRIS).requiresCorrectToolForDrops().strength(50.0F, 1200.0F).lightLevel(blockState -> 13)));
    public static final RegistryObject<Block> VOID_BLOCK = BLOCKS.register("void_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).requiresCorrectToolForDrops().strength(50.0F, 1200.0F).lightLevel(blockState -> 9).noOcclusion().isViewBlocking(NEVER)));

    public static RegistryObject<SlabBlock> registerSlab(String name, Supplier<Block> block) {
        return registerCustomSlab(name, () -> BlockBehaviour.Properties.copy(block.get()));
    }

    public static RegistryObject<SlabBlock> registerCustomSlab(String name, Supplier<BlockBehaviour.Properties> properties) {
        return BLOCKS.register(name, () -> new SlabBlock(properties.get()));
    }

    public static RegistryObject<StairBlock> registerStairs(String name, Supplier<Block> block) {
        return BLOCKS.register(name, () -> {
            Block get = block.get();
            return new StairBlock(get::defaultBlockState, BlockBehaviour.Properties.copy(get));
        });
    }
}