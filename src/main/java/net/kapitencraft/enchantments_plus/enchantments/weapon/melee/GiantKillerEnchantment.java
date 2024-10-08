package net.kapitencraft.enchantments_plus.enchantments.weapon.melee;

import net.kapitencraft.kap_lib.enchantments.abstracts.ExtendedCalculationEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.IWeaponEnchantment;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class GiantKillerEnchantment extends ExtendedCalculationEnchantment implements IWeaponEnchantment {

    public GiantKillerEnchantment() {
        super(Enchantment.Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}, CalculationType.ALL, ProcessPriority.HIGHEST);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public double execute(int level, ItemStack enchanted, LivingEntity attacker, LivingEntity attacked, double damage, DamageSource source) {
        double MoreHpPercent = attacked.getHealth() / attacker.getHealth();
        return  (float) (damage * (1 + Math.min(MoreHpPercent * level * 0.01, 0.5)));
    }


    @Override
    public String[] getDescriptionMods(int level) {
        return new String[] {level + "%", "50%"};
    }
}
