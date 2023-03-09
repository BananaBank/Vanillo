package team.bananabank.vanillo.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import team.bananabank.vanillo.Vanillo;

import java.util.List;

public class VFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registries.CONFIGURED_FEATURE, Vanillo.ID);
    public static final RegistryObject<ConfiguredFeature<?, ?>> VOID_ORE = CONFIGURED_FEATURES.register("void_ore", () -> {
        return new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(new BlockMatchTest(Blocks.END_STONE), VBlocks.VOID_ORE.get().defaultBlockState(), 3));
    });

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registries.PLACED_FEATURE, Vanillo.ID);
    public static final RegistryObject<PlacedFeature> SPARSE_VOID_ORE = PLACED_FEATURES.register("sparse_void_ore", () -> {
        // .rangeTriangle(VerticalAnchor.absolute(8), VerticalAnchor.absolute(24)).count(5).squared()
        return new PlacedFeature(VOID_ORE.getHolder().get(), List.of());
    });
}
