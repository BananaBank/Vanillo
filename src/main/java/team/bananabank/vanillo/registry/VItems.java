package team.bananabank.vanillo.registry;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import team.bananabank.vanillo.Vanillo;
import team.bananabank.vanillo.item.*;

import java.util.function.Function;

/**
 * @author TheDarkColour, cfrishausen, DJRoundTable
 */
public final class VItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Vanillo.ID);

    // Extra slab blocks
    public static final RegistryObject<Item> DIRT_SLAB = registerBlockItem(VBlocks.DIRT_SLAB);
    public static final RegistryObject<Item> COARSE_DIRT_SLAB = registerBlockItem(VBlocks.COARSE_DIRT_SLAB);
//    public static final RegistryObject<Item> GRASS_SLAB = registerBlockItem(VBlocks.GRASS_SLAB);
    
    // Funny blocks
    public static final RegistryObject<Item> BRICK_BRICKS = registerBlockItem(VBlocks.BRICK_BRICKS);
    public static final RegistryObject<Item> BRICK_BRICK_SLAB = registerBlockItem(VBlocks.BRICK_BRICK_SLAB);
    public static final RegistryObject<Item> BRICK_BRICK_STAIRS = registerBlockItem(VBlocks.BRICK_BRICK_STAIRS);
    public static final RegistryObject<Item> STONE_BRICK_BRICKS = registerBlockItem(VBlocks.STONE_BRICK_BRICKS);
    public static final RegistryObject<Item> STONE_BRICK_BRICK_SLAB = registerBlockItem(VBlocks.STONE_BRICK_BRICK_SLAB);
    public static final RegistryObject<Item> STONE_BRICK_BRICK_STAIRS = registerBlockItem(VBlocks.STONE_BRICK_BRICK_STAIRS);

    // Rails
    public static final RegistryObject<Item> WOODEN_RAIL = ITEMS.register("wooden_rail", () -> new WoodenRailItem(VBlocks.WOODEN_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> GLOWSTONE_RAIL = registerBlockItem(VBlocks.GLOWSTONE_RAIL);
    public static final RegistryObject<Item> DIAMOND_POWERED_RAIL = registerBlockItem(VBlocks.DIAMOND_POWERED_RAIL);
    public static final RegistryObject<Item> NETHERITE_POWERED_RAIL = registerFireproofBlockItem(VBlocks.NETHERITE_POWERED_RAIL);
    public static final RegistryObject<Item> VOID_POWERED_RAIL = registerFireproofBlockItem(VBlocks.VOID_POWERED_RAIL);

    // Food Items
    public static final RegistryObject<Item> ENDER_OMELETTE = registerFoodItem("ender_omelette", 20, 1.5f);

    // Void Equipment
    //public static final RegistryObject<Item> VOID_ESSENCE = registerSimpleItem("void_essence", properties -> properties.fireResistant().rarity(Rarity.RARE));
    public static final RegistryObject<Item> VOID_CRYSTAL = registerSimpleItem("void_crystal", properties -> properties.fireResistant().rarity(Rarity.RARE));
    public static final RegistryObject<Item> VOID_SHARD = registerSimpleItem("void_shard", properties -> properties.fireResistant().rarity(Rarity.RARE));
    public static final RegistryObject<Item> VOID_ORE = registerVoidBlockItem(VBlocks.VOID_ORE);
    public static final RegistryObject<Item> VOID_BLOCK = registerVoidBlockItem(VBlocks.VOID_BLOCK);
    public static final RegistryObject<Item> VOID_BOOTS = registerVoidArmorItem("void_boots", EquipmentSlot.FEET);
    public static final RegistryObject<Item> VOID_LEGGINGS = registerVoidArmorItem("void_leggings", EquipmentSlot.LEGS);
    public static final RegistryObject<Item> VOID_CHESTPLATE = registerVoidArmorItem("void_chestplate", EquipmentSlot.CHEST);
    public static final RegistryObject<Item> VOID_HELMET = registerVoidArmorItem("void_helmet", EquipmentSlot.HEAD);
    public static final RegistryObject<Item> VOID_BOW = ITEMS.register("void_bow", () -> new VoidBowItem(new Item.Properties().fireResistant().defaultDurability(1569).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> VOID_SWORD = ITEMS.register("void_sword", () -> new VoidSwordItem(VoidItemTier.INSTANCE, 3, -2.4F, (new Item.Properties()).fireResistant().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> VOID_SHOVEL = ITEMS.register("void_shovel", () -> new ShovelItem(VoidItemTier.INSTANCE, 1.5F, -3.0F, (new Item.Properties()).fireResistant().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> VOID_PICKAXE = ITEMS.register("void_pickaxe", () -> new VoidPickaxeItem(VoidItemTier.INSTANCE, 1, -2.8F, (new Item.Properties()).fireResistant().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> VOID_AXE = ITEMS.register("void_axe", () -> new AxeItem(VoidItemTier.INSTANCE, 5.0F, -3.0F, (new Item.Properties()).fireResistant().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> VOID_HOE = ITEMS.register("void_hoe", () -> new HoeItem(VoidItemTier.INSTANCE, -5, 0.0F, (new Item.Properties()).fireResistant().rarity(Rarity.RARE)));

    // Recycler
    public static final RegistryObject<Item> RECYCLER = registerBlockItem(VBlocks.RECYCLER);

    // Make a BlockItem easily and adds it to the creative tab automatically
    public static RegistryObject<Item> registerBlockItem(RegistryObject<? extends Block> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static RegistryObject<Item> registerVoidBlockItem(RegistryObject<Block> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().fireResistant().rarity(Rarity.RARE)));
    }
    
    public static RegistryObject<Item> registerFireproofBlockItem(RegistryObject<Block> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().fireResistant()));
    }

    // Register a simple item that doesn't do anything (like crafting materials)
    public static RegistryObject<Item> registerSimpleItem(String name, Function<Item.Properties, Item.Properties> applyProperties) {
        Item.Properties properties = applyProperties.apply(new Item.Properties());

        return ITEMS.register(name, () -> new Item(properties));
    }

    public static RegistryObject<Item> registerVoidArmorItem(String name, EquipmentSlot slot) {
        return ITEMS.register(name, () -> new ArmorItem(VoidArmorMaterial.INSTANCE, slot, new Item.Properties().fireResistant().rarity(Rarity.RARE)));
    }

    public static RegistryObject<Item> registerFoodItem(String name, int nutrition, float saturation) {
        return registerSimpleItem(name, properties -> properties.food(new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation).build()));
    }
}
