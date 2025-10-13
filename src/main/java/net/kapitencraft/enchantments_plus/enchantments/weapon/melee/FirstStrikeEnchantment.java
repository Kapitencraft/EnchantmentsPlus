package net.kapitencraft.enchantments_plus.enchantments.weapon.melee;

import net.kapitencraft.kap_lib.enchantments.abstracts.CountEnchantment;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class FirstStrikeEnchantment extends Enchantment implements CountEnchantment {

    protected FirstStrikeEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public CountType countType() {
        return CountType.ONCE;
    }

    @Override
    public int getCountAmount(int i) {
        return 1;
    }

    @Override
    public float mainExecute(int level, ItemStack enchanted, LivingEntity attacker, LivingEntity attacked, float damageAmount, int curHit, DamageSource source, float attackStrenghtScale) {
        return damageAmount * (1 + level * .25f);
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
    public Object[] getDescriptionMods(int level) {
        return new Object[] {level * 25 + "%"};
    }
}
