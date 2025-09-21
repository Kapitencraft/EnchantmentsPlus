package net.kapitencraft.enchantments_plus.loot_table.condition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.kapitencraft.enchantments_plus.registry.ModLootItemConditions;
import net.kapitencraft.kap_lib.helpers.MathHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class BehindEntityCondition implements LootItemCondition {
    public static final MapCodec<BehindEntityCondition> CODEC = Codec.unit(new BehindEntityCondition()).fieldOf("behind");

    @Override
    public LootItemConditionType getType() {
        return ModLootItemConditions.BEHIND.value();
    }

    @Override
    public boolean test(LootContext lootContext) {
        return lootContext.hasParam(LootContextParams.DIRECT_ATTACKING_ENTITY) && MathHelper.isBehind(lootContext.getParam(LootContextParams.DIRECT_ATTACKING_ENTITY), lootContext.getParam(LootContextParams.THIS_ENTITY));
    }
}
