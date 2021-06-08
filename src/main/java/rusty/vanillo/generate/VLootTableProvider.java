package rusty.vanillo.generate;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTableManager;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.conditions.SurvivesExplosion;
import net.minecraft.loot.functions.ExplosionDecay;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rusty.vanillo.registry.VBlocks;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class VLootTableProvider extends LootTableProvider {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private static final Logger LOGGER = LogManager.getLogger();
    private final DataGenerator generator;
    private final Map<Block, LootTable.Builder> blockTables = Maps.newHashMap();

    public VLootTableProvider(DataGenerator generator) {
        super(generator);
        this.generator = generator;
    }

    @Override
    public void run(DirectoryCache directoryCache) {
        addBlockLootTables();

        HashMap<ResourceLocation, LootTable> namespacedTables = new HashMap<>(blockTables.size());

        for (Map.Entry<Block, LootTable.Builder> entry : blockTables.entrySet()) {
            // Add tables to the block loot parameter set automatically
            namespacedTables.put(entry.getKey().getLootTable(), entry.getValue().setParamSet(LootParameterSets.BLOCK).build());
        }

        writeLootTables(namespacedTables, directoryCache);
    }

    // Add block loot tables
    private void addBlockLootTables() {
        // void equipment
        dropSelf(VBlocks.VOID_ORE);
        dropSelf(VBlocks.VOID_BLOCK);

        // rails
        dropSelf(VBlocks.WOODEN_RAIL);
        dropSelf(VBlocks.GLOWSTONE_RAIL);
        dropSelf(VBlocks.DIAMOND_POWERED_RAIL);
        dropSelf(VBlocks.NETHERITE_POWERED_RAIL);

        // extra slabs
        slabLootTable(VBlocks.DIRT_SLAB);
        slabLootTable(VBlocks.COARSE_DIRT_SLAB);
    }

    // Loot table to drop the block itself. Think Diamond Block, Sand, etc.
    private void dropSelf(RegistryObject<Block> block) {
        // refer to the diamond block loot table for this one
        addLoot(block, new LootTable.Builder().withPool(new LootPool.Builder().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(block.get())).when(SurvivesExplosion.survivesExplosion())));
    }

    // Loot table to drop slabs properly. Think any kind of slab block
    private void slabLootTable(RegistryObject<SlabBlock> slab) {
        // refer to the oak slab loot table for this one
        addLoot(slab, new LootTable.Builder().withPool(
                new LootPool.Builder()
                        .setRolls(ConstantRange.exactly(1)) // rolls
                        .add(ItemLootEntry.lootTableItem(slab.get()) // entries
                                .apply(SetCount.setCount(ConstantRange.exactly(2)) // each call to apply adds function to functions
                                        .when(BlockStateProperty.hasBlockStateProperties(slab.get()) // "when" is a condition
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlockStateProperties.SLAB_TYPE, SlabType.DOUBLE)) // type double
                                        ))
                                .apply(ExplosionDecay.explosionDecay())
                        )));
    }

    private void addLoot(RegistryObject<? extends Block> block, LootTable.Builder builder) {
        blockTables.put(block.get(), builder);
    }

    // Saves the loot tables to files
    private void writeLootTables(Map<ResourceLocation, LootTable> tables, DirectoryCache cache) {
        Path outputFolder = generator.getOutputFolder();

        for (Map.Entry<ResourceLocation, LootTable> entry : tables.entrySet()) {
            Path path = outputFolder.resolve("data/" + entry.getKey().getNamespace() + "/loot_tables/" + entry.getKey().getPath() + ".json");

            try {
                IDataProvider.save(GSON, cache, LootTableManager.serialize(entry.getValue()), path);
            } catch (Exception e) {
                LOGGER.error("Couldn't write loot table {}", path, e);
            }
        }
    }
}
