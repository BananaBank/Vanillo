package rusty.vanillo.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import rusty.vanillo.registry.VItems;

/**
 * Uses a similar singleton pattern like in {@link VoidArmorMaterial}.
 *
 * @author TheDarkColour
 */
public enum VoidItemTier implements IItemTier {
    INSTANCE; // Singleton instance

    /**
     * Lazy values are loaded later so that the item is guaranteed to be registered by that time
     * This Lazy value contains the ingredient used to repair Void items in an Anvil
     */
    public static final LazyValue<Ingredient> REPAIR_COST = new LazyValue(() -> Ingredient.of(VItems.VOID_SHARD.get()));

    @Override
    public int getUses() {
        return 2468; // Higher than Netherite
    }

    @Override
    public float getSpeed() {
        return 10.5f; // Faster than Netherite, slower than Gold
    }

    @Override
    public float getAttackDamageBonus() {
        return 5.0f; // Higher than Netherite
    }

    @Override
    public int getLevel() {
        return 5; // Higher than Netherite
    }

    @Override
    public int getEnchantmentValue() {
        return 18; // Higher than Netherite, lower than Gold
    }

    @Override
    public Ingredient getRepairIngredient() {
        return REPAIR_COST.get(); // Get the stored value of the LazyValue
    }
}
