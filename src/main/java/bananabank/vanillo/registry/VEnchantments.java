package rusty.vanillo.registry;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rusty.vanillo.Vanillo;
import rusty.vanillo.enchantment.AbyssalImpulseEnchantment;
import rusty.vanillo.enchantment.BanishmentEnchantment;
import rusty.vanillo.enchantment.PunisherEnchantment;

public final class VEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Vanillo.ID);

    //public static final RegistryObject<Enchantment> AUTO_SMELT = ENCHANTMENTS.register("auto_smelt", AutoSmeltEnchantment::new);
    public static final RegistryObject<Enchantment> BANISHMENT = ENCHANTMENTS.register("banishment", BanishmentEnchantment::new);
    public static final RegistryObject<Enchantment> ABYSSAL_IMPULSE = ENCHANTMENTS.register("abyssal_impulse", AbyssalImpulseEnchantment::new);
    public static final RegistryObject<Enchantment> PUNISHER = ENCHANTMENTS.register("punisher", PunisherEnchantment::new);
}
