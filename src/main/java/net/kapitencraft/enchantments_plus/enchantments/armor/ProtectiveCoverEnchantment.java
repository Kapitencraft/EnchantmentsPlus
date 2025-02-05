package net.kapitencraft.enchantments_plus.enchantments.armor;

import net.kapitencraft.kap_lib.enchantments.abstracts.ExtendedAbilityEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.IArmorEnchantment;
import net.kapitencraft.kap_lib.helpers.MathHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class ProtectiveCoverEnchantment extends Enchantment implements IArmorEnchantment, ExtendedAbilityEnchantment {
    public ProtectiveCoverEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentCategory.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
    }

    @Override
    public void onTick(LivingEntity livingEntity, int level) {
        livingEntity.level().getProfiler().push("protective cover enchantment");
        List<Projectile> list = MathHelper.getEntitiesAround(Projectile.class, livingEntity, level * 1.5);
        list.forEach(p -> p.setDeltaMovement(new Vec3(0, 0, 0)));
        livingEntity.level().getProfiler().pop();
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
