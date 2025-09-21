package net.kapitencraft.enchantments_plus.registry;

import com.mojang.serialization.MapCodec;
import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.kapitencraft.enchantments_plus.loot_table.modifier.*;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public interface ModLootTableModifiers {

    DeferredRegister<MapCodec<? extends IGlobalLootModifier>> REGISTRY = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, EnchantmentsPlusMod.MOD_ID);

    Supplier<MapCodec<TelekinesisModifier>> TELEKINESIS = REGISTRY.register("telekinesis", ()-> TelekinesisModifier.CODEC);
    Supplier<MapCodec<ReplenishModifier>> REPLENISH = REGISTRY.register("replenish", ()-> ReplenishModifier.CODEC);
    Supplier<MapCodec<SmeltModifier>> SMELT = REGISTRY.register("smelt", ()-> SmeltModifier.CODEC);
    Supplier<MapCodec<ScavengerModifier>> SCAVENGER = REGISTRY.register("scavenger", ()-> ScavengerModifier.CODEC);
    Supplier<MapCodec<CompactModifier>> COMPACT = REGISTRY.register("compact", () -> CompactModifier.CODEC);
    Supplier<MapCodec<ChromaticModifier>> CHROMATIC = REGISTRY.register("chromatic", () -> ChromaticModifier.CODEC);
}
