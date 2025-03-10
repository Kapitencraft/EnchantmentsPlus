package net.kapitencraft.enchantments_plus.enchantments.weapon.ranged;

import com.google.common.collect.Multimap;
import net.kapitencraft.kap_lib.enchantments.abstracts.IWeaponEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.StatBoostEnchantment;
import net.kapitencraft.kap_lib.helpers.AttributeHelper;
import net.kapitencraft.kap_lib.registry.ExtraAttributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import java.util.List;
import java.util.function.Consumer;

public class ElvishMasteryEnchantment extends Enchantment implements StatBoostEnchantment, IWeaponEnchantment {
    public ElvishMasteryEnchantment() {
        super(Enchantment.Rarity.RARE, EnchantmentCategory.BOW, DEFAULT_SLOT);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public List<EquipmentSlot> slots() {
        return List.of(DEFAULT_SLOT);
    }

    @Override
    public Consumer<Multimap<Attribute, AttributeModifier>> getModifiers(int level, ItemStack enchanted, EquipmentSlot slot) {
        return multimap -> multimap.put(ExtraAttributes.DRAW_SPEED.get(), AttributeHelper.createModifier("Elvish Mastery Enchantment", AttributeModifier.Operation.ADDITION, 7.5*level));
    }

    @Override
    public String[] getDescriptionMods(int level) {
        return new String[] {"+" + level*7.5};
    }
}
