package net.kapitencraft.enchantments_plus.enchantments.armor;

import net.kapitencraft.kap_lib.enchantments.abstracts.ExtendedAbilityEnchantment;
import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class EnduranceEnchantment extends Enchantment implements ExtendedAbilityEnchantment {
    public EnduranceEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR, MiscHelper.ARMOR_EQUIPMENT);
    }

    @Override
    public void onTick(LivingEntity source, int level) {
        if (source.tickCount % 20 == 0 && source.getHealth() < source.getMaxHealth()) {
            source.heal(level * .02f);
        }
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public Object[] getDescriptionMods(int level) {
        return new Object[] {level * .02f};
    }
}
