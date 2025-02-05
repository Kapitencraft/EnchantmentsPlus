package net.kapitencraft.enchantments_plus.registry;

import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.kapitencraft.enchantments_plus.enchantments.*;
import net.kapitencraft.enchantments_plus.enchantments.armor.*;
import net.kapitencraft.enchantments_plus.enchantments.tools.*;
import net.kapitencraft.enchantments_plus.enchantments.weapon.*;
import net.kapitencraft.enchantments_plus.enchantments.weapon.melee.*;
import net.kapitencraft.enchantments_plus.enchantments.weapon.ranged.*;
import java.util.function.Supplier;

public interface ModEnchantments {
    DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, EnchantmentsPlusMod.MOD_ID);

    private static RegistryObject<Enchantment> register(String name, Supplier<Enchantment> supplier) {
        return REGISTRY.register(name, supplier);
    }


    RegistryObject<Enchantment> FLASH = register("flash", FlashEnchantment::new);
    RegistryObject<Enchantment> BONK = register("bonk", BonkEnchantment::new);
    RegistryObject<Enchantment> EXPERIENCED = register("experienced", ExperiencedEnchantment::new);
    RegistryObject<Enchantment> SMELTING_TOUCH = register("smelting_touch", SmeltingTouchEnchantment::new);
    RegistryObject<Enchantment> DELICATE = register("delicate", DelicateEnchantment::new);
    RegistryObject<Enchantment> HEAVY_PLATING = register("heavy_plating", HeavyPlatingEnchantment::new);
    RegistryObject<Enchantment> SCAVENGER = register("scavenger", ScavengerEnchantment::new);
    RegistryObject<Enchantment> ENDER_SLAYER = register("ender_slayer", EnderSlayerEnchantment::new);
    //RegistryObject<Enchantment> CHAIN_LIGHTNING = register("chain_lightning", ChainLightningEnchantment::new);
    RegistryObject<Enchantment> COMBAT_KNOWLEDGE = register("combat_knowledge", CombatKnowledgeEnchantment::new);
    RegistryObject<Enchantment> TRANSYLVANIAN = register("transylvanian", TransylvanianEnchantment::new);
    RegistryObject<Enchantment> HEALTH_MENDING = register("health_mending", HealthMendingEnchantment::new);
    RegistryObject<Enchantment> TRUE_PROTECTION = register("true_protection", TrueProtectionEnchantment::new);
    RegistryObject<Enchantment> MAGIC_PROTECTION = register("magic_protection", MagicProtectionEnchantment::new);
    RegistryObject<Enchantment> ENDER_FRIEND = register("ender_friend", EnderFriendEnchantment::new);
    RegistryObject<Enchantment> OVERLOAD = register("overload", OverloadEnchantment::new);
    RegistryObject<Enchantment> ELVISH_MASTERY = register("elvish_mastery", ElvishMasteryEnchantment::new);
    RegistryObject<Enchantment> FAST_ARROWS = register("fast_arrows", FastArrowsEnchantment::new);
    RegistryObject<Enchantment> GIANT_KILLER = register("giant_killer", GiantKillerEnchantment::new);
    RegistryObject<Enchantment> AIM = register("aim", AimEnchantment::new);
    RegistryObject<Enchantment> FROZEN_ARROWS = register("frozen_arrows", FrozenArrowsEnchantment::new);
    RegistryObject<Enchantment> INFERNO = register("inferno", InfernoEnchantment::new);
    RegistryObject<Enchantment> EXTINGUISH = register("extinguish", ExtinguishEnchantment::new);
    RegistryObject<Enchantment> BASALT_WALKER = register("basalt_walker", BasaltWalkerEnchantment::new);
    RegistryObject<Enchantment> SNIPE = register("snipe", SnipeEnchantment::new);
    RegistryObject<Enchantment> REJUVENATE = register("rejuvenate", RejuvenateEnchantment::new);
    RegistryObject<Enchantment> GROWTH = register("growth", GrowthEnchantment::new);
    RegistryObject<Enchantment> DOUBLE_JUMP = register("double_jump", DoubleJumpEnchantment::new);
    RegistryObject<Enchantment> FIRM_STAND = register("firm_stand", FirmStandEnchantment::new);
    RegistryObject<Enchantment> PROTECTIVE_COVER = register("protective_cover", ProtectiveCoverEnchantment::new);
    RegistryObject<Enchantment> NECROTIC_TOUCH = register("necrotic_touch", NecroticTouchEnchantment::new);
    RegistryObject<Enchantment> THORNY = register("thorny", ThornyEnchantment::new);
    RegistryObject<Enchantment> LUMBERJACK = register("lumberjack", LumberjackEnchantment::new);
    RegistryObject<Enchantment> VEIN_MINER = register("vein_miner", VeinMinerEnchantment::new);
    RegistryObject<Enchantment> POISONOUS_BLADE = register("poisonous_blade", PoisonousBladeEnchantment::new);
    RegistryObject<Enchantment> CHILLING = register("chilling", ChillingEnchantment::new);
    RegistryObject<Enchantment> LIGHTNING_LORD = register("lightning_lord", LightningLordEnchantment::new);
    RegistryObject<Enchantment> TRIPLE_STRIKE = register("triple_strike", TripleStrikeEnchantment::new);
    RegistryObject<Enchantment> BACK_STAB = register("back_stab", BackStabEnchantment::new);
    RegistryObject<Enchantment> JUSTICE = register("justice", JusticeEnchantment::new);
    RegistryObject<Enchantment> VENOMOUS = register("venomous", VenomousEnchantment::new);
    RegistryObject<Enchantment> CRITICAL = register("critical", CriticalEnchantment::new);
    RegistryObject<Enchantment> DIVINE_GIFT = register("divine_gift", DivineGiftEnchantment::new);
    RegistryObject<Enchantment> ARMOR_SHREDDING = register("armor_shredding", ArmorShreddingEnchantment::new);
    RegistryObject<Enchantment> TELEKINESIS = register("telekinesis", TelekinesisEnchantment::new);
    RegistryObject<Enchantment> REPLENISH = register("replenish", ReplenishEnchantment::new);
    RegistryObject<Enchantment> WIND_BLESSING = register("wind_blessing", WindBlessingEnchantment::new);
    RegistryObject<Enchantment> ENLIGHTENMENT = register("enlightenment", EnlightenmentEnchantment::new);
    RegistryObject<Enchantment> GLUTTONOUS = register("gluttonous", GluttonousEnchantment::new);
    RegistryObject<Enchantment> COMPACTING = register("compacting", CompactingEnchantment::new);
    RegistryObject<Enchantment> BLOOD_THIRST = register("blood_thirst", BloodThirstEnchantment::new);
    RegistryObject<Enchantment> RESILIENCE = register("resilience", ResilienceEnchantment::new);
    RegistryObject<Enchantment> CHROMATIC = register("chromatic", ChromaticEnchantment::new);
}
