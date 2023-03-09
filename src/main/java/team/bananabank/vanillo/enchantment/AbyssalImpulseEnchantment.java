package team.bananabank.vanillo.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class AbyssalImpulseEnchantment extends Enchantment {
    public AbyssalImpulseEnchantment() {
        super(Enchantment.Rarity.VERY_RARE, VEnchantmentTypes.VOID_BOW, new EquipmentSlot[] { EquipmentSlot.MAINHAND });
    }

    public int getMinCost(int level) {
        return 15;
    }

    public int getMaxCost(int level) {
        return super.getMinCost(level) + 50;
    }

    public int getMaxLevel() {
        return 1;
    }
}
