package rusty.vanillo.compat.jei;

import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import rusty.vanillo.Vanillo;
import rusty.vanillo.recipe.RecyclingRecipe;
import rusty.vanillo.registry.VBlocks;

public class RecyclingRecipeCategory implements IRecipeCategory<RecyclingRecipe> {
    public static final ResourceLocation ID = new ResourceLocation(Vanillo.ID, "recycling");
    private static final String TITLE = "gui.vanillo.category.recycling";
    private static final int inputSlot = 0;
    private static final int fuelSlot = 1;
    private static final int outputSlot = 2;
    private final IDrawableStatic staticFlame;
    private final IDrawableAnimated animatedFlame;
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable arrow;

    public RecyclingRecipeCategory(IGuiHelper helper) {
        ResourceLocation texture = new ResourceLocation("jei", "textures/gui/gui_vanilla.png");

        staticFlame = helper.createDrawable(texture, 82, 114, 14, 14);
        animatedFlame = helper.createAnimatedDrawable(this.staticFlame, 300, IDrawableAnimated.StartDirection.TOP, true);

        background = helper.createDrawable(texture, 0, 114, 82, 54);
        icon = helper.createDrawableIngredient(new ItemStack(VBlocks.RECYCLER.get()));
        arrow = helper.drawableBuilder(texture, 82, 128, 24, 17).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
    }



    @Override
    public ResourceLocation getUid() {
        return ID;
    }

    @Override
    public Class<? extends RecyclingRecipe> getRecipeClass() {
        return RecyclingRecipe.class;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setIngredients(RecyclingRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void draw(RecyclingRecipe recipe, MatrixStack stack, double mouseX, double mouseY) {
        animatedFlame.draw(stack, 1, 20);
        arrow.draw(stack, 24, 18);
        float experience = recipe.getExperience();

        if (experience > 0.0F) {
            String xp = I18n.get("gui.jei.category.smelting.experience", new Object[]{experience});
            FontRenderer font = Minecraft.getInstance().font;
            int width = font.width(xp);
            font.draw(stack, xp, (float)(this.background.getWidth() - width), 0.0F, -8355712);
        }
    }

    @Override
    public String getTitle() {
        return I18n.get(TITLE);
    }

    @Override
    public void setRecipe(IRecipeLayout layout, RecyclingRecipe recyclingRecipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = layout.getItemStacks();
        guiItemStacks.init(0, true, 0, 0);
        guiItemStacks.init(2, false, 60, 18);
        guiItemStacks.set(ingredients);
    }
}
