package net.kapitencraft.enchantments_plus.enchantments.armor;

import net.kapitencraft.kap_lib.enchantments.abstracts.ModEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class EnlightenmentEnchantment extends Enchantment implements ModEnchantment {
    public EnlightenmentEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR_HEAD, new EquipmentSlot[] {EquipmentSlot.HEAD});
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public Object[] getDescriptionMods(int level) {
        return new Object[]{40 * level + "%", 20 * level + "%"};
    }
}
