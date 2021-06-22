package rusty.vanillo.client.glint;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.vertex.MatrixApplyingVertexBuilder;
import com.mojang.blaze3d.vertex.VertexBuilderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.item.ItemStack;

// Called from the core mods
public final class GlintHooks {
    // todo add to BipedArmorLayer
    public static IVertexBuilder getArmorFoilBuffer(IRenderTypeBuffer buffer, RenderType layer, boolean regularType, boolean p_239386_3_) {
        // todo get ItemStack context
        return p_239386_3_ ? VertexBuilderUtils.create(buffer.getBuffer(ColoredGlints.getArmorGlint(null, regularType)), buffer.getBuffer(layer)) : buffer.getBuffer(layer);
    }

    public static IVertexBuilder getElytraFoil(ItemStack stack, IRenderTypeBuffer buffer, RenderType layer, boolean regularType, boolean p_239386_3_) {
        return p_239386_3_ ? VertexBuilderUtils.create(buffer.getBuffer(ColoredGlints.getArmorGlint(stack, regularType)), buffer.getBuffer(layer)) : buffer.getBuffer(layer);
    }

    public static IVertexBuilder getFoilBuffer(ItemStack stack, IRenderTypeBuffer p_229113_0_, RenderType p_229113_1_, boolean p_229113_2_, boolean p_229113_3_) {
        if (p_229113_3_) {
            // PATCH
            return VertexBuilderUtils.create(p_229113_0_.getBuffer(ColoredGlints.getItemGlint(stack, Minecraft.useShaderTransparency() && p_229113_1_ == Atlases.translucentItemSheet(), p_229113_2_)), p_229113_0_.getBuffer(p_229113_1_));
        } else {
            return p_229113_0_.getBuffer(p_229113_1_);
        }
    }

    public static IVertexBuilder getFoilBufferDirect(ItemStack stack, IRenderTypeBuffer p_239391_0_, RenderType p_239391_1_, boolean p_239391_2_, boolean p_239391_3_) {
        return p_239391_3_ ? VertexBuilderUtils.create(p_239391_0_.getBuffer(ColoredGlints.getDirectGlint(stack, p_239391_2_)), p_239391_0_.getBuffer(p_239391_1_)) : p_239391_0_.getBuffer(p_239391_1_);
    }

    public static IVertexBuilder getCompassFoilBuffer(ItemStack stack, IRenderTypeBuffer buffer, RenderType layer, MatrixStack.Entry entry) {
        return VertexBuilderUtils.create(new MatrixApplyingVertexBuilder(buffer.getBuffer(ColoredGlints.getItemGlint(stack, false)), entry.pose(), entry.normal()), buffer.getBuffer(layer));
    }

    public static IVertexBuilder getCompassFoilBufferDirect(ItemStack stack, IRenderTypeBuffer buffer, RenderType layer, MatrixStack.Entry entry) {
        return VertexBuilderUtils.create(new MatrixApplyingVertexBuilder(buffer.getBuffer(ColoredGlints.getItemGlint(stack, true)), entry.pose(), entry.normal()), buffer.getBuffer(layer));
    }
}
