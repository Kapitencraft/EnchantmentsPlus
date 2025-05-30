package net.kapitencraft.enchantments_plus.enchantments.armor;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class BlockBreatherEnchantment extends Enchantment {
    public BlockBreatherEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR_HEAD, new EquipmentSlot[] {EquipmentSlot.HEAD});
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }
}
