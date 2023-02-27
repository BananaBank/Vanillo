package rusty.vanillo.feature;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.world.BiomeLoadingEvent;


public class VFeatures {
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.BiomeCategory.THEEND) {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, VConfiguredFeatures.VOID_ORE_FEATURE);
        }
    }
}