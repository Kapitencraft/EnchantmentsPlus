package net.kapitencraft.enchantments_plus.enchantments.weapon;

import net.kapitencraft.kap_lib.enchantments.abstracts.ExtendedCalculationEnchantment;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class TwoHandedEnchantment extends Enchantment implements ExtendedCalculationEnchantment {
    public TwoHandedEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentCategory.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 3;
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
    public Object[] getDescriptionMods(int level) {
        return new Object[] {level * 20 + "%"};
    }

    @Override
    public float execute(int level, ItemStack enchanted, LivingEntity attacker, LivingEntity attacked, float damage, DamageSource source, float attackStrengthScale) {
        if (attacker.getOffhandItem().isEmpty()) return (1 + level * .2f) * damage;
        return damage;
    }
}
