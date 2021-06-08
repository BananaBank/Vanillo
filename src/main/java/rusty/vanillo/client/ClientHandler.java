package rusty.vanillo.client;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import rusty.vanillo.registry.VBlocks;

/**
 * @author DJRoundTable
 */
public final class ClientHandler {
    public static void registerEvents() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientHandler::clientSetup);
    }

    private static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(VBlocks.WOODEN_RAIL.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(VBlocks.GLOWSTONE_RAIL.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(VBlocks.DIAMOND_POWERED_RAIL.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(VBlocks.NETHERITE_POWERED_RAIL.get(), RenderType.cutout());
        });
    }
}