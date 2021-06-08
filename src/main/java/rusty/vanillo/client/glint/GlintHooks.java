package rusty.vanillo.client.glint;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.vertex.MatrixApplyingVertexBuilder;
import com.mojang.blaze3d.vertex.VertexBuilderUtils;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;

public final class GlintHooks {
    public static IVertexBuilder getArmorFoilBuffer(IRenderTypeBuffer buffer, RenderType layer, boolean regularType, boolean p_239386_3_) {
        return p_239386_3_ ? VertexBuilderUtils.create(buffer.getBuffer(ColoredGlints.getArmorGlint(regularType)), buffer.getBuffer(layer)) : buffer.getBuffer(layer);
    }

    public static IVertexBuilder getCompassFoilBuffer(IRenderTypeBuffer buffer, RenderType layer, MatrixStack.Entry entry) {
        return VertexBuilderUtils.create(new MatrixApplyingVertexBuilder(buffer.getBuffer(ColoredGlints.getItemGlint(false)), entry.pose(), entry.normal()), buffer.getBuffer(layer));
    }

    public static IVertexBuilder getCompassFoilDirect(IRenderTypeBuffer buffer, RenderType layer, MatrixStack.Entry entry) {
        return VertexBuilderUtils.create(new MatrixApplyingVertexBuilder(buffer.getBuffer(ColoredGlints.getItemGlint(true)), entry.pose(), entry.normal()), buffer.getBuffer(layer));
    }
}
