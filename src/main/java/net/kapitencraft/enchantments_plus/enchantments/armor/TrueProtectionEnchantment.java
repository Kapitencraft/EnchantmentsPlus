package net.kapitencraft.enchantments_plus.enchantments.armor;

import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class TrueProtectionEnchantment extends Enchantment {
    public TrueProtectionEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR, MiscHelper.ARMOR_EQUIPMENT);
    }

    @Override
    public int getDamageProtection(int level, @NotNull DamageSource source) {
        return source.is(DamageTypeTags.BYPASSES_ARMOR) ? level : 0;
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }
}
