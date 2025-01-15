package net.kapitencraft.enchantments_plus.enchantments.weapon;

import net.kapitencraft.enchantments_plus.data_gen.ModDamageTypes;
import net.kapitencraft.enchantments_plus.registry.ModMobEffects;
import net.kapitencraft.kap_lib.client.particle.animation.core.ParticleAnimation;
import net.kapitencraft.kap_lib.client.particle.animation.finalizers.EmptyFinalizer;
import net.kapitencraft.kap_lib.client.particle.animation.spawners.RingSpawner;
import net.kapitencraft.kap_lib.client.particle.animation.terminators.EitherTerminator;
import net.kapitencraft.kap_lib.client.particle.animation.terminators.EntityRemovedTerminator;
import net.kapitencraft.kap_lib.client.particle.animation.terminators.TimedTerminator;
import net.kapitencraft.kap_lib.enchantments.abstracts.CountEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.ExtraEnchantmentCategories;
import net.kapitencraft.kap_lib.enchantments.abstracts.IUltimateEnchantment;
import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;

public class InfernoEnchantment extends Enchantment implements CountEnchantment, IUltimateEnchantment {
    public InfernoEnchantment() {
        super(Rarity.VERY_RARE, ExtraEnchantmentCategories.ALL_WEAPONS, MiscHelper.WEAPON_SLOT);
    }

    @Override
    public String mapName() {
        return "InfernoMap";
    }

    @Override
    public CountType countType() {
        return CountType.NORMAL;
    }

    @Override
    public int getCountAmount(int level) {
        return 10;
    }

    @Override
    public double mainExecute(int level, ItemStack enchanted, LivingEntity attacker, LivingEntity attacked, double damageAmount, int curTick, DamageSource source) {
        if (!source.getMsgId().equals("inferno")) {
            if (attacked.level() instanceof ServerLevel sL) {
                ParticleAnimation.builder()
                        .spawnTime(ParticleAnimation.SpawnTime.absolute(1))
                        .spawn(RingSpawner.entityWithBBSize(attacked, 1.5f, 1.2f)
                                .setParticle(ParticleTypes.DRIPPING_LAVA)
                                .heightPerTick(.02f)
                                .rotPerTick(3)
                        ).terminatedWhen(new EitherTerminator.Builder()
                                .addTerminator(TimedTerminator.ticks(400), EntityRemovedTerminator.builder(attacked))
                        )
                        .finalizes(EmptyFinalizer.builder())
                        .sendToAllPlayers(sL);

            }
            MiscHelper.increaseEffectDuration(attacked, ModMobEffects.STUN.get(), 80);
            attack(attacked, attacker, 0, (float) (damageAmount * (100 + level * 25) / 100));
        }
        return damageAmount;
    }

    private void attack(LivingEntity attacked, LivingEntity attacker, int tick, float damage) {
        MiscHelper.schedule(20, () -> {
            attacked.hurt(attacked.damageSources().source(ModDamageTypes.INFERNO, attacker), damage);
                if (tick < 5) {
                attack(attacked, attacker, tick + 1, damage);
            }
        });
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }


    @Override
    protected boolean checkCompatibility(@NotNull Enchantment ench) {
        return !(ench instanceof IUltimateEnchantment);
    }

    @Override
    public String[] getDescriptionMods(int level) {
        return new String[]{(100 + level * 25) + "%"};
    }

    @Override
    public @NotNull CalculationType type() {
        return CalculationType.ALL;
    }

    @Override
    public @NotNull ProcessPriority priority() {
        return ProcessPriority.LOWEST;
    }
}