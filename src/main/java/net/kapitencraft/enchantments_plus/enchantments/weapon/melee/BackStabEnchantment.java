package net.kapitencraft.enchantments_plus.enchantments.weapon.melee;

import net.kapitencraft.kap_lib.enchantments.abstracts.ExtendedCalculationEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.IWeaponEnchantment;
import net.kapitencraft.kap_lib.helpers.MathHelper;
import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class BackStabEnchantment extends Enchantment implements ExtendedCalculationEnchantment, IWeaponEnchantment {
    public BackStabEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.WEAPON, MiscHelper.WEAPON_SLOT);
    }

    @Override
    public @NotNull CalculationType type() {
        return CalculationType.ONLY_MELEE;
    }

    @Override
    public @NotNull ProcessPriority priority() {
        return ProcessPriority.HIGHEST;
    }

    @Override
    public double execute(int level, ItemStack enchanted, LivingEntity attacker, LivingEntity attacked, double damage, DamageSource source) {
        if (MathHelper.isBehind(attacker, attacked)) {
            return damage * (1 + level * 0.25);
        }
        return damage;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }


    @Override
    public String[] getDescriptionMods(int level) {
        return new String[] {level*25 + "%"};
    }
}