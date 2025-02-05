package net.kapitencraft.enchantments_plus.data_gen;

import net.kapitencraft.enchantments_plus.registry.ModEnchantments;
import net.kapitencraft.kap_lib.KapLibMod;
import net.kapitencraft.kap_lib.data_gen.NbtSensitiveShapedRecipeBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        //ALL CRAFTS DISABLED!
        //create2IngredientEnchantmentCraft(pWriter, Items.NETHER_STAR, Items.BLAZE_ROD, ModEnchantments.INFERNO, 1);
        //create2IngredientEnchantmentCraft(pWriter, Items.COAL_BLOCK, Items.COAL, ModEnchantments.SMELTING_TOUCH, 1);
        //create2IngredientEnchantmentCraft(pWriter, Items.CHORUS_FLOWER, Items.NETHER_WART, ModEnchantments.REPLENISH, 1);
        //createEnchantmentCraft(pWriter, Items.FEATHER, ModEnchantments.DOUBLE_JUMP, 1);
        //create2IngredientEnchantmentCraft(pWriter, Items.GOLDEN_APPLE, Items.GOLDEN_CARROT, ModEnchantments.GLUTTONOUS, 1);
        //createEnchantmentCraft(pWriter, Items.ECHO_SHARD, Enchantments.SWIFT_SNEAK, 1);
    }

    private void createEnchantmentCraft(Consumer<FinishedRecipe> writer, Item ingredient, Enchantment enchantment, int level) {
        ItemStack result = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, level));
        NbtSensitiveShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .pattern("###")
                .pattern("#.#")
                .pattern("###")
                .define('#', ingredient)
                .define('.', Items.BOOK)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(writer, ForgeRegistries.ENCHANTMENTS.getKey(enchantment).withPrefix("craft_"));
    }

    private void createEnchantmentCraft(Consumer<FinishedRecipe> writer, Item ingredient, Supplier<Enchantment> enchSup, int level) {
        createEnchantmentCraft(writer, ingredient, enchSup.get(), level);
    }

    private void create2IngredientEnchantmentCraft(Consumer<FinishedRecipe> writer, Item ingredient1, Item ingredient2, Enchantment enchantment, int level) {
        ItemStack result = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, level));
        NbtSensitiveShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .pattern("#*#")
                .pattern("*.*")
                .pattern("#*#")
                .define('#', ingredient1)
                .define('*', ingredient2)
                .define('.', Items.BOOK)
                .unlockedBy(getHasName(ingredient1), has(ingredient1))
                .save(writer, ForgeRegistries.ENCHANTMENTS.getKey(enchantment).withPrefix("craft_"));

    }

    private void create2IngredientEnchantmentCraft(Consumer<FinishedRecipe> writer, Item ingredient1, Item ingredient2, Supplier<Enchantment> enchantment, int level) {
        create2IngredientEnchantmentCraft(writer, ingredient1, ingredient2, enchantment.get(), level);
    }
}
