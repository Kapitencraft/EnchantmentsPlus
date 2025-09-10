package net.kapitencraft.enchantments_plus.loot_table.modifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.kapitencraft.enchantments_plus.enchantments.tools.ChromaticEnchantment;
import net.kapitencraft.enchantments_plus.registry.ModEnchantments;
import net.kapitencraft.kap_lib.helpers.LootTableHelper;
import net.kapitencraft.kap_lib.item.loot_table.IConditional;
import net.kapitencraft.kap_lib.item.loot_table.modifiers.ModLootModifier;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ChromaticModifier extends ModLootModifier implements IConditional {
    public static final MapCodec<ChromaticModifier> CODEC = LootTableHelper.simpleCodec(ChromaticModifier::new);

    public ChromaticModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> items, LootContext lootContext) {
        LivingEntity living = LootTableHelper.getLivingSource(lootContext);
        if (living != null) {
            int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.CHROMATIC, living);
            if (level > 0) {
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).is(ItemTags.WOOL)) {
                        Optional<Holder<Item>> wool = BuiltInRegistries.ITEM.getRandomElementOf(ItemTags.WOOL, lootContext.getRandom());
                        if (wool.isPresent()) {
                            items.set(i, new ItemStack(wool.get().value(), items.get(i).getCount()));
                        }
                    }
                }
            }
        }
        return items;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
