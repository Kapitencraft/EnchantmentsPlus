package net.kapitencraft.enchantments_plus.enchantments;

import net.kapitencraft.kap_lib.enchantments.abstracts.SimpleToolEnchantment;

public class ExperiencedEnchantment extends SimpleToolEnchantment {
    public ExperiencedEnchantment() {
        super(Rarity.UNCOMMON);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
