package net.kapitencraft.enchantments_plus.enchantments.tools;

import net.kapitencraft.enchantments_plus.enchantments.ModEnchantmentCategories;
import net.kapitencraft.kap_lib.enchantments.abstracts.IToolEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;

public class ChromaticEnchantment extends Enchantment implements IToolEnchantment {

    public ChromaticEnchantment() {
        super(Rarity.VERY_RARE, ModEnchantmentCategories.SHEARS, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public boolean isCurse() {
        return true;
    }
}
