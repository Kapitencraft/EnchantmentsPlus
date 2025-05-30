package net.kapitencraft.enchantments_plus.enchantments.tools;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class MagmaticCurseEnchantment extends Enchantment {

    public MagmaticCurseEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public boolean isCurse() {
        return true;
    }
}
