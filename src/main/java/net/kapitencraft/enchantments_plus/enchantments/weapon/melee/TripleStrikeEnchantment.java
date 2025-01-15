package net.kapitencraft.enchantments_plus.enchantments.weapon.melee;

import net.kapitencraft.kap_lib.enchantments.abstracts.CountEnchantment;
import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class TripleStrikeEnchantment extends Enchantment implements CountEnchantment {
    public TripleStrikeEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentCategory.WEAPON, MiscHelper.WEAPON_SLOT);
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public int getMinCost(int level) {
        return 5 + level * 20;
    }

    @Override
    public int getMaxCost(int p_44691_) {
        return this.getMinCost(p_44691_) * 2;
    }

    @Override
    public String mapName() {
        return "TripleStrikeMap";
    }

    @Override
    public CountType countType() {
        return CountType.EXCEPT;
    }

    @Override
    public int getCountAmount(int level) {
        return 3;
    }

    @Override
    public double mainExecute(int level, ItemStack enchanted, LivingEntity attacker, LivingEntity attacked, double damageAmount, int curTick, DamageSource source) {
        damageAmount *= (1 + 0.2 * level);
        return damageAmount;
    }


    @Override
    public String[] getDescriptionMods(int level) {
        return new String[] {level*20 + "%"};
    }

    @Override
    public @NotNull CalculationType type() {
        return CalculationType.ALL;
    }

    @Override
    public @NotNull ProcessPriority priority() {
        return ProcessPriority.HIGHEST;
    }
}
