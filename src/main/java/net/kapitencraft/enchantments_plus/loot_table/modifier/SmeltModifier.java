package net.kapitencraft.enchantments_plus.loot_table.modifier;

import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.kapitencraft.enchantments_plus.data_gen.ModEnchantments;
import net.kapitencraft.kap_lib.loot.IConditional;
import net.kapitencraft.kap_lib.loot.modifiers.ModLootModifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class SmeltModifier extends ModLootModifier implements IConditional {
    public static final MapCodec<SmeltModifier> CODEC = IConditional.simpleCodec(SmeltModifier::new);
    private LootContext context = null;

    public SmeltModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        Entity entity = context.getParamOrNull(LootContextParams.ATTACKING_ENTITY);
        if (entity == null) entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
        if (entity instanceof LivingEntity source && source.getMainHandItem().getEnchantmentLevel(source.registryAccess().holderOrThrow(ModEnchantments.SMELTING_TOUCH)) > 0) {
            context.getLevel().getProfiler().push("smelt modifier");
            this.context = context;
            generatedLoot = new ObjectArrayList<>(generatedLoot.stream().map(this::run).toList());
            this.context = null;
            context.getLevel().getProfiler().pop();
        }
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

    private ItemStack run(ItemStack unSmelt) {
        if (!unSmelt.isEmpty()) {
            Optional<RecipeHolder<SmeltingRecipe>> optional = context.getLevel().getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(unSmelt), context.getLevel());
            if (optional.isPresent()) {
                ItemStack itemstack = optional.get().value().getResultItem(context.getLevel().registryAccess());
                if (!itemstack.isEmpty()) {
                    ItemStack itemstack1 = itemstack.copy();
                    itemstack1.setCount(unSmelt.getCount() * itemstack.getCount()); //Forge: Support smelting returning multiple
                    return itemstack1;
                }
            }
        }
        return unSmelt;
    }

}
