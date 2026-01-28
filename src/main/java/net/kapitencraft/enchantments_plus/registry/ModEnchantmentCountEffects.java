package net.kapitencraft.enchantments_plus.registry;

import com.mojang.serialization.MapCodec;
import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.kapitencraft.enchantments_plus.enchantments.components.FirstStrike;
import net.kapitencraft.enchantments_plus.enchantments.components.Inferno;
import net.kapitencraft.enchantments_plus.enchantments.components.LightningLord;
import net.kapitencraft.enchantments_plus.enchantments.components.TripleStrike;
import net.kapitencraft.kap_lib.enchantment.EnchantmentEffectRegistries;
import net.kapitencraft.kap_lib.enchantment.abstracts.EnchantmentCountEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public interface ModEnchantmentCountEffects {
    DeferredRegister<MapCodec<? extends EnchantmentCountEffect>> REGISTRY = DeferredRegister.create(EnchantmentEffectRegistries.Keys.COUNT, EnchantmentsPlusMod.MOD_ID);

    DeferredHolder<MapCodec<? extends EnchantmentCountEffect>, MapCodec<LightningLord>> LIGHTNING_LORD = REGISTRY.register("lightning_lord", () -> LightningLord.CODEC);
    DeferredHolder<MapCodec<? extends EnchantmentCountEffect>, MapCodec<FirstStrike>> FIRST_STRIKE = REGISTRY.register("first_strike", () -> FirstStrike.CODEC);
    DeferredHolder<MapCodec<? extends EnchantmentCountEffect>, MapCodec<TripleStrike>> TRIPLE_STRIKE = REGISTRY.register("triple_strike", () -> TripleStrike.CODEC);
    DeferredHolder<MapCodec<? extends EnchantmentCountEffect>, MapCodec<Inferno>> INFERNO = REGISTRY.register("inferno", () -> Inferno.CODEC);
}
