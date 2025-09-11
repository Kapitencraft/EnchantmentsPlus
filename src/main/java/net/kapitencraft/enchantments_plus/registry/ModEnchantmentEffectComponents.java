package net.kapitencraft.enchantments_plus.registry;

import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Unit;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.xml.crypto.Data;
import java.util.function.UnaryOperator;

public interface ModEnchantmentEffectComponents {
    DeferredRegister<DataComponentType<?>> REGISTRY = DeferredRegister.create(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, EnchantmentsPlusMod.MOD_ID);

    DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> BREATH_BLOCK = registerUnit("breath_block");
    DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> ENDER_FRIEND = registerUnit("ender_friend");
    DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> ENLIGHTENMENT = registerUnit("enlightenment");
    DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> INSOMNIA = registerUnit("insomnia");
    DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> SLEEPY = registerUnit("sleepy");
    DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> CHROMATIC = registerUnit("chromatic");
    DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> DELICATE = registerUnit("delicate");
    DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> MAGMATIC = registerUnit("magmatic");
    DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> REPLENISH = registerUnit("replenish");
    DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> SILENT_HARVEST = registerUnit("silent_harvest");
    DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> SMELTING_TOUCH = registerUnit("smelting_touch");

    private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> operator) {
        return REGISTRY.register(name, () -> operator.apply(DataComponentType.builder()).build());
    }

    private static DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> registerUnit(String name) {
        return register(name, unitBuilder -> unitBuilder.persistent(Unit.CODEC));
    }
}
