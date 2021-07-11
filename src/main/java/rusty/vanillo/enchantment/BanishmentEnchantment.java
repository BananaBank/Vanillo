package rusty.vanillo.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class BanishmentEnchantment extends Enchantment {
    public BanishmentEnchantment() {
        super(Enchantment.Rarity.VERY_RARE, VEnchantmentTypes.VOID_BOW, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND});
    }
    public int getMinCost(int p_77321_1_) {
        return 15;
    }

    public int getMaxCost(int p_223551_1_) {
        return super.getMinCost(p_223551_1_) + 50;
    }

    public int getMaxLevel() {
        return 3;
    }
}
