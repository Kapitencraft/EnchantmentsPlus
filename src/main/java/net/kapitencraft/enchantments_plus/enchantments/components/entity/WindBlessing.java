package net.kapitencraft.enchantments_plus.enchantments.components.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.kapitencraft.kap_lib.particle.animation.activation_triggers.EntityAddedTrigger;
import net.kapitencraft.kap_lib.particle.animation.core.ParticleAnimation;
import net.kapitencraft.kap_lib.particle.animation.finalizers.SetLifeTimeFinalizer;
import net.kapitencraft.kap_lib.particle.animation.spawners.RingSpawner;
import net.kapitencraft.kap_lib.particle.animation.target.pos.PositionTarget;
import net.kapitencraft.kap_lib.particle.animation.target.rot.RotationTarget;
import net.kapitencraft.kap_lib.particle.animation.terminators.EntityRemovedTerminatorTrigger;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

public class WindBlessing implements EnchantmentEntityEffect {
    public static final MapCodec<WindBlessing> CODEC = Codec.unit(new WindBlessing()).fieldOf("wind_blessing");

    @Override
    public void apply(@NotNull ServerLevel level, int enchantmentLevel, @NotNull EnchantedItemInUse item, Entity arrow, @NotNull Vec3 origin) {
        arrow.setNoGravity(true);
        if (arrow.level() instanceof ServerLevel) {
            ParticleAnimation.builder()
                    .terminatedWhen(EntityRemovedTerminatorTrigger.create(arrow))
                    .spawn(RingSpawner.noHeight()
                            .rotPerTick(10)
                            .setParticle(new DustParticleOptions(new Vector3f(1, 1, 1), .7f))
                            .setTarget(PositionTarget.entity(arrow))
                            .rotation(RotationTarget.forEntity(arrow))
                            .axis(Direction.Axis.Z)
                            .spawnCount(2)
                            .radius(.2f)
                    )
                    .finalizes(SetLifeTimeFinalizer.builder().lifeTime(40).resetAge())
                    .spawnTime(ParticleAnimation.SpawnTime.absolute(1))
                    .activatedOn(EntityAddedTrigger.forEntity(arrow))
                    .sendToAllPlayers();
        }
    }

    @Override
    public @NotNull MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
