package net.kapitencraft.enchantments_plus.registry;

import com.mojang.serialization.Codec;
import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.kapitencraft.enchantments_plus.loot_table.modifier.ReplenishModifier;
import net.kapitencraft.enchantments_plus.loot_table.modifier.ScavengerModifier;
import net.kapitencraft.enchantments_plus.loot_table.modifier.SmeltModifier;
import net.kapitencraft.enchantments_plus.loot_table.modifier.TelekinesisModifier;
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
}
