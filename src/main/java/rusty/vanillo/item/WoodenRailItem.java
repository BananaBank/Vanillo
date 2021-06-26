package rusty.vanillo.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

/**
 * Just here for burn time
 */
public class WoodenRailItem extends BlockItem {
    public WoodenRailItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return 100;
    }
}
