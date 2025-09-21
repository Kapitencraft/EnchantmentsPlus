package net.kapitencraft.enchantments_plus.loot_table.condition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.kapitencraft.enchantments_plus.registry.ModLootItemConditions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import org.checkerframework.checker.units.qual.A;

public class AttackerEmptyOffhandCondition implements LootItemCondition {
    public static final MapCodec<AttackerEmptyOffhandCondition> CODEC = Codec.unit(new AttackerEmptyOffhandCondition()).fieldOf("empty_offhand");

    @Override
    public LootItemConditionType getType() {
        return ModLootItemConditions.OFFHAND_EMPTY.value();
    }

    @Override
    public boolean test(LootContext lootContext) {
        return lootContext.hasParam(LootContextParams.DIRECT_ATTACKING_ENTITY) && lootContext.getParam(LootContextParams.DIRECT_ATTACKING_ENTITY) instanceof LivingEntity living && living.getOffhandItem().isEmpty();
    }
}
