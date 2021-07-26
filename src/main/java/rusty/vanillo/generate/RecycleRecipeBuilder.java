package rusty.vanillo.generate;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import rusty.vanillo.registry.VRecipeSerializers;

import javax.annotation.Nullable;
import java.util.function.Consumer;

/**
 * Inspired by the {@link net.minecraft.data.recipes.SimpleCookingRecipeBuilder} if you're wondering
 *
 * @author TheDarkColour
 */
public class RecycleRecipeBuilder {
    private final ItemStack result;
    private final Ingredient input;
    private final float xp; // experience rewards
    private final int duration; // ticks it takes to cook
    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    private String group; // idk what this does lol

    public RecycleRecipeBuilder(ItemStack result, Ingredient input, float xp, int duration, String group) {
        this.result = result;
        this.input = input;
        this.xp = xp;
        this.duration = duration;
        this.group = group;
    }

    public static RecycleRecipeBuilder recycling(Ingredient input, ItemStack result, float xp, int duration, String group) {
        return new RecycleRecipeBuilder(result, input, xp, duration, group);
    }

    public static RecycleRecipeBuilder recycling(Ingredient input, ItemStack result, float xp, int duration) {
        return recycling(input, result, xp, duration, "");
    }

    // todo check if this is used
    public static RecycleRecipeBuilder recycling(Ingredient input, ItemLike result, float xp, int duration) {
        return recycling(input, new ItemStack(result), xp, duration, "");
    }

    public RecycleRecipeBuilder unlockedBy(String criterionName, CriterionTriggerInstance criterion) {
        this.advancement.addCriterion(criterionName, criterion);
        return this;
    }

    public void save(Consumer<FinishedRecipe> consumer) {
        save(consumer, result.getItem().getRegistryName().getPath());
    }

    public void save(Consumer<FinishedRecipe> consumer, String path) {
        ResourceLocation id = new ResourceLocation(path);

        save(consumer, id);
    }

    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        ensureValid(id);
        advancement.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        consumer.accept(new Result(id, group == null ? "" : group, input, result, xp, duration, advancement, new ResourceLocation(id.getNamespace(), "recipes/" + result.getItem().getItemCategory().getRecipeFolderName() + "/" + id.getPath())));
    }

    private void ensureValid(ResourceLocation p_218634_1_) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + p_218634_1_);
        }
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final String group;
        private final Ingredient input;
        private final ItemStack result;
        private final float xp;
        private final int duration;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, String group, Ingredient input, ItemStack result, float xp, int duration, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.group = group;
            this.input = input;
            this.result = result;
            this.xp = xp;
            this.duration = duration;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        public void serializeRecipeData(JsonObject object) {
            if (!group.isEmpty()) {
                object.addProperty("group", group);
            }

            JsonObject result = new JsonObject();
            result.addProperty("item", this.result.getItem().getRegistryName().toString());
            result.addProperty("count", this.result.getCount());

            object.add("ingredient", input.toJson());
            object.add("result", result);
            object.addProperty("experience", xp);
            object.addProperty("cookingtime", duration);
        }

        public RecipeSerializer<?> getType() {
            return VRecipeSerializers.RECYCLING.get();
        }

        public ResourceLocation getId() {
            return this.id;
        }

        @Nullable
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
