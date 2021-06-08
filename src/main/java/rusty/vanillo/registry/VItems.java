package rusty.vanillo.registry;

import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rusty.vanillo.Vanillo;
import rusty.vanillo.item.VTab;
import rusty.vanillo.item.VoidArmorMaterial;
import rusty.vanillo.item.VoidItemTier;

import java.util.function.Function;

/**
 * @author TheDarkColour, cfrishausen, DJRoundTable
 */
public final class VItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Vanillo.ID);

    // Extra slab blocks
    public static final RegistryObject<Item> DIRT_SLAB = registerBlockItem(VBlocks.DIRT_SLAB);
    public static final RegistryObject<Item> COARSE_DIRT_SLAB = registerBlockItem(VBlocks.COARSE_DIRT_SLAB);

    // Rails
    public static final RegistryObject<Item> WOODEN_RAIL = registerBlockItem(VBlocks.WOODEN_RAIL);
    public static final RegistryObject<Item> GLOWSTONE_RAIL = registerBlockItem(VBlocks.GLOWSTONE_RAIL);
    public static final RegistryObject<Item> DIAMOND_POWERED_RAIL = registerBlockItem(VBlocks.DIAMOND_POWERED_RAIL);
    public static final RegistryObject<Item> NETHERITE_POWERED_RAIL = registerBlockItem(VBlocks.NETHERITE_POWERED_RAIL);

    // Food Items
    public static final RegistryObject<Item> ENDER_OMELETTE = registerFoodItem("ender_omelette", 20, 1.5f);

    // Void Equipment
    public static final RegistryObject<Item> VOID_ESSENCE = registerSimpleItem("void_essence", properties -> properties.fireResistant().rarity(Rarity.RARE));
    public static final RegistryObject<Item> VOID_SHARD = registerSimpleItem("void_shard", properties -> properties.fireResistant().rarity(Rarity.RARE));
    public static final RegistryObject<Item> VOID_CRYSTAL = registerSimpleItem("void_crystal", properties -> properties.fireResistant().rarity(Rarity.RARE));
    public static final RegistryObject<Item> VOID_ORE = registerVoidBlockItem(VBlocks.VOID_ORE);
    public static final RegistryObject<Item> VOID_BLOCK = registerVoidBlockItem(VBlocks.VOID_BLOCK);
    public static final RegistryObject<Item> VOID_BOOTS = registerVoidArmorItem("void_boots", EquipmentSlotType.FEET);
    public static final RegistryObject<Item> VOID_LEGGINGS = registerVoidArmorItem("void_leggings", EquipmentSlotType.LEGS);
    public static final RegistryObject<Item> VOID_CHESTPLATE = registerVoidArmorItem("void_chestplate", EquipmentSlotType.CHEST);
    public static final RegistryObject<Item> VOID_HELMET = registerVoidArmorItem("void_helmet", EquipmentSlotType.HEAD);
    public static final RegistryObject<Item> VOID_SWORD = ITEMS.register("void_sword", () -> new SwordItem(VoidItemTier.INSTANCE, 3, -2.4F, (new Item.Properties()).fireResistant().rarity(Rarity.RARE).tab(VTab.INSTANCE)));
    public static final RegistryObject<Item> VOID_SHOVEL = ITEMS.register("void_shovel", () -> new ShovelItem(VoidItemTier.INSTANCE, 1.5F, -3.0F, (new Item.Properties()).fireResistant().rarity(Rarity.RARE).tab(VTab.INSTANCE)));
    public static final RegistryObject<Item> VOID_PICKAXE = ITEMS.register("void_pickaxe", () -> new PickaxeItem(VoidItemTier.INSTANCE, 1, -2.8F, (new Item.Properties()).fireResistant().rarity(Rarity.RARE).tab(VTab.INSTANCE)));
    public static final RegistryObject<Item> VOID_AXE = ITEMS.register("void_axe", () -> new AxeItem(VoidItemTier.INSTANCE, 5.0F, -3.0F, (new Item.Properties()).fireResistant().rarity(Rarity.RARE).tab(VTab.INSTANCE)));
    public static final RegistryObject<Item> VOID_HOE = ITEMS.register("void_hoe", () -> new HoeItem(VoidItemTier.INSTANCE, -5, 0.0F, (new Item.Properties()).fireResistant().rarity(Rarity.RARE).tab(VTab.INSTANCE)));

    // Make a BlockItem easily and adds it to the creative tab automatically
    public static RegistryObject<Item> registerBlockItem(RegistryObject<? extends Block> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(VTab.INSTANCE)));
    }

    public static RegistryObject<Item> registerVoidBlockItem(RegistryObject<Block> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(VTab.INSTANCE).fireResistant().rarity(Rarity.RARE)));
    }

    // Register a simple item that doesn't do anything (like crafting materials)
    public static RegistryObject<Item> registerSimpleItem(String name, Function<Item.Properties, Item.Properties> applyProperties) {
        Item.Properties properties = applyProperties.apply(new Item.Properties().tab(VTab.INSTANCE));

        return ITEMS.register(name, () -> new Item(properties));
    }

    public static RegistryObject<Item> registerVoidArmorItem(String name, EquipmentSlotType slot) {
        return ITEMS.register(name, () -> new ArmorItem(VoidArmorMaterial.INSTANCE, slot, new Item.Properties().rarity(Rarity.RARE).tab(VTab.INSTANCE)));
    }

    public static RegistryObject<Item> registerFoodItem(String name, int a, float sat) {
        return ITEMS.register(name, () -> new Item(new Item.Properties().food(new Food.Builder().nutrition(a).saturationMod(sat).build())));
    }
}
