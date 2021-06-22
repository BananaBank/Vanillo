package rusty.vanillo.client.glint;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import rusty.vanillo.Vanillo;

import java.util.SortedMap;

/**
 * Creates different RenderTypes for different colored glints.
 * The only difference between the variants is the texture of the glint.
 *
 * Each color has seven different render types that all get stored in arrays.
 * Methods are provided for accessing the different types from ItemRenderer.
 *
 * @author TheDarkColour
 */
public final class ColoredGlints {
    // Glint color names
    public static final String[] COLORS = new String[] { "white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black", "rainbow" };
    private static final int BUFFER_SIZE = 256;

    // Item render types
    private static final RenderType[] COLORED_GLINTS = new RenderType[COLORS.length];
    private static final RenderType[] COLORED_FABULOUS_GLINTS = new RenderType[COLORS.length]; // "translucent" glints
    private static final RenderType[] COLORED_DIRECT_GLINTS = new RenderType[COLORS.length];

    // Armor render types
    private static final RenderType[] COLORED_ARMOR_GLINTS = new RenderType[COLORS.length];
    private static final RenderType[] COLORED_ARMOR_ENTITY_GLINTS = new RenderType[COLORS.length];

    // Item entity render types
    private static final RenderType[] COLORED_ENTITY_GLINTS = new RenderType[COLORS.length];
    private static final RenderType[] COLORED_DIRECT_ENTITY_GLINTS = new RenderType[COLORS.length];

    // Override default render type
    public static RenderType getArmorGlint(ItemStack stack, boolean normal) {
        byte color = getGlintIndex(stack);

        return normal ? COLORED_ARMOR_GLINTS[color] : COLORED_ARMOR_ENTITY_GLINTS[color];
    }

    // Override default render type
    public static RenderType getEntityGlint(ItemStack stack, boolean direct) {
        byte color = getGlintIndex(stack);

        return direct ? COLORED_DIRECT_ENTITY_GLINTS[color] : COLORED_ENTITY_GLINTS[color];
    }

    // Override default render type
    public static RenderType getItemGlint(ItemStack stack, boolean direct) {
        byte color = getGlintIndex(stack);

        return direct ? COLORED_DIRECT_GLINTS[color] : COLORED_GLINTS[color];
    }

    // Supports the Fabulous Graphics setting
    public static RenderType getItemGlint(ItemStack stack, boolean fabulous, boolean direct) {
        byte color = getGlintIndex(stack);

        return fabulous ? COLORED_FABULOUS_GLINTS[color] : getItemGlint(stack, direct);
    }

    // Override default render type
    public static RenderType getDirectGlint(ItemStack stack, boolean entityGlint) {
        byte color = getGlintIndex(stack);

        return entityGlint ? COLORED_DIRECT_ENTITY_GLINTS[color] : COLORED_DIRECT_GLINTS[color];
    }

    private static byte getGlintIndex(ItemStack stack) {
        return stack.hasTag() && stack.getTag().contains("Glint") ? stack.getTag().getByte("Glint") : 2;
    }

    // Create all the glint render types
    static {
        // fill all the arrays with colored glints
        // Use an indexed for loop to keep order consistent in all the arrays
        for (int i = 0; i < COLORS.length; ++i) {
            // Obtain color at the current index
            String color = COLORS[i];

            // Use default glint for magenta
            if (color == "magenta") {
                COLORED_GLINTS[i] = RenderType.glint();
                COLORED_FABULOUS_GLINTS[i] = RenderType.glintTranslucent();
                COLORED_DIRECT_GLINTS[i] = RenderType.glintDirect();

                COLORED_ARMOR_GLINTS[i] = RenderType.armorGlint();
                COLORED_ARMOR_ENTITY_GLINTS[i] = RenderType.armorEntityGlint();

                COLORED_ENTITY_GLINTS[i] = RenderType.entityGlint();
                COLORED_DIRECT_ENTITY_GLINTS[i] = RenderType.entityGlintDirect();
                continue;
            }

            // Texture of the colored glint
            ResourceLocation glintTexture = new ResourceLocation(Vanillo.ID, "textures/glint/" + color + "_glint.png");

            // Make colored copies of the glint render type but with different textures
            COLORED_GLINTS[i] = RenderType.create(color + "_glint", DefaultVertexFormats.POSITION_TEX, GL11.GL_QUADS, BUFFER_SIZE, RenderType.State.builder().setTextureState(new RenderState.TextureState(glintTexture, true, false)).setWriteMaskState(RenderType.COLOR_WRITE).setCullState(RenderType.NO_CULL).setDepthTestState(RenderType.EQUAL_DEPTH_TEST).setTransparencyState(RenderType.GLINT_TRANSPARENCY).setTexturingState(RenderType.GLINT_TEXTURING).createCompositeState(false));
            COLORED_FABULOUS_GLINTS[i] = RenderType.create(color + "_glint_translucent", DefaultVertexFormats.POSITION_TEX, GL11.GL_QUADS, BUFFER_SIZE, RenderType.State.builder().setTextureState(new RenderState.TextureState(glintTexture, true, false)).setWriteMaskState(RenderType.COLOR_WRITE).setCullState(RenderType.NO_CULL).setDepthTestState(RenderType.EQUAL_DEPTH_TEST).setTransparencyState(RenderType.GLINT_TRANSPARENCY).setTexturingState(RenderType.GLINT_TEXTURING).setOutputState(RenderType.ITEM_ENTITY_TARGET).createCompositeState(false));
            COLORED_DIRECT_GLINTS[i] = RenderType.create(color + "_glint_direct", DefaultVertexFormats.POSITION_TEX, GL11.GL_QUADS, BUFFER_SIZE, RenderType.State.builder().setTextureState(new RenderState.TextureState(glintTexture, true, false)).setWriteMaskState(RenderType.COLOR_WRITE).setCullState(RenderType.NO_CULL).setDepthTestState(RenderType.EQUAL_DEPTH_TEST).setTransparencyState(RenderType.GLINT_TRANSPARENCY).setTexturingState(RenderType.GLINT_TEXTURING).createCompositeState(false));

            COLORED_ARMOR_GLINTS[i] = RenderType.create(color + "_armor_glint", DefaultVertexFormats.POSITION_TEX, GL11.GL_QUADS, BUFFER_SIZE, RenderType.State.builder().setTextureState(new RenderState.TextureState(glintTexture, true, false)).setWriteMaskState(RenderType.COLOR_WRITE).setCullState(RenderType.NO_CULL).setDepthTestState(RenderType.EQUAL_DEPTH_TEST).setTransparencyState(RenderType.GLINT_TRANSPARENCY).setTexturingState(RenderType.GLINT_TEXTURING).setLayeringState(RenderType.VIEW_OFFSET_Z_LAYERING).createCompositeState(false));
            COLORED_ARMOR_ENTITY_GLINTS[i] = RenderType.create(color + "_armor_entity_glint", DefaultVertexFormats.POSITION_TEX, GL11.GL_QUADS, BUFFER_SIZE, RenderType.State.builder().setTextureState(new RenderState.TextureState(glintTexture, true, false)).setWriteMaskState(RenderType.COLOR_WRITE).setCullState(RenderType.NO_CULL).setDepthTestState(RenderType.EQUAL_DEPTH_TEST).setTransparencyState(RenderType.GLINT_TRANSPARENCY).setTexturingState(RenderType.ENTITY_GLINT_TEXTURING).setLayeringState(RenderType.VIEW_OFFSET_Z_LAYERING).createCompositeState(false));

            COLORED_ENTITY_GLINTS[i] = RenderType.create(color + "_entity_glint", DefaultVertexFormats.POSITION_TEX, GL11.GL_QUADS, BUFFER_SIZE, RenderType.State.builder().setTextureState(new RenderState.TextureState(glintTexture, true, false)).setWriteMaskState(RenderType.COLOR_WRITE).setCullState(RenderType.NO_CULL).setDepthTestState(RenderType.EQUAL_DEPTH_TEST).setTransparencyState(RenderType.GLINT_TRANSPARENCY).setOutputState(RenderType.ITEM_ENTITY_TARGET).setTexturingState(RenderType.ENTITY_GLINT_TEXTURING).createCompositeState(false));
            COLORED_DIRECT_ENTITY_GLINTS[i] = RenderType.create(color + "_entity_glint_direct", DefaultVertexFormats.POSITION_TEX, GL11.GL_QUADS, BUFFER_SIZE, RenderType.State.builder().setTextureState(new RenderState.TextureState(glintTexture, true, false)).setWriteMaskState(RenderType.COLOR_WRITE).setCullState(RenderType.NO_CULL).setDepthTestState(RenderType.EQUAL_DEPTH_TEST).setTransparencyState(RenderType.GLINT_TRANSPARENCY).setTexturingState(RenderType.ENTITY_GLINT_TEXTURING).createCompositeState(false));
        }

        // Register the newly created render types
        SortedMap<RenderType, BufferBuilder> fixedBuffers = Minecraft.getInstance().renderBuffers().fixedBuffers;

        // Add every glint render type to the registered RenderTypeBuffers
        for (int i = 0; i < COLORS.length; ++i) {
            fixedBuffers.put(COLORED_GLINTS[i], new BufferBuilder(BUFFER_SIZE));
            fixedBuffers.put(COLORED_FABULOUS_GLINTS[i], new BufferBuilder(BUFFER_SIZE));
            fixedBuffers.put(COLORED_DIRECT_GLINTS[i], new BufferBuilder(BUFFER_SIZE));

            fixedBuffers.put(COLORED_ARMOR_GLINTS[i], new BufferBuilder(BUFFER_SIZE));
            fixedBuffers.put(COLORED_ARMOR_ENTITY_GLINTS[i], new BufferBuilder(BUFFER_SIZE));

            fixedBuffers.put(COLORED_ENTITY_GLINTS[i], new BufferBuilder(BUFFER_SIZE));
            fixedBuffers.put(COLORED_DIRECT_ENTITY_GLINTS[i], new BufferBuilder(BUFFER_SIZE));
        }
    }

    public static void endBatch(IRenderTypeBuffer.Impl buffer) {
        for (int i = 0; i < COLORS.length; i++) {
            buffer.endBatch(COLORED_GLINTS[i]);
            buffer.endBatch(COLORED_FABULOUS_GLINTS[i]);
            buffer.endBatch(COLORED_DIRECT_GLINTS[i]);
            buffer.endBatch(COLORED_ARMOR_GLINTS[i]);
            buffer.endBatch(COLORED_ARMOR_ENTITY_GLINTS[i]);
            buffer.endBatch(COLORED_ENTITY_GLINTS[i]);
            buffer.endBatch(COLORED_DIRECT_ENTITY_GLINTS[i]);
        }
    }
}
