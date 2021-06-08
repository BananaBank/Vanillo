package rusty.vanillo.generate;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

/**
 * @author cfrishausen
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VGeneration {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        gen.addProvider(new VBlockStatesProvider(gen, helper));
        gen.addProvider(new VItemModelProvider(gen, helper));
        gen.addProvider(new VLanguageProvider(gen));
        gen.addProvider(new VRecipeProvider(gen));
        gen.addProvider(new VLootTableProvider(gen));


        BlockTagsProvider blockTags = new VBlockTagsProvider(gen, helper);
        gen.addProvider(blockTags);
        gen.addProvider(new VItemTagsProvider(gen, blockTags, helper));
    }
}
