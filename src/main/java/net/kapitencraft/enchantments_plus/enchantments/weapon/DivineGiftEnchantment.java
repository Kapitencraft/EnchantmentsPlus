package net.kapitencraft.enchantments_plus.enchantments.weapon;

import com.google.common.collect.Multimap;
import net.kapitencraft.kap_lib.enchantments.abstracts.IWeaponEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.StatBoostEnchantment;
import net.kapitencraft.kap_lib.helpers.AttributeHelper;
import net.kapitencraft.kap_lib.registry.ExtraAttributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import java.util.List;
import java.util.function.Consumer;

public class DivineGiftEnchantment extends Enchantment implements StatBoostEnchantment, IWeaponEnchantment {
    public DivineGiftEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.WEAPON, DEFAULT_SLOT);
    }

    @Override
    public List<EquipmentSlot> slots() {
        return List.of(DEFAULT_SLOT);
    }

    @Override
    public Consumer<Multimap<Attribute, AttributeModifier>> getModifiers(int level, ItemStack enchanted, EquipmentSlot slot) {
        return multimap -> multimap.put(Attributes.LUCK, AttributeHelper.createModifier("Divine Gift Enchantment", AttributeModifier.Operation.ADDITION, level * 2));
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
    public String[] getDescriptionMods(int level) {
        return new String[] {"+" + level*2};
    }
}
