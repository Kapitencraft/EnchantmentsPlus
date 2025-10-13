package net.kapitencraft.enchantments_plus.enchantments.components;

import com.mojang.serialization.MapCodec;
import net.kapitencraft.kap_lib.enchantments.abstracts.EnchantmentCountEffect;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class FirstStrike implements EnchantmentCountEffect {
    public static final MapCodec<FirstStrike> CODEC = MapCodec.unit(FirstStrike::new);

    @Override
    public CountType countType() {
        return CountType.ONCE;
    }

    @Override
    public int getCountAmount(int i) {
        return 1;
    }

    @Override
    public float mainExecute(int level, ItemStack enchanted, LivingEntity attacker, LivingEntity attacked, float damageAmount, int curHit, DamageSource source, float attackStrenghtScale) {
        return damageAmount * (1 + level * .25f);
    }

    @Override
    public MapCodec<? extends EnchantmentCountEffect> codec() {
        return CODEC;
    }
}
