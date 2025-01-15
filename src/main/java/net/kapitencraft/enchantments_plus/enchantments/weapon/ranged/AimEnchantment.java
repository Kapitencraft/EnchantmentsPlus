package net.kapitencraft.enchantments_plus.enchantments.weapon.ranged;

import net.kapitencraft.kap_lib.enchantments.abstracts.IWeaponEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.ModBowEnchantment;
import net.kapitencraft.kap_lib.helpers.MathHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;

public class AimEnchantment extends Enchantment implements ModBowEnchantment, IWeaponEnchantment {
    public AimEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public CompoundTag write(CompoundTag tag, int level, ItemStack bow, LivingEntity owner, AbstractArrow arrow) {
        return tag;
    }

    @Override
    public float execute(int level, @Nullable LivingEntity livingEntity, CompoundTag compoundTag, ExePhase phase, float oldDamage, AbstractArrow arrow) {
        if (phase == ExePhase.TICK) {
            MathHelper.getLivingAround(arrow, level * 2).stream()
                    .filter(living -> arrow.getOwner() != living && !living.isDeadOrDying())
                    .sorted(Comparator.comparingDouble(value -> value.distanceTo(arrow)))
                    .findAny().ifPresent(living -> arrow.setDeltaMovement(MathHelper.clampLength(living.position().subtract(arrow.position()), arrow.getDeltaMovement().length())));
        }
        return oldDamage;
    }

    @Override
    public boolean shouldTick() {
        return true;
    }


    @Override
    public Object[] getDescriptionMods(int level) {
        return new Object[] {2*level};
    }
}
