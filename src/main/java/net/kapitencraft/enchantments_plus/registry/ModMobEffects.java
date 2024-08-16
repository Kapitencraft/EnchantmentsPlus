package net.kapitencraft.enchantments_plus.registry;

import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.kapitencraft.enchantments_plus.mob_effects.StunMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public interface ModMobEffects {
    DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, EnchantmentsPlusMod.MOD_ID);

    RegistryObject<MobEffect> STUN = REGISTRY.register("stun", StunMobEffect::new);

}
