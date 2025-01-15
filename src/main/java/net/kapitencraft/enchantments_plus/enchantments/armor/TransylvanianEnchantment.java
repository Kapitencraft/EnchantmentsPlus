package net.kapitencraft.enchantments_plus.enchantments.armor;

import net.kapitencraft.kap_lib.enchantments.abstracts.ExtendedAbilityEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.IArmorEnchantment;
import net.kapitencraft.kap_lib.helpers.MathHelper;
import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import java.util.List;

public class TransylvanianEnchantment extends Enchantment implements ExtendedAbilityEnchantment, IArmorEnchantment {
    public TransylvanianEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR, MiscHelper.ARMOR_EQUIPMENT);
    }

    @Override
    public void onTick(LivingEntity source, int level) {
        List<LivingEntity> livings = MathHelper.getLivingAround(source, level * 1.5);
        livings = livings.stream().filter(living -> living.is(source)).toList();
        source.heal(livings.size() / 2f);
    }

    @Override
    public String[] getDescriptionMods(int level) {
        return new String[] {"" + level * 1.5};
    }
}
