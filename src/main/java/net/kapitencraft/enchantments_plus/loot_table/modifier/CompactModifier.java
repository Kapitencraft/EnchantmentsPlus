package net.kapitencraft.enchantments_plus.loot_table.modifier;

import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.kapitencraft.enchantments_plus.data_gen.ModEnchantments;
import net.kapitencraft.kap_lib.item.misc.Compacting;
import net.kapitencraft.kap_lib.loot.IConditional;
import net.kapitencraft.kap_lib.loot.modifiers.ModLootModifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import org.jetbrains.annotations.NotNull;

public class CompactModifier extends ModLootModifier implements IConditional {
    public static final MapCodec<CompactModifier> CODEC = IConditional.simpleCodec(CompactModifier::new);

    public CompactModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @SuppressWarnings({"DataFlowIssue"})
    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        Entity entity = context.getParamOrNull(LootContextParams.ATTACKING_ENTITY);
        if (entity == null) entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
        if (entity instanceof LivingEntity living && EnchantmentHelper.getEnchantmentLevel(living.registryAccess().holderOrThrow(ModEnchantments.COMPACTING), living) > 0) {
            context.getLevel().getProfiler().push("compact modifier");
            for (int i = 0; i < generatedLoot.size(); i++) {
                ItemStack stack = generatedLoot.get(i);
                Compacting.Result compactResult = Compacting.tryCompact(stack.getItem(), context.getLevel());
                if (compactResult != Compacting.Result.EMPTY) {
                    int amount = compactResult.getCountReq();
                    ItemStack result = compactResult.result();
                    int resultSize = 0;
                    while (stack.getCount() >= amount) {
                        stack.shrink(amount);
                        resultSize += result.getCount();
                    }
                    if (resultSize > 0) {
                        ItemStack outputResult = result.copyWithCount(resultSize);
                        if (stack.isEmpty()) {
                            generatedLoot.remove(stack);
                        }
                        boolean handled = false;
                        for (ItemStack s : generatedLoot) {
                            if (ItemStack.isSameItemSameComponents(s, outputResult)) {
                                handled = true;
                                s.grow(outputResult.getCount());
                            }
                        }
                        if (!handled) generatedLoot.add(outputResult);
                    }
                }
                context.getLevel().getProfiler().pop();
            }
        }
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
