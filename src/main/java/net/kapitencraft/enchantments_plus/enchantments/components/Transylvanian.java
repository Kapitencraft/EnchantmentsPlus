package net.kapitencraft.enchantments_plus.enchantments.components;

import com.mojang.serialization.MapCodec;
import net.kapitencraft.enchantments_plus.registry.ModEnchantmentEntityEffects;
import net.kapitencraft.kap_lib.core.helpers.MathHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class Transylvanian implements EnchantmentEntityEffect {
    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {
        if (!entity.level().isClientSide() && entity.tickCount % 20 == 0 && entity instanceof LivingEntity living) {
            List<LivingEntity> livings = MathHelper.getLivingAround(entity, enchantmentLevel * 1.5);
            livings = livings.stream().filter(living1 -> !living1.is(entity)).toList();
            living.heal(livings.size() / 2f);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return ModEnchantmentEntityEffects.TRANSYLVANIAN.get();
    }
}
