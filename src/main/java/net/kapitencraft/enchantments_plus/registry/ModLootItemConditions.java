package net.kapitencraft.enchantments_plus.registry;

import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.kapitencraft.enchantments_plus.loot_table.condition.AttackerEmptyOffhandCondition;
import net.kapitencraft.enchantments_plus.loot_table.condition.BehindEntityCondition;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.neoforge.registries.DeferredRegister;

public interface ModLootItemConditions {
    DeferredRegister<LootItemConditionType> REGISTRY = DeferredRegister.create(Registries.LOOT_CONDITION_TYPE, EnchantmentsPlusMod.MOD_ID);

    Holder<LootItemConditionType> BEHIND = REGISTRY.register("behind", () -> new LootItemConditionType(BehindEntityCondition.CODEC));
    Holder<LootItemConditionType> OFFHAND_EMPTY = REGISTRY.register("offhand_empty", () -> new LootItemConditionType(AttackerEmptyOffhandCondition.CODEC));
}