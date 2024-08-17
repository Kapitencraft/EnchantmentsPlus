    package net.kapitencraft.enchantments_plus.enchantments.weapon;

import net.kapitencraft.kap_lib.enchantments.abstracts.IWeaponEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.ModEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.ModEnchantmentCategories;
import net.minecraft.world.item.enchantment.Enchantment;

public class ScavengerEnchantment extends Enchantment implements IWeaponEnchantment, ModEnchantment {
    public ScavengerEnchantment() {
        super(Rarity.RARE, ModEnchantmentCategories.ALL_WEAPONS, DEFAULT_SLOT);
    }

    @Override
    public String[] getDescriptionMods(int level) {
        return new String[] {level * 20 + "%"};
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
