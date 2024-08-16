package net.kapitencraft.enchantments_plus.enchantments.armor;

import net.kapitencraft.kap_lib.enchantments.abstracts.ExtendedCalculationEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.IToolEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.ModEnchantmentCategories;
import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ThornyEnchantment extends ExtendedCalculationEnchantment implements IToolEnchantment {

    @Override
    public int getMaxLevel() {
        return 5;
    }

    public ThornyEnchantment() {
        super(Rarity.RARE, ModEnchantmentCategories.SHIELD, MiscHelper.WEAPON_SLOT, CalculationType.ALL, ProcessPriority.HIGHEST);
    }

    @Override
    public double execute(int level, ItemStack enchanted, LivingEntity attacker, LivingEntity attacked, double damage, DamageSource source) {
        attacker.hurt(attacked.damageSources().thorns(attacked), level);
        return damage;
    }


    @Override
    public String[] getDescriptionMods(int level) {
        return new String[] {""};
    }
}
