package team.bananabank.vanillo.generate;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author cfrishausen
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VGeneration {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        var output = gen.getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();
        var lookup = event.getLookupProvider();

        gen.addProvider(true, new VBlockStatesProvider(output, helper));
        gen.addProvider(true, new VItemModelProvider(output, helper));
        gen.addProvider(true, new VLanguageProvider(output));
        gen.addProvider(true, new VRecipeProvider(output));
        gen.addProvider(true, new VLootTableProvider(gen));


        BlockTagsProvider blockTags = new VBlockTagsProvider(output, lookup, helper);
        gen.addProvider(true, blockTags);
        gen.addProvider(true, new VItemTagsProvider(output, lookup, blockTags, helper));
    }
}
