package net.kapitencraft.enchantments_plus.enchantments.components.count;

import com.mojang.serialization.MapCodec;
import net.kapitencraft.kap_lib.enchantment.abstracts.EnchantmentCountEffect;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class TripleStrike implements EnchantmentCountEffect {
    public static final MapCodec<TripleStrike> CODEC = MapCodec.unit(TripleStrike::new);

    @Override
    public CountType countType() {
        return CountType.ONCE;
    }

    @Override
    public int getCountAmount(int level) {
        return 3;
    }

    @Override
    public float mainExecute(int level, ItemStack enchanted, LivingEntity attacker, LivingEntity attacked, float damageAmount, int curTick, DamageSource source, float attackStrenghtScale) {
        damageAmount *= (1 + 0.2f * level);
        return damageAmount;
    }

    @Override
    public MapCodec<? extends EnchantmentCountEffect> codec() {
        return CODEC;
    }
}
