package net.kapitencraft.enchantments_plus.enchantments.tools;

public class ReplenishEnchantment extends FarmingToolEnchantment {
    public ReplenishEnchantment() {
        super(Rarity.VERY_RARE);
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

}
