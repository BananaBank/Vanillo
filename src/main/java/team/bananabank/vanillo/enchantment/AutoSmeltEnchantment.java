package bananabank.vanillo.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.event.LootTableLoadEvent;

public final class AutoSmeltEnchantment extends Enchantment {
    public AutoSmeltEnchantment() {
        super(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[] { EquipmentSlot.MAINHAND });
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

    public boolean checkCompatibility(Enchantment enchantment) {
        return super.checkCompatibility(enchantment) && enchantment != Enchantments.BLOCK_FORTUNE && enchantment != Enchantments.SILK_TOUCH;
    }

    public static void modifyLoot(LootTableLoadEvent event) {
        LootTable table = event.getTable();

        //if (table.getParamSet() == LootParameterSets.BLOCK) {
            //table.
       // }
    }
}
