package rusty.vanillo.enchantment;

import net.minecraft.enchantment.EnchantmentType;
import rusty.vanillo.registry.VItems;

public class VEnchantmentTypes {
    public static final EnchantmentType VOID_BOW = EnchantmentType.create("VOID_BOW_VANILLO", item -> item == VItems.VOID_BOW.get());
    public static final EnchantmentType VOID_SWORD = EnchantmentType.create("VOID_SWORD_VANILLO", item -> item == VItems.VOID_SWORD.get());

    // Any tool (including sword)
    public static final EnchantmentType VOID_TOOLS = EnchantmentType.create("VOID_TOOLS_VANILLO", item -> {
        return item == VItems.VOID_SWORD.get() || item == VItems.VOID_SHOVEL.get() || item == VItems.VOID_PICKAXE.get() || item == VItems.VOID_AXE.get() || item == VItems.VOID_HOE.get();
    });
}
