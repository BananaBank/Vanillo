package rusty.vanillo.client;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import rusty.vanillo.client.screen.RecyclerScreen;
import rusty.vanillo.registry.VBlocks;
import rusty.vanillo.registry.VItems;
import rusty.vanillo.registry.VMenuTypes;

import java.awt.Color;

/**
 * @author DJRoundTable, TheDarkColour
 */
public final class ClientHandler {
    public static void registerEvents() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientHandler::clientSetup);
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientHandler::registerBlockColors);
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientHandler::registerItemColors);
    }

    private static void clientSetup(FMLClientSetupEvent event) {
        // register screen
        MenuScreens.register(VMenuTypes.RECYCLER.get(), RecyclerScreen::new);

        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(VBlocks.WOODEN_RAIL.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(VBlocks.GLOWSTONE_RAIL.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(VBlocks.DIAMOND_POWERED_RAIL.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(VBlocks.NETHERITE_POWERED_RAIL.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(VBlocks.VOID_POWERED_RAIL.get(), RenderType.cutout());


//            RenderTypeLookup.setRenderLayer(VBlocks.GRASS_SLAB.get(), RenderType.cutoutMipped());
        });

        ItemProperties.register(VItems.VOID_BOW.get(), new ResourceLocation("pull"), (stack, level, living, i) -> {
            return living == null ? 0.0f : (living.getUseItem() != stack ? 0.0f : (stack.getUseDuration() - living.getUseItemRemainingTicks()) / 20.0f);
        });
        ItemProperties.register(VItems.VOID_BOW.get(), new ResourceLocation("pulling"), (stack, level, living, i) -> {
            return living != null && living.isUsingItem() && living.getUseItem() == stack ? 1.0f : 0.0f;
        });
    }

    // Higher
    public static Color getRainbowColor(long ticks, float partialTicks, float speedFactor) {
        return Color.getHSBColor((float) ((180 * Math.sin((ticks + partialTicks) / speedFactor) - 180) / 360.0f), 1.0f, 0.5f);
    }
}