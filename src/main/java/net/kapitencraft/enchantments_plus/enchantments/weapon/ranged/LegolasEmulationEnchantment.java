package net.kapitencraft.enchantments_plus.enchantments.weapon.ranged;

import net.kapitencraft.kap_lib.enchantments.abstracts.ModEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class LegolasEmulationEnchantment extends Enchantment implements ModEnchantment {
    public LegolasEmulationEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }


    @Override
    public String[] getDescriptionMods(int level) {
        return new String[] {"" + level};
    }
}
