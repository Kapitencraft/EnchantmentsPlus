package net.kapitencraft.enchantments_plus.enchantments.tools;

import net.kapitencraft.kap_lib.enchantments.abstracts.ExtraEnchantmentCategories;
import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class FarmingToolEnchantment extends Enchantment {
    protected FarmingToolEnchantment(Rarity p_44676_, EnchantmentCategory category) {
        super(p_44676_, category, MiscHelper.WEAPON_SLOT);
    }
}
