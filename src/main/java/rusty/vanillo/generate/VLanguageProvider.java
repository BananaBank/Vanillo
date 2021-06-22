package rusty.vanillo.generate;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import rusty.vanillo.Vanillo;
import rusty.vanillo.registry.VItems;

public class VLanguageProvider extends LanguageProvider {
    public VLanguageProvider(DataGenerator gen) {
        super(gen, Vanillo.ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.vanillo", "Vanillo");
        add("gui.vanillo.category.recycling", "Recycling"); // jei

        // Extra slabs
        addItem(VItems.DIRT_SLAB, "Dirt Slab");
        addItem(VItems.COARSE_DIRT_SLAB, "Coarse Dirt Slab");
//        addItem(VItems.GRASS_SLAB, "Grass Slab");

        // Rails
        addItem(VItems.WOODEN_RAIL, "Wooden Rail");
        addItem(VItems.GLOWSTONE_RAIL, "Glowstone Rail");
        addItem(VItems.DIAMOND_POWERED_RAIL, "Diamond Powered Rail");
        addItem(VItems.NETHERITE_POWERED_RAIL, "Netherite Powered Rail");
        addItem(VItems.VOID_POWERED_RAIL, "Void Rift Powered Rail");

        // Void Equipment
        addItem(VItems.VOID_CRYSTAL, "Void Crystal");
        addItem(VItems.VOID_SHARD, "Void Shard");
        addItem(VItems.VOID_ESSENCE, "Void Essence");
        addItem(VItems.VOID_ORE, "Remnants of the Rift");
        addItem(VItems.VOID_BLOCK, "Void Crystal Block");
        addItem(VItems.VOID_BOOTS, "Fallen Treads of the Void");
        addItem(VItems.VOID_LEGGINGS, "Eternal Soul Greaves");
        addItem(VItems.VOID_CHESTPLATE, "Phantom Bulwark");
        addItem(VItems.VOID_HELMET, "Crown of the Rift");
        addItem(VItems.VOID_SWORD, "The Silenced Blade");
        addItem(VItems.VOID_SHOVEL, "Spade of the Abyss");
        addItem(VItems.VOID_PICKAXE, "Carver of Emptiness");
        addItem(VItems.VOID_AXE, "Dark Rift Hatchet");
        addItem(VItems.VOID_HOE, "Harvest of the Chasm");

        addItem(VItems.MYSTERY_CUBE, "Sticky Surprise");

        // Recycler
        addItem(VItems.RECYCLER, "Recycler");

        // Professions
        add("entity.minecraft.villager.vanillo.conductor", "Conductor");

        // Container Title
        add("container.recycler", "Recycler");
    }
}