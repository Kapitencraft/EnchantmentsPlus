package net.kapitencraft.enchantments_plus.util;

import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.kapitencraft.kap_lib.cooldown.Cooldown;
import net.kapitencraft.kap_lib.registry.custom.core.ExtraRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public interface ModCooldowns {
    DeferredRegister<Cooldown> REGISTRY = DeferredRegister.create(ExtraRegistries.Keys.COOLDOWNS, EnchantmentsPlusMod.MOD_ID);

    RegistryObject<Cooldown> BONK = REGISTRY.register("bonk", () -> new Cooldown(1200, l -> {}));
}
