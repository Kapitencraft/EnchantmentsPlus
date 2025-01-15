package net.kapitencraft.enchantments_plus.enchantments.weapon.melee;

import net.kapitencraft.kap_lib.enchantments.abstracts.ModEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class BloodThirstEnchantment extends Enchantment implements ModEnchantment {
    public BloodThirstEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public Object[] getDescriptionMods(int level) {
        return new Object[] {level + "%", level * 2};
    }
}
