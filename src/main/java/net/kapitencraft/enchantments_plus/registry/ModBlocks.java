package net.kapitencraft.enchantments_plus.registry;

import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.kapitencraft.enchantments_plus.block.FragileBasaltBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public interface ModBlocks {
    DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, EnchantmentsPlusMod.MOD_ID);

    RegistryObject<Block> FRAGILE_BASALT = REGISTRY.register("fragile_basalt", FragileBasaltBlock::new);
}
