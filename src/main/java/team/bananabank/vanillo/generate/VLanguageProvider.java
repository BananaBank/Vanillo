package team.bananabank.vanillo.generate;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.common.data.LanguageProvider;
import team.bananabank.vanillo.Vanillo;
import team.bananabank.vanillo.registry.VBlocks;
import team.bananabank.vanillo.registry.VEnchantments;
import team.bananabank.vanillo.registry.VItems;

import java.util.function.Supplier;

/**
 * @author TheDarkColour, cfrishausen, DJRoundTable
 */
public class VLanguageProvider extends LanguageProvider {
    public VLanguageProvider(PackOutput output) {
        super(output, Vanillo.ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.vanillo", "Vanillo");
        add("gui.vanillo.category.recycling", "Recycling"); // jei

        // Extra slabs
        addItem(VItems.DIRT_SLAB, "Dirt Slab");
        addItem(VItems.COARSE_DIRT_SLAB, "Coarse Dirt Slab");

        // Rails
        addItem(VItems.WOODEN_RAIL, "Wooden Rail");
        addItem(VItems.GLOWSTONE_RAIL, "Glowstone Rail");
        addItem(VItems.DIAMOND_POWERED_RAIL, "Diamond Powered Rail");
        addItem(VItems.NETHERITE_POWERED_RAIL, "Netherite Powered Rail");
        addItem(VItems.VOID_POWERED_RAIL, "Void Rift Powered Rail");

        // Void Equipment
        addItem(VItems.VOID_CRYSTAL, "Void Crystal");
        addItem(VItems.VOID_SHARD, "Void Shard");
        //addItem(VItems.VOID_ESSENCE, "Void Essence");
        addItem(VItems.VOID_ORE, "Remnants of the Rift");
        addItem(VItems.VOID_BLOCK, "Void Crystal Block");
        addItem(VItems.VOID_BOOTS, "Fallen Treads of the Void");
        addItem(VItems.VOID_LEGGINGS, "Void Greaves");
        addItem(VItems.VOID_CHESTPLATE, "Void Bulwark");
        addItem(VItems.VOID_HELMET, "Crown of the Void");
        addItem(VItems.VOID_BOW, "Bow of the Void");
        addItem(VItems.VOID_SWORD, "Blade of the Void");
        addItem(VItems.VOID_SHOVEL, "Spade of the Void");
        addItem(VItems.VOID_PICKAXE, "Carver of the Void");
        addItem(VItems.VOID_AXE, "Void Rift Hatchet");
        addItem(VItems.VOID_HOE, "Harvest of the Void");

        // Brick Bricks + Stone Brick Bricks
        addBlock(VBlocks.BRICK_BRICKS, "Brick Bricks");
        addBlock(VBlocks.BRICK_BRICK_SLAB, "Brick Brick Slab");
        addBlock(VBlocks.BRICK_BRICK_STAIRS, "Brick Brick Stairs");
        addBlock(VBlocks.STONE_BRICK_BRICKS, "Stone Brick Bricks");
        addBlock(VBlocks.STONE_BRICK_BRICK_SLAB, "Stone Brick Brick Slab");
        addBlock(VBlocks.STONE_BRICK_BRICK_STAIRS, "Stone Brick Brick Stairs");

        // :smirk:
        addItem(VItems.ENDER_OMELETTE, "Ender Omelette");

        // Enchantments
        //addEnchantment(VEnchantments.AUTO_SMELT, "Auto Smelt");
        addEnchantment(VEnchantments.BANISHMENT, "Banishment", "Finishes off wounded targets");
        addEnchantment(VEnchantments.ABYSSAL_IMPULSE, "Abyssal Impulse", "Has a chance to debuff targets");
        addEnchantment(VEnchantments.PUNISHER, "Punisher", "Deals more damage to targets at full health");

        // Recycler
        addItem(VItems.RECYCLER, "Recycler");

        // Professions
        add("entity.minecraft.villager.vanillo.conductor", "Conductor");

        // Container Title
        add("container.recycler", "Recycler");
    }

    public void addEnchantment(Supplier<? extends Enchantment> enchantment, String name, String description) {
        addEnchantment(enchantment, name);
        add(enchantment.get().getDescriptionId() + ".desc", description);
    }
}