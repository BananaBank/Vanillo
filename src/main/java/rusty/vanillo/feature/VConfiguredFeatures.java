package rusty.vanillo.feature;

import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.Placement;
import rusty.vanillo.Vanillo;
import rusty.vanillo.registry.VBlocks;

public class VConfiguredFeatures {
    public static final RuleTest END_STONE = new BlockMatchRuleTest(Blocks.END_STONE);
    public static final ConfiguredFeature<?, ?> VOID_ORE_FEATURE = Feature.ORE.configured(new OreFeatureConfig(END_STONE, VBlocks.VOID_ORE.get().defaultBlockState(), 3)).decorated(Placement.DEPTH_AVERAGE.configured(new DepthAverageConfig(16, 8))).count(5).squared();

    public static void registerConfiguredFeatures() {
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(Vanillo.ID, "void_ore_feature"), VConfiguredFeatures.VOID_ORE_FEATURE);
    }
}
