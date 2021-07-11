package rusty.vanillo.feature;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.event.world.BiomeLoadingEvent;


public class VFeatures {
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.Category.THEEND) {
            event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, VConfiguredFeatures.VOID_ORE_FEATURE);
        }
    }
}