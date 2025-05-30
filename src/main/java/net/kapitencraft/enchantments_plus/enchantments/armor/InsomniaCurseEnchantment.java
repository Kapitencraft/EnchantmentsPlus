package net.kapitencraft.enchantments_plus.enchantments.armor;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class InsomniaCurseEnchantment extends Enchantment {
    public InsomniaCurseEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentCategory.ARMOR_HEAD, new EquipmentSlot[]{EquipmentSlot.HEAD});
    }

    @Override
    public boolean isCurse() {
        return true;
    }
}
