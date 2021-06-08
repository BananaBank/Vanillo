package rusty.vanillo.registry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rusty.vanillo.Vanillo;
import rusty.vanillo.block.RecyclerBlock;
import rusty.vanillo.block.VPoweredRailBlock;
import rusty.vanillo.block.VRailBlock;

public final class VBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Vanillo.ID);

    // Extra slab blocks
    public static final RegistryObject<SlabBlock> DIRT_SLAB = BLOCKS.register("dirt_slab", () -> new SlabBlock(AbstractBlock.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL)));
    public static final RegistryObject<SlabBlock> COARSE_DIRT_SLAB = BLOCKS.register("coarse_dirt_slab", () -> new SlabBlock(AbstractBlock.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL)));

    // Recycler
    public static final RegistryObject<Block> RECYCLER = BLOCKS.register("recycler", () -> new RecyclerBlock(Block.Properties.of(Material.HEAVY_METAL).sound(SoundType.ANVIL).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE)));

    // Rails
    public static final RegistryObject<Block> WOODEN_RAIL = BLOCKS.register("wooden_rail", () -> new VRailBlock(0.1f, Block.Properties.of(Material.DECORATION).sound(SoundType.LADDER).noCollission().strength(0.7F)));
    public static final RegistryObject<Block> GLOWSTONE_RAIL = BLOCKS.register("glowstone_rail", () -> new VRailBlock(0.4f, Block.Properties.of(Material.DECORATION).sound(SoundType.GLASS).noCollission().strength(0.7F).lightLevel(blockState -> 14)));
    public static final RegistryObject<Block> DIAMOND_POWERED_RAIL = BLOCKS.register("diamond_powered_rail", () -> new VPoweredRailBlock(0.75f, Block.Properties.of(Material.DECORATION).sound(SoundType.METAL).noCollission().strength(0.7F)));
    public static final RegistryObject<Block> NETHERITE_POWERED_RAIL = BLOCKS.register("netherite_powered_rail", () -> new VPoweredRailBlock(1.5f, Block.Properties.of(Material.DECORATION).sound(SoundType.NETHERITE_BLOCK).noCollission().strength(0.7F)));

    // Void Equipment
    public static final RegistryObject<Block> VOID_ORE = BLOCKS.register("void_ore", () -> new Block(AbstractBlock.Properties.of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).requiresCorrectToolForDrops().strength(50.0F, 1200.0F)));
    public static final RegistryObject<Block> VOID_BLOCK = BLOCKS.register("void_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).requiresCorrectToolForDrops().strength(50.0F, 1200.0F)));
}