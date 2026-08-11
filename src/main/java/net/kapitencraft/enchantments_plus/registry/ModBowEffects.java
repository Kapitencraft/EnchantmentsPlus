package net.kapitencraft.enchantments_plus.registry;

import com.mojang.serialization.MapCodec;
import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.kapitencraft.enchantments_plus.enchantments.components.bow.Aim;
import net.kapitencraft.enchantments_plus.enchantments.components.bow.Snipe;
import net.kapitencraft.kap_lib.enchantment.EnchantmentEffectRegistries;
import net.kapitencraft.kap_lib.enchantment.abstracts.EnchantmentBowEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public interface ModBowEffects {
    DeferredRegister<MapCodec<? extends EnchantmentBowEffect>> REGISTRY = DeferredRegister.create(EnchantmentEffectRegistries.Keys.BOW, EnchantmentsPlusMod.MOD_ID);

    DeferredHolder<MapCodec<? extends EnchantmentBowEffect>, MapCodec<Snipe>> SNIPE = REGISTRY.register("snipe", () -> Snipe.CODEC);
    DeferredHolder<MapCodec<? extends EnchantmentBowEffect>, MapCodec<Aim>> AIM = REGISTRY.register("aim", () -> Aim.CODEC);
}
