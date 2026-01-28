package net.kapitencraft.enchantments_plus.loot_table.modifier;

import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.kapitencraft.kap_lib.loot.IConditional;
import net.kapitencraft.kap_lib.loot.modifiers.ModLootModifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import org.jetbrains.annotations.NotNull;

public class ReplenishModifier extends ModLootModifier implements IConditional {
    public static final MapCodec<ReplenishModifier> CODEC = IConditional.simpleCodec(ReplenishModifier::new);
    public ReplenishModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        BlockState state = context.getParamOrNull(LootContextParams.BLOCK_STATE);
        if (state != null && state.is(BlockTags.CROPS)) {
            context.getLevel().getProfiler().push("replenish modifier");
            Item item = state.getBlock().asItem();
            for (ItemStack stack : generatedLoot) {
                if (stack.getItem() == item) {
                    stack.shrink(1);
                    break;
                }
            }
            context.getLevel().getProfiler().pop();
        }
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
