package team.bananabank.vanillo.enchantment;

import net.minecraft.world.item.enchantment.EnchantmentCategory;
import team.bananabank.vanillo.registry.VItems;

public class VEnchantmentTypes {
    public static final EnchantmentCategory VOID_BOW = EnchantmentCategory.create("VOID_BOW_VANILLO", item -> item == VItems.VOID_BOW.get());
    public static final EnchantmentCategory VOID_SWORD = EnchantmentCategory.create("VOID_SWORD_VANILLO", item -> item == VItems.VOID_SWORD.get());

    // Any tool (including sword)
    public static final EnchantmentCategory VOID_TOOLS = EnchantmentCategory.create("VOID_TOOLS_VANILLO", item -> {
        return item == VItems.VOID_SWORD.get() || item == VItems.VOID_SHOVEL.get() || item == VItems.VOID_PICKAXE.get() || item == VItems.VOID_AXE.get() || item == VItems.VOID_HOE.get();
    });
}
