package rusty.vanillo.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rusty.vanillo.Vanillo;
import rusty.vanillo.tileentity.RecyclerBlockEntity;

/**
 * @author DJRoundTable
 */
public final class VBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Vanillo.ID);

    public static final RegistryObject<BlockEntityType<RecyclerBlockEntity>> RECYCLER_ENTITY = TILE_ENTITIES.register("recycler_entity", () -> BlockEntityType.Builder.of(RecyclerBlockEntity::new, VBlocks.RECYCLER.get()).build(null));
}
