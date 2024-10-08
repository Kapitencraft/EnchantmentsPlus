package net.kapitencraft.enchantments_plus.enchantments.armor;

import net.kapitencraft.kap_lib.enchantments.abstracts.IArmorEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ProtectiveCoverEnchantment extends Enchantment implements IArmorEnchantment {
    public ProtectiveCoverEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentCategory.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
    }
}
