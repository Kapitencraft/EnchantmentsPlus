package net.kapitencraft.enchantments_plus.enchantments.weapon.ranged;

import net.kapitencraft.kap_lib.enchantments.abstracts.IWeaponEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.ModBowEnchantment;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class SnipeEnchantment extends Enchantment implements ModBowEnchantment, IWeaponEnchantment {

    public SnipeEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public CompoundTag write(CompoundTag tag, int level, ItemStack bow, LivingEntity owner, AbstractArrow arrow) {
        tag.putDouble("LaunchX", arrow.getX());
        tag.putDouble("LaunchY", arrow.getY());
        tag.putDouble("LaunchZ", arrow.getZ());
        return tag;
    }

    @Override
    public float execute(int level, @Nullable LivingEntity target, CompoundTag tag, ExePhase type, float oldDamage, AbstractArrow arrow) {
        if (type == ExePhase.HIT) {
            Vec3 start = new Vec3(tag.getDouble("LaunchX"), tag.getDouble("LaunchY"), tag.getDouble("LaunchZ"));
            Vec3 targetPos = arrow.position();
            double distance = start.distanceTo(targetPos);
            return (float) (oldDamage * (1 + (distance / 10) * 0.01 * level));
        }
        return oldDamage;
    }

    @Override
    public boolean shouldTick() {
        return false;
    }


    @Override
    public String[] getDescriptionMods(int level) {
        return new String[] {level + "%"};
    }
}
