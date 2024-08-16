package net.kapitencraft.enchantments_plus.enchantments.tools;

import net.kapitencraft.kap_lib.enchantments.abstracts.IToolEnchantment;
import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class VeinMinerEnchantment extends Enchantment implements IToolEnchantment {
    public VeinMinerEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.DIGGER, MiscHelper.WEAPON_SLOT);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}