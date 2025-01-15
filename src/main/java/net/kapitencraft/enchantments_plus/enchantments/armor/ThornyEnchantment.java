package net.kapitencraft.enchantments_plus.enchantments.armor;

import net.kapitencraft.kap_lib.enchantments.abstracts.ExtendedCalculationEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.ExtraEnchantmentCategories;
import net.kapitencraft.kap_lib.enchantments.abstracts.IToolEnchantment;
import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;

public class ThornyEnchantment extends Enchantment implements ExtendedCalculationEnchantment, IToolEnchantment {

    @Override
    public int getMaxLevel() {
        return 5;
    }

    public ThornyEnchantment() {
        super(Rarity.RARE, ExtraEnchantmentCategories.SHIELD, MiscHelper.WEAPON_SLOT);
    }

    @Override
    public @NotNull CalculationType type() {
        return CalculationType.ALL;
    }

    @Override
    public @NotNull ProcessPriority priority() {
        return ProcessPriority.HIGHEST;
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
