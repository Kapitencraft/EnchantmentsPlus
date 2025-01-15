package net.kapitencraft.enchantments_plus.enchantments.weapon.melee;

import net.kapitencraft.kap_lib.enchantments.abstracts.ExtendedCalculationEnchantment;
import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class EnderSlayerEnchantment extends Enchantment implements ExtendedCalculationEnchantment {

    public EnderSlayerEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentCategory.WEAPON, MiscHelper.WEAPON_SLOT);
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
        return (attacked instanceof EnderMan || attacked instanceof Endermite) ? damage * (1 + level / 4f) : damage;
    }

    @Override
    public String[] getDescriptionMods(int level) {
        return new String[]{"+" + level * 25 + "%"};
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
