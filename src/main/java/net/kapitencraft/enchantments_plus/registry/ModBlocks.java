package net.kapitencraft.enchantments_plus.registry;

import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.kapitencraft.enchantments_plus.block.FragileBasaltBlock;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public interface ModBlocks {
    DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(EnchantmentsPlusMod.MOD_ID);

    DeferredBlock<FragileBasaltBlock> FRAGILE_BASALT = REGISTRY.register("fragile_basalt", FragileBasaltBlock::new);
}
