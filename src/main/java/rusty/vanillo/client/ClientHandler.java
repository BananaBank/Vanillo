package rusty.vanillo.client;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiContainerEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import rusty.vanillo.client.screen.RecyclerScreen;
import rusty.vanillo.registry.VBlocks;
import rusty.vanillo.registry.VContainerTypes;
import rusty.vanillo.registry.VItems;

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
        ScreenManager.register(VContainerTypes.RECYCLER.get(), RecyclerScreen::new);

        event.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(VBlocks.WOODEN_RAIL.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(VBlocks.GLOWSTONE_RAIL.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(VBlocks.DIAMOND_POWERED_RAIL.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(VBlocks.NETHERITE_POWERED_RAIL.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(VBlocks.VOID_POWERED_RAIL.get(), RenderType.cutout());


//            RenderTypeLookup.setRenderLayer(VBlocks.GRASS_SLAB.get(), RenderType.cutoutMipped());
        });

        ItemModelsProperties.register(VItems.VOID_BOW.get(), new ResourceLocation("pull"), (stack, level, living) -> {
            return living == null ? 0.0f : (living.getUseItem() != stack ? 0.0f : (stack.getUseDuration() - living.getUseItemRemainingTicks()) / 20.0f);
        });
        ItemModelsProperties.register(VItems.VOID_BOW.get(), new ResourceLocation("pulling"), (stack, level, living) -> {
            return living != null && living.isUsingItem() && living.getUseItem() == stack ? 1.0f : 0.0f;
        });
    }

    public static void overrideSmithingScreen(GuiContainerEvent.DrawBackground event) {

    }

    /*// Adds grass slab to blocks that have biome tint colors
    private static void registerBlockColors(ColorHandlerEvent.Block event) {
        event.getBlockColors().register((p_228064_0_, p_228064_1_, p_228064_2_, p_228064_3_) -> {
            return p_228064_1_ != null && p_228064_2_ != null ? BiomeColors.getAverageGrassColor(p_228064_1_, p_228064_2_) : GrassColors.get(0.5D, 1.0D);
        }, VBlocks.GRASS_SLAB.get());
    }

    // Adds the grass slab item block to tint colors
    private static void registerItemColors(ColorHandlerEvent.Item event) {
        BlockColors blockColors = event.getBlockColors();
        event.getItemColors().register((stack, p_210235_2_) -> {
            BlockState blockstate = ((BlockItem)stack.getItem()).getBlock().defaultBlockState();
            return blockColors.getColor(blockstate, null, null, p_210235_2_);
        }, VBlocks.GRASS_SLAB.get());
    }*/

    // Higher
    public static Color getRainbowColor(long ticks, float partialTicks, float speedFactor) {
        return Color.getHSBColor((float) ((180 * Math.sin((ticks + partialTicks) / speedFactor) - 180) / 360.0f), 1.0f, 0.5f);
    }
}