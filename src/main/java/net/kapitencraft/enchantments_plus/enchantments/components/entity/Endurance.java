package net.kapitencraft.enchantments_plus.enchantments.components.entity;

import com.mojang.serialization.MapCodec;
import net.kapitencraft.enchantments_plus.registry.ModEnchantmentEntityEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public class Endurance implements EnchantmentEntityEffect {

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {
        if (entity instanceof LivingEntity living && entity.tickCount % 20 == 0 && living.getHealth() < living.getMaxHealth()) {
            living.heal(enchantmentLevel * .02f);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return ModEnchantmentEntityEffects.ENDURANCE.get();
    }
}
