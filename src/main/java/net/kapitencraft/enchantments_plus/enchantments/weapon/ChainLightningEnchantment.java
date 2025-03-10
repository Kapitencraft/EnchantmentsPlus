package net.kapitencraft.enchantments_plus.enchantments.weapon;

import net.kapitencraft.kap_lib.enchantments.abstracts.ExtendedCalculationEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.IUltimateEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.IWeaponEnchantment;
import net.kapitencraft.kap_lib.helpers.MathHelper;
import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class ChainLightningEnchantment extends Enchantment implements ExtendedCalculationEnchantment, IUltimateEnchantment, IWeaponEnchantment {
    public ChainLightningEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.WEAPON, MiscHelper.WEAPON_SLOT);
    }

    @Override
    public @NotNull CalculationType type() {
        return CalculationType.ALL;
    }

    @Override
    public @NotNull ProcessPriority priority() {
        return ProcessPriority.LOWEST;
    }

    @Override
    public double execute(int level, ItemStack enchanted, LivingEntity attacker, LivingEntity attacked, double damage, DamageSource source) {
        if (MathHelper.chance(level * 1., attacker)) {
            //ChainLightningHelper.spawnLightnings(level, attacked, attacker, (float) damage);
        }
        return damage;
    }

    //TODO visuals

    @Override
    public String[] getDescriptionMods(int level) {
        return new String[] {MathHelper.defRound(level * 10) + "%", (int) (Math.sqrt(level)) + "", level / 2 + "", 1 + level * 0.05 + "%"};
    }


    @Override
    protected boolean checkCompatibility(@NotNull Enchantment ench) {
        return !(ench instanceof IUltimateEnchantment);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
