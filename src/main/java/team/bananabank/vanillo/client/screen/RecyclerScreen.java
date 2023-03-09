package team.bananabank.vanillo.client.screen;


import team.bananabank.vanillo.menu.RecyclerMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import team.bananabank.vanillo.Vanillo;

public class RecyclerScreen extends AbstractContainerScreen<RecyclerMenu> {
    // Gui texture location
    public static final ResourceLocation TEXTURE = new ResourceLocation(Vanillo.ID, "textures/gui/container/recycler_gui.png");

    // Default constructor
    public RecyclerScreen(RecyclerMenu container, Inventory inv, Component title) {
        super(container, inv, title);
    }

    @Override // render
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        renderTooltip(stack, mouseX, mouseY);
    }

    @Override // drawGuiBackground
    protected void renderBg(PoseStack stack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        this.minecraft.getTextureManager().bindForSetup(TEXTURE);
        int guiLeft = leftPos;
        int guiTop = topPos;
        this.blit(stack, guiLeft, guiTop, 0, 0, this.imageWidth, this.imageHeight);

        // Draws the fuel flame
        if (this.menu.isLit()) {
            int fuel = this.menu.getLitProgress(); // Gets remaining fuel as a ratio * 13
            this.blit(stack, guiLeft + 56, guiTop + 36 + 12 - fuel, 176, 12 - fuel, 14, fuel + 1);
        }

        // Draws the gears
        int progress = (int) ((((double) this.menu.getBurnProgress()) / 24.0) * 25); // Gets a remaining fuel as a ratio * 24
        this.blit(stack, guiLeft + 80, guiTop + 34, 176, 14, progress + 1, 16);
    }

    @Override // UV = XY Texture coordinates
    public void blit(PoseStack stack, int xPos, int yPos, int uMin, int vMin, int uMax, int vMax) {
        super.blit(stack, xPos, yPos, uMin, vMin, uMax, vMax);
    }
}

