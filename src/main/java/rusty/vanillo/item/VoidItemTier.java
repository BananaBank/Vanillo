package rusty.vanillo.item;

import com.google.common.base.Suppliers;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import rusty.vanillo.registry.VItems;

import java.util.function.Supplier;

/**
 * Uses a similar singleton pattern like in {@link VoidArmorMaterial}.
 *
 * @author TheDarkColour
 */
public enum VoidItemTier implements Tier {
    INSTANCE; // Singleton instance

    /**
     * Lazy values are loaded later so that the item is guaranteed to be registered by that time
     * This Lazy value contains the ingredient used to repair Void items in an Anvil
     */
    public static final Supplier<Ingredient> REPAIR_COST = Suppliers.memoize(() -> Ingredient.of(VItems.VOID_SHARD.get()));

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
