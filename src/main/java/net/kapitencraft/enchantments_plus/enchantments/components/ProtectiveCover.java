package net.kapitencraft.enchantments_plus.enchantments.components;

import com.mojang.serialization.MapCodec;
import net.kapitencraft.kap_lib.helpers.MathHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class ProtectiveCover implements EnchantmentEntityEffect {

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {
        entity.level().getProfiler().push("protective cover enchantment");
        List<Projectile> list = MathHelper.getEntitiesAround(Projectile.class, entity, enchantmentLevel * 1.5);
        list.forEach(p -> p.setDeltaMovement(new Vec3(0, 0, 0)));
        entity.level().getProfiler().pop();
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return null;
    }
}
