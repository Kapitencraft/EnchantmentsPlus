package net.kapitencraft.enchantments_plus.enchantments.components.count;

import com.mojang.serialization.MapCodec;
import net.kapitencraft.enchantments_plus.data_gen.ModDamageTypes;
import net.kapitencraft.kap_lib.core.helpers.MiscHelper;
import net.kapitencraft.kap_lib.enchantment.abstracts.EnchantmentCountEffect;
import net.kapitencraft.kap_lib.mob_effect.registry.ExtraMobEffects;
import net.kapitencraft.kap_lib.particle.animation.core.ParticleAnimation;
import net.kapitencraft.kap_lib.particle.animation.finalizers.EmptyFinalizer;
import net.kapitencraft.kap_lib.particle.animation.spawners.RingSpawner;
import net.kapitencraft.kap_lib.particle.animation.terminators.EntityRemovedTerminatorTrigger;
import net.kapitencraft.kap_lib.particle.animation.terminators.TimedTerminator;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

public class Inferno implements EnchantmentCountEffect {
    public static final MapCodec<Inferno> CODEC = MapCodec.unit(Inferno::new);

    @Override
    public CountType countType() {
        return CountType.NORMAL;
    }

    @Override
    public int getCountAmount(int level) {
        return 10;
    }

    @Override
    public float mainExecute(int level, ItemStack enchanted, LivingEntity attacker, LivingEntity attacked, float damageAmount, int curTick, DamageSource source, float attackDamageScale) {
        if (attackDamageScale > .9 && !source.getMsgId().equals("inferno")) {
            Level world = attacker.level();
            world.getProfiler().push("inferno enchantment");
            int extinguishLevel = EnchantmentHelper.getEnchantmentLevel(world.registryAccess().holderOrThrow(net.kapitencraft.enchantments_plus.data_gen.ModEnchantments.EXTINGUISH), attacked);
            if (attacked.level() instanceof ServerLevel) {
                ParticleAnimation.builder()
                        .spawnTime(ParticleAnimation.SpawnTime.absolute(1))
                        .spawn(RingSpawner.entityWithBBSize(attacked, 1.5f, 1.2f)
                                .setParticle(ParticleTypes.DRIPPING_LAVA)
                                .heightPerTick(.02f)
                                .rotPerTick(3)
                        ).terminatedWhen(TimedTerminator.seconds(5))
                        .terminatedWhen(EntityRemovedTerminatorTrigger.create(attacked))
                        .finalizes(EmptyFinalizer.builder())
                        .sendToAllPlayers();

            }
            int extinguishDamageReduction = extinguishLevel == 0 ? 0 : 50 + extinguishLevel * 15;
            attack(attacked, attacker, damageAmount * (100 + level * 25 - extinguishDamageReduction) / 100);
            MiscHelper.maxEffectDuration(attacked, ExtraMobEffects.STUN, 100);
            world.getProfiler().pop();
        }
        return damageAmount;
    }

    private void attack(LivingEntity attacked, LivingEntity attacker, float damage) {
        new Object() {
            private int ticks = 0;

            public void start() {
                NeoForge.EVENT_BUS.register(this);
            }

            @SubscribeEvent
            public void tick(ServerTickEvent.Post event) {
                this.ticks += 1;
                if (this.ticks % 20 == 0) {
                    attacked.hurt(attacked.damageSources().source(ModDamageTypes.INFERNO, attacker), damage);
                }
                if (this.ticks >= 100)
                    end();
            }

            private void end() {
                NeoForge.EVENT_BUS.unregister(this);
            }
        }.start();
    }

    @Override
    public MapCodec<? extends EnchantmentCountEffect> codec() {
        return CODEC;
    }
}
