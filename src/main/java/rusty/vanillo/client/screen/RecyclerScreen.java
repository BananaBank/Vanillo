package rusty.vanillo.client.screen;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.FurnaceFuelSlot;
import net.minecraft.inventory.container.FurnaceResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import rusty.vanillo.Vanillo;
import rusty.vanillo.container.RecyclerContainer;

public class RecyclerScreen extends ContainerScreen<RecyclerContainer> {

    private final ResourceLocation SCREEN = new ResourceLocation(Vanillo.ID, "textures/gui/container/recycler_gui.png");

    public RecyclerScreen(RecyclerContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
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
        this.minecraft.getTextureManager().bind(SCREEN);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(stack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }



    /*@Override // drawGuiContainerForeground
    protected void renderLabels(MatrixStack stack, int mouseX, int mouseY) {
        super.renderLabels(stack, mouseX, mouseY);
    }*/
}

