package net.kapitencraft.enchantments_plus.enchantments.weapon.melee;

import net.kapitencraft.kap_lib.enchantments.abstracts.ExtendedCalculationEnchantment;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class ExecuteEnchantment extends Enchantment implements ExtendedCalculationEnchantment {
    public ExecuteEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
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
    public float execute(int level, ItemStack enchanted, LivingEntity attacker, LivingEntity attacked, float damage, DamageSource source, float attackStrengthScale) {
        return damage * .01f * (1 - attacked.getHealth() / attacked.getMaxHealth());
    }

    @Override
    public Object[] getDescriptionMods(int level) {
        return new Object[] {level + "%"};
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
