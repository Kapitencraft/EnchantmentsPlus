package net.kapitencraft.enchantments_plus.enchantments.armor;

import com.google.common.collect.Multimap;
import net.kapitencraft.kap_lib.enchantments.abstracts.ArmorStatBoostEnchantment;
import net.kapitencraft.kap_lib.helpers.AttributeHelper;
import net.kapitencraft.kap_lib.helpers.MathHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import java.util.function.Consumer;

public class FirmStandEnchantment extends ArmorStatBoostEnchantment {
    public FirmStandEnchantment() {
        super(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.WEARABLE, EquipmentSlot.FEET, EquipmentSlot.LEGS);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public Consumer<Multimap<Attribute, AttributeModifier>> getArmorModifiers(int level, ItemStack enchanted, EquipmentSlot slot) {
        return multimap -> multimap.put(Attributes.KNOCKBACK_RESISTANCE, AttributeHelper.createModifierForSlot("Firm Stand Enchantment", AttributeModifier.Operation.ADDITION, level, slot));
    }

    @Override
    public String[] getDescriptionMods(int level) {
        return new String[]{"+" + MathHelper.defRound(level*0.1)};
    }
}
