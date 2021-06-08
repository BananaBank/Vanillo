package rusty.vanillo.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import rusty.vanillo.Vanillo;
import rusty.vanillo.registry.VItems;

/**
 * Here we do not use an enum for the singleton because
 * enums cannot extend other classes.
 *
 * @author TheDarkColour
 */
public final class VTab extends ItemGroup {
    public static final VTab INSTANCE = new VTab(); // Singleton instance

    // Private constructor to ensure no other instances are created
    private VTab() {
        super(Vanillo.ID);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(VItems.WOODEN_RAIL.get()); // Item icon of creative tab is a wooden rail
    }
}
