package rusty.vanillo.client.screen;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import rusty.vanillo.Vanillo;
import rusty.vanillo.container.RecyclerContainer;

public class RecyclerScreen extends ContainerScreen<RecyclerContainer> {
    // Gui texture location
    public static final ResourceLocation TEXTURE = new ResourceLocation(Vanillo.ID, "textures/gui/container/recycler_gui.png");

    // Default constructor
    public RecyclerScreen(RecyclerContainer container, PlayerInventory inv, ITextComponent title) {
        super(container, inv, title);
    }

    @Override // render
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        renderTooltip(stack, mouseX, mouseY);
    }

    @Override // drawGuiBackground
    protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(TEXTURE);
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
    public void blit(MatrixStack stack, int xPos, int yPos, int uMin, int vMin, int uMax, int vMax) {
        super.blit(stack, xPos, yPos, uMin, vMin, uMax, vMax);
    }
}

