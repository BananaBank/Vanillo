package rusty.vanillo.feature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import rusty.vanillo.Vanillo;
import rusty.vanillo.registry.VBlocks;


public class VFeatures {
    public static final RuleTest END_STONE = new BlockMatchRuleTest(Blocks.END_STONE);
    public static final Lazy<BlockState> VOID_ORE = Lazy.of(() -> VBlocks.VOID_ORE.get().defaultBlockState());
    public static final Lazy<ConfiguredFeature<?, ?>> VOID_ORE_FEATURE = Lazy.of(() -> {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(Vanillo.ID, "void_ore_feature"), Feature.NO_SURFACE_ORE.configured(new OreFeatureConfig(END_STONE, VOID_ORE.get(), 2)).decorated(Placement.DEPTH_AVERAGE.configured(new DepthAverageConfig(16, 8))).squared());
    });

    public static void onBiomeLoad(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.Category.THEEND) {
            event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, VOID_ORE_FEATURE.get());
        }
    }
}

