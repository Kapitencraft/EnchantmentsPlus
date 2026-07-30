package net.kapitencraft.enchantments_plus;

import net.kapitencraft.enchantments_plus.registry.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.javafmlmod.FMLModContainer;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@Mod(EnchantmentsPlusMod.MOD_ID)
public class EnchantmentsPlusMod {
    public static final ResourceKey<LootTable> SCAVENGER_DROPS = ResourceKey.create(Registries.LOOT_TABLE, EnchantmentsPlusMod.res("scavenger_drops"));
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "enchantments_plus";

    public EnchantmentsPlusMod(IEventBus modEventBus, FMLModContainer container) {

        ModBlocks.REGISTRY.register(modEventBus);
        ModEnchantmentEntityEffects.REGISTRY.register(modEventBus);
        ModEnchantmentEffectComponents.REGISTRY.register(modEventBus);
        ModEnchantmentCountEffects.REGISTRY.register(modEventBus);
        ModBowEffects.REGISTRY.register(modEventBus);
        ModCooldowns.REGISTRY.register(modEventBus);
        ModLootTableModifiers.REGISTRY.register(modEventBus);

        container.registerConfig(ModConfig.Type.SERVER, ServerConfig.SPEC);

        NeoForge.EVENT_BUS.addListener(EnchantmentsPlusMod::onRegisterCommands);
    }

    public static ResourceLocation res(String val) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, val);
    }

    public static void onRegisterCommands(RegisterCommandsEvent event) {
        MaxEnchantCommand.register(event.getDispatcher());
    }
}