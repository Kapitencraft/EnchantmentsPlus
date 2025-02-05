package net.kapitencraft.enchantments_plus.enchantments.weapon;

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

public class ArmorShreddingEnchantment extends Enchantment  implements StatBoostEnchantment, IWeaponEnchantment {
    public ArmorShreddingEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.WEAPON, DEFAULT_SLOT);
    }

    @Override
    public List<EquipmentSlot> slots() {
        return List.of(DEFAULT_SLOT);
    }

    @Override
    public Consumer<Multimap<Attribute, AttributeModifier>> getModifiers(int level, ItemStack enchanted, EquipmentSlot slot) {
        return multimap -> multimap.put(ExtraAttributes.ARMOR_SHREDDER.get(), AttributeHelper.createModifier("Armor Shredder Enchantment", AttributeModifier.Operation.ADDITION, level));
    }
    @Override
    public String[] getDescriptionMods(int level) {
        return new String[] {"+" + level};
    }

    @Override
    public int getMaxLevel() {
        return 7;
    }
}