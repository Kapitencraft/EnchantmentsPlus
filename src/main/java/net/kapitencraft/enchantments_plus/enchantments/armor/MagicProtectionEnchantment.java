package net.kapitencraft.enchantments_plus.enchantments.armor;

import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.kapitencraft.kap_lib.tags.ExtraTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class MagicProtectionEnchantment extends Enchantment {
    public MagicProtectionEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentCategory.ARMOR, MiscHelper.ARMOR_EQUIPMENT);
    }

    @Override
    public int getDamageProtection(int level, @NotNull DamageSource source) {
        return source.is(ExtraTags.DamageTypes.MAGIC) ? 2 * level : 0;
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }
}
