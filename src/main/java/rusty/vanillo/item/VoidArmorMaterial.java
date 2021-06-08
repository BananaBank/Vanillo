package rusty.vanillo.item;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import rusty.vanillo.Vanillo;

/**
 * Class that holds all the armor properties of Void armor.
 * Because these properties are defined through an interface,
 * we use a singleton enum rather than static methods.
 *
 * A singleton is basically a class with only one instance.
 * It's like static but with the benefit of being able to implement an interface.
 *
 * @author TheDarkColour
 */
public enum VoidArmorMaterial implements IArmorMaterial {
    INSTANCE; // Single enum value that acts as the only instance of this class (singleton)

    // Durability for Boots, Leggings, Chestplate, and Helmet in that order
    private static final int[] DURABILITY = new int[]{559, 645, 688, 473};
    // Same protection as Diamond and Netherite
    private static final int[] PROTECTION = new int[]{3, 6, 8, 3};

    @Override
    public int getDurabilityForSlot(EquipmentSlotType slotType) {
        return DURABILITY[slotType.getIndex()];
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType slotType) {
        return PROTECTION[slotType.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return 20; // Slightly higher than Netherite but lower than Gold
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_NETHERITE; // Use Netherite sound unless you'd like to make your own
    }

    @Override
    public Ingredient getRepairIngredient() {
        return VoidItemTier.REPAIR_COST.get(); // Used the same in ItemTier as it is here
    }

    @Override
    public String getName() {
        return Vanillo.ID + ":void"; // Use 'vanillo:void' as the name of our armor so it uses textures from our mod's assets folder
    }

    @Override
    public float getToughness() {
        return 3.5f; // Higher than Netherite
    }

    @Override
    public float getKnockbackResistance() {
        return 0.15f; // Slightly higher than Netherite
    }
}
