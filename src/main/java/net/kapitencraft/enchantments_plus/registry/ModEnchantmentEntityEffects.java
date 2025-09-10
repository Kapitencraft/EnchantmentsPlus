package net.kapitencraft.enchantments_plus.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.kapitencraft.enchantments_plus.enchantments.components.Endurance;
import net.kapitencraft.enchantments_plus.enchantments.components.ProtectiveCover;
import net.kapitencraft.enchantments_plus.enchantments.components.Transylvanian;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public interface ModEnchantmentEntityEffects {
    DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> REGISTRY = DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, EnchantmentsPlusMod.MOD_ID);

    DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<Endurance>> ENDURANCE = REGISTRY.register("endurance", () -> Codec.unit(Endurance::new).fieldOf("endurance"));
    DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<ProtectiveCover>> PROTECTIVE_COVER = REGISTRY.register("protective_cover", () -> Codec.unit(ProtectiveCover::new).fieldOf("protective_cover"));
    DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<Transylvanian>> TRANSYLVANIAN = REGISTRY.register("transylvanian", () -> Codec.unit(Transylvanian::new).fieldOf("transylvanian"));
}
