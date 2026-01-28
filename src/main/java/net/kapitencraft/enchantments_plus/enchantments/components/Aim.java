package net.kapitencraft.enchantments_plus.enchantments.components;

import com.mojang.serialization.MapCodec;
import net.kapitencraft.kap_lib.core.helpers.MathHelper;
import net.kapitencraft.kap_lib.enchantment.abstracts.EnchantmentBowEffect;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;

public class Aim implements EnchantmentBowEffect {
    public static final MapCodec<Aim> CODEC = MapCodec.unit(Aim::new);

    @Override
    public void write(CompoundTag compoundTag, int i, ItemStack itemStack, LivingEntity livingEntity, AbstractArrow abstractArrow) {
    }

    @Override
    public void execute(int level, @Nullable LivingEntity target, CompoundTag tag, ExePhase type, MutableFloat oldDamage, AbstractArrow arrow) {
        if (!arrow.onGround() && type == ExePhase.TICK && !arrow.getDeltaMovement().equals(Vec3.ZERO)) {
            MathHelper.getLivingAround(arrow, level * 2).stream()
                    .filter(living -> arrow.getOwner() != living && !living.isDeadOrDying())
                    .sorted(Comparator.comparingDouble(value -> value.distanceTo(arrow)))
                    .findAny().ifPresent(
                            living -> {
                                Vec2 rotation = MathHelper.createTargetRotationFromPos(arrow.position(), living.getEyePosition());
                                Vec3 delta = arrow.getDeltaMovement();

                                arrow.setXRot(rotation.x);
                                arrow.setYRot(rotation.y);
                                arrow.setDeltaMovement(living.position().subtract(arrow.position()).normalize().scale(delta.length()));
                                arrow.hurtMarked = true;
                            }
                    );
        }
    }

    @Override
    public boolean shouldTick() {
        return true;
    }

    @Override
    public MapCodec<? extends EnchantmentBowEffect> codec() {
        return CODEC;
    }
}
