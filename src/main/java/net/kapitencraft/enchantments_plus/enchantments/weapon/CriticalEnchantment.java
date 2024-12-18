package net.kapitencraft.enchantments_plus.enchantments.weapon;

import com.google.common.collect.Multimap;
import net.kapitencraft.kap_lib.enchantments.abstracts.ExtraEnchantmentCategories;
import net.kapitencraft.kap_lib.enchantments.abstracts.IWeaponEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.StatBoostEnchantment;
import net.kapitencraft.kap_lib.helpers.AttributeHelper;
import net.kapitencraft.kap_lib.registry.ExtraAttributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

public class CriticalEnchantment extends StatBoostEnchantment implements IWeaponEnchantment {
    public CriticalEnchantment() {
        super(Rarity.COMMON, ExtraEnchantmentCategories.ALL_WEAPONS, DEFAULT_SLOT);
    }

    @Override
    public Consumer<Multimap<Attribute, AttributeModifier>> getModifiers(int level, ItemStack enchanted, EquipmentSlot slot) {
        return multimap -> multimap.put(ExtraAttributes.CRIT_DAMAGE.get(), AttributeHelper.createModifier("Critical Enchantment", AttributeModifier.Operation.ADDITION, level * 10));
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public String[] getDescriptionMods(int level) {
        return new String[] {"+" + (10*level)};
    }
}
