package net.kapitencraft.enchantments_plus.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.kapitencraft.enchantments_plus.enchantments.tools.ChromaticEnchantment;
import net.kapitencraft.enchantments_plus.loot_table.modifier.*;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public interface ModLootTableModifiers {

    DeferredRegister<MapCodec<? extends IGlobalLootModifier>> REGISTRY = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, EnchantmentsPlusMod.MOD_ID);

    RegistryObject<Codec<TelekinesisModifier>> TELEKINESIS = REGISTRY.register("telekinesis", ()-> TelekinesisModifier.CODEC);
    RegistryObject<Codec<ReplenishModifier>> REPLENISH = REGISTRY.register("replenish", ()-> ReplenishModifier.CODEC);
    RegistryObject<Codec<SmeltModifier>> SMELT = REGISTRY.register("smelt", ()-> SmeltModifier.CODEC);
    RegistryObject<Codec<ScavengerModifier>> SCAVENGER = REGISTRY.register("scavenger", ()-> ScavengerModifier.CODEC);
    RegistryObject<Codec<CompactModifier>> COMPACT = REGISTRY.register("compact", () -> CompactModifier.CODEC);
    RegistryObject<Codec<ChromaticModifier>> CHROMATIC = REGISTRY.register("chromatic", () -> ChromaticModifier.CODEC);
}
