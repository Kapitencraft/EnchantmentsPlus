package net.kapitencraft.enchantments_plus.enchantments.components;

import com.mojang.serialization.MapCodec;
import net.kapitencraft.kap_lib.enchantment.abstracts.EnchantmentCountEffect;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class LightningLord implements EnchantmentCountEffect {
    public static final MapCodec<LightningLord> CODEC = MapCodec.unit(LightningLord::new);

    @Override
    public CountType countType() {
        return CountType.NORMAL;
    }

    @Override
    public int getCountAmount(int level) {
        return (int) (3 + level * 0.4);
    }

    @Override
    public float mainExecute(int level, ItemStack enchanted, LivingEntity attacker, LivingEntity attacked, float damageAmount, int curHit, DamageSource source, float attackStrenghtScale) {
        if (attackStrenghtScale == 1 && attacker.level() instanceof ServerLevel serverLevel) {
            LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(serverLevel);
            assert lightning != null;
            lightning.moveTo(Vec3.atBottomCenterOf(attacked.getOnPos()));
            lightning.setVisualOnly(true);
            serverLevel.addFreshEntity(lightning);
            damageAmount *= (1 + level * 0.1f);
        }
        return damageAmount;
    }

    @Override
    public MapCodec<? extends EnchantmentCountEffect> codec() {
        return CODEC;
    }
}
