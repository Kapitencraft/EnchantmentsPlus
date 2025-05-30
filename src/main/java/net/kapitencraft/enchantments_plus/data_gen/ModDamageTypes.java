package net.kapitencraft.enchantments_plus.data_gen;

import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageType;

public interface ModDamageTypes {

    ResourceKey<DamageType> INFERNO = createKey("inferno");
    ResourceKey<DamageType> CHAIN_LIGHTNING = createKey("chain_lightning");

    private static ResourceKey<DamageType> createKey(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(EnchantmentsPlusMod.MOD_ID, name));
    }

    static void bootstrap(BootstapContext<DamageType> context) {
        context.register(INFERNO, new DamageType("inferno", 0.02f, DamageEffects.BURNING));
        context.register(CHAIN_LIGHTNING, new DamageType("chain_lightning", .1f));
    }
}
