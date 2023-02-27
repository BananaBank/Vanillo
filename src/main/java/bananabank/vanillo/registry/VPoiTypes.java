package rusty.vanillo.registry;

import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rusty.vanillo.Vanillo;

public class VPoiTypes {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, Vanillo.ID);

    public static final RegistryObject<PoiType> CONDUCTOR = POI_TYPES.register("conductor", () -> new PoiType("conductor", PoiType.getBlockStates(Blocks.CRAFTING_TABLE), 1, 1));
}
