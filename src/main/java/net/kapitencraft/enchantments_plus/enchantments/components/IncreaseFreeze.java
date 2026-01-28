package net.kapitencraft.enchantments_plus.enchantments.components;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record IncreaseFreeze(LevelBasedValue value) implements EnchantmentEntityEffect {
    public static final MapCodec<IncreaseFreeze> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            LevelBasedValue.CODEC.fieldOf("amount").forGetter(IncreaseFreeze::value)
    ).apply(i, IncreaseFreeze::new));

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {
        entity.setTicksFrozen(entity.getTicksFrozen() + (int) value.calculate(enchantmentLevel));
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
