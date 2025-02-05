package net.kapitencraft.enchantments_plus.enchantments.tools;

import net.kapitencraft.enchantments_plus.enchantments.ModEnchantmentCategories;
import net.kapitencraft.kap_lib.enchantments.abstracts.IToolEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;

public class ChromaticEnchantment extends Enchantment implements IToolEnchantment {
    public static final Item[] WOOL = new Item[] {
            Items.WHITE_WOOL,
            Items.ORANGE_WOOL,
            Items.MAGENTA_WOOL,
            Items.LIGHT_BLUE_WOOL,
            Items.YELLOW_WOOL,
            Items.LIME_WOOL,
            Items.PINK_WOOL,
            Items.GRAY_WOOL,
            Items.LIGHT_GRAY_WOOL,
            Items.CYAN_WOOL,
            Items.PURPLE_WOOL,
            Items.BLUE_WOOL,
            Items.BROWN_WOOL,
            Items.GREEN_WOOL,
            Items.RED_WOOL,
            Items.BLACK_WOOL
    };

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
