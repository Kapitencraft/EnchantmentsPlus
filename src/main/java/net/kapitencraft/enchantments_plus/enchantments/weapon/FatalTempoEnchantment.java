package net.kapitencraft.enchantments_plus.enchantments.weapon;

import net.kapitencraft.kap_lib.enchantments.abstracts.ExtraEnchantmentCategories;
import net.kapitencraft.kap_lib.enchantments.abstracts.IUltimateEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.ModEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class FatalTempoEnchantment extends Enchantment implements ModEnchantment, IUltimateEnchantment {
    public FatalTempoEnchantment() {
        super(Rarity.VERY_RARE, ExtraEnchantmentCategories.ALL_WEAPONS, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public Object[] getDescriptionMods(int level) {
        return new Object[] {level * 10 + "%"};
    }
}
