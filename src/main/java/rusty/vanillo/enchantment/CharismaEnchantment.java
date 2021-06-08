package rusty.vanillo.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

// todo figure this out
public final class CharismaEnchantment extends Enchantment {
    private static final EquipmentSlotType[] ARMOR_SLOTS = new EquipmentSlotType[]{EquipmentSlotType.FEET, EquipmentSlotType.LEGS, EquipmentSlotType.CHEST, EquipmentSlotType.HEAD};

    public CharismaEnchantment() {
        super(Rarity.RARE, EnchantmentType.ARMOR, ARMOR_SLOTS);
    }
}
