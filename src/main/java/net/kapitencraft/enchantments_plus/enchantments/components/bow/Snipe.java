package net.kapitencraft.enchantments_plus.enchantments.components.bow;

import com.mojang.serialization.MapCodec;
import net.kapitencraft.kap_lib.enchantment.abstracts.EnchantmentBowEffect;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.jetbrains.annotations.Nullable;

public class Snipe implements EnchantmentBowEffect {
    public static final MapCodec<Snipe> CODEC = MapCodec.unit(Snipe::new);

    @Override
    public void write(CompoundTag tag, int level, ItemStack bow, LivingEntity owner, AbstractArrow arrow) {
        tag.putDouble("LaunchX", arrow.getX());
        tag.putDouble("LaunchY", arrow.getY());
        tag.putDouble("LaunchZ", arrow.getZ());
    }

    @Override
    public void execute(int level, @Nullable LivingEntity target, CompoundTag tag, ExePhase type, MutableFloat oldDamage, AbstractArrow arrow) {
        if (type == ExePhase.HIT) {
            Vec3 start = new Vec3(tag.getDouble("LaunchX"), tag.getDouble("LaunchY"), tag.getDouble("LaunchZ"));
            Vec3 targetPos = arrow.position();
            double distance = start.distanceTo(targetPos);
            oldDamage.setValue(oldDamage.getValue() * (1 + distance * 0.001 * level));
        }
    }

    @Override
    public boolean shouldTick() {
        return false;
    }

    @Override
    public MapCodec<? extends EnchantmentBowEffect> codec() {
        return CODEC;
    }
}
