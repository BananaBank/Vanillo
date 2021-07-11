package rusty.vanillo.registry;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rusty.vanillo.Vanillo;
import rusty.vanillo.entities.BanishmentLightningEntity;

public class VEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Vanillo.ID);

    public static final RegistryObject<EntityType<BanishmentLightningEntity>> BANISHMENT_LIGHTNING = ENTITY_TYPES.register("banishment_lightning", () -> EntityType.Builder.of(BanishmentLightningEntity::new, EntityClassification.MISC).clientTrackingRange(16).updateInterval(Integer.MAX_VALUE).build("vanillo:banishment_lightning"));
}
