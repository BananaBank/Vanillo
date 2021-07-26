package rusty.vanillo.feature;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import rusty.vanillo.Vanillo;
import rusty.vanillo.registry.VBlocks;

public class VConfiguredFeatures {
    public static final RuleTest END_STONE = new BlockMatchTest(Blocks.END_STONE);
    public static final ConfiguredFeature<?, ?> VOID_ORE_FEATURE = Feature.ORE.configured(new OreConfiguration(END_STONE, VBlocks.VOID_ORE.get().defaultBlockState(), 3)).rangeTriangle(VerticalAnchor.absolute(8), VerticalAnchor.absolute(24)).count(5).squared();

    public static void registerConfiguredFeatures() {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Vanillo.ID, "void_ore_feature"), VConfiguredFeatures.VOID_ORE_FEATURE);
    }
}
