package net.kapitencraft.enchantments_plus.registry;

import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.kapitencraft.kap_lib.cooldown.Cooldown;
import net.kapitencraft.kap_lib.cooldown.registry.CooldownRegistries;
import net.minecraft.core.Holder;
import net.neoforged.neoforge.registries.DeferredRegister;

public interface ModCooldowns {
    DeferredRegister<Cooldown> REGISTRY = DeferredRegister.create(CooldownRegistries.Keys.COOLDOWNS, EnchantmentsPlusMod.MOD_ID);

    Holder<Cooldown> BONK = REGISTRY.register("bonk", () -> new Cooldown(1200, living -> {}));
}