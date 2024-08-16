package net.kapitencraft.enchantments_plus.enchantments.weapon;

import com.google.common.collect.Multimap;
import net.kapitencraft.kap_lib.enchantments.abstracts.WeaponStatBoostEnchantment;
import net.kapitencraft.kap_lib.helpers.AttributeHelper;
import net.kapitencraft.kap_lib.registry.ModAttributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

public class ArmorShreddingEnchantment extends WeaponStatBoostEnchantment {
    public ArmorShreddingEnchantment() {
        super(Rarity.VERY_RARE);
    }

    @Override
    public Consumer<Multimap<Attribute, AttributeModifier>> getModifiers(int level, ItemStack enchanted, EquipmentSlot slot) {
        return multimap -> multimap.put(ModAttributes.ARMOR_SHREDDER.get(), AttributeHelper.createModifier("Armor Shredder Enchantment", AttributeModifier.Operation.ADDITION, level));
    }
    @Override
    public String[] getDescriptionMods(int level) {
        return new String[] {"+" + level};
    }
}