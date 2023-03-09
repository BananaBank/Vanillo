package team.bananabank.vanillo.generate;

import com.google.common.collect.Maps;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;
import team.bananabank.vanillo.registry.VBlocks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class VLootTableProvider extends LootTableProvider {
    private final Map<Block, LootTable.Builder> blockTables = Maps.newHashMap();
    private final DataGenerator generator;

    public VLootTableProvider(DataGenerator generator) {
        super(generator.getPackOutput(), Set.of(), List.of());
        this.generator = generator;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        addBlockLootTables();

        HashMap<ResourceLocation, LootTable> namespacedTables = new HashMap<>(blockTables.size());

        for (Map.Entry<Block, LootTable.Builder> entry : blockTables.entrySet()) {
            // Add tables to the block loot parameter set automatically
            namespacedTables.put(entry.getKey().getLootTable(), entry.getValue().setParamSet(LootContextParamSets.BLOCK).build());
        }

        final PackOutput output = generator.getPackOutput();
        final PackOutput.PathProvider pathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "loot_tables");

        return CompletableFuture.allOf(namespacedTables.entrySet().stream().map((entry) -> DataProvider.saveStable(cache, LootTables.serialize(entry.getValue()), pathProvider.json(entry.getKey()))).toArray(CompletableFuture[]::new));
    }

    // Add block loot tables
    private void addBlockLootTables() {
        // void equipment
        dropSelf(VBlocks.VOID_BLOCK);

        // recycler
        dropSelf(VBlocks.RECYCLER);

        // brick bricks + stone brick bricks stairs/blocks
        dropSelf(VBlocks.BRICK_BRICKS);
        dropSelf(VBlocks.BRICK_BRICK_STAIRS);
        dropSelf(VBlocks.STONE_BRICK_BRICKS);
        dropSelf(VBlocks.STONE_BRICK_BRICK_STAIRS);

        // rails
        dropSelf(VBlocks.WOODEN_RAIL);
        dropSelf(VBlocks.GLOWSTONE_RAIL);
        dropSelf(VBlocks.DIAMOND_POWERED_RAIL);
        dropSelf(VBlocks.NETHERITE_POWERED_RAIL);
        dropSelf(VBlocks.VOID_POWERED_RAIL);

        // extra slabs
        slabLootTable(VBlocks.DIRT_SLAB);
        slabLootTable(VBlocks.COARSE_DIRT_SLAB);
        slabLootTable(VBlocks.STONE_BRICK_BRICK_SLAB);
        slabLootTable(VBlocks.BRICK_BRICK_SLAB);
    }

    // Loot table to drop the block itself. Think Diamond Block, Sand, etc.
    private void dropSelf(RegistryObject<? extends Block> block) {
        // refer to the diamond block loot table for this one
        addLoot(block, new LootTable.Builder().withPool(new LootPool.Builder().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(block.get())).when(ExplosionCondition.survivesExplosion())));
    }

    // Loot table to drop slabs properly. Think any kind of slab block
    private void slabLootTable(RegistryObject<SlabBlock> slab) {
        // refer to the oak slab loot table for this one
        addLoot(slab, new LootTable.Builder().withPool(
                new LootPool.Builder()
                        .setRolls(ConstantValue.exactly(1)) // rolls
                        .add(LootItem.lootTableItem(slab.get()) // entries
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2)) // each call to apply adds function to functions
                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(slab.get()) // "when" is a condition
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlockStateProperties.SLAB_TYPE, SlabType.DOUBLE)) // type double
                                        ))
                                .apply(ApplyExplosionDecay.explosionDecay())
                        )));
    }

    private void addLoot(RegistryObject<? extends Block> block, LootTable.Builder builder) {
        blockTables.put(block.get(), builder);
    }
}
