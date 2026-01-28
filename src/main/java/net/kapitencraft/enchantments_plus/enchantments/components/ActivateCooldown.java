package net.kapitencraft.enchantments_plus.enchantments.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.kapitencraft.kap_lib.cooldown.Cooldown;
import net.kapitencraft.kap_lib.cooldown.registry.CooldownRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record ActivateCooldown(Cooldown cooldown, boolean reduceWithTime) implements EnchantmentEntityEffect {
    public static final MapCodec<ActivateCooldown> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            CooldownRegistries.COOLDOWNS.byNameCodec().fieldOf("cooldown").forGetter(ActivateCooldown::cooldown),
            Codec.BOOL.fieldOf("reduceWithTime").forGetter(ActivateCooldown::reduceWithTime)
    ).apply(i, ActivateCooldown::new));

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {
        if (entity instanceof LivingEntity living) {
            cooldown.applyCooldown(living, reduceWithTime);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
