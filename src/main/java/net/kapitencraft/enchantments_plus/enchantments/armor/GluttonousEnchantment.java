package net.kapitencraft.enchantments_plus.enchantments.armor;

import net.kapitencraft.kap_lib.enchantments.abstracts.IUltimateEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.ModEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class GluttonousEnchantment extends Enchantment implements ModEnchantment, IUltimateEnchantment {

    public GluttonousEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR_HEAD, new EquipmentSlot[]{EquipmentSlot.HEAD});
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public Object[] getDescriptionMods(int level) {
        return new Object[]{level * 10 + "%"};
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
