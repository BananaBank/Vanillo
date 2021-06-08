package rusty.vanillo;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import rusty.vanillo.client.ClientHandler;
import rusty.vanillo.registry.VBlocks;
import rusty.vanillo.registry.VEnchantments;
import rusty.vanillo.registry.VItems;
import rusty.vanillo.registry.VTileEntities;

/**
 * @author TheDarkColour, cfrishausen, DJRoundTable
 */
@Mod(Vanillo.ID)
public final class Vanillo {
    public static final String ID = "vanillo";

    public Vanillo() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // LOWEST priority; fires after every other mod
        modEventBus.addGenericListener(Item.class, EventPriority.LOWEST, this::onItemRegistry);

        VBlocks.BLOCKS.register(modEventBus);
        VItems.ITEMS.register(modEventBus);
        VEnchantments.ENCHANTMENTS.register(modEventBus);
        VTileEntities.TILE_ENTITIES.register(modEventBus);
        ClientHandler.registerEvents();

        // Register ourselves for server and other game events we are interested in
        //MinecraftForge.EVENT_BUS.register(this);
    }

    // Called after all other items are registered
    public void onItemRegistry(RegistryEvent.Register<Item> event) {
        // Example for accesstransformers: https://mcforge.readthedocs.io/en/latest/advanced/accesstransformers/
        Items.OBSIDIAN.isFireResistant = true;
        Items.BLAZE_ROD.isFireResistant = true;
        Items.BLAZE_POWDER.isFireResistant = true;
    }
}
