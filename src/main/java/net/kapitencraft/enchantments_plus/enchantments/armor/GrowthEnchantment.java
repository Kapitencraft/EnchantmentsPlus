package net.kapitencraft.enchantments_plus.enchantments.armor;

import com.google.common.collect.Multimap;
import net.kapitencraft.kap_lib.enchantments.abstracts.ArmorStatBoostEnchantment;
import net.kapitencraft.kap_lib.helpers.AttributeHelper;
import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import java.util.List;
import java.util.function.Consumer;

public class GrowthEnchantment extends Enchantment implements ArmorStatBoostEnchantment {
    public GrowthEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.WEARABLE, MiscHelper.ARMOR_EQUIPMENT);
    }

    public int getMaxLevel() {
        return 3;
    }

    @Override
    public Consumer<Multimap<Attribute, AttributeModifier>> getArmorModifiers(int level, ItemStack enchanted, EquipmentSlot slot) {
        return multimap -> multimap.put(Attributes.MAX_HEALTH, AttributeHelper.createModifierForSlot("growth", AttributeModifier.Operation.ADDITION, level, slot));
    }

    @Override
    public String[] getDescriptionMods(int level) {
        return new String[] {"+" + level};
    }

    @Override
    public List<EquipmentSlot> slots() {
        return List.of(MiscHelper.ARMOR_EQUIPMENT);
    }
}
