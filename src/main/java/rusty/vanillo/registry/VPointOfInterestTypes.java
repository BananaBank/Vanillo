package rusty.vanillo.registry;

import net.minecraft.block.Blocks;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rusty.vanillo.Vanillo;

public class VPointOfInterestTypes {
    public static final DeferredRegister<PointOfInterestType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, Vanillo.ID);

    public static final RegistryObject<PointOfInterestType> CONDUCTOR = POI_TYPES.register("conductor", () -> new PointOfInterestType("conductor", PointOfInterestType.getBlockStates(Blocks.CRAFTING_TABLE), 1, 1));
}
