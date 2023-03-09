package team.bananabank.vanillo.registry;

import team.bananabank.vanillo.blockentity.RecyclerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import team.bananabank.vanillo.Vanillo;

/**
 * @author DJRoundTable
 */
public final class VBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Vanillo.ID);

    public static final RegistryObject<BlockEntityType<RecyclerBlockEntity>> RECYCLER_ENTITY = TILE_ENTITIES.register("recycler_entity", () -> BlockEntityType.Builder.of(RecyclerBlockEntity::new, VBlocks.RECYCLER.get()).build(null));
}
