package net.kapitencraft.enchantments_plus.loot_table.modifier;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.kapitencraft.enchantments_plus.enchantments.tools.ChromaticEnchantment;
import net.kapitencraft.enchantments_plus.registry.ModEnchantments;
import net.kapitencraft.kap_lib.helpers.LootTableHelper;
import net.kapitencraft.kap_lib.item.loot_table.IConditional;
import net.kapitencraft.kap_lib.item.loot_table.modifiers.ModLootModifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import org.jetbrains.annotations.NotNull;

public class ChromaticModifier extends ModLootModifier implements IConditional {
    public static final Codec<ChromaticModifier> CODEC = LootTableHelper.simpleCodec(ChromaticModifier::new);

    public ChromaticModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> items, LootContext lootContext) {
        LivingEntity living = LootTableHelper.getLivingSource(lootContext);
        if (living != null) {
            int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.CHROMATIC.get(), living);
            if (level > 0) {
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).is(ItemTags.WOOL)) {
                        items.set(i, new ItemStack(ChromaticEnchantment.WOOL[Mth.nextInt(lootContext.getRandom(), 0, 15)]));
                    }
                }
            }
        }
        return items;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
