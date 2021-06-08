package rusty.vanillo.registry;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rusty.vanillo.Vanillo;

/**
 * @author DJRoundTable
 */
public final class VTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Vanillo.ID);
    //public static final RegistryObject<TileEntityType<?>> RECYCLER_ENTITY = TILE_ENTITIES.register("recycler_entity", () -> new TileEntity());
}
