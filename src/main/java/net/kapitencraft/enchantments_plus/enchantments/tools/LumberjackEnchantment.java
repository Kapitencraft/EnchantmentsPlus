package net.kapitencraft.enchantments_plus.enchantments.tools;

import net.kapitencraft.enchantments_plus.enchantments.ModEnchantmentCategories;
import net.kapitencraft.kap_lib.enchantments.abstracts.IToolEnchantment;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class LumberjackEnchantment extends Enchantment implements IToolEnchantment {
    public LumberjackEnchantment() {
        super(Rarity.VERY_RARE, ModEnchantmentCategories.AXE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.is(ItemTags.AXES);
    }
}
