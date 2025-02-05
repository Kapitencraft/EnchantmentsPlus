package net.kapitencraft.enchantments_plus.enchantments;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ModEnchantmentCategories {
    public static final EnchantmentCategory SHEARS = EnchantmentCategory.create("SHEARS", item -> item instanceof ShearsItem);
    public static final EnchantmentCategory ARMOR_MOTION = EnchantmentCategory.create("ARMOR_MOTION", item -> EnchantmentCategory.ARMOR_FEET.canEnchant(item) || EnchantmentCategory.ARMOR_LEGS.canEnchant(item));
    public static final EnchantmentCategory AXE = EnchantmentCategory.create("AXE", item -> item instanceof AxeItem);
}
