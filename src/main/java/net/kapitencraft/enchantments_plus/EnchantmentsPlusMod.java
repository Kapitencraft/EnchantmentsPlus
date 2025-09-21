package net.kapitencraft.enchantments_plus;

import com.mojang.logging.LogUtils;
import net.kapitencraft.enchantments_plus.registry.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.javafmlmod.FMLModContainer;
import org.slf4j.Logger;

@Mod(EnchantmentsPlusMod.MOD_ID)
public class EnchantmentsPlusMod
{
    public static final ResourceKey<LootTable> SCAVENGER_DROPS = ResourceKey.create(Registries.LOOT_TABLE, EnchantmentsPlusMod.res("scavenger_drops"));
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "enchantments_plus";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public EnchantmentsPlusMod(IEventBus modEventBus, FMLModContainer container)
    {

        ModBlocks.REGISTRY.register(modEventBus);
        ModEnchantmentEntityEffects.REGISTRY.register(modEventBus);
        ModEnchantmentEffectComponents.REGISTRY.register(modEventBus);
        ModEnchantmentCountEffects.REGISTRY.register(modEventBus);
        ModBowEffects.REGISTRY.register(modEventBus);
        ModCooldowns.REGISTRY.register(modEventBus);
        ModLootItemConditions.REGISTRY.register(modEventBus);
        ModLootTableModifiers.REGISTRY.register(modEventBus);

        container.registerConfig(ModConfig.Type.SERVER, ServerConfig.SPEC);
    }

    public static ResourceLocation res(String val) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, val);
    }
}