package net.kapitencraft.enchantments_plus.registry;

import com.mojang.serialization.Codec;
import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.kapitencraft.enchantments_plus.enchantments.tools.ChromaticEnchantment;
import net.kapitencraft.enchantments_plus.loot_table.modifier.*;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public interface ModLootTableModifiers {

    DeferredRegister<Codec<? extends IGlobalLootModifier>> REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, EnchantmentsPlusMod.MOD_ID);

    RegistryObject<Codec<TelekinesisModifier>> TELEKINESIS = REGISTRY.register("telekinesis", ()-> TelekinesisModifier.CODEC);
    RegistryObject<Codec<ReplenishModifier>> REPLENISH = REGISTRY.register("replenish", ()-> ReplenishModifier.CODEC);
    RegistryObject<Codec<SmeltModifier>> SMELT = REGISTRY.register("smelt", ()-> SmeltModifier.CODEC);
    RegistryObject<Codec<ScavengerModifier>> SCAVENGER = REGISTRY.register("scavenger", ()-> ScavengerModifier.CODEC);
    RegistryObject<Codec<CompactModifier>> COMPACT = REGISTRY.register("compact", () -> CompactModifier.CODEC);
    RegistryObject<Codec<ChromaticModifier>> CHROMATIC = REGISTRY.register("chromatic", () -> ChromaticModifier.CODEC);
}
