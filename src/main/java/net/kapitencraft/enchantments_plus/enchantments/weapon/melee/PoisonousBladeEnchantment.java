package net.kapitencraft.enchantments_plus.enchantments.weapon.melee;

import net.kapitencraft.kap_lib.enchantments.abstracts.EffectApplicationEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.IWeaponEnchantment;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class PoisonousBladeEnchantment extends Enchantment implements EffectApplicationEnchantment, IWeaponEnchantment {
    public PoisonousBladeEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }


    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public MobEffect getEffect() {
        return MobEffects.POISON;
    }

    @Override
    public int getChance(int level) {
        return 100;
    }

    @Override
    public int getScale() {
        return 5;
    }

    @Override
    public @NotNull CalculationType type() {
        return CalculationType.ONLY_MELEE;
    }
}
