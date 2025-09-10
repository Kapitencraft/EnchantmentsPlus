package net.kapitencraft.enchantments_plus;

import com.mojang.logging.LogUtils;
import net.kapitencraft.enchantments_plus.registry.ModBlocks;
import net.kapitencraft.enchantments_plus.registry.ModEnchantments;
import net.kapitencraft.enchantments_plus.registry.ModLootTableModifiers;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLJavaModLanguageProvider;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(EnchantmentsPlusMod.MOD_ID)
public class EnchantmentsPlusMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "enchantments_plus";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public EnchantmentsPlusMod(IEventBus modEventBus)
    {

        ModBlocks.REGISTRY.register(modEventBus);
        ModEnchantments.REGISTRY.register(modEventBus);
        ModLootTableModifiers.REGISTRY.register(modEventBus);

        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public static ResourceLocation res(String val) {
        return new ResourceLocation(MOD_ID, val);
    }
}