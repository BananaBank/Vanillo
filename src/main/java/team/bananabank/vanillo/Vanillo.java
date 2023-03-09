package team.bananabank.vanillo;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import team.bananabank.vanillo.client.ClientHandler;
import team.bananabank.vanillo.command.GlintCommand;
import team.bananabank.vanillo.item.VoidBowItem;
import team.bananabank.vanillo.item.VoidSwordItem;
import team.bananabank.vanillo.recipe.ColoredGlintAnvilRecipe;
import team.bananabank.vanillo.registry.VBlockEntities;
import team.bananabank.vanillo.registry.VBlocks;
import team.bananabank.vanillo.registry.VEnchantments;
import team.bananabank.vanillo.registry.VFeatures;
import team.bananabank.vanillo.registry.VItems;
import team.bananabank.vanillo.registry.VMenuTypes;
import team.bananabank.vanillo.registry.VPoiTypes;
import team.bananabank.vanillo.registry.VRecipeSerializers;
import team.bananabank.vanillo.registry.VSoundEvents;
import team.bananabank.vanillo.registry.VVillagerProfessions;

/**
 * @author TheDarkColour, cfrishausen, DJRoundTable
 */
@Mod(Vanillo.ID)
public final class Vanillo {
    public static final String ID = "vanillo";

    public Vanillo() {
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        final IEventBus fgBus = MinecraftForge.EVENT_BUS;

        modBus.addListener(EventPriority.LOWEST, this::onItemRegistry);

        fgBus.addListener(this::onRegisterCommands);
        fgBus.addListener(ColoredGlintAnvilRecipe::onAnvilUpdate);
        fgBus.addListener(VVillagerProfessions::addTrades);
        fgBus.addListener(VoidBowItem::damageEvent);
        fgBus.addListener(VoidBowItem::cleanupEntities);
        fgBus.addListener(VoidSwordItem::punish);

        VBlocks.BLOCKS.register(modBus);
        VItems.ITEMS.register(modBus);
        VEnchantments.ENCHANTMENTS.register(modBus);
        VBlockEntities.TILE_ENTITIES.register(modBus);
        VFeatures.CONFIGURED_FEATURES.register(modBus);
        VFeatures.PLACED_FEATURES.register(modBus);
        VVillagerProfessions.PROFESSIONS.register(modBus);
        VPoiTypes.POI_TYPES.register(modBus);
        VSoundEvents.SOUND_EVENTS.register(modBus);
        VMenuTypes.MENUS.register(modBus);
        VRecipeSerializers.SERIALIZERS.register(modBus);

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientHandler::registerEvents);
    }

    public void onRegisterCommands(RegisterCommandsEvent event) {
        GlintCommand.register(event.getDispatcher());
    }

    public void onItemRegistry(RegisterEvent event) {
        if (event.getRegistryKey() == Registries.ITEM) {
            Items.OBSIDIAN.isFireResistant = true;
            Items.BLAZE_ROD.isFireResistant = true;
            Items.BLAZE_POWDER.isFireResistant = true;
        }
    }
}
