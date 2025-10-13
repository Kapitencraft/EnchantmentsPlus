package net.kapitencraft.enchantments_plus;

import com.mojang.logging.LogUtils;
import net.kapitencraft.enchantments_plus.registry.ModBlocks;
import net.kapitencraft.enchantments_plus.registry.ModEnchantments;
import net.kapitencraft.enchantments_plus.registry.ModLootTableModifiers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EnchantmentsPlusMod.MOD_ID)
public class EnchantmentsPlusMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "enchantments_plus";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public EnchantmentsPlusMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.REGISTRY.register(modEventBus);
        ModEnchantments.REGISTRY.register(modEventBus);
        ModLootTableModifiers.REGISTRY.register(modEventBus);

        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public static ResourceLocation res(String val) {
        return new ResourceLocation(MOD_ID, val);
    }
}