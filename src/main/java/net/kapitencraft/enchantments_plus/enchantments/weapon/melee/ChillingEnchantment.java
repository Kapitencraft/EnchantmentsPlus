package net.kapitencraft.enchantments_plus.enchantments.weapon.melee;

import net.kapitencraft.kap_lib.KapLibMod;
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

public class ChillingEnchantment extends Enchantment implements ExtendedCalculationEnchantment, IWeaponEnchantment {
    public ChillingEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.WEAPON, MiscHelper.WEAPON_SLOT);
    }


    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public @NotNull CalculationType type() {
        return CalculationType.ONLY_MELEE;
    }

    @Override
    public @NotNull ProcessPriority priority() {
        return ProcessPriority.LOWEST;
    }

    @Override
    public double execute(int level, ItemStack enchanted, LivingEntity attacker, LivingEntity attacked, double damage, DamageSource source) {
        int secondsRandom = KapLibMod.RANDOM_SOURCE.nextIntBetweenInclusive(1, level);
        MathHelper.add(attacked::getTicksFrozen, attacked::setTicksFrozen, secondsRandom * 20);
        return damage;
    }

    @Override
    public String[] getDescriptionMods(int level) {
        return new String[0];
    }
}
