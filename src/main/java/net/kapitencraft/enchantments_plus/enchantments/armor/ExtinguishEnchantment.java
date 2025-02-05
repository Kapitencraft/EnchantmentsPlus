package net.kapitencraft.enchantments_plus.enchantments.armor;

import net.kapitencraft.kap_lib.enchantments.abstracts.ModEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ExtinguishEnchantment extends Enchantment implements ModEnchantment {
    public static final int BASE_DAMAGE_REDUCTION = 10;

    public ExtinguishEnchantment() {
        super(Rarity.COMMON, EnchantmentCategory.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public Object[] getDescriptionMods(int level) {
        return new Object[] {(BASE_DAMAGE_REDUCTION + level * 15) + "%"};
    }
}
