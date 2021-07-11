package rusty.vanillo.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rusty.vanillo.client.ClientHandler;
import rusty.vanillo.entities.BanishmentLightningEntity;

import java.awt.Color;
import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class BanishedLightningRenderer extends EntityRenderer<BanishmentLightningEntity> {
    public BanishedLightningRenderer(EntityRendererManager manager) {
        super(manager);
    }

    public void render(BanishmentLightningEntity entity, float yaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int light) {
        float[] afloat = new float[8];
        float[] afloat1 = new float[8];
        float f = 0.0F;
        float f1 = 0.0F;
        Random random = new Random(entity.seed);

        for (int i = 7; i >= 0; --i) {
            afloat[i] = f;
            afloat1[i] = f1;
            f += (float) (random.nextInt(11) - 5);
            f1 += (float) (random.nextInt(11) - 5);
        }

        IVertexBuilder builder = buffer.getBuffer(RenderType.lightning());
        Matrix4f matrix4f = stack.last().pose();

        for (int j = 0; j < 4; ++j) {
            Random random1 = new Random(entity.seed);

            for (int k = 0; k < 3; ++k) {
                int l = 7;
                int i1 = 0;
                if (k > 0) {
                    l = 7 - k;
                }

                if (k > 0) {
                    i1 = l - 2;
                }

                float f2 = afloat[l] - f;
                float f3 = afloat1[l] - f1;

                for (int j1 = l; j1 >= i1; --j1) {
                    float f4 = f2;
                    float f5 = f3;

                    if (k == 0) {
                        f2 += (float) (random1.nextInt(11) - 5);
                        f3 += (float) (random1.nextInt(11) - 5);
                    } else {
                        f2 += (float) (random1.nextInt(31) - 15);
                        f3 += (float) (random1.nextInt(31) - 15);
                    }

                    float f10 = 0.1F + (float) j * 0.2F;
                    if (k == 0) {
                        f10 = (float) ((double) f10 * ((double) j1 * 0.1D + 1.0D));
                    }

                    float f11 = 0.1F + (float) j * 0.2F;
                    if (k == 0) {
                        f11 *= (float) (j1 - 1) * 0.1F + 1.0F;
                    }

                    Color rainbowCol = ClientHandler.getRainbowColor(entity.level.getGameTime(), partialTicks, 16.0f);

                    float r = ((float) rainbowCol.getRed()) / 255.0f;
                    float g = ((float) rainbowCol.getGreen()) / 255.0f;
                    float b = ((float) rainbowCol.getBlue()) / 255.0f;

                    quad(matrix4f, builder, f2, f3, j1, f4, f5, r, g, b, f10, f11, false, false, true, false);
                    quad(matrix4f, builder, f2, f3, j1, f4, f5, r, g, b, f10, f11, true, false, true, true);
                    quad(matrix4f, builder, f2, f3, j1, f4, f5, r, g, b, f10, f11, true, true, false, true);
                    quad(matrix4f, builder, f2, f3, j1, f4, f5, r, g, b, f10, f11, false, true, false, false);
                }
            }
        }
    }

    private static void quad(Matrix4f pose, IVertexBuilder builder, float p_229116_2_, float p_229116_3_, int p_229116_4_, float p_229116_5_, float p_229116_6_, float r, float g, float b, float p_229116_10_, float p_229116_11_, boolean p_229116_12_, boolean p_229116_13_, boolean p_229116_14_, boolean p_229116_15_) {
        builder.vertex(pose, p_229116_2_ + (p_229116_12_ ? p_229116_11_ : -p_229116_11_), (float) (p_229116_4_ * 16), p_229116_3_ + (p_229116_13_ ? p_229116_11_ : -p_229116_11_)).color(r, g, b, 0.3F).endVertex();
        builder.vertex(pose, p_229116_5_ + (p_229116_12_ ? p_229116_10_ : -p_229116_10_), (float) ((p_229116_4_ + 1) * 16), p_229116_6_ + (p_229116_13_ ? p_229116_10_ : -p_229116_10_)).color(r, g, b, 0.3F).endVertex();
        builder.vertex(pose, p_229116_5_ + (p_229116_14_ ? p_229116_10_ : -p_229116_10_), (float) ((p_229116_4_ + 1) * 16), p_229116_6_ + (p_229116_15_ ? p_229116_10_ : -p_229116_10_)).color(r, g, b, 0.3F).endVertex();
        builder.vertex(pose, p_229116_2_ + (p_229116_14_ ? p_229116_11_ : -p_229116_11_), (float) (p_229116_4_ * 16), p_229116_3_ + (p_229116_15_ ? p_229116_11_ : -p_229116_11_)).color(r, g, b, 0.3F).endVertex();
    }

    public ResourceLocation getTextureLocation(BanishmentLightningEntity entity) {
        return AtlasTexture.LOCATION_BLOCKS;
    }

}
