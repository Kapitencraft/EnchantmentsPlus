package net.kapitencraft.enchantments_plus.enchantments.tools;

import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class SmeltingTouchEnchantment extends Enchantment {
    public SmeltingTouchEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.BREAKABLE, MiscHelper.WEAPON_SLOT);
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }
}
