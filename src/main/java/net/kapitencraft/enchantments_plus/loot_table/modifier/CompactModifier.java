package net.kapitencraft.enchantments_plus.loot_table.modifier;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.kapitencraft.enchantments_plus.registry.ModEnchantments;
import net.kapitencraft.kap_lib.helpers.LootTableHelper;
import net.kapitencraft.kap_lib.item.Compacting;
import net.kapitencraft.kap_lib.item.loot_table.IConditional;
import net.kapitencraft.kap_lib.item.loot_table.modifiers.ModLootModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class CompactModifier extends ModLootModifier implements IConditional {
    public static final Codec<CompactModifier> CODEC = LootTableHelper.simpleCodec(CompactModifier::new);

    protected CompactModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @SuppressWarnings({"DataFlowIssue", "UnnecessaryLocalVariable"})
    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        LivingEntity living = LootTableHelper.getLivingSource(context);
        if (living != null && EnchantmentHelper.getEnchantmentLevel(ModEnchantments.COMPACTING.get(), living) > 0)
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
                            if (ItemStack.isSameItemSameTags(s, outputResult)) {
                                handled = true;
                                s.grow(outputResult.getCount());
                            }
                        }
                        if (!handled) generatedLoot.add(outputResult);
                    }
                }
            }
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
