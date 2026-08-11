package net.kapitencraft.enchantments_plus.registry;

import com.mojang.serialization.MapCodec;
import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.kapitencraft.enchantments_plus.enchantments.components.entity.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public interface ModEnchantmentEntityEffects {
    DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> REGISTRY = DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, EnchantmentsPlusMod.MOD_ID);

    DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<Endurance>> ENDURANCE = REGISTRY.register("endurance", () -> MapCodec.unit(Endurance::new));
    DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<ProtectiveCover>> PROTECTIVE_COVER = REGISTRY.register("protective_cover", () -> MapCodec.unit(ProtectiveCover::new));
    DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<Transylvanian>> TRANSYLVANIAN = REGISTRY.register("transylvanian", () -> MapCodec.unit(Transylvanian::new));
    DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<IncreaseFreeze>> INCREASE_FREEZE = REGISTRY.register("increase_freeze", () -> IncreaseFreeze.CODEC);
    DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<WindBlessing>> WIND_BLESSING = REGISTRY.register("wind_blessing", () -> WindBlessing.CODEC);
    DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<ActivateCooldown>> ACTIVATE_COOLDOWN = REGISTRY.register("activate_cooldown", () -> ActivateCooldown.CODEC);
    DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<ApplyTimedModifier>> TIMED_MODIFIER = REGISTRY.register("apply_timed_modifier", () -> ApplyTimedModifier.CODEC);
    DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<Starvation>> STARVATION = REGISTRY.register("starvation", () -> Starvation.CODEC);
}
