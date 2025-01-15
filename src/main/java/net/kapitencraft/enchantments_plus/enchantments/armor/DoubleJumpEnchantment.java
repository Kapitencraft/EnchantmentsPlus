package net.kapitencraft.enchantments_plus.enchantments.armor;

import com.google.common.collect.Multimap;
import net.kapitencraft.kap_lib.enchantments.abstracts.ArmorStatBoostEnchantment;
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

public class DoubleJumpEnchantment extends Enchantment implements ArmorStatBoostEnchantment {
    public DoubleJumpEnchantment() {
        super(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[] {EquipmentSlot.FEET});
    }

    @Override
    public Consumer<Multimap<Attribute, AttributeModifier>> getArmorModifiers(int level, ItemStack enchanted, EquipmentSlot slot) {
        return multimap -> multimap.put(ExtraAttributes.DOUBLE_JUMP.get(), AttributeHelper.createModifier("Double Jump Enchantment", AttributeModifier.Operation.ADDITION, level));
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public String[] getDescriptionMods(int level) {
        return new String[] {"+" + level};
    }

    @Override
    public List<EquipmentSlot> slots() {
        return List.of(EquipmentSlot.FEET);
    }
}
