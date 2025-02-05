package net.kapitencraft.enchantments_plus.enchantments.tools;

import net.kapitencraft.kap_lib.enchantments.abstracts.ExtraEnchantmentCategories;

public class ReplenishEnchantment extends FarmingToolEnchantment {
    public ReplenishEnchantment() {
        super(Rarity.VERY_RARE, ExtraEnchantmentCategories.HOE);
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

}
