package net.kapitencraft.enchantments_plus.enchantments.armor;

import com.google.common.collect.Multimap;
import net.kapitencraft.kap_lib.enchantments.abstracts.ArmorStatBoostEnchantment;
import net.kapitencraft.kap_lib.helpers.AttributeHelper;
import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.kapitencraft.kap_lib.registry.ExtraAttributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import java.util.function.Consumer;

public class RejuvenateEnchantment extends ArmorStatBoostEnchantment {
    public RejuvenateEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentCategory.WEARABLE, MiscHelper.ARMOR_EQUIPMENT);
    }

    public int getMaxLevel() {
        return 10;
    }

    @Override
    public Consumer<Multimap<Attribute, AttributeModifier>> getArmorModifiers(int level, ItemStack enchanted, EquipmentSlot slot) {
        return multimap -> multimap.put(ExtraAttributes.HEALTH_REGEN.get(), AttributeHelper.createModifierForSlot("rejuvenate", AttributeModifier.Operation.ADDITION, level * 2, slot));
    }


    @Override
    public String[] getDescriptionMods(int level) {
        return new String[] {"+" + level*2};
    }
}