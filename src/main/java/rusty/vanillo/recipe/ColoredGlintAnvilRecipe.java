package rusty.vanillo.recipe;

import it.unimi.dsi.fastutil.objects.Object2ByteLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ByteMap;
import net.minecraft.Util;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.AnvilUpdateEvent;

public class ColoredGlintAnvilRecipe {
    private static final Object2ByteMap<Tag<Item>> TAG_COLOR_2_INDEX = Util.make(new Object2ByteLinkedOpenHashMap<>(), map -> {
        map.put(Tags.Items.DYES_WHITE, (byte) 0);
        map.put(Tags.Items.DYES_ORANGE, (byte) 1);
        map.put(Tags.Items.DYES_MAGENTA, (byte) 2);
        map.put(Tags.Items.DYES_LIGHT_BLUE, (byte) 3);
        map.put(Tags.Items.DYES_YELLOW, (byte) 4);
        map.put(Tags.Items.DYES_LIME, (byte) 5);
        map.put(Tags.Items.DYES_PINK, (byte) 6);
        map.put(Tags.Items.DYES_GRAY, (byte) 7);
        map.put(Tags.Items.DYES_LIGHT_GRAY, (byte) 8);
        map.put(Tags.Items.DYES_CYAN, (byte) 9);
        map.put(Tags.Items.DYES_PURPLE, (byte) 10);
        map.put(Tags.Items.DYES_BLUE, (byte) 11);
        map.put(Tags.Items.DYES_BROWN, (byte) 12);
        map.put(Tags.Items.DYES_GREEN, (byte) 13);
        map.put(Tags.Items.DYES_RED, (byte) 14);
        map.put(Tags.Items.DYES_BLACK, (byte) 15);
        map.put(Tags.Items.GEMS_DIAMOND, (byte) 16);
    });

    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();

        if (event.getPlayer().level.isClientSide) {
            return;
        }

        if (left.hasFoil() && (right.is(Tags.Items.DYES) || right.is(Tags.Items.GEMS_DIAMOND))) {
            // todo replace with switch expression in 1.17
            for (Object2ByteMap.Entry<Tag<Item>> tag : TAG_COLOR_2_INDEX.object2ByteEntrySet()) {
                if (right.is(tag.getKey())) {
                    ItemStack result = left.copy();
                    result.getOrCreateTag().putByte("Glint", tag.getByteValue());
                    event.setOutput(result);
                    event.setCost(3);
                    event.setMaterialCost(1);
                    return;
                }
            }
        }
    }
}
