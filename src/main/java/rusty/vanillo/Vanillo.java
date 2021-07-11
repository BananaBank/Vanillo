package rusty.vanillo;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import rusty.vanillo.client.ClientHandler;
import rusty.vanillo.command.GlintCommand;
import rusty.vanillo.feature.VConfiguredFeatures;
import rusty.vanillo.feature.VFeatures;
import rusty.vanillo.item.VoidBowItem;
import rusty.vanillo.item.VoidSwordItem;
import rusty.vanillo.recipe.ColoredGlintAnvilRecipe;
import rusty.vanillo.registry.VBlocks;
import rusty.vanillo.registry.VContainerTypes;
import rusty.vanillo.registry.VEnchantments;
import rusty.vanillo.registry.VItems;
import rusty.vanillo.registry.VPointOfInterestTypes;
import rusty.vanillo.registry.VRecipeSerializers;
import rusty.vanillo.registry.VSoundEvents;
import rusty.vanillo.registry.VTileEntities;
import rusty.vanillo.registry.VVillagerProfessions;

/**
 * @author TheDarkColour, cfrishausen, DJRoundTable
 */
@Mod(Vanillo.ID)
public final class Vanillo {
    public static final String ID = "vanillo";

    public Vanillo() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        final IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        // LOWEST priority; fires after every other mod
        modEventBus.addGenericListener(Item.class, EventPriority.LOWEST, this::onItemRegistry);
        modEventBus.addListener(this::onCommonSetup);

        forgeEventBus.addListener(this::onRegisterCommands);
        forgeEventBus.addListener(ColoredGlintAnvilRecipe::onAnvilUpdate);
        forgeEventBus.addListener(VVillagerProfessions::addTrades);
        forgeEventBus.addListener(VFeatures::onBiomeLoad);
        forgeEventBus.addListener(VoidBowItem::damageEvent);
        forgeEventBus.addListener(VoidBowItem::cleanupEntities);
        forgeEventBus.addListener(VoidSwordItem::punish);

        // Registry classes
        VBlocks.BLOCKS.register(modEventBus);
        VItems.ITEMS.register(modEventBus);
        VEnchantments.ENCHANTMENTS.register(modEventBus);
        VTileEntities.TILE_ENTITIES.register(modEventBus);
        VVillagerProfessions.PROFESSIONS.register(modEventBus);
        VPointOfInterestTypes.POI_TYPES.register(modEventBus);
        VSoundEvents.SOUND_EVENTS.register(modEventBus);
        VContainerTypes.CONTAINERS.register(modEventBus);
        VRecipeSerializers.SERIALIZERS.register(modEventBus);

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientHandler::registerEvents);
    }

    public void onRegisterCommands(RegisterCommandsEvent event) {
        GlintCommand.register(event.getDispatcher());
    }

    public void onCommonSetup(FMLCommonSetupEvent event) {
        VConfiguredFeatures.registerConfiguredFeatures();
    }

    // Called after all other items are registered
    public void onItemRegistry(RegistryEvent.Register<Item> event) {
        // Example for accesstransformers: https://mcforge.readthedocs.io/en/latest/advanced/accesstransformers/
        Items.OBSIDIAN.isFireResistant = true;
        Items.BLAZE_ROD.isFireResistant = true;
        Items.BLAZE_POWDER.isFireResistant = true;
    }
}
