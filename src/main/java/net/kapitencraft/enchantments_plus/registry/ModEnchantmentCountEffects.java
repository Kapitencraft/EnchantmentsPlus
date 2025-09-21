package net.kapitencraft.enchantments_plus.registry;

import com.mojang.serialization.MapCodec;
import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.kapitencraft.enchantments_plus.enchantments.components.Inferno;
import net.kapitencraft.enchantments_plus.enchantments.components.LightningLord;
import net.kapitencraft.enchantments_plus.enchantments.components.TripleStrike;
import net.kapitencraft.kap_lib.enchantments.abstracts.EnchantmentCountEffect;
import net.kapitencraft.kap_lib.registry.custom.core.ExtraRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public interface ModEnchantmentCountEffects {
    DeferredRegister<MapCodec<? extends EnchantmentCountEffect>> REGISTRY = DeferredRegister.create(ExtraRegistries.ENCHANTMENT_COUNT_EFFECTS, EnchantmentsPlusMod.MOD_ID);

    DeferredHolder<MapCodec<? extends EnchantmentCountEffect>, MapCodec<LightningLord>> LIGHTNING_LORD = REGISTRY.register("lightning_lord", () -> LightningLord.CODEC);
    DeferredHolder<MapCodec<? extends EnchantmentCountEffect>, MapCodec<TripleStrike>> TRIPLE_STRIKE = REGISTRY.register("triple_strike", () -> TripleStrike.CODEC);
    DeferredHolder<MapCodec<? extends EnchantmentCountEffect>, MapCodec<Inferno>> INFERNO = REGISTRY.register("inferno", () -> Inferno.CODEC);
}
