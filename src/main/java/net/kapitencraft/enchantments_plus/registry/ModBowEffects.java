package net.kapitencraft.enchantments_plus.registry;

import com.mojang.serialization.MapCodec;
import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.kapitencraft.enchantments_plus.enchantments.components.Aim;
import net.kapitencraft.enchantments_plus.enchantments.components.Snipe;
import net.kapitencraft.kap_lib.enchantments.abstracts.EnchantmentBowEffect;
import net.kapitencraft.kap_lib.registry.custom.core.ExtraRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public interface ModBowEffects {
    DeferredRegister<MapCodec<? extends EnchantmentBowEffect>> REGISTRY = DeferredRegister.create(ExtraRegistries.Keys.ENCHANTMENT_BOW_EFFECTS, EnchantmentsPlusMod.MOD_ID);

    DeferredHolder<MapCodec<? extends EnchantmentBowEffect>, MapCodec<Snipe>> SNIPE = REGISTRY.register("snipe", () -> Snipe.CODEC);
    DeferredHolder<MapCodec<? extends EnchantmentBowEffect>, MapCodec<Aim>> AIM = REGISTRY.register("aim", () -> Aim.CODEC);
}
