package net.kapitencraft.enchantments_plus.loot_table.modifier;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.kapitencraft.kap_lib.helpers.LootTableHelper;
import net.kapitencraft.kap_lib.item.loot_table.IConditional;
import net.kapitencraft.kap_lib.item.loot_table.LootContextReader;
import net.kapitencraft.kap_lib.item.loot_table.modifiers.ModLootModifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import org.jetbrains.annotations.NotNull;

public class ReplenishModifier extends ModLootModifier implements IConditional {
    public static final Codec<ReplenishModifier> CODEC = LootTableHelper.simpleCodec(ReplenishModifier::new);
    protected ReplenishModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        LootContextReader.simple(context, BlockState.class, LootContextParams.BLOCK_STATE).ifPresent(state -> {
            if (state.is(BlockTags.CROPS)) {
                Item item = state.getBlock().asItem();
                for (ItemStack stack : generatedLoot) {
                    if (stack.getItem() == item) {
                        stack.shrink(1);
                        break;
                    }
                }
            }
        });
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
