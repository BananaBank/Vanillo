package team.bananabank.vanillo.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class PunisherEnchantment extends Enchantment {
    public PunisherEnchantment() {
        super(Enchantment.Rarity.VERY_RARE, VEnchantmentTypes.VOID_SWORD, new EquipmentSlot[] { EquipmentSlot.MAINHAND});
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
