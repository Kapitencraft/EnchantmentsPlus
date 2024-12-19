package net.kapitencraft.enchantments_plus.enchantments.weapon.ranged;

import com.google.common.collect.Multimap;
import net.kapitencraft.kap_lib.enchantments.abstracts.IUltimateEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.StatBoostEnchantment;
import net.kapitencraft.kap_lib.helpers.AttributeHelper;
import net.kapitencraft.kap_lib.registry.ExtraAttributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import java.util.function.Consumer;

public class WindBlessingEnchantment extends StatBoostEnchantment implements IUltimateEnchantment {

    public WindBlessingEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.BOW);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public Consumer<Multimap<Attribute, AttributeModifier>> getModifiers(int level, ItemStack enchanted, EquipmentSlot slot) {
        return multimap -> multimap.put(ExtraAttributes.ARROW_SPEED.get(), AttributeHelper.createModifier("WindBlessing", AttributeModifier.Operation.ADDITION, 10*level));
    }
}
