package team.bananabank.vanillo.client.glint;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.SheetedDecalTextureGenerator;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexMultiConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.world.item.ItemStack;

// Called from the core mods
public final class GlintHooks {
    // Just in case
    public static final ThreadLocal<ItemStack> ARMOR_CONTEXT = ThreadLocal.withInitial(() -> ItemStack.EMPTY);

    // used for writing one of the core mods but doesn't actually do anything
    public static VertexConsumer aaa(MultiBufferSource buffers, RenderType type, boolean regularType, boolean p_239386_3_) {
        return getArmorFoilBuffer(buffers, type, regularType, p_239386_3_);
    }

    public static VertexConsumer getArmorFoilBuffer(MultiBufferSource buffers, RenderType type, boolean regularType, boolean p_239386_3_) {
        return p_239386_3_ ? VertexMultiConsumer.create(buffers.getBuffer(ColoredGlints.getArmorGlint(ARMOR_CONTEXT.get(), regularType)), buffers.getBuffer(type)) : buffers.getBuffer(type);
    }

    public static VertexConsumer getElytraFoil(ItemStack stack, MultiBufferSource buffers, RenderType type, boolean regularType, boolean p_239386_3_) {
        return p_239386_3_ ? VertexMultiConsumer.create(buffers.getBuffer(ColoredGlints.getArmorGlint(stack, regularType)), buffers.getBuffer(type)) : buffers.getBuffer(type);
    }

    public static VertexConsumer getFoilBuffer(ItemStack stack, MultiBufferSource buffers, RenderType type, boolean direct, boolean p_229113_3_) {
        if (p_229113_3_) {
            return VertexMultiConsumer.create(buffers.getBuffer(ColoredGlints.getItemGlint(stack, Minecraft.useShaderTransparency() && type == Sheets.translucentItemSheet(), direct)), buffers.getBuffer(type));
        } else {
            return buffers.getBuffer(type);
        }
    }

    public static VertexConsumer getFoilBufferDirect(ItemStack stack, MultiBufferSource buffers, RenderType type, boolean entityGlint, boolean hasGlint) {
        return hasGlint ? VertexMultiConsumer.create(buffers.getBuffer(ColoredGlints.getDirectGlint(stack, entityGlint)), buffers.getBuffer(type)) : buffers.getBuffer(type);
    }

    public static VertexConsumer getCompassFoilBuffer(ItemStack stack, MultiBufferSource buffers, RenderType type, PoseStack.Pose pose) {
        return VertexMultiConsumer.create(new SheetedDecalTextureGenerator(buffers.getBuffer(ColoredGlints.getItemGlint(stack, false)), pose.pose(), pose.normal(), 0.0078125F), buffers.getBuffer(type));
    }

    public static VertexConsumer getCompassFoilBufferDirect(ItemStack stack, MultiBufferSource buffers, RenderType type, PoseStack.Pose pose) {
        return VertexMultiConsumer.create(new SheetedDecalTextureGenerator(buffers.getBuffer(ColoredGlints.getItemGlint(stack, true)), pose.pose(), pose.normal(), 0.0078125F), buffers.getBuffer(type));
    }
}
